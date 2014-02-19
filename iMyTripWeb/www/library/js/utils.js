/**
 * ---------------------------------------------------
 * Project   : SchedaCliente
 * User      : Marco
 * Date      : 10/2/12
 * Module    : utils
 * Descrition:
 *
 * utils.js - the utility file
 * ---------------------------------------------------
 */
function utilsPoint(lat,lng,description) {
    this.lat = lat;
    this.lng = lng;
    this.description = '';
    if (description != undefined) {
        this.description = description;
    }
    this.convertToGoogle = function() {
        return new google.maps.LatLng(this.lat, thid.lng);
    }
}
/*---------------------------------------------------------------
 * Class  Notifier
 * used display error message in popup dialog box
 * uses Zebra dialog
 * --------------------------------------------------------------
 */
var dlg = null;
var isOpen = false;
function notifier() {

}
notifier.closed = function(callback){
    isOpen = false;
    if (callback != undefined) {
        callback();
    }
}

/**
 * notify an error message
 * @param titleText         the title text
 * @param message           the message to display
 * @param module            the module name
 * @param isJsonSource      flag to indicate json source
 */
notifier.error = function (titleText, message, module, isJsonSource, callback) {
    var result = '';
    if (isJsonSource != undefined && isJsonSource == true) {
        result = message.replace(/\r\n/g, "<br />").replace(/\n/g, "<br />");
        result = result.replace(/\t/g, "&nbsp;&nbsp;&nbsp;&nbsp;")
    } else {
        var message = message + '<br><br>' + 'Module : ' + module + '<br>';
        result = message;
    }
    if (isOpen == true) return;
    $.Zebra_Dialog(message, {
        'modal'          :true,
        'type'           :'error',
        'overlay_opacity':.5,
        'title'          :titleText,
        'onClose'        :notifier.closed(callback)

});

}
/**
 * notify an exception message
 * @param titleText         the title text
 * @param exc               the exception object
 * @param module            the module name
 * @param callback          the callback function
 */
notifier.exception = function (titleText, exc, module, callback) {
    var result = '';

    var message = exc.message + '<br><br>' + 'Name   : ' + exc.name + '<br>' + 'Module : ' + module + '<br>';
    result = message;


    $.Zebra_Dialog(message, {
        'modal'          :true,
        'type'           :'error',
        'title'          :titleText,
        'overlay_opacity':.5,
        'onClose'        :notifier.closed(callback)
    });
}
/**
 * notify an info message
 * @param titleText
 * @param message
 * @param callback
 */
notifier.info = function (titleText, message, callback) {

    $.Zebra_Dialog(message, {
        'modal'          :true,
        'type'           :'information',
        'title'          :titleText,
        'overlay_opacity':.5,
        'onClose'        :notifier.closed(callback)
    });
}
/**
 * notify an info warning
 * @param titleText
 * @param message
 * @param callback
 */
notifier.warning = function (titleText, message, callback) {

    $.Zebra_Dialog(message, {
        'modal'          :true,
        'type'           :'warning',
        'title'          :titleText,
        'overlay_opacity':.5,
        'onClose'        :notifier.closed(callback)
    });
}
/**
 * Object utils
 * contains some general purpose functions
 */
function utils() {
}
/**
 * formatDate in dd/MM/yyyy format
 * @param date
 * @return {String}
 */
utils.formatDate = function (date) {

    var d = date.getDate();
    var day = (d < 10) ? '0' + d : d;
    var m = date.getMonth() + 1;
    var month = (m < 10) ? '0' + m : m;
    var yy = date.getYear();
    var year = (yy < 1000) ? yy + 1900 : yy;

    return day + "/" + month + "/" + year;
}

utils.showOverlay  = function(overlayDiv) {
    this.hideOverlay(overlayDiv);
    var overlay = $('<div id="overlay"> </div>');
    overlay.appendTo(overlayDiv);
}


utils.hideOverlay  = function(overlayDiv) {
    if (overlayDiv != undefined) {
        $("#overlay", overlayDiv).remove()
    }
}

utils.showSpinner  = function(overlayDiv,message) {
    var msg = '';
    if (message != undefined)
        msg = message;
    else
        msg = 'Attendere prego';
    var overlaySrc = '<div id="overlay"> <div id="spinner"><table border="0" width="100%" height="100%"><tr><td width="32px" style="vertical-align: middle; text-align:center;"><img style="margin-left: 8px;"  src="library/images/spinner.gif"></td><td  style="vertical-align: middle; padding-left: 8px; text-align: left; font-family: Helvetica; font-size: 12px; color:blue;  ">'+msg+'</td></tr></table></div> </div>';
    var overlay = $(overlaySrc);
    overlay.appendTo(overlayDiv)
}
/**
 *
 */
utils.logGroup = function(obj) {
    console.log('-----------------------------------------------');
    console.log(obj);
    console.log('-----------------------------------------------');
}
utils.logDebug = function(obj) {
    console.log(obj);
}
utils.logGroupEnd = function() {
    console.log('-----------------------------------------------');
}

function config () {
    this.db = null;
    this.shortName = 'iMyTripDB';
    this.version = '1.0';
    this.displayName = 'iMyTrip Config DB';
    this.maxSize = 1024 * 1024 ;

    this.sqlSentence = {
        CREATE : "CREATE TABLE IF NOT EXISTS ConfigDB (configInfo TEXT NOT NULL )",
        INSERT : "INSERT INTO ConfigDB (configInfo ) VALUES ( ?)",
        UPDATE : "UPDATE ConfigDB SET configInfo = ? ",
        GET    : "SELECT configInfo FROM ConfigDB",
        COUNT  : "SELECT COUNT(*) FROM ConfigDB",
        DROP   : "DROP TABLE ConfigDB"
    };
    /**
     * Initialize Datatabase Function
     */
    this.initialize = function ( _callback ) {
        try {
            if ( window.openDatabase ) {
                this.db = openDatabase(this.shortName, this.version, this.displayName, this.maxSize);
                if ( !this.db ) {
                    // titleText, message, module, isJsonSource, callback)
                    notifier.error('CSDDb database non disponibile','Funzione config.createConfigDB ritorna null', 'utils.js config.initialize' , false);
                } else {
                    this.validateDB(_callback);
                }
            } else {
                notifier.error('CSDDb database non disponibile','Servizio SQLLite non disponibile', 'utils.js config.initialize', false);
            }
        } catch( e ) {
            notifier.exception("Error trying to open database " + this.shortName, e, 'utils.js config.initialize' );
        }
    };
    /**
     *  validateDB function
     */
    this.validateDB = function (_callback) {
        var _this = this;
        this.db.transaction(
            function(transaction) {
                transaction.executeSql( _this.sqlSentence.COUNT, [],
                    function(transaction, result) {
                        if ( typeof _callback === "function" ) {
                            _callback.apply({}, [] );
                        }
                    },
                    function( transaction, error) {
                        transaction.executeSql( _this.sqlSentence.CREATE, [],
                            function(result) {
                                // create our single row
                                _this.setDefaultConfiguration(_callback);

                            });
                    });
            });
    };
    /**
     * getConfiguration  function
     */
    this.getConfiguration = function ( _callback) {
        var _this = this;
        this.db.transaction(function(transaction) {
            transaction.executeSql( _this.sqlSentence.GET, [],
                function(transaction, result) {
                    if (result.rows.length > 0) {
                        var _row = result.rows.item(0);
                        var configInfo = _row['configInfo'];
                        var configObject = JSON.parse(configInfo);
                        if ( typeof _callback === "function" ) {
                            _callback.apply(  {}, [configObject]  );
                        }
                    } else {
                        _this.setDefaultConfiguration(_callback);
                    }
                },
                function(transaction, error) {
                    notifier.error('Errore in accesso a iMyTrip database ','Impossibile recuprare i dati dalla tabella ' + _this.shortName + ' ' + error, 'utils.js config.getConfiguration', false);
                 });
        });
    };
    /**
     * setConfiguration  function
     */
    this.setConfiguration = function ( json, _callback) {
        var _this = this;
        this.db.transaction(function(transaction) {
            transaction.executeSql( _this.sqlSentence.UPDATE,[json] ,
                function(transaction, result) {
                    if (result.rowsAffected > 0) {
                        if ( typeof _callback === "function" ) {
                            _callback.apply(  {}, []   );
                        }
                    } else  {
                        transaction.executeSql( _this.sqlSentence.INSERT, [json] ,
                            function (transaction, result) {
                                if ( typeof _callback === "function" ) {
                                    _callback.apply( {}, [ ]);
                                }
                            },
                            function(transaction, error) {
                                notifier.error('Errore in accesso a iMyTrip database ','Impossibile inserire i dati dalla tabella ' + _this.shortName + ' ' + error, 'utils.js config.getConfiguration', false);
                            });
                    }
                },
                function(transaction, error) {
                    notifier.error('Errore in accesso a iMyTrip database ','Impossibile aggiornare i dati dalla tabella ' + _this.shortName + ' ' + error, 'utils.js config.getConfiguration', false);
                });
        });
    };
    /**
     *  setDefaultConfiguration configuration
     */
    this.setDefaultConfiguration = function (_callback) {
        var configInfo = '{"server": "http://192.168.1.2:8080/iMyTripLogic/resources/", "timeout" : 30000, "rememberMe" : false, "userId": "", "password":"" }';
        this.setConfiguration(configInfo, function()
            {
                _callback.apply(  {}, [configInfo]  );
            }
        );
    }
}


function uty_trim(str,chars)
{
    return uty_ltrim(uty_rtrim(str,chars),chars);
}
function uty_ltrim(str, chars)
{
    chars = chars || "\\s";
    return str.replace(new RegExp("^["+chars+"]+","g"),"");
}
function uty_rtrim(str,chars)
{
    chars = chars || "\\s";
    return str.replace(new RegExp("["+chars+"]+$","g"),"");
}
function uty_starts(haystack,needle)
{
    return(haystack.match("^"+needle)==needle);
}
function uty_ends(haystack,needle)
{
    return(haystack.match(needle+"$")==needle);
}
function uty_right(str, chr)
{
    return str.slice(str.length-chr,str.length);
}
function uty_left(str, chr)
{
    return str.slice(0, chr - str.length);
}