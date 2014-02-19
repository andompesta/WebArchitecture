/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/27/12
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myPosWestView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#west',
    templateName     : 'myPosWestView',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        this.model.set({"generalPosition":[]});
        // load global positions
        this.loadGlobalPositions();
        this.render();
    },
    destroy: function(){
        $('#west').empty();
        $(this.$el).off("click");
        this.unbind();
    },
    /**
     * appHeaderView served events
     */
    events                  : {
        "click ul[id=listArea]     li a": "menuAreaSelection",
        "click ul[id=listPersonal] li a": "menuPersonalSelection",
        "click a[id=navArea]"           : "showArea",
        "click a[id=navPersonal]"       : "showPersonal"
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myPosWestView render');

        // Render the view
        var _this = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/MYPOS/templates');
            var personalPositions = [];
            if (app.appContext.user != undefined &&
                app.appContext.user.localitaPrivataList  != undefined ){
                personalPositions = app.appContext.user.localitaPrivataList;
            }
            if (app.appContext.generalPosition == undefined) {
                app.appContext.generalPosition = [];
            }
            this.model.set({"personalPositions":personalPositions});
            this.model.set({"generalPosition":app.appContext.generalPosition});


            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
                setTimeout(function() {
                    MyTripEvent.trigger('renderPosition',null,null);
                }, 500);
            }
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.loginView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    updateView : function(params) {
        this.render();
    },
    loadGlobalPositions : function() {
        var self = this;
        this.model.getService('account','generalPosition',null, $('#container'), function(result) {
            self.globalPositionLoaded(result);
        });
    },
    globalPositionLoaded : function (result) {
        //aggiornare la posizione globale
        if (result.rc != undefined && result.rc != 0) {
            notifier.error("Error in generalPosition  ",result.description,"MyTrip.myPosWestView.globalPositionLoaded");

            return;
        } else {
            app.appContext.generalPosition = result;
            this.render();
        }
    },
    showArea : function(evnt) {
        var id = evnt.srcElement.id;
        $('#listArea').show();
        $('#listPersonal').hide();
        $('#navArea').parent().addClass('active');
        $('#navPersonal').parent().removeClass('active');
    },
    showPersonal : function(evnt) {
        var id = evnt.srcElement.id;
        if(app.appStatus == 'off') {
            notifier.warning("Dati non disponibili","Le località personali sono accedibili dopo essersi connessi al sistema (login)")
            return;
        }
        $('#listArea').hide();
        $('#listPersonal').show();
        $('#navArea').parent().removeClass('active');
        $('#navPersonal').parent().addClass('active');
    },
    menuAreaSelection : function(evnt) {
        this.menuSelection(evnt);
    },
    menuPersonalSelection  : function(evnt) {
        this.menuSelection(evnt);
    },
    menuSelection : function(evnt) {

        var myPosition = null;
        var strCoordinates = evnt.srcElement.attributes['data'].value;
        var position = JSON.parse(strCoordinates);
        var descrizione = 'n.d.';
        var removable = false;
        var idLocalita = -1;

        if (position != undefined) {
            var lat = position.lat,
                long = position.long;
            myPosition = new google.maps.LatLng(lat, long);
            descrizione = position.descr;
            removable = position.removable;
            idLocalita = position.idLocalita;
        }
        console.log(myPosition);

        var infoDescr = {"idLocalita":idLocalita, "desc" : descrizione, "removable" : removable};
        switch (app.deviceType) {
            case 'iPad':
                if (app.deviceOrientation == 'portrait') {
                    app.closeSidebar(function() {
                        MyTripEvent.trigger('renderPosition',myPosition,infoDescr);
                    });
                } else {
                    MyTripEvent.trigger('renderPosition',myPosition,infoDescr);
                }
                break;
            case 'iPhone':
                app.closeSidebar(function() {
                    MyTripEvent.trigger('renderPosition',myPosition,infoDescr);
                });
                break;
            case 'win':
                if (app.deviceOrientation == 'portrait')
                    app.closeSidebar(function() {
                        MyTripEvent.trigger('renderPosition',myPosition,infoDescr);

                    });
                else
                    MyTripEvent.trigger('renderPosition',myPosition,infoDescr);
                break;
            default :
                break;
        }
    }
});