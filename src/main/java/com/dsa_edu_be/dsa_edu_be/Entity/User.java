package com.dsa_edu_be.dsa_edu_be.Entity;

import com.dsa_edu_be.dsa_edu_be.Enum.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String passwords;
    private String fullname;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();

    public User(){
    }

    public User(String id, String username, UserRole role, String passwords, String fullname) {
        this.id = id;
        this.username = username;
        this.passwords = passwords;
        this.fullname = fullname;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", passwords='" + passwords + '\'' +
                ", fullname='" + fullname + '\'' +
                ", role=" + role +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
