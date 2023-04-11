package com.example.practice.repository;

import com.example.practice.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    Page<Job> findByActive(boolean b, PageRequest pageRequest);
}
