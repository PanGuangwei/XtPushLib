package com.xingtel.xtpushlib;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Receiver which trigger action on {@link XtPushListener}
 */
public class PushListenerService extends FirebaseMessagingService {
    private static final String TAG = PushListenerService.class.getSimpleName();

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        XtPushManager.getInstance(getApplicationContext()).onMessage(remoteMessage);
    }
}
