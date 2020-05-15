package com.jparnaudo.apcentro22.firebase;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseServices extends FirebaseMessagingService {

        private static final String TAG = "MyFirebaseMsgService";

        @Override
        public void onNewToken(String token) {
            Log.d(TAG, "Refreshed token: " + token);
            sendRegistrationToServer(token);
        }

              private void sendRegistrationToServer(String token) {

        }

    }