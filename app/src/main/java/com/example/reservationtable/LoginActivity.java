package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.flags.Singletons;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText email,haslo;
    Button loguj;
    TextView przypomnij,rejestruj,mail,password;
    ImageView logo;
    public String Email,Haslo;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser uzytkownik = firebaseAuth.getCurrentUser();
        if(uzytkownik!=null) {
            String email=uzytkownik.getEmail();
            if (email.equals("maciej.oles@gmail.com")) {
                Intent intent = new Intent(getApplicationContext(), MenuklientActivity.class);
                Email=email;
                intent.putExtra("Email",Email);
                startActivity(intent);
            }
            if(email.equals("reservationtable1@gmail.com")) {
                Intent intent = new Intent(getApplicationContext(), MenurestauracjaActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email=findViewById(R.id.emaileditText);
        mail=findViewById(R.id.emailtextView);
        haslo=findViewById(R.id.hasloeditText);
        password=findViewById(R.id.haslotextView);
        loguj=findViewById(R.id.zalogujbutton);
        przypomnij=findViewById(R.id.przypomnijtextview);
        rejestruj=findViewById(R.id.rejestrujtextView);
        logo=findViewById(R.id.logoimageView);

        firebaseAuth=FirebaseAuth.getInstance();

        loguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email=email.getText().toString();
                Haslo=haslo.getText().toString();
                if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Haslo)) {
                    firebaseAuth.signInWithEmailAndPassword(Email, Haslo)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                            if (Email.equals("maciej.oles@gmail.com")) {
                                                Intent intent=new Intent(LoginActivity.this,MenuklientActivity.class);
                                                intent.putExtra("Email",Email);
                                                startActivity(intent);
                                                //startActivity(new Intent(LoginActivity.this, MenuklientActivity.class));
                                            }
                                            if(Email.equals("reservationtable1@gmail.com")){
                                                startActivity(new Intent(LoginActivity.this, MenurestauracjaActivity.class));
                                            }
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Proszę, zweryfikuj swój email",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        przypomnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,PrzypomnieniehaslaActivity.class));
            }
        });

        rejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RejestacjaActivity.class));
            }
        });
    }
}
