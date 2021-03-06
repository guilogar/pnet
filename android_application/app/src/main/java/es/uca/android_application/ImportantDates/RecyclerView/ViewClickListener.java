package es.uca.android_application.ImportantDates.RecyclerView;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import es.uca.android_application.ImportantDates.ClickListener.RecyclerViewClickListener;

public class ViewClickListener implements View.OnClickListener
{
    private RecyclerViewClickListener listener;
    private RecyclerView.ViewHolder vh;

    public ViewClickListener(RecyclerViewClickListener listener, RecyclerView.ViewHolder vh)
    {
        this.listener = listener;
        this.vh = vh;
    }

    @Override
    public void onClick(View v)
    {
        this.listener.onClick(v);
    }
}