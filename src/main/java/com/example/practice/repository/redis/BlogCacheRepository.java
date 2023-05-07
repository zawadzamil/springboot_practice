package com.example.practice.repository.redis;

import com.example.practice.model.redis.BlogCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCacheRepository extends CrudRepository<BlogCache,Integer> {
}
