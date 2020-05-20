package es.uca.android_application.ImportantDates.ClickListener;

import android.content.Context;
import android.view.View;

public interface RecyclerViewClickListener
{
    void onClick(View v);
    Context getContext();
}