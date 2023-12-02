package com.Authentication.Repository;

import com.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

//import org.springframework.scheduling.annotation.Async;

@Repository
public interface LoginRepository extends JpaRepository<User,Long> {
    @Async
    User findByUserNameAndPassword(String username, String password);

    //@Async
    User findByUserName(String username);
}
