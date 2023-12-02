package com.gs.h2oapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gs.h2oapp.Action.ProductActivity;
import com.gs.h2oapp.Action.ProductsActivity;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Warley Lima 
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private ArrayList<Product> lstProducts = new ArrayList<>();
    private ViewGroup viewGroup;
    private CarrinhoBusiness cartBusiness;
    private Context mContext;
    private String nameLogo = "";
    private int resourceId;

    public ProductAdapter(ArrayList<Product> android, Context context) {
        this.lstProducts = android;
        this.mContext = context;
        if(null == cartBusiness){
            cartBusiness = new CarrinhoBusinessImp();
        }
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup vg, int i) {
        this.viewGroup = vg;
        return  new ViewHolder(LayoutInflater.from(vg.getContext()), vg);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, final int position) {
        if (lstProducts.size() > 0) {
            Resources resources = viewGroup.getContext().getResources();
            /*Drawable[] mPlaceAvators;

            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[1];
            for (int z = 6; z < 7; z++) {
                mPlaceAvators[0] = a.getDrawable(z);
            }*/
            viewHolder.name.setText(this.lstProducts.get(position).getNm());
           // viewHolder.description.setText("R$" + this.lstProducts.get(position).getPrecoUnitarioNormalProduto());
            viewHolder.description.setText("R$".concat(Utils.formatToCurrency(this.lstProducts.get(position).getPrecoUnitarioNormalProduto())));
            //viewHolder.avator.setImageDrawable(mPlaceAvators[0]);

             nameLogo =  Utils.getNameLogo(this.lstProducts.get(position).getIdB());
            if(!nameLogo.equals("")) {
                resourceId = resources.getIdentifier(nameLogo, "drawable", mContext.getPackageName());
                Drawable drawable = resources.getDrawable(resourceId);
                viewHolder.avator.setImageDrawable(drawable);
            }

            LinearLayout divProductList = viewHolder.itemView.findViewById(R.id.div_product_list);
            if(null != divProductList){
                divProductList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Gson gson = new Gson();
                        String productAsAString = gson.toJson(lstProducts.get(position));
                        Intent intent = new Intent(mContext, ProductActivity.class);
                        //Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                        intent.putExtra("MyProductAsString", productAsAString);
                        mContext.startActivity(intent);
                    }
                });
            }

            ImageButton divAddCart = viewHolder.itemView.findViewById(R.id.btnAddCart);
            if(null != divAddCart){
                divAddCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCart(lstProducts.get(position));
                        /*int id= lstProducts.get(position).getIdP();
                        Toast.makeText(viewGroup.getContext(), "ID Produto:".concat(" "+id), Toast.LENGTH_LONG).show();*/
                    }
                });
            }

            /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id= lstProducts.get(position).getIdP();

                    Toast.makeText(viewGroup.getContext(), "ID Produto:".concat(" "+id), Toast.LENGTH_LONG).show();
                }
            });*/
        }
    }

    @Override
    public int getItemCount() {
        //return lstProducts.size();
        int ret;
        if(lstProducts.isEmpty()){
            ret = 0;
        }
        else{
            ret = lstProducts.size();
        }
        return ret;
    }

    private void addCart(Product p) {
        try {
            p.setDescontoPromocionalProduto(BigDecimal.ZERO);
            p.setQuantProdutoAddCart(BigDecimal.ONE);
            p.setPrecoUnitarioProduto(p.getPrecoUnitarioNormalProduto());
            cartBusiness.insertItemCarrinho(p);
           // System.out.println("SUCESSO ---------------------> " + cartBusiness.getValor());
            Toast.makeText(viewGroup.getContext(), "Produto Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Log.d("Erro", "Adding product: " + e.getMessage());
            Toast.makeText(viewGroup.getContext(), "Falha ao Adicionar o Produto!", Toast.LENGTH_SHORT).show();
        }
         // Intent intent = new Intent(viewGroup.getContext(), CompaniesActivity.class);

        //setContentView(R.layout.activity_products);
        //startActivity(intent);
        //intent.
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView avator;
        private TextView name;
        private TextView description;
        //private LinearLayout divProductList;
        private ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_product_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            //divProductList = (LinearLayout) itemView.findViewById(R.id.div_product_list);

            /*ImageButton divAddCart = (ImageButton) itemView.findViewById(R.id.btnAddCart);
            if(null != divAddCart){
                divAddCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       addCart();
                        System.out.println("ADD CART PRESSIONED----------------!!");
                    }
                });
            }*/
        }





    }


}
