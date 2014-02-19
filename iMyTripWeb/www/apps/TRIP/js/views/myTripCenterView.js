/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 03/01/2013
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myTripCenterView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#center',
    templateName     : 'myTripCenter',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','renderTrip' ,'renderMyPos','showPosition');
        this.routedEvents = options.routedEvents;
        MyTripEvent.bind('renderTrip', this.renderTrip);
        MyTripEvent.bind('renderMyPos', this.renderMyPos);
        console.log('myTripCenterView initialized');
        this.render();
    },
    destroy: function(){
        app.closeRightBar();
        $('#map_canvas').remove();
        $('#center').empty();
        MyTripEvent.unbind('renderTrip', this.renderTrip );
        MyTripEvent.unbind('renderMyPos', this.renderMyPos );
        this.unbind();
    },
    resizeView : function() {

    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myTripCenterView render');

        // Render the view
        var _this = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/TRIP/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }
            this.getLocation();
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myTripCenterView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    /**
     * Compute actual location
     */
    getLocation : function ()
    {
        if (navigator.geolocation)
        {
            navigator.geolocation.getCurrentPosition(this.showPosition);
        } else {
            notifier.exception("Geolocation is not supported by this browser.",null,"MyTrip.myTripCenterView",null)
        }
    },
    /**
     * Accepts a postiton as returned by browser
     * @param position
     */
    showPosition : function(position)
    {
        var self = this;
        var lat = position.coords.latitude;
        var long = position.coords.longitude;

        console.log(position);

        this.myPosition = new google.maps.LatLng(lat, long);
        this.showMapAtPosition(this.myPosition,true);
        /*
        setTimeout(function() {
            self.renderMyPos(self.myPosition, 'library/images/meMarker.png');
        }, 500);
        */
    },
    /**
     * Accepts a position in form google.maps.LatLng
     * @param myPosition
     */
    showMapAtPosition : function(myPosition,resetMarker) {
        var self = this;
        if (app.mapObject == undefined) {
            setTimeout(function() {
                self.directionsDisplay = new google.maps.DirectionsRenderer();
                self.myLocation = myPosition;
                var mapOptions = {
                    zoom:14,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    center: myPosition
                }
                app.mapObject = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
                self.directionsDisplay.setMap(app.mapObject);


            }, 200);

        } else {
            if (this.marker != undefined && resetMarker == true) {
                this.marker.setMap(null);
            }
            app.mapObject.setCenter(myPosition);
        }

    },
    renderTrip : function(searchParams) {
        console.log(searchParams);

        var self = this;
        setTimeout(function() {
            self.doRouteOnMap(searchParams);
        }, 200);
    },
    renderMyPos : function(aPosition) {
        if (this.model.myMarker != undefined) {
            this.model.myMarker.setMap(null);
        }
        this.model.myMarker = new google.maps.Marker({
            position: aPosition,
            map: app.mapObject,
            icon: 'library/images/meMarker.png',
            draggable: false
        });
    },
    doRouteOnMap : function(searchParams) {

        // searchParams = {"departure" : departureInfo, "arrival" : arrivalInfo , "time" : millSeconds , "transportType" : this.model.transportType };
        var fromPos = '';
        var toPos = '';
        switch(searchParams.departure.mode) {
            case  "POSITION":
            case  "PREFERRED":
                fromPos = searchParams.departure.position.position.lat() + ', ' + searchParams.departure.position.position.lng();
                break;
            case  "ADDRESS":
                fromPos = searchParams.departure.position;
                break;
        }
        switch(searchParams.arrival.mode) {
            case  "POSITION":
            case  "PREFERRED":
                toPos = searchParams.arrival.position.position.lat() + ', ' + searchParams.arrival.position.position.lng();
                break;
            case  "ADDRESS":
                toPos = searchParams.arrival.position;
                break;
        }
        var params = {
            "Sensor":"false",
            "Origin": fromPos,
            "Destination": toPos,
            "Mode": searchParams.transportType,
            "DepartureTimeMs": searchParams.time,
            "ArrivalTimeMs":0
        };

        var self = this;
        this.model.callService('route','computeRoute',params, $('#container'), function(result) {
            self.routeFound(result);
            // call doTicket service
        });
    },
    routeFound : function(result) {
        //aggiornare la route
        if (result.rc != 0) {
            notifier.error("Error in computeRoute ",result.description,"MyTrip.myTripCenterView.routeFound");
            return;
        }

        if (this.model.get('flightPaths') == undefined) {
            this.model.set({'flightPaths': []});
        }
        for (var j = 0; j < this.model.get('flightPaths').length; j++) {
            this.model.get('flightPaths')[j].setPath([]);
        }
        this.model.set({'flightPaths': []});
        this.clearMarkers();

        var resultPath= JSON.parse(result.description);
        if (resultPath.status != 'OK') {
            notifier.warning("Errore in ricerca percorso", resultPath.status);
            return;
        }
        // call a new service for calculate the ticket
        this.doTicket(result);
        // trigger an event on east frame
        MyTripEvent.trigger('describeTrip',resultPath);

        console.log(resultPath);
        // drowe path
        for (var i = 0; i < resultPath.routes[0].legs[0].steps.length; i++) {
            console.log(resultPath.routes[0].legs[0].steps[i]);
            // alert(resultPath.routes[0].legs[0].steps[i].distance.text + ' ' + resultPath.routes[0].legs[0].steps[i].duration.text + ' ' + resultPath.routes[0].legs[0].steps[i].travel_mode);
            if (resultPath.routes[0].legs[0].steps[i].start_location != undefined) {
                var plcolor = "#FF0000";
                var icon = 'library/images/bus.png';

                switch(resultPath.routes[0].legs[0].steps[i].travel_mode) {
                    case 'WALKING':
                        plcolor = "#00FF00";
                        icon = 'library/images/walk.png';
                        break;
                    case 'DRIVING':
                        plcolor = "#0000FF";
                        icon = 'library/images/car.png';
                        break;
                    default:
                        break;
                }
                var descr = '<p>' + resultPath.routes[0].legs[0].steps[i].html_instructions + '</p>' +
                            '<p>Distanza: ' + resultPath.routes[0].legs[0].steps[i].distance.text + '</p>' +
                            '<p>Tempo percorrenza: ' + resultPath.routes[0].legs[0].steps[i].duration.text + '</p>';

                if (resultPath.routes[0].legs[0].steps[i].transit_details != undefined) {
                    descr += '<p><b>Sali a         : ' +  resultPath.routes[0].legs[0].steps[i].transit_details.departure_stop.name +'</b></p>';
                    descr += '<p><b>Scendi a       : ' +  resultPath.routes[0].legs[0].steps[i].transit_details.arrival_stop.name +'</b></p>';
                    descr += '<p><b>Numero fermate : ' +  resultPath.routes[0].legs[0].steps[i].transit_details.num_stops +'</b></p>';
                    descr += '<p><b>Orario partenza: ' +  resultPath.routes[0].legs[0].steps[i].transit_details.departure_time.text +'</b></p>';
                    descr += '<p><b>Orario arrivo  : ' +  resultPath.routes[0].legs[0].steps[i].transit_details.arrival_time.text +'</b></p>';

                }
                this.placeMarker(resultPath.routes[0].legs[0].steps[i].start_location,descr,icon);

                var flightPlanCoordinates = this.decodeLine( resultPath.routes[0].legs[0].steps[i].polyline.points);
                var flightPath = new google.maps.Polyline({
                    path: flightPlanCoordinates,
                    strokeColor: plcolor,
                    strokeOpacity: 1.0,
                    strokeWeight: 2
                });
                this.model.get('flightPaths').push(flightPath);
                flightPath.setMap(app.mapObject);
            }

        }
    },
    // http://facstaff.unca.edu/mcmcclur/googlemaps/encodepolyline/decode.js
    // This function is from Google's polyline utility.
    decodeLine : function(encoded) {
        var len = encoded.length;
        var index = 0;
        var array = [];
        var lat = 0;
        var lng = 0;

        while (index < len) {
            var b;
            var shift = 0;
            var result = 0;
            do {
                b = encoded.charCodeAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            var dlat = ((result & 1) ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charCodeAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            var dlng = ((result & 1) ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            // LJR modified to return array of LatLngs
            array.push(new google.maps.LatLng(lat * 1e-5, lng * 1e-5));
        }

        return array;
    },
    clearMarkers : function() {
        if (this.model.get('markers') != undefined) {
            for (var i = 0; i < this.model.get('markers').length; i++)
            {
                this.model.get('markers')[i].setMap(null);
            }
        }
    },
    placeMarker : function (position, description, icon) {
        if (this.model.get('markers') == undefined) {
            this.model.set({'markers':[]});
        }
        var aPosition = new google.maps.LatLng(position.lat, position.lng);
        var marker = new google.maps.Marker({
            position: aPosition,
            map: app.mapObject,
            icon: icon,
            draggable: false
        });
        this.model.get('markers').push(marker);
        var infowindow = new google.maps.InfoWindow({
            content: description,
            size: new google.maps.Size(150,100)
        });
        var self = this;
        google.maps.event.addListener(marker, 'click', function(evnt) {
            infowindow.open(app.mapObject, marker);
        });
    },
    doTicket : function (result){
        var self = this;
        if(self.model.get('tickets') != undefined){
            self.model.set({'tickets':[]});
        }
        var resultJSON = JSON.parse(result.description);
        if (resultJSON.status != 'OK') {
            notifier.warning("Errore in ricerca percorso", resultPath.status);
            return;
        }
        var steps = [];
        for (var i = 0; i < resultJSON.routes[0].legs[0].steps.length; i++) {
            var line = '';
            var agency = '';
            var busnr = '';
            if(resultJSON.routes[0].legs[0].steps[i].transit_details != undefined) {
                line = resultJSON.routes[0].legs[0].steps[i].transit_details.line.name;
                agency = resultJSON.routes[0].legs[0].steps[i].transit_details.line.agencies[0].name;
                busnr = resultJSON.routes[0].legs[0].steps[i].transit_details.line.short_name;
            }
            var step = {
                "distance": resultJSON.routes[0].legs[0].steps[i].distance.text,
                "duration": resultJSON.routes[0].legs[0].steps[i].duration.text,
                "travelMode": resultJSON.routes[0].legs[0].steps[i].travel_mode,
                "line": line,
                "agency": agency

            };
            //if (step.travelMode == "TRANSIT")
                steps.push(step);
        }
        var param = {
            "step":steps
        }
        console.log(param);
        this.model.callService('route','computeTicket',param, $('#container'), function(result) {
            console.log("ticket:");
            console.log(result);
                // trigger an event on east frame
            if(result.rc != undefined && result.rc != 0){
                notifier.warning('Errore nel caloclo dei ticket', result.description ,'MyTrip.myTripCenterView doTicket' ,null);
            }
            else{
                if(result.rc == 0){
                    self.model.set({'tickets':result});
                }
            }
        });
    }
});