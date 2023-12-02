package com.gs.h2oapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gs.h2oapp.Action.CompaniesActivity;
import com.gs.h2oapp.Entity.Adress;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.ItemClickListener;

import java.util.List;

/**
 * Created by Warley Lima 
 */
public class AdressListAdapter extends RecyclerView.Adapter<AdressListAdapter.ViewHolder>{
    private List<Adress> lstAdress;
    private ItemClickListener clickListener;
    private Adress adress;
    private ViewGroup viewGroup;
    public AdressListAdapter(List<Adress> android) {
        this.lstAdress = android;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup vg, int i) {
        this.viewGroup = vg;
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.content_adress_list, vg, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (lstAdress.size() > 0) {
            adress = lstAdress.get(i);
            viewHolder.name.setText(adress.getName());
            viewHolder.adress.setText(adress.getAdress().concat(", ").concat(adress.getNumber()));
            viewHolder.bairro.setText(adress.getBairro());
            viewHolder.cidade.setText(adress.getCity());
            viewHolder.complemento.setText(adress.getComplement());

            LinearLayout divAdressList = viewHolder.itemView.findViewById(R.id.div_adress_list);
            if(null != divAdressList){
                divAdressList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Gson gson = new Gson();
                        String adressAsString = gson.toJson(lstAdress.get(i));
                        Intent intent = new Intent(viewGroup.getContext(), CompaniesActivity.class);
                        intent.putExtra("fromGoogle", "N" );
                        intent.putExtra("MyAdressAsString", adressAsString);
                        viewGroup.getContext().startActivity(intent);
                    }
                });
            }


            final LinearLayout divUpdtAdress = viewHolder.itemView.findViewById(R.id.div_update_adress);
            if(null != divUpdtAdress){
                divUpdtAdress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null){
                            clickListener.onClick(divUpdtAdress, i);
                        }

                    }
                });
            }
            final ImageButton btnUpdtAdress = viewHolder.itemView.findViewById(R.id.btnUpdateAdress);
            if(null != btnUpdtAdress){
                btnUpdtAdress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(btnUpdtAdress, i);
                        }
                    }
                });
            }

           /* final LinearLayout divRemAdress = viewHolder.itemView.findViewById(R.id.div_rem_adress);
            if(null != divRemAdress){
                divRemAdress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null){
                            clickListener.onClick(divRemAdress, i);
                        }

                    }
                });
            }
            final ImageButton btnRemAdress = viewHolder.itemView.findViewById(R.id.btnRemAdress);
            if(null != btnRemAdress){
                btnRemAdress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(btnRemAdress, i);
                        }
                    }
                });
            }*/
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        int ret;
        if(lstAdress != null){
            ret = lstAdress.size();
        }
        else{
            ret = 0;
        }
        return ret;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView adress;
        private TextView bairro;
        private TextView cidade;
        private TextView complemento;
        private ViewHolder(View itemView) {
           // super(inflater.inflate(R.layout.content_adress_list, parent, false));
            super(itemView);
            name = itemView.findViewById(R.id.name_item_adress);
            adress =  itemView.findViewById(R.id.list_adress);
            bairro =  itemView.findViewById(R.id.list_bairro);
            cidade =  itemView.findViewById(R.id.list_cidade);
            complemento =  itemView.findViewById(R.id.list_comp);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            // call the onClick in the ItemClickListener
            // if (clickListener != null) clickListener.onClick(view, getAdapterPosition());

        }
    }
}
