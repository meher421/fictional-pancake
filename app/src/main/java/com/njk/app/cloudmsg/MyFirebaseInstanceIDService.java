package com.njk.app.cloudmsg;

/**
 * Created by meher.c on 24-08-2016.
 */
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.njk.backend.registration.Registration;

import java.io.IOException;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseService-123456";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        try{
            sendRegistrationToServer(refreshedToken);
        }catch (IOException e)
        {
            Log.e(TAG,"Error sending refresh token");

        }
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
     private void sendRegistrationToServer(String token) throws IOException {
//        Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(),
//                new AndroidJsonFactory(), null)
//                // Need setRootUrl and setGoogleClientRequestInitializer only for local testing,
//                // otherwise they can be skipped
//                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                    @Override
//                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
//                            throws IOException {
//                        abstractGoogleClientRequest.setDisableGZipContent(true);
//                    }
//                });

         Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(),
                 new AndroidJsonFactory(), null)
                 .setRootUrl("https://fcm-test-141505.appspot.com/_ah/api/");
        Registration regService = builder.build();
        regService.register(token).execute();
    }
}