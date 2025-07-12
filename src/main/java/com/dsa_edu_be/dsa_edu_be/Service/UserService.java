package com.dsa_edu_be.dsa_edu_be.Service;

import com.dsa_edu_be.dsa_edu_be.Entity.User;
import com.dsa_edu_be.dsa_edu_be.Repository.UserRepository;
import com.dsa_edu_be.dsa_edu_be.dto.request.User.UserCreationRequest;
import com.dsa_edu_be.dsa_edu_be.dto.request.User.UserUpdateRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
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
        //Validate username is it existed
        if (checkUsernameExist(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username was existed");
        }

        //Create new user object
        User user = new User(
                UUID.randomUUID().toString(),
                request.getUsername(),
                request.getRole(),
                request.getPasswords(),
                request.getFullname()
        );

        try{
            //Save object to database
            userRepository.save(user);
            return new ResponseEntity<String>("Create user successfully", HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something wrong when save to DB");
        }
    };

    public ResponseEntity<String> updateUser(@NotNull(message = "User id cannot be empty") String id, UserUpdateRequest request){
        try{
            //Check user id existed and get user object
            Optional<User> userOpt = userRepository.findById(id);
            if (userOpt.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found this user");
            }

            //Update detail for the user founded
            User user = getUser(request, userOpt);

            //Save updated user detail
            userRepository.save(user);
            return new ResponseEntity<String>("Updated user successfully", HttpStatus.OK);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something wrong when save to DB");
        }
    }

    private static User getUser(UserUpdateRequest request, Optional<User> userOpt) {
        User user = userOpt.get();

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getPasswords() != null) {
            user.setPasswords(request.getPasswords());
        }

        if (request.getFullname() != null){
            user.setFullname(request.getFullname());
        }

        user.setUpdated_at(LocalDateTime.now());
        return user;
    }
}
