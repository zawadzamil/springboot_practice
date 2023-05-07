package com.example.practice.service;

import com.example.practice.dto.BlogDTO;
import com.example.practice.enums.StatusEnum;
import com.example.practice.exceptions.ResourceAlreadyExistException;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Blog;
import com.example.practice.model.BlogCategory;
import com.example.practice.model.redis.BlogCache;
import com.example.practice.repository.BlogCategoryRepository;
import com.example.practice.repository.BlogRepository;
import com.example.practice.repository.redis.BlogCacheRepository;
import com.example.practice.repository.specification.BlogSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@AllArgsConstructor
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogCategoryRepository blogCategoryRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final BlogCacheRepository blogCacheRepository;

    public Blog create(BlogDTO blogDTO) {
        Blog existingBlog = blogRepository.findBySlug(blogDTO.getSlug());
        if (existingBlog != null) {
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDTO, blog);

        if (blogDTO.getCategory() != 0) {
            BlogCategory category = blogCategoryRepository.findById(blogDTO.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Blog category not found"));
            blog.setCategory(category);
        }

        return blogRepository.save(blog);
    }


    public Page<Blog> getAll(int id,
                             String title,
                             String slug,
                             List<String> tags,
                             String category,
                             Date fromDate,
                             Date toDate,
                             Date upper,
                             Date lower,
                             PageRequest pageRequest) {
        Specification<Blog> matchId = BlogSpecification.matchId(id);
        Specification<Blog> status = BlogSpecification.checkByStatus(StatusEnum.PUBLISHED);
        Specification<Blog> titleSpecification = BlogSpecification.checkByTitle(title);
        Specification<Blog> slugSpecification = BlogSpecification.checkBySlug(slug);
        Specification<Blog> tagSpecification = BlogSpecification.checkByTags(tags);
        Specification<Blog> categorySpecification = BlogSpecification.checkByCategory(category);

        LocalDateTime localDateTimeFrom = LocalDateTime.ofInstant(fromDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTimeTo = LocalDateTime.ofInstant(toDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTimeUpperBound = LocalDateTime.ofInstant(upper.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTimeLowerBound = LocalDateTime.ofInstant(lower.toInstant(), ZoneId.systemDefault());

        Specification<Blog> dateRangeSpecification = BlogSpecification.checkByDateRange(localDateTimeFrom, localDateTimeTo);
        Specification<Blog> uperRangeSpecification = BlogSpecification.checkByUperRange(localDateTimeUpperBound);
        Specification<Blog> lowerrRangeSpecification = BlogSpecification.checkByLowerRange(localDateTimeLowerBound);

        Specification<Blog> specification = where(matchId)
                .and(status)
                .and(slugSpecification)
                .and(tagSpecification)
                .and(categorySpecification)
                .and(dateRangeSpecification)
                .and(uperRangeSpecification)
                .and(lowerrRangeSpecification)
                .and(titleSpecification);

        return blogRepository.findAll(specification, pageRequest);
    }

    public Page<Blog> getAllPrivate(int id, PageRequest pageRequest) {
        return blogRepository.findAll(pageRequest);
    }

    public Blog update(int id, Blog blog) {
        Blog existing = blogRepository.findBySlug(blog.getSlug());
        if (existing != null && existing.getId() != id) {
            throw new ResourceAlreadyExistException("Slug must be unique");
        }
        return blogRepository.findById(id).map(item -> {
            item.setTitle(blog.getTitle());
            item.setSlug(blog.getSlug());
            item.setTags(blog.getTags());
            item.setContent(blog.getContent());

            return blogRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Blog not found."));
    }

    public String delete(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not found."));
        blogRepository.deleteById(id);
        return "Blog deleted successfully.";
    }

    public Blog publish(int id, StatusEnum status) {

        return blogRepository.findById(id).map(item -> {
            item.setStatus(status);

            return blogRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
    }


    public Blog getInfo(int id) {
        Optional<BlogCache> blogCache = blogCacheRepository.findById(id);
        if (blogCache.isPresent()) {
            log.info("Cache Hit!");
            return blogCache.get().getBlog();
        } else {
            Blog post = blogRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
            log.info("Cache Miss!");

                BlogCache cache = new BlogCache();
                cache.setId(post.getId());
                cache.setBlog(post);
                blogCacheRepository.save(cache);

                return post;
            }
        }

    }
