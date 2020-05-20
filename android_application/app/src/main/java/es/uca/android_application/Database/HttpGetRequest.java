package es.uca.android_application.Database;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpGetRequest extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private String url;
    private String collection;

    public HttpGetRequest(String url, String collection)
    {
        this.url = url;
        this.collection = collection;
    }

    public String httpRequest(String url, String collection)
    {
        String text = null;
        HttpURLConnection urlConnection = null;

        try
        {
            System.out.println("TAREA ASINCRONA");
            System.out.println(url + '/' + collection);
            URL urlToRequest = new URL(url + '/' + collection);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            text = new Scanner(in).useDelimiter("\\A").next();
        }
        catch (Exception e)
        {
            return e.toString();
        }
        finally
        {
            if(urlConnection != null) urlConnection.disconnect();
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
/*
    public String httpPostRequest(String collection, Map<String, String> data) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, String> entry : data.entrySet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        String params = result.toString();
        OutputStream out = null;

        try
        {
            URL url = new URL(this.env.get("URL_BASE") + "/" + collection);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e)
        {
            return e.toString();
        }

        return out.toString();
    }

    public String httpDeleteRequest(String collection, String id) throws UnsupportedEncodingException
    {
        OutputStream out = null;
        try
        {
            URL url = new URL(this.env.get("URL_BASE") + "/" + collection + "/" + id);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write("");
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e)
        {
            return e.toString();
        }

        return out.toString();
    }*/