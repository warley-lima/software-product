package com.gs.h2oapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gs.h2oapp.Entity.Liters;
import com.gs.h2oapp.R;

import java.util.List;

/**
 * Created by Warley Lima
 */
public class LitersAdapter extends BaseAdapter {
    private List<Liters> lstLiters;
    private Activity mContext;

    public LitersAdapter(Activity mContext, List<Liters> lstBran) {
        this.mContext = mContext;
        this.lstLiters = lstBran;
    }

    @Override
    public int getCount() {
        int ret;
        if(lstLiters != null){
            ret = lstLiters.size();
        }
        else{
            ret = 0;
        }
        return ret;
    }

    @Override
    public Liters getItem(int position) {
        if(lstLiters != null){
            return lstLiters.get(position);
        }
        else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        int ret;
        if(lstLiters != null){
            ret = lstLiters.get(position).getIdLiters();
        }
        else{
            ret = 0;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            LayoutInflater lv = mContext.getLayoutInflater();
            convertView = lv.inflate(R.layout.list_options_orderby, container, false);
        }

        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(getItem(position).getLitersName());
        return convertView;
    }
}
