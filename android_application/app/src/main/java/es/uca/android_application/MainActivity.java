package es.uca.android_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import es.uca.android_application.importantDates.ImportantDates;

public class MainActivity extends AppCompatActivity
{
    @Override
    //Cuando eliges una opción en el menú..
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
       //En función del elemento que selecciones en el menú..
        switch(item.getItemId())
        {
            case R.id.item1:
            {
                Intent intent = new Intent(this, event_attendees.class);
                startActivity(intent);
                return true;
            }
            case R.id.item2:
            {
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.item3:
            {
                try
                {
                    Intent intent = new Intent(this, ImportantDates.class);
                    startActivity(intent);
                } catch(Exception e)
                {
                    Toast.makeText(this, "Open important dates...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            case R.id.item4:
            {
                Toast.makeText(this, "Item 4 selected", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
