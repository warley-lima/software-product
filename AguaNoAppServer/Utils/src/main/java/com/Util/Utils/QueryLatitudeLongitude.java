package com.Util.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class QueryLatitudeLongitude {

    public static JSONObject getLatLangByAdress(String adress){
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=".concat(adress.replaceAll(" ", "+")).concat(",+CA&key=AIzaSyADPNbF4VGzrvfGiz_5sCBcXbqY2IGyz1U");

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        JSONObject location = null;
        HttpResponse response;
        try {
            response = client.execute(request);
            if(200 ==  response.getStatusLine().getStatusCode()){
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(content);
                JSONObject jb = (JSONObject) obj;
                System.out.println(content);
                //now read
                JSONArray jsonObject1 = (JSONArray) jb.get("results");
                JSONObject jsonObject2 = (JSONObject)jsonObject1.get(0);
                JSONObject jsonObject3 = (JSONObject)jsonObject2.get("geometry");
                location = (JSONObject) jsonObject3.get("location");

            } else {
                location = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return location;


    }

}
