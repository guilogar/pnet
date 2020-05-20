package es.uca.android_application.importantDates.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.uca.android_application.R;
import es.uca.android_application.importantDates.ClickListener.FirstClickListener;
import es.uca.android_application.importantDates.ClickListener.RecyclerViewClickListener;
import es.uca.android_application.importantDates.Timetable;

public class ImportantDatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<Timetable> data = new ArrayList<>();
    private FirstClickListener f;

    public ImportantDatesAdapter(ArrayList<Timetable> data)
    {
        this.data = data;
    }

    public void updateData(ArrayList<Timetable> data)
    {
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();

        View v = LayoutInflater.from(context).inflate(R.layout.image_button, parent, false);

        this.f = new FirstClickListener(context);
        return new RowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof RowViewHolder)
        {
            RowViewHolder rowHolder = (RowViewHolder) holder;
            this.f.setString("" + position);
            rowHolder.setViewClickListener(this.f);
        }
    }

    @Override
    public int getItemCount()
    {
        return this.data.size();
    }
}