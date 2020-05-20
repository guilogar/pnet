package es.uca.android_application.importantDates.ClickListener;

import android.content.Context;
import android.view.View;

public interface RecyclerViewClickListener
{
    void onClick(View view, int position);
    Context getContext();
}