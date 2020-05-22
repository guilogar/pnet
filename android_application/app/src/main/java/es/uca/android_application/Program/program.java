package es.uca.android_application.Program;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import es.uca.android_application.Database.Database;
import es.uca.android_application.MainActivity;
import es.uca.android_application.R;

public class program extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Database myInvokeTask=new Database();
    private ArrayList<programItem> programs = new ArrayList<programItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        //Referencias.
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view_program);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try
        {
            //Array de programas.
            JSONArray myArray = (JSONArray)myInvokeTask.getAllData("timetable");
            //Recorrer JSON e INSERTAr en array. JSON ARRAY.
            for(int i=0; i<myArray.length(); ++i)
            {
                //Un programa.
                JSONObject obj = myArray.getJSONObject(i);

                //Descripción del programa que estamos tratando: title.
                //Hora de una actividad perteneciente a un programa: hour.
                //Actividad perteneciente a una hora de un programa: activity.

                //Un programa tiene varias actividades, cada una con una hora.
                JSONArray activities= new JSONArray(obj.getString("table"));
                String hour1 = activities.getJSONObject(0).getString("hour");
                String hour2 = activities.getJSONObject(1).getString("hour");
                String hour3 = activities.getJSONObject(2).getString("hour");
                String hour4 = activities.getJSONObject(3).getString("hour");
                String hour5 = activities.getJSONObject(4).getString("hour");
                String hour6 = activities.getJSONObject(5).getString("hour");
                String activity1 = activities.getJSONObject(0).getString("activity");
                String activity2 = activities.getJSONObject(1).getString("activity");
                String activity3 = activities.getJSONObject(2).getString("activity");
                String activity4 = activities.getJSONObject(3).getString("activity");
                String activity5 = activities.getJSONObject(4).getString("activity");
                String activity6 = activities.getJSONObject(5).getString("activity");
                programs.add(new programItem(
                        obj.getString("title"),
                        new Pair<String,String>(hour1,activity1),
                        new Pair<String,String>(hour2,activity2),
                        new Pair<String,String>(hour3,activity3),
                        new Pair<String,String>(hour4,activity4),
                        new Pair<String,String>(hour5,activity5),
                        new Pair<String,String>(hour6,activity6)
                ));
            }
            // Lo ponemos aquí para que nos aseeguremos que una vez recogida la información, la cumplimentamos en el adaptador. PROBLEMAS DE PARALELISMO.
            mAdapter=new programAdapter(programs);
            mRecyclerView.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();}
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
