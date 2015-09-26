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
  var formData = new FormData();
  
  formData.append('endpoint', 'https://android.googleapis.com/gcm/send');
  formData.append('subscriptionId', registrationId);
  formData.append('authorization', authorizationHeader);
  formData.append('payload', payload);
  
  fetch(PUSH_SERVER_URL + '/send_push', {
    method: 'post',
    body: formData
  }).then(function(response) {
    console.log('Response = ', response);
  }).catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}
