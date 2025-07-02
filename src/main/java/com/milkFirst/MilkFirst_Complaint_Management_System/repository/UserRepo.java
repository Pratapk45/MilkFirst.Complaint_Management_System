package com.milkFirst.MilkFirst_Complaint_Management_System.repository;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users ,Long> {
    Users findByUsername(String username);
}
