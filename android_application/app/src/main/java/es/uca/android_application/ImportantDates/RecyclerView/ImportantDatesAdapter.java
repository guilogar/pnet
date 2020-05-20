package es.uca.android_application.ImportantDates.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.uca.android_application.R;
import es.uca.android_application.ImportantDates.ClickListener.ClickListener;
import es.uca.android_application.ImportantDates.ClickListener.FirstClickListener;
import es.uca.android_application.ImportantDates.ClickListener.SecondClickListener;
import es.uca.android_application.ImportantDates.ClickListener.ThirdClickListener;
import es.uca.android_application.ImportantDates.Timetable;

public class ImportantDatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<Timetable> data = new ArrayList<>();
    private ClickListener cl;

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

        this.cl = new ClickListener(context);
        return new RowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof RowViewHolder)
        {
            RowViewHolder rowHolder = (RowViewHolder) holder;

            Timetable t = this.data.get(position);

            String dateString = (String) t.getAttribute("dateOfMake");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            ClickListener cl = new ClickListener(this.cl.getContext());
            try
            {
                Date dateTimetable = format.parse(dateString);
                Date today = new Date();

                int dayTimetable = dateTimetable.getDate();
                int dayToday = today.getDate();

                if(dayTimetable > dayToday)
                {
                    FirstClickListener f = new FirstClickListener(this.cl.getContext());
                    f.setText("Ya ha pasado el plazo de acceso a este evento." + dayTimetable + "/" + dayToday);
                    cl = f;
                } else if(dayTimetable < dayToday)
                {
                    SecondClickListener s = new SecondClickListener(this.cl.getContext());
                    s.setText("Aun no ha llegado este evento." + dayTimetable + "/" + dayToday);
                    cl = s;
                } else
                {
                    ThirdClickListener s = new ThirdClickListener(
                            this.cl.getContext(),
                            (String) t.getAttribute("title"),
                            (String) t.getAttribute("title")
                    );
                    cl = s;
                }

            } catch (Exception e)
            {
                cl.setText(e.toString());
            }

            rowHolder.setViewClickListener(cl);
        }
    }

    @Override
    public int getItemCount()
    {
        return this.data.size();
    }
}