package es.uca.android_application.ImportantDates.ClickListener;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ClickListener implements RecyclerViewClickListener
{
    private Context context;
    private String text = "";

    public ClickListener(Context context)
    {
        this.context = context;
    }

    public Context getContext()
    {
        return this.context;
    }

    public void onClick(View v)
    {
        Toast.makeText(this.context, this.text, Toast.LENGTH_SHORT).show();
    }

    public void setText(String text)
    {
        this.text = text;
    }
    public String getText() { return this.text; }
}