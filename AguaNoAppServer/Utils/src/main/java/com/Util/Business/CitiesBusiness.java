package com.Util.Business;

import com.Util.Model.Cidade;

public interface CitiesBusiness {

    Cidade getCityByName (String name, String siglaUF);
}
