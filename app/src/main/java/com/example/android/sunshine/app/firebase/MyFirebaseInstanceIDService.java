package com.example.android.sunshine.app.firebase;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.android.sunshine.app.MainActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = "FCMInstanceIdService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is also called
     * when the InstanceID token is initially generated, so this is where
     * you retrieve the token.
     */
    @Override
    public void onTokenRefresh() {

        Log.w(TAG,"onTokenRefresh of instance id service!");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String token = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(token);

        // You should store a boolean that indicates whether the generated token has been
        // sent to your server. If the boolean is false, send the token to your server,
        // otherwise your server should have already received the token.
        sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER, true).apply();
    }

    /**
     * Normally, you would want to persist the registration to third-party servers. Because we do
     * not have a server, and are faking it with a website, you'll want to log the token instead.
     * That way you can see the value in logcat, and note it for future use in the website.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        Log.i(TAG, "FCM Registration Token: " + token);
    }
}
