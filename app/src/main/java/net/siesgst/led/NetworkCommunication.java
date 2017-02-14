package net.siesgst.led;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rohitramaswamy on 08/09/16.
 */
public class NetworkCommunication
{

    public void sendData(String url,String data)
    {
        String keyValuePair = "data="+data;
        //Log.d("LOG",keyValuePair);
        String output = "http://"+url+"?"+keyValuePair;
        Log.v("Log",output);
        HttpURLConnection urlconnection = null;
                try
                {
                    Log.v("log","d");
                    URL link = new URL(output);
                    urlconnection = (HttpURLConnection)link.openConnection();
                    urlconnection.setRequestMethod("POST");
                    urlconnection.connect();
                    //OutputStreamWriter writer = new OutputStreamWriter(urlconnection.getOutputStream());
                    //writer.write(keyValuePair);
                    //writer.flush();
                    //writer.close();
                    Log.d("LOGbruh",convertStreamToString(urlconnection.getInputStream()));
                }
                catch(MalformedURLException e)
                {
                    Log.v("log","e");
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    Log.v("log","f");
                    e.printStackTrace();
                }
                finally
                {
                    Log.v("log","h");
                    if(urlconnection != null)
                    {
                        Log.v("log","g");
                        urlconnection.disconnect();
                    }
                }

    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try
        {
            while((line = reader.readLine())!=null)

            {
                sb.append(line).append("\n");
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(is != null)
                    is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
