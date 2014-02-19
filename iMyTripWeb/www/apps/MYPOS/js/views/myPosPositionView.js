/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 04/01/2013
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myPosPositionView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'myPosPosition',
    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('MyTrip.myPosPositionView initialized');
        this.render();
    },
    destroy: function(){
        $('#simplemodal').empty();
        this.unbind();
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myPosPositionView render');

        // Render the view
        var self = this;
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
            if (this.model.get('params') != undefined ) {
                if ( this.model.get('params') == undefined) {
                    $('#latitude').val('');
                } else {
                    $('#latitude').val(this.model.get('params').latitude);
                }
                if (this.model.get('params') == undefined) {
                    $('#logitude').val('');
                } else {
                    $('#logitude').val(this.model.get('params').longitude);
                }
            } else {
                $('#latitude').val('');
                $('#logitude').val('');
            }
            $('#frmPosition').submit(function(event)
            {
                console.log('SUBMIT');
                self.savePosition();
                return false;
            });
            $('#frmPosition').bind("reset", function() {
                self.destroy();
                $.modal.close();
                return false;
            });

            setTimeout(function() {
                $('#descrizione').focus();
            }, 100);

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myPosPositionView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    updateView : function() {
        this.render();
    },
    savePosition: function() {
        var lat = $('#latitude').val();
        var lgn = $('#logitude').val();
        var desc = $('#descrizione').val();

        if (app.appStatus == 'off') {
            notifier.warning("Eseguire il logon","Non si e' connessi al sistema");
            return;
        }
        if (lat == '') {
            notifier.warning("Manca campo latitudine","Inserire il campo latitudine");
            return;
        }
        if (lgn == '') {
            notifier.warning("Manca campo longitudine","Inserire il campo longitudine");
            return;
        }
        if (desc == '') {
            notifier.warning("Manca campo descrizione","Inserire il campo descrizione");
            return;
        }
        var self = this;
        var params = app.appContext.user;
        if (params.localitaPrivataList == undefined) {
            params.localitaPrivataList = [];
        }
        var newPos = {"descrizione":desc,"latitudine":lat,"longitudine":lgn};
        for (var i = 0; i < app.appContext.user.localitaPrivataList.length; i++) {
            if (app.appContext.user.localitaPrivataList[i].descrizione == desc) {
                app.appContext.user.localitaPrivataList.splice(i,1);
                break;
            }
        }
        params.localitaPrivataList.push(newPos);
        this.model.callService('account','updateUser',params, $('#container'), function(result) {
            self.positionSaved(result);
        });
    },
    positionSaved : function(result) {
        //aggiornare L'account
        if (result.rc != 0) {
            notifier.error("Error in update User ",result.description,"MyTrip.myPosPositionView.loginDone");
            return;
        } else {
            app.appContext.user = result;
            app.updateViews();
            $.modal.close();
        }
    }
});