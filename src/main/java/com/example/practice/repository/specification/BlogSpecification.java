package com.example.practice.repository.specification;

import com.example.practice.enums.StatusEnum;
import com.example.practice.model.Blog;
import com.example.practice.model.BlogCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

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

    public static Specification<Blog> checkByTags(List<String> tags){
        return (blogRoot, criteriaQuery, criteriaBuilder)-> {
            if (tags == null || tags.size() == 0) {
                return criteriaBuilder.conjunction();
            } else {
                List<Predicate> predicates = new ArrayList<>();
                for (String tag : tags) {
                    predicates.add(criteriaBuilder.isMember(tag, blogRoot.get("tags")));
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    public static Specification<Blog> checkByCategory(String category){
        return (blogRoot, criteriaQuery, criteriaBuilder)->{
            if (StringUtils.isEmpty(category)) {
                return criteriaBuilder.conjunction();
            } else {
                Path<BlogCategory> category1 = blogRoot.get("category");
                return  criteriaBuilder.equal(category1.get("name"),category);
            }
        };
    }
}
