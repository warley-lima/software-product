package com.gs.h2oapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Utils;

import java.util.List;

/**
 * Created by Warley Lima
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder>{
    private List<ItemCart> lstItemsCart;
    private ViewGroup viewGroup;
    private Context mContext;

    private ItemCart itemCart;

    public OrderListAdapter(List<ItemCart> android, Context context) {
        this.lstItemsCart = android;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        this.viewGroup = viewGroup;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_order_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (lstItemsCart.size() > 0) {
           try{
               Resources resources = viewGroup.getContext().getResources();
               itemCart = lstItemsCart.get(position);
               viewHolder.name.setText(itemCart.getProduct().getNm());
               // viewHolder.valor.setText("R$" + itemCart.getValor());
               viewHolder.valor.setText("R$".concat(Utils.formatToCurrency(itemCart.getValor())));
               viewHolder.quantidade.setText(itemCart.getQuantidade().toString());

               String nameLogo = Utils.getNameLogo(this.itemCart.getProduct().getIdB());
               if(!nameLogo.equals("")) {
                   int resourceId = resources.getIdentifier(nameLogo, "drawable", mContext.getPackageName());
                   Drawable drawable = resources.getDrawable(resourceId);
                   viewHolder.avator.setImageDrawable(drawable);
               }
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }



    @Override
    public int getItemCount() {
        int ret;
        if(lstItemsCart.isEmpty()){
            ret = 0;
        }
        else{
            ret = lstItemsCart.size();
        }
        return ret;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView avator;
        private TextView name;
        private TextView valor;
        private TextView quantidade;
        ViewHolder(View itemView) {
            super(itemView);
            avator = itemView.findViewById(R.id.list_avatar_product_order);
            name = itemView.findViewById(R.id.list_title_product_order);
            valor = itemView.findViewById(R.id.list_desc_product_order);
            quantidade = itemView.findViewById(R.id.list_qnt_product_order);
            itemView.setTag(itemView);
        }
    }
}
