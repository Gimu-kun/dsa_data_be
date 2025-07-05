package com.dsa_edu_be.dsa_edu_be.dto.request;

import com.dsa_edu_be.dsa_edu_be.Enum.UserRole;

import java.time.LocalDateTime;

public class UserUpdateRequest {
    private String passwords;
    private String fullname;
    private UserRole role;
    private LocalDateTime updated_at;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
