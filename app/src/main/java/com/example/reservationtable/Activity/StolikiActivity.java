package com.example.reservationtable.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reservationtable.R;
import com.example.reservationtable.Rezerwacja;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class StolikiActivity extends AppCompatActivity {

    private static final String TAG="StolikiActivity";

    ImageView imageStolikPierwszy,imageStolikDrugi,imageStolikTrzeci,imageStolikCzwarty,imageStolikPiaty,imageStolikSzosty;
    Button buttonDalej;

    int stolik=0;
    Boolean onOffPierwszy=false,onOffDrugi=false,onOffTrzeci=false,onOffCzwarty=false,onOffPiaty=false,onOffSzosty=false;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stoliki);

        imageStolikPierwszy=findViewById(R.id.stolik1);
        imageStolikDrugi=findViewById(R.id.stolik2);
        imageStolikTrzeci=findViewById(R.id.stolik3);
        imageStolikCzwarty=findViewById(R.id.stolik4);
        imageStolikPiaty=findViewById(R.id.stolik5);
        imageStolikSzosty=findViewById(R.id.stolik6);
        buttonDalej=findViewById(R.id.dalejbutton);

        this.firebaseFirestore=FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        String wybranaData=intent.getStringExtra("Data");
        String email=intent.getStringExtra("Email");
        String wybranaGodzina=intent.getStringExtra("Godzina");
        int ilosc=intent.getIntExtra("Osob",0);
        Boolean wylaczStolikPierwszy=intent.getBooleanExtra("WylaczStolik1",false);
        Boolean wylaczStolikDrugi=intent.getBooleanExtra("WylaczStolik2",false);
        Boolean wylaczStolikCzwarty=intent.getBooleanExtra("WylaczStolik4",false);
        Boolean wylaczStolikPiaty=intent.getBooleanExtra("WylaczStolik5",false);

        opcjestolikow();

        wylaczonystolik1();
        wylaczonystolik2();
        wylaczonystolik3();
        wylaczonystolik4();
        wylaczonystolik5();
        wylaczonystolik6();

        if(ilosc==1 || ilosc==2){
            imageStolikPierwszy.setBackgroundColor(Color.GREEN);
            imageStolikPierwszy.setClickable(true);
            imageStolikDrugi.setBackgroundColor(Color.GREEN);
            imageStolikDrugi.setClickable(true);
                firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                    Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                    rezerwacja.setDocumentId(documentSnapshot.getId());
                                    String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        imageStolikPierwszy.setBackgroundColor(Color.RED);
                                        imageStolikPierwszy.setClickable(false);
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,e.toString());
                    }
                });
                firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                    Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                    rezerwacja.setDocumentId(documentSnapshot.getId());
                                    String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        imageStolikDrugi.setBackgroundColor(Color.RED);
                                        imageStolikDrugi.setClickable(false);
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,e.toString());
                    }
                });
        }
        else if(ilosc==3){
            imageStolikTrzeci.setBackgroundColor(Color.GREEN);
            imageStolikTrzeci.setClickable(true);
            firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                if(documentId!=null){
                                    imageStolikTrzeci.setBackgroundColor(Color.RED);
                                    imageStolikTrzeci.setClickable(false);
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }
        else if(ilosc==4){
            imageStolikCzwarty.setBackgroundColor(Color.GREEN);
            imageStolikCzwarty.setClickable(true);
            imageStolikPiaty.setBackgroundColor(Color.GREEN);
            imageStolikPiaty.setClickable(true);
            firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        imageStolikCzwarty.setBackgroundColor(Color.RED);
                                        imageStolikCzwarty.setClickable(false);
                                    }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
            firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        imageStolikPiaty.setBackgroundColor(Color.RED);
                                        imageStolikPiaty.setClickable(false);
                                    }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }
        if(ilosc==5 || ilosc==6){
            imageStolikSzosty.setBackgroundColor(Color.GREEN);
            imageStolikSzosty.setClickable(true);
            firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data",wybranaData).whereEqualTo("Godzina",wybranaGodzina).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        imageStolikSzosty.setBackgroundColor(Color.RED);
                                        imageStolikSzosty.setClickable(false);
                                    }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
        }

        if(wylaczStolikPierwszy==true){
            imageStolikPierwszy.setBackgroundColor(Color.GRAY);
            imageStolikPierwszy.setClickable(false);
            Log.d(TAG,"Lalala:" + wylaczStolikPierwszy);
        }else if(wylaczStolikDrugi==true){
            imageStolikDrugi.setBackgroundColor(Color.GRAY);
            imageStolikDrugi.setClickable(false);
            Log.d(TAG,"Lalala:" + wylaczStolikDrugi);
        }else if(wylaczStolikCzwarty==true){
            imageStolikCzwarty.setBackgroundColor(Color.GRAY);
            imageStolikCzwarty.setClickable(false);
            Log.d(TAG,"Lalala:" + wylaczStolikCzwarty);
        }else if(wylaczStolikPiaty==true){
            imageStolikPiaty.setBackgroundColor(Color.GRAY);
            imageStolikPiaty.setClickable(false);
            Log.d(TAG,"Lalala:" + wylaczStolikPiaty);
        }

        imageStolikPierwszy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik = 1;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.1",Toast.LENGTH_SHORT).show();
            }
        });

            imageStolikDrugi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stolik=2;
                    Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.2",Toast.LENGTH_SHORT).show();
                }
            });


        imageStolikTrzeci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=3;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.3",Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikCzwarty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=4;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.4",Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikPiaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=5;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.5",Toast.LENGTH_SHORT).show();
            }
        });

        imageStolikSzosty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=6;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.6",Toast.LENGTH_SHORT).show();
            }
        });

        buttonDalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stolik==0){
                    Toast.makeText(StolikiActivity.this,"Nie wybrałes żadnego stolika!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(StolikiActivity.this, DaneosoboweActivity.class);
                    intent.putExtra("Data", wybranaData);
                    intent.putExtra("Godzina", wybranaGodzina);
                    intent.putExtra("Stolik", stolik);
                    intent.putExtra("Email",email);
                    intent.putExtra("Osob", ilosc);
                    startActivity(intent);
                }
            }
        });
    }

    private void opcjestolikow() {
        imageStolikPierwszy.setVisibility(View.INVISIBLE);
        imageStolikDrugi.setVisibility(View.INVISIBLE);
        imageStolikTrzeci.setVisibility(View.INVISIBLE);
        imageStolikCzwarty.setVisibility(View.INVISIBLE);
        imageStolikPiaty.setVisibility(View.INVISIBLE);
        imageStolikSzosty.setVisibility(View.INVISIBLE);

        imageStolikPierwszy.setClickable(false);
        imageStolikDrugi.setClickable(false);
        imageStolikTrzeci.setClickable(false);
        imageStolikCzwarty.setClickable(false);
        imageStolikPiaty.setClickable(false);
        imageStolikSzosty.setClickable(false);

        imageStolikPierwszy.setBackgroundColor(Color.GRAY);
        imageStolikDrugi.setBackgroundColor(Color.GRAY);
        imageStolikTrzeci.setBackgroundColor(Color.GRAY);
        imageStolikCzwarty.setBackgroundColor(Color.GRAY);
        imageStolikPiaty.setBackgroundColor(Color.GRAY);
        imageStolikSzosty.setBackgroundColor(Color.GRAY);
    }

    private void wylaczonystolik1() {
        firebaseFirestore.collection("Stoliknr1").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffPierwszy = documentSnapshot.getBoolean("OnOff");
                                if (onOffPierwszy == true) {
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }else{
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik2() {
        firebaseFirestore.collection("Stoliknr2").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffDrugi = documentSnapshot.getBoolean("OnOff");
                                if (onOffDrugi == true) {
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }else{
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik3() {
        firebaseFirestore.collection("Stoliknr3").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffTrzeci = documentSnapshot.getBoolean("OnOff");
                                if (onOffTrzeci == true) {
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }else{
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik4() {
        firebaseFirestore.collection("Stoliknr4").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffCzwarty = documentSnapshot.getBoolean("OnOff");
                                if (onOffCzwarty == true) {
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }else{
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik5() {
        firebaseFirestore.collection("Stoliknr5").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onOffPiaty = documentSnapshot.getBoolean("OnOff");
                                if (onOffPiaty == true) {
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }else{
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }
                                Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "Get Failed with " + task.getException());
                        }
                    }
                });
    }
    private void wylaczonystolik6(){
        firebaseFirestore.collection("Stoliknr6").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                onOffSzosty=documentSnapshot.getBoolean("OnOff");
                                if(onOffSzosty==true){
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                }else{
                                    imageStolikPierwszy.setVisibility(View.VISIBLE);
                                    imageStolikDrugi.setVisibility(View.VISIBLE);
                                    imageStolikTrzeci.setVisibility(View.VISIBLE);
                                    imageStolikCzwarty.setVisibility(View.VISIBLE);
                                    imageStolikPiaty.setVisibility(View.VISIBLE);
                                    imageStolikSzosty.setVisibility(View.VISIBLE);
                                }
                                Log.d(TAG,"DocumentSnapshot data: "+documentSnapshot.getData());
                            }else{
                                Log.d(TAG,"No such document");
                            }
                        }else{
                            Log.d(TAG,"Get Failed with "+task.getException());
                        }
                    }
                });
    }
}
