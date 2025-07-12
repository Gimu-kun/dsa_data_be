package com.dsa_edu_be.dsa_edu_be.dto.request.User;

import com.dsa_edu_be.dsa_edu_be.Enum.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UserCreationRequest {
    @NotNull(message = "Username cannot be empty")
    @Size(min = 8,max = 36,message = "Username must have between 8-36 characters")
    private String username;

    @NotNull(message = "Passwords cannot be empty")
    @Size(min = 8,max = 36,message = "Passwords must have between 8-36 characters")
    private String passwords;

    @NotNull(message = "Fullname field cannot be empty")
    private String fullname;

    @NotNull(message = "Role cannot be empty")
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
