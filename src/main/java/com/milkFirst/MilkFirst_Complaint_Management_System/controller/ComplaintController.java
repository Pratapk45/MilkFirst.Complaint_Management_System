package com.milkFirst.MilkFirst_Complaint_Management_System.controller;

import com.milkFirst.MilkFirst_Complaint_Management_System.dto.ComplaintRequestDto;
import com.milkFirst.MilkFirst_Complaint_Management_System.dto.ComplaintResponseDto;
import com.milkFirst.MilkFirst_Complaint_Management_System.entity.ComplaintStatus;
import com.milkFirst.MilkFirst_Complaint_Management_System.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/add")
    public ResponseEntity<ComplaintResponseDto> create(@RequestBody ComplaintRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.createComplaint(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ComplaintResponseDto>> getAll() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintResponseDto> updateStatus( @PathVariable Long id, @RequestParam ComplaintStatus status) {
        return ResponseEntity.ok(complaintService.updateStatus(id, status));
    }

    @GetMapping("/status-count")
    public ResponseEntity<Map<ComplaintStatus, Long>> getStatusCount() {
        return ResponseEntity.ok(complaintService.getStatusCount());
    }
}
