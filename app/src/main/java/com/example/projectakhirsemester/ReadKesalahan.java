package com.example.projectakhirsemester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadKesalahan extends AppCompatActivity {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RequestKesalahan> rs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_kesalahan);
        rvView = (RecyclerView) findViewById(R.id.rv_kesalahan);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();

        database.child("DataKesalahan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rs = new ArrayList<RequestKesalahan>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    RequestKesalahan r = noteDataSnapshot.getValue(RequestKesalahan.class);
                    r.setKey(noteDataSnapshot.getKey());
                    rs.add(r);
                }
                adapter = new AdapterKesalahanRecycleView(rs, ReadKesalahan.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, ReadKesalahan.class);
    }

    public void onDeleteData(RequestKesalahan request, final int position) {
        if (database != null) {
            database.child("Data").child(request.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ReadKesalahan.this, "Data Telah Terhapus", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}