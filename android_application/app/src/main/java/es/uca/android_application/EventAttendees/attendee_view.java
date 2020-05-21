package es.uca.android_application.EventAttendees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
    private Button change,back,delete;
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
        back= (Button)findViewById(R.id.back_form2);
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
        //Recibimos el mensajero.
        Intent intent = getIntent();
        //Abrimos el paquete.
        final Bundle extras = intent.getExtras();
        //Colocamos la información recibida en el view.
        final String id_attendee=extras.getString("id_attendee");
        /////////////
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
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(attendee_view.this, event_attendees.class);
                startActivity(intent);
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
    //CLASE QUE MODELA EL SERVICIO GET ONE, (por id del elemento).
    private class GetAttendee extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String text= null;
            HttpURLConnection urlConnection= null;
            try
            {
                URL urlToRequest= new URL("https://pnet.herokuapp.com/api/v1/inscriptions/"+params[0]);
                urlConnection= (HttpURLConnection) urlToRequest.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                text= new Scanner(in).useDelimiter("\\A").next();
            }
            catch (Exception e)
            {
                return e.toString();
            }
            finally
            {
                if(urlConnection!= null)
                    urlConnection.disconnect();
            }
            return text;
        }
        @Override
        protected void onPostExecute(String results)
        {
            //Ha recibido algo.
            if(results!= null)
            {
                try
                {
                        JSONArray myArray= new JSONArray(results);
                        JSONObject obj= myArray.getJSONObject(0);
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
            }
        }
    }
    //CLASE QUE MODELA EL SERVICIO DELETE POR ID, PARA ELIMINAR EL ASISTENTE MOSTRADO.
    private class deleteAttende extends AsyncTask<String, String, String>
    {
        private int responseConnCode;
        private InputStream stream;
        private  String servermessage = null;
        @Override
        protected String doInBackground(String... params)
        {
            String text= null;
            HttpURLConnection urlConnection= null;
            try
            {
                URL  url= new URL("https://pnet.herokuapp.com/api/v1/inscriptions/" + params[0]);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestProperty("Content-Type", "application/json" );
                httpCon.setRequestMethod("DELETE");
                httpCon.connect();
                responseConnCode =  httpCon.getResponseCode();

                //En función del código de error..
                if(responseConnCode == 200)
                {
                    stream = httpCon.getInputStream();
                    try (Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()))
                    {
                        servermessage = scanner.useDelimiter("\\A").next();
                    }
                }
                else
                {
                    stream = httpCon.getErrorStream();
                    try (Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()))
                    {
                        servermessage = scanner.useDelimiter("\\A").next();
                    }
                }
                //Extraemos del JSON el texto de error.
                JSONObject json = new JSONObject(servermessage);
                servermessage = json.getString("msg");
            }
            catch (Exception e)
            {
                System.out.println("Excepción "+ e.toString());
                return e.toString();
            }
            finally
            {
                if(urlConnection!= null)
                    urlConnection.disconnect();
            }
            return text;
        }
        @Override
        protected void onPostExecute(String results)
        {
                Intent transition = new Intent(attendee_view.this,  event_attendees.class);
                try {
                    if (responseConnCode == 200)
                    {
                        Toast.makeText(getApplicationContext(), "Asistente eliminado. Código servidor: "+responseConnCode+" con descripción: "+servermessage, Toast.LENGTH_SHORT).show();
                        startActivity(transition);
                    }
                    else
                        {
                          Toast.makeText(getApplicationContext(), "¡Ups, ha habido un error ! Código de error: "+responseConnCode+" con descripción: "+servermessage, Toast.LENGTH_SHORT).show();
                           startActivity(transition);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        }
    }
}