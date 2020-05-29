package es.uca.android_application.Localization;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;
import org.w3c.dom.Text;

import es.uca.android_application.ImportantDates.Timetable;
import es.uca.android_application.R;

public class Localization extends AppCompatActivity implements OnMapReadyCallback
{
    private String idEvent;
    private Timetable t;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.localization);
        TextView tv = (TextView) this.findViewById(R.id.description);
        tv.setText("Calle Plocia, s/n, 11006 Cádiz");

        try
        {
            this.idEvent = getIntent().getExtras().getString("ID_EVENT");

            this.t = Timetable.getTimetable(this.idEvent);

            this.setTitle((String) t.getAttribute("title"));

            String description = (String) this.t.getAttribute("description");

            tv.setText(description);

            SupportMapFragment mapFragment = (SupportMapFragment)
                    getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } catch(Exception e)
        {
            SupportMapFragment mapFragment = (SupportMapFragment)
                    getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        try
        {
            JSONObject localization = (JSONObject) this.t.getAttribute("localization");
            String street = localization.getString("verbose");
            double x = localization.getDouble("x");
            double y = localization.getDouble("y");

            LatLng cadiz = new LatLng(x, y);

            googleMap.addMarker(new MarkerOptions().position(cadiz).title(street));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cadiz));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo( 12.0f ) );
        } catch (Exception e)
        {
            //Entra cuando no hay notificación.

            double x = 36.53;
            double y = -6.29;
            LatLng cadiz = new LatLng(x, y);
            googleMap.addMarker(new MarkerOptions().position(cadiz).title("Calle Plocia, s/n, 11006 Cádiz"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cadiz));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo( 12.0f ) );
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
