package com.milkFirst.MilkFirst_Complaint_Management_System.service;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Admin;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.UserPrincipal;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Users;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.AdminRepo;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);

        if (user != null) {
            return new UserPrincipal(user);
        }
        Admin admin =adminRepo.findByUsername(username);
        System.out.println("Admin is >>"+admin);
        if(admin!=null){
            return new User(admin.getUsername(), admin.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        throw new UsernameNotFoundException("User or Admin not found");
    }
}
