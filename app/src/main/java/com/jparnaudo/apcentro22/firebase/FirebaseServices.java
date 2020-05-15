package com.jparnaudo.apcentro22.firebase;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jparnaudo.apcentro22.AuthActivity;
import com.jparnaudo.apcentro22.R;

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