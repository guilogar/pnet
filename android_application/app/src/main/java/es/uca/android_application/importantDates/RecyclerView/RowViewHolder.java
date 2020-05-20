package es.uca.android_application.importantDates.RecyclerView;

import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import es.uca.android_application.R;
import es.uca.android_application.importantDates.ClickListener.RecyclerViewClickListener;

public class RowViewHolder extends RecyclerView.ViewHolder
{
    private ImageButton ib;

    RowViewHolder(View v, RecyclerViewClickListener listener)
    {
        super(v);

        ViewClickListener vcl = new ViewClickListener(listener, this);

        this.ib = (ImageButton) v.findViewById(R.id.ib);

        this.ib.setOnClickListener(vcl);
    }
}
