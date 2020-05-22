package es.uca.android_application.EventAttendees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import es.uca.android_application.Database.Database;
import es.uca.android_application.R;

public class change_attendee extends AppCompatActivity
{
    private Button update;
    private Database myInvokeTask = new Database();
    private JSONObject results;
    private EditText name, surnames, email, ad, dd, dni, phone, bd;
    private int responseConnCode;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_attendee);
        //Obtenemos referencias.
        update=(Button)findViewById(R.id.send_button3);
        name=(EditText)findViewById(R.id.name_form3);
        surnames=(EditText)findViewById(R.id.surnames_form3);
        email=(EditText)findViewById(R.id.email_form3);
        ad=(EditText)findViewById(R.id.ad_form3);
        dd=(EditText)findViewById(R.id.dd_form3);
        dni=(EditText)findViewById(R.id.dni_form3);
        phone=(EditText)findViewById(R.id.phone_form3);
        bd=(EditText)findViewById(R.id.date_form3);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Recibimos el mensajero.
        Intent intent2 = getIntent();
        //Abrimos el paquete.
        final Bundle extras = intent2.getExtras();
        //Colocamos la información recibida en el view.
        final String id_attendee=extras.getString("id_attendee");
        //Establecemos la información por defecto para que el usuario tenga que escribir lo menos posible en el formulario de actualización.
        try
        {
            JSONArray myArray= (JSONArray) myInvokeTask.getData("inscriptions",id_attendee);
            JSONObject obj = myArray.getJSONObject(0);
            System.out.println(myArray);
            //Nombre: fname.
            //Apellidos: lname.
            //Email: ename.
            //Fecha de llegada: adname.
            //Fecha de salida: ddname.
            //DNI: dniname.
            //Teléfono: phonename.
            //Fecha de nacimiento: bdname.
            name.setText(obj.getString("fname"));
            surnames.setText(obj.getString("lname"));
            email.setText(obj.getString("ename"));
            ad.setText(obj.getString("adname"));
            dd.setText(obj.getString("ddname"));
            dni.setText(obj.getString("dniname"));
            phone.setText(obj.getString("phonename"));
            bd.setText(obj.getString("bdname"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(notEmptyFields())
                {
                            Map<String,Object> json_map = new HashMap<String, Object>();
                            json_map.put("fname",name.getText().toString());
                            json_map.put("lname",surnames.getText().toString());
                            json_map.put("ename",email.getText().toString());
                            json_map.put("adname",ad.getText().toString());
                            json_map.put("ddname",dd.getText().toString());
                            json_map.put("dniname",dni.getText().toString());
                            json_map.put("phonename",phone.getText().toString());
                            json_map.put("bdname",bd.getText().toString());

                            Intent transition = new Intent(change_attendee.this,  event_attendees.class);
                            try
                            {
                                results =(JSONObject) myInvokeTask.putData("inscriptions",json_map,id_attendee);
                                String servermessage= results.getString("msg");
                                responseConnCode=myInvokeTask.getStatusOfLastRequest();
                                if (responseConnCode == 200)
                                {
                                    Toast.makeText(getApplicationContext(), "Asistente actualizado. Código servidor: "+responseConnCode+" y descripción: "+servermessage, Toast.LENGTH_SHORT).show();
                                    startActivity(transition);
                                }
                            }
                            catch(Exception e)
                            {
                                responseConnCode=myInvokeTask.getStatusOfLastRequest();
                                Toast.makeText(getApplicationContext(), "¡Error al actualizar! Código de error: "+responseConnCode, Toast.LENGTH_SHORT).show();
                                startActivity(transition);
                            }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡No pueden haber campos vacíos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Función: comprobación de que los campos introducidos en el formulario de actualización sean no vacíos.
    private Boolean notEmptyFields()
    {
        if(name.getText().toString().matches("") || surnames.getText().toString().matches("") ||
                email.getText().toString().matches("") ||ad.getText().toString().matches("") ||
                dd.getText().toString().matches("") ||dni.getText().toString().matches("") ||
                phone.getText().toString().matches("") || bd.getText().toString().matches(""))
        {
            return false;
        }
        else
        {
            return true;
        }
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
