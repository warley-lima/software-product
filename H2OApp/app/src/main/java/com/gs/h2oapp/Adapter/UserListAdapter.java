package com.gs.h2oapp.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gs.h2oapp.Entity.User;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.ItemClickListener;

import java.util.List;

/**
 * Created by Warley Lima on
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{
    private List<User> userList;
    private ItemClickListener clickListener;

    public UserListAdapter(List<User> android) {
        this.userList = android;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.content_user_list, vg, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        User user;
        if (userList.size() > 0) {
            user = userList.get(i);
            viewHolder.name.setText(user.getName());
            viewHolder.fone.setText(user.getFone());

            final LinearLayout divUpdtUser = viewHolder.itemView.findViewById(R.id.div_update_user);
            if(null != divUpdtUser){
                divUpdtUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null){
                            clickListener.onClick(divUpdtUser, i);
                        }
                    }
                });
            }
            final ImageButton btnUpdtUser = viewHolder.itemView.findViewById(R.id.btnUpdateUser);
            if(null != btnUpdtUser){
                btnUpdtUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (clickListener != null) {
                            clickListener.onClick(btnUpdtUser, i);
                        }
                    }
                });
            }
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        int ret;
        if(userList != null){
            ret = userList.size();
        }
        else{
            ret = 0;
        }
        return ret;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView fone;
        private ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_item_user);
            fone =  itemView.findViewById(R.id.list_fone);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
        }
    }
}
