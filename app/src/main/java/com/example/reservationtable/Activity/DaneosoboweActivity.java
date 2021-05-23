package com.example.reservationtable.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reservationtable.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class DaneosoboweActivity extends AppCompatActivity {

    private static final String TAG="DaneOsobowe";
    EditText editImie,editNazwisko,editEmail,editTelefon,editIloscOsob,editGodzina,editData;
    Button buttonRezerwuj;
    CheckBox checkZapiszwGalerii;
    ImageView imageKodQR;
    Bitmap bitmap;

    String ilosc,stolik,email,daneRezerwacji;

    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    public final static int QRCodeWidth=500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daneosobowe);

        editImie=findViewById(R.id.imieeditText);
        editNazwisko=findViewById(R.id.nazwiskoeditText);
        editEmail=findViewById(R.id.emaileditText);
        editTelefon=findViewById(R.id.numereditText);
        editIloscOsob=findViewById(R.id.iloscosobeditText);
        editGodzina=findViewById(R.id.godzinaeditText);
        editData=findViewById(R.id.dataeditText);
        buttonRezerwuj=findViewById(R.id.rezerwujbutton);
        checkZapiszwGalerii=findViewById(R.id.table3checkBox);
        imageKodQR=findViewById(R.id.imageView8);

        Intent intent=getIntent();
        String wybranaData=intent.getStringExtra("Data");
        String wybranaGodzina=intent.getStringExtra("Godzina");
        String emailUzytkownika=intent.getStringExtra("Email");
        int wybranyStolik=intent.getIntExtra("Stolik",0);
        int wybranaIloscOsob=intent.getIntExtra("Osob",0);

        ilosc=String.valueOf(wybranaIloscOsob);
        stolik=String.valueOf(wybranyStolik);

        editIloscOsob.setText(String.valueOf(wybranaIloscOsob));
        editGodzina.setText(wybranaGodzina);
        editData.setText(wybranaData);
        editEmail.setText(emailUzytkownika);

        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();

        Map<String,Object> daneRezerwacyjne=new HashMap<>();

            buttonRezerwuj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String imie = editImie.getText().toString();
                    String nazwisko = editNazwisko.getText().toString();
                    email = editEmail.getText().toString();
                    String telefon = editTelefon.getText().toString();
                        if (imie.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego imienia!", Toast.LENGTH_SHORT).show();
                        } else if (nazwisko.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego nazwiska!", Toast.LENGTH_SHORT).show();
                        } else if (email.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego emaila!", Toast.LENGTH_SHORT).show();
                        } else if (telefon.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego numeru telefonu!", Toast.LENGTH_SHORT).show();
                        }

                    daneRezerwacji ="Imię i Nazwisko:" + imie + " " + nazwisko + " Email:" + email + " Telefon:" + telefon + " Stolik:" + stolik + " Dsta:" + wybranaData + " Godzina:" + wybranaGodzina + " Ilość osób:" + ilosc;

                    daneRezerwacyjne.put("Imie", imie);
                    daneRezerwacyjne.put("Nazwisko", nazwisko);
                    daneRezerwacyjne.put("Email", emailUzytkownika);
                    daneRezerwacyjne.put("Telefon", telefon);
                    daneRezerwacyjne.put("Data", wybranaData);
                    daneRezerwacyjne.put("Godzina", wybranaGodzina);
                    daneRezerwacyjne.put("Stolik", stolik);
                    daneRezerwacyjne.put("Ilosc", ilosc);
                    daneRezerwacyjne.put("Kod", daneRezerwacji);


                    firebaseFirestore.collection("Stoliknr" + stolik).add(daneRezerwacyjne)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writting document", e);
                        }
                    });
                    try {
                        bitmap = textToImageEncode(daneRezerwacji);
                        imageKodQR.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                    if (checkZapiszwGalerii.isChecked()) {
                        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "code_scanner", null);
                        Toast.makeText(DaneosoboweActivity.this, "Zapisane w galerii", Toast.LENGTH_SHORT).show();
                    }

                    uploadImage();
                    //startActivity(new Intent(DaneosoboweActivity.this, MenuklientActivity.class));
                }
            });
        }

    private Bitmap textToImageEncode(String value) throws WriterException {
        BitMatrix bitMatrix;
        Hashtable hints=new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        try {
            bitMatrix=new MultiFormatWriter().encode(value, BarcodeFormat.DATA_MATRIX.QR_CODE, QRCodeWidth,QRCodeWidth,hints);
        }catch (IllegalArgumentException e){
            return null;
        }

        int bitMatrixWidth=bitMatrix.getWidth();
        int bitMatrixHeight=bitMatrix.getHeight();
        int[] pixels=new int[bitMatrixWidth*bitMatrixHeight];

        for (int y=0;y<bitMatrixHeight;y++){
            int offset=y*bitMatrixWidth;
            for(int x=0;x<bitMatrixWidth;x++){
                pixels[offset+x]=bitMatrix.get(x,y) ? getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }

        Bitmap bitmap=Bitmap.createBitmap(bitMatrixWidth,bitMatrixHeight,Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,500,0,0,bitMatrixWidth,bitMatrixHeight);
        return bitmap;
    }

    public void uploadImage(){
        StorageReference imageReference=storageReference.child("KodQR/"+daneRezerwacji+".jpg");
        imageKodQR.setDrawingCacheEnabled(true);
        imageKodQR.buildDrawingCache();
        Bitmap bitmap1=((BitmapDrawable) imageKodQR.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] data=byteArrayOutputStream.toByteArray();

        UploadTask uploadTask=imageReference.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"Error uploading image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Intent intent=new Intent(DaneosoboweActivity.this, MenuKlientActivity.class);
                //startActivity(new Intent(DaneosoboweActivity.this, MenuklientActivity.class));
                intent.putExtra("Email", email);
                startActivity(intent);
            }
        });
    }
}
