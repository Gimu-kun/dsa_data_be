package com.dsa_edu_be.dsa_edu_be.dto.request.User;

import com.dsa_edu_be.dsa_edu_be.Enum.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {
    private String username;
    private String passwords;
    private String fullname;
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
