/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/29/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myConfigureView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'configure',
    username        : '',
    password        : '',
    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('myConfigureView initialized');
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
        console.log('MyTrip.myConfigureView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/CONFIG/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }

            $('#server').val(app.appContext.deviceConfig.server);
            $('#timeout').val(app.appContext.deviceConfig.timeout);
            $('#frmConfigure').submit(function(event)
            {
                console.log('SUBMIT');
                self.verifyConfigure();
                return false;
            });
            $('#frmConfigure').bind("reset", function() {
                self.destroy();
                $.modal.close();
                return false;
            });
            setTimeout(function() {
                $('#server').focus();
            }, 100);

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myConfigureView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    verifyConfigure: function() {
        this.model.server = $('#server').val();
        this.model.timeout = $('#timeout').val();
        if (this.model.server.trim() == "") {
            notifier.warning("Mancanza nome server","Inserire il campo server");
            return;
        }
        if (this.model.timeout == 0) {
            notifier.warning("Timeout non valido","Inserire il campo timeout in ms");
            return;
        }
        app.appContext.deviceConfig.server = this.model.server;
        app.appContext.deviceConfig.timeout = this.model.timeout;
        // save the configuration
        app.appContext.deviceConfigurator.setConfiguration( JSON.stringify(app.appContext.deviceConfig) , function(){

            $.modal.close();
        });
    }
});