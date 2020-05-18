package es.uca.android_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class change_attendee extends AppCompatActivity
{
    private Button back, update;
    private updateAttendee request;
    private GetAttendee request2;
    private EditText name, surnames, email, ad, dd, dni, phone, bd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_attendee);
        //Obtenemos referencias.
        back=(Button)findViewById(R.id.back_button3);
        update=(Button)findViewById(R.id.send_button3);
        name=(EditText)findViewById(R.id.name_form3);
        surnames=(EditText)findViewById(R.id.surnames_form3);
        email=(EditText)findViewById(R.id.email_form3);
        ad=(EditText)findViewById(R.id.ad_form3);
        dd=(EditText)findViewById(R.id.dd_form3);
        dni=(EditText)findViewById(R.id.dni_form3);
        phone=(EditText)findViewById(R.id.phone_form3);
        bd=(EditText)findViewById(R.id.date_form3);
        //Recibimos el mensajero.
        Intent intent2 = getIntent();
        //Abrimos el paquete.
        final Bundle extras = intent2.getExtras();
        //Colocamos la información recibida en el view.
        final String id_attendee=extras.getString("id_attendee");
        //Establecemos la información por defecto para que el usuario tenga que escribir lo menos posible en el formulario de actualización.
        request2=new GetAttendee();
        request2.execute(id_attendee);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(change_attendee.this, event_attendees.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(notEmptyFields())
                {
                    request = new updateAttendee();
                    request.execute(
                            id_attendee,
                            name.getText().toString(),
                            surnames.getText().toString(),
                            email.getText().toString(),
                            ad.getText().toString(),
                            dd.getText().toString(),
                            dni.getText().toString(),
                            phone.getText().toString(),
                            bd.getText().toString()
                    );
                }else
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

    //CLASE QUE MODELA EL SERVICIO GET POR ID PARA RELLENAR CAMPOS DEL FORMULARIO CON DATOS ANTIGUOS.
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
            }
        }
    }
    //CLASE QUE MODELA EL SERVICIO DELETE POR ID, PARA ELIMINAR EL ASISTENTE MOSTRADO.
    private class updateAttendee extends AsyncTask<String, String, String>
    {
        String text= null;
        HttpURLConnection urlConnection= null;
        private int responseConnCode;
        private InputStream stream;
        private  String servermessage = null;
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                URL urlToRequest= new URL("https://pnet.herokuapp.com/api/v1/inscriptions/"+params[0]);
                urlConnection= (HttpURLConnection) urlToRequest.openConnection();
                urlConnection.setRequestMethod("PUT");
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                //Preparamos la información a enviar en formato JSON.
                JSONObject obj = new JSONObject();
                obj.put("fname",params[1]);
                obj.put("lname",params[2]);
                obj.put("ename",params[3]);
                obj.put("adname",params[4]);
                obj.put("ddname",params[5]);
                obj.put("dniname",params[6]);
                obj.put("phonename", params[7]);
                obj.put("bdname",params[8]);
                //Envío de datos por el flujo de abstracción: conexión.
                OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
                osw.write(obj.toString());
                osw.flush();
                responseConnCode =  urlConnection.getResponseCode();
                //En función del código de error.
                if(responseConnCode == 200) {

                    stream = urlConnection.getInputStream();

                    try (Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name()))
                    {
                        servermessage = scanner.useDelimiter("\\A").next();
                    }
                }
                else
                {
                    stream = urlConnection.getErrorStream();

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
            Intent transition = new Intent(change_attendee.this,  event_attendees.class);
            try
            {
                if (responseConnCode == 200)
                {
                    Toast.makeText(getApplicationContext(), "Asistente actualizado. Código servidor: "+responseConnCode+" y descripción: "+servermessage, Toast.LENGTH_SHORT).show();
                    startActivity(transition);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡Error al actualizar! Código de error: "+responseConnCode+" con descripción: "+servermessage, Toast.LENGTH_SHORT).show();
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
