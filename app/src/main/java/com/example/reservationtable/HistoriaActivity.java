package com.example.reservationtable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.vision.text.Line;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HistoriaActivity extends AppCompatActivity {

    private static final String TAG="FireLog";
    CalendarView kalendarz;
    private RecyclerView kalendarzrecyclerview;
    private HistoriaAdapter historiaAdapter;
    private List<Rezerwacja> historiaList;
    private FirebaseFirestore firebaseFirestore;

    String Miesiac;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historia);

        kalendarz=findViewById(R.id.calendarView);
        kalendarzrecyclerview=(RecyclerView) findViewById(R.id.historialist);

        historiaList=new ArrayList<>();
        historiaAdapter=new HistoriaAdapter(this,historiaList);
        kalendarzrecyclerview.setHasFixedSize(true);
        kalendarzrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        kalendarzrecyclerview.setAdapter(historiaAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        kalendarz.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                historiaList.clear();
                kalendarzrecyclerview.getAdapter().notifyDataSetChanged();
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
                firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaList.add(rezerwacja);

                                    historiaAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr2").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaList.add(rezerwacja);

                                    historiaAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr3").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaList.add(rezerwacja);

                                    historiaAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr4").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaList.add(rezerwacja);

                                    historiaAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr5").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaList.add(rezerwacja);

                                    historiaAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
                firebaseFirestore.collection("Stoliknr6").whereEqualTo("Data",data).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.d(TAG,"Error:"+e.getMessage());
                        }else{
                            for (DocumentChange documentChange:queryDocumentSnapshots.getDocumentChanges()){
                                if(documentChange.getType()==DocumentChange.Type.ADDED){
                                    Rezerwacja rezerwacja=documentChange.getDocument().toObject(Rezerwacja.class);
                                    historiaList.add(rezerwacja);

                                    historiaAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}
