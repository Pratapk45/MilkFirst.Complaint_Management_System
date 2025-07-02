package com.milkFirst.MilkFirst_Complaint_Management_System.controller;

import com.milkFirst.MilkFirst_Complaint_Management_System.entity.Users;
import com.milkFirst.MilkFirst_Complaint_Management_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService ;

  @PostMapping("/register")
  public ResponseEntity<Users> addUser(@RequestBody Users user){
      return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
  }
  @GetMapping("/demo")
  public String user(){
    return "Hiii Pratap";
  }
}
