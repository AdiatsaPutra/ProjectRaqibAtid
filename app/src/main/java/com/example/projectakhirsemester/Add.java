package com.example.projectakhirsemester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add extends AppCompatActivity {

    private DatabaseReference database;

    TextInputEditText etJudul;
    TextInputEditText etKeterangan;
    TextInputEditText etTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        database = FirebaseDatabase.getInstance().getReference();

        etJudul = findViewById(R.id.judulKebaikan);
        etKeterangan = findViewById(R.id.keteranganKebaikan);
       etTanggal = findViewById(R.id.tanggalKebaikan);

        Button tambahKebaikan = findViewById(R.id.tambahKebaikan);
        final Request request = (Request) getIntent().getSerializableExtra("Data");
        if (request != null) {
            etJudul.setText(request.getJudul());
            etKeterangan.setText(request.getKeterangan());
           etTanggal.setText(request.getTanggal());
            tambahKebaikan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    request.setJudul(etJudul.getText().toString());
                    request.setKeterangan(etKeterangan.getText().toString());
                   request.setTanggal(etTanggal.getText().toString());

                    updateBarang(request);
                }
            });
        } else {
            tambahKebaikan.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!(etJudul.getText().toString().isEmpty()) &&
                            !(etKeterangan.getText().toString().isEmpty()
                                   && !(etTanggal.getText().toString().isEmpty())
                            ))
                        submitUser(new Request(etJudul.getText().toString(),
                                etKeterangan.getText().toString()
                               , etTanggal.getText().toString()
                        ));
                    else
                        Toast.makeText(getApplicationContext(), "Data Tidak Boleh Kosong", Toast.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etJudul.getWindowToken(), 0);
                }
            });

        }
    }

    private void submitUser(Request request) {
        database.child("Data")
                .push()
                .setValue(request)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                etJudul.setText("");
                                etJudul.setText("");
                               etTanggal.setText("");

                                Toast.makeText(Add.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, Add.class);
    }

    private void updateBarang(Request request) {
        database.child("Data")
                .child(request.getKey())
                .setValue(request)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                etJudul.setText("");
                                etJudul.setText("");
                               etTanggal.setText("");

                                Toast.makeText(Add.this, "Data Berhasil Diupdate!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }
}
