package com.Util.Business;

import com.Util.Model.Cidade;
import com.Util.Repository.CitiesRepository;
import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

import javax.inject.Inject;
@Component
public class CitiesBusinessImp implements  CitiesBusiness {
    @Inject
    private CitiesRepository repository;

    @Override
    public Cidade getCityByName(String name, String siglaUF) {
        return repository.findCidadeByNameAndSigla(name, siglaUF);
    }
}
