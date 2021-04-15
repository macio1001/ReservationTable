package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MenurestauracjaActivity extends AppCompatActivity {

    ImageView logo;
    Button rezerwacje,skaner,stoliki,wyszukiwanie;
     public static final int CAMERA_PERMISSION_CODE=100;
    FloatingActionButton wyloguj;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menurestauracja);

        logo=findViewById(R.id.logoimageView);
        rezerwacje=findViewById(R.id.rezerwacjebutton);
        skaner=findViewById(R.id.skanerbutton);
        stoliki=findViewById(R.id.stolikibutton);
        wyszukiwanie=findViewById(R.id.wyszukiwaniebutton);

        wyloguj=findViewById(R.id.wylogujbtn);
        firebaseAuth = FirebaseAuth.getInstance();

        rezerwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenurestauracjaActivity.this,HistoriaActivity.class));
            }
        });

        skaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenurestauracjaActivity.this,SkanerActivity.class));
            }
        });

        wyszukiwanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenurestauracjaActivity.this,WyszukiwanierezerwacjiActivity.class));
            }
        });

        stoliki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenurestauracjaActivity.this,StolikiOff.class));
            }
        });
    }

    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenurestauracjaActivity.this,LoginActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
