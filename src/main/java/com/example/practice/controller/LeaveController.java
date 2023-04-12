package com.example.practice.controller;

import com.example.practice.dto.LeaveDTO;
import com.example.practice.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/leave")
@AllArgsConstructor
public class LeaveController {
    private LeaveService leaveService;

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody @Valid LeaveDTO leaveDTO){
        return  ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Leave application created successfully.",
                "data", leaveService.create(leaveDTO)
        ));
    }
}
