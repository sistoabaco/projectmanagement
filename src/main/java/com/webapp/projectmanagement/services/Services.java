package com.webapp.projectmanagement.services;

import com.webapp.projectmanagement.model.User;
import com.webapp.projectmanagement.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class Services implements UserDetailsService{
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepo.findByUsername(username);

         if(user == null) {
             log.error("error!!! User not found!");
             throw  new UsernameNotFoundException("error!!! User not found!");
         }

         log.info("Sucess!!! User found in the database: {}", username);

         Collection <SimpleGrantedAuthority> authorities = new ArrayList<>();
         user.getRole().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getDescription()));
         });
         return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }
}
