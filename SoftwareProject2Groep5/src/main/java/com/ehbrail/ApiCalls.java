package com.ehbrail;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.boon.core.Sys;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jorda on 3/11/2016.
 */
public  class ApiCalls {



    public static String getStationsXML() throws IOException {
        String url = "https://api.irail.be/stations";
        long startTime = System.currentTimeMillis();
        String response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor stationsXML = " + result);
        return response;
    }

    //Maakt gebruik van OkHttp
    public static String getIRailRoute(String van, String naar) throws IOException {
        String url = "https://api.irail.be/connections/?to="+naar+"&from="+van+"&format=json";
        long startTime = System.currentTimeMillis();
        String response = doGetRequest(url);
        long stopTime = System.currentTimeMillis();
        long result = stopTime - startTime;
        System.out.println("Tijd nodig voor getIRailRoute = " + result);
        return response;
    }

    public static String doGetRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.isSuccessful());
        if (response.isSuccessful()){
            return response.body().string();
        }
        else return "BadResponse";
    }


}
