package es.uca.android_application.importantDates.ClickListener;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ClickListener implements RecyclerViewClickListener
{
    private Context context;
    private String text = "toatsito";

    public ClickListener(Context context)
    {
        this.context = context;
    }

    public Context getContext()
    {
        return this.context;
    }

    public void onClick(View view, int position)
    {
        Toast.makeText(this.context, this.text, Toast.LENGTH_SHORT).show();
    }

    public void setString(String text)
    {
        this.text = text;
    }
    public String getText() { return this.text; }
}