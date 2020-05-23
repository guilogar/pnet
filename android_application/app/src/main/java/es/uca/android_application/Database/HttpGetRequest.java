package es.uca.android_application.Database;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpGetRequest extends HttpRequest
{
    public HttpGetRequest(String url, String collection)
    {
        super(url, collection);
    }

    public HttpGetRequest(String url, String collection, String id)
    {
        super(url, collection + "/" + id);
    }

    public String httpRequest(String url, String collection)
    {
        String text = "";
        HttpURLConnection urlConnection = null;

        try
        {
            URL urlToRequest = new URL(url + '/' + collection);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            this.setResponseCode(urlConnection.getResponseCode());
            text = new Scanner(in).useDelimiter("\\A").next();
        }
        catch (Exception e)
        {
            return "";
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
            return this.httpRequest(this.getUrl(), this.getCollection());
        } catch (Exception e)
        {
            return e.toString();
        }
    }
}