package com.example.practice.controller;

import com.example.practice.dto.BlogDTO;
import com.example.practice.enums.StatusEnum;
import com.example.practice.model.Blog;
import com.example.practice.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
public class BlogController {

    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BlogDTO blogDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.create(blogDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id", defaultValue = "0") int id,
                                    @RequestParam(value = "title", defaultValue = "") String title,
                                    @RequestParam(value = "slug", defaultValue = "") String slug,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "tags", defaultValue = "") List<String> tags,
                                    @RequestParam(value = "category" ,defaultValue = "") String category,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll(id,
                title,
                slug,
                tags,
                category,
                pageRequest));
    }

    @GetMapping("/private")
    public ResponseEntity<?> getAllPrivate(@RequestParam(value = "id", defaultValue = "0") int id,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllPrivate(id, pageRequest));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getInfo(id));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody Blog blog) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.update(id, blog));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", blogService.delete(id)
        ));
    }

    @PutMapping("/publish")
    public ResponseEntity<?> publish (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.publish(id, StatusEnum.PUBLISHED));
    }

    @PutMapping("/unpublish")
    public ResponseEntity<?> unpublish (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", blogService.publish(id, StatusEnum.UNPUBLISHED)
        ));
    }
}
