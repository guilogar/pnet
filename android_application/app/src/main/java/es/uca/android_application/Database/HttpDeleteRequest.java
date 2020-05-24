package es.uca.android_application.Database;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpDeleteRequest extends HttpRequest
{
    public HttpDeleteRequest(String url, String collection, String id)
    {
        super(url, collection + "/" + id);
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

            this.setResponseCode(urlConnection.getResponseCode());
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
            return this.httpRequest(this.getUrl(), this.getCollection());
        } catch (Exception e)
        {
            return e.toString();
        }
    }
}