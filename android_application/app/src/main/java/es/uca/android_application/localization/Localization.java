package es.uca.android_application.localization;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.uca.android_application.R;
import es.uca.android_application.importantDates.RecyclerView.ImportantDatesAdapter;
import es.uca.android_application.importantDates.Timetable;

public class Localization extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.localization);

        // to make visible the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this, "localizacion!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
            {
                try
                {
                    this.finish();
                } catch (Exception e)
                {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
