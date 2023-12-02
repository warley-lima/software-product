package com.Products.Business;

import com.Products.Model.Liters;
import com.Products.Repository.LitersRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class LitersBusinessImp implements LitersBusiness {

    @Inject
    private LitersRepository repository;

    @Transactional
    @Override
    public void save(Liters module) {
        repository.save(module);
    }

    @Transactional
    @Override
    public List<Liters> getAllLiters() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Liters getliter(int idProdct) {
        return repository.getOne(idProdct);
    }


}
