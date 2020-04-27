package cat.udl.tidic.a_favour;

import android.content.Context;
import android.net.wifi.WifiNetworkSuggestion;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationPush {

    private static final String CHANNEL_ID = "19";
    NotificationCompat.Builder builder;

    public NotificationPush(CharSequence textTitle, CharSequence textContent, Context c){

           builder = new NotificationCompat.Builder(c, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    public void showNotification(int notificationId, Context c){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(c);


        notificationManager.notify(notificationId, builder.build());
    }
}
