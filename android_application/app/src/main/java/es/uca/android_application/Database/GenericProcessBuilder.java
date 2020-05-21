package es.uca.android_application.Database;

import java.util.Map;

public class GenericProcessBuilder
{
    public static ProcessBuilder genericProcess()
    {
        return GenericProcessBuilder.specificProcess("https://pnet.herokuapp.com/api/v1");
    }

    public static ProcessBuilder specificProcess(String url)
    {
        ProcessBuilder pb = new ProcessBuilder();

        Map<String, String> env = pb.environment();
        env.put("URL_BASE", url);

        return pb;
    }
}
