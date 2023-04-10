package com.example.practice.controller;

import com.example.practice.dto.BlogCategoryDTO;
import com.example.practice.model.BlogCategory;
import com.example.practice.service.BlogCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/blogs/categories")
@AllArgsConstructor
public class BlogCategoryController {
    private BlogCategoryService blogCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BlogCategoryDTO blogCategoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Blog category created successfully.",
                "data", blogCategoryService.create(blogCategoryDTO)
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll (@RequestParam(value = "id", defaultValue = "0") int id,
                                     @RequestParam(value = "page" , defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(blogCategoryService.getAll(id, pageRequest));
    }


    @PutMapping("/")
    public ResponseEntity<?> update(int id, @RequestBody @Valid BlogCategory blogCategory){
      try {
          return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                  "message" , "Blog Category updated successfully",
                  "data", blogCategoryService.update(id, blogCategory)
          ));
      }
      catch (Exception e) {
          return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                  "message" , "Slug must be unique."
          ));
      }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message" , blogCategoryService.delete(id)
        ));
    }
}
