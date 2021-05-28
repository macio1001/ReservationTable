package com.example.reservationtable.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservationtable.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogowanieActivity extends AppCompatActivity {

    EditText editEmail,editHaslo;
    Button buttonLoguj;
    TextView textPrzypomnij,textRejestruj;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser uzytkownik = firebaseAuth.getCurrentUser();
        if(uzytkownik!=null) {
            String email=uzytkownik.getEmail();
            if (email.equals("maciej.oles@gmail.com")) {
                Intent intent = new Intent(getApplicationContext(), MenuKlientActivity.class);
                intent.putExtra("Email",email);
                startActivity(intent);
            }
            if(email.equals("reservationtable1@gmail.com")) {
                Intent intent = new Intent(getApplicationContext(), MenuRestauracjaActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editEmail=findViewById(R.id.emaileditText);
        editHaslo=findViewById(R.id.hasloeditText);
        buttonLoguj=findViewById(R.id.zalogujbutton);
        textRejestruj=findViewById(R.id.rejestrujtextView);
        textPrzypomnij=findViewById(R.id.przypomnijtextview);

        firebaseAuth=FirebaseAuth.getInstance();

        buttonLoguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editEmail.getText().toString();
                String haslo=editHaslo.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(haslo)) {
                    firebaseAuth.signInWithEmailAndPassword(email, haslo)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                            if (email.equals("maciej.oles@gmail.com")) {
                                                Intent intent=new Intent(LogowanieActivity.this,MenuKlientActivity.class);
                                                intent.putExtra("Email",email);
                                                startActivity(intent);
                                            }
                                            if(email.equals("reservationtable1@gmail.com")){
                                                startActivity(new Intent(LogowanieActivity.this, MenuRestauracjaActivity.class));
                                            }
                                        } else {
                                            Toast.makeText(LogowanieActivity.this, "Proszę, zweryfikuj swój email",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(LogowanieActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        textPrzypomnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogowanieActivity.this, PrzypomnienieHaslaActivity.class));
            }
        });

        textRejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogowanieActivity.this, RejestacjaActivity.class));
            }
        });
    }
}
