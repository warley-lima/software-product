package com.Coupons.Business;

import com.Coupons.Model.Coupons;
import com.Coupons.Repository.CouponsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CouponBusinessImp implements CouponBusiness {

    private final CouponsRepository repository;

    @Inject
    public CouponBusinessImp(CouponsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(Coupons user) {
        repository.save(user);
    }

    @Transactional
    @Override
    public void update(Coupons cupom) {
        /*Coupons u = repository.findById(cupom.getId());
        u.setValor(cupom.getValor());
        u.setDataInicio(cupom.getDataInicio());
        u.setDataFim(cupom.getDataFim());
        repository.saveAndFlush(u);*/
        repository.saveAndFlush(cupom);
    }

    @Transactional
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Coupons> getCouponsByIdCompany(long idCompany, Pageable pageable) {
        return repository.findByIdCompanyOrderById(idCompany, pageable);
    }

    @Override
    public Page<Coupons> getCouponSearchByIdCompany(long idCompany, Pageable pageable, String name) {
        return repository.findByIdCompanyAndValorContainingIgnoreCase(idCompany, name, pageable);
    }

    @Override
    public Coupons getCoupon(long id) {
        return repository.findById(id);
    }

    @Override
    public Coupons getCoupon(long idCompany, String name, Date data, Date dataF) {
        return repository.findByIdCompanyAndValorAndDataInicioBeforeAndDataFimAfter(idCompany,name,data,dataF);
    }

}
