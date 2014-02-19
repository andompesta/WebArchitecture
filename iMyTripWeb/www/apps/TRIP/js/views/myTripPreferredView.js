/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 04/01/2013
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myTripPreferredView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#west',
    templateName     : 'myTripPreferred',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
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
        "click a[id=navPersonal]"       : "showPersonal",
        "click [id=btClose]"            : "onClose"
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myTripPreferredView render');

        // Render the view
        var _this = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/TRIP/templates');
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
                $('#searchTo').html(this.model.searchMode);
            }
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myTripPreferredView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
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
        var description = evnt.srcElement.innerText;
        var position = JSON.parse(strCoordinates);
        if (position != undefined) {
            var lat = position.lat,
                long = position.long;
            myPosition = new google.maps.LatLng(lat, long);
        }
        console.log(myPosition);
        if (this.model.searchType == 'ARRIVAL')
            this.model.searchArrivalPos = {"position": myPosition, "description": description};
        else
            this.model.searchDeparturePos = {"position": myPosition, "description": description};
        this.doClose();
    },
    onClose :  function(evnt) {
        this.doClose();
    },
    doClose : function() {
        this.destroy();
        var myTripWest = new MyTrip.myTripWestView({model: this.model});

    }
});