package com.Brand.Repository;

import com.Brand.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    //@Async
    //User findByUserNameAndPassword(String username, String password);
    /*@Async
    List<Brand> findAllBy();*/

}
