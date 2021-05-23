package com.example.reservationtable.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reservationtable.Activity.RezerwacjaInfoActivity;
import com.example.reservationtable.Activity.WyszukiwanierezerwacjiActivity;
import com.example.reservationtable.R;
import com.example.reservationtable.Rezerwacja;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RezerwacjaAdapter extends RecyclerView.Adapter<RezerwacjaAdapter.ViewHolder>{

    public List<Rezerwacja> rezerwacjaList;
    public WyszukiwanierezerwacjiActivity wyszukiwanierezerwacjiActivity;

    public RezerwacjaAdapter(WyszukiwanierezerwacjiActivity wyszukiwanierezerwacjiActivity,List<Rezerwacja> rezerwacjaList){

        this.rezerwacjaList=rezerwacjaList;
        this.wyszukiwanierezerwacjiActivity=wyszukiwanierezerwacjiActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imieText.setText(rezerwacjaList.get(position).getImie());
        holder.nazwiskotext.setText(rezerwacjaList.get(position).getNazwisko());
        holder.stolikText.setText(rezerwacjaList.get(position).getStolik());
        holder.godzinaText.setText(rezerwacjaList.get(position).getGodzina());
    }

    @Override
    public int getItemCount() {
        return rezerwacjaList.size();
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
        public void onClick(View v){
            Rezerwacja rezerwacja=rezerwacjaList.get(getAdapterPosition());
            Intent intent=new Intent(wyszukiwanierezerwacjiActivity, RezerwacjaInfoActivity.class);
            intent.putExtra("rezerwacja",rezerwacja);
            wyszukiwanierezerwacjiActivity.startActivity(intent);
        }
    }
}
