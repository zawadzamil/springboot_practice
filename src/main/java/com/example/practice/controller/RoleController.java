package com.example.practice.controller;

import com.example.practice.dto.RoleDTO;
import com.example.practice.model.Role;
import com.example.practice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid RoleDTO roleDTO){
       try{
           return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                   "message","Role Created Successfully.",
                   "data",roleService.saveAndUpdate(roleDTO)
           ));
       }
       catch (Exception exception){
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "message","Role must be unique"
        ));
       }
    }

    @GetMapping("/")
    public ResponseEntity<?> getOne(@RequestParam("id") int id ){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getOne(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam(value = "id",defaultValue = "0") int id,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size
                                    ){
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll(id,pageRequest));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody @Valid Role role){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(id,role));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete (@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message",roleService.deleteRole(id)
        ));
    }
}
