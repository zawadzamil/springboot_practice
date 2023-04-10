package com.example.practice.service;

import com.example.practice.dto.BlogCategoryDTO;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.BlogCategory;
import com.example.practice.repository.BlogCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.BitSet;

@Service
@AllArgsConstructor
public class BlogCategoryService {
    private  final BlogCategoryRepository blogCategoryRepository;

    public BlogCategory create(BlogCategoryDTO blogCategoryDTO){
       BlogCategory blogCategory = new BlogCategory();
        BeanUtils.copyProperties(blogCategoryDTO, blogCategory);

        return blogCategoryRepository.save(blogCategory);
    }

    public Page<BlogCategory> getAll (int id, PageRequest pageRequest){
            return blogCategoryRepository.findAll(pageRequest);

    }

    public BlogCategory update (int id,BlogCategory category){
        return blogCategoryRepository.findById(id).map(item -> {
            item.setName(category.getName());
            item.setSlug(category.getSlug());

            return blogCategoryRepository.save(item);

        }).orElseThrow(()->new ResourceNotFoundException("Blog category not found."));
    }

    public String delete (int id) {
        BlogCategory blogCategory = blogCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Blog category not found."));
       blogCategoryRepository.deleteById(id);
       return "Blog category deleted successfully.";
    }
}
