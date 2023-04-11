package com.example.practice.controller;

import com.example.practice.dto.JobCategoryDTO;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.JobCategory;
import com.example.practice.service.JobCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/jobs/categories")
@AllArgsConstructor
public class JobCategoryController {

    private JobCategoryService jobCategoryService;
    @PostMapping("create")
    public ResponseEntity<?> create (@RequestBody @Valid JobCategoryDTO jobCategory){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                   "message", "Job category created successfully",
                   "data", jobCategoryService.create(jobCategory)
           ));
       }
       catch (Exception e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                   "message", "Slug must be unique."
           ));
       }
    }
@GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") int id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobCategoryService.getAll(id, pageRequest));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody JobCategory jobCategory){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message","Job Category updated successfully.",
                    "data",jobCategoryService.update(id, jobCategory)
            ));
        }
        catch (Exception e) {
           if(e instanceof ResourceNotFoundException){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                       "message",e.getMessage()
               ));
           }
           else{
               return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                       "message","Slug / Name must be unique" + e.toString()
               ));
           }
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?>  delete(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobCategoryService.delete(id)
        ));
    }
}
