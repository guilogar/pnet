package es.uca.android_application.ImportantDates.RecyclerView;

import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import es.uca.android_application.R;
import es.uca.android_application.ImportantDates.ClickListener.RecyclerViewClickListener;

public class RowViewHolder extends RecyclerView.ViewHolder
{
    private ImageButton ib;

    RowViewHolder(View v)
    {
        super(v);
        this.ib = (ImageButton) v.findViewById(R.id.ib);
    }

    public void setViewClickListener(RecyclerViewClickListener listener)
    {
        ViewClickListener vcl = new ViewClickListener(listener, this);
        this.ib.setOnClickListener(vcl);
    }
}
