package es.uca.android_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import es.uca.android_application.Database.Database;
import es.uca.android_application.EventAttendees.event_attendees;
import es.uca.android_application.ImportantDates.ImportantDates;
import es.uca.android_application.Localization.Localization;
import es.uca.android_application.Program.program;

public class MainActivity extends AppCompatActivity
{
    private static Boolean firstTimeBooting=true;
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
                Intent intent2 = new Intent(this, program.class);
                startActivity(intent2);
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
                Intent intent = new Intent(this, Localization.class);
                startActivity(intent);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (firstTimeBooting)
        {
                // To renewal data when application init....
                Database db = new Database();
            try {
                String result = db.getAllData("renewalData").toString();
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                firstTimeBooting = false;
            } catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
