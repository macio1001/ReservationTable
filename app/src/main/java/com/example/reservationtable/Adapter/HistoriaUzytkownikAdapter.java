package com.example.reservationtable.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reservationtable.Activity.HistoriaUzytkownikActivity;
import com.example.reservationtable.Activity.RezerwacjaInfoActivity;
import com.example.reservationtable.R;
import com.example.reservationtable.Rezerwacja;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriaUzytkownikAdapter extends RecyclerView.Adapter<HistoriaUzytkownikAdapter.ViewHolder> {

    public List<Rezerwacja> historiaUzytkownikList;
    public HistoriaUzytkownikActivity historiaUzytkownikActivity;

    public HistoriaUzytkownikAdapter(HistoriaUzytkownikActivity historiaUzytkownikActivity,List<Rezerwacja> historiaUserList){
        this.historiaUzytkownikActivity=historiaUzytkownikActivity;
        this.historiaUzytkownikList=historiaUserList;
    }

    @NonNull
    @Override
    public HistoriaUzytkownikAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaUzytkownikAdapter.ViewHolder holder, int position) {
        holder.imieText.setText(historiaUzytkownikList.get(position).getImie());
        holder.nazwiskotext.setText(historiaUzytkownikList.get(position).getNazwisko());
        holder.stolikText.setText(historiaUzytkownikList.get(position).getStolik());
        holder.godzinaText.setText(historiaUzytkownikList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return historiaUzytkownikList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View widok;

        public TextView imieText;
        public TextView nazwiskotext;
        public TextView stolikText;
        public TextView godzinaText;

        public ViewHolder(View itemView){
            super(itemView);
            widok=itemView;

            imieText=(TextView) widok.findViewById(R.id.ImieTextView);
            nazwiskotext=(TextView) widok.findViewById(R.id.NazwiskoTextView);
            stolikText=(TextView) widok.findViewById(R.id.StolikTextVIew);
            godzinaText=(TextView) widok.findViewById(R.id.GodzinaTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Rezerwacja rezerwacja=historiaUzytkownikList.get(getAdapterPosition());
            Intent intent=new Intent(historiaUzytkownikActivity, RezerwacjaInfoActivity.class);
            intent.putExtra("rezerwacja",rezerwacja);
            historiaUzytkownikActivity.startActivity(intent);
        }
    }
}
