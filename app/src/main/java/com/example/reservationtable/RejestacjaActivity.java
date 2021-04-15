package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RejestacjaActivity extends AppCompatActivity {

    ImageView logo;
    TextView mail,password,password2;
    EditText email,haslo,haslo2;
    Button rejestruj;


    FirebaseAuth firebaseAuth;

    String Email;
    String Haslo,Haslo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rejestracja);

        logo=findViewById(R.id.logoimageView);
        mail=findViewById(R.id.emailtextView);
        password=findViewById(R.id.haslotextView);
        password2=findViewById(R.id.powtorzhaslotextView);
        email=findViewById(R.id.emaileditText);
        haslo=findViewById(R.id.hasloeditText);
        haslo2=findViewById(R.id.powtorzhasloeditText);
        rejestruj=findViewById(R.id.zarejestrujbutton);


        firebaseAuth= FirebaseAuth.getInstance();

        rejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email=email.getText().toString();
                Haslo=haslo.getText().toString();
                Haslo2=haslo2.getText().toString();
                if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Haslo)) {
                    firebaseAuth.createUserWithEmailAndPassword(Email, Haslo)
                            .addOnCompleteListener((task) -> {
                                if (task.isSuccessful()) {
                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RejestacjaActivity.this, "Sukces.Proszę zweryfikować swój adres email",
                                                        Toast.LENGTH_LONG).show();
                                                email.setText("");
                                                haslo.setText("");
                                                startActivity(new Intent(RejestacjaActivity.this, LoginActivity.class));
                                            } else {
                                                if(Haslo.length()<6){
                                                    Toast.makeText(RejestacjaActivity.this,"Hasło musi mieć conajmniej 6 znaków!",Toast.LENGTH_LONG).show();
                                                }
                                                else if(!Haslo.equals(Haslo2)){
                                                    Toast.makeText(RejestacjaActivity.this,"Podane hasła nie są takie same!",Toast.LENGTH_LONG).show();
                                                }
                                                else{
                                                    Toast.makeText(RejestacjaActivity.this, task.getException().getMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(RejestacjaActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }
}
