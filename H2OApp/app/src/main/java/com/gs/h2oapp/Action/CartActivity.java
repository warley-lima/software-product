package com.gs.h2oapp.Action;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.h2oapp.Adapter.CartListAdapter;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.ItemClickListener;
import com.gs.h2oapp.Utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerItemList;
    private CartListAdapter cartListAdapter;
    private CarrinhoBusiness cartBusiness;
    private TextView txtEntrega,txtTotal;
    private List<ItemCart> lstItemsCart;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
       /* container = findViewById(R.id.container);
        hello = findViewById(R.id.welcome);
        Profile = findViewById(R.id.profile);*/

        //Set back button to activity
        /*android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Carrinho");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

        if(null == lstItemsCart){
            lstItemsCart = new ArrayList<>();
        }
        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }

        lstItemsCart = cartBusiness.getItens();

        /*txtTotal = (TextView) findViewById(R.id.txtTotal);
        if(txtTotal != null){
            txtTotal.setText("R$" + cartBusiness.getValor());
        }*/
        txtTotal = findViewById(R.id.tv_total);
        txtEntrega = findViewById(R.id.tv_entrega);
        getValueEntrega();
        getValueTotal();
        initViews();
    }

    private void getValueEntrega(){
        if(txtEntrega != null && cartBusiness.getItens().size() > 0){
            //txtEntrega.setText("R$" + cartBusiness.getValorEntrega());
            txtEntrega.setText("R$".concat(Utils.formatToCurrency(cartBusiness.getValorEntrega())));
        }
    }
    private void getValueTotal(){
        if(txtTotal != null && cartBusiness.getValor() != BigDecimal.ZERO){
            BigDecimal tot = cartBusiness.getValor();
            tot = tot.subtract(cartBusiness.getValorDescontoCupom());
            tot = tot.add(cartBusiness.getValorEntrega());
            txtTotal.setText("R$".concat(Utils.formatToCurrency(tot)));
        }
    }
    public void removeItem(Product p){
        if(recyclerItemList != null){
            cartBusiness.deleteItemCarrinho(p);
            cartListAdapter.notifyDataSetChanged();
            getValueTotal();
            spinner.setVisibility(View.GONE);
            Toast.makeText(this, "Produto Removido com Sucesso!", Toast.LENGTH_SHORT).show();
            if(cartBusiness.getItens().size() == 0){
                txtEntrega.setText("R$0,00");
                txtTotal.setText("R$0,00");
            }
        }
    }
    public void removeItemSeta(Product p){
        if(recyclerItemList != null){
            cartBusiness.deleteItemCarrinhoSeta(p);
           cartListAdapter.notifyDataSetChanged();
            getValueTotal();
            spinner.setVisibility(View.GONE);
           // Toast.makeText(this, "Quantidade Removida com Sucesso!", Toast.LENGTH_SHORT).show();
            if(cartBusiness.getItens().size() == 0){
                txtEntrega.setText("R$0,00");
                txtTotal.setText("R$0,00");
            }
        }
    }
    public void addItemSeta(Product p){
        if(recyclerItemList != null){
            cartBusiness.insertItemCarrinhoSeta(p);
            cartListAdapter.notifyDataSetChanged();
            getValueTotal();
            spinner.setVisibility(View.GONE);
           // Toast.makeText(this, "Quantidade Adicionada com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    /*DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setMealTotal();
        }
    };*/

    private void makeOrder(){
        //cartBusiness.clearCart();
        /*if(txtTotal != null && txtEntrega != null){
            txtTotal.setText("R$0,00");
            txtEntrega.setText("R$0,00");
        }*/
        cartListAdapter.notifyDataSetChanged();
        //Invocando a Tela de Confirmação
        Intent myIntent = new Intent(this, CheckOutActivity.class);
        //myIntent.putExtra("MyNameDist", lstCompanies.get(i).getNm());
       // myIntent.putExtra("idCompany", lstCompanies.get(i).getId());
        //  myIntent.putExtra("valorEntrega", lstCompanies.get(i).getTx());
        startActivity(myIntent);
        //Toast.makeText(this, "Pedido Realizado com sucesso!", Toast.LENGTH_LONG).show();
    }

    private void initViews(){
        spinner= findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        recyclerItemList = findViewById(R.id.recycler_cart);
        recyclerItemList.setHasFixedSize(false);
        recyclerItemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       // cartListAdapter = new CartListAdapter(cartBusiness.getItens(), this);
        if (cartListAdapter == null) {
            lstItemsCart = cartBusiness.getItens();
            cartListAdapter = new CartListAdapter(lstItemsCart, R.layout.item_cart_list,this);
            cartListAdapter.setClickListener(this);
            //cartListAdapter.setHasStableIds(true);
            //cartListAdapter.registerDataSetObserver(observer);
            recyclerItemList.setAdapter(cartListAdapter);
        } else {
            cartListAdapter.notifyDataSetChanged();
        }
        /*final LinearLayout divBtnContShopping = (LinearLayout) findViewById(R.id.div_btn_cont_shopping);
        if(null != divBtnContShopping){
            divBtnContShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    //Intent myIntent = new Intent(CartActivity.this, ProductsActivity.class);
                    //startActivity(myIntent);
                }
            });
        }*/
        final Button btnContShopping = findViewById(R.id.btn_cont_shopping);
        if(null != btnContShopping){
            btnContShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //finish();
                    Intent myIntent = new Intent(CartActivity.this, ProductsActivity.class);
                    startActivity(myIntent);
                }
            });
        }

       /* final LinearLayout divBtnOrder = (LinearLayout) findViewById(R.id.div_btn_order);
        if(null != divBtnOrder){
            divBtnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeOrder();
                }
            });
        }*/
        final Button btnPlaceOrder = findViewById(R.id.btn_save_end);
        if(null != btnPlaceOrder){
            btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!cartBusiness.getItens().isEmpty()) {
                        makeOrder();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Seu Carrinho está vazio!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        spinner.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view, final int position) {
        /*final ItemCart itemCart = lstItemsCart.get(position);
        Toast.makeText(this, "---:".concat(itemCart.getProduct().getNm()), Toast.LENGTH_LONG).show();
        switch (view.getId()) {
            case R.id.div_rem_prod:
                removeItem(lstItemsCart.get(position).getProduct());
                break;
            default:
                //return super.onOptionsItemSelected(item);
                break;
        }*/
    }

    @Override
    public void onClick(LinearLayout view, int position) {
        /*if (position > -1){
           // final ItemCart itemCart = lstItemsCart.get(position);
           // Toast.makeText(this, "HOPE IN JESUS--->".concat(itemCart.getProduct().getNm()), Toast.LENGTH_LONG).show();
            // System.out.println("HOPE ---------------------> " + view.getParent().);
            switch (view.getId()) {
                case R.id.div_rem_prod:
                    spinner.setVisibility(View.VISIBLE);
                    //final ItemCart itemCart = lstItemsCart.get(position);
                    removeItem(lstItemsCart.get(position).getProduct());
                    break;
                case R.id.div_rem_item:
                    spinner.setVisibility(View.VISIBLE);
                    removeItemSeta(lstItemsCart.get(position).getProduct());
                    break;
                case R.id.div_add_item:
                    spinner.setVisibility(View.VISIBLE);
                    addItemSeta(lstItemsCart.get(position).getProduct());
                    break;
                default:
                    //return super.onOptionsItemSelected(item);
                    break;
            }
        }*/
    }

    @Override
    public void onClick(ImageButton view, int position) {
        if (position > -1) {
            //final ItemCart itemCart = lstItemsCart.get(position);
            switch (view.getId()) {
                case R.id.btnRemCart:
                    spinner.setVisibility(View.VISIBLE);
                    removeItem(lstItemsCart.get(position).getProduct());
                    break;
                case R.id.btnRemItemCart:
                    spinner.setVisibility(View.VISIBLE);
                    removeItemSeta(lstItemsCart.get(position).getProduct());
                    break;
                case R.id.btnAddtemCart:
                    spinner.setVisibility(View.VISIBLE);
                    addItemSeta(lstItemsCart.get(position).getProduct());
                    break;
                default:
                    //return super.onOptionsItemSelected(item);
                    break;
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void insertOrder(View view){
        /*if(total>0){
            Gson gson = new Gson();
           // jsonCartList = gson.toJson(CartListAdapter.selecteditems);
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            placeOrderRequest();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
            builder.setMessage("Do you want to place Order ?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }else{
            Toast.makeText(CartActivity.this,"No items in Cart !", Toast.LENGTH_LONG).show();
        }*/
    }

    private void placeOrderRequest(){
        //Send Request to Server with required Parameters
        /*
          jsonCartList - Consists of Objects of all product selected.
        */
    }

}
