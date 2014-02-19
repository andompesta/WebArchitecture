/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 09/01/2013
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myPaymentsWestView  = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#west',
    templateName     : 'myPaymentsWest',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','onSelectCard','addCard','removeCard');
        this.routedEvents = options.routedEvents;
        this.render();
    },
    destroy: function(){
        $(this.$el).off("click");
        $('#west').empty();
        this.unbind();
    },
    /**
     * form served events
     */
    events                  : {
        "click [id=payments] tr td input": "onSelectCard",
        "click [id=addCard]" : "addCard",
        "click [id=removeCard]" : "removeCard"
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myPaymentsWestView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            this.model.set({'conti': app.appContext.user.contoList});
            var template_string = templateLoader.load(this.templateName,'apps/PAYMENTS/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myTripWestView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    updateView : function() {
        this.render();
    },
    onSelectCard : function(evnt) {
        console.log(evnt);
        var idCard = evnt.currentTarget.getAttribute('value');
        for (var i = 0; i < app.appContext.user.contoList.length; i++) {
            if (app.appContext.user.contoList[i].idConto == idCard ) {
                this.showCard(app.appContext.user.contoList[i]);
                this.selectedCard = app.appContext.user.contoList[i];
                break;
            }
        }
    },
    showCard : function(card) {
        /*
        alert(card.tipo);
        alert(card.codiceCarta);
        alert(card.saldo);
        */
        switch (app.deviceType) {
            case 'iPad':
                if (app.deviceOrientation == 'portrait') {
                    app.closeSidebar(function() {
                        MyTripEvent.trigger('listCard',card);
                    });
                } else {
                    MyTripEvent.trigger('listCard',card);
                }
                break;
            case 'iPhone':
                app.closeSidebar(function() {
                    MyTripEvent.trigger('listCard',card);
                });
                break;
            case 'win':
                if (app.deviceOrientation == 'portrait')
                    app.closeSidebar(function() {
                        MyTripEvent.trigger('listCard',card);

                    });
                else
                    MyTripEvent.trigger('listCard',card);
                break;
            default :
                break;
        }

    },
    addCard : function(evnt) {
        app.doLaunchApp('NEWCARD');
    },
    removeCard : function(evnt) {
        if ( this.selectedCard == undefined) {
            notifier.warning("Carta non selezionata","Selezionare una carta prego");
            return;
        }
        if (confirm('Sicuro di voler eleiminare la carta : '+ this.selectedCard.tipo) ) {
            var params =  {
                "uuid" :  app.appContext.user.uuid,
                "conto" : {
                    "tipo" : this.selectedCard.tipo,
                    "codiceCarta" : this.selectedCard.codiceCarta,
                    "saldo" : this.selectedCard.saldo,
                    "idConto" : this.selectedCard.idConto
                }
            };
            var self = this;
            this.model.callService('account','removeCard',params, $('#container'), function(result) {
                self.revomeCardDone(result);
            });
        }
    },
    revomeCardDone : function(result) {
        if (result.rc != 0) {
            notifier.error("Errore rimozione carta",result.description,"MyTrip.myNewCardView.newCardDone");
            return;
        }
        app.appContext.user = result;
        this.render();
    }
});