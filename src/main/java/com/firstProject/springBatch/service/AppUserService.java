package com.firstProject.springBatch.service;

import com.firstProject.springBatch.dao.RoleRepo;
import com.firstProject.springBatch.dao.StudentRepo;
import com.firstProject.springBatch.dao.UserRepo;
import com.firstProject.springBatch.domain.AppUser;
import com.firstProject.springBatch.domain.Role;
import com.firstProject.springBatch.domain.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if(user == null){
            log.error("user can not be found sorry!");
            throw new UsernameNotFoundException("user name not found!");
        } else {
            log.info("you are logged in successfully");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(user.getUsername(),user.getPassword(),authorities);
    }

    public Role saveRole(Role role_user) {
        return roleRepo.save(role_user);
    }

    public AppUser saveUser(AppUser appUser) {
        return userRepo.save(appUser);
    }


}
