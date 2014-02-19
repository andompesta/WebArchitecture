/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 03/01/2013
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myTripWestView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#west',
    templateName     : 'myTripWest',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','showPosition','onSearchPath','onTransportationMode','onDepartureType','onArrivalType');
        this.routedEvents = options.routedEvents;
        this.model.transportationMode = 'transit';

        $('#departureMode').html('Indirizzo');
        $('#departureAddress').removeAttr("disabled");
        this.model.departureType = 'ADDRESS';

        $('#arrivalMode').html('Indirizzo');
        $('#arrivalAddress').removeAttr("disabled");
        this.model.arrivalType = 'ADDRESS';
        this.render();
    },
    destroy: function(){
        $('#west').empty();
        this.unbind();
    },
    /**
     * form served events
     */
    events                  : {
        "click [id=buttOk]"             : "onSearchPath",
        "click [id=transportType]"      : "onTransportationMode",
        "click [id=departureType] li a" : "onDepartureType",
        "click [id=arrivalType] li a"   : "onArrivalType"
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myTripWestView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/TRIP/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);

                var oggi = new Date();
                var gg = '';
                var hh = '';
                var mm = '';
                if (oggi.getDate()  < 10) {
                    gg = '0' + oggi.getDate();
                } else {
                    gg = '' + oggi.getDate();
                }
                if (oggi.getHours() < 10) {
                    hh = '0' + oggi.getHours();
                } else {
                    hh = '' + oggi.getHours();
                }
                if (oggi.getMinutes() < 10) {
                    mm = '0' + oggi.getMinutes();
                } else {
                    mm = '' + oggi.getMinutes();
                }
                $('#data').val(oggi.getFullYear() + '-' + oggi.getMonth() + 1 + '-' + gg);
                $('#orario').val(hh + ":" + mm);

                var depart = document.getElementById('departureAddress');
                var arrival = document.getElementById('arrivalAddress');
                var options = {
                    componentRestrictions: {country: 'it'}
                };

                var autoCompleteDepart = new google.maps.places.Autocomplete(depart, options);
                var autoCompleteArrival = new google.maps.places.Autocomplete(arrival, options);

                if (this.model.searchDeparturePos != undefined) {
                    if ( this.model.searchDeparturePos.description == "Dove sono ora") {
                        $('#departureMode').html('Posizione attuale');
                        this.model.searchDeparturePos.position = this.model.myPosition.position;
                        $('#departureAddress').attr("disabled", "disabled");
                        $('#departureAddress').val('* ' + this.model.searchDeparturePos.description);
                        this.model.departureType = 'POSITION';
                    } else {
                        $('#departureMode').html('Preferiti');
                        $('#departureAddress').attr("disabled", "disabled");
                        $('#departureAddress').val('* ' + this.model.searchDeparturePos.description);
                        this.model.departureType = 'PREFERRED';
                    }
                }

                if (this.model.searchArrivalPos != undefined) {
                    if ( this.model.searchArrivalPos.description == "Dove sono ora") {
                        $('#arrivalMode').html('Posizione attuale');
                        this.model.searchArrivalPos.position = this.model.myPosition.position;
                        $('#departureAddress').attr("disabled", "disabled");
                        $('#departureAddress').val('* ' + this.model.searchDeparturePos.description);
                        this.model.arrivalType = 'POSITION';
                    } else {
                        $('#arrivalMode').html('Preferiti');
                        $('#arrivalAddress').attr("disabled", "disabled");
                        $('#arrivalAddress').val('* ' + this.model.searchArrivalPos.description);
                        this.model.arrivalType = 'PREFERRED';
                    }
                }
                if (this.model.myPosition == undefined) {
                    this.getLocation();
                }
            }

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myTripWestView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    onSearchPath : function(evnt) {
        var departureInfo = null;
        var arrivalInfo = null;
        switch(this.model.departureType) {
            case 'ADDRESS':
                departureInfo = {"mode":this.model.departureType, "position": $('#departureAddress').val() };
                break;
            case 'PREFERRED':
                departureInfo = {"mode":this.model.departureType, "position": this.model.searchDeparturePos };
                break;
            case 'POSITION':
                departureInfo = {"mode":this.model.departureType, "position": this.model.searchDeparturePos };
                break;
        }
        switch(this.model.arrivalType) {
            case 'ADDRESS':
                arrivalInfo = {"mode":this.model.arrivalType, "position": $('#arrivalAddress').val() };
                break;
            case 'PREFERRED':
                arrivalInfo = {"mode":this.model.arrivalType, "position": this.model.searchArrivalPos };
                break;
            case 'POSITION':
                arrivalInfo = {"mode":this.model.arrivalType, "position": this.model.searchArrivalPos };
                break;
        }

                                           
        var data = $('#data').val();
        var ora = $('#orario').val();
        // new Date(year, month, day, hours, minutes, seconds, milliseconds)
        var dt = new Date(data.substr(0,4),data.substr(5,2),data.substr(8,2),ora.substr(0,2),ora.substr(3,2),0,0);
        var millSeconds = dt.getTime() / 1000;

        // alert(millSeconds);
                                           
        searchParams = {"departure" : departureInfo, "arrival" : arrivalInfo , "time" : millSeconds , "transportType" : this.model.transportationMode };
        // Call event on myTripCentreView
        switch (app.deviceType) {
            case 'iPad':
                if (app.deviceOrientation == 'portrait') {
                    app.closeSidebar(function() {
                        MyTripEvent.trigger('renderTrip',searchParams);
                    });
                } else {
                    MyTripEvent.trigger('renderTrip',searchParams);
                }
                break;
            case 'iPhone':
                app.closeSidebar(function() {
                    MyTripEvent.trigger('renderTrip',searchParams);
                });
                break;
            case 'win':
                if (app.deviceOrientation == 'portrait')
                    app.closeSidebar(function() {
                        MyTripEvent.trigger('renderTrip',searchParams);

                    });
                else
                    MyTripEvent.trigger('renderTrip',searchParams);
                break;
            default :
                break;
        }
    },
    /**
     *
     * When you calculate directions, you may specify which transportation mode to use.
     * By default, directions are calculated as driving directions. The following travel modes are currently supported:
     *
     *  driving (default) indicates standard driving directions using the road network.
     *  walking     requests walking directions via pedestrian paths & sidewalks (where available).
     *  bicycling   requests bicycling directions via bicycle paths & preferred streets (where available).
     *  transit     requests directions via public transit routes (where available).
     */
    onTransportationMode : function(evnt) {
        var butType = '';
        var butElem = null;
        if (evnt.srcElement.localName == 'img') {
            butType = evnt.srcElement.offsetParent.attributes['data-content'].nodeValue;
            butElem = evnt.srcElement.offsetParent;
        } else {
            butType = evnt.srcElement.attributes['data-content'].nodeValue;
            butElem = evnt.srcElement;
        }
        for (var i = 0; i < butElem.parentElement.children.length; i++) {
            butElem.parentElement.children[i].attributes['class'].nodeValue="btn";
        }
        butElem.attributes['class'].nodeValue="btn active";
        this.model.transportationMode = butType;
    },
    onDepartureType : function(evnt) {
        var departureType= evnt.srcElement.attributes['data-content'].nodeValue;
        $('#departureMode')[0].innerText = evnt.srcElement.innerText;
        switch(departureType) {
            case 'ADDRESS':
                $('#departureAddress').show();
                $('#departureAddress').removeAttr("disabled");
                $('#departureAddress').val('');
                $('#departureAddress').focus();
                this.model.departureType = 'ADDRESS';
                break;
            case 'POSITION':
                $('#departureAddress').show();
                $('#departureAddress').attr("disabled", "disabled");
                $('#departureAddress').val('* Dove sono ora');
                this.model.departureType = 'POSITION';
                this.model.searchDeparturePos = this.model.myPosition;
                this.getLocation();
                break;
            case 'PREFERRED':
                this.model.searchDeparturePos = null;
                this.model.searchMode='Cerca partenza';
                this.model.searchType='DEPARTURE';
                this.model.departureType = 'PREFERRED';
                var myTripPreferred = new MyTrip.myTripPreferredView({model: this.model});
                break;
        }
    },
    onArrivalType : function(evnt) {
        var arrivalType= evnt.srcElement.attributes['data-content'].nodeValue;
        $('#arrivalMode')[0].innerText = evnt.srcElement.innerText;

        switch(arrivalType) {
            case 'ADDRESS':
                $('#arrivalAddress').show();
                $('#arrivalAddress').removeAttr("disabled");
                $('#arrivalAddress').val('');
                $('#arrivalAddress').focus();
                this.model.arrivalType = 'ADDRESS';
                break;
            case 'POSITION':
                $('#arrivalAddress').show();
                $('#arrivalAddress').attr("disabled", "disabled");
                $('#arrivalAddress').val('* Dove sono ora');
                this.model.arrivalType = 'POSITION';
                this.model.searchArrivalPos = this.model.myPosition;
                this.getLocation();
                break;
            case 'PREFERRED':
                this.model.searchArrivalPos = null;
                this.model.searchMode='Cerca destinazione';
                this.model.searchType='ARRIVAL';
                this.model.arrivalType = 'PREFERRED';
                var myTripPreferred = new MyTrip.myTripPreferredView({model: this.model});
                break;
        }
    },
    getLocation : function () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(this.showPosition);
        } else {
            notifier.exception("Geolocation is not supported by this browser.",null,"MyTrip.myPosCenterView",null)
        }
    },
    /**
     * Accepts a postiton as returned by browser
     * @param position
     */
    showPosition : function(position)
    {
        var lat = position.coords.latitude;
        var long = position.coords.longitude;
        this.model.myPosition = {"position": new google.maps.LatLng(lat, long), "description": 'Dove sono ora'};
        MyTripEvent.trigger('renderMyPos',this.model.myPosition.position);
    }
});