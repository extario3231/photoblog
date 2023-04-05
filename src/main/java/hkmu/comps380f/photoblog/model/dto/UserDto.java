package hkmu.comps380f.photoblog.model.dto;

import hkmu.comps380f.photoblog.model.UserRole;

import java.util.List;

public class UserDto {
    private String username;
    private String password;
    private String description;
    private String phoneNumber;
    private String email;
    private List<UserRole> roles;

    public UserDto(String username, String password, String description, String phoneNumber, String email, List<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
