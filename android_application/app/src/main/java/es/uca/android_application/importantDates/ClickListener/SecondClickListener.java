package es.uca.android_application.importantDates.ClickListener;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SecondClickListener extends ClickListener implements RecyclerViewClickListener
{
    public SecondClickListener(Context context)
    {
        super(context);
    }

    public void onClick(View view)
    {
        Snackbar.make(view, this.getText(), Snackbar.LENGTH_SHORT).show();
    }
}