package com.User.Service;

import com.User.Model.User;
import com.User.Repository.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;

public class UserDetailsServiceImp implements UserDetailsService {
    @Inject
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        UserBuilder builder;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(user.getName());
            builder.password(user.getPassword());
            //builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getPerfil());
            StringBuilder scope = new StringBuilder();
            for (int i = 0; i < user.getRules().size(); i++) {
                scope.append(user.getRules().get(i).getIdModuleFK()).append(user.getRules().get(i).getName().concat(" "));
            }
            builder.authorities(scope.toString().concat(user.getPerfil()));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

}
