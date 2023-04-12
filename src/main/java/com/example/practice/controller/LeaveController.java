package com.example.practice.controller;

import com.example.practice.dto.LeaveDTO;
import com.example.practice.enums.LeaveStatus;
import com.example.practice.model.Leave;
import com.example.practice.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseEntity<?> getAll (@RequestParam(value = "id" ,defaultValue = "0") int id,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size" , defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(leaveService.getAllLeave(id,pageRequest));
    }

    @PostMapping("/recommend")
    public ResponseEntity<?> recommend (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , leaveService.action(id, LeaveStatus.RECOMMENDED)
        ));
    }

    @PostMapping("/not-recommend")
    public ResponseEntity<?> notRecommend (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , leaveService.action(id, LeaveStatus.NOT_RECOMMENDED)
        ));
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approve (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , leaveService.action(id, LeaveStatus.APPROVED)
        ));
    }

    @PostMapping("/disapprove")
    public ResponseEntity<?> disapprove (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , leaveService.action(id, LeaveStatus.DISAPPROVED)
        ));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancel (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , leaveService.action(id, LeaveStatus.CANCELLED)
        ));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody Leave leave){
            return  ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message" , "Leave application updated",
                    "data" , leaveService.update(id,leave)
            ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , leaveService.delete(id)
        ));
    }
}
