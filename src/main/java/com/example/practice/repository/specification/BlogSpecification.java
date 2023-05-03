package com.example.practice.repository.specification;

import com.example.practice.enums.StatusEnum;
import com.example.practice.model.Blog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class BlogSpecification {

    public static Specification<Blog> matchId(int id){
        return (blogRoot, criteriaQuery, criteriaBuilder)->{
            if(id > 0){
                return criteriaBuilder.equal(blogRoot.get("id"), id);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Blog> checkByStatus (StatusEnum status){
        return (blogRoot, criteriaQuery, criteriaBuilder)->  (status != null) ? criteriaBuilder.equal(blogRoot.get("status"), StatusEnum.PUBLISHED) : criteriaBuilder.conjunction();
    }



    public static Specification<Blog> checkByTitle (String title) {
        return (blogRoot, criteriaQuery, criteriaBuilder)-> StringUtils.isEmpty(title) ? criteriaBuilder.conjunction() : criteriaBuilder.like(blogRoot.get("title"),"%" + title + "%");
    }
    public static Specification<Blog> checkBySlug(String slug){
        return (blogRoot, criteriaQuery,criteriaBuilder)-> StringUtils.isEmpty(slug) ? criteriaBuilder.conjunction() : criteriaBuilder.like(blogRoot.get("slug"),"%" + slug + "%");
    }

    public static Specification<Blog> checkByTags(String tag){
        return (blogRoot, criteriaQuery, criteriaBuilder)-> StringUtils.isEmpty(tag) ? criteriaBuilder.conjunction() : criteriaBuilder.isMember(tag, blogRoot.get("tags"));
    }
}