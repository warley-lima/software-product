package com.gs.h2oapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gs.h2oapp.Entity.Brand;
import com.gs.h2oapp.R;

import java.util.List;

/**
 * Created by Warley Lima
 */
public class BrandAdapter  extends BaseAdapter {
    private List<Brand> lstBrands;
    private Activity mContext;

    public BrandAdapter(Activity mContext, List<Brand> lstBran) {
        this.mContext = mContext;
        this.lstBrands = lstBran;
    }

    @Override
    public int getCount() {
        int ret;
        if(lstBrands != null){
            ret = lstBrands.size();
        }
        else{
            ret = 0;
        }
        return ret;
    }

    @Override
    public Brand getItem(int position) {
        if(lstBrands != null){
            return lstBrands.get(position);
        }
        else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        int ret;
        if(lstBrands != null){
            ret = lstBrands.get(position).getIdBrand();
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
                .setText(getItem(position).getBrandName());
        return convertView;
    }
}
