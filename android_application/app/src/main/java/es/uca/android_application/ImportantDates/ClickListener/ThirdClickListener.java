package es.uca.android_application.ImportantDates.ClickListener;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.core.app.NotificationCompat;

import es.uca.android_application.R;
import es.uca.android_application.ImportantDates.ImportantDates;
import es.uca.android_application.Localization.Localization;

public class ThirdClickListener extends ClickListener implements RecyclerViewClickListener
{
    private String title;
    private String message;
    private String idEvent;

    public ThirdClickListener(Context context, String title, String message, String idEvent)
    {
        super(context);
        this.title = title;
        this.message = message;
        this.idEvent = idEvent;
    }

    public void onClick(View v)
    {
        Context ctx = this.getContext();

        int NOTIFICATION_ID = 001;
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "cic_channel_001";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            CharSequence name = "cic_channel";
            String Description = "Channel for push notifications of app";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            // mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(this.title)
                .setContentText(this.message);

        Intent resultIntent = new Intent(ctx, Localization.class);
        resultIntent.putExtra("ID_EVENT", this.idEvent);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(ImportantDates.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}