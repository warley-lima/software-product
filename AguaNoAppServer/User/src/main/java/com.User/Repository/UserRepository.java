package com.User.Repository;

import com.User.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    @Async
    User findByUserNameAndPassword(String username, String password);
    @Async
    User findByUserName(String username);
    @Async
    Page<User> findByIdCompanyOrderByName(long idCompany, Pageable pageable);
    @Async
    //@Query("select p from User p where p.idCompany =?1 and p.userName like %?2% or p.name like %?2% order by p.name")
    Page<User> findByIdCompanyAndNameContainingIgnoreCase(long idCompany, String name, Pageable pageable);
    @Async
    User findById(long idUser);

   // @Async
 //   User findByUserName(String user);
   /* @Modifying
   // @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
    void update();*/


}
