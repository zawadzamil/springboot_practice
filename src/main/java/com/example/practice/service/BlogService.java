package com.example.practice.service;

import com.example.practice.dto.BlogDTO;
import com.example.practice.enums.StatusEnum;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Blog;
import com.example.practice.model.BlogCategory;
import com.example.practice.repository.BlogCategoryRepository;
import com.example.practice.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogCategoryRepository blogCategoryRepository;

    public Blog create(BlogDTO blogDTO){
        Blog blog = new Blog();


        BeanUtils.copyProperties(blogDTO,blog);
        if (blogDTO.getCategory() != 0) {
            BlogCategory category = blogCategoryRepository.findById(blogDTO.getCategory()).orElseThrow(()-> new ResourceNotFoundException("Blog category not found"));
            blog.setCategory(category);
        }

        return blogRepository.save(blog);
    }

    public Page<Blog> getAll (int id, PageRequest pageRequest){
        return  blogRepository.findByStatus(StatusEnum.PUBLISHED, pageRequest);
    }

    public Page<Blog> getAllPrivate (int id, PageRequest pageRequest){
        return  blogRepository.findAll( pageRequest);
    }

    public Blog update (int id,Blog blog){
        return blogRepository.findById(id).map(item ->{
            item.setTitle(blog.getTitle());
            item.setSlug(blog.getSlug());
            item.setTags(blog.getTags());
            item.setContent(blog.getContent());

            return blogRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Blog not found."));
    }

    public String delete(int id){
        Blog blog = blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Blog not found."));
        blogRepository.deleteById(id);
        return "Blog deleted successfully.";
    }

    public String publish(int id, StatusEnum status){
       Blog  blog = blogRepository.findById(id).map(item ->{
            item.setStatus(status);

            return blogRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Blog not found"));

       return "Blog " +blog.getStatus().toString().toLowerCase() +" successfully.";
    }

    public Blog getInfo (int id){
        return blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Blog not found"));
    }

}
