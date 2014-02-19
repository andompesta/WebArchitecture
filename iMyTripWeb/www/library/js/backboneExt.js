/**
 * ---------------------------------------------------
 * Project   : iMyTripWeb
 * User      : Sandro
 * Date      : 10/2/12
 * Module    : backboneExt.js
 * Descrition: backboneExt.js is the definition of extended
 *             backbone functionality for iMyTrip
 * ---------------------------------------------------
 *
 * used to define the CSD namespace
 *
 */
if (typeof MyTrip == 'undefined') {
    // define the MyTrip namespace
    var MyTrip = {};
}

/**
 * _super prototype for Backbone.js
 * @param method
 * @return {Object}
 * @private
 */
Backbone.Model.prototype._super = function(method){
    return this.constructor.__super__[method].apply(this, _.rest(arguments));
}
/**
 * The object myEvent that allows cross view event
 * publlish subscribe event pattern
 * @type {*}
 */
var MyTripEvent = _.extend({}, Backbone.Events);

/**
 * MyTrip.Model extension of Backbone.Model
 */
MyTrip.Model = Backbone.Model.extend({

    initialize: function() {
        console.log('MyTrip.Model initialization');
    },
    callService : function(service,method,params,overlayDiv,callback){

        var spinnerInfo = 'Calling remote service: ' + service + ' <br> Method: ' + method ;
        if (overlayDiv != undefined)
            utils.showSpinner(overlayDiv,spinnerInfo);

        try { $('#waitIcon').show();} catch(e) {}

        try {
            var startTime = new Date();

            if(app.appContext.deviceConfig.server.charAt( app.appContext.deviceConfig.server.length-1 ) == "/") {
                app.appContext.deviceConfig.server = app.appContext.deviceConfig.server.slice(0, -1);
            }


            console.log('.....> call service : '+ app.appContext.deviceConfig.server + '/' + service + '/' + method );
            $.ajaxSetup( { cache : false } );
            $.ajax({
                type: "POST",
                timeout :  app.appContext.deviceConfig.timeout,
                cache: false,
                url:  app.appContext.deviceConfig.server + '/' + service + '/' + method + '?rnd=' + (Math.random()*200) +'_'+ (startTime.getTime()),
                dataType: "json",
                contentType: "application/json",
                data : JSON.stringify(params),
                error: function (jqXHR, textStatus, errorThrown) {
                    // later record end time
                    var endTime = new Date();
                    // time difference in ms
                    var timeDiff = endTime - startTime;
                    if (overlayDiv != undefined)
                        utils.hideOverlay($(overlayDiv));
                    try { $('#waitIcon').hide();} catch(e) {}

                    utils.logGroup("AJAX error: " + service + '/' + method);
                    utils.logDebug(jqXHR);
                    utils.logDebug(textStatus);
                    utils.logGroupEnd();

                    if (callback != null) {
                        var cResult = {rc : -1,  Timers: {},  description : 'Error in json request to ' + service + '/' + method + ' : ' +  textStatus, RcDescription: errorThrown.message};
                        cResult.Timers.TotalClient = timeDiff;
                        cResult.Timers.ServiceMethod = service + '/' + method;
                        if ( typeof callback === "function" ) {
                            callback.apply({}, [cResult] );
                        }

                    }
                },
                success: function (data, textStatus, jqXHR) {
                    // later record end time
                    var endTime = new Date();
                    // time difference in ms
                    var timeDiff = endTime - startTime;
                    //data.Timers.TotalClient = timeDiff;
                    //data.Timers.ServiceMethod = service + '/' + method;

                    if (overlayDiv != undefined)
                        utils.hideOverlay($(overlayDiv));
                    try { $('#waitIcon').hide();} catch(e) {}

                    utils.logGroup("AJAX success: " + service + '/' + method );
                    utils.logDebug(textStatus);
                    utils.logDebug(data);
                    utils.logGroupEnd();

                    app.appContext.nrOfCalls++;
                    if ( typeof callback === "function" ) {
                        callback.apply({}, [data] );
                    }

                }
            });
        } catch(e) {

            if (overlayDiv != undefined)
                utils.hideOverlay($(overlayDiv));
            try { $('#waitIcon').hide();} catch(e) {}

            // later record end time
            var endTime = new Date();
            // time difference in ms
            var timeDiff = endTime - startTime;
            if (callback != null) {
                var cResult = {rc : -1,  Timers: {},  description : 'Exception in $ajax Call request to  '+ service + '/' + method , description: e.message};
                cResult.Timers.TotalClient = timeDiff;
                cResult.Timers.ServiceMethod = service + '/' + method;
                if ( typeof callback === "function" ) {
                    callback.apply({}, [cResult] );
                }
            }
        }
    },
    getService : function(service,method,params,overlayDiv,callback){

        var spinnerInfo = 'Calling remote service: ' + service + ' <br> Method: ' + method ;
        if (overlayDiv != undefined)
            utils.showSpinner(overlayDiv,spinnerInfo);
        try { $('#waitIcon').show();} catch(e) {}

        try {
            var startTime = new Date();

            if(app.appContext.deviceConfig.server.charAt( app.appContext.deviceConfig.server.length-1 ) == "/") {
                app.appContext.deviceConfig.server = app.appContext.deviceConfig.server.slice(0, -1);
            }

            var prms = '';
            if (params != undefined && params.length > 0) {
                prms = '/'+ params;
            }
            console.log('.....> get service : '+ app.appContext.deviceConfig.server + '/' + service + '/' + method );
            $.ajaxSetup( { cache : false } );
            $.ajax({
                type: "GET",
                timeout :  app.appContext.deviceConfig.timeout,
                cache: false,
                url:  app.appContext.deviceConfig.server + '/' + service + '/' + method + prms + '?rnd=' + (Math.random()*200) +'_'+ (startTime.getTime()) ,
                contentType: "application/json",
                error: function (jqXHR, textStatus, errorThrown) {
                    // later record end time
                    var endTime = new Date();
                    // time difference in ms
                    var timeDiff = endTime - startTime;
                    if (overlayDiv != undefined)
                        utils.hideOverlay($(overlayDiv));
                    try { $('#waitIcon').hide();} catch(e) {}

                    utils.logGroup("AJAX error: " + service + '/' + method);
                    utils.logDebug(jqXHR);
                    utils.logDebug(textStatus);
                    utils.logGroupEnd();

                    if (callback != null) {
                        var cResult = {rc : -1,  Timers: {},  description : 'Error in json request to ' + service + '/' + method + ' : ' +  textStatus, RcDescription: errorThrown.message};
                        cResult.Timers.TotalClient = timeDiff;
                        cResult.Timers.ServiceMethod = service + '/' + method;
                        if ( typeof callback === "function" ) {
                            callback.apply({}, [cResult] );
                        }

                    }
                },
                success: function (data, textStatus, jqXHR) {
                    // later record end time
                    var endTime = new Date();
                    // time difference in ms
                    var timeDiff = endTime - startTime;
                    //data.Timers.TotalClient = timeDiff;
                    //data.Timers.ServiceMethod = service + '/' + method;
                    try { $('#waitIcon').hide();} catch(e) {}

                    if (overlayDiv != undefined)
                        utils.hideOverlay($(overlayDiv));
                    utils.logGroup("AJAX success: " + service + '/' + method );
                    utils.logDebug(textStatus);
                    utils.logDebug(data);
                    utils.logGroupEnd();

                    app.appContext.nrOfCalls++;
                    if ( typeof callback === "function" ) {
                        callback.apply({}, [data] );
                    }

                }
            });
        } catch(e) {

            if (overlayDiv != undefined)
                utils.hideOverlay($(overlayDiv));
            try { $('#waitIcon').hide();} catch(e) {}

            // later record end time
            var endTime = new Date();
            // time difference in ms
            var timeDiff = endTime - startTime;
            if (callback != null) {
                var cResult = {rc : -1,  Timers: {},  description : 'Exception in $ajax Call request to  '+ service + '/' + method , description: e.message};
                cResult.Timers.TotalClient = timeDiff;
                cResult.Timers.ServiceMethod = service + '/' + method;
                if ( typeof callback === "function" ) {
                    callback.apply({}, [cResult] );
                }
            }
        }
    }
});
/**
 * MyTrip.Collection
 * @type {*}
 */
MyTrip.Collection = Backbone.Collection.extend({
    initialize: function() {
        console.log('MyTrip.Collection initialization');
    }
});

/**
 * CSD.View  extension of Backbone.View
 */
MyTrip.View = Backbone.View.extend({
    initialize: function () {
        _.bindAll(this, 'render');
        this.render();
    },
    /**
     * destroy the view
     */
    destroy: function(){
        try {
            this.stopListening();
            this.unbind();

        } catch(e) {}
    },
    setPosition : function() {

    },
    resizeView : function() {

    },
    updateView : function() {

    },
    /**
     * render the view
     */
    render: function () {
        try {
        } catch (e) {
            alert(e.message);
        }
    }
});

/**
 * ---------------------------------------------------
 * Project   : MyTrip
 * User      : Sandro
 * Date      : 10/2/12
 * Module    : backboneExt.js
 * Descrition: templateLoader is used to load a template
 *             html file
 * ---------------------------------------------------
 *
 */
function templateLoader() {
}

templateLoader.load = function (templatename, templatedir) {
    // var urlroot = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + '/' + location.dom;
    var tmpl_dir = 'templates';
    if (templatedir != undefined)
        tmpl_dir = templatedir;

    var tmpl_url = tmpl_dir + '/' + templatename + '.html';
    console.log('Loading template: ' + tmpl_url);
    var tmpl_string;
    $.ajax({
        url     : tmpl_url,
        method  : 'GET',
        async   : false,
        error   : function (xhr, ajaxOptions, thrownError) {
            notifier.error("Template loading", "Error in loading template: " + tmpl_url + " <br>[ " + xhr.status + " ]  " + thrownError, "jquery.shellutils templateLoader.load");
            return null;
        },
        success : function (data) {
            tmpl_string = data;
        }
    });
    return tmpl_string;
}
