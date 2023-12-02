package com.gs.h2oapp.Action;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gs.h2oapp.Adapter.BrandAdapter;
import com.gs.h2oapp.Adapter.CompanyAdapter;
import com.gs.h2oapp.Adapter.LitersAdapter;
import com.gs.h2oapp.Adapter.OptionsOrderByAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Brand;
import com.gs.h2oapp.Entity.Company;
import com.gs.h2oapp.Entity.FiltersDTO;
import com.gs.h2oapp.Entity.Liters;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Configuration;
import com.gs.h2oapp.Utils.InitializerApi;
import com.gs.h2oapp.Utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompaniesActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private static ArrayList<Company> lstCompanies;
    private RecyclerView recyclerView;
    private CompanyAdapter adapter;
    private String latitude = null;
    private String longitude = null;
    private String myAdress = null;
    private String fromGoogle = null;
    //private String fromGoogleAutoLocale = null;
    private CarrinhoBusiness cartBusiness;
    private ProgressBar spinner;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private TextView txtRua, txtBairroCity;
    private List<Brand> lstBrands;
    private List<Brand> lstBrandsFilter = new ArrayList<>();
    private List<FiltersDTO> lstFiltersDTO;
    private BrandAdapter marcasB;
    private ListView recViewOptions;
    private View viewM;
    private View viewL;
    private ListView recViewBrands;
    private ListView recViewLiters;
    private AlertDialog.Builder builderBrandsFilter;
    private AlertDialog.Builder builderLitersFilter;
    private AlertDialog brandDialog;
    private AlertDialog litersDialog;
    private List<Liters> lstLiters;
    private List<Liters> lstLitersFilter = new ArrayList<>();
    private LitersAdapter litersB;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Distribuídoras");
        setupHeaderNavigation(toolbar);
        fromGoogle = getIntent().getStringExtra("fromGoogle");
        // fromGoogleAutoLocale = getIntent().getStringExtra("fromGoogleAutoLocale");
       /* myAdress = getIntent().getStringExtra("adress");
        latitude = getIntent().getStringExtra("latitude");
        longitude = getIntent().getStringExtra("longitude");*/
        lstCompanies = new ArrayList<>();
        if (null == cartBusiness) {
            cartBusiness = new CarrinhoBusinessImp();
        }
        initViews();
        getAdress();

        LinearLayout divChangeEntrega = findViewById(R.id.div_change_end);
        divChangeEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //changeEntegra(1);
               /* Intent itentSearchAdress = new Intent(getApplicationContext(),AutoCompleteActivity.class);
                startActivity(itentSearchAdress);*/
                openAutocompleteActivity();
            }
        });
    }

    private void setupHeaderNavigation(Toolbar toolbar) {
        // Create Navigation drawer and inlfate layout
        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_companies);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            /*supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);*/
        }


        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        boolean ret = false;
                        int id = menuItem.getItemId();
                        if (id == R.id.action_home) {
                            // loadHomeFragment();
                            ret = true;
                        } else if (id == R.id.action_meus_locais) {
                            Intent myIntent = new Intent(getApplicationContext(), AdressListActivity.class);
                            startActivity(myIntent);
                            ret = true;
                        } else if(id == R.id.action_meu_perfil) {
                            Intent myIntent = new Intent(getBaseContext(), UserListActivity.class);
                            startActivity(myIntent);
                            ret = true;
                        }else if (id == R.id.action_meus_pedidos) {
                            Intent myIntent = new Intent(getBaseContext(), OrderListActivity.class);
                            startActivity(myIntent);
                            ret = true;
                        }
                        return ret;
                    }
                });
    }

    private void setupBottomNavigation() {
       /* mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_cart) {
                   // loadHomeFragment();
                    return true;
                } else if (id == R.id.action_edit_location) {
                   // loadProfileFragment();
                    return true;
                } else if (id == R.id.action_edit_location) {
                    //loadSettingsFragment();
                    return true;
                }
                return false;
            }
        });*/
    }

   /* private void changeEntegra(int id) {
        Intent myIntent = new Intent(this, ProductsActivity.class);
        myIntent.putExtra("idCompany", lstCompanies.get(1).getId());
        startActivity(myIntent);
    }*/

    private void initViews() {
       /* recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/
        txtRua = findViewById(R.id.txtRua);
        txtBairroCity = findViewById(R.id.txtBairroCity);
        recyclerView = findViewById(R.id.my_recycler_view);
        // recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);
       // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        // recyclerView.setLayoutManager(layoutManager);

        // getActivity().setTitle("Distribuidoras");
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.INVISIBLE);
        //spinner.setVisibility(View.GONE);
    }

    private void getCompaniesServer(String lat, String longi) {
        spinner.setVisibility(View.VISIBLE);
        lstCompanies = new ArrayList<>();
        Call<ArrayList<Company>> callCompanies = new InitializerApi(Configuration.getBaseUrl()).getCompanyService().loadCompaniesTeste(lat, longi, "SP");
        callCompanies.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> callCompanies, Response<ArrayList<Company>> companies) {
                // Log.i("CONSULTA EMPRESAS", "RETORNO: " + companies.body());
                lstCompanies = companies.body();
                if (lstCompanies != null && !lstCompanies.isEmpty()) {
                    adapter = new CompanyAdapter(lstCompanies);
                } else {
                    Toast.makeText(CompaniesActivity.this, "Nenhuma Distribuídora localizada!", Toast.LENGTH_LONG).show();
                    lstCompanies = new ArrayList<>();
                    adapter = new CompanyAdapter(lstCompanies);
                }
                recyclerView.setAdapter(adapter);
                /*if (lstCompanies.size() > 0) {
                    for (Company p : lstCompanies) {
                        System.out.println(p.getId() +" " + p.getNm() +" R$" +p.getTx());
                    }
                }*/
                spinner.setVisibility(View.INVISIBLE);
                //spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Company>> callCompanies, Throwable t) {
                //Log.e("CONSULTAEMPRESAS", t.getCause().getMessage());
                Toast.makeText(getApplicationContext(), "Falha ao consultar as empresas!", Toast.LENGTH_LONG).show();
                spinner.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }

    private void getCompaniesByBrandsServer(String lat, String longi, String ids) {
        spinner.setVisibility(View.VISIBLE);
        lstCompanies = new ArrayList<>();
        Call<ArrayList<Company>> callCompanies = new InitializerApi(Configuration.getBaseUrl()).getCompanyService().getCompaniesByBrands(lat, longi, "SP", ids);
        callCompanies.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> callCompanies, Response<ArrayList<Company>> companies) {
                lstCompanies = companies.body();
                if (null != lstCompanies && !lstCompanies.isEmpty()) {
                    adapter = new CompanyAdapter(lstCompanies);
                    //Pegar aqui o campo selecionado para ordenação  list_op_companies
                    if (null != recViewOptions.getAdapter()) {
                        SparseBooleanArray checkedOrder = recViewOptions.getCheckedItemPositions();
                        for (int i = 0; i < recViewOptions.getAdapter().getCount(); i++) {
                            if (checkedOrder.get(i)) {
                                lstCompanies = Utils.updateToZeroAvNull(lstCompanies);
                                companiesOrderBy(i);
                                i =  recViewOptions.getAdapter().getCount();
                            }
                        }
                    }
                } else {
                    Toast.makeText(CompaniesActivity.this, "Nenhuma Distribuídora localizada!", Toast.LENGTH_LONG).show();
                    lstCompanies = new ArrayList<>();
                    adapter = new CompanyAdapter(lstCompanies);
                }
                recyclerView.setAdapter(adapter);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Company>> callCompanies, Throwable t) {
                //Log.e("CONSULTAEMPRESAS", t.getCause().getMessage());
                Toast.makeText(getApplicationContext(), "Falha ao consultar as empresas!", Toast.LENGTH_LONG).show();
                spinner.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }

    private void getCompaniesByLitersServer(String lat, String longi, String ids) {
        spinner.setVisibility(View.VISIBLE);
        lstCompanies = new ArrayList<>();
        Call<ArrayList<Company>> callCompanies = new InitializerApi(Configuration.getBaseUrl()).getCompanyService().getCompaniesByLiters(lat, longi, "SP", ids);
        callCompanies.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> callCompanies, Response<ArrayList<Company>> companies) {
                lstCompanies = companies.body();
                if (null != lstCompanies && !lstCompanies.isEmpty()) {
                    adapter = new CompanyAdapter(lstCompanies);
                    //Pegar aqui o campo selecionado para ordenação  list_op_companies
                    if (null != recViewOptions.getAdapter()) {
                        SparseBooleanArray checkedOrder = recViewOptions.getCheckedItemPositions();
                        for (int i = 0; i < recViewOptions.getAdapter().getCount(); i++) {
                            if (checkedOrder.get(i)) {
                                lstCompanies = Utils.updateToZeroAvNull(lstCompanies);
                                companiesOrderBy(i);
                                i =  recViewOptions.getAdapter().getCount();
                            }
                        }
                    }
                } else {
                    Toast.makeText(CompaniesActivity.this, "Nenhuma Distribuídora localizada!", Toast.LENGTH_LONG).show();
                    lstCompanies = new ArrayList<>();
                    adapter = new CompanyAdapter(lstCompanies);
                }
                recyclerView.setAdapter(adapter);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Company>> callCompanies, Throwable t) {
                //Log.e("CONSULTAEMPRESAS", t.getCause().getMessage());
                Toast.makeText(getApplicationContext(), "Falha ao consultar as empresas!", Toast.LENGTH_LONG).show();
                spinner.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }

    private void getCompaniesByBrandsLitersServer(String lat, String longi, String idsb, String idsl) {
        spinner.setVisibility(View.VISIBLE);
        lstCompanies = new ArrayList<>();
        Call<ArrayList<Company>> callCompanies = new InitializerApi(Configuration.getBaseUrl()).getCompanyService().getCompaniesByBrandsLiters(lat, longi, "SP", idsb, idsl);
        callCompanies.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> callCompanies, Response<ArrayList<Company>> companies) {
                lstCompanies = companies.body();
                if (null != lstCompanies && !lstCompanies.isEmpty()) {
                    adapter = new CompanyAdapter(lstCompanies);
                    //Pegar aqui o campo selecionado para ordenação  list_op_companies
                    if (null != recViewOptions.getAdapter()) {
                        SparseBooleanArray checkedOrder = recViewOptions.getCheckedItemPositions();
                        for (int i = 0; i < recViewOptions.getAdapter().getCount(); i++) {
                            if (checkedOrder.get(i)) {
                                lstCompanies = Utils.updateToZeroAvNull(lstCompanies);
                                companiesOrderBy(i);
                                i =  recViewOptions.getAdapter().getCount();
                            }
                        }
                    }
                } else {
                    Toast.makeText(CompaniesActivity.this, "Nenhuma Distribuídora localizada!", Toast.LENGTH_LONG).show();
                    lstCompanies = new ArrayList<>();
                    adapter = new CompanyAdapter(lstCompanies);
                }
                recyclerView.setAdapter(adapter);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Company>> callCompanies, Throwable t) {
                //Log.e("CONSULTAEMPRESAS", t.getCause().getMessage());
                Toast.makeText(getApplicationContext(), "Falha ao consultar as empresas!", Toast.LENGTH_LONG).show();
                spinner.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }

    private void getAdress() {
        if ("S".equals(fromGoogle)) {
            spinner.setVisibility(View.VISIBLE);
            // String hope = "-23.6927564,-46.5603657";
            myAdress = getIntent().getStringExtra("adress");
            latitude = getIntent().getStringExtra("latitude");
            longitude = getIntent().getStringExtra("longitude");
            String hope = latitude;
            hope = hope.concat("," + longitude);
            //FROM GOOGLE LOCATION BY GPS
            if (myAdress == null && (latitude != null && latitude != "0" && longitude != null && longitude != "0")) {
                // Call<JsonObject> call = new InitializerApi(Configuration.getBaseUrlGoogleApi()).getGoogleApiService().getAdressByLatLng(hope, "ROOFTOP", "street_address", "AIzaSyADPNbF4VGzrvfGiz_5sCBcXbqY2IGyz1U");
                Call<JsonObject> call = new InitializerApi(Configuration.getBaseUrlGoogleApi()).getGoogleApiService().getAdressByLatLng(hope, "street_address", "AIzaSyADPNbF4VGzrvfGiz_5sCBcXbqY2IGyz1U");
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (null != response.body()) {
                            JsonObject jsonObject = response.body();
                            if (jsonObject.size() > 0) {
                               // System.out.println(jsonObject);
                                JsonElement statusConsulta = jsonObject.get("status");
                                if ("OK".equals(statusConsulta.getAsString())) {
                                    JsonElement imgurl = jsonObject.get("results");
                                    JsonElement addressComponents = imgurl.getAsJsonArray().iterator().next().getAsJsonObject().get("address_components");
                                    String number = null;
                                    String adress = null;
                                    String bairro = null;
                                    String cidade = null;
                                    String uf = null;
                                    String key = null;
                                    for (JsonElement p : addressComponents.getAsJsonArray()) {
                                        key = p.getAsJsonObject().get("types").getAsJsonArray().get(0).getAsString();
                                        if (key.equals("street_number")) {
                                            number = p.getAsJsonObject().get("long_name").getAsString();
                                        }
                                        if (key.equals("route")) {
                                            adress = p.getAsJsonObject().get("long_name").getAsString();
                                        }
                                        if (key.equals("political")) {
                                            bairro = p.getAsJsonObject().get("long_name").getAsString();
                                        }
                                        if (key.equals("locality") || key.equals("administrative_area_level_2")) {
                                            cidade = p.getAsJsonObject().get("long_name").getAsString();
                                        }
                                        if (key.equals("administrative_area_level_1")) {
                                            uf = p.getAsJsonObject().get("long_name").getAsString();
                                        }
                                    }
                                    //MOSTRAR O DIALOG DE CONFIRMAÇÃO DE ENDEREÇO
                                  /* cartBusiness.setAdress(adress);
                                   cartBusiness.setBairro(bairro);
                                   cartBusiness.setCity(cidade);
                                   cartBusiness.setNumber(number);*/

                                  /*
                                    // TextView txtRua = findViewById(R.id.txtRua);
                                    txtRua.setText(adress + ", " + number);
                                    //TextView txtBairroCity = findViewById(R.id.txtBairroCity);
                                    txtBairroCity.setText(bairro + ", " + cidade);*/
                                    spinner.setVisibility(View.INVISIBLE);
                                   /*final AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                                    dialog.setTitle("Confirmar Localização")
                                            .setMessage("O endereço de entrega está correto?")
                                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                                    cartBusiness.setLatitude(latitude);
                                                    cartBusiness.setLongitude(longitude);
                                                    getCompaniesServer();
                                                }
                                            })
                                            .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                                    cartBusiness.setAdress(null);
                                                    cartBusiness.setBairro(null);
                                                    cartBusiness.setCity(null);
                                                    cartBusiness.setNumber(null);
                                                    Intent itentSearchAdress = new Intent(getApplicationContext(),AutoCompleteActivity.class);
                                                    startActivity(itentSearchAdress);
                                                }
                                            });
                                    //dialog.create();
                                    //dialog.show();
                                   */
                                    showAlertGoogleLocale(adress, bairro, cidade, number);

                                  /* cartBusiness.setAdress(adress);
                                   cartBusiness.setBairro(bairro);
                                   cartBusiness.setCity(cidade);
                                   cartBusiness.setNumber(number);
                                   cartBusiness.setLatitude(latitude);
                                   cartBusiness.setLongitude(longitude);
                                   TextView txtRua = findViewById(R.id.txtRua);
                                   txtRua.setText(adress + ", " + number);
                                   TextView txtBairroCity = findViewById(R.id.txtBairroCity);
                                   txtBairroCity.setText(bairro + ", " + cidade);
                                   spinner.setVisibility(View.GONE);
                                   getCompaniesServer();*/
                                } else {
                                    spinner.setVisibility(View.GONE);
                                    showAlert("Atenção", "Nenhum endereço encontrado!");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        spinner.setVisibility(View.INVISIBLE);
                        t.printStackTrace();
                    }
                });
            }
            //FROM GOOGLE PLACES
            else {
                spinner.setVisibility(View.VISIBLE);
                Adress adressRet = Utils.formatAdress(myAdress);
               /*String adress = null;
               String bairro = null;
               String cidade = null;
               String uf = null;
               String temp = null;
               adress = myAdress.substring(0,myAdress.indexOf("-") - 1);
               temp = myAdress.substring(myAdress.indexOf("-") + 2);
               bairro = temp.substring(0,myAdress.indexOf(",") - 1);
               cidade = temp.substring(temp.indexOf(",") +2 ,temp.indexOf("-") - 1);
               temp = temp.substring(temp.indexOf("-") - 1);
               uf = temp.substring(temp.indexOf("-") +2 ,temp.indexOf(","));
               cartBusiness.setAdress(adress);
               cartBusiness.setBairro(bairro);
               cartBusiness.setCity(cidade);
               cartBusiness.setNumber("");*/
                if (null != adressRet) {
                    cartBusiness.setAdress(adressRet.getAdress());
                    cartBusiness.setBairro(adressRet.getBairro());
                    cartBusiness.setCity(adressRet.getCity());
                    cartBusiness.setNumber(adressRet.getNumber());
                    cartBusiness.setLatitude(latitude);
                    cartBusiness.setLongitude(longitude);
                    // TextView txtRua = findViewById(R.id.txtRua);
                    txtRua.setText(adressRet.getAdress());
                    // TextView txtBairroCity = findViewById(R.id.txtBairroCity);
                    txtBairroCity.setText(adressRet.getBairro().concat(", ").concat(adressRet.getCity()));
                    spinner.setVisibility(View.INVISIBLE);
                    getCompaniesServer(latitude, longitude);
                } else {
                    spinner.setVisibility(View.INVISIBLE);
                    showAlert("Atenção", "Nenhum endereço válido encontrado!");
                }
            }
        } else {
            spinner.setVisibility(View.VISIBLE);
            Gson gson = new Gson();
            try {
                String adressAsString = getIntent().getStringExtra("MyAdressAsString");
                Adress adress = gson.fromJson(adressAsString, Adress.class);
                if (null != adress) {
                    //Resources resources = this.getResources();
                    cartBusiness.setAdress(adress.getAdress());
                    cartBusiness.setBairro(adress.getBairro());
                    cartBusiness.setComplemento(adress.getComplement());
                    cartBusiness.setCity(adress.getCity());
                    cartBusiness.setNumber(adress.getNumber());
                    //CONSULTAR A LATITUDE E LONGITUDE AQUI-----------------------------
                    latitude = adress.getLatitude();
                    longitude = adress.getLongitude();
                    cartBusiness.setLatitude(latitude);
                    cartBusiness.setLongitude(longitude);
                    //TextView txtRua = findViewById(R.id.txtRua);
                    txtRua.setText(adress.getAdress().concat(", ".concat(adress.getNumber())));
                    //TextView txtBairroCity = findViewById(R.id.txtBairroCity);
                    txtBairroCity.setText(adress.getBairro().concat(", ".concat(adress.getCity())));
                    spinner.setVisibility(View.INVISIBLE);
                    getCompaniesServer(latitude, longitude);
                   /*nameLogo =  Utils.getNameLogo(this.product.getIdB());
                   if(!nameLogo.equals("")) {
                       resourceId = resources.getIdentifier(nameLogo, "drawable", this.getPackageName());
                       Drawable drawable = resources.getDrawable(resourceId);
                       avator.setImageDrawable(drawable);
                   }*/
                } else {
                    spinner.setVisibility(View.INVISIBLE);
                    showAlert("Atenção", "Nenhum endereço encontrado!");
                }
            } catch (Exception e) {
                spinner.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void showAlertGoogleLocale(final String adress, final String bairro, final String cidade, final String number) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Confirmar Localização")
                .setMessage("O endereço de entrega está correto?\n"+adress + ", " + number+"\n"+bairro)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        cartBusiness.setAdress(adress);
                        cartBusiness.setBairro(bairro);
                        cartBusiness.setCity(cidade);
                        cartBusiness.setNumber(number);
                        cartBusiness.setLatitude(latitude);
                        cartBusiness.setLongitude(longitude);
                        txtRua.setText(adress + ", " + number);
                        txtBairroCity.setText(bairro + ", " + cidade);
                        getCompaniesServer(latitude, longitude);
                    }
                })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        cartBusiness.setAdress(null);
                        cartBusiness.setBairro(null);
                        cartBusiness.setCity(null);
                        cartBusiness.setNumber(null);
                        // Intent itentSearchAdress = new Intent(getApplicationContext(),AutoCompleteActivity.class);
                        // startActivity(itentSearchAdress);
                        openAutocompleteActivity();
                    }
                });

        dialog.show();
    }

    private void showAlert(String title, String message) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        /*Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);*/
                        Intent itentSearchAdress = new Intent(getApplicationContext(), AutoCompleteActivity.class);
                        startActivity(itentSearchAdress);
                    }
                });
                /*.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                      //Intent itentSearchAdress = new Intent(getApplicationContext(),AutoCompleteActivity.class);
                      //  startActivity(itentSearchAdress);
                    }
                });*/

        dialog.show();
    }

    private void showAlert() {
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        OptionsOrderByAdapter optionsOrderByAdapter = new OptionsOrderByAdapter(this, 1);
        final View promptView = layoutInflater.inflate(R.layout.dialog_filter_companies, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        recViewOptions = promptView.findViewById(R.id.list_op_companies);
        recViewOptions.setAdapter(optionsOrderByAdapter);
        builder.setView(promptView);
        final AlertDialog dlgFilterGeral = builder.show();
        if (null != lstBrandsFilter || null != lstLitersFilter) {
            LinearLayout divGeralFiltrarCompanies = promptView.findViewById(R.id.div_geral_filtrar_companies);
            insertDivInLayout(layoutInflater, divGeralFiltrarCompanies);
        }
        final Button btnMarcas = promptView.findViewById(R.id.btn_filtrar_marcas_companies);
        if (null != btnMarcas) {
            btnMarcas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //btnMarcas.setBackgroundTintList(Color.rgb(123,104,238));
                    spinner.setVisibility(View.VISIBLE);
                    btnMarcas.setEnabled(false);
                    //spinner.setVisibility(View.VISIBLE);
                    if (null == lstBrands || lstBrands.isEmpty()) {
                        getMarcasServer(layoutInflater, promptView, btnMarcas);
                    } else {
                        getDialogBrands(layoutInflater, promptView, "N",btnMarcas);
                    }
                }
            });
        }
       final Button btnLitros = promptView.findViewById(R.id.btn_filtrar_litros_companies);
        if (null != btnLitros) {
            btnLitros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnLitros.setEnabled(false);
                    spinner.setVisibility(View.VISIBLE);
                    if (null == lstLiters || lstLiters.isEmpty()) {
                        getLitersServer(layoutInflater, promptView,btnLitros);
                    } else {
                        getDialogLiters(layoutInflater, promptView, "N", btnLitros);
                    }
                }
            });
        }
        Button btnFiltrar = promptView.findViewById(R.id.btn_save_end);
        if (null != btnFiltrar) {
            btnFiltrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!lstBrandsFilter.isEmpty() && (null == lstLitersFilter || lstLitersFilter.isEmpty())) {
                        String ids = "";
                        for (Brand bra : lstBrandsFilter) {
                            ids += Integer.toString(bra.getIdBrand()).concat(",");
                        }
                        ids = "{".concat(ids.substring(0, ids.length() - 1).concat("}"));
                        getCompaniesByBrandsServer(cartBusiness.getLatitude(), cartBusiness.getLongitude(), ids);
                        dlgFilterGeral.dismiss();
                    } else if (!lstLitersFilter.isEmpty() && (null == lstBrandsFilter || lstBrandsFilter.isEmpty())) {
                        String ids = "";
                        for (Liters bra : lstLitersFilter) {
                            ids += Integer.toString(bra.getIdLiters()).concat(",");
                        }
                        ids = "{".concat(ids.substring(0, ids.length() - 1).concat("}"));
                        getCompaniesByLitersServer(cartBusiness.getLatitude(), cartBusiness.getLongitude(), ids);
                        dlgFilterGeral.dismiss();
                    } else if (!lstLitersFilter.isEmpty() && !lstBrandsFilter.isEmpty()) {
                        String idsb = "";
                        for (Brand bra : lstBrandsFilter) {
                            idsb += Integer.toString(bra.getIdBrand()).concat(",");
                        }
                        idsb = "{".concat(idsb.substring(0, idsb.length() - 1).concat("}"));
                        String idsl = "";
                        for (Liters bra : lstLitersFilter) {
                            idsl += Integer.toString(bra.getIdLiters()).concat(",");
                        }
                        idsl = "{".concat(idsl.substring(0, idsl.length() - 1).concat("}"));
                        getCompaniesByBrandsLitersServer(cartBusiness.getLatitude(), cartBusiness.getLongitude(), idsb, idsl);
                        dlgFilterGeral.dismiss();
                    } else {
                        //Pegar aqui o campo selecionado para ordenação  list_op_companies
                        if (null != recViewOptions.getAdapter()) {
                            SparseBooleanArray checkedOrder = recViewOptions.getCheckedItemPositions();
                            for (int i = 0; i < recViewOptions.getAdapter().getCount(); i++) {
                                if (checkedOrder.get(i)) {
                                    companiesOrderBy(i);
                                    i =  recViewOptions.getAdapter().getCount();
                                }
                            }
                        }
                        dlgFilterGeral.dismiss();
                    }
                    /*
                    //Pegar aqui o campo selecionado para ordenação  list_op_companies
                    if (null != recViewOptions.getAdapter()) {
                        SparseBooleanArray checkedOrder = recViewOptions.getCheckedItemPositions();
                        for (int i = 0; i < recViewOptions.getAdapter().getCount(); i++) {
                            if (checkedOrder.get(i)) {
                                companiesOrderBy(i);
                                i =  recViewOptions.getAdapter().getCount();
                            }
                        }
                    }*/
                }
            });
        }

        Button btnLimparFiltros = promptView.findViewById(R.id.btn_limpar_filtros_companies);
        if (null != btnLimparFiltros) {
            btnLimparFiltros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != recViewBrands) {
                        lstBrandsFilter = new ArrayList<>();
                        for (int i = 0; i < recViewBrands.getAdapter().getCount(); i++) {
                            recViewBrands.setItemChecked(i, false);
                        }
                    }
                    if (null != recViewLiters) {
                        lstLitersFilter = new ArrayList<>();
                        for (int i = 0; i < recViewLiters.getAdapter().getCount(); i++) {
                            recViewLiters.setItemChecked(i, false);
                        }
                    }
                    if (null != recViewBrands || null != recViewLiters) {
                        getCompaniesServer(cartBusiness.getLatitude(), cartBusiness.getLongitude());
                    }

                    dlgFilterGeral.dismiss();
                }
            });
        }
    }

    private void companiesOrderBy(int tpOrder) {
       switch (tpOrder){
           // "Crescente(A - Z)", "Decrescente(Z - A)", "Menor Taxa","Maior Taxa"}
           case 0:
               Collections.sort(lstCompanies, new Comparator<Company>() {
                   @Override
                   public int compare(Company o1, Company o2) {
                       // return Integer.valueOf(o1.getNm()).compareTo(Integer.valueOf(o2.getNm()));
                       return o1.getNm().compareTo(o2.getNm());
                   }
               });
               /*adapter.notifyDataSetChanged();
               adapter.notifyAdapterDataSetChanged(lstCompanies);*/
               adapter = new CompanyAdapter(lstCompanies);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
               break;
           case 1:
               Collections.sort(lstCompanies, new Comparator<Company>() {
                   @Override
                   public int compare(Company o1, Company o2) {
                       return o2.getNm().compareTo(o1.getNm());
                   }
               });
               /*adapter.notifyDataSetChanged();
               adapter.notifyAdapterDataSetChanged(lstCompanies);*/
               adapter = new CompanyAdapter(lstCompanies);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(lstCompanies, new Comparator<Company>() {
                    @Override
                    public int compare(Company o1, Company o2) {
                        return o1.getTx().compareTo(o2.getTx());
                    }
                });
               /*adapter.notifyDataSetChanged();
               adapter.notifyAdapterDataSetChanged(lstCompanies);*/
                adapter = new CompanyAdapter(lstCompanies);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case 3:
                Collections.sort(lstCompanies, new Comparator<Company>() {
                    @Override
                    public int compare(Company o1, Company o2) {
                        return o2.getTx().compareTo(o1.getTx());
                    }
                });
                /*adapter.notifyDataSetChanged();
                adapter.notifyAdapterDataSetChanged(lstCompanies);*/
                adapter = new CompanyAdapter(lstCompanies);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
           case 4:
             //  ArrayList<Company> lst = adapter.getLstCompanies();
                 Collections.sort(lstCompanies, new Comparator<Company>() {
                   @Override
                   public int compare(Company o1, Company o2) {
                       return o2.getAv().compareTo(o1.getAv());
                   }
               });
               /*adapter.notifyDataSetChanged();
               adapter.notifyAdapterDataSetChanged(lstCompanies);*/
               adapter = new CompanyAdapter(lstCompanies);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
               break;
        }
    }

    private void getDialogBrands(final LayoutInflater layoutInflater, final View view, String fromServer, Button btnEnable) {
        btnEnable.setEnabled(true);
        if ("S" == fromServer) {
            brandDialog.show();
            spinner.setVisibility(View.VISIBLE);
            Button btnFiltrarMarcas = viewM.findViewById(R.id.btn_concluido_filter_marcas);
            if (null != btnFiltrarMarcas) {
                final LinearLayout divGeralFiltrarCompanies = view.findViewById(R.id.div_geral_filtrar_companies);
                btnFiltrarMarcas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SparseBooleanArray checked = recViewBrands.getCheckedItemPositions();
                        for (int i = 0; i < recViewBrands.getAdapter().getCount(); i++) {
                            if (checked.get(i)) {
                                Brand b = (Brand) recViewBrands.getAdapter().getItem(i);
                                addBrandInFilter(b);
                            }
                        }
                        brandDialog.dismiss();
                        insertDivInLayout(layoutInflater, divGeralFiltrarCompanies);
                    }
                });
            }
        } else {
            brandDialog.show();
            spinner.setVisibility(View.INVISIBLE);
            Button btnFiltrarMarcas = viewM.findViewById(R.id.btn_concluido_filter_marcas);
            if (null != btnFiltrarMarcas) {
                final LinearLayout divGeralFiltrarCompanies = view.findViewById(R.id.div_geral_filtrar_companies);
                btnFiltrarMarcas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SparseBooleanArray checked = recViewBrands.getCheckedItemPositions();
                        lstBrandsFilter = new ArrayList<>();
                        for (int i = 0; i < recViewBrands.getAdapter().getCount(); i++) {
                            if (checked.get(i)) {
                                Brand b = (Brand) recViewBrands.getAdapter().getItem(i);
                                addBrandInFilter(b);
                            }
                        }
                        brandDialog.dismiss();  // to dismiss
                        insertDivInLayout(layoutInflater, divGeralFiltrarCompanies);
                    }
                });
            }

        }
    }

    private void getDialogLiters(final LayoutInflater layoutInflater, final View view, String fromServer, Button btnLiters) {
        btnLiters.setEnabled(true);
        if ("S" == fromServer) {
            litersDialog.show();
            spinner.setVisibility(View.GONE);
            Button btnFiltrarLiters = viewL.findViewById(R.id.btn_concluido_filter_liters);
            if (null != btnFiltrarLiters) {
                final LinearLayout divGeralFiltrarCompanies = view.findViewById(R.id.div_geral_filtrar_companies);
                btnFiltrarLiters.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SparseBooleanArray checked = recViewLiters.getCheckedItemPositions();
                        for (int i = 0; i < recViewLiters.getAdapter().getCount(); i++) {
                            if (checked.get(i)) {
                                Liters b = (Liters) recViewLiters.getAdapter().getItem(i);
                                addLitersInFilter(b);
                            }
                        }
                        litersDialog.dismiss();
                        insertDivInLayout(layoutInflater, divGeralFiltrarCompanies);
                    }
                });
            }
        } else {
            litersDialog.show();
            spinner.setVisibility(View.GONE);
            Button btnFiltrarLiters = viewL.findViewById(R.id.btn_concluido_filter_liters);
            if (null != btnFiltrarLiters) {
                final LinearLayout divGeralFiltrarCompanies = view.findViewById(R.id.div_geral_filtrar_companies);
                btnFiltrarLiters.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SparseBooleanArray checked = recViewLiters.getCheckedItemPositions();
                        lstLitersFilter = new ArrayList<>();
                        for (int i = 0; i < recViewLiters.getAdapter().getCount(); i++) {
                            if (checked.get(i)) {
                                Liters b = (Liters) recViewLiters.getAdapter().getItem(i);
                                addLitersInFilter(b);
                            }
                        }
                        litersDialog.dismiss();  // to dismiss
                        insertDivInLayout(layoutInflater, divGeralFiltrarCompanies);
                    }
                });
            }
        }
    }

    private void insertDivInLayout(LayoutInflater layoutInflater, LinearLayout divPrincipal) {
        //divPrincipal.removeView(div1);
        //OPÇÃO 1 UTILIZAR UM ARRAYLIST PADRÃO PARA MARCAS E LITROS
        // NESTE TERIA UM CAMPO PARA INDICAR A QUAL TIPO PERTENCE O ITEM
        // REMOVER TODOS OS ITENS DE MARCA OU TODOS DE LITROS
        // MANTER UM ARRAY INDIVIDUAL PARA MARCA E LITROS
        // O CÓDIGO JÁ ESTARIA PRONTO
        if ((null != lstBrandsFilter && lstBrandsFilter.size() > 0) || (null != lstLitersFilter && lstLitersFilter.size() > 0)) {
            lstFiltersDTO = new ArrayList<>();
            for (Brand bra : lstBrandsFilter) {
                lstFiltersDTO.add(new FiltersDTO(1, bra.getIdBrand(), bra.getBrandName()));
            }

            for (Liters bra : lstLitersFilter) {
                lstFiltersDTO.add(new FiltersDTO(2, bra.getIdLiters(), bra.getLitersName()));
            }


            divPrincipal.removeAllViews();
            //Calculando a quantidade de linha
            int lines = Utils.calculateDivFilters(lstFiltersDTO.size());
            // int lines = Utils.calculateDivFilters(lstBrandsFilter.size());
            //Inserir botões na linha até quatro itens;
            int lim;
            int ini;
            for (int z = 1; z <= lines; z++) {
                LinearLayout div = (LinearLayout) layoutInflater.inflate(R.layout.div_content_filtros1, null);
                ini = 4 * (z - 1);
                lim = z * 4;
                /*for (int i = ini; i < lim; i++) {
                    if(lstBrandsFilter.size() > i) {
                        AppCompatButton btn1 = (AppCompatButton) layoutInflater.inflate(R.layout.content_filtros_pos1, null);
                        btn1.setText(lstBrandsFilter.get(i).getBrandName());
                        div.addView(btn1);
                    }
                }*/
                for (int i = ini; i < lim; i++) {
                    if (lstFiltersDTO.size() > i) {
                        AppCompatButton btn1 = (AppCompatButton) layoutInflater.inflate(R.layout.content_filtros_pos1, null);
                        btn1.setText(lstFiltersDTO.get(i).getNameObject());
                        div.addView(btn1);
                    }
                }
                //Add a linha na div geral; lstBrandsFilter.size()
                divPrincipal.addView(div);
            }
            //Inserir os botões nas próximas linhas;
           /* for (int i = 0; i < lstBrandsFilter.size(); i++) {
                LinearLayout div1 = (LinearLayout) layoutInflater.inflate(R.layout.div_content_filtros1, null);

                AppCompatButton btn1 = (AppCompatButton) layoutInflater.inflate(R.layout.content_filtros_pos1, null);
                btn1.setText(lstBrandsFilter.get(i).getBrandName());
                div1.addView(btn1);
                divPrincipal.addView(div1);

            }*/
        } else {
            divPrincipal.removeAllViews();
        }
    }

    private void addBrandInFilter(Brand b) {
        if (null == lstBrandsFilter || lstBrandsFilter.isEmpty()) {
            lstBrandsFilter.add(b);
        } else {
            boolean isBrandInFilter = false;
            for (Brand bra : lstBrandsFilter) {
                if (bra.getIdBrand() == b.getIdBrand()) {
                    isBrandInFilter = true;
                }
            }
            if (!isBrandInFilter) {
                lstBrandsFilter.add(b);
            }
        }
    }

    private void addLitersInFilter(Liters b) {
        if (null == lstLitersFilter || lstLitersFilter.isEmpty()) {
            lstLitersFilter.add(b);
        } else {
            boolean isLitersInFilter = false;
            for (Liters bra : lstLitersFilter) {
                if (bra.getIdLiters() == b.getIdLiters()) {
                    isLitersInFilter = true;
                }
            }
            if (!isLitersInFilter) {
                lstLitersFilter.add(b);
            }
        }
    }

    private void getMarcasServer(final LayoutInflater layoutInflater, final View view, final Button btnMarcas) {
        lstBrands = new ArrayList<>();
        spinner.setVisibility(View.VISIBLE);
        Call<ArrayList<Brand>> call = new InitializerApi(Configuration.getBaseUrl()).getBrandService().getBrands();
        call.enqueue(new Callback<ArrayList<Brand>>() {
            @Override
            public void onResponse(Call<ArrayList<Brand>> call, Response<ArrayList<Brand>> response) {
                lstBrands = response.body();
                if (lstBrands.size() > 0) {
                    viewM = layoutInflater.inflate(R.layout.dialog_filter_marcas, null);
                    marcasB = new BrandAdapter(CompaniesActivity.this, lstBrands);
                    recViewBrands = viewM.findViewById(R.id.list_marcas);
                    recViewBrands.setAdapter(marcasB);
                    builderBrandsFilter = new AlertDialog.Builder(viewM.getContext());
                    builderBrandsFilter.setView(viewM);
                    brandDialog = builderBrandsFilter.create();
                    getDialogBrands(layoutInflater, view, "S",btnMarcas);
                } else {
                    Toast.makeText(CompaniesActivity.this, "Nenhuma Marca Encontrada!", Toast.LENGTH_LONG).show();
                    lstBrands = new ArrayList<>();
                }
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Brand>> call, Throwable t) {
                spinner.setVisibility(View.INVISIBLE);
                Log.e("Falha ao pegar Marcas", t.getCause().getMessage());
            }
        });
    }

    private void getLitersServer(final LayoutInflater layoutInflater, final View view, final Button btnLiters) {
        lstLiters = new ArrayList<>();
        Call<ArrayList<Liters>> call = new InitializerApi(Configuration.getBaseUrl()).getLitersService().getLiters();
        call.enqueue(new Callback<ArrayList<Liters>>() {
            @Override
            public void onResponse(Call<ArrayList<Liters>> call, Response<ArrayList<Liters>> response) {
                lstLiters = response.body();
                if (lstLiters.size() > 0) {
                    //ORDERNANDO A LISTA
                    Utils.orderLitersById(lstLiters);
                    viewL = layoutInflater.inflate(R.layout.dialog_filter_liters, null);
                    litersB = new LitersAdapter(CompaniesActivity.this, lstLiters);
                    recViewLiters = viewL.findViewById(R.id.list_liters);
                    recViewLiters.setAdapter(litersB);
                    builderLitersFilter = new AlertDialog.Builder(viewL.getContext());
                    builderLitersFilter.setView(viewL);
                    litersDialog = builderLitersFilter.create();
                    getDialogLiters(layoutInflater, view, "S", btnLiters);
                } else {
                    Toast.makeText(CompaniesActivity.this, "Nenhuma Quantidade Encontrada!", Toast.LENGTH_LONG).show();
                    lstLiters = new ArrayList<>();
                }
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Liters>> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                Log.e("Falha pegar Quantidades", t.getCause().getMessage());
            }
        });
    }

    private void openAutocompleteActivity() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setBoundsBias(new LatLngBounds(
                    new LatLng(-23.5475000, -46.6361100),
                    new LatLng(-23.5475000, -46.6361100)))
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily resolvable.
            String message = "Google Play Services não está disponível: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check that the result was from the autocomplete widget.
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                if (place.getLatLng().latitude != 0 && place.getLatLng().longitude != 0) {
                    spinner.setVisibility(View.VISIBLE);
                    Adress adressRet = Utils.formatAdress(place.getAddress().toString());
                    if (null != adressRet) {
                        String lat = Double.toString(place.getLatLng().latitude);
                        String longi = Double.toString(place.getLatLng().longitude);
                        cartBusiness.setAdress(adressRet.getAdress());
                        cartBusiness.setBairro(adressRet.getBairro());
                        cartBusiness.setCity(adressRet.getCity());
                        cartBusiness.setNumber(adressRet.getNumber());
                        cartBusiness.setLatitude(lat);
                        cartBusiness.setLongitude(longi);
                        txtRua.setText(adressRet.getAdress());
                        txtBairroCity.setText(adressRet.getBairro().concat(", ").concat(adressRet.getCity()));
                        spinner.setVisibility(View.GONE);
                        getCompaniesServer(lat, longi);
                    } else {
                        spinner.setVisibility(View.GONE);
                        showAlert("Atenção", "Nenhum endereço válido encontrado!");
                    }
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_cart) {
           /* Intent myIntent = new Intent(this, CartActivity.class);
            //myIntent.putExtra("idCompany", lstCompanies.get(1).getId());
            startActivity(myIntent);*/
            if (cartBusiness.getItens().size() == 0) {
                Toast.makeText(this, "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
            } else {
                Intent myIntent = new Intent(this, CartActivity.class);
                startActivity(myIntent);
            }
        } else if (id == R.id.action_options) {
            showAlert();
        }

        /*else if (id == R.id.action_company_asc) {
            Collections.sort(lstCompanies, new Comparator<Company>() {
                @Override
                public int compare(Company o1, Company o2) {
                   // return Integer.valueOf(o1.getNm()).compareTo(Integer.valueOf(o2.getNm()));
                    return o1.getNm().compareTo(o2.getNm());
                }
            });
            adapter.notifyDataSetChanged();

            //adapter = new CompanyAdapter(lstCompanies);
            //recyclerView.setAdapter(adapter);

            //showAlert();
        }else if (id == R.id.action_company_desc) {
            Collections.sort(lstCompanies, new Comparator<Company>() {
                @Override
                public int compare(Company o1, Company o2) {
                return o2.getNm().compareTo(o1.getNm());
                }
            });
            //adapter = new CompanyAdapter(lstCompanies);
            //recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else if (id == R.id.action_company_delivery_min) {
            Collections.sort(lstCompanies, new Comparator<Company>() {
                @Override
                public int compare(Company o1, Company o2) {
                return o1.getTx().compareTo(o2.getTx());
                }
            });
            adapter.notifyDataSetChanged();
        }
        else if (id == R.id.action_company_delivery_max) {
            Collections.sort(lstCompanies, new Comparator<Company>() {
                @Override
                public int compare(Company o1, Company o2) {
                    return o2.getTx().compareTo(o1.getTx());
                }
            });
            adapter.notifyDataSetChanged();
        }*/
        return super.onOptionsItemSelected(item);
    }

}
