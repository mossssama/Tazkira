package com.example.alarm.Alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationModule {

    NotificationCompat.Builder builder;
    NotificationManagerCompat managerCompat;
    String channelID;
    Context context;

    public NotificationModule(String notificationTitle, String notificationContent, String notificationName, int notificationIcon, Context context, String channelID) {
        this.channelID=channelID;
        this.context=context;
        notificationBuilder(notificationTitle,notificationContent,notificationIcon);
        managerCompat = NotificationManagerCompat.from(context);
        notificationChecker(notificationName);
    }

    private void notificationBuilder(String notificationTitle,String notificationContent,int notificationIcon){
        this.builder =new NotificationCompat.Builder(this.context,this.channelID);
        this.builder.setContentTitle(notificationTitle);
        this.builder.setContentText(notificationContent);
        this.builder.setSmallIcon(notificationIcon);
        this.builder.setAutoCancel(true);
    }

    private void notificationChecker(String notificationName){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(this.channelID,notificationName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) this.context.getSystemService(context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

    }

    public void fireNotification(){
        managerCompat.notify(1, builder.build());
    }
}
