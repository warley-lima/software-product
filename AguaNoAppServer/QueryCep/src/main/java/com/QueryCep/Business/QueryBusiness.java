package com.QueryCep.Business;

import com.QueryCep.WSCorreios.EnderecoERP;

public interface QueryBusiness {
     EnderecoERP getAdressByCep(String cep);

}
