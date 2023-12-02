package com.User.Business;


import com.User.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserBusiness {
     void save(User brand);
     void update(User brand);
     void delete(long idProd);
    // void delete(User idProd);
     Page<User> getUsersByIdCompany(long idCompany, Pageable pageable);
     Page<User> getUserSearchByIdCompany(long idCompany, Pageable pageable, String name);
     User getUser(long idProdct);
     User getUserByMail(String userMail);

}
