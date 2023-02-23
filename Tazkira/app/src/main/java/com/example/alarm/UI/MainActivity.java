package com.example.alarm.UI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.alarm.API.SingletonRetrofitClient;
import com.example.alarm.Alarm.AlarmReceiver;
import com.example.alarm.HelperClasses.Generator;
import com.example.alarm.POJO.Verse;
import com.example.alarm.R;
import com.example.alarm.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    long TIME =10*1000;     //10 sec
    long INTERVAL=5*1000;   //5 sec

    static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        requestVerse(Generator.getRandomIntegerInRange(1,6236),this);

        binding.getNotify.setOnClickListener((View view)-> {
            requestVerse(Generator.getRandomIntegerInRange(1,6236),this);
            AlarmReceiver.alarmBuilder(this,0,intent,PendingIntent.FLAG_IMMUTABLE|PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmReceiver.setHourlyAlarm(02,02,AlarmReceiver.alarmManager,AlarmReceiver.pendingIntent);
        });

        binding.stopNotify.setOnClickListener((View v)-> {
            AlarmReceiver.alarmBuilder(this,0,intent,PendingIntent.FLAG_IMMUTABLE|PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmReceiver.setHourlyAlarm(00,41,AlarmReceiver.alarmManager,AlarmReceiver.pendingIntent);
            AlarmReceiver.setAlarmAfterSpecificTime(AlarmReceiver.ONE_CENTURY,AlarmReceiver.alarmManager,AlarmReceiver.pendingIntent);
        });

    }

    public static void requestVerse(int num, Context context){

        SingletonRetrofitClient.getInstance().getApi().getVerse(num).enqueue(new Callback<Verse>() {
            @Override
            public void onResponse(Call<Verse> call, Response<Verse> response) {
                intent = new Intent(context,AlarmReceiver.class);
                intent.putExtra("Title", "تذكرة");
                intent.putExtra("Content", response.body().getData().getText());
                intent.putExtra("ID", "1");
                intent.putExtra("Name", "تذكرة");
            }

            @Override
            public void onFailure(Call<Verse> call, Throwable t) {

            }
        });
    }


}

