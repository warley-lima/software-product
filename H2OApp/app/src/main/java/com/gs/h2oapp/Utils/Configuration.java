package com.gs.h2oapp.Utils;

/**
 * Created by Warley Lima 
 */
public class Configuration {


   public static String urlCompanies = "http://192.168.15.4:8080/api/companypub";
    public static String BASE_URL = "http://192.168.15.4:8080/api/";
    public static String BASE_URL_GOOGLE_API = "https://maps.googleapis.com/maps/api/";

    public static String getUrlCompanies() {
        return urlCompanies;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBaseUrlGoogleApi() {
        return BASE_URL_GOOGLE_API;
    }
}
