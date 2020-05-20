package es.uca.android_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class event_attendees extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button myAddButton,back;
    private GetAllJson myInvokeTask;
    private ArrayList<form> forms=new ArrayList<form>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendees);
        back= (Button)findViewById(R.id.back_button);
        myAddButton=(Button)findViewById(R.id.add_button);
        mRecyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //GET/ALL.
        myInvokeTask = new GetAllJson();
        myInvokeTask.execute();
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(event_attendees.this, MainActivity.class);
                startActivity(intent);
            }
        });
        myAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), form_view.class);
                startActivity(intent);
            }
        });
    }
    //CLASE QUE SE ENCARGA DEL SERVICIO GET ALL(todos los asistentes del sistema).
    private class GetAllJson extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            String text= null;
            HttpURLConnection urlConnection= null;
            try
            {   //La URL puede ser cualquiera de las dos siguientes:
                //https://pnet.herokuapp.com/api/v1/inscriptions  -> BASE DE DATOS.
                //http://10.0.2.2:3000/api/v1/inscriptions -> EMULADOR (IP y PUERTO: SERVIDOR EJECUTÁNDOSE EN LA MÁQUINA CLIENTE).
                URL urlToRequest= new URL("https://pnet.herokuapp.com/api/v1/inscriptions");
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
                    //Array.
                    JSONArray myArray= new JSONArray(results);
                    //Recorrer JSON e INSERTAr en array. JSON ARRAY.
                   for(int i=0; i<myArray.length(); ++i)
                   {
                       JSONObject obj = myArray.getJSONObject(i);
                       //Id de la inscripción: _id.
                       //Nombre: fname.
                       //Apellidos: lname.
                       //Email: ename.
                       //Fecha de llegada: adname.
                       //Fecha de salida: ddname.
                       //DNI: dniname.
                       //Teléfono: phonename.
                       //Fecha de nacimiento: bdname.
                       //Fecha de inscripción: idname.
                       forms.add(new form(
                               obj.getString("_id"),
                               obj.getString("fname"),
                               obj.getString("lname"),
                               obj.getString("ename"),
                               obj.getString("adname"),
                               obj.getString("ddname"),
                               obj.getString("dniname"),
                               obj.getString("phonename"),
                               obj.getString("bdname"),
                               obj.getString("idname")
                               )
                       );
                   }
                   // Lo ponemos aquí para que nos aseeguremos que una vez recogida la información, la cumplimentamos en el adaptador. PROBLEMAS DE PARALELISMO.
                    mAdapter=new formAdapter(forms);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (Exception e) {
                    e.printStackTrace();}
            }
        }
    }
}