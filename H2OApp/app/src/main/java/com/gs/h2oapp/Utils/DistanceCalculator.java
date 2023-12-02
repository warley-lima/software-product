package com.gs.h2oapp.Utils;

/**
 * Created by Warley Lima
 */

public class DistanceCalculator {
    public static void main (String[] args) throws java.lang.Exception
    {
         System.out.println(distance(-23.7018144, -46.5692485, -23.7058125, -46.5479492, "K") + " Kilometers\n");
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

     private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
