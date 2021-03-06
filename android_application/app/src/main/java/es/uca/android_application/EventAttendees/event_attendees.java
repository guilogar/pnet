package es.uca.android_application.EventAttendees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class event_attendees extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button myAddButton;
    //private GetAllJson myInvokeTask;
    private Database myInvokeTask;
    private JSONArray myArray;
    private ArrayList<form> forms=new ArrayList<form>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendees);
        myAddButton=(Button)findViewById(R.id.add_button);
        mRecyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myInvokeTask= new Database();
        try
        {
             myArray = (JSONArray)myInvokeTask.getAllData("inscriptions");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //Recorrer JSON e INSERTAr en array. JSON ARRAY.
        for(int i=0; i<myArray.length(); ++i)
        {
            try {
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
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        mAdapter=new formAdapter(forms);
        mRecyclerView.setAdapter(mAdapter);
        myAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), form_view.class);
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