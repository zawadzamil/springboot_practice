package com.example.practice.service;

import com.example.practice.dto.LeaveDTO;
import com.example.practice.enums.LeaveStatus;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Leave;
import com.example.practice.model.User;
import com.example.practice.repository.LeaveRepository;
import com.example.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final UserRepository userRepository;

    public Leave create (LeaveDTO leaveDTO){
       Leave leave = new Leave();
        BeanUtils.copyProperties(leaveDTO,leave);
        User user = userRepository.findById(50).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        leave.setUser(user);
        return leaveRepository.save(leave);
    }

    public Page<Leave> getAllLeave (int id, PageRequest pageRequest){
        return leaveRepository.findAll(pageRequest);
    }

    public String action(int id, LeaveStatus leaveStatus){
        Leave leave = leaveRepository.findById(id).map(item ->{
            item.setStatus(leaveStatus);

            return leaveRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Leave application not found"));

        return "Leave application "+ leaveStatus.toString().toLowerCase() + ".";

    }

    public Leave update(int id, Leave leave){

        return leaveRepository.findById(id).map(item-> {
            item.setNatureOfLeave(leave.getNatureOfLeave());
            item.setStartDate(leave.getStartDate());
            item.setEndDate(leave.getEndDate());
            item.setReason(leave.getReason());
            item.setDateOfJoining(leave.getDateOfJoining());
            item.setStatus(LeaveStatus.PENDING);

            return leaveRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Leave application not found"));
    }

    public String delete (int id) {
        Leave leave = leaveRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Leave application not found"));

        leaveRepository.deleteById(id);

        return "Leave application deleted successfully";
    }
}
