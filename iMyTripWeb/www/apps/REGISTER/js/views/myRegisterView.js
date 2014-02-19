/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/30/12
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myRegisterView = MyTrip.View.extend({
    // element in template appBody.htm
    el              : '#simplemodal',
    templateName    : 'myRegister',
    initialize      : function (options) {
        // Initialize the View
        _.bindAll(this, 'render');
        this.routedEvents = options.routedEvents;
        console.log('myRegisterView initialized');
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
        console.log('MyTrip.myRegisterView render');

        // Render the view
        var self = this;
        try {
            // Get the DOM element
            var $el = $(this.el);

            var template_string = templateLoader.load(this.templateName,'apps/REGISTER/templates');
            if (template_string != undefined) {
                // Compile the template using underscore
                var compiled_template = _.template(template_string, this.model.attributes);
                // inject the template
                $el.html(compiled_template);
            }
            if (app.appStatus == 'on') {
                $('#regtitle').text("Modifica Registrazione");
                $('#username').val(app.appContext.user.userName);
                $('#username').attr("disabled", "disabled");
                $('#password').val('');
                $('#name').val(app.appContext.user.nome);
                $('#surname').val(app.appContext.user.cognome);
                $('#birthDate').val(app.appContext.user.dataNascita);
                $('#email').val(app.appContext.user.mail);
                $('#emailConfirm').val('');
            }
            $('#frmRegister').submit(function(event)
            {
                console.log('SUBMIT');
                self.execRegister();
                return false;
            });
            $('#frmRegister').bind("reset", function() {
                self.destroy();
                 $.modal.close();
                return false;
            });
            setTimeout(function() {
                if (app.appStatus == 'off')
                    $('#username').focus();
                else
                    $('#password').focus();
            }, 100);

        } catch (e) {
            notifier.exception('Errore in rendering del template: ' + this.templateName, e ,'MyTrip.myRegisterView render' ,null);
            throw new Error('Errore in rendering del template: ' + this.templateName);
        }
    },
    execRegister: function() {
        this.model.username = $('#username').val();
        this.password = $('#password').val();

        this.model.username  = $('#username').val();
        this.model.name  = $('#name').val();
        this.model.surname  = $('#surname').val();
        this.model.birthDate  = $('#birthDate').val();
        this.model.email  = $('#email').val();
        this.model.emailConfirm  = $('#emailConfirm').val();
        this.model.password  = $('#password').val();

        if (this.model.username.trim() == "") {
            notifier.warning("Mancanza campo Username","Inserire il campo username");
            return;
        }
        if (this.model.name.trim() == "") {
            notifier.warning("Mancanza campo Nome","Inserire il campo nome");
            return;
        }
        if (this.model.surname.trim() == "") {
            notifier.warning("Mancanza campo Cognome","Inserire il campo cognome");
            return;
        }
        if (this.model.email.trim() == "") {
            notifier.warning("Mancanza campo Email","Inserire il campo email");
            return;
        }
        if (this.model.emailConfirm.trim() == "") {
            notifier.warning("Mancanza campo Conferma Email","Inserire il campo conferma email");
            return;
        }
        if (this.model.password.trim() == "") {
            notifier.warning("Mancanza campo Password","Inserire il campo password");
            return;
        }
        if (this.model.email.trim() != this.model.emailConfirm.trim()) {
            notifier.warning("Errore in Email","Campi Email e Conferma Email non corretti");
            return;
        }

        var myUuid = '';
        var myAbilitato = 'false';
        if (app.appStatus == 'on') {
            myUuid = app.appContext.user.uuid;
            myAbilitato = true;
        }

        var params =  {
            "uuid" : myUuid,
            "abilitato" : myAbilitato,
            "userName" : this.model.username,
            "pass": this.model.password,
            "nome" : this.model.name,
            "cognome" : this.model.surname,
            "dataNascita" : this.model.birthDate,
            "mail" : this.model.email
        };
        var self = this;
        if (app.appStatus == 'off') {
            this.model.callService('account','register',params, $('#container'), function(result) {
                self.registrationDone(result);
            });
        } else {
            this.model.callService('account','updateUser',params, $('#container'), function(result) {
                self.registrationDone(result);
            });
        }
    },
    registrationDone : function(result) {
        if (result.rc == -1) {
            notifier.error("Errore in registrazione",result.description,"MyTrip.myRegisterView.registrationDone")
            return;
        }
        if (result.rc == -2) {
            notifier.warning("Notifica da registrazione",result.description,"MyTrip.myRegisterView.registrationDone")
        }
        $.modal.close();
    }
});