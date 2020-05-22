package es.uca.android_application.Database;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpDeleteRequest extends AsyncTask<Void, Void, String> {

    private String url;
    private String collection;
    private int responseCode = 0;

    public HttpDeleteRequest(String url, String collection, String id)
    {
        this.url = url;
        this.collection = collection + "/" + id;
    }

    public int getResponseCode()
    {
        return this.responseCode;
    }

    public String httpRequest(String urlString, String collection)
    {
        String text = "";
        HttpURLConnection urlConnection = null;
        try
        {
            URL urlToRequest = new URL(urlString + '/' + collection);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setRequestMethod("DELETE");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            this.responseCode = urlConnection.getResponseCode();
            text = new Scanner(in).useDelimiter("\\A").next();
        } catch (Exception e)
        {
            return "";
        }

        return text;
    }

    protected String doInBackground(Void... params)
    {
        try {
            return this.httpRequest(this.url, this.collection);
        } catch (Exception e)
        {
            return e.toString();
        }
    }
}