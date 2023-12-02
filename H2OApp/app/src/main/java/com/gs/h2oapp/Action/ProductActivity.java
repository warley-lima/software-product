package com.gs.h2oapp.Action;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Utils;

import java.math.BigDecimal;

public class ProductActivity extends AppCompatActivity {
    private CarrinhoBusiness cartBusiness;
    private Product product;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView nameProd, descProd, valorProd;
    private ImageView avator;
    private String nameLogo = "";
    private int resourceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("Detalhes do Produto");
        setupHeaderNavigation(toolbar);
        initViews();

        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
        getProduct();

    }
    private void initViews(){
        nameProd = findViewById(R.id.name_prod);
        descProd = findViewById(R.id.desc_prod);
        valorProd = findViewById(R.id.valor_prod);
        avator = findViewById(R.id.img_prod);
        final LinearLayout divBtnAddProd = findViewById(R.id.div_add_prod);
        if(null != divBtnAddProd){
            divBtnAddProd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null == product){
                        Toast.makeText(ProductActivity.this, "Falha. Produto Vazio!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addCart(product);
                    }
                }
            });
        }
        final ImageButton btnAddProdCart = findViewById(R.id.btnAddProdCart);
        if(null != btnAddProdCart){
            btnAddProdCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null == product){
                        Toast.makeText(ProductActivity.this, "Falha. Produto Vazio!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addCart(product);
                    }
                }
            });
        }
        final LinearLayout divCloseProd = findViewById(R.id.div_close_prod);
        if(null != divCloseProd){
            divCloseProd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        final ImageButton btnCloseProd = findViewById(R.id.btnCloseProd);
        if(null != btnCloseProd){
            btnCloseProd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
    private void setupHeaderNavigation(Toolbar toolbar) {
        NavigationView navigationView = findViewById(R.id.nav_prod_view);
        mDrawerLayout = findViewById(R.id.drawer_product);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
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
    private void addCart(Product p) {
        try {
            p.setDescontoPromocionalProduto(BigDecimal.ZERO);
            p.setQuantProdutoAddCart(BigDecimal.ONE);
            p.setPrecoUnitarioProduto(p.getPrecoUnitarioNormalProduto());
            cartBusiness.insertItemCarrinho(p);
            Toast.makeText(this, "Produto Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
           // finish();
            Intent myIntent = new Intent(this, CartActivity.class);
            startActivity(myIntent);
        }
        catch (Exception e){
            Log.d("Erro", "Adding product: " + e.getMessage());
            Toast.makeText(this, "Falha ao Adicionar o Produto!", Toast.LENGTH_SHORT).show();
        }
    }
    private void getProduct(){
        Gson gson = new Gson();
        try {
            String productAsAString = getIntent().getStringExtra("MyProductAsString");
            product = gson.fromJson(productAsAString, Product.class);
            if(null != product){
                Resources resources = this.getResources();
                nameProd.setText(this.product.getNm());
                descProd.setText(this.product.getNm());
               // valorProd.setText("R$" + this.product.getPrecoUnitarioNormalProduto());
                valorProd.setText("R$".concat(Utils.formatToCurrency(this.product.getPrecoUnitarioNormalProduto())));
                nameLogo =  Utils.getNameLogo(this.product.getIdB());
                if(!nameLogo.equals("")) {
                    resourceId = resources.getIdentifier(nameLogo, "drawable", this.getPackageName());
                    Drawable drawable = resources.getDrawable(resourceId);
                    avator.setImageDrawable(drawable);
                }
            }
        }catch (Exception e){
            System.out.println("ERRO AO CONVERTER O PRODUTO!" + e.getMessage());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }  else if (id == R.id.action_cart) {
            Intent myIntent = new Intent(this, CartActivity.class);
            startActivity(myIntent);
        }else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }*/
        if (id == R.id.action_cart) {
            if(cartBusiness.getItens().size() == 0){
                Toast.makeText(this, "O Carrinho está vazio!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent myIntent = new Intent(this, CartActivity.class);
                startActivity(myIntent);
            }
        }else if (id == R.id.action_back) {
            //spinner.setVisibility(View.GONE);
            finish();
            return true;
        }else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
