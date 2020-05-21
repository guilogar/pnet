package es.uca.android_application.Database;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpPostRequest extends AsyncTask<Void, Void, String> {

    private String url;
    private String collection;
    private Map<String, Object> data;

    public HttpPostRequest(String url, String collection,  Map<String, Object> data)
    {
        this.url = url;
        this.collection = collection;
        this.data = data;
    }

    public HttpPostRequest(String url, String collection, String id,  Map<String, Object> data)
    {
        this.url = url;
        this.collection = collection + "/" + id;
        this.data = data;
    }

    public String httpRequest(String urlString, String collection, Map<String, Object> data) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, Object> entry : data.entrySet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
        }

        String params = result.toString();
        OutputStream out = null;

        String response = "";

        try
        {
            // make the http post
            URL url = new URL(urlString + "/" + collection);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();

            // see the response...
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((line = br.readLine()) != null)
            {
                response += line;
            }
        } catch (Exception e)
        {
            return "";
        }

        return response;
    }

    protected String doInBackground(Void... params)
    {
        try {
            return this.httpRequest(this.url, this.collection, this.data);
        } catch (Exception e)
        {
            return e.toString();
        }
    }
}