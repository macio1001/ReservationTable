package com.example.reservationtable;

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
import android.widget.TextView;
import android.widget.Toast;

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

    TextView daneosobowe,imie,nazwisko,email,telefon,iloscosob,godzina,data;
    EditText imieedit,nazwiskoedit,emailedit,telefonedit,iloscosobedit,godzinaedit,dataedit;
    Button rezerwuj;
    CheckBox wyborgaleria;
    String Imie,Nazwisko,Email,Telefon,Ilosc;
    public FirebaseFirestore firebaseFirestore;
    public FirebaseStorage firebaseStorage;
    public StorageReference storageReference;
    private static final String TAG="FireLog";
    public final static int QRCodeWidth=500;
    ImageView kodQR;
    Bitmap bitmap;
    String Text,stolik1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daneosobowe);

        daneosobowe=findViewById(R.id.naglowektextView);
        imie=findViewById(R.id.imietextView);
        imieedit=findViewById(R.id.imieeditText);
        nazwisko=findViewById(R.id.nazwiskotextView);
        nazwiskoedit=findViewById(R.id.nazwiskoeditText);
        email=findViewById(R.id.emailtextView);
        emailedit=findViewById(R.id.emaileditText);
        telefon=findViewById(R.id.telefontextView);
        telefonedit=findViewById(R.id.numereditText);
        rezerwuj=findViewById(R.id.rezerwujbutton);
        wyborgaleria=findViewById(R.id.dowloadtogallery);
        iloscosob=findViewById(R.id.iloscosobtextView);
        iloscosobedit=findViewById(R.id.iloscosobeditText);
        godzina=findViewById(R.id.godzinatextView);
        godzinaedit=findViewById(R.id.godzinaeditText);
        data=findViewById(R.id.datatextView);
        dataedit=findViewById(R.id.dataeditText);
        kodQR=findViewById(R.id.imageView8);

        Intent intent1=getIntent();
        String wybranadata2=intent1.getStringExtra("Data");
        String wybranagodzina2=intent1.getStringExtra("Godzina");
        String Email1=intent1.getStringExtra("Email");
        int stolik=intent1.getIntExtra("Stolik",0);
        int ilosc2=intent1.getIntExtra("Osob",0);
        String godzinakoncowa2=intent1.getStringExtra("GodzinaKoncowa");

        Ilosc=String.valueOf(ilosc2);

        stolik1=String.valueOf(stolik);
        String podanyemail=Email1;

        iloscosobedit.setText(Ilosc);
        godzinaedit.setText(wybranagodzina2);
        dataedit.setText(wybranadata2);
        emailedit.setText(podanyemail);

        Map<String,Object> daneosobowe=new HashMap<>();
        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();


            rezerwuj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Imie = imieedit.getText().toString();
                    Nazwisko = nazwiskoedit.getText().toString();
                    Email = emailedit.getText().toString();
                    Telefon = telefonedit.getText().toString();
                        if (Imie.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego imienia!", Toast.LENGTH_SHORT).show();
                        } else if (Nazwisko.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego nazwiska!", Toast.LENGTH_SHORT).show();
                        } else if (Email.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego emaila!", Toast.LENGTH_SHORT).show();
                        } else if (Telefon.trim().length() == 0) {
                            Toast.makeText(DaneosoboweActivity.this, "Nie wprowadziłeś swojego numeru telefonu!", Toast.LENGTH_SHORT).show();
                        }

                    Text = Imie + " " + Nazwisko + " " + Email + " " + Telefon + " " + stolik + " " + wybranadata2 + " " + wybranagodzina2 + " " + godzinakoncowa2 + " " + ilosc2;

                    daneosobowe.put("Imie", Imie);
                    daneosobowe.put("Nazwisko", Nazwisko);
                    daneosobowe.put("Email", podanyemail);
                    daneosobowe.put("Telefon", Telefon);
                    daneosobowe.put("Data", wybranadata2);
                    daneosobowe.put("Godzina", wybranagodzina2);
                    daneosobowe.put("Stolik", stolik1);
                    daneosobowe.put("Ilosc", Ilosc);
                    daneosobowe.put("Kod",Text);

                    firebaseFirestore.collection("Stoliknr" + stolik).add(daneosobowe)
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
                        bitmap = textToImageEncode(Text);
                        kodQR.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                    if (wyborgaleria.isChecked()) {
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
        StorageReference imageReference=storageReference.child("KodQR/"+Text+".jpg");
        kodQR.setDrawingCacheEnabled(true);
        kodQR.buildDrawingCache();
        Bitmap bitmap1=((BitmapDrawable) kodQR.getDrawable()).getBitmap();
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
                Intent intent=new Intent(DaneosoboweActivity.this,MenuklientActivity.class);
                //startActivity(new Intent(DaneosoboweActivity.this, MenuklientActivity.class));
                intent.putExtra("Email", Email);
                startActivity(intent);
            }
        });
    }
}
