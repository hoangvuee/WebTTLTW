package Models.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserDTO {
    private int id;
    private String email;
    private String userName;
    private String phoneNumber;
    private int role;
    @JsonProperty("isActive")
    private boolean isActive;
    private Date createAt;

    public UserDTO() {
    }

    public UserDTO(int id, String email, String userName, String phoneNumber, int role, boolean isActive) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public UserDTO(int id, String email, String userName, String phoneNumber, int role, boolean isActive, Date createAt) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.isActive = isActive;
        this.createAt = createAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", createAt=" + createAt +
                '}';
    }
}
