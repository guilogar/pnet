package es.uca.android_application.importantDates;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import es.uca.android_application.Database.Database;

public class Timetable
{
    private Map<String, Object> attributes;

    public Timetable(Map<String, Object> attributes)
    {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes()
    {
        return this.attributes;
    }

    public Object getAttribute(String key)
    {
        return this.attributes.get(key);
    }

    public static ArrayList<Timetable> getAllTimetable() throws JSONException, ExecutionException, InterruptedException
    {
        ProcessBuilder pb = new ProcessBuilder();

        Map<String, String> env = pb.environment();
        env.put("URL_BASE", "https://pnet.herokuapp.com/api/v1");
        Database db = new Database(pb);

        ArrayList<Timetable> timetables = new ArrayList<>();

        JSONArray time = db.getData("timetable");
        
        for(int i = 0; i < time.length(); i++)
        {
            JSONObject t = time.getJSONObject(i);
            t.keys();
            Iterator<String> iterator = t.keys();

            HashMap<String, Object> attributes = new HashMap<>();

            while(iterator.hasNext())
            {
                String key = iterator.next();
                attributes.put(key, t.get(key));
            }

            Timetable timetable = new Timetable(attributes);

            timetables.add(timetable);
        }

        return timetables;
    }
}
