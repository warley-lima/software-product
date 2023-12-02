package com.gs.h2oapp.Adapter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gs.h2oapp.Action.ProductsActivity;
import com.gs.h2oapp.Business.CarrinhoBusiness;
import com.gs.h2oapp.Business.CarrinhoBusinessImp;
import com.gs.h2oapp.Entity.Company;
import com.gs.h2oapp.Entity.CompanyPaymentTypes;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Warley Lima
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private ArrayList<Company> lstCompanies;
    private ViewGroup viewGroup;
    private CarrinhoBusiness cartBusiness;
    private Resources resources;
    private ViewHolder vhAdapterChange;
    private int z = 0;
    private int resourceId;
    private Drawable drawable;

    public CompanyAdapter(ArrayList<Company> android) {
        this.lstCompanies = android;
        if (null == cartBusiness) {
            this.cartBusiness = new CarrinhoBusinessImp();
        }

        // super(itemView);
        // context = itemView.getContext();
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {
        this.viewGroup = vg;
        return new ViewHolder(LayoutInflater.from(vg.getContext()), vg);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        if (lstCompanies.size() > 0) {
            // Drawable[] mPlaceAvators;
             resources = viewGroup.getContext().getResources();
           // vhAdapterChange = viewHolder;
           /* TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[1];
            for (int z = 6; z < 7; z++) {
                mPlaceAvators[0] = a.getDrawable(z);
            }*/
            viewHolder.name.setText(this.lstCompanies.get(i).getNm());
            //viewHolder.description.setText("Taxa de Entrega R$" + this.android.get(i).getTx());
            viewHolder.description.setText("R$".concat(Utils.formatToCurrency(this.lstCompanies.get(i).getTx())));
            /* viewHolder.description.setText("R$".concat(String.valueOf(this.lstCompanies.get(i).getTx())));*/
            // viewHolder.avator.setImageDrawable(mPlaceAvators[0]);
            /*nameLogo =  Utils.getNameLogo(this.lstCompanies.get(i).getIdCA());
            if(!nameLogo.equals("")) {
                int resourceId = resources.getIdentifier(nameLogo, "drawable", viewGroup.getContext().getPackageName());
                Drawable drawable = resources.getDrawable(resourceId);
                viewHolder.avator.setImageDrawable(drawable);
            }*/

            resourceId = resources.getIdentifier("h2o_4", "drawable", viewGroup.getContext().getPackageName());
            drawable = resources.getDrawable(resourceId);
            viewHolder.avator.setImageDrawable(drawable);

            if (null != this.lstCompanies.get(i).getPaymentTypeList()) {
                for (z = 0; z < this.lstCompanies.get(i).getPaymentTypeList().size(); z++) {
                    switch (this.lstCompanies.get(i).getPaymentTypeList().get(z).getIdType()) {
                        case 2:
                        case 3:
                            viewHolder.pgtCart.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            viewHolder.pgtMon.setVisibility(View.VISIBLE);
                            break;
                        default:
                            viewHolder.pgtCart.setVisibility(View.INVISIBLE);
                            viewHolder.pgtMon.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
                /*for (CompanyPaymentTypes paymentTypes : this.lstCompanies.get(i).getPaymentTypeList()) {
                    switch (paymentTypes.getIdType()) {
                        case 2:
                        case 3:
                            viewHolder.pgtCart.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            viewHolder.pgtMon.setVisibility(View.VISIBLE);
                            break;
                        default:
                            viewHolder.pgtCart.setVisibility(View.INVISIBLE);
                            viewHolder.pgtMon.setVisibility(View.INVISIBLE);
                            break;
                    }
                }*/
            }
            if ((null != this.lstCompanies.get(i).getAv()) && (this.lstCompanies.get(i).getVt().intValue() > 0)) {
                viewHolder.avaliation.setText(this.lstCompanies.get(i).getAv().toString());
                viewHolder.avaliation.setVisibility(View.VISIBLE);
                viewHolder.stars.setVisibility(View.VISIBLE);
            } else {
                this.lstCompanies.get(i).setAv(BigDecimal.ZERO);
                viewHolder.avaliation.setText("N/A");
                //viewHolder.avaliation.setVisibility(View.INVISIBLE);
                viewHolder.avaliation.setVisibility(View.VISIBLE);
                viewHolder.stars.setVisibility(View.INVISIBLE);
            }


            /*  Resources res = getResources();
                String mDrawableName = "h2o_4";
                int resourceId = res.getIdentifier(mDrawableName , "drawable", getPackageName());
                Drawable drawable = res.getDrawable(resourceId);
                img.setImageDrawable(drawable );*/

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cartBusiness.getItens().isEmpty()) {
                        // int id = lstCompanies.get(i).getId();
                        //Toast.makeText(viewGroup.getContext(), "ID Empresa:".concat(" "+id), Toast.LENGTH_SHORT).show();
                        /*//LIMPANDO O VALOR DA ENTREGA;
                        cartBusiness.setValorEntrega(BigDecimal.ZERO);*/
                        //ALTERANDO O VALOR DA ENTREGA;
                        cartBusiness.setValorEntrega(lstCompanies.get(i).getTx());
                        cartBusiness.setNameCompany(lstCompanies.get(i).getNm());
                        cartBusiness.setIdCompany(lstCompanies.get(i).getId());
                        cartBusiness.setPaymentTypeList(lstCompanies.get(i).getPaymentTypeList());
                        Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                       /* myIntent.putExtra("MyNameDist", lstCompanies.get(i).getNm());
                        myIntent.putExtra("idCompany", lstCompanies.get(i).getId());
                      //  myIntent.putExtra("valorEntrega", lstCompanies.get(i).getTx());*/
                        viewGroup.getContext().startActivity(myIntent);
                    } else if (lstCompanies.get(i).getId() == cartBusiness.getItens().get(0).getProduct().getIdC()) {
                        //ALTERANDO O VALOR DA ENTREGA;
                        cartBusiness.setValorEntrega(lstCompanies.get(i).getTx());
                        cartBusiness.setNameCompany(lstCompanies.get(i).getNm());
                        cartBusiness.setIdCompany(lstCompanies.get(i).getId());
                        cartBusiness.setPaymentTypeList(lstCompanies.get(i).getPaymentTypeList());
                        Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                        viewGroup.getContext().startActivity(myIntent);
                    } else {
                        showAlert(lstCompanies.get(i));
                    }
                }
            });
        } else {
            //Toast.makeText(viewGroup.getContext(), "Nenhuma Distribuídora localizada!", Toast.LENGTH_LONG).show();
        }
    }

    public void notifyAdapterDataSetChanged(final ArrayList<Company> lstComp) {
        //notifyDataSetChanged();
        if (lstComp.size() > 0) {
            //resources = viewGroup.getContext().getResources();
            //int resourceId = resources.getIdentifier("h2o_4", "drawable", viewGroup.getContext().getPackageName());
            //Drawable drawable = resources.getDrawable(resourceId);
            vhAdapterChange = new ViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
            for (z = 0; z < lstComp.size(); z++) {
               // vhAdapterChange.name.setText(lstComp.get(z).getNm());
                //vhAdapterChange.description.setText("R$".concat(Utils.formatToCurrency(lstComp.get(z).getTx())));
               // vhAdapterChange.avator.setImageDrawable(drawable);
                if (null != lstComp.get(z).getPaymentTypeList()) {
                    for (CompanyPaymentTypes paymentTypes : lstComp.get(z).getPaymentTypeList()) {
                        switch (paymentTypes.getIdType()) {
                            case 2:
                            case 3:
                                vhAdapterChange.pgtCart.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                vhAdapterChange.pgtMon.setVisibility(View.VISIBLE);
                                break;
                            default:
                                vhAdapterChange.pgtCart.setVisibility(View.INVISIBLE);
                                vhAdapterChange.pgtMon.setVisibility(View.INVISIBLE);
                                break;
                        }
                    }
                }
                if ((null != lstComp.get(z).getAv()) && (lstComp.get(z).getVt() != BigDecimal.ZERO)) {
                    vhAdapterChange.avaliation.setText(lstComp.get(z).getAv().toString());
                    vhAdapterChange.avaliation.setVisibility(View.VISIBLE);
                    vhAdapterChange.stars.setVisibility(View.VISIBLE);
                } else {
                    lstComp.get(z).setAv(BigDecimal.ZERO);
                    vhAdapterChange.avaliation.setText("");
                    //vhAdapterChange.avaliation.setText("N/A");
                    vhAdapterChange.avaliation.setVisibility(View.VISIBLE);
                    vhAdapterChange.stars.setVisibility(View.INVISIBLE);
                }
                /*vhAdapterChange.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cartBusiness.getItens().isEmpty()) {
                            cartBusiness.setValorEntrega(lstComp.get(z).getTx());
                            cartBusiness.setNameCompany(lstComp.get(z).getNm());
                            cartBusiness.setIdCompany(lstComp.get(z).getId());
                            cartBusiness.setPaymentTypeList(lstComp.get(z).getPaymentTypeList());
                            Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                            viewGroup.getContext().startActivity(myIntent);
                        } else if (lstComp.get(z).getId() == cartBusiness.getItens().get(0).getProduct().getIdC()) {
                            //ALTERANDO O VALOR DA ENTREGA;
                            cartBusiness.setValorEntrega(lstComp.get(z).getTx());
                            cartBusiness.setNameCompany(lstComp.get(z).getNm());
                            cartBusiness.setIdCompany(lstComp.get(z).getId());
                            cartBusiness.setPaymentTypeList(lstComp.get(z).getPaymentTypeList());
                            Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                            viewGroup.getContext().startActivity(myIntent);
                        } else {
                            showAlert(lstComp.get(z));
                        }
                    }
                });*/
            }
        }

    }


    // @Override
   // public void notifyDataSetChanged() {
        /*sort(new Comparator<IceContact>() {
            @Override
            public int compare(IceContact iceContact, IceContact iceContact2) {
                return iceContact.compareTo(iceContact2);
            }
        });*/
    //    super.notifyDataSetChanged();
  //  }

    private void showAlert(final Company company) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(viewGroup.getContext());
        dialog.setTitle("Atenção!")
                .setMessage("Não é possível comprar itens de outra distribuidora, é necessário remover os itens!\nDeseja esvaziar o carrinho?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        cartBusiness.clearCart();
                        //ALTERANDO O VALOR DA ENTREGA;
                        cartBusiness.setValorEntrega(company.getTx());
                        cartBusiness.setNameCompany(company.getNm());
                        cartBusiness.setIdCompany(company.getId());
                        cartBusiness.setPaymentTypeList(company.getPaymentTypeList());
                        //LIMPANDO O CUPOM DE DESCONTO
                        cartBusiness.setValorDescontoCupom(BigDecimal.ZERO);
                        Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                       /* myIntent.putExtra("MyNameDist", lstCompanies.get(i).getNm());
                        myIntent.putExtra("idCompany", lstCompanies.get(i).getId());
                      //  myIntent.putExtra("valorEntrega", lstCompanies.get(i).getTx());*/
                        viewGroup.getContext().startActivity(myIntent);
                        /*//LIMPANDO O VALOR DA ENTREGA;
                        cartBusiness.setValorEntrega(BigDecimal.ZERO);*/
                        //ALTERANDO O VALOR DA ENTREGA;
                      //  cartBusiness.setValorEntrega(company.getTx());

                     //   Intent myIntent = new Intent(viewGroup.getContext(), ProductsActivity.class);
                        myIntent.putExtra("MyNameDist", company.getNm());
                        myIntent.putExtra("idCompany", company.getId());
                        // myIntent.putExtra("valorEntrega", company.getTx());
                        viewGroup.getContext().startActivity(myIntent);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        int ret;
        if (lstCompanies != null) {
            ret = lstCompanies.size();
        } else {
            ret = 0;
        }
        return ret;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView avator;
        private TextView name;
        private TextView description;
        private TextView avaliation;
        private ImageView stars;
        private ImageView pgtCart;
        private ImageView pgtMon;

        private ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = itemView.findViewById(R.id.list_avatar);
            name = itemView.findViewById(R.id.list_title);
            description = itemView.findViewById(R.id.list_desc);
            avaliation = itemView.findViewById(R.id.txt_nota);
            stars = itemView.findViewById(R.id.imgNota);
            pgtCart = itemView.findViewById(R.id.imgPgtoCart);
            pgtMon = itemView.findViewById(R.id.imgPgtoMoney);
        }
    }
}
