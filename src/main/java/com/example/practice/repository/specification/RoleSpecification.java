package com.example.practice.repository.specification;

import com.example.practice.model.Role;
import org.springframework.data.jpa.domain.Specification;

public class RoleSpecification {
    public static Specification<Role> matchId(int id) {
        return (userRoot, cq, cb) -> {

            if (id > 0) {
                return cb.equal(userRoot.get("id"), id);
            }
            return cb.conjunction();

        };
    }
}
