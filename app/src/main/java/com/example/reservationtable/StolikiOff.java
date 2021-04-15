package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StolikiOff extends AppCompatActivity {

    private static final String TAG="RezerwujActivity";

    private ImageView stolik1,stolik2,stolik3,stolik4,stolik5,stolik6;
    private TextView tytul;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    private Button akceptuj;
    FirebaseFirestore firebaseFirestore;
    public Boolean stolik1off=false,stolik2off=false,stolik3off=false,stolik4off=false,stolik5off=false,stolik6off=false;
    public Boolean stolik1on=false,stolik2on=false,stolik3on=false,stolik4on=false,stolik5on=false,stolik6on=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoliki_off);

        tytul=findViewById(R.id.OffStolik);
        stolik1=findViewById(R.id.imageView);
        stolik2=findViewById(R.id.imageView2);
        stolik3=findViewById(R.id.imageView3);
        stolik4=findViewById(R.id.imageView4);
        stolik5=findViewById(R.id.imageView5);
        stolik6=findViewById(R.id.imageView6);
        akceptuj=findViewById(R.id.acceotbutton);
        checkBox1=findViewById(R.id.table1checkBox);
        checkBox2=findViewById(R.id.table2checkBox);
        checkBox3=findViewById(R.id.dowloadtogallery);
        checkBox4=findViewById(R.id.table4checkBox);
        checkBox5=findViewById(R.id.table5checkBox);
        checkBox6=findViewById(R.id.table6checkBox);

        firebaseFirestore=FirebaseFirestore.getInstance();

        sprawdzStolik1();
        sprawdzStolik2();
        sprawdzStolik3();
        sprawdzStolik4();
        sprawdzStolik5();
        sprawdzStolik6();

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(checkBox1.isChecked()){
                    firebaseFirestore.collection("Stoliknr1").document("Status").update("OnOff",true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik1off=true;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }else{
                    firebaseFirestore.collection("Stoliknr1").document("Status").update("OnOff",false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik1off=false;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(checkBox2.isChecked()){
                    firebaseFirestore.collection("Stoliknr2").document("Status").update("OnOff",true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik2off=true;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }else{
                    firebaseFirestore.collection("Stoliknr2").document("Status").update("OnOff",false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik2off=false;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(checkBox3.isChecked()){
                    firebaseFirestore.collection("Stoliknr3").document("Status").update("OnOff",true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik3off=true;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }else{
                    firebaseFirestore.collection("Stoliknr3").document("Status").update("OnOff",false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik3off=false;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(checkBox4.isChecked()){
                    firebaseFirestore.collection("Stoliknr4").document("Status").update("OnOff",true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik4off=true;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }else{
                    firebaseFirestore.collection("Stoliknr4").document("Status").update("OnOff",false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik4off=false;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }
            }
        });

        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(checkBox5.isChecked()){
                    firebaseFirestore.collection("Stoliknr5").document("Status").update("OnOff",true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik5off=true;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }else{
                    firebaseFirestore.collection("Stoliknr5").document("Status").update("OnOff",false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik5off=false;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }
            }
        });

        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(checkBox6.isChecked()){
                    firebaseFirestore.collection("Stoliknr6").document("Status").update("OnOff",true)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik6off=true;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }else{
                    firebaseFirestore.collection("Stoliknr6").document("Status").update("OnOff",false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    stolik6off=false;
                                    Log.d(TAG,"DocumentSnapshot succesfully uptated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG,"Error uptading document",e);
                        }
                    });
                }
            }
        });

        akceptuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StolikiOff.this,MenurestauracjaActivity.class));
            }
        });
    }

    public void sprawdzStolik1(){
        firebaseFirestore.collection("Stoliknr1").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                stolik1on=documentSnapshot.getBoolean("OnOff");
                                if(stolik1on==true){
                                    checkBox1.setChecked(true);
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
    };

    public void sprawdzStolik2(){
        firebaseFirestore.collection("Stoliknr2").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                stolik2on=documentSnapshot.getBoolean("OnOff");
                                if(stolik2on==true){
                                    checkBox2.setChecked(true);
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

    public void sprawdzStolik3(){
        firebaseFirestore.collection("Stoliknr3").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                stolik3on=documentSnapshot.getBoolean("OnOff");
                                if(stolik3on==true){
                                    checkBox3.setChecked(true);
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
    };

    public void sprawdzStolik4(){
        firebaseFirestore.collection("Stoliknr4").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                stolik4on=documentSnapshot.getBoolean("OnOff");
                                if(stolik4on==true){
                                    checkBox4.setChecked(true);
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
    };

    public void sprawdzStolik5(){
        firebaseFirestore.collection("Stoliknr5").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                stolik5on=documentSnapshot.getBoolean("OnOff");
                                if(stolik5on.equals(true)){
                                    checkBox5.setChecked(true);
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
    };

    public void sprawdzStolik6(){
        firebaseFirestore.collection("Stoliknr6").document("Status").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot.exists()){
                                stolik6on=documentSnapshot.getBoolean("OnOff");
                                if(stolik6on==true){
                                    checkBox6.setChecked(true);
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
