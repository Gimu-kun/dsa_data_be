package com.dsa_edu_be.dsa_edu_be.Controller;

import com.dsa_edu_be.dsa_edu_be.Entity.User;
import com.dsa_edu_be.dsa_edu_be.Service.UserService;
import com.dsa_edu_be.dsa_edu_be.dto.request.User.UserCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.User.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id,@RequestBody UserUpdateRequest request){
        return userService.updateUser(id,request);
    }
}
