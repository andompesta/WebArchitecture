/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 03/01/2013
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myTripEastView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#east',
    templateName     : 'myTripEast',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','describeTrip','onBuyTicket');
        this.routedEvents = options.routedEvents;
        MyTripEvent.bind('describeTrip', this.describeTrip);
        console.log('myTripEastView initialized');

        this.render();
    },
    destroy: function(){
        $('#east').empty();
        $(this.$el).off("click");
        MyTripEvent.unbind('describeTrip', this.describeTrip );
        this.unbind();
    },
    resizeView : function() {

    },
    /**
     * appHeaderView served events
     */
    events                  : {
        "click [id=btBuy]"            : "onBuyTicket"
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myTripEastView render');

        // Render the view
        var _this = this;
        try {
            // Get the DOM element
            var $el = $(this.el);
            if (this.model.get('tripLeg') != undefined) {
                var template_string = templateLoader.load(this.templateName,'apps/TRIP/templates');
                if (template_string != undefined) {
                    // Compile the template using underscore
                    var compiled_template = _.template(template_string, this.model.attributes);
                    // inject the template
                    $el.html(compiled_template);
                }
            }
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myTripEastView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    describeTrip : function(resultPath) {
        console.log(resultPath.routes[0].legs[0]);
        this.model.set({'tripLeg':resultPath.routes[0].legs[0]});
        this.render()
        /*
        if(this.model.get('tickets') == undefined || this.model.get('tickets').length == 0){
            $('#btBuy').attr("disabled", "true");
        }
        else
        {
            $('#btBuy').attr("disabled", "false");
        }
        */
    },
    onBuyTicket : function(evnt) {
        if (app.appStatus == 'off') {
            notifier.warning("Acquisto impossibile","Eseguire il logon per gestire pagamenti");
            return;
        }

        app.doLaunchApp('PAYBILL',this.model.get('tickets'));
    }
});