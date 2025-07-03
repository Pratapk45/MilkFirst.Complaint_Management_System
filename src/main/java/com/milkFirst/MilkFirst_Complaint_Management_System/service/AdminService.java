package com.milkFirst.MilkFirst_Complaint_Management_System.service;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Admin;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Admin registerAdmin(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }
}
