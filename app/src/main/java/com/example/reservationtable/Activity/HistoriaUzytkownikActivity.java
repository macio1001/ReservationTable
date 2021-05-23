package com.example.reservationtable.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.example.reservationtable.Adapter.HistoriaUzytkownikAdapter;
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

public class HistoriaUzytkownikActivity extends AppCompatActivity {

    private static final String TAG="HistoriaUzytkActivity";
    CalendarView calendarKalendarzUzytkownik;
    RecyclerView recyclerviewKalendarzUzytkownik;

    HistoriaUzytkownikAdapter historiaUzytkownikAdapter;
    List<Rezerwacja> historiaUzytkownikList;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historiauser);

        calendarKalendarzUzytkownik=findViewById(R.id.calendarView2);
        recyclerviewKalendarzUzytkownik=(RecyclerView) findViewById(R.id.historiaUserList);

        historiaUzytkownikList=new ArrayList<>();
        historiaUzytkownikAdapter=new HistoriaUzytkownikAdapter(this,historiaUzytkownikList);
        recyclerviewKalendarzUzytkownik.setHasFixedSize(true);
        recyclerviewKalendarzUzytkownik.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewKalendarzUzytkownik.setAdapter(historiaUzytkownikAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        Intent intent=getIntent();
        String email=intent.getStringExtra("Email");


        calendarKalendarzUzytkownik.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int rok, int miesiac, int dzien) {
                String nazwaMesiaca= RezerwacjaUtils.getNazwaMiesiaca(miesiac);
                historiaUzytkownikList.clear();
                recyclerviewKalendarzUzytkownik.getAdapter().notifyDataSetChanged();

                String data=dzien+" "+nazwaMesiaca+" "+rok;
                for(int i=0;i<=6;i++) {
                    firebaseFirestore.collection("Stoliknr1").whereEqualTo("Data", data).whereEqualTo("Email", email).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "Error:" + e.getMessage());
                            } else {
                                for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                        Rezerwacja rezerwacja = documentChange.getDocument().toObject(Rezerwacja.class);
                                        historiaUzytkownikList.add(rezerwacja);

                                        historiaUzytkownikAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
