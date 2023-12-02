package com.Rule.Repository;

import com.Rule.Model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface RuleRepository extends JpaRepository<Rule,Long>{
    //@Async
    //User findByUserNameAndPassword(String username, String password);
   /* @Async
    @Query("delete from Rule p where p.idUserFK =?1 ")
  //  Integer deleteAllByIdUserFK(long idUser);
    Integer deleteAllRulersUser(long idUser);*/
    @Modifying
    @Transactional
    @Query("delete from Rule p where p.idUserFK =?1 ")
    void deleteByIdUserFK(long idUser);

}
