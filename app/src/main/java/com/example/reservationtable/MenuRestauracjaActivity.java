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

public class MenuRestauracjaActivity extends AppCompatActivity {
    Button buttonRezerwacje,buttonSkaner,buttonSoliki,buttonWyszukiwanie;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menurestauracja);

        buttonRezerwacje=findViewById(R.id.rezerwacjebutton);
        buttonSkaner=findViewById(R.id.skanerbutton);
        buttonSoliki=findViewById(R.id.stolikibutton);
        buttonWyszukiwanie=findViewById(R.id.wyszukiwaniebutton);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonRezerwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaActivity.this,HistoriaActivity.class));
            }
        });

        buttonSkaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaActivity.this,SkanerActivity.class));
            }
        });

        buttonWyszukiwanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaActivity.this,WyszukiwanierezerwacjiActivity.class));
            }
        });

        buttonSoliki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRestauracjaActivity.this,StolikiOffActivity.class));
            }
        });
    }

    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenuRestauracjaActivity.this,LogowanieActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
