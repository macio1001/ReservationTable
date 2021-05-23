package com.example.reservationtable.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.example.reservationtable.Adapter.HistoriaAdapter;
import com.example.reservationtable.R;
import com.example.reservationtable.Rezerwacja;
import com.example.reservationtable.RezerwacjaUtils;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoriaActivity extends AppCompatActivity {

    private static final String TAG="FireLog";
    CalendarView calendarKalendarz;
    RecyclerView recyclerviewKalendarz;

    HistoriaAdapter historiaAdapter;
    List<Rezerwacja> historiaList;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historia);

        calendarKalendarz=findViewById(R.id.calendarView);
        recyclerviewKalendarz=(RecyclerView) findViewById(R.id.historialist);

        historiaList=new ArrayList<>();
        historiaAdapter=new HistoriaAdapter(this,historiaList);
        recyclerviewKalendarz.setHasFixedSize(true);
        recyclerviewKalendarz.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewKalendarz.setAdapter(historiaAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        calendarKalendarz.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                String nazwaMesiaca= RezerwacjaUtils.getNazwaMiesiaca(miesiac);
                historiaList.clear();
                recyclerviewKalendarz.getAdapter().notifyDataSetChanged();

                String data=dzien+" "+nazwaMesiaca+" "+rok;
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
            }
        });
    }
}
