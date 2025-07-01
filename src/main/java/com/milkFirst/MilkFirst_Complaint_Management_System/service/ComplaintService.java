package com.milkFirst.MilkFirst_Complaint_Management_System.service;

import com.milkFirst.MilkFirst_Complaint_Management_System.dto.ComplaintResponseDto;
import com.milkFirst.MilkFirst_Complaint_Management_System.dto.CreateComplaintRequestDto;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Complaint;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.ComplaintStatus;
import com.milkFirst.MilkFirst_Complaint_Management_System.exception.ComplaintNotFoundException;
import com.milkFirst.MilkFirst_Complaint_Management_System.repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ComplaintResponseDto createComplaint(CreateComplaintRequestDto dto) {
        Complaint complaint = new Complaint();
        complaint.setCustomerName(dto.getCustomerName());
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
        ComplaintResponseDto dto = new ComplaintResponseDto();
        dto.setComplaintId(complaint.getComplaintId());
        dto.setCustomerName(complaint.getCustomerName());
        dto.setIssueDescription(complaint.getIssueDescription());
        dto.setStatus(complaint.getStatus());
        dto.setRaisedOn(complaint.getRaisedOn());
        dto.setResolvedOn(complaint.getResolvedOn());

        return dto;
    }
}
