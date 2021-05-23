package com.example.reservationtable.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservationtable.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RejestacjaActivity extends AppCompatActivity {
    EditText editEmail,editHaslo,editDrugieHaslo;
    Button buttonRejestruj;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rejestracja);

        editEmail=findViewById(R.id.emaileditText);
        editHaslo=findViewById(R.id.hasloeditText);
        editDrugieHaslo=findViewById(R.id.powtorzhasloeditText);
        buttonRejestruj=findViewById(R.id.zarejestrujbutton);

        firebaseAuth= FirebaseAuth.getInstance();

        buttonRejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editEmail.getText().toString();
                String haslo=editHaslo.getText().toString();
                String drugieHaslo=editDrugieHaslo.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(haslo) && !TextUtils.isEmpty(drugieHaslo)) {
                    if (haslo.length() < 6) {
                        Toast.makeText(RejestacjaActivity.this, "Hasło musi mieć conajmniej 6 znaków!", Toast.LENGTH_LONG).show();
                    } else if (!haslo.equals(drugieHaslo)) {
                        Toast.makeText(RejestacjaActivity.this, "Podane hasła nie są takie same!", Toast.LENGTH_LONG).show();
                    } else
                        firebaseAuth.createUserWithEmailAndPassword(email, haslo)
                                .addOnCompleteListener((task) -> {
                                    if (task.isSuccessful()) {
                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RejestacjaActivity.this, "Sukces.Proszę zweryfikować swój adres email",
                                                            Toast.LENGTH_LONG).show();
                                                    editEmail.setText("");
                                                    editHaslo.setText("");
                                                    startActivity(new Intent(RejestacjaActivity.this, LogowanieActivity.class));
                                                } else {
                                                    Toast.makeText(RejestacjaActivity.this, task.getException().getMessage(),
                                                            Toast.LENGTH_LONG).show();
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
