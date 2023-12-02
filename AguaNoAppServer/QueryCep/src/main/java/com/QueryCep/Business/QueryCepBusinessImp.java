package com.QueryCep.Business;


import com.QueryCep.WSCorreios.*;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class QueryCepBusinessImp implements QueryBusiness {

    @Override
    public EnderecoERP getAdressByCep(String cep) {
        EnderecoERP resp = null;
       try {
            AtendeCliente ws = new AtendeClienteService().getAtendeClientePort();
            cep = cep.replaceAll("-", "");
            if(cep.length() == 8){
                resp =  ws.consultaCEP(cep);
            }
        } catch (SQLException_Exception | SigepClienteException ex) {
            Logger.getLogger(QueryCepBusinessImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
}
