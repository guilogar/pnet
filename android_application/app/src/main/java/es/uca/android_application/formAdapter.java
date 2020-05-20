package es.uca.android_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class formAdapter extends RecyclerView.Adapter<formAdapter.MyViewHolder>
{
    private ArrayList<form> forms;
    private Context context;
    // Copiamos la lista de asistentes al array a nuestro atributo local
    public formAdapter(ArrayList<form> myDataset)
    {
        forms = myDataset;
    }
    @Override
    public formAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v =    LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        context = parent.getContext();
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
                holder.number.setText(String.valueOf(forms.get(position).getInscrDate()));
                holder.name.setText(forms.get(position).getName());
                holder.show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int duration = Toast.LENGTH_LONG;
                            Intent intent = new Intent(context.getApplicationContext(), attendee_view.class);
                            //La colocamos en el paquete.
                            Bundle bundle = new Bundle();
                            //Rellenamos el paquete, el tipo de dato puesto o clave si es un String, y el dato en cuestión.
                            //Añadimos la información que queremos pasar.
                            bundle.putString("id_attendee",forms.get(position).getId());
                            //Añadimos el paquete al mensajero.
                            intent.putExtras(bundle);
                            //Cambiamos de actividad. El mensajero se va.
                            context.startActivity(intent);
                        }
                });
    }
    @Override
    public int getItemCount()
    {
        return forms.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView number;
        TextView name;
        Button show;
        public MyViewHolder(View v)
        {
            super(v);
            number = (TextView) v.findViewById(R.id.number);
            name = (TextView) v.findViewById(R.id.name_item_text);
            show = (Button) v.findViewById(R.id.show);
        }
    }
}