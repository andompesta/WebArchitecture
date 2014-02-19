
/**
 * Created with JetBrains WebStorm.
 * User: marco
 * Date: 12/29/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
MyTrip.myRegisterModel = MyTrip.Model.extend({
    initialize : function () {
        console.log('MyTrip.myRegisterModel initialization');
    },
    username : "",
    name : "",
    surname : "",
    birthDate : new Date(),
    email : "",
    emailConfirm : "",
    password : ""
});