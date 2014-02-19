/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 06/01/2013
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myPayBillView = MyTrip.View.extend({
    // element in template appBody.htm
    el               : '#simplemodal',
    templateName     : 'myPayBill',
    initialize       : function (options) {
        // Initialize the View
        _.bindAll(this, 'render','onBuyTicket');
        this.routedEvents = options.routedEvents;

        console.log('MyTrip.myPayBillView initialized');

        this.render();
    },
    destroy: function(){
        $('#frmPayment').unbind('submit');
        $('#frmPayment').unbind('reset');
        $(this.$el).off("click");
        $('#simplemodal').empty();
        this.unbind();
    },
    resizeView : function() {

    },
    /**
     * appHeaderView served events
     */
    events                  : {
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myPayBillView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            if (app.appContext.user.contoList == undefined) {
                notifier.error("Nessun mezzo di pagamento","Aprire un mezzo di pagamento prego");
                self.destroy();
                $.modal.close();
                return ;
            }
            if (app.appContext.user.contoList[0] != undefined) {
                this.model.set({'conti':app.appContext.user.contoList});
                for (var i = 0; i < this.model.get('conti').length; i++) {
                    var conto = this.model.get('conti')[i];
                    conto.formattedsaldo =  new NumberFormat(conto.saldo).toFormatted();
                }
                var template_string = templateLoader.load(this.templateName,'apps/PAYMENTS/templates');
                if (template_string != undefined) {
                    // Compile the template using underscore
                    var compiled_template = _.template(template_string, this.model.attributes);
                    // inject the template
                    $el.html(compiled_template);
                }
            }

            var ticketInfo = self.model.get('params');
            var text = 'Biglietto non disponibile';
            if(ticketInfo != undefined) {
                if(ticketInfo.rc != 0)
                {
                    return;
                }
                text = 'Il prezzo del biglietto è : ' + new NumberFormat(ticketInfo.prezzo).toFormatted() + ' EUR \n ';
                text += 'Autobus da prendere : \n ';
                for(var i = 0 ; i < ticketInfo.nomeBus.length;i++)
                {
                    text += '--> '+ticketInfo.nomeBus[i] + ' \n';
                }

            }
            else{
                $('#btBuy2').attr("disabled", "true");
            }

            $('#percorso').val(text);
            $('#myPaymentDlg').submit(function(event)
            {
                console.log('SUBMIT');
                self.onBuyTicket();
                return false;
            });
            $('#myPaymentDlg').bind("reset", function() {
                self.destroy();
                $.modal.close();
                return false;
            });
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myPayBillView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    onBuyTicket : function(evnt) {
        var self = this;
        var id = $("#frmPayment input[type='radio']:checked").val();
        if(id == undefined) {
            alert('Selezionare una carta');
            return false;
        }
        /*
        for(var i = 0; i < $(inputFld).length;  i++) {
            if($(inputFld).getAttribute('checked')){
                id = $(inputFld).val();
            }
        }
        */
        var ticketInfo = self.model.get('params');
        var param = {
            "uuid":app.appContext.user.uuid,
            "idContoCorrente":id,
            "descrizione" : $('#percorso').val(),
            "prezzo":ticketInfo.prezzo
        }
        console.log(param);
        this.model.callService('account','bonifico',param, $('#container'), function(result) {
            self.paymentDone(result);
        });
        return false;
    },
    paymentDone : function(result) {
        console.log(result);

        //update account
        if(result.rc == 0){
            app.appContext.user = result;
            this.destroy();
            $.modal.close();
        }
        else{
            if (result.description != undefined) {
                notifier.error('Errore nel pagamento', result.description ,'MyTrip.myPayBillView' ,null);
            }  else {
                notifier.error('Errore nel pagamento', 'Transazione abortita' ,'MyTrip.myPayBillView' ,null);
            }
        }
    },

    onCheckCard : function(evnt) {
        var self = this;
        // alert('card ' + evnt.srcElement.getAttribute('value') + ' checked: ' +  evnt.srcElement.checked);
        $('#cards tr').each(function(i, tr) {
            var inputFld = $(tr).find("input");
            if (inputFld != undefined) {
                if (evnt.srcElement.getAttribute('value') == $(inputFld).val()) {
                    self.model.set({"checkedCard" : $(inputFld).val()});
                } else {
                    if ($(inputFld).length > 0) {
                        $(inputFld).removeAttr('checked');
                    }
                }
            }
        });
    }
});