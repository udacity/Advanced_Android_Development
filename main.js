var PUSH_SERVER_URL = 'https://udacity-simple-push-demo.appspot.com';

var registrationTextField = document.querySelector('.js-regid');
var authorizationTextField = document.querySelector('.js-authkey');

var sendPushViaXHRButton = document.querySelector('.js-send-push');
sendPushViaXHRButton.addEventListener('click', function(e) {
  var registrationId = registrationTextField.value;
  var authorization = authorizationTextField.value;
  var payload = '{"location": "LA", "weather": "Sharknado"}';
  
  sendPushMessage(registrationId, authorization, payload);
});

function sendPushMessage(registrationId, authorizationHeader, payload) {
  console.log('Sending a push message');
  console.log('  RegID: ', registrationId);
  console.log('  AuthKey: ', authorizationHeader);
  console.log('  Payload: ', payload);

  formData = {
    endpoint: 'https://android.googleapis.com/gcm/send',
    subscriptionId: registrationId,
    authorization: authorizationHeader,
    payload: payload
  };

  $.ajax(PUSH_SERVER_URL + '/send_push', {
    method: 'post',
    data: $.param(formData),
    success: function(data) {
      console.log('Response = ', data);
    }
  });
}
