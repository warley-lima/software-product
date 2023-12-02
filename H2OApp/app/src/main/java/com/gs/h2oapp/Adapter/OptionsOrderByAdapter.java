package com.gs.h2oapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.OptionsCheckBoxArray;

/**
 * Created by Warley Lima
 */
public class OptionsOrderByAdapter extends BaseAdapter {

    private Activity mContext;
    private LayoutInflater layout;
    private int idClasse;
    public OptionsOrderByAdapter(Activity context, int tipo){
        this.mContext = context;
       // this.layout = lv;
        this.idClasse = tipo;
    }

    @Override
    public int getCount() {
        int ret;
        switch (idClasse){
            case 1:
                ret = OptionsCheckBoxArray.COMPANIES_OPTIONS_ORDERBY.length;
                break;
            case 2:
                ret = OptionsCheckBoxArray.MARCAS_OPTIONS_ORDERBY.length;
                break;
            case 3:
                ret = OptionsCheckBoxArray.LITROS_OPTIONS_ORDERBY.length;
                break;
            default:
                ret = 0;
                break;
        }
        return ret;
    }

    @Override
    public String getItem(int position) {
        //return Cheeses.CHEESES[position];
        String ret;
        switch (idClasse){
            case 1:
                ret = OptionsCheckBoxArray.COMPANIES_OPTIONS_ORDERBY[position];
                break;
            case 2:
                ret = OptionsCheckBoxArray.MARCAS_OPTIONS_ORDERBY[position];
                break;
            case 3:
                ret = OptionsCheckBoxArray.LITROS_OPTIONS_ORDERBY[position];
                break;
            default:
                ret = "";
                break;
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        //return Cheeses.CHEESES[position].hashCode();
        long ret;
        switch (idClasse){
            case 1:
                ret = OptionsCheckBoxArray.COMPANIES_OPTIONS_ORDERBY[position].hashCode();
                break;
            case 2:
                ret = OptionsCheckBoxArray.MARCAS_OPTIONS_ORDERBY[position].hashCode();
                break;
            case 3:
                ret = OptionsCheckBoxArray.LITROS_OPTIONS_ORDERBY[position].hashCode();
                break;
            default:
                ret = 0;
                break;
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
                .setText(getItem(position));
        return convertView;
    }
}
