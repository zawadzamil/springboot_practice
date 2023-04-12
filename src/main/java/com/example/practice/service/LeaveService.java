package com.example.practice.service;

import com.example.practice.dto.LeaveDTO;
import com.example.practice.model.Leave;
import com.example.practice.repository.LeaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;

    public Leave create (LeaveDTO leaveDTO){
       Leave leave = new Leave();
        BeanUtils.copyProperties(leaveDTO,leave);
        return leaveRepository.save(leave);
    }
}
