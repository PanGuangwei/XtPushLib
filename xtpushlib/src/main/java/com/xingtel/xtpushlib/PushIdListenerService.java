package com.xingtel.xtpushlib;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;

import androidx.annotation.NonNull;

/**
 * This service do the unique task ; save FCM Id token locally & remotely
 */
public class PushIdListenerService extends FirebaseMessagingService {
    private static final String TAG = PushIdListenerService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        startService(new Intent(this, SavePushIdService.class));
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
/*    @Override
    public void onTokenRefresh() {
        startService(new Intent(this, SavePushIdService.class));
    }*/
}
