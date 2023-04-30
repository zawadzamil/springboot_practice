package com.example.practice.service;

import com.example.practice.dto.JobCategoryDTO;
import com.example.practice.exceptions.ResourceAlreadyExistException;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.JobCategory;
import com.example.practice.repository.JobCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobCategoryService {
    private final JobCategoryRepository jobCategoryRepository;

    public JobCategory create(JobCategoryDTO jobCategoryDTO) {
        JobCategory existing = jobCategoryRepository.findBySlug(jobCategoryDTO.getSlug());
        if (existing != null) {
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        JobCategory jobCategory = new JobCategory();
        BeanUtils.copyProperties(jobCategoryDTO, jobCategory);

        return jobCategoryRepository.save(jobCategory);
    }

    public Page<JobCategory> getAll(int id, PageRequest pageRequest) {
        return jobCategoryRepository.findAll(pageRequest);
    }

    public JobCategory update(int id, JobCategory category) {
        JobCategory existing = jobCategoryRepository.findBySlug(category.getSlug());
        if (existing != null && existing.getId() != id) {
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        return jobCategoryRepository.findById(id).map(item -> {
            item.setName(category.getName());
            item.setSlug(category.getSlug());

            return jobCategoryRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Job category not found"));
    }

    public String delete(int id) {
        JobCategory category = jobCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job category not found"));

        jobCategoryRepository.deleteById(id);
        return "Job category deleted successfully";
    }
}
