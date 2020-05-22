package es.uca.android_application.EventAttendees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

import es.uca.android_application.Database.Database;
import es.uca.android_application.R;

public class form_view extends AppCompatActivity
{
    private EditText name, surnames, email,ad,dd,dni,phone_number,birthday;
   private Button send;
   private int responseConnCode;
   private JSONObject results;
   private Database myInvokeTask= new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view);
        //Almaceno referencias de donde voy a sacar la información.
        send= (Button)findViewById(R.id.send_button);
        name = (EditText) findViewById(R.id.name_form);
        surnames = (EditText) findViewById(R.id.surnames_form);
        email = (EditText) findViewById(R.id.email_form);
        ad = (EditText) findViewById(R.id.ad_form);
        dd = (EditText) findViewById(R.id.dd_form);
        dni = (EditText) findViewById(R.id.dni_form);
        phone_number = (EditText) findViewById(R.id.phone_form);
        birthday = (EditText) findViewById(R.id.date_form);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Validación de los campos.
                if(notEmptyFormFields())
                {
                    //Fecha actual.
                    Date now= new Date();
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                    cal.setTime(now);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    month+=1;
                    String inscDate= String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year);

                    Map<String, Object> json_map= new HashMap<String,Object>();

                    //Le paso los parámetros necesarios.
                           json_map.put("fname",name.getText().toString());
                           json_map.put("lname",surnames.getText().toString());
                           json_map.put("ename",email.getText().toString());
                           json_map.put("adname",ad.getText().toString());
                           json_map.put("ddname",dd.getText().toString());
                           json_map.put("dniname",dni.getText().toString());
                           json_map.put("phonename",phone_number.getText().toString());
                           json_map.put("bdname",birthday.getText().toString());
                           json_map.put("idname",inscDate);
                           Intent transition = new Intent(form_view.this, event_attendees.class);
                           try
                           {

                               results= (JSONObject) myInvokeTask.postData("inscriptions",json_map);
                               responseConnCode=myInvokeTask.getStatusOfLastRequest();
                               String servermessage = results.getString("msg") ;
                               if(responseConnCode == 201)
                               {
                                   Toast.makeText(getApplicationContext(), "¡Registro correctamente! Código servidor: "+responseConnCode+" y descripción: "+servermessage, Toast.LENGTH_SHORT).show();
                                   startActivity(transition);
                               }
                           }
                           catch (Exception e)
                           {
                               responseConnCode=myInvokeTask.getStatusOfLastRequest();
                               Toast.makeText(getApplicationContext(), "¡Ha habido algún error, inténtalo de nuevo! Código de error: "+responseConnCode, Toast.LENGTH_SHORT).show();
                           }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"¡No se permiten campos vacíos en el formulario!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Función auxiliar: comprueba que los campos del formulario (nuevo asistente), no son vacíos.
    boolean notEmptyFormFields()
    {
        if(name.getText().toString().matches("") ||
                surnames.getText().toString().matches("")||
                email.getText().toString().matches("")||
                ad.getText().toString().matches("")||
                dd.getText().toString().matches("")||
                dni.getText().toString().matches("")||
                phone_number.getText().toString().matches("") ||
                birthday.getText().toString().matches("")
        )
        {
            return false;
        }
        else
            {
                return true;
            }
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
                //Se lo pasa a la clase padre el objeto por si ésta sabe qué hacer con él.
                return super.onOptionsItemSelected(item);
            }
        }
    }

}