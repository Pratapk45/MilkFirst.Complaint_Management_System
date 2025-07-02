package com.milkFirst.MilkFirst_Complaint_Management_System.dto;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.ComplaintStatus;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class ComplaintResponseDto {
    private Long complaintId;
    private String customerName;
    private  Long mobileNo ;
    private String gmail;
    private String issueDescription;
    private ComplaintStatus status;
    private LocalDateTime raisedOn;
    private LocalDateTime resolvedOn;
    private String duration;
}
