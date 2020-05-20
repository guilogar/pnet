package es.uca.android_application.ImportantDates.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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

    public void setViewClickListener(RecyclerViewClickListener listener, String image)
    {
        ViewClickListener vcl = new ViewClickListener(listener, this);
        this.ib.setOnClickListener(vcl);

        byte [] encodeByte = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        this.ib.setImageBitmap(bitmap);
    }
}
