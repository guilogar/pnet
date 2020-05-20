package es.uca.android_application.Database;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Database
{
    private ProcessBuilder pb;
    private Map<String, String> env;

    public Database(ProcessBuilder pb)
    {
        this.pb = pb;
        this.env = pb.environment();
    }

    public JSONArray getData(String collection) throws JSONException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, String> asyncTask = new HttpGetRequest(this.env.get("URL_BASE"), collection);
        asyncTask.execute();
        String result = asyncTask.get();
        return new JSONArray(result);
    }

    public JSONArray postData(String collection, Map<String, String> data) throws JSONException, UnsupportedEncodingException
    {
        String result = ""; // this.httpPostRequest(collection, data);
        return new JSONArray(result);
    }

    // not implemented yet
    public JSONArray putData(String collection, Map<String, String> data) throws JSONException
    {
        return null;
    }

    public JSONArray destroy(String collection, String id) throws JSONException, UnsupportedEncodingException
    {
        String result = ""; //this.httpDeleteRequest(collection, id);
        return new JSONArray(result);
    }
}
