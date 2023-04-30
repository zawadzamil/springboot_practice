package com.example.practice.service;

import com.example.practice.dto.RoleDTO;
import com.example.practice.exceptions.ResourceAlreadyExistException;
import com.example.practice.exceptions.ResourceNotFoundException;
import com.example.practice.model.Role;
import com.example.practice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role saveAndUpdate(RoleDTO roleDTO) {
        Role existingRole = roleRepository.findByAlias(roleDTO.getAlias());

        if (existingRole != null) {
            throw new ResourceAlreadyExistException("Role already exists");
        }

        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);

        return roleRepository.save(role);
    }

    public Role getOne(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found."));
    }

    public Page<Role> getAll(int id, PageRequest pageRequest) {
//        Specification<Role> matchId = RoleSpecification.matchId(id);
//        Specification<Role> specification = Specification.where(matchId);

        return roleRepository.findAll(pageRequest);
    }

    public Role updateRole(int id, Role role) {
        return roleRepository.findById(id).map(item -> {
            item.setAlias(role.getAlias());
            item.setPermissionString(role.getPermissionString());

            return roleRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
    }

    public String deleteRole(int id) {
        Role deletedRole = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
        roleRepository.deleteById(id);
        return "Role Deleted Successfully";
    }
}
