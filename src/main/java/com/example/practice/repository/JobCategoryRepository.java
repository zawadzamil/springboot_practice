package com.example.practice.repository;

import com.example.practice.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory,Integer> {

    JobCategory findBySlug(String slug);
}
