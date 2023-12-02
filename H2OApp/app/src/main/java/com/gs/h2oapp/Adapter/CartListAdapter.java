package com.gs.h2oapp.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.h2oapp.Action.CartActivity;
import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.ItemClickListener;
import com.gs.h2oapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Warley Lima
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{
    private List<ItemCart> lstItemsCart = new ArrayList<>();
    private ViewGroup viewGroup;
   // private CartActivity cartActivity;
    //private Callback callback;
    private ItemClickListener clickListener;
    private Context mContext;
    private int rowLayout;
    private String nameLogo = "";
    private int resourceId;

    private ItemCart itemCart;

    public CartListAdapter(List<ItemCart> android, int rowLayout, Context context) {
        this.lstItemsCart = android;
       // this.rowLayout = rowLayout;
        this.mContext = context;
    }

    /*@Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup vg, int i) {
        this.viewGroup = vg;
        if(cartActivity == null){
            this.cartActivity = new CartActivity();
        }
        return  new ViewHolder(LayoutInflater.from(vg.getContext()), vg);
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        this.viewGroup = viewGroup;
       // View v = LayoutInflater.from(mContext).inflate(R.layout.item_cart_list, viewGroup, false);
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart_list, viewGroup, false);
        //View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (lstItemsCart.size() > 0) {
            Resources resources = viewGroup.getContext().getResources();
            /*Drawable[] mPlaceAvators;

            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[1];
            for (int z = 6; z < 7; z++) {
                mPlaceAvators[0] = a.getDrawable(z);
            }*/
            itemCart = lstItemsCart.get(position);
            viewHolder.name.setText(itemCart.getProduct().getNm());
          //  viewHolder.description.setText("R$" + itemCart.getValor());
            viewHolder.description.setText("R$".concat(Utils.formatToCurrency(itemCart.getValor())));
            viewHolder.quantidade.setText(itemCart.getQuantidade().toString());

            nameLogo =  Utils.getNameLogo(this.itemCart.getProduct().getIdB());
            if(!nameLogo.equals("")) {
                resourceId = resources.getIdentifier(nameLogo, "drawable", mContext.getPackageName());
                Drawable drawable = resources.getDrawable(resourceId);
                viewHolder.avator.setImageDrawable(drawable);
            }


            final LinearLayout divRemItem = viewHolder.itemView.findViewById(R.id.div_rem_prod);
            if(null != divRemItem){
                divRemItem.setOnClickListener(new View.OnClickListener() {
                    /*@Override
                    public void onClick(View v) {
                        // setClickListener(this);
                        //((LinearLayout) divRemItem).onClick(v, position);
                        Toast.makeText(viewGroup.getContext(), "CARAIO:".concat(itemCart.getProduct().getNm()), Toast.LENGTH_LONG).show();

                    }*/
                    @Override
                    public void onClick(View view) {
                        // call the onClick in the ItemClickListener
                        if (clickListener != null) clickListener.onClick(divRemItem, position);

                    }
                });
            }
           final ImageButton btnRemItem = viewHolder.itemView.findViewById(R.id.btnRemCart);
            if(null != btnRemItem){
                btnRemItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) clickListener.onClick(btnRemItem, position);
                    }
                });
            }

            final LinearLayout divRemItemSeta = (LinearLayout)  viewHolder.itemView.findViewById(R.id.div_rem_item);
            if(null != divRemItemSeta){
                divRemItemSeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(divRemItemSeta, position);
                        }
                    }
                });
            }
            final ImageButton btnRemItemSeta = (ImageButton)  viewHolder.itemView.findViewById(R.id.btnRemItemCart);
            if(null != btnRemItemSeta){
                btnRemItemSeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                           // notifyDataSetChanged();
                            clickListener.onClick(btnRemItemSeta, position);
                        }
                    }
                });
            }
            final LinearLayout divAddItemSeta = (LinearLayout)  viewHolder.itemView.findViewById(R.id.div_add_item);
            if(null != divAddItemSeta){
                divAddItemSeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(divAddItemSeta, position);
                        }
                    }
                });
            }
            final ImageButton btnAddItemSeta = (ImageButton)  viewHolder.itemView.findViewById(R.id.btnAddtemCart);
            if(null != btnAddItemSeta){
                btnAddItemSeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(btnAddItemSeta, position);
                        }
                    }
                });
            }


            /*final LinearLayout divRemItem = (LinearLayout)  viewHolder.itemView.findViewById(R.id.div_rem_prod);
            if(null != divRemItem){
                divRemItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // setClickListener(this);
                        //((LinearLayout) divRemItem).onClick(v, position);
                    }
                });
            }*/
            /*LinearLayout divRemItem = (LinearLayout)  viewHolder.itemView.findViewById(R.id.div_rem_prod);
            if(null != divRemItem){
                divRemItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(viewGroup.getContext(), "Produto:".concat(itemCart.getProduct().getNm()), Toast.LENGTH_LONG).show();
                        cartActivity.removeItem(itemCart.getProduct());
                        //addCart(lstItemsCart.get(position));
                        //int id= lstProducts.get(position).getIdP();
                       // Toast.makeText(viewGroup.getContext(), "ID Produto:".concat(" "+id), Toast.LENGTH_LONG).show();
                    }
                });
            }

            ImageButton btnRemItem = (ImageButton)  viewHolder.itemView.findViewById(R.id.btnRemCart);
            if(null != btnRemItem){
                btnRemItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(viewGroup.getContext(), "Produto 2:".concat(itemCart.getProduct().getNm()), Toast.LENGTH_LONG).show();
                        cartActivity.removeItem(itemCart.getProduct());
                    }
                });
            }*/
            //ImageButton divAddCart = (ImageButton)  viewHolder.itemView.findViewById(R.id.btnAddCart);
           /* if(null != divAddCart){
                divAddCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // addCart();
                        int id= itemCart.getProduct().getIdP();
                        Toast.makeText(viewGroup.getContext(), "ID Produto:".concat(" "+id), Toast.LENGTH_LONG).show();
                    }
                });
            }*/

            /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id= lstItemsCart.get(position).getIdP();

                    Toast.makeText(viewGroup.getContext(), "ID Produto:".concat(" "+id), Toast.LENGTH_LONG).show();
                }
            });*/
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

    /*public Product removeItem(Product p){
        cartBusiness.deleteItemCarrinho(p);
        System.out.println("SUCESSO ---------------------> " + cartBusiness.getValor());
        Toast.makeText(viewGroup.getContext(), "Produto Removido com Sucesso!", Toast.LENGTH_SHORT).show();
       // getValueTotal();
        return p;
    }*/

    /*private void setClickListeners(View view)
    {
        view.setOnClickListener(clickListener);
    }*/

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    private void addCart() {
       // Intent intent = new Intent(viewGroup.getContext(), CompaniesActivity.class);
        Toast.makeText(viewGroup.getContext(), "Produto Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
        //setContentView(R.layout.activity_products);
        //startActivity(intent);
        //intent.
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView avator;
        private TextView name;
        private TextView description;
        private TextView quantidade;
        public ViewHolder(View itemView) {
            super(itemView);
            avator = itemView.findViewById(R.id.list_img);
            name = itemView.findViewById(R.id.name_item);
            description = itemView.findViewById(R.id.list_valor);
            quantidade = itemView.findViewById(R.id.txt_quantidade);
            //LinearLayout divRemItem = (LinearLayout) itemView.findViewById(R.id.div_rem_prod);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        /*private ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_cart_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_img);
            name = (TextView) itemView.findViewById(R.id.name_item);
            description = (TextView) itemView.findViewById(R.id.list_valor);
            quantidade = (TextView) itemView.findViewById(R.id.txt_quantidade);
            itemView.setOnClickListener(this); // bind the listener

        }*/

        @Override
        public void onClick(View view) {
            // call the onClick in the ItemClickListener
           // if (clickListener != null) clickListener.onClick(view, getAdapterPosition());

        }

    }


}
