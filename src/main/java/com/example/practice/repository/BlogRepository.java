package com.example.practice.repository;

import com.example.practice.enums.StatusEnum;
import com.example.practice.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Page<Blog> findByStatus(StatusEnum statusEnum, PageRequest pageRequest);

    Blog findBySlug(String slug);
}
