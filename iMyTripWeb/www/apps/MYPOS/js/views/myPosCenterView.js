/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/27/12
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myPosCenterView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#center',
    templateName     : 'myPosCenterView',
    myLocation       : null,                    // my actual location
    directionsDisplay: null,                    // direction display
    marker           : null,                    // the current marker
    myPosition       : null,                    // the actual position
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','showPosition', 'renderPosition','rimuoviPunto' );
        this.routedEvents = options.routedEvents;
        console.log('myPosCenterView initialized');
        MyTripEvent.bind('renderPosition', this.renderPosition);
        this.render();
    },
    destroy: function(){
        $(this.$el).off("click");
        $('#map_canvas').remove();
        $('#center').empty();
        MyTripEvent.unbind('renderPosition', this.renderPosition );
        this.unbind();
    },
    resizeView : function() {
        if (this.myPosition != undefined)
            this.showMapAtPosition(this.myPosition,false);
    },
    /**
     * appHeaderView served events
     */
    events  : {
        "click a[id=btRimuovi]"  : "rimuoviPunto"
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myPosCenterView render');

        // Render the view
        var _this = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/MYPOS/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }
            this.getLocation();
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myPosCenterView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    /**
     * Remote event used to reposition the map at given position
     * if position is undefined go to actual browser location
     * @param myPosition as google.maps.LatLng
     */
    renderPosition : function(myPosition,descr) {

        if (myPosition == undefined) {
            this.getLocation();
        } else {
            this.myPosition = myPosition;
            this.showMapAtPosition(this.myPosition,true);
            this.placeMarker(this.myPosition,null,descr);
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
            notifier.exception("Geolocation is not supported by this browser.",null,"MyTrip.myPosCenterView",null)
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
        setTimeout(function() {
            self.placeMarker(self.myPosition, 'library/images/meMarker.png');
        }, 500);
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

                google.maps.event.addListener(app.mapObject, "rightclick", function(event) {
                    var lat = event.latLng.lat();
                    var lng = event.latLng.lng();

                    var params = {"latitude": lat,"longitude":lng };

                    // populate yor box/field with lat, lng
                    // alert("{\"lat\":" + lat + ", \"long\":" + lng + "}");
                    app.doLaunchApp('POSITION',params);
                });
            }, 200);

        } else {
            if (this.marker != undefined && resetMarker == true) {
                this.marker.setMap(null);
            }
            app.mapObject.setCenter(myPosition);
        }

    },
    placeMarker : function (myPosition, icon, descr) {
        if (this.marker != undefined) {
            this.marker.setMap(null);
        }
        var infomsg = '';
        var canRemove = false;
        if (descr != undefined) {
            infomsg = descr.desc;
            canRemove = descr.removable;
        }
        if (icon == undefined) {
            this.marker = new google.maps.Marker({
                position: myPosition,
                map: app.mapObject,
                draggable: false
            });
            var message = infomsg;
            if (canRemove == true) {
                message += "<br><a href='#' id='btRimuovi' data='" + descr.idLocalita + "' >Rimuovi</a> ";
            }
            var infowindow = new google.maps.InfoWindow({
                    content: message,
                    size: new google.maps.Size(50,50)
                });
            var self = this;
            google.maps.event.addListener(this.marker, 'click', function(evnt) {
                console.log(evnt);
                infowindow.open(app.mapObject,self.marker);
            });
        } else {
            this.marker = new google.maps.Marker({
                position: myPosition,
                icon: icon,
                map: app.mapObject,
                draggable: false,
                title: 'Dove mi trovo'
            });

        }
    },
    rimuoviPunto : function(evnt) {

        var id = evnt.currentTarget.getAttribute('data');

        var toRemove = null;
        for (var i=0; i < app.appContext.user.localitaPrivataList.length; i++) {
            if (app.appContext.user.localitaPrivataList[i].idLocalita == id) {
                toRemove = app.appContext.user.localitaPrivataList[i];
                break;
            }
        }
        if (toRemove == undefined) {
            return;
        }
        if (confirm('Sicuro di voler eliminare la località : ' + toRemove.descrizione + '?')) {
            var param = {
                "uuid":app.appContext.user.uuid,
                "idLocalita": toRemove.idLocalita
            }
            console.log(param);
            var self = this;
            this.model.callService('account','removeLocation',param, $('#container'), function(result) {
                self.removeDone(result);
            });
        }
        return false;
    },
    removeDone : function(result) {
        if (result.rc == -1) {
            notifier.error("Errore in rimazione Località",result.description,"MyTrip.myPosCenterView.removeDone")
            return;
        } else {
            this.marker.setMap(null);
            app.appContext.user = result;
            app.updateViews();
        }
    }
});