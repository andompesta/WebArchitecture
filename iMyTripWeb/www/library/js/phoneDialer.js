var PhoneDialer = function() {

}

PhoneDialer.prototype.dial = function(phnum) {
    // alert('calling cordova PhoneDialer.dialPhone')
    Cordova.exec("PhoneDialer.dialPhone", {"number" : phnum });
};

if(!window.plugins) {
    window.plugins = {};
}
if(!window.plugins.phoneDialer) {
    // alert('phone dialer init');
    window.plugins.phoneDialer = new PhoneDialer();
}
