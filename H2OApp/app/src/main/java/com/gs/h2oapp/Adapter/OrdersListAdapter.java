package com.gs.h2oapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gs.h2oapp.Entity.Order;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.ItemClickListener;
import com.gs.h2oapp.Utils.Utils;

import java.util.List;

/**
 * Created by Warley Lima
 */
public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.ViewHolder>{
    private List<Order> lstOrder;
    private ItemClickListener clickListener;
    private Order order;
    public OrdersListAdapter(List<Order> o) {
        this.lstOrder = o;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup vg, int i) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.content_orders_list, vg, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (lstOrder.size() > 0) {
            //Ordernardo a lista de Pedidos do Mais Novo para o Mais Antigo;
            Utils.orderOrdersById(lstOrder);
            order = lstOrder.get(i);
            viewHolder.name.setText(order.getNameDist());
           // System.out.println("JESUS IS HOPE----->" + order.getIdOrderServer());
            viewHolder.data.setText(order.getDataPedido());
            //viewHolder.entrega.setText("R$".concat(order.getValorTaxaEntrega()));
            viewHolder.entrega.setText("R$".concat(Utils.formatToCurrency(order.getValorTaxaEntrega())));
           // viewHolder.valorTotal.setText("R$".concat(order.getValorTotal()));
            viewHolder.valorTotal.setText("R$".concat(Utils.formatToCurrency(order.getValorTotal())));
            /*final LinearLayout divRemOrder = viewHolder.itemView.findViewById(R.id.div_rem_order);
            if(null != divRemOrder){
                divRemOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null){
                            clickListener.onClick(divRemOrder, i);
                        }
                    }
                });
            }
            final ImageButton btnRemOrder = viewHolder.itemView.findViewById(R.id.btnRemOrder);
            if(null != btnRemOrder){
                btnRemOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(btnRemOrder, i);
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
        if(lstOrder != null){
            ret = lstOrder.size();
        }
        else{
            ret = 0;
        }
        return ret;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView data;
        private TextView entrega;
        private TextView valorTotal;
        private ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_company_order);
            data =  itemView.findViewById(R.id.list_data_order);
            entrega =  itemView.findViewById(R.id.list_entrega_order);
            valorTotal =  itemView.findViewById(R.id.list_total_order);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
