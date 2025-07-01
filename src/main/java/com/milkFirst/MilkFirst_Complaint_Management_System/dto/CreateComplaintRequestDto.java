package com.milkFirst.MilkFirst_Complaint_Management_System.dto;

import lombok.Data;

@Data
public class CreateComplaintRequestDto {

    private String customerName;
    private String issueDescription;
}
