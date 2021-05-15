package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigInteger;

public class StolikiActivity extends AppCompatActivity {

    private ImageView stolik1,stolik2,stolik3,stolik4,stolik5,stolik6;
    private TextView wyborsotlika;
    private Button dalej;
    public int stolik=0;
    Boolean onoff1=false,onoff2=false,onoff3=false,onoff4=false,onoff5=false,onoff6=false;
    FirebaseFirestore firebaseFirestore;
    private static final String TAG="RezerwujActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stoliki);

        stolik1=findViewById(R.id.stolik1);
        stolik2=findViewById(R.id.stolik2);
        stolik3=findViewById(R.id.stolik3);
        stolik4=findViewById(R.id.stolik4);
        stolik5=findViewById(R.id.stolik5);
        stolik6=findViewById(R.id.stolik6);
        dalej=findViewById(R.id.dalejbutton);
        wyborsotlika=findViewById(R.id.wyborstolika);
        this.firebaseFirestore=FirebaseFirestore.getInstance();
        Intent intent1=getIntent();
        String wybranadata1=intent1.getStringExtra("Data");
        String email1=intent1.getStringExtra("Email");
        String wybranagodzina1=intent1.getStringExtra("Godzina");
        int ilosc1=intent1.getIntExtra("Osob",0);
        Boolean WylaczStoliknr1=intent1.getBooleanExtra("WylaczStolik1",false);
        Boolean WylaczStoliknr2=intent1.getBooleanExtra("WylaczStolik2",false);
        Boolean WylaczStoliknr4=intent1.getBooleanExtra("WylaczStolik4",false);
        Boolean WylaczStoliknr5=intent1.getBooleanExtra("WylaczStolik5",false);

        opcjestolikow();

        wylaczonystolik1();
        wylaczonystolik2();
        wylaczonystolik3();
        wylaczonystolik4();
        wylaczonystolik5();
        wylaczonystolik6();

        if(ilosc1==1 || ilosc1==2){
            stolik1.setBackgroundColor(Color.GREEN);
            stolik1.setClickable(true);
            stolik2.setBackgroundColor(Color.GREEN);
            stolik2.setClickable(true);
                firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data",wybranadata1).whereEqualTo("Godzina",wybranagodzina1).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                    Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                    rezerwacja.setDocumentId(documentSnapshot.getId());
                                    String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        stolik1.setBackgroundColor(Color.RED);
                                        stolik1.setClickable(false);
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,e.toString());
                    }
                });
                firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data",wybranadata1).whereEqualTo("Godzina",wybranagodzina1).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                    Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                    rezerwacja.setDocumentId(documentSnapshot.getId());
                                    String documentId=rezerwacja.getDocumentId();

                                    if(documentId!=null){
                                        stolik2.setBackgroundColor(Color.RED);
                                        stolik2.setClickable(false);
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
        else if(ilosc1==3){
            stolik3.setBackgroundColor(Color.GREEN);
            stolik3.setClickable(true);
            firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data",wybranadata1).whereEqualTo("Godzina",wybranagodzina1).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        stolik3.setBackgroundColor(Color.RED);
                                        stolik3.setClickable(false);
                                    }
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
        else if(ilosc1==4){
            stolik4.setBackgroundColor(Color.GREEN);
            stolik4.setClickable(true);
            stolik5.setBackgroundColor(Color.GREEN);
            stolik5.setClickable(true);
            firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data",wybranadata1).whereEqualTo("Godzina",wybranagodzina1).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        stolik4.setBackgroundColor(Color.RED);
                                        stolik4.setClickable(false);
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG,e.toString());
                }
            });
            firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data",wybranadata1).whereEqualTo("Godzina",wybranagodzina1).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        stolik5.setBackgroundColor(Color.RED);
                                        stolik5.setClickable(false);
                                    }
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
        if(ilosc1==5 || ilosc1==6){
            stolik6.setBackgroundColor(Color.GREEN);
            stolik6.setClickable(true);
            firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data",wybranadata1).whereEqualTo("Godzina",wybranagodzina1).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                                Rezerwacja rezerwacja=documentSnapshot.toObject(Rezerwacja.class);
                                rezerwacja.setDocumentId(documentSnapshot.getId());
                                String documentId=rezerwacja.getDocumentId();

                                if(documentId!=null){
                                    if(documentId!=null){
                                        stolik6.setBackgroundColor(Color.RED);
                                        stolik6.setClickable(false);
                                    }
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

        if(WylaczStoliknr1==true){
            stolik1.setBackgroundColor(Color.GRAY);
            stolik1.setClickable(false);
            Log.d(TAG,"Lalala:" + WylaczStoliknr1);
        }else if(WylaczStoliknr2==true){
            stolik2.setBackgroundColor(Color.GRAY);
            stolik2.setClickable(false);
            Log.d(TAG,"Lalala:" + WylaczStoliknr2);
        }else if(WylaczStoliknr4==true){
            stolik4.setBackgroundColor(Color.GRAY);
            stolik4.setClickable(false);
            Log.d(TAG,"Lalala:" + WylaczStoliknr4);
        }else if(WylaczStoliknr5==true){
            stolik5.setBackgroundColor(Color.GRAY);
            stolik5.setClickable(false);
            Log.d(TAG,"Lalala:" + WylaczStoliknr5);
        }

        stolik1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik = 1;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.1",Toast.LENGTH_SHORT).show();
            }
        });

            stolik2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stolik=2;
                    Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.2",Toast.LENGTH_SHORT).show();
                }
            });


        stolik3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=3;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.3",Toast.LENGTH_SHORT).show();
            }
        });

        stolik4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=4;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.4",Toast.LENGTH_SHORT).show();
            }
        });

        stolik5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=5;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.5",Toast.LENGTH_SHORT).show();
            }
        });

        stolik6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stolik=6;
                Toast.makeText(StolikiActivity.this,"Wybrałeś stolik nr.6",Toast.LENGTH_SHORT).show();
            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stolik==0){
                    Toast.makeText(StolikiActivity.this,"Nie wybrałes żadnego stolika!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(StolikiActivity.this, DaneosoboweActivity.class);
                    intent.putExtra("Data", wybranadata1);
                    intent.putExtra("Godzina", wybranagodzina1);
                    intent.putExtra("Stolik", stolik);
                    intent.putExtra("Email",email1);
                    intent.putExtra("Osob", ilosc1);
                    startActivity(intent);
                }
            }
        });
    }

    private void opcjestolikow() {
        stolik1.setVisibility(View.INVISIBLE);
        stolik2.setVisibility(View.INVISIBLE);
        stolik3.setVisibility(View.INVISIBLE);
        stolik4.setVisibility(View.INVISIBLE);
        stolik5.setVisibility(View.INVISIBLE);
        stolik6.setVisibility(View.INVISIBLE);

        stolik1.setClickable(false);
        stolik2.setClickable(false);
        stolik3.setClickable(false);
        stolik4.setClickable(false);
        stolik5.setClickable(false);
        stolik6.setClickable(false);

        stolik1.setBackgroundColor(Color.GRAY);
        stolik2.setBackgroundColor(Color.GRAY);
        stolik3.setBackgroundColor(Color.GRAY);
        stolik4.setBackgroundColor(Color.GRAY);
        stolik5.setBackgroundColor(Color.GRAY);
        stolik6.setBackgroundColor(Color.GRAY);
    }

    private void wylaczonystolik1() {
        firebaseFirestore.collection("Stoliknr1").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                onoff1 = documentSnapshot.getBoolean("OnOff");
                                if (onoff1 == true) {
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
                                }else{
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
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
                                onoff2 = documentSnapshot.getBoolean("OnOff");
                                if (onoff2 == true) {
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
                                }else{
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
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
                                onoff3 = documentSnapshot.getBoolean("OnOff");
                                if (onoff3 == true) {
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
                                }else{
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
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
                                onoff4 = documentSnapshot.getBoolean("OnOff");
                                if (onoff4 == true) {
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
                                }else{
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
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
                                onoff5 = documentSnapshot.getBoolean("OnOff");
                                if (onoff5 == true) {
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
                                }else{
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
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
                                onoff6=documentSnapshot.getBoolean("OnOff");
                                if(onoff6==true){
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                }else{
                                    stolik1.setVisibility(View.VISIBLE);
                                    stolik2.setVisibility(View.VISIBLE);
                                    stolik3.setVisibility(View.VISIBLE);
                                    stolik4.setVisibility(View.VISIBLE);
                                    stolik5.setVisibility(View.VISIBLE);
                                    stolik6.setVisibility(View.VISIBLE);
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
