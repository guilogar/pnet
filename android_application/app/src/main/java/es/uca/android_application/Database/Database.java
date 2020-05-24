package es.uca.android_application.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Database
{
    private ProcessBuilder pb;
    private Map<String, String> env;
    private int status = 0;

    public Database(ProcessBuilder pb)
    {
        this.setProcessBuilder(pb);
    }

    public Database()
    {
        this.setProcessBuilder(GenericProcessBuilder.genericProcess());
    }

    private void setProcessBuilder(ProcessBuilder pb)
    {
        this.pb = pb;
        this.env = this.pb.environment();
    }

    private Object returnResult(String result) throws JSONException
    {
        try
        {
            return new JSONArray(result);
        } catch(Exception e)
        {
            return new JSONObject(result);
        }
    }

    public int getStatusOfLastRequest()
    {
        return this.status;
    }

    public Object getAllData(String collection) throws JSONException, ExecutionException, InterruptedException
    {
        HttpGetRequest asyncTask = new HttpGetRequest(this.env.get("URL_BASE"), collection);
        asyncTask.execute();
        String result = asyncTask.get();

        this.status = asyncTask.getResponseCode();

        return this.returnResult(result);
    }

    public Object getData(String collection, String id) throws JSONException, ExecutionException, InterruptedException
    {
        HttpGetRequest asyncTask = new HttpGetRequest(this.env.get("URL_BASE"), collection, id);
        asyncTask.execute();
        String result = asyncTask.get();

        this.status = asyncTask.getResponseCode();

        return this.returnResult(result);
    }

    public Object postData(String collection, Map<String, Object> data) throws JSONException, ExecutionException, InterruptedException
    {
        HttpPostRequest asyncTask = new HttpPostRequest(
                this.env.get("URL_BASE"), collection, data
        );
        asyncTask.execute();
        String result = asyncTask.get();

        this.status = asyncTask.getResponseCode();

        return this.returnResult(result);
    }

    public Object putData(String collection, Map<String, Object> data, String id) throws JSONException, ExecutionException, InterruptedException
    {
        HttpPutRequest asyncTask = new HttpPutRequest(
                this.env.get("URL_BASE"), collection, id, data
        );
        asyncTask.execute();
        String result = asyncTask.get();

        this.status = asyncTask.getResponseCode();

        return this.returnResult(result);
    }

    public Object deleteData(String collection, String id) throws JSONException, ExecutionException, InterruptedException
    {
        HttpDeleteRequest asyncTask = new HttpDeleteRequest(
                this.env.get("URL_BASE"), collection, id
        );
        asyncTask.execute();
        String result = asyncTask.get();

        this.status = asyncTask.getResponseCode();

        return this.returnResult(result);
    }
}
