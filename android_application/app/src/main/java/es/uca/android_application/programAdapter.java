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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class programAdapter extends RecyclerView.Adapter<programAdapter.MyViewHolder>
{
    private ArrayList<programItem> programs;
    private Context context;
    // Copiamos la lista de asistentes al array a nuestro atributo local
    public programAdapter(ArrayList<programItem> myDataset)
    {
        programs = myDataset;
    }
    @Override
    public programAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v =    LayoutInflater.from(parent.getContext()).inflate(R.layout.program_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        context = parent.getContext();
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        //Establecemos los valores de los TextView.
        holder.program_title.setText(String.valueOf(programs.get(position).getTitle()));
        holder.program_hour1.setText(String.valueOf(programs.get(position).getActivity(0).first));
        holder.program_hour2.setText(String.valueOf(programs.get(position).getActivity(1).first));
        holder.program_hour3.setText(String.valueOf(programs.get(position).getActivity(2).first));
        holder.program_hour4.setText(String.valueOf(programs.get(position).getActivity(3).first));
        holder.program_hour5.setText(String.valueOf(programs.get(position).getActivity(4).first));
        holder.program_hour6.setText(String.valueOf(programs.get(position).getActivity(5).first));
        holder.program_activity1.setText(String.valueOf(programs.get(position).getActivity(0).second));
        holder.program_activity2.setText(String.valueOf(programs.get(position).getActivity(1).second));
        holder.program_activity3.setText(String.valueOf(programs.get(position).getActivity(2).second));
        holder.program_activity4.setText(String.valueOf(programs.get(position).getActivity(3).second));
        holder.program_activity5.setText(String.valueOf(programs.get(position).getActivity(4).second));
        holder.program_activity6.setText(String.valueOf(programs.get(position).getActivity(5).second));
    }
    @Override
    public int getItemCount()
    {
        return programs.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView program_title;
        TextView program_hour1,program_hour2,program_hour3,program_hour4,program_hour5,program_hour6;
        TextView program_activity1, program_activity2, program_activity3, program_activity4, program_activity5, program_activity6;

        public MyViewHolder(View v)
        {
            super(v);
            //Referencias.
            program_title=(TextView)v.findViewById(R.id.program_title);
            program_hour1=(TextView)v.findViewById(R.id.program_hour1);
            program_hour2=(TextView)v.findViewById(R.id.program_hour2);
            program_hour3=(TextView)v.findViewById(R.id.program_hour3);
            program_hour4=(TextView)v.findViewById(R.id.program_hour4);
            program_hour5=(TextView)v.findViewById(R.id.program_hour5);
            program_hour6=(TextView)v.findViewById(R.id.program_hour6);
            program_activity1=(TextView)v.findViewById(R.id.program_activity1);
            program_activity2=(TextView)v.findViewById(R.id.program_activity2);
            program_activity3=(TextView)v.findViewById(R.id.program_activity3);
            program_activity4=(TextView)v.findViewById(R.id.program_activity4);
            program_activity5=(TextView)v.findViewById(R.id.program_activity5);
            program_activity6=(TextView)v.findViewById(R.id.program_activity6);

        }
    }
}