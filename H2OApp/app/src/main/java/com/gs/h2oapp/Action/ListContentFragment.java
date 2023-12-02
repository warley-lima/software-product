package com.gs.h2oapp.Action;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gs.h2oapp.Adapter.CompanyAdapter;
import com.gs.h2oapp.Entity.Company;
import com.gs.h2oapp.R;
import com.gs.h2oapp.Utils.Configuration;
import com.gs.h2oapp.Utils.InitializerApi;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Provides UI for the view with List.
 */
public class ListContentFragment extends Fragment {
    private static ArrayList<Company> lstProd;
    private RecyclerView recyclerView;
    private CompanyAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        lstProd = new ArrayList<>();
        initViews(inflater, container, savedInstanceState);
        return recyclerView;
    }

    private void initViews(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getActivity().setTitle("Distribuidoras");
        getCompaniesServer();
    }

    private void getCompaniesServer() {
        lstProd = null;
        Call<ArrayList<Company>> call =   new InitializerApi(Configuration.getBaseUrl()).getCompanyService().loadCompaniesTeste("-23.5432742","-46.6414281", "SP");
        call.enqueue(new Callback<ArrayList<Company>>() {
            @Override
            public void onResponse(Call<ArrayList<Company>> call, Response<ArrayList<Company>> response) {
                Log.i("onResponse chamado", "onResponse: " + response.body());
                lstProd = response.body();
                adapter = new CompanyAdapter(lstProd);
                recyclerView.setAdapter(adapter);
                if (lstProd.size() > 0) {
                    for (Company p : lstProd) {
                        System.out.println(p.getId() +" " + p.getNm() +" R$" +p.getTx());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Company>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getCause().getMessage());
            }
        });
    }

}
