package com.example.practice.repository;

import com.example.practice.model.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory,Integer> {
    BlogCategory findBySlug(String slug);
}
