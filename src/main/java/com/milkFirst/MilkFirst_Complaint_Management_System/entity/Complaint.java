package com.milkFirst.MilkFirst_Complaint_Management_System.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    private String customerName;

    private String issueDescription;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    private LocalDateTime raisedOn;

    private LocalDateTime resolvedOn;
}
