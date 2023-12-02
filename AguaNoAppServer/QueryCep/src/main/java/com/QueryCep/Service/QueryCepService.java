package com.QueryCep.Service;


import com.QueryCep.Business.QueryBusiness;
import com.QueryCep.WSCorreios.EnderecoERP;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("/query")
public class QueryCepService {
    private final QueryBusiness queryBusiness;
    @Inject
    public QueryCepService(QueryBusiness queryBusiness) {
        this.queryBusiness = queryBusiness;
    }

    @GET
    @Path("/cep")
    @Produces(MediaType.APPLICATION_JSON)
    public EnderecoERP getProductsById(@QueryParam("c") String cep) {
        EnderecoERP ret = null;
        try {
            ret =  queryBusiness.getAdressByCep(cep);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }



}
