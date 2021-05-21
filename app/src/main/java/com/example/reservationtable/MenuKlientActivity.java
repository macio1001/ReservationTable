package com.example.reservationtable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.function.LongFunction;

public class MenuKlientActivity extends AppCompatActivity {
    Button buttonHistoria,buttonRezerwuj;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuklient);

        buttonHistoria=findViewById(R.id.historiabutton);
        buttonRezerwuj=findViewById(R.id.rezerwujbutton);
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent=getIntent();
        String email=intent.getStringExtra("Email");


        buttonHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuKlientActivity.this,HistoriaUzytkownikActivity.class);
                intent.putExtra("Email",email);
                startActivity(intent);
            }
        });

        buttonRezerwuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuKlientActivity.this,RezerwujActivity.class);
                intent.putExtra("Email",email);
                startActivity(intent);
            }
        });
    }

    public void Wyloguj(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(MenuKlientActivity.this,LogowanieActivity.class);
        intent.putExtra("finish",true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
