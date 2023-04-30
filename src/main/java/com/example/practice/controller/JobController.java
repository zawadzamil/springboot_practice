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
    public ResponseEntity<?> create(@RequestBody @Valid JobDTO jobDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.create(jobDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") int id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAll(id, pageRequest, false));
    }

    @GetMapping("/private")
    public ResponseEntity<?> getAllPrivate(@RequestParam(value = "id", defaultValue = "0") int id,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAll(id, pageRequest, true));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody() Job job) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.update(id, job));
    }

    @PutMapping("/active")
    public ResponseEntity<?> activate(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.activation(id, true)
        ));
    }

    @PutMapping("/deactive")
    public ResponseEntity<?> deactivate(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.activation(id, false)
        ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", jobService.delete(id)
        ));
    }

}
