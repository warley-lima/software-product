package com.Util.Tests;

import com.Util.Utils.QueryLatitudeLongitude;

import org.json.simple.JSONObject;

public class Test {

    public static void main(String[] args) {
      JSONObject location = QueryLatitudeLongitude.getLatLangByAdress("Avenida Barão de Mauá - 191, Centro, São Bernardo do Campo - SP");
       if(null != location){
           System.out.println( "Lat = "+location.get("lat"));
           System.out.println( "Lng = "+location.get("lng"));
           System.out.println(location);
       }

    }

}
