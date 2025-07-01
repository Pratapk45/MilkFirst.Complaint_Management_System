package com.milkFirst.MilkFirst_Complaint_Management_System.repository;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Complaint;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Long> {

      Long countByStatus(ComplaintStatus s);
}
