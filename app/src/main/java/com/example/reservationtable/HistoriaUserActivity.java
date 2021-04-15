package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoriaUserActivity extends AppCompatActivity {

    private static final String TAG="FireLog";
    CalendarView kalendarzUser;
    private RecyclerView kalendarzUserrecyclerview;
    private HistoriaUserAdapter historiaUserAdapter;
    //private LoginActivity loginActivity;
    private List<Rezerwacja> historiaUserList;
    private FirebaseFirestore firebaseFirestore;
    String Miesiac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historiauser);

        kalendarzUser=findViewById(R.id.calendarView2);
        kalendarzUserrecyclerview=(RecyclerView) findViewById(R.id.historiaUserList);

        historiaUserList=new ArrayList<>();
        historiaUserAdapter=new HistoriaUserAdapter(this,historiaUserList);
        kalendarzUserrecyclerview.setHasFixedSize(true);
        kalendarzUserrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        kalendarzUserrecyclerview.setAdapter(historiaUserAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        Intent intent1=getIntent();
        String email1=intent1.getStringExtra("Email");


        kalendarzUser.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                historiaUserList.clear();
                kalendarzUserrecyclerview.getAdapter().notifyDataSetChanged();
                if(miesiac==0){
                    Miesiac="Styczeń";
                }else if(miesiac==1){
                    Miesiac="Luty";
                }else if(miesiac==2){
                    Miesiac="Marzec";
                }else if(miesiac==3){
                    Miesiac="Kwiecień";
                }else if(miesiac==4){
                    Miesiac="Maj";
                }else if(miesiac==5){
                    Miesiac="Czerwiec";
                }else if(miesiac==6){
                    Miesiac="Lipiec";
                }else if(miesiac==7){
                    Miesiac="Sierpień";
                }else if(miesiac==8){
                    Miesiac="Wrzesień";
                }else if(miesiac==9){
                    Miesiac="Październik";
                }else if(miesiac==10){
                    Miesiac="Listopad";
                }else if(miesiac==11){
                    Miesiac="Grudzień";
                }
                String data=dzien+" "+Miesiac+" "+rok;
                firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data",data).whereEqualTo("Email",email1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaUserList.add(rezerwacja);

                                    historiaUserAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data",data).whereEqualTo("Email",email1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaUserList.add(rezerwacja);

                                    historiaUserAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data",data).whereEqualTo("Email",email1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaUserList.add(rezerwacja);

                                    historiaUserAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data",data).whereEqualTo("Email",email1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaUserList.add(rezerwacja);

                                    historiaUserAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data",data).whereEqualTo("Email",email1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaUserList.add(rezerwacja);

                                    historiaUserAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data",data).whereEqualTo("Email",email1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaUserList.add(rezerwacja);

                                    historiaUserAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}
