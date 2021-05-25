package com.example.reservationtable.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reservationtable.R;
import com.example.reservationtable.Rezerwacja;
import com.example.reservationtable.Adapter.RezerwacjaAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WyszukiwanierezerwacjiActivity extends AppCompatActivity {

    private static final String TAG="WyszukiwanieActivity";
    EditText editKod;
    Button buttonSzukaj;
    RecyclerView recyclerviewRezerwacja;

    List<Rezerwacja> rezerwacjaList;
    RezerwacjaAdapter rezerwacjaAdapter;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wyszukiwanierezerwacji);

        editKod=findViewById(R.id.kodeditText);
        buttonSzukaj=findViewById(R.id.szukajbutton);

        recyclerviewRezerwacja=(RecyclerView) findViewById(R.id.rezerwujlist);
        rezerwacjaList=new ArrayList<>();
        rezerwacjaAdapter=new RezerwacjaAdapter(this,rezerwacjaList);
        recyclerviewRezerwacja.setHasFixedSize(true);
        recyclerviewRezerwacja.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewRezerwacja.setAdapter(rezerwacjaAdapter);

        firebaseFirestore=FirebaseFirestore.getInstance();

        buttonSzukaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wyszukanie=editKod.getText().toString();

                for(int i=0;i<=6;i++) {
                    firebaseFirestore.collection("Stoliknr"+i).whereEqualTo("Nazwisko", wyszukanie).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "Error:" + e.getMessage());
                            } else {
                                for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                                        Rezerwacja rezerwacja = documentChange.getDocument().toObject(Rezerwacja.class);
                                        rezerwacjaList.add(rezerwacja);

                                        rezerwacjaAdapter.notifyDataSetChanged();
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
