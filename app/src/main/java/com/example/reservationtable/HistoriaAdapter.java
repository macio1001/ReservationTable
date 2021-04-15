package com.example.reservationtable;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoriaAdapter extends RecyclerView.Adapter<HistoriaAdapter.ViewHolder> {

    public List<Rezerwacja> historiaList;
    public HistoriaActivity historiaActivity;

    public HistoriaAdapter(HistoriaActivity historiaActivity,List<Rezerwacja> historiaList){
        this.historiaList=historiaList;
        this.historiaActivity=historiaActivity;
    }

    @NonNull
    @Override
    public HistoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaAdapter.ViewHolder holder, int position) {
        holder.imieText.setText(historiaList.get(position).getImie());
        holder.nazwiskotext.setText(historiaList.get(position).getNazwisko());
        holder.stolikText.setText(historiaList.get(position).getStolik());
        holder.godzinaText.setText(historiaList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return historiaList.size();
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
            Rezerwacja rezerwacja=historiaList.get(getAdapterPosition());
            Intent intent=new Intent(historiaActivity,RezerwacjaInfo.class);
            intent.putExtra("rezerwacja",rezerwacja);
            historiaActivity.startActivity(intent);
        }
    }
}
