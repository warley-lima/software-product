package com.gs.h2oapp.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.gs.h2oapp.Entity.Company;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Warley Lima 
 */
public class Teste {

    public static void main(String[] args) {
        Utils.getCedulasTroco(30);
      
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            else {
                Address location = address.get(0);
                System.out.println("------------------*****************-----------------\n" +
                        "LAT: "+location.getLatitude()+" LONG:"+ location.getLongitude());
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

}
