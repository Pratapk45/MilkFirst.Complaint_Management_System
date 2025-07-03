package com.milkFirst.MilkFirst_Complaint_Management_System.repository;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin , Long> {
    Admin findByUsername(String username);
}
