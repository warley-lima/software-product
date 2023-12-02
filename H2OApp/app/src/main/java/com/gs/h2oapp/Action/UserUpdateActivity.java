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

import com.google.gson.Gson;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.User;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Repository.UserRepository;

public class UserUpdateActivity extends AppCompatActivity {
    private TextInputEditText txtName, txtFone;
    private TextInputLayout iptLytName;
    private TextInputLayout iptLytFone;
    private Integer idUser = 0;
    private UserRepository userRepository;
    private ProgressBar spinner;
    private DrawerLayout mDrawerLayout;
    private CarrinhoBusiness cartBusiness;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Editar Endereço");

        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        setupHeaderNavigation(toolbar);


        iptLytName =  findViewById(R.id.input_layout_name_update_user);
        iptLytFone = findViewById(R.id.input_layout_fone_update_user);

        txtName =  findViewById(R.id.input_name_update_user);
        txtFone =  findViewById(R.id.input_fone_update_user);

        Button btnUpdate = findViewById(R.id.btn_cadastrar_update_user);
        Button btnRemove = findViewById(R.id.btn_remove_user);

        txtName.setText(getIntent().getStringExtra("nameUser"));
        txtFone.setText(getIntent().getStringExtra("foneUser"));
        idUser = getIntent().getIntExtra("idUser", 0);

        spinner.setVisibility(View.INVISIBLE);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                if(submitForm()){
                    clearTexts();
                    Intent myIntent = new Intent(getApplicationContext(), UserListActivity.class);
                    startActivity(myIntent);
                }
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);
                removeUser();
            }
        });
        requestFocus(txtName);
    }
    private void setupHeaderNavigation(Toolbar toolbar) {
        NavigationView navigationView =  findViewById(R.id.nav_user_up_view);
        mDrawerLayout = findViewById(R.id.drawer_user_up);
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
                        if (id == R.id.action_home) {
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
                            myIntent.putExtra("fromGoogle", "N" );
                            myIntent.putExtra("MyAdressAsString", adressAsString);
                            startActivity(myIntent);
                            ret = true;
                        } else if (id == R.id.action_meu_cart) {
                            if(cartBusiness.getItens().size() == 0){
                                Toast.makeText(getBaseContext(), "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent myIntent = new Intent(getBaseContext(), CartActivity.class);
                                startActivity(myIntent);
                            }
                            ret = true;
                        } else if (id == R.id.action_meus_pedidos) {
                            Intent myIntent = new Intent(getBaseContext(), OrderListActivity.class);
                            startActivity(myIntent);
                            ret = true;
                        } else if (id == R.id.action_meus_locais) {
                            Intent myIntent = new Intent(getBaseContext(), AdressListActivity.class);
                            startActivity(myIntent);
                            ret = true;
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
    private void removeUser(){
        userRepository = new UserRepository(getApplicationContext());
        User o = new User();
        o.setIdUser(idUser);
        if (userRepository.delete(o) > 0){
            clearTexts();
            spinner.setVisibility(View.INVISIBLE);
            Intent myIntent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(myIntent);
        }else{
            Toast.makeText(getApplicationContext(), "Não foi possível remover o perfil!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean submitForm() {
        if (!validateName()) {
            spinner.setVisibility(View.GONE);
            return false;
        }
        if (!validateFone()) {
            spinner.setVisibility(View.GONE);
            return false;
        }

        try{
            userRepository = new UserRepository(getApplicationContext());
            User o = new User();
            if (idUser != null){
                o.setName(txtName.getText().toString().trim());
                o.setFone(txtFone.getText().toString().trim());
                o.setIdUser(idUser);
                int idOrder = userRepository.update(o);
                System.out.println("EDITADO COM SUCESSO!!!!!" + idOrder);
                return true;
            }else{
                o.setName(txtName.getText().toString().trim());
                o.setFone(txtFone.getText().toString().trim());
                userRepository.insert(o);
                return true;
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Falha ao editar o Perfil!".concat(e.toString()), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void clearTexts(){
        txtFone.setText("");
        txtName.setText("");
        requestFocus(txtName);

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
    private boolean validateFone() {
        if (txtFone.getText().toString().trim().isEmpty()) {
            iptLytFone.setError(getString(R.string.err_msg_fone));
            requestFocus(txtFone);
            return false;
        } else {
            iptLytFone.setErrorEnabled(false);
        }
        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
