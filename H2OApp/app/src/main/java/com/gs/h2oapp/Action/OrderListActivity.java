package com.gs.h2oapp.Action;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.gs.h2oapp.Adapter.OrdersListAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Order;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Repository.OrderRepository;
import com.gs.h2oapp.Utils.ItemClickListener;

import java.util.List;

public class OrderListActivity extends AppCompatActivity implements ItemClickListener {
    private List<Order> lstOrders;
    private OrdersListAdapter adapter;
    private ProgressBar spinner;
    private DrawerLayout mDrawerLayout;
    private CarrinhoBusiness cartBusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Meus Pedidos");
        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        setupHeaderNavigation();


        OrderRepository orderRepository = new OrderRepository(getApplicationContext());
        lstOrders =  orderRepository.getAllOrders();
        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = findViewById(R.id.recycler_orders);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        if (adapter == null) {
            if(!lstOrders.isEmpty()){
                /*for(Order a : lstOrders){
                    System.out.println("Teste********" +a.getName());
                }*/
                adapter = new OrdersListAdapter(lstOrders);
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


    private void setupHeaderNavigation() {
        NavigationView navigationView =  findViewById(R.id.nav_view_orders);
        mDrawerLayout = findViewById(R.id.drawer_orders);
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
                        }else if (id == R.id.action_meu_cart) {
                            if(cartBusiness.getItens().size() == 0){
                                Toast.makeText(getBaseContext(), "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent myIntent = new Intent(getBaseContext(), CartActivity.class);
                                startActivity(myIntent);
                            }
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
        /*if (id == R.id.action_cart) {
            spinner.setVisibility(View.GONE);
            if(cartBusiness.getItens().size() == 0){
                Toast.makeText(this, "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent myIntent = new Intent(this, CartActivity.class);
                startActivity(myIntent);
            }
        }else */if (id == R.id.action_back) {
            spinner.setVisibility(View.GONE);
            finish();
            return true;
        }else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


   /* private void removeOrder(Order a){
       if (orderRepository.delete(a) > 0){
            lstOrders =  orderRepository.getAllOrders();
            for(Order z : lstOrders){
                System.out.println("CARAIO---------" +z.getName());
            }
            adapter = new OrdersListAdapter(lstOrders);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Pedido removido com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Não foi possível remover o pedido!", Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void onClick(LinearLayout view, int position) {
       /* if (position > -1){
            switch (view.getId()) {
                case R.id.div_rem_order:
                    removeOrder(lstOrders.get(position));
                    break;
                default:
                    break;
            }
        }*/
    }

    @Override
    public void onClick(ImageButton view, int position) {
        /*if (position > -1) {
             switch (view.getId()) {
                case R.id.btnRemOrder:
                    removeOrder(lstOrders.get(position));
                    break;
                default:
                    //return super.onOptionsItemSelected(item);
                    break;
            }
        }*/
    }

    @Override
    public void onClick(View view, int position) {

    }
}
