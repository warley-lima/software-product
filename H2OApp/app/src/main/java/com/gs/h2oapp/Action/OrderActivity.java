package com.gs.h2oapp.Action;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gs.h2oapp.Adapter.OrderListAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private ProgressBar spinner;
    private CarrinhoBusiness cartBusiness;
    private List<ItemCart> lstItemsCart;
    private DrawerLayout mDrawerLayout;
    private String nameCompany;
    private String taxaEntrega;
    private String valorProds;
    private String valorDescontos;
    private String valorPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Detalhes do Pedido");
        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        if(null == lstItemsCart){
            lstItemsCart = new ArrayList<>();
        }
        try{
            lstItemsCart = cartBusiness.getItens();
            nameCompany = cartBusiness.getNameCompany();
            // taxaEntrega = "R$".concat(cartBusiness.getValorEntrega().toString());
            taxaEntrega = "R$".concat(Utils.formatToCurrency(cartBusiness.getValorEntrega()));
            // valorPedido = "R$".concat(cartBusiness.getValor().toString());
            valorProds = "R$".concat(Utils.formatToCurrency(cartBusiness.getValor()));
          //  BigDecimal fromGoogle = BigDecimal.valueOf(getIntent().getLongExtra("valorDescontadoCupom",0));
           // valorDescontos= "- R$".concat(Utils.formatToCurrency(cartBusiness.getValorDescontoCupom()));
            BigDecimal t = new BigDecimal(getIntent().getStringExtra("valorDescontadoCupom"));
           // valorDescontos= "- R$".concat(Utils.formatToCurrency(BigDecimal.valueOf(getIntent().getLongExtra("valorDescontadoCupom",0))));
            valorDescontos= "- R$".concat(Utils.formatToCurrency(t));

           // valorPedido = "R$".concat(Utils.formatToCurrency(cartBusiness.getValorTotal()));
            //(getValor().subtract(getValorDescCumpom())).add(getValorEntrega())
            valorPedido = "R$".concat(Utils.formatToCurrency(cartBusiness.getValorTotal().subtract(t)));
        }catch (Exception t){
            t.printStackTrace();
        }
        setupHeaderNavigation();
        initViews();
    }
    private void setupHeaderNavigation() {
        NavigationView navigationView =  findViewById(R.id.nav_view_order);
        mDrawerLayout = findViewById(R.id.drawer_order);
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
    private void initViews(){
        TextView nameDist = findViewById(R.id.tv_company_order);
        if(null != nameDist){
           // nameCompany.setText(cartBusiness.getNameCompany());
            nameDist.setText(nameCompany);
        }
        TextView txEntrega = findViewById(R.id.tv_taxa);
        if(null != txEntrega){
            //taxaEntrega.setText("R$".concat(cartBusiness.getValorEntrega().toString()));
            txEntrega.setText(taxaEntrega);
        }
        TextView valorProdutos = findViewById(R.id.tv_subtotal);
        if(null != valorProdutos){
            valorProdutos.setText(valorProds);
        }
        TextView valorDesc= findViewById(R.id.tv_desc);
        if(null != valorDesc){
            valorDesc.setText(valorDescontos);
        }

        TextView valorOrder = findViewById(R.id.tv_total);
        if(null != valorOrder){
           // valorPedido.setText("R$".concat(Double.toString(cartBusiness.getValor())));
            valorOrder.setText(valorPedido);
        }
        TextView tvAdressOrder = findViewById(R.id.tv_adress_order);
        if(null != tvAdressOrder){
           // tvAdressOrder.setText(cartBusiness.getAdress().concat(",").concat(cartBusiness.getNumber()));
            if(null != cartBusiness.getNumber()){
                tvAdressOrder.setText(cartBusiness.getAdress().concat(",").concat(cartBusiness.getNumber()));
            }
            else{
                tvAdressOrder.setText(cartBusiness.getAdress());
            }
        }
        TextView tvCompOrder = findViewById(R.id.tv_comp_order);
        if(null != tvCompOrder){
            tvCompOrder.setText(cartBusiness.getComplemento());
        }
        TextView tvBairroOrder = findViewById(R.id.tv_bairro_order);
        if(null != tvBairroOrder){
            tvBairroOrder.setText(cartBusiness.getBairro());
        }
        TextView tvCityOrder = findViewById(R.id.tv_city_order);
        if(null != tvCityOrder){
            tvCityOrder.setText(cartBusiness.getCity());
        }

        Button btnContShopping = findViewById(R.id.btn_order_home);
        if(null != btnContShopping){
            btnContShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Adress adress = new Adress();
                    adress.setAdress(cartBusiness.getAdress());
                    adress.setBairro(cartBusiness.getBairro());
                    adress.setNumber(cartBusiness.getNumber());
                    adress.setCity(cartBusiness.getCity());
                    adress.setLatitude(cartBusiness.getLatitude());
                    adress.setLongitude(cartBusiness.getLongitude());
                    Gson gson = new Gson();
                    String adressAsString = gson.toJson(adress);
                    Intent myIntent = new Intent(OrderActivity.this, CompaniesActivity.class);
                    myIntent.putExtra("fromGoogle", "N" );
                    myIntent.putExtra("MyAdressAsString", adressAsString);
                    startActivity(myIntent);

                }
            });
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_order_prod);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        OrderListAdapter adapter;
        if(lstItemsCart != null) {
            adapter = new OrderListAdapter(lstItemsCart, OrderActivity.this);
        }
        else{
            Toast.makeText(OrderActivity.this, "Nenhum Produto Encontrado!", Toast.LENGTH_LONG).show();
            lstItemsCart = new ArrayList<>();
            adapter = new OrderListAdapter(lstItemsCart, OrderActivity.this);
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        cartBusiness.setItens(new ArrayList<ItemCart>());
        cartBusiness.setValorEntrega(BigDecimal.ZERO);
        cartBusiness.setValorDescontoCupom(BigDecimal.ZERO);
       // cartBusiness.setValor();
        spinner.setVisibility(View.GONE);

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_products, menu);
        return true;
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }/*else if (id == R.id.action_order_asc) {
            spinner.setVisibility(View.GONE);
        }else if (id == R.id.action_order_desc) {
            spinner.setVisibility(View.GONE);
        }else if (id == R.id.action_value) {
            spinner.setVisibility(View.GONE);
        }*/
        return super.onOptionsItemSelected(item);
    }
}
