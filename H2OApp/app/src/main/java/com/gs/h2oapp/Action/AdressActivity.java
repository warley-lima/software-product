package com.gs.h2oapp.Action;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.AdressQueryCep;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Repository.AdressRepository;
import com.gs.h2oapp.Utils.Configuration;
import com.gs.h2oapp.Utils.InitializerApi;
import com.gs.h2oapp.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdressActivity extends AppCompatActivity {
    private TextInputEditText txtName, txtCep, txtNumber, txtComp,txtAdress, txtBairro, txtCity;
    private TextInputLayout iptLytName;
    private TextInputLayout iptLytCep;
    private TextInputLayout iptLytNumber;
    private TextInputLayout iptLytAdress;
    private TextInputLayout iptLytBairro;
    private TextInputLayout iptLytCity;
    private AdressQueryCep resp;
    private ProgressBar spinner;
    private DrawerLayout mDrawerLayout;
    private CarrinhoBusiness cartBusiness;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        Toolbar toolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Adicionar Endereço");
        setupHeaderNavigation(toolbar);
        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        iptLytName =  findViewById(R.id.input_layout_name_adress);
        iptLytCep = findViewById(R.id.input_layout_cep);
        iptLytNumber =  findViewById(R.id.input_layout_number_adress);
        iptLytAdress =  findViewById(R.id.input_layout_adress_adress);
        iptLytBairro=  findViewById(R.id.input_layout_bairro_adress);
        iptLytCity=  findViewById(R.id.input_layout_cidade_adress);
        txtName =  findViewById(R.id.input_name_adress);
        txtCep =  findViewById(R.id.input_cep);
        txtNumber = findViewById(R.id.input_number_adress);
        txtComp = findViewById(R.id.input_complemento_adress);
        txtAdress = findViewById(R.id.input_adress_adress);
        txtBairro = findViewById(R.id.input_bairro_adress);
        txtCity = findViewById(R.id.input_cidade_adress);
        Button btnSignUp = findViewById(R.id.btn_cadastrar_adress);
        txtName.setText(getIntent().getStringExtra("nameAdress"));
        txtCep.setText(getIntent().getStringExtra("cepAdress"));
        txtNumber.setText(getIntent().getStringExtra("numberAdress"));
        txtComp.setText(getIntent().getStringExtra("compAdress"));
        txtAdress.setText(getIntent().getStringExtra("adressAdress"));
        txtBairro.setText(getIntent().getStringExtra("bairroAdress"));
        txtCity.setText(getIntent().getStringExtra("cityAdress"));
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                if(submitForm()){
                   clearTexts();
                   spinner.setVisibility(View.GONE);
                   Intent myIntent = new Intent(getApplicationContext(), AdressListActivity.class);
                   startActivity(myIntent);
               }
            }
        });
        txtCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    getAdressByCep();
                }
            }
        });
        requestFocus(txtName);
    }

    private void getAdressByCep() {
        if (!txtCep.getText().toString().trim().isEmpty() && txtCep.getText().toString().replaceAll("-", "").length() == 8) {
            spinner.setVisibility(View.VISIBLE);
            getAdressQueryCep(txtCep.getText().toString().trim());
        }
    }

    private void getAdressQueryCep(String cep) {
        Call<AdressQueryCep> call =   new InitializerApi(Configuration.getBaseUrl()).getQueryCepService().queryCep(cep);
        call.enqueue(new Callback<AdressQueryCep>() {
            @Override
            public void onResponse(@NonNull Call<AdressQueryCep> call, @NonNull Response<AdressQueryCep> response) {
                resp = response.body();
                if(resp != null) {
                    txtAdress.setText(resp.getEnd());
                    txtBairro.setText(resp.getBairro());
                    txtCity.setText(resp.getCidade());
                    spinner.setVisibility(View.GONE);
                }
                else{
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "O CEP não foi encontrado!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AdressQueryCep> call, @NonNull Throwable t) {
                spinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Falha ao consultar o CEP!", Toast.LENGTH_LONG).show();
              }
        });
    }

    private void setupHeaderNavigation(Toolbar toolbar) {
        NavigationView navigationView =  findViewById(R.id.nav_adress_view);
        mDrawerLayout = findViewById(R.id.drawer_adress_ad);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            assert indicator != null;
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        boolean ret = false;
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.action_home: {
                                Adress adress = new Adress();
                                adress.setAdress(cartBusiness.getAdress());
                                adress.setBairro(cartBusiness.getBairro());
                                adress.setNumber(cartBusiness.getNumber());
                                adress.setCity(cartBusiness.getCity());
                                adress.setLatitude(cartBusiness.getLatitude());
                                adress.setLongitude(cartBusiness.getLongitude());
                                Gson gson = new Gson();
                                String adressAsString = gson.toJson(adress);
                                Intent myIntent = new Intent(getApplicationContext(), CompaniesActivity.class);
                                myIntent.putExtra("fromGoogle", "N");
                                myIntent.putExtra("MyAdressAsString", adressAsString);
                                startActivity(myIntent);
                                ret = true;
                                break;
                            }
                            case R.id.action_meu_cart:
                                if (cartBusiness.getItens().size() == 0) {
                                    Toast.makeText(getBaseContext(), "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent myIntent = new Intent(getBaseContext(), CartActivity.class);
                                    startActivity(myIntent);
                                }
                                ret = true;
                                break;
                            case R.id.action_meu_perfil: {
                                Intent myIntent = new Intent(getBaseContext(), UserListActivity.class);
                                startActivity(myIntent);
                                ret = true;
                                break;
                            }
                            case R.id.action_meus_pedidos: {
                                Intent myIntent = new Intent(getBaseContext(), OrderListActivity.class);
                                startActivity(myIntent);
                                ret = true;
                                break;
                            }
                        }
                        return ret;
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_order_adress, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_back) {
            spinner.setVisibility(View.GONE);
            finish();
            return true;
        }else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean submitForm() {
       if (!validateName()) {
           spinner.setVisibility(View.GONE);
            return false;
        }
        if (!validateCep()) {
            spinner.setVisibility(View.GONE);
            return false;
        }
        if (!validateNumber()) {
            spinner.setVisibility(View.GONE);
            return false;
        }
        if (!validateAdress()) {
            spinner.setVisibility(View.GONE);
            return false;
        }
        if (!validateBairro()) {
            spinner.setVisibility(View.GONE);
            return false;
        }
        if (!validateCidade()) {
            spinner.setVisibility(View.GONE);
            return false;
        }
        try{
           //CONSULTAR A LATITUDE E A LONGITUDE
            String adressConsulta = txtAdress.getText().toString().trim();
            String numConsulta = txtNumber.getText().toString().trim();
            String bairroConsulta = txtBairro.getText().toString().trim();
            String cidadeConsulta = txtCity.getText().toString().trim();
            String consulta = adressConsulta;
            if (!numConsulta.equals("")){
                consulta = consulta.concat(", ".concat(numConsulta));
            }
            if (!bairroConsulta.equals("")){
                consulta = consulta.concat(" - ".concat(bairroConsulta));
            }
            if (!cidadeConsulta.equals("")){
                consulta = consulta.concat(", ".concat(cidadeConsulta));
            }
            LatLng latLng = Utils.getLocationFromAddress(this, consulta);
            AdressRepository orderRepository = new AdressRepository(getApplicationContext());
            Adress o = new Adress();
            if (latLng != null){
                o.setAdress(adressConsulta);
                o.setNumber(numConsulta);
                o.setBairro(bairroConsulta);
                o.setCity(cidadeConsulta);
                o.setCep(txtCep.getText().toString().trim());
                o.setComplement(txtComp.getText().toString().trim());
                o.setName(txtName.getText().toString().trim());
                o.setLatitude(Double.toString(latLng.latitude));
                o.setLongitude(Double.toString(latLng.longitude));
                long idOrder = orderRepository.insert(o);
                System.out.println("INSERIDO COM SUCESSO " + idOrder);
                return true;
            }else{
                o.setAdress(adressConsulta);
                o.setNumber(numConsulta);
                o.setBairro(bairroConsulta);
                o.setCity(cidadeConsulta);
                o.setCep(txtCep.getText().toString().trim());
                o.setComplement(txtComp.getText().toString().trim());
                o.setName(txtName.getText().toString().trim());
                o.setLatitude("");
                o.setLongitude("");
                orderRepository.insert(o);
                return true;
            }
       }
        catch (Exception e){
            spinner.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Falha ao salvar o Endereço!".concat(e.toString()), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void clearTexts(){
        txtCep.setText("");
        txtAdress.setText("");
        txtBairro.setText("");
        txtCity.setText("");
        txtName.setText("");
        txtNumber.setText("");
        txtComp.setText("");
    }
    private boolean validateName() {
        if (txtName.getText().toString().trim().isEmpty()) {
            iptLytName.setError(getString(R.string.err_msg_name_adress));
            requestFocus(txtName);
            return false;
        } else {
            iptLytName.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateCep() {
        if (txtCep.getText().toString().trim().isEmpty()) {
            iptLytCep.setError(getString(R.string.err_msg_cep));
            requestFocus(txtCep);
            return false;
        } else {
            iptLytCep.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateNumber() {
        if (txtNumber.getText().toString().trim().isEmpty()) {
            iptLytNumber.setError(getString(R.string.err_msg_password));
            requestFocus(txtNumber);
            return false;
        } else {
            iptLytNumber.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateAdress() {
        if (txtAdress.getText().toString().trim().isEmpty()) {
            iptLytAdress.setError(getString(R.string.err_msg_adress));
            requestFocus(txtAdress);
            return false;
        } else {
            iptLytAdress.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateBairro() {
        if (txtBairro.getText().toString().trim().isEmpty()) {
            iptLytBairro.setError(getString(R.string.err_msg_bairro));
            requestFocus(txtBairro);
            return false;
        } else {
            iptLytBairro.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateCidade() {
        if (txtCity.getText().toString().trim().isEmpty()) {
            iptLytCity.setError(getString(R.string.err_msg_cidade));
            requestFocus(txtCity);
            return false;
        } else {
            iptLytCity.setErrorEnabled(false);
        }
        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
