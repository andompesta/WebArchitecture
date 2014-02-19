/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/29/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myLoginView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'myLogin',
    username        : '',
    password        : '',
    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('myLoginView initialized');
        this.render();
    },
    destroy: function(){
        $('#frmLogin').unbind('submit');
        $('#frmLogin').unbind('reset');
        $('#simplemodal').empty();
        this.unbind();
    },
    /**
     * the render method
     */
    render           : function () {
        console.log('MyTrip.myLoginView render');

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
            $('#frmLogin').submit(function(event)
            {
                console.log('SUBMIT');
                self.verifyLogin();
                return false;
            });
            $('#frmLogin').bind("reset", function() {
                self.destroy();
                $.modal.close();
                return false;
            });
            $('#register').bind("click",function() {
                 $.modal.close();
                app.doLaunchApp('REGISTER');
            });
            $('#pwdReset').bind("click",function() {
                $.modal.close();
                app.doLaunchApp('PWDRESET');
            });
            if (app.appContext.deviceConfig != undefined && app.appContext.deviceConfig.rememberMe) {
                $('#username').val(app.appContext.deviceConfig.userId);
                $('#password').val(app.appContext.deviceConfig.password);
                $('#remind').attr('checked','checked');

            } else {
                $('#username').val('');
                $('#password').val('');
                $('#remind').removeAttr('checked');
            }

            setTimeout(function() {
                $('#username').focus();
            }, 100);

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myLoginView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    verifyLogin: function() {
        this.username = $('#username').val();
        this.password = $('#password').val();
        if (this.username.trim() == "") {
            notifier.warning("Mancanza Username","Inserire il campo username");
            return;
        }
        if (this.password.trim() == "") {
            notifier.warning("Mancanza Password","Inserire il campo password");
            return;
        }
        var params =  {
            "userName" : this.username,
            "password": this.password,
            "device": app.deviceType
        };
        var self = this;
        this.model.callService('account','logIn',params, $('#container'), function(result) {
            self.loginDone(result);
        });
    },
    loginDone : function(result) {
        if (result.rc != 0) {
            notifier.error("Error in login",result.description,"MyTrip.myLoginView.loginDone");
            return;
        }
        app.appContext.user = result;
        this.model.set({'username' : this.username });
        this.model.set({'password' : this.password});

        app.appContext.deviceConfig.userId = this.username;
        app.appContext.deviceConfig.password = this.password;
        if ($('#remind').attr('checked') == 'checked')
            app.appContext.deviceConfig.rememberMe = true;
        else
            app.appContext.deviceConfig.rememberMe = false;
        app.updateViews();

        var self = this;
        app.appContext.deviceConfigurator.setConfiguration( JSON.stringify(app.appContext.deviceConfig) , function(){
            // imposta lo stato ad on line
            app.appStatus = "on";
            
            $('#userConnected').html(app.appContext.user.nome + ', ' + app.appContext.user.cognome);
            $('#connectedStatus').attr('src','library/images/signal-green.png');
            app.updateViews();
            self.destroy();
            $.modal.close();
        });
    }
});