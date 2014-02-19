/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 09/01/2013
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myPaymentsCenterView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#center',
    templateName     : 'myPaymentsCenter',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','listCard');
        this.routedEvents = options.routedEvents;
        MyTripEvent.bind('listCard', this.listCard);
        console.log('MyTrip.myPaymentsCenterView initialized');
        this.render();
    },
    destroy: function(){
        $('#center').empty();
        MyTripEvent.unbind('listCard', this.listCard );
        this.unbind();
    },
    resizeView : function() {

    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myPaymentsCenterView render');

        // Render the view
        var _this = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            if (this.model.get('conto') == undefined) {
                this.model.set({'conto':{'tipo':'','idConto':'','codiceCarta':'','saldo':0}});
            }
            if (this.model.get('movimenti') == undefined) {
                this.model.set({'movimenti':[]});
            }
            var template_string = templateLoader.load(this.templateName,'apps/PAYMENTS/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myPaymentsCenterView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    listCard : function(card) {
        console.log(card);

        this.model.set({conto:card});

        var params =  {
            "uuid" :  app.appContext.user.uuid,
            "conto" : card
        };
        var self = this;
        this.model.callService('account','getMovimenti',params, $('#container'), function(result) {
            self.movimentiDone(result);
        });
    },
    movimentiDone : function(result) {
        if (result != undefined && result.length > 0) {
            if (result[0].rc != 0) {
                notifier.error("Errore rimozione carta",result[0].description,"MyTrip.myNewCardView.newCardDone");
                return;
            }
            this.model.set({'movimenti': result});
            this.render();
        } else {
            this.model.set({'movimenti': []});
            this.render();
        }
    }
});