package es.uca.android_application.EventAttendees;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.Scanner;
import java.util.TimeZone;

import es.uca.android_application.R;

public class form_view extends AppCompatActivity
{
    private EditText name, surnames, email,ad,dd,dni,phone_number,birthday;
   private Button send,back;
   private postTask myInvokeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view);
        //Almaceno referencias de donde voy a sacar la información.
        send= (Button)findViewById(R.id.send_button);
        back= (Button)findViewById(R.id.back_button);
        name = (EditText) findViewById(R.id.name_form);
        surnames = (EditText) findViewById(R.id.surnames_form);
        email = (EditText) findViewById(R.id.email_form);
        ad = (EditText) findViewById(R.id.ad_form);
        dd = (EditText) findViewById(R.id.dd_form);
        dni = (EditText) findViewById(R.id.dni_form);
        phone_number = (EditText) findViewById(R.id.phone_form);
        birthday = (EditText) findViewById(R.id.date_form);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(form_view.this, event_attendees.class);
                startActivity(intent);
            }
        });
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
                    //Llamada al servicio.
                    myInvokeTask = new postTask();
                    //Le paso los parámetros necesarios.
                    myInvokeTask.execute(
                            name.getText().toString(),
                            surnames.getText().toString(),
                            email.getText().toString(),
                            ad.getText().toString(),
                            dd.getText().toString(),
                            dni.getText().toString(),
                            phone_number.getText().toString(),
                            birthday.getText().toString(),
                            inscDate
                            );
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
    //CLASE QUE IMPLEMENTA EL SERVICIO POST.
    private class postTask extends AsyncTask<String, String, String>
    {
        private TextView myName;
        private  int responseConnCode;
        private InputStream stream;
        private  String servermessage = null;
        @Override
        protected String doInBackground(String... params)
        {
            String text= null;
            HttpURLConnection urlConnection= null;
            try
            {
                URL urlToRequest= new URL("https://pnet.herokuapp.com/api/v1/inscriptions");
                urlConnection= (HttpURLConnection) urlToRequest.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                //Preparamos la información a enviar en formato JSON.
                JSONObject obj = new JSONObject();
                obj.put("fname",params[0]);
                obj.put("lname",params[1]);
                obj.put("ename",params[2]);
                obj.put("adname",params[3]);
                obj.put("ddname",params[4]);
                obj.put("dniname",params[5]);
                obj.put("phonename", params[6]);
                obj.put("bdname",params[7]);
                obj.put("idname",params[8]);
                //Envío de datos por el flujo de abstracción: conexión.
                OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
                osw.write(obj.toString());
                osw.flush();

                responseConnCode = urlConnection.getResponseCode();
                //En función del código de error..
                if(responseConnCode == 201)
                {
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
            Toast msg;
            //Vuelvo a la anterior actividad: CLASE ACTUAL --> CLASE POSTERIOR.
            Intent transition = new Intent(form_view.this, event_attendees.class);
            if(responseConnCode == 201)
            {
                msg = Toast.makeText(getApplicationContext(), "¡Registro correctamente! Código servidor: "+responseConnCode+" y descripción: "+servermessage, Toast.LENGTH_SHORT);
                startActivity(transition);
            }
            else
                {
                    msg = Toast.makeText(getApplicationContext(), "¡Ha habido algún error, inténtalo de nuevo! Código de error: "+responseConnCode+" y descripción: "+servermessage, Toast.LENGTH_SHORT);
                }
            msg.show();
        }
    }
}