package com.example.reservationtable;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriaUserAdapter extends RecyclerView.Adapter<HistoriaUserAdapter.ViewHolder> {

    public List<Rezerwacja> historiaUserList;
    public HistoriaUserActivity historiaUserActivity;

    public HistoriaUserAdapter(HistoriaUserActivity historiaUserActivity,List<Rezerwacja> historiaUserList){
        this.historiaUserActivity=historiaUserActivity;
        this.historiaUserList=historiaUserList;
    }

    @NonNull
    @Override
    public HistoriaUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaUserAdapter.ViewHolder holder, int position) {
        holder.imieText.setText(historiaUserList.get(position).getImie());
        holder.nazwiskotext.setText(historiaUserList.get(position).getNazwisko());
        holder.stolikText.setText(historiaUserList.get(position).getStolik());
        holder.godzinaText.setText(historiaUserList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return historiaUserList.size();
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
            Rezerwacja rezerwacja=historiaUserList.get(getAdapterPosition());
            Intent intent=new Intent(historiaUserActivity, RezerwacjaInfo.class);
            intent.putExtra("rezerwacja",rezerwacja);
            historiaUserActivity.startActivity(intent);
        }
    }
}
