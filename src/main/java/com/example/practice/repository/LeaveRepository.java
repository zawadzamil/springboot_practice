package com.example.practice.repository;

import com.example.practice.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LeaveRepository  extends JpaRepository<Leave,Integer> {
}
