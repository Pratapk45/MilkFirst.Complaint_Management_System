package com.milkFirst.MilkFirst_Complaint_Management_System.service;

import com.milkFirst.MilkFirst_Complaint_Management_System.dto.ComplaintRequestDto;
import com.milkFirst.MilkFirst_Complaint_Management_System.dto.ComplaintResponseDto;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Admin;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Complaint;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.ComplaintStatus;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Users;
import com.milkFirst.MilkFirst_Complaint_Management_System.exception.ComplaintNotFoundException;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.AdminRepo;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.ComplaintRepo;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private  UserRepo userRepo;
     @PreAuthorize("hasRole('USER')")
    public ComplaintResponseDto createComplaint(ComplaintRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // this is the logged-in username
        Users user = userRepo.findByUsername(username);

        Complaint complaint = new Complaint();
        complaint.setCustomerName(user.getUsername());
        complaint.setIssueDescription(dto.getIssueDescription());
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setRaisedOn(LocalDateTime.now());

        Complaint saved = complaintRepo.save(complaint);
        return toDTO(saved);
    }

    public List<ComplaintResponseDto> getAllComplaints() {
        return complaintRepo.findAll().stream().map(this::toDTO).toList();
    }

    public ComplaintResponseDto updateStatus(Long id, ComplaintStatus status) {
        Complaint complaint = complaintRepo.findById(id)
                .orElseThrow(() -> new ComplaintNotFoundException("Complaint not found"));

        complaint.setStatus(status);
        if (status == ComplaintStatus.RESOLVED) {
            complaint.setResolvedOn(LocalDateTime.now());
        }

        Complaint updated = complaintRepo.save(complaint);
        return toDTO(updated);
    }

    public Map<ComplaintStatus, Long> getStatusCount() {

        Map<ComplaintStatus, Long> countMap = new HashMap<>();
        for (ComplaintStatus s : ComplaintStatus.values()) {
            countMap.put(s, complaintRepo.countByStatus(s));
        }
        return countMap;
    }

    private ComplaintResponseDto toDTO(Complaint complaint) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // this is the logged-in username
        Users user = userRepo.findByUsername(username);

        ComplaintResponseDto dto = new ComplaintResponseDto();
        dto.setComplaintId(complaint.getComplaintId());
        dto.setCustomerName(complaint.getCustomerName());
        dto.setIssueDescription(complaint.getIssueDescription());
        dto.setStatus(complaint.getStatus());
        dto.setRaisedOn(complaint.getRaisedOn());
        dto.setResolvedOn(complaint.getResolvedOn());
        if (complaint.getResolvedOn() != null) {
            long l=Duration.between(complaint.getRaisedOn(), complaint.getResolvedOn()).toMinutes();
            dto.setDuration((l/60)+" Hours and "+(l%60)+" Minutes");
        }

        return dto;
    }
}
