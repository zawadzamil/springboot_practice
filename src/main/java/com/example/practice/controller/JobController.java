package com.example.practice.controller;

import com.example.practice.dto.JobDTO;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Job;
import com.example.practice.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody @Valid JobDTO jobDTO){
       try{
           return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                   "message","Job created successfully",
                   "data", jobService.create(jobDTO)
           ));
       }
       catch (Exception e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                   "message","Slug must be unique"
           ));
       }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0")int id,
                                    @RequestParam(value = "page", defaultValue ="0")int page,
                                    @RequestParam(value = "size", defaultValue = "10")int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAll(id, pageRequest,false));
    }

    @GetMapping("/private")
    public ResponseEntity<?> getAllPrivate(@RequestParam(value = "id", defaultValue = "0")int id,
                                    @RequestParam(value = "page", defaultValue ="0")int page,
                                    @RequestParam(value = "size", defaultValue = "10")int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAll(id, pageRequest,true));
    }

    @PutMapping("/")
    public ResponseEntity<?> update (@RequestParam("id") int id, @RequestBody() Job job){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Job updated successfully.",
                    "data", jobService.update(id, job)
            ));
        }
        catch (Exception e){
            if(e instanceof ResourceNotFoundException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "message", e.getMessage()
                ));
            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                        "message", "Slug must be unique"
                ));
            }
        }
    }

    @PutMapping("/active")
    public ResponseEntity<?> activate(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.activation(id, true)
        ));
    }

    @PutMapping("/deactive")
    public ResponseEntity<?> deactivate(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.activation(id, false)
        ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message",jobService.delete(id)
        ));
    }

}
