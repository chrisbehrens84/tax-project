package com.skillstorm.taxappbackend.models;
import javax.persistence.Column;
import javax.persistence.Id;

public class User {

    @Id
    @Column
    private Integer user_id;


    private String user_email;
    private String user_password;
    private String isSelfemployed;
    private String isEmployee;

    public User() {
    }

    public User(String user_email, String user_password, String isSelfemployed, String isEmployee) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.isSelfemployed = isSelfemployed;
        this.isEmployee = isEmployee;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getIsSelfemployed() {
        return isSelfemployed;
    }

    public void setIsSelfemployed(String isSelfemployed) {
        this.isSelfemployed = isSelfemployed;
    }

    public String getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(String isEmployee) {
        this.isEmployee = isEmployee;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
        result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
        result = prime * result + ((user_password == null) ? 0 : user_password.hashCode());
        result = prime * result + ((isSelfemployed == null) ? 0 : isSelfemployed.hashCode());
        result = prime * result + ((isEmployee == null) ? 0 : isEmployee.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (user_id == null) {
            if (other.user_id != null)
                return false;
        } else if (!user_id.equals(other.user_id))
            return false;
        if (user_email == null) {
            if (other.user_email != null)
                return false;
        } else if (!user_email.equals(other.user_email))
            return false;
        if (user_password == null) {
            if (other.user_password != null)
                return false;
        } else if (!user_password.equals(other.user_password))
            return false;
        if (isSelfemployed == null) {
            if (other.isSelfemployed != null)
                return false;
        } else if (!isSelfemployed.equals(other.isSelfemployed))
            return false;
        if (isEmployee == null) {
            if (other.isEmployee != null)
                return false;
        } else if (!isEmployee.equals(other.isEmployee))
            return false;
        return true;
    }

}
