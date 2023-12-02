package com.gs.h2oapp.Action;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Repository.AdressRepository;

public class MainActivity extends AppCompatActivity implements LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private DrawerLayout mDrawerLayout;
    final String TAG = "GPS";
    private long UPDATE_INTERVAL = 200 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    GoogleApiClient gac;
    LocationRequest locationRequest;
    private String adress = null;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        gac = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (!isGooglePlayServicesAvailable() || !isLocationEnabled()) {
            AdressRepository adressRepository = new AdressRepository(getApplicationContext());
            if(adressRepository.getTotalAdress() > 0) {
                spinner.setVisibility(View.GONE);
                Intent myIntent = new Intent(this, AdressListActivity.class);
                startActivity(myIntent);
            }
            else{
                spinner.setVisibility(View.GONE);
                showAlert();
            }
        }
    }

    @Override
    protected void onStart() {
        spinner.setVisibility(View.VISIBLE);
        gac.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        gac.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Intent myIntent = new Intent(this, CompaniesActivity.class);
            myIntent.putExtra("fromGoogle", "S" );
            myIntent.putExtra("fromGoogleAutoLocale", "S" );
            myIntent.putExtra("adress", adress );
            myIntent.putExtra("latitude", Double.toString(location.getLatitude()));
            myIntent.putExtra("longitude", Double.toString(location.getLongitude()));
            spinner.setVisibility(View.GONE);
            startActivity(myIntent);
        }
        else{
            spinner.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Não foi possível pegar sua localização", Toast.LENGTH_LONG).show();
            Intent itentSearchAdress = new Intent(getApplicationContext(),AutoCompleteActivity.class);
            startActivity(itentSearchAdress);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                locationRequest = new LocationRequest();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(60000);
                locationRequest.setFastestInterval(60000);
                 ActivityCompat.requestPermissions(MainActivity.this,
                         new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        else{
            locationRequest = new LocationRequest();
            locationRequest.setInterval(60000);
            locationRequest.setFastestInterval(60000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(gac, locationRequest, this);
            }
        }


       /*locationRequest = new LocationRequest();
        locationRequest.setInterval(60000);
        locationRequest.setFastestInterval(60000);
        //locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(gac, locationRequest, this);
        }*/
    }

    @Override
    public void onRequestPermissionsResult(
        int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(MainActivity.this, "Permissão concedida!", Toast.LENGTH_LONG).show();
                    try {
                        LocationServices.FusedLocationApi.requestLocationUpdates(gac, locationRequest, this);
                        //LocationServices.FusedLocationApi.requestLocationUpdates(gac, locationRequest, this);
                    } catch (SecurityException e) {
                        Toast.makeText(MainActivity.this, "SecurityException:\n" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                } else {
                   // Toast.makeText(MainActivity.this, "Permissão negada!", Toast.LENGTH_LONG).show();
                    //MOSTRAR ALERTA IGUAL AO DO SHOWALERT, MAS SEM A OPÇÃO PARA HABILITAR;
                    showAlertLocationNegate();
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(MainActivity.this, "FalhaNaConexão: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
        Log.d("DDD", connectionResult.toString());
    }

    private void updateUI(Location loc) {
        Log.d(TAG, "updateUI");
        Intent myIntent = new Intent(this, CompaniesActivity.class);
        myIntent.putExtra("fromGoogle", "S" );
        myIntent.putExtra("adress", adress );
        myIntent.putExtra("latitude", Double.toString(loc.getLatitude()));
        myIntent.putExtra("longitude", Double.toString(loc.getLongitude()));
        startActivity(myIntent);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isGooglePlayServicesAvailable() {
        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
               // Log.d(TAG, "Este dispositivo não é suportado.");
                finish();
            }
            return false;
        }
       // Log.d(TAG, "Este dispositivo não é suportado 222.");
        return true;
    }

    private void showAlert() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_localizacao, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Button btnCadAdress = promptView.findViewById(R.id.btn_save_end);
        if(null != btnCadAdress){
            btnCadAdress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(getApplicationContext(),AdressActivity.class);
                    startActivity(myIntent);
                }
            });
        }

        Button btnHabilitar = promptView.findViewById(R.id.btn_habilitar);
        if(null != btnHabilitar){
            btnHabilitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                }
            });
        }
        builder.setView(promptView);
        builder.show();
    }

    private void showAlertLocationNegate() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View promptView = layoutInflater.inflate(R.layout.dialog_localizacao_negada, null);
        final AlertDialog dlgShow;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Button btnCadAdress = promptView.findViewById(R.id.btn_save_end);
        Button btnHabilitar = promptView.findViewById(R.id.btn_info_end);
        builder.setView(promptView);
        dlgShow = builder.show();
        if(null != btnCadAdress){
            btnCadAdress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(getApplicationContext(),AdressActivity.class);
                    startActivity(myIntent);
                }
            });
        }

        if(null != btnHabilitar){
            btnHabilitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        dlgShow.dismiss();
                        Intent itentSearchAdress = new Intent(getApplicationContext(),AutoCompleteActivity.class);
                        startActivity(itentSearchAdress);
                    } catch (Exception ex) {
                        String message = "Falha: " + ex.getMessage();
                        Toast.makeText(null, message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}



