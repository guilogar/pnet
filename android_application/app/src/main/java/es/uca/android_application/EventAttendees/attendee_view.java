package es.uca.android_application.EventAttendees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import es.uca.android_application.Database.Database;
import es.uca.android_application.R;

public class attendee_view extends AppCompatActivity
{
    private Button change,delete;
    private TextView name,surnames,email,ad,dd,dni,phone,bd,idate;
    private int responseRequestCode;
    private JSONObject results;
    //private deleteAttende request;
    //private GetAttendee request2;
    private Database myInvokeTask = new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_view);
        //Cogemos las referencias del layout.
        delete= (Button)findViewById(R.id.delete_form2);
        change=(Button)findViewById(R.id.put_form2);
        name=(TextView)findViewById(R.id.name_text2_1);
        surnames=(TextView)findViewById(R.id.surnames_text2_1);
        email=(TextView)findViewById(R.id.email_text2_1);
        ad=(TextView)findViewById(R.id.ad_text2_1);
        dd=(TextView)findViewById(R.id.dd_text2_1);
        dni=(TextView)findViewById(R.id.dni_text2_1);
        phone=(TextView)findViewById(R.id.phone_text2_1);
        bd=(TextView)findViewById(R.id.bd_text2_1);
        idate=(TextView)findViewById(R.id.id_text2_1);
        //Hacemos visible un botón que Android siempre crea por defecto en todas las actividades (layouts): el botón de volver.
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Recibimos el mensajero.
        Intent intent = getIntent();
        //Abrimos el paquete.
        final Bundle extras = intent.getExtras();
        //Colocamos la información recibida en el view.
        final String id_attendee=extras.getString("id_attendee");
        try
        {
            JSONArray myArray= (JSONArray) myInvokeTask.getData("inscriptions",id_attendee);
            JSONObject obj = myArray.getJSONObject(0);
            //Nombre: fname.
            //Apellidos: lname.
            //Email: ename.
            //Fecha de llegada: adname.
            //Fecha de salida: ddname.
            //DNI: dniname.
            //Teléfono: phonename.
            //Fecha de nacimiento: bdname.
            //Fecha de inscripción: idname.
            name.setText(obj.getString("fname"));
            surnames.setText(obj.getString("lname"));
            email.setText(obj.getString("ename"));
            ad.setText(obj.getString("adname"));
            dd.setText(obj.getString("ddname"));
            dni.setText(obj.getString("dniname"));
            phone.setText(obj.getString("phonename"));
            bd.setText(obj.getString("bdname"));
            idate.setText(obj.getString("idname"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Listeners. (Al hacer click en un botón..).
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent transition = new Intent(attendee_view.this,  event_attendees.class);
                try
                {
                    results =(JSONObject)myInvokeTask.deleteData("inscriptions",id_attendee);
                     String servermessage= results.getString("msg");
                    responseRequestCode= myInvokeTask.getStatusOfLastRequest();
                    if (responseRequestCode == 200)
                    {
                        Toast.makeText(getApplicationContext(), "Asistente eliminado. Código servidor: "+responseRequestCode+" con descripción: "+servermessage, Toast.LENGTH_SHORT).show();
                        startActivity(transition);
                    }
                }
                catch(Exception e)
                {
                    responseRequestCode= myInvokeTask.getStatusOfLastRequest();
                    Toast.makeText(getApplicationContext(), "¡Ups, ha habido un error! Código de error "+responseRequestCode+": asistente inexistente.", Toast.LENGTH_SHORT).show();
                    startActivity(transition);
                    e.printStackTrace();
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), change_attendee.class);
                //La colocamos en el paquete.
                Bundle bundle = new Bundle();
                //Rellenamos el paquete, el tipo de dato puesto o clave si es un String, y el dato en cuestión.
                //Añadimos la información que queremos pasar.
                bundle.putString("id_attendee",id_attendee);
                //Añadimos el paquete al mensajero.
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    //Controlamos lo que hace el botón de volver.
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
            {
                try
                {
                    //Terminamos la activity.
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