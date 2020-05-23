package es.uca.android_application.Database;

import android.os.AsyncTask;

public class HttpRequest extends AsyncTask<Void, Void, String> {

    private String url;
    private String collection;
    private int responseCode = 0;

    public HttpRequest(String url, String collection)
    {
        this.url = url;
        this.collection = collection;
    }

    public int getResponseCode()
    {
        return this.responseCode;
    }
    public void setResponseCode(int code) { this.responseCode = code; }
    public String getUrl() { return this.url; }
    public String getCollection() { return this.collection; }

    protected String doInBackground(Void... params)
    {
        return null;
    }
}