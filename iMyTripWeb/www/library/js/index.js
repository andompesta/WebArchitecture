/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/12/12
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
var app = {
    // used to avoid too many resize events
    appResizeTimeOut : null,
    // Application Context
    appContext : {
        "deviceConfigurator" : {},
        "deviceConfig" : {},
        "user" : {}
    },
    // The application map Object
    mapObject : null,
    // Flag for device initialization
    deviceReady :false,
    // Device Basic Width in pixels
    deviceBasicWidth: 0,
    // Device Height in pixels
    deviceBasicHeight: 0,
    // type of device
    deviceType : 'win',
    // Device orientation (portrait or landscape)
    deviceOrientation: 'none',
    // Device Width in pixels
    deviceWidth: 0,
    // Device Height in pixels
    deviceHeight: 0,
    // the device top borser for status bar
    deviceTopBorder : 0,
    // first time activation
    firstTime : true,
    // appStatus (logged in or out)
    appStatus : "off",
    // the current running application
    appCurrent : "",
    // the current running application models
    appModels : [],
    // the current running application views
    appViews : [],
    // applications
    applications : [
        {   id: "MYPOS",
            app: "MYPOS",
            viewInAppMenu: true,
            name:"Le mie posizioni",
            icon:"Map.png",
            tooltip: "I miei punti di interesse" ,
            status: ["off","on"],
            showDialog: false,
            showPathButton: false,
            showLeftButton: {
                portrait: [
                    {"win"   : "true"},
                    {"iPad"  : "true"},
                    {"iPhone": "true"} ],
                landscape:[
                    {"win"   : "false"},
                    {"iPad"  : "false"},
                    {"iPhone": "true"} ]
            },
            showPanes: {
                portrait : [
                    {"win"   : "'center'"},
                    {"iPad"  : "'center'"},
                    {"iPhone": "'center'"} ],
                landscape: [
                    {"win"   : "'west'"},
                    {"iPad"  : "'west'"},
                    {"iPhone": "'center'"} ]
            },
            sources:["models/myPosModel.js","views/myPosCenterView.js","views/myPosWestView.js"],
            mvc: {
                models: ["MyTrip.myPosModel"] ,
                views: ["MyTrip.myPosCenterView","MyTrip.myPosWestView"]
            }
        },
        {   id: "TRIP",
            app: "TRIP",
            viewInAppMenu: true,
            name:"Vai a...",
            icon:"Percorso.png",
            tooltip: "Cerca percorso per recarsi a" ,
            status: ["off","on"],
            showDialog: false,
            showPathButton: true,
            showLeftButton: {
                portrait: [
                    {"win"   : "false"},
                    {"iPad"  : "false"},
                    {"iPhone": "false"} ],
                landscape:[
                    {"win"   : "false"},
                    {"iPad"  : "false"},
                    {"iPhone": "false"} ]
            },
            showPanes: {
                portrait : [
                    {"win"   : "'west'"},
                    {"iPad"  : "'west'"},
                    {"iPhone": "'west'"} ],
                landscape: [
                    {"win"   : "'west'"},
                    {"iPad"  : "'west'"},
                    {"iPhone": "'west'"} ]
            },
            sources:["models/myTripModel.js","views/myTripCenterView.js","views/myTripWestView.js","views/myTripEastView.js","views/myTripPreferredView.js"],
            mvc: {
                models: ["MyTrip.myTripModel"] ,
                views: ["MyTrip.myTripCenterView","MyTrip.myTripWestView","MyTrip.myTripEastView","MyTrip.myTripPreferred"]
            }
        },
        {   id:"USERMNGM",
            app: "USERMNGM",
            viewInAppMenu: true,
            name:"Login",
            icon:"Login.png",
            tooltip: "Esegui il login al sistema" ,
            status: ["off"],
            showDialog: true,
            sources:["models/myLoginModel.js","views/myLoginView.js"],
            mvc: {
                models: ["MyTrip.myLoginModel"] ,
                views: ["MyTrip.myLoginView"]
            }
        },
        {   id:"LOGOFF",
            app: "USERMNGM",
            viewInAppMenu: true,
            name:"Logout",
            icon:"Logout.png",
            tooltip: "Esegui il logout" ,
            status: ["on"],
            showDialog: true,
            sources:["models/myLoginModel.js","views/myLogoutView.js"],
            mvc: {
                models: ["MyTrip.myLoginModel"] ,
                views: ["MyTrip.myLogoutView"]
            }
        },
        {   id:"REGISTER",
            app: "REGISTER",
            viewInAppMenu: true,
            name:"Registrazione",
            icon:"Register.png",
            tooltip: "Esegui la registrazione al sito" ,
            status: ["off","on"],
            showDialog: true,
            sources:["models/myRegisterModel.js","views/myRegisterView.js"],
            mvc: {
                models: ["MyTrip.myRegisterModel"] ,
                views: ["MyTrip.myRegisterView"]
            }
        },
        {   id:"PAYMENTS",
            app: "PAYMENTS",
            viewInAppMenu: true,
            name:"Pagamenti",
            icon:"Creditcard.png",
            tooltip: "Imposta il tuoi sistemi di pagamento" ,
            status: ["on"],
            showDialog: false,
            showPathButton: false,
            showLeftButton: {
                portrait: [
                    {"win"   : "false"},
                    {"iPad"  : "false"},
                    {"iPhone": "false"} ],
                landscape:[
                    {"win"   : "false"},
                    {"iPad"  : "false"},
                    {"iPhone": "false"} ]
            },
            showPanes: {
                portrait : [
                    {"win"   : "'west'"},
                    {"iPad"  : "'west'"},
                    {"iPhone": "'west'"} ],
                landscape: [
                    {"win"   : "'west'"},
                    {"iPad"  : "'west'"},
                    {"iPhone": "'west'"} ]
            },
            sources:["models/myPaymentsModel.js","views/myPaymentsCenterView.js","views/myPaymentsWestView.js"],
            mvc: {
                models: ["MyTrip.myPaymentsModel"] ,
                views: ["MyTrip.myPaymentsCenterView","MyTrip.myPaymentsWestView"]
            }

        },
        {   id:"PAYBILL",
            app: "PAYMENTS",
            viewInAppMenu: false,
            name:"Paga biglietto",
            icon:"Creditcard.png",
            tooltip: "Paga un biglietto" ,
            status: ["on"],
            showDialog: true,
            sources:["models/myPayBillModel.js","views/myPayBillView.js"],
            mvc: {
                models: ["MyTrip.myPayBillModel"] ,
                views: ["MyTrip.myPayBillView"]
            }
        },
        {   id:"NEWCARD",
            app: "PAYMENTS",
            viewInAppMenu: false,
            name:"Nuova carta pagamento",
            icon:"Creditcard.png",
            tooltip: "Definisci una nuova carta" ,
            status: ["on"],
            showDialog: true,
            sources:["models/myNewCardModel.js","views/myNewCardView.js"],
            mvc: {
                models: ["MyTrip.myNewCardModel"] ,
                views: ["MyTrip.myNewCardView"]
            }
        },
        {   id:"PWDRESET",
            app: "USERMNGM",
            viewInAppMenu: true,
            name:"Reset pwd",
            icon:"PwdReset.png",
            tooltip: "Esegui il reset della password" ,
            status: ["on","off"],
            showDialog: true,
            sources:["models/myResetPwdModel.js","views/myResetPwdView.js"],
            mvc: {
                models: ["MyTrip.myResetPwdModel"] ,
                views: ["MyTrip.myResetPwdView"]
            }
        },
        {   id:"CONFIG",
            app: "CONFIG",
            name:"Configurazione",
            icon:"Tools.png",
            tooltip: "Esegui la configurazione sistema" ,
            status: ["on","off"],
            showDialog: true,
            sources:["models/myConfigureModel.js","views/myConfigureView.js"],
            mvc: {
                models: ["MyTrip.myConfigureModel"] ,
                views: ["MyTrip.myConfigureView"]
            }
        },
        {   id:"POSITION",
            app: "MYPOS",
            name:"Punti interesse",
            icon:"Map.png",
            tooltip: "Inserisci punto di interesse" ,
            status: ["on"],
            showDialog: true,
            sources:["models/myPosModel.js","views/myPosPositionView.js"],
            mvc: {
                models: ["MyTrip.myPosModel"] ,
                views: ["MyTrip.myPosPositionView"]
            }
        }
    ],
    // Application Constructor
    initialize: function() {
        $.support.cors= true;
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {

        try {
            var self = this;
            document.addEventListener("deviceready", this.onDeviceReady, false);
            document.addEventListener("orientationchange", this.onOrientationChange, true);
            window.onresize=function(){self.onResize();};
        } catch (e) {
            alert('device ' + appContext.get('device') + 'does not responds to phonegap');
        }

        try {
            var self = this;
            setTimeout(function(){
                // document ready event
                $(document).ready(function () {
                    console.log('sulla docuemnt ready - non per iPad o iPhone');
                    self.onDeviceReady();
                });

            }, 200);
        } catch (e) {
            alert('device ' + appContext.get('device') + 'does not responds to $(document).ready');
        }
    },
    // deviceready Event Handler
    onDeviceReady: function() {
        app.deviceReady();
    },
    onResize : function() {
        app.deviceResize();
    },
    onOrientationChange : function() {
        if(window.orientation == -90 || window.orientation == 90) {
            // LANDSCAPE
            console.log('ORIENTATION CHANGE LANDSCAPE');
            switch(app.deviceType) {
                case 'iPad':
                    break;
                case 'iPhone':
                    break;
                default:
                    break;
            }
        } else {
            console.log('ORIENTATION CHANGE PORTRAIT');
            switch(app.deviceType) {
                case 'iPad':
                    //$('#west').hide();
                    //$('#shiftSidebar').fadeIn();
                    break;
                case 'iPhone':
                    break;
                default:
                    break;
            }
        }
        app.deviceSetLayOut();
    },
    // deviceReady function
    deviceReady: function( ) {
        if (this.deviceReady == true) return;
        this.deviceReady = true;


        console.log('device Ready');
        // Used by unit tests to tell when the page is loaded.
        window.pageIsLoaded = true;
        $('.ui-loader').remove();
        $('.ui-mobile-viewport').css('margin','0px');

        //Enable Cross Domain via CORS
        $.support.cors = true;
        try {
            // required to manage templates loaded dynamically
            $.tmpload.defaults.tplWrapper = _.template;
        } catch(e) {
            alert('Template loader not instantiated: ' + e.message);
        }
        this.showPathButton(false);
        this.closeRightBar();
        console.log('SETUP CONNECTION TO LOCAL CONFIGURATION DB');
        this.appContext.deviceConfigurator = new config();
        this.appContext.deviceConfigurator.initialize(this.configDBConnected);
    },
    // the configuration db is coeencted and active
    configDBConnected : function() {
        try {
            app.appContext.deviceConfigurator.getConfiguration(app.configDBLoaded);
        } catch(e) {
            notifier.exception("Exception in deviceConfigurator.getConfiguration",e,"app.configDBConnected");
        }
    },
    // the configuration is loaded
    configDBLoaded : function(configInfo) {
        console.log(configInfo);
        app.appContext.deviceConfig = configInfo;
        try {
            var self = app;
            console.log ('STARTUP APPLICATION IS --> ' + self.applications[0].id);
            setTimeout(function() {
                self.deviceSetLayOut();
                self.doAction(self.applications[0]);
                console.log ('STARTUP APPLICATION DONE --> ' + self.applications[0].id);
            }, 200);

        } catch(e) {
            notifier.exception("Exception in deviceConfigurator.configDBLoaded",e,"app.configDBLoaded");
        }
    },
    // device resized
    deviceResize : function() {
        if (this.deviceType != 'win')
            return;
        var self = this;
        clearTimeout(self.appResizeTimeOut);
        self.appResizeTimeOut = setTimeout(function() {
            self.deviceSetLayOut();
        }, 300);
    },
    // deviceSetLayOut function
    deviceSetLayOut: function( ) {

        console.log('real dimensions Width: ' + $(window).width() +  ' Height: '  + $(window).height());
        if (this.deviceBasicHeight >= 0 && this.deviceBasicWidth >= 0) {
            this.deviceBasicHeight = $(window).innerHeight();
            this.deviceBasicWidth = $(window).innerWidth();
        }
        if (typeof device == "undefined"){
            this.deviceType = 'win';
            this.deviceTopBorder = 0;
        } else {
            this.deviceType = device.platform;
        }

        if(window.orientation == -90 || window.orientation == 90) {
            this.deviceOrientation = 'landscape';
            switch(this.deviceType) {
                case 'iPad':
                    this.deviceWidth = 1024;
                    this.deviceHeight = 768;
                    break;
                case 'iPhone':
                    this.deviceWidth = 480;
                    this.deviceHeight = 320;
                    break;
                default:
                    this.deviceWidth = $(window).innerWidth();
                    this.deviceHeight = $(window).innerHeight();
                    if (this.deviceWidth > this.deviceHeight && this.deviceWidth > 1024)
                        this.deviceOrientation = 'landscape';
                    else
                        this.deviceOrientation = 'portrait';
                    break;
            }
        } else {
            this.deviceOrientation = 'portrait';
            switch(this.deviceType) {
                case 'iPad':
                    this.deviceWidth = 768;
                    this.deviceHeight = 1024;
                    break;
                case 'iPhone':
                    this.deviceWidth = 320;
                    this.deviceHeight = 480;
                    break;
                default:
                    this.deviceWidth = $(window).innerWidth();
                    this.deviceHeight = $(window).innerHeight();
                    if (this.deviceWidth > this.deviceHeight && this.deviceWidth > 1024)
                        this.deviceOrientation = 'landscape';
                    else
                        this.deviceOrientation = 'portrait';
                    break;
            }
        }

        this.deviceHeight = this.deviceHeight - this.deviceTopBorder;
        console.log('device platform: ' + this.deviceType + ' orientation: ' + this.deviceOrientation + ' -- width: ' + this.deviceWidth+', height=' + this.deviceHeight);
        
        
        var viewport = 'user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width='+this.deviceWidth+', height=' + this.deviceHeight;
 
        $('meta[name=viewport]').attr('content',viewport);
        $('body').css('width', '');
        $('body').css('height', '');
        $('.ui-page').css('min-height','');
        $('.ui-page').css('min-width','');
        $('body').css('width', this.deviceWidth + 'px');
        $('body').css('height', this.deviceHeight + 'px');

        $.modal.close();

        this.doLayout();
    },
    // do the basic layout
    doLayout: function( ) {
        var container = $('#container');

        var className  = '-' + this.deviceType + '-' + this.deviceOrientation;

        $("#north").removeClass().addClass("north").addClass("north" + className).addClass("red-background");
        $("#west").removeClass().addClass("west").addClass("west" + className);
        $("#south").removeClass().addClass("south").addClass("south" + className).addClass("gray-background");
        $("#east").removeClass().addClass("east").addClass("east" + className);
        $('#west').css('width', '');

        console.log("north" + className + ' '  + "west" + className + ' ' + "south" + className + ' ' + "east" + className);

        if (this.deviceType != 'iPhone') {
            $('.top-navbar .btnText').show();
            $('.bankIcon').show();
        }
        else  {
            $('.bankIcon').hide();
            if (this.deviceOrientation == 'portrait')
                $('.top-navbar .btnText').hide();
            else
                $('.top-navbar .btnText').show();
        }
        this.recalcCenter();
        container.layout({
            type: 'border',
            hgap: 0,
            vgap: 0,
            resize: false
        });


    },
    recalcCenter : function() {
        // resize center
        var wdt = 0;
        var wdt1 = 0;
        if ($('#west').is(':visible') )
            wdt = parseInt($('#west').css('width').replace('px',''));
        if ($('#east').is(':visible') )
            wdt1 = parseInt($('#east').css('width').replace('px',''));

        var newWdt = parseInt(this.deviceWidth) - wdt - wdt1 - 1;

        $('#center').width(newWdt);
        //$('#center').height($('#west').height());
        // $('#center').width();
        console.log('Reset center position to: width ' + $('#center').width() + ' height ' + $('#center').height());
        if (this.mapObject != undefined)
            google.maps.event.trigger(this.mapObject, "resize");
        for (var i = 0; i < this.appViews.length; i++) {
            this.appViews[i].resizeView();
        }
    },
    // evaluate the center and west pane position
    // and orientation dependig on app configuration
    setPaneOrientationAndPosition : function(application) {
        // evaluate if the left button must be shown
        for (var i = 0; i < eval('application.showLeftButton.'+ this.deviceOrientation + '.length'); i++) {
            var showLeftButt = eval('application.showLeftButton.' + this.deviceOrientation + '[i].' + this.deviceType );
            if (showLeftButt != undefined) {
                console.log('setPaneOrientationAndPosition Show left button : ' + showLeftButt);
                if (showLeftButt==true) {
                    $('#shiftSidebar').fadeIn();
                } else {
                    $('#shiftSidebar').fadeOut();
                }
            }
        }
        // evaluate if  west and center pane are to be displayed or
        // only the center pane
        // depending on deviceType
        for (var i = 0; i < eval('application.showPanes.'+ this.deviceOrientation + '.length'); i++) {
            var pane = eval('application.showPanes.'  + this.deviceOrientation + '[i].' +  this.deviceType );
            if (pane != undefined) {
                console.log('setPaneOrientationAndPosition Pane to open is: ' + eval(pane));
                
                if (eval(pane)=='center') {
                    //$('#west').hide();
                    this.closeSidebar();
                }
            }
        }
    },

    mainMenu : function() {


        $('#simplemodal').empty();

        var apps = '<ul id="appSelector">';
        for (var i = 0; i<this.applications.length; i++){
            var doIt = false;
            for (var j=0; j < this.applications[i].status.length; j++) {
                if (this.applications[i].status[j] == this.appStatus ) {
                    doIt = true;
                }
            }
            if (doIt == true && this.applications[i].device != undefined) {
                doIt = false;
                for (var j=0; j < this.applications[i].device.length; j++) {
                    if (this.applications[i].device[j] == this.deviceType ) {
                        doIt = true;
                    }
                }
            }
            if (this.applications[i].viewInAppMenu == false) {
                var doIt = false;
            }

            if (doIt == true) {
                apps += '<li>';
                apps += ' <a id="'+this.applications[i].id +'" href="#" onclick="app.doIt(this)">';
                apps += '  <div>';
                apps += '   <img src="library/images/selector/'+this.applications[i].icon+'" width="48px" height="48px" alt="'+this.applications[i].tooltip+'" />';
                apps += '   <p>'+this.applications[i].name+'</p>';
                apps += '  </div>';
                apps += ' </a>';
                apps += '</li>';
            }
        }
        apps += '</ul>';

        $('#simplemodal').append(apps);


        var appWidth;
        var appHeight;
        var appRows = 2;
        var appCols = 3;

        switch(this.deviceType) {
            case 'iPad':
                switch (this.deviceOrientation) {
                    case 'portrait':
                        appWidth = 320;
                        appHeight = 480;
                        appRows = 4;
                        appCols = 3;
                        break;
                    case 'landscape':
                        appWidth = 480;
                        appHeight = 400;
                        appRows = 3;
                        appCols = 4;
                        break;
                }
                break;
            case 'iPhone':
                switch (this.deviceOrientation) {
                    case 'portrait':
                        appWidth = 315;
                        appHeight = 390;
                        appRows = 3;
                        appCols = 3;
                        break;
                    case 'landscape':
                        appWidth = 475;
                        appHeight = 240;
                        appRows = 2;
                        appCols = 4;
                        break;
                }
                break;
            default :
                switch (this.deviceOrientation) {
                    case 'portrait':
                        appWidth = 320;
                        appHeight = 480;
                        appRows = 4;
                        appCols = 3;
                        break;
                    case 'landscape':
                        appWidth = 480;
                        appHeight = 320;
                        appRows = 3;
                        appCols = 4;
                        break;
                }
                break;
        }
        $('#appSelector').promptumenu({
            width:appWidth,
            height:appHeight,
            rows: appRows,
            columns: appCols,
            direction: 'horizontal',
            pages: true,
            leftoffset : 24,
            title: 'Seleziona una funzione',
            titlecolor: '#ffffff',
            titlebackcolor: '#7c7c7c'
        });

        $("#simplemodal").modal({
            opacity:0,
            position: [42,1],
            overlayClose:true,
            overlayCss: {backgroundColor:"#ececec"}
        });
    },
    showPathButton: function(visible) {
        if (visible) {
            $('#shiftRightBar').fadeIn();
        }  else {
            $('#shiftRightBar').fadeOut();
        }
    },
    shiftRightBar : function() {
        console.log('SHOW RIGHTBAR ----> ');
        if ($('#east').is(':visible')) {
            this.closeRightBar();
        } else {
            this.openRightBar();
        }
    },
    openRightBar : function() {
        if ($('#east').is(':visible')) {
            return;
        }
        var self = this;
        console.log('prima di close sidebar');
        app.hideSidebar();
        console.log('sidebar closed');
        $('#east').show();
        if (self.deviceType != 'iPhone') {
            $('#east').css('width','320px');
        } else {
            $('#east').css('width',app.deviceWidth + 'px');
        }
        this.deviceSetLayOut();
    },
    closeRightBar : function() {
        if ($('#east').is(':hidden')) {
            return;
        }
        $('#east').css('width','0px');
        $('#east').hide();
        this.deviceSetLayOut();
    },
    shiftSidebar: function() {
        this.doLayout();
        if ($('#west').is(':visible'))
            this.closeSidebar();
        else
            this.openSidebar();
    },
    showSidebar : function() {
        var wdt = parseInt($('#west').css('width').replace('px','')) + 1;
        console.log('SHOW SIDEBAR ----> ' + wdt);
        $('#west').css("left","0px");
        $('#center').css("left","+="+wdt+"px");
        $('#west').show();
        this.deviceSetLayOut();
        $('#shiftSidebar').hide();
        $('#mainMenu').show();
    },
    hideSidebar : function() {
        var wdt = parseInt($('#west').css('width').replace('px','')) + 1;
        var self = this;

        console.log('HIDE SIDEBAR ----> ' + wdt);
        $('#west').css("left", "-="+$('#west').css('width'));
        $('#west').hide();
        $('#center').css("left","-="+wdt+"px");
        this.deviceSetLayOut();
        $('#shiftSidebar').show();
        $('#mainMenu').hide();
    },
    openSidebar : function(callback) {
        $('#shiftSidebar').fadeOut();
        var wdt = parseInt($('#west').css('width').replace('px','')) + 1;

        var self = this;
        console.log('OPEN SIDEBAR ----> ' + wdt);
        this.closeRightBar(); // if any and opened
        $('#west').show();
        $('#west').animate({"left": "0px"},"slow",function(){});
        $('#center').animate({"left": "+="+wdt+"px"},"slow",function(){
                                self.deviceSetLayOut();
                                try {
                                    if (typeof callback === "function")
                                           callback.apply({});
                                } catch(e) {}
                             });
        $('#shiftSidebar').fadeOut();
        $('#mainMenu').fadeIn();
    },
    closeSidebar : function(callback) {
        var wdt = parseInt($('#west').css('width').replace('px','')) + 1;
        var self = this;

        console.log('CLOSE SIDEBAR ----> ' + wdt);
        $('#west').animate({"left": "-="+$('#west').css('width')},"slow", function() {
                            });
        $('#center').animate({"left": "-="+wdt+"px"},"slow",function(){
                                $('#west').hide();
                                self.deviceSetLayOut();
                                try {
                                    if (typeof callback === "function")
                                        callback.apply({});
                                } catch(e) {}
                             });
        $('#shiftSidebar').fadeIn();
        $('#mainMenu').fadeOut();
    },
    sidebarClick : function(elem) {
        this.closeSidebar();
    },
    doIt : function(elem) {
        var self = this;
        console.log("Activate application : " + elem.id);
        $.modal.close();
        for (var i = 0; i< this.applications.length; i++){
            if (elem != undefined && elem.id == this.applications[i].id) {
                this.appCurrent = this.applications[i].id;
                $('#west').show();
                this.deviceSetLayOut();
                this.doAction(this.applications[i]);
                break;
            }
        }
    },
    doLaunchApp : function(id, params) {
        for (var i = 0; i< this.applications.length; i++){
            if (id != undefined && id == this.applications[i].id) {
                this.appCurrent = this.applications[i].id;
                // this.deviceSetLayOut();
                this.doAction(this.applications[i],params);
                break;
            }
        }
        console.log('Application lauched ' + id);
    },
    doAction : function(application,params) {

        var src = "apps/"+ application.app + "/js";
        if (application.id == "CALL") {
            // Android  document.location.href = 'tel:+1-800-555-1234';
            try {
                //document.location.href='tel:1-408-555-5555';
                window.plugins.phoneDialer.dial('+41-79-7512306');
            } catch(e) {
                console.log("Error in dial attempt ");
                console.log(e);

            }
            return;
        }
        console.log("---> START APPLICATION: " + application.id );
        //console.log(application);


        // evaluate the list of modules that must be dynamically
        // loaded via require.js
        var modules = [];
        for (var i = 0; i < application.sources.length; i++) {
            var modelSrc = src + "/" + application.sources[i];
            modules.push(modelSrc);
        }
        var self = this;
        console.log(modules);

        try {
            // use require.js to dynamically load the
            // required modules
            require(modules, function() {
                if (application.showDialog == undefined || application.showDialog == false) {
                    $('#appTitle').html(application.name);
                    // close a map object if used
                    if (self.mapObject != undefined) {
                        self.mapObject = null;
                    }

                    // OPEN WEST  PANES
                    //$('#west').show();
                    self.showSidebar();

                    // Set all panes position and orientation depending on app
                    // configuration
                    self.setPaneOrientationAndPosition(application);
                    // show or hide the path button

                    self.showPathButton(application.showPathButton);
                    // remove old models
                    if (self.appModels != undefined && self.appModels.length > 0) {
                        for (var i = 0; i < self.appModels.length; i++) {
                            self.appModels[i] = null;
                        }
                        self.appModels = [];
                    }
                    // remove old views
                    if (self.appViews != undefined && self.appViews.length > 0) {
                        for (var i = 0; i < self.appViews.length; i++) {

                            self.appViews[i].destroy();
                            self.appViews[i] = null;
                        }
                        self.appViews = [];
                    }
                    // Load the model (only one)
                    var modelModue = eval('new ' + application.mvc.models[0] + '()');
                    if (modelModue != undefined) {
                        self.appModels.push(modelModue);
                        // and load all the views
                        for (var i = 0; i < application.mvc.views.length; i++) {
                            try {
                                if (params != undefined && modelModue != undefined) {
                                    modelModue.set({"params": params});
                                }
                                var viewModule = eval('new ' + application.mvc.views[i] + '({model: modelModue})');

                                if (viewModule != undefined) {
                                    self.appViews.push(viewModule);
                                }
                            } catch (e) {
                                break;
                            }
                        }
                    }
                } else {
                    // OPEN DIALOG
                    var dlgWidth = 500;
                    var dlgHeight = 320;
                    if (self.deviceType == 'iPhone') {
                        dlgWidth = self.deviceWidth;
                        dlgHeight = self.deviceHeight;
                    }

                    $('#simplemodal').empty();
                    $("#simplemodal").modal({
                        opacity: 45,
                        modal: true,
                        minHeight:dlgHeight,
                        minWidth: dlgWidth,
                        autoPosition : true,
                        autoResize: true,
                        overlayClose : false,
                        overlayCss: {backgroundColor:"#ececec"}
                    });
                    // Load the model (only one)
                    var modelModue = eval('new ' + application.mvc.models[0] + '()');
                    if (modelModue != undefined) {
                        // and load all the views
                        for (var i = 0; i < application.mvc.views.length; i++) {
                            try {
                                if (params != undefined && modelModue != undefined) {
                                    modelModue.set({"params": params});

                                }
                                var viewModule = eval('new ' + application.mvc.views[i] + '({model: modelModue})');

                                if ($('#simplemodal') != undefined && $('#simplemodal').children().length > 0) {
                                    var device_special = '';
                                    if (self.deviceType == 'iPhone')
                                        device_special = 'iPhone_';
                                    var myClass = 'dialog_' + device_special + self.deviceOrientation;

                                    $('#simplemodal:first').addClass(myClass);
                                }

                            } catch (e) {
                                break;
                            }
                        }
                    }
                }
            });
            console.log('Required js modules loaded and initialized');
        } catch(e) {
            //notifier.exception('Errore in caricamento dinamico moduli : ' + modules[0]   , e ,'app.doAction()' ,null);
            alert('Errore in caricamento dinamico moduli : ' + modules[0]);
        }
        console.log("---> STARTED APPLICATION: " + application.id );
    },
    updateViews : function(params) {
        if (this.appViews != undefined) {
            for (var i=0; i < this.appViews.length; i++) {
                this.appViews[i].updateView(params);
            }
        }
    }
};
