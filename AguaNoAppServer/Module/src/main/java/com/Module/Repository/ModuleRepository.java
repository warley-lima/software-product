package com.Module.Repository;

import com.Module.Model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.scheduling.annotation.Async;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long>{
    //@Async
    //User findByUserNameAndPassword(String username, String password);
}
