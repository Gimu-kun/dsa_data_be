package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.User;
import com.dsa_edu_be.dsa_edu_be.Repository.UserRepository;
import com.dsa_edu_be.dsa_edu_be.dto.request.User.UserCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.User.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){
      return userRepository.findAll();
    };

    public boolean checkIdExitst(String id){
        Optional<User> user = userRepository.findById(id);
        return user.isPresent();
    };

    public User getUserById(String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found with this user id");
        }
        return user.get();
    };

    private boolean checkUsernameExist(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    };

    public ResponseEntity<String> createUser(UserCreationRequest request){
        if (checkUsernameExist(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username was existed");
        }
        User user = new User(
                UUID.randomUUID().toString(),
                request.getUsername(),
                request.getRole(),
                request.getPasswords(),
                request.getFullname()
        );

        System.out.println(user.toString());
        try{
            userRepository.save(user);
            return new ResponseEntity<String>("Create user successfully", HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something wrong when save to DB");
        }
    };

    public ResponseEntity<String> updateUser(String id,UserUpdateRequest request){
        try{
            Optional<User> userOpt = userRepository.findById(id);
            if (userOpt.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found this user");
            }
            User user = userOpt.get();
            if (request.getRole() != null && !request.getRole().equals(user.getRole())) {
                user.setRole(request.getRole());
            }

            if (request.getPasswords() != null && !Objects.equals(request.getPasswords(), user.getPasswords())) {
                user.setPasswords(request.getPasswords());
            }

            if (request.getFullname() != null && !Objects.equals(request.getFullname(),user.getFullname())){
                user.setFullname(request.getFullname());
            }

            user.setUpdated_at(LocalDateTime.now());
            userRepository.save(user);
            return new ResponseEntity<String>("Updated user successfully", HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something wrong when save to DB");
        }
    }
}
