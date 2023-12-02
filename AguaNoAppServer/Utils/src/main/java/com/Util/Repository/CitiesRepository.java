package com.Util.Repository;

import com.Util.Model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;


@Repository
public interface CitiesRepository extends JpaRepository<Cidade,Long> {
    @Async
    Cidade findByidCity(long idCity);
    @Async
    Cidade findCidadeByNameAndSigla(String nameCity, String siglaUf);
}
