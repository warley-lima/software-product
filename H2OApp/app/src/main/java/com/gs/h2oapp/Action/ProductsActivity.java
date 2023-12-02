package com.gs.h2oapp.Action;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gs.h2oapp.Adapter.ProductAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Company;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Configuration;
import com.gs.h2oapp.Utils.InitializerApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private static ArrayList<Product> lstProd;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private TextView nameDist;
    private CarrinhoBusiness cartBusiness;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Produtos");
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        setupHeaderNavigation(toolbar);
        lstProd = new ArrayList<>();
       // getActivity().setTitle("Distribuidoras");
        initViews();
    }

    private void setupHeaderNavigation(Toolbar toolbar) {
        // Create Navigation drawer and inlfate layout
        NavigationView navigationView =  findViewById(R.id.nav_view_products);
        mDrawerLayout = findViewById(R.id.drawer_products);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
           /* indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);*/
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
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

    public void addCart() {
        /*Intent intent = new Intent(this, CompaniesActivity.class);
        startActivity(intent);*/
    }

    private void initViews(){
        nameDist = findViewById(R.id.name_dist);
        if(null != nameDist){
           // nameDist.setText(getIntent().getStringExtra("MyNameDist"));
            nameDist.setText(cartBusiness.getNameCompany());
        }
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getProductsServer();
    }

    private void getProductsServer() {
        lstProd = null;
       /* int idCompany = getIntent().getIntExtra("idCompany", 0);
        Call<ArrayList<Product>> call =   new InitializerApi(Configuration.getBaseUrl()).getProductService().loadProducts(idCompany);*/
        Call<ArrayList<Product>> call =   new InitializerApi(Configuration.getBaseUrl()).getProductService().loadProducts(cartBusiness.getIdCompany());
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
              //  Log.i("onResponse chamado", "onResponse: " + response.body());
                lstProd = response.body();
                if(lstProd != null) {
                    adapter = new ProductAdapter(lstProd, ProductsActivity.this);
                }
                else{
                    Toast.makeText(ProductsActivity.this, "Nenhum Produto Encontrado!", Toast.LENGTH_LONG).show();
                    lstProd = new ArrayList<>();
                    adapter = new ProductAdapter(lstProd, ProductsActivity.this);

                }
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                spinner.setVisibility(View.GONE);
                 /*if (lstCompanies.size() > 0) {
                    for (Company p : lstCompanies) {
                        System.out.println(p.getId() +" " + p.getNm() +" R$" +p.getTx());
                    }
                }*/





                /*if (lstProd.size() > 0) {
                    for (Company p : lstProd) {
                        System.out.println(p.getId() +" " + p.getNm() +" R$" +p.getTx());
                    }
                }*/

            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                Log.e("onFaillure chamado ", t.getCause().getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_products, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            spinner.setVisibility(View.GONE);
            if(cartBusiness.getItens().size() == 0){
                Toast.makeText(this, "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent myIntent = new Intent(this, CartActivity.class);
                startActivity(myIntent);
            }
        }else if (id == R.id.action_back) {
            spinner.setVisibility(View.GONE);
            finish();
            return true;
        }else if (id == R.id.action_order_asc) {
            Collections.sort(lstProd, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getNm().compareTo(o2.getNm());
                }
            });
            adapter.notifyDataSetChanged();
        }else if (id == R.id.action_order_desc) {
            Collections.sort(lstProd, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o2.getNm().compareTo(o1.getNm());
                }
            });
            adapter.notifyDataSetChanged();
        }else if (id == R.id.action_value_min) {
            Collections.sort(lstProd, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getPrecoUnitarioNormalProduto().compareTo(o2.getPrecoUnitarioNormalProduto());
                }
            });
            adapter.notifyDataSetChanged();
        }else if (id == R.id.action_value_max) {
            Collections.sort(lstProd, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o2.getPrecoUnitarioNormalProduto().compareTo(o1.getPrecoUnitarioNormalProduto());
                }
            });
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
