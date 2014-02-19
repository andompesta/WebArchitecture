/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/29/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myResetPwdView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'myResetPwd',

    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('myResetPwdView initialized');
        this.render();
    },
    destroy: function(){
        $('#frmResetPwd').unbind('submit');
        $('#simplemodal').empty();
        this.unbind();
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myResetPwdView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/USERMNGM/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }
            if (app.appContext.user != undefined) {
                $('#email').val(app.appContext.user.mail);
            }
            $('#frmResetPwd').submit(function(event)
            {
                console.log('SUBMIT');
                self.model.email = $('#email').val();
                self.doResetPwd();
                return false;
            });
            $('#frmResetPwd').bind("reset", function() {
                self.destroy();
                 $.modal.close();
                return false;
            });
            setTimeout(function() {
                $('#email').focus();
            }, 100);
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myResetPwdView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    doResetPwd: function() {
        var params =   this.model.email;

        var self = this;
        this.model.callService('account','resetPass', params, $('#container'), function(result) {
            self.doResetPwdDone(result);
        });
    },
    doResetPwdDone : function(result) {
        if (result.rc == -1) {
            notifier.error("Error in reset password",result.description,"MyTrip.myResetPwdView.logoutDone")
            return;
        }
        notifier.warning("Messaggio di reset", result.description);
        this.destroy();
        $.modal.close();
    }
});