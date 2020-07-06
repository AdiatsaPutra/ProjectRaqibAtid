package com.example.projectakhirsemester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Home extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private DatabaseReference databaseReference;
    private ArrayList<Request> daftarKebaikan;

    private TextView pengingat;
    ImageView pick_time;
    CardView cardKebaikan;
    CardView cardAddKebaikan;
    CardView cardKesalahan;
    CardView cardAddKesalahan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        pick_time = findViewById(R.id.pick_time);
        pick_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFaragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        cardKebaikan = findViewById(R.id.lihat_kebaikan);
        cardAddKebaikan = findViewById(R.id.add_kebaikan);
        cardKesalahan = findViewById(R.id.lihat_keburukan);
        cardAddKesalahan = findViewById(R.id.add_kesalahan);

        cardKebaikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihatKebaikan();
            }
        });
        cardAddKebaikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKebaikan();
            }
        });
        cardKesalahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lihatKesalahan();
            }
        });
        cardAddKesalahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKesalahan();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c) {
        pengingat = findViewById(R.id.pengingat);
        String timeText = "Alarm Diset Pada Pukul: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        pengingat.setText(timeText);
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }


    private void addKebaikan(){
        Intent intent = new Intent(Home.this, Add.class);
        startActivity(intent);
    }

    private void lihatKebaikan(){
        Intent intent = new Intent(Home.this, ReadKebaikan.class);
        startActivity(intent);
    }
    private void addKesalahan(){
        Intent intent = new Intent(Home.this, AddKeburukan.class);
        startActivity(intent);
    }

    private void lihatKesalahan(){
        Intent intent = new Intent(Home.this, ReadKesalahan.class);
        startActivity(intent);
    }
}

