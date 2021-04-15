package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RezerwacjaInfo extends AppCompatActivity {

    ImageView logoInfo;
    TextView Imieinfo,imieinfo,Nazwiskoinfo,nazwiskoinfo,Emailinfo,emailinfo,Godzinainfo,godzinainfo,
    Datainfo,datainfo,Telefoninfo,telefoninfo,Stolikinfo,stolikinfo,Iloscinfo,iloscinfo;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    String kod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezerwacja_info);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        Rezerwacja rezerwacja=(Rezerwacja) getIntent().getSerializableExtra("rezerwacja");

        logoInfo=findViewById(R.id.logoinfoimageview);
        Imieinfo=findViewById(R.id.ImieInfoTextView);
        imieinfo=findViewById(R.id.imieInfoTextVIew);
        Nazwiskoinfo=findViewById(R.id.NazwiskoInfoTextView);
        nazwiskoinfo=findViewById(R.id.nazwiskoInfoTextView);
        Emailinfo=findViewById(R.id.EmailInfoTextVIew);
        emailinfo=findViewById(R.id.emailInfoTextView);
        Godzinainfo=findViewById(R.id.GodzinaInfoTextView);
        godzinainfo=findViewById(R.id.godzinaInfoTextView);
        Datainfo=findViewById(R.id.DatainfoTextView);
        datainfo=findViewById(R.id.dataInfoTextView);
        Telefoninfo=findViewById(R.id.TelefonInfoTextView);
        telefoninfo=findViewById(R.id.telefonInfoTextView);
        Stolikinfo=findViewById(R.id.StolikInfoTextView);
        stolikinfo=findViewById(R.id.stolikInfoTextView);
        Iloscinfo=findViewById(R.id.IloscInfoTextView);
        iloscinfo=findViewById(R.id.iloscInfoTextView);

        kod=rezerwacja.getKod();
        downloadImage();

        imieinfo.setText(rezerwacja.getImie());
        nazwiskoinfo.setText(rezerwacja.getNazwisko());
        emailinfo.setText(rezerwacja.getEmail());
        godzinainfo.setText(rezerwacja.getGodzina());
        datainfo.setText(rezerwacja.getData());
        telefoninfo.setText(rezerwacja.getTelefon());
        stolikinfo.setText(rezerwacja.getStolik());
        iloscinfo.setText(rezerwacja.getIlosc());
    }

    public void downloadImage(){
        StorageReference imageReference=storageReference.child("KodQR/"+kod+".jpg");

        long MAXBYTES=1024*1024;

        imageReference.getBytes(MAXBYTES).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                logoInfo.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
