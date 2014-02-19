/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/29/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myLogoutView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'myLogout',
    username        : '',
    password        : '',
    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('myLogoutView initialized');
        this.render();
    },
    destroy: function(){
        $('#frmLogout').unbind('submit');
        $('#frmLogout').unbind('reset');
        $('#simplemodal').empty();
        this.unbind();
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myLogoutView render');

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
            $('#frmLogout').submit(function(event)
            {
                console.log('SUBMIT');
                self.doLogout();
                return false;
            });
            $('#frmLogout').bind("reset", function() {
                self.destroy();
                $.modal.close();
                return false;
            });
        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myLogoutView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    doLogout: function() {
        var params =  app.appContext.user.uuid;
        var self = this;
        this.model.callService('account','logOut', params, $('#container'), function(result) {
            self.logoutDone(result);
        });
    },
    logoutDone : function(result) {
        if (result.rc != 0) {
            notifier.error("Error in logout",result.description,"MyTrip.myLogoutView.logoutDone")
        }
        $('#userConnected').html('');
        $('#connectedStatus').attr('src','library/images/signal-yellow.png');

        app.appContext.user = {};
        app.appStatus = "off";
        this.destroy();
        $.modal.close();
        app.doLaunchApp('MYPOS');
    }
});