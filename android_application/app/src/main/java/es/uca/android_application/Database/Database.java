package es.uca.android_application.Database;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public JSONArray getAllData(String collection) throws JSONException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, String> asyncTask = new HttpGetRequest(this.env.get("URL_BASE"), collection);
        asyncTask.execute();
        String result = asyncTask.get();
        return new JSONArray(result);
    }

    public JSONArray getData(String collection, String id) throws JSONException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, String> asyncTask = new HttpGetRequest(this.env.get("URL_BASE"), collection, id);
        asyncTask.execute();
        String result = asyncTask.get();
        return new JSONArray(result);
    }

    public JSONObject postData(String collection, Map<String, Object> data) throws JSONException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, String> asyncTask = new HttpPostRequest(
                this.env.get("URL_BASE"), collection, data
        );
        asyncTask.execute();
        String result = asyncTask.get();
        return new JSONObject(result);
    }

    public String putData(String collection, Map<String, Object> data, String id) throws JSONException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, String> asyncTask = new HttpPutRequest(
                this.env.get("URL_BASE"), collection, id, data
        );
        asyncTask.execute();
        String result = asyncTask.get();
        return result;
    }

    public JSONObject deleteData(String collection, String id) throws JSONException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, String> asyncTask = new HttpDeleteRequest(
                this.env.get("URL_BASE"), collection, id
        );
        asyncTask.execute();
        String result = asyncTask.get();
        return new JSONObject(result);
    }
}
