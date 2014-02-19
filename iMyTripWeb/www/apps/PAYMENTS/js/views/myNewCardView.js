/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 09/01/2013
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myNewCardView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'myNewCard',
    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('MyTrip.myNewCardView initialized');
        this.render();
    },
    destroy: function(){
        $('#frmNewCard').unbind('submit');
        $('#simplemodal').empty();
        this.unbind();
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myNewCardView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/PAYMENTS/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }
            $('#frmNewCard').submit(function(event)
            {
                console.log('SUBMIT');
                self.submitNewCard();
                return false;
            });
            $('#frmNewCard').bind("reset", function() {
                self.destroy();
                $.modal.close();
                return false;
            });
            setTimeout(function() {
                $('#tipoCarta').focus();
            }, 100);

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myNewCardView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    submitNewCard: function() {
        this.tipoCarta = $('#tipoCarta').val();
        this.codiceCarta = $('#newCodiceCarta').val();
        if (this.tipoCarta.trim() == "") {
            notifier.warning("Mancanza definizione tipo cata","Inserire il campo tipo carta");
            return;
        }
        if (this.codiceCarta.trim() == "") {
            notifier.warning("Mancanza Nr. carta","Inserire il campo Numero Carta");
            return;
        }
        var params =  {
            "uuid" :  app.appContext.user.uuid,
            "conto" : {
                "tipo" : this.tipoCarta,
                "codiceCarta" : this.codiceCarta,
                "saldo" : 0
            }
        };
        var self = this;
        this.model.callService('account','addCard',params, $('#container'), function(result) {
            self.newCardDone(result);
        });
    },
    newCardDone : function(result) {
        if (result.rc != 0) {
            notifier.error("Errore nuovo mezzo pagamento",result.description,"MyTrip.myNewCardView.newCardDone");
            return;
        }
        app.appContext.user = result;
        app.updateViews();
        this.destroy();
        $.modal.close();
    }
});