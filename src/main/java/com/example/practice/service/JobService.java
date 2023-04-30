package com.example.practice.service;

import com.example.practice.dto.JobDTO;
import com.example.practice.exceptions.ResourceAlreadyExistException;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Job;
import com.example.practice.model.JobCategory;
import com.example.practice.repository.JobCategoryRepository;
import com.example.practice.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobCategoryRepository jobCategoryRepository;

    public Job create(JobDTO jobDTO) {
        Job existingJob = jobRepository.findBySlug(jobDTO.getSlug());

        if(existingJob != null){
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        Job job = new Job();
        BeanUtils.copyProperties(jobDTO,job);
        if(jobDTO.getCategory() != 0){
            JobCategory jobCategory = jobCategoryRepository.findById(jobDTO.getCategory()).orElseThrow(()-> new ResourceNotFoundException("Job category not found"));
            job.setCategory(jobCategory);
        }
        return jobRepository.save(job);
    }

    public Page<Job> getAll (int id, PageRequest pageRequest, boolean isPrivate){
       if (isPrivate){
           return jobRepository.findAll(pageRequest);
       }
       else{
           return jobRepository.findByActive(true,pageRequest);
       }
    }

    public Job update(int id,Job job){

        Job existingJob = jobRepository.findBySlug(job.getSlug());

        if(existingJob != null && existingJob.getId() != id){
            throw new ResourceAlreadyExistException("Slug must be unique");
        }

        return jobRepository.findById(id).map(item ->{
            item.setTitle(job.getTitle());
            item.setSlug(job.getSlug());
            item.setDescription(job.getDescription());
            item.setDeadline(job.getDeadline());
            item.setNature(job.getNature());
            item.setType(job.getType());
            item.setNoOfVacancies(job.getNoOfVacancies());
            item.setExperience(job.getExperience());

            return jobRepository.save(item);
        }).orElseThrow(()-> new ResourceNotFoundException("Job not found."));
    }

    public String activation(int id, boolean active) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Job not found."));

        job.setActive(active);
       jobRepository.save(job);

       String activation = active? "activated" : "deactivated";
       return "Job "+ activation +" successfully.";
    }

    public String delete(int id) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Job not found"));

        jobRepository.deleteById(id);

        return "Job deleted successfully.";
    }
}
