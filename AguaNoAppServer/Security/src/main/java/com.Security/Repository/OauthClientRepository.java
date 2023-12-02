package com.Security.Repository;

import com.Security.Model.OauthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;


@Repository
public interface OauthClientRepository extends JpaRepository<OauthClient,Long>{
   @Async
    OauthClient findByIdCompany(long idCompany);
    @Async
    OauthClient findByIdOauthClient(long idOauthClient);
}
