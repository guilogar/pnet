package es.uca.android_application.importantDates.ClickListener;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class FirstClickListener implements RecyclerViewClickListener
{
    private Context context;

    public FirstClickListener(Context context)
    {
        this.context = context;
    }

    public Context getContext()
    {
        return this.context;
    }

    public void onClick(View view, int position)
    {
        Toast.makeText(this.context, "toasito", Toast.LENGTH_SHORT).show();
    }
}