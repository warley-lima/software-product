package com.gs.h2oapp.Action;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gs.h2oapp.Adapter.UserListAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.User;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Repository.UserRepository;
import com.gs.h2oapp.Utils.ItemClickListener;

import java.util.List;

public class UserListActivity extends AppCompatActivity implements ItemClickListener {
    private List<User> userList;
    private UserListAdapter adapter;
    private ProgressBar spinner;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CarrinhoBusiness cartBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Meu Perfil");

        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        setupHeaderNavigation(toolbar);
        FloatingActionButton fab =  findViewById(R.id.fab_add_user);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), UserActivity.class);
                startActivity(myIntent);
            }
        });
        UserRepository userRepository = new UserRepository(getApplicationContext());
        userList =  userRepository.getAllUser();
        if(!userList.isEmpty()){
            fab.setVisibility(View.INVISIBLE);
        }else{
            fab.setVisibility(View.VISIBLE);
        }
        initViews();
    }


    private void setupHeaderNavigation(Toolbar toolbar) {
        NavigationView navigationView =  findViewById(R.id.nav_view_user);
        mDrawerLayout = findViewById(R.id.drawer_user);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            assert indicator != null;
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
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
                        } else if (id == R.id.action_meus_locais) {
                            Intent myIntent = new Intent(getApplicationContext(), AdressListActivity.class);
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


    private void initViews(){
       spinner.setVisibility(View.VISIBLE);
       RecyclerView recyclerView = findViewById(R.id.recycler_user);
       recyclerView.setHasFixedSize(false);
       recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        if (adapter == null) {
            if(!userList.isEmpty()){
               adapter = new UserListAdapter(userList);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);
                spinner.setVisibility(View.GONE);
            }
            else{
                spinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Nenhum registro encontrado!", Toast.LENGTH_SHORT).show();
            }
        } else {
            spinner.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(LinearLayout view, int position) {
        if (position > -1){
             switch (view.getId()) {
                case R.id.div_update_user:
                    final User user = userList.get(position);
                    Intent itentUpdtUser = new Intent(getApplicationContext(), UserUpdateActivity.class);
                    itentUpdtUser.putExtra("idUser", user.getIdUser());
                    itentUpdtUser.putExtra("nameUser", user.getName());
                    itentUpdtUser.putExtra("foneUser", user.getFone());
                    startActivity(itentUpdtUser);
                break;
            }
        }
    }

    @Override
    public void onClick(ImageButton view, int position) {
        if (position > -1) {
            final User user = userList.get(position);
            switch (view.getId()) {
                case R.id.btnUpdateUser:
                    Intent itentUpdtUser = new Intent(getApplicationContext(), UserUpdateActivity.class);
                    itentUpdtUser.putExtra("idUser", user.getIdUser());
                    itentUpdtUser.putExtra("nameUser", user.getName());
                    itentUpdtUser.putExtra("foneUser", user.getFone());
                    startActivity(itentUpdtUser);
                break;
            }
        }
    }

    @Override
    public void onClick(View view, int position) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.main_order_adress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_cart) {
            spinner.setVisibility(View.GONE);
            if(cartBusiness.getItens().size() == 0){
                Toast.makeText(this, "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent myIntent = new Intent(this, CartActivity.class);
                startActivity(myIntent);
            }
        }else*/ if (id == R.id.action_back) {
            spinner.setVisibility(View.GONE);
            finish();
            return true;
        }else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
