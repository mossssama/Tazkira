package com.example.alarm.Alarm;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.example.alarm.API.SingletonRetrofitClient;
import com.example.alarm.HelperClasses.Generator;
import com.example.alarm.POJO.Verse;
import com.example.alarm.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmReceiver extends BroadcastReceiver {

    public static PendingIntent pendingIntent;
    public static AlarmManager alarmManager;

    static long ONE_SECOND=1000;
    static long ONE_MINUTE=60*ONE_SECOND;
    static long ONE_HOUR=60*ONE_MINUTE;
    static long ONE_DAY=24*ONE_HOUR;
    static long ONE_WEEK=7*ONE_DAY;
    static long ONE_MONTH=30*ONE_DAY;
    static long ONE_YEAR=12*ONE_MONTH;
    public static long ONE_CENTURY=100*ONE_YEAR;

    @Override
    public void onReceive(Context context, Intent intent) {
        SingletonRetrofitClient.getInstance().getApi().getVerse(Generator.getRandomIntegerInRange(1,6236)).enqueue(new Callback<Verse>() {
            @Override
            public void onResponse(Call<Verse> call, Response<Verse> response) {
                String noteTitle = intent.getStringExtra("Title");
                String noteID=intent.getStringExtra("ID");
                String noteName=intent.getStringExtra("Name");
                NotificationModule notification = new NotificationModule(noteTitle,response.body().getData().getText(), noteName, R.drawable.ic_launcher_background, context.getApplicationContext(), noteID);
                notification.fireNotification();
            }

            @Override
            public void onFailure(Call<Verse> call, Throwable t) {

            }
        });
    }

    public static void alarmBuilder(Context context, int requestCode, Intent intent, int flag){
        pendingIntent=PendingIntent.getBroadcast(context,requestCode,intent,flag);
        alarmManager=(AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    public static void setAlarmAfterSpecificTime(long timeInMillis, AlarmManager alarmManager, PendingIntent pendingIntent){
        long currentTimeInMilliSeconds= SystemClock.elapsedRealtime();
        long alarmTime=currentTimeInMilliSeconds+timeInMillis;
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME,alarmTime,pendingIntent);
    }

    public static void setAlarmAtSpecificTime(int hour, int minutes, AlarmManager alarmManager, PendingIntent pendingIntent){
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minutes);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }

    public static void setHourlyAlarm(int hour, int minutes, AlarmManager alarmManager, PendingIntent pendingIntent){
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minutes);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),ONE_HOUR,pendingIntent);
    }

    public static void setDailyAlarm(int hour, int minutes, AlarmManager alarmManager, PendingIntent pendingIntent){
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minutes);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),ONE_DAY,pendingIntent);
    }

}
