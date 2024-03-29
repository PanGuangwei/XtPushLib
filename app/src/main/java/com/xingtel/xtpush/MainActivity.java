package com.xingtel.xtpush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.xingtel.xtpushlib.XtPushListener;
import com.xingtel.xtpushlib.XtPushManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity implements XtPushListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	protected void onResume() {
		super.onResume();
		XtPushManager.getInstance(this).registerListener(this);
		XtPushManager.getInstance(this).subscribeTopic("top");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		XtPushManager.getInstance(this).unRegisterListener();
	}

	@Override
	public void onDeviceRegistered(String deviceToken) {
		Log.i("TEST", "deviceToken="+deviceToken);
	}


	@Override
	public void onMessage(RemoteMessage remoteMessage) {
		if (remoteMessage.getData()!=null){
			Log.i("TEST", "From: " + remoteMessage.getFrom()+" "+remoteMessage.getData().get("haha"));
		}
		if (remoteMessage.getNotification()!=null){
			Log.i("TEST", "Notification Message Body: " + remoteMessage.getNotification().getBody());
			sendNotification(remoteMessage.getNotification().getBody());
		}
	}

	@Override
	public void onPlayServiceError() {
		Log.i("TEST", "error services");
	}
	/**
	 * Create and show a simple notification containing the received FCM message.
	 *
	 * @param messageBody FCM message body received.
	 */
	private void sendNotification(String messageBody) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
				PendingIntent.FLAG_ONE_SHOT);

		Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContentTitle("FCM Message")
				.setContentText(messageBody)
				.setAutoCancel(true)
				.setSound(defaultSoundUri)
				.setContentIntent(pendingIntent);

		NotificationManager notificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
	}
}
