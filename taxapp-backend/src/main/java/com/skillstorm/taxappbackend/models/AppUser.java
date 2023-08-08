package com.skillstorm.taxappbackend.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Document(collection = "app_users") 
public class AppUser implements UserDetails{

    @Id
    private String id;

    @NonNull
    private String email;

    @NonNull
    private String password;
    
    private String firstName;
    private String lastName;
    private Integer ssn;
    private String address;
    private String city;
    private Integer zip;
    private String role;


    public String getId() {
      return id;
    }


    public void setId(String id) {
      this.id = id;
    }


    public String getEmail() {
      return email;
    }


    public void setEmail(String email) {
      this.email = email;
    }


    // public String getPassword() {
    //   return password;
    // }


    public void setPassword(String password) {
      this.password = password;
    }


    public String getFirstName() {
      return firstName;
    }


    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }


    public String getLastName() {
      return lastName;
    }


    public void setLastName(String lastName) {
      this.lastName = lastName;
    }


    public Integer getSsn() {
      return ssn;
    }


    public void setSsn(Integer ssn) {
      this.ssn = ssn;
    }


    public String getAddress() {
      return address;
    }


    public void setAddress(String address) {
      this.address = address;
    }


    public String getCity() {
      return city;
    }


    public void setCity(String city) {
      this.city = city;
    }


    public Integer getZip() {
      return zip;
    }


    public void setZip(Integer zip) {
      this.zip = zip;
    }
    
    public String getRole() {
      return role;
    }
    
    
    public void setRole(String role) {
      this.role = role;
    }


    public AppUser() {
    }

    

    public AppUser(String email, String password) {
      this.email = email;
      this.password = password;
    }


    public AppUser(String email, String password, String firstName, String lastName, Integer ssn, String address,
        String city, Integer zip, String role) {
      this.email = email;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.ssn = ssn;
      this.address = address;
      this.city = city;
      this.zip = zip;
      this.role = role;
    }


   


    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((password == null) ? 0 : password.hashCode());
      result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
      result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
      result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
      result = prime * result + ((address == null) ? 0 : address.hashCode());
      result = prime * result + ((city == null) ? 0 : city.hashCode());
      result = prime * result + ((zip == null) ? 0 : zip.hashCode());
      result = prime * result + ((role == null) ? 0 : role.hashCode());
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
      AppUser other = (AppUser) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      if (email == null) {
        if (other.email != null)
          return false;
      } else if (!email.equals(other.email))
        return false;
      if (password == null) {
        if (other.password != null)
          return false;
      } else if (!password.equals(other.password))
        return false;
      if (firstName == null) {
        if (other.firstName != null)
          return false;
      } else if (!firstName.equals(other.firstName))
        return false;
      if (lastName == null) {
        if (other.lastName != null)
          return false;
      } else if (!lastName.equals(other.lastName))
        return false;
      if (ssn == null) {
        if (other.ssn != null)
          return false;
      } else if (!ssn.equals(other.ssn))
        return false;
      if (address == null) {
        if (other.address != null)
          return false;
      } else if (!address.equals(other.address))
        return false;
      if (city == null) {
        if (other.city != null)
          return false;
      } else if (!city.equals(other.city))
        return false;
      if (zip == null) {
        if (other.zip != null)
          return false;
      } else if (!zip.equals(other.zip))
        return false;
      if (role == null) {
        if (other.role != null)
          return false;
      } else if (!role.equals(other.role))
        return false;
      return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
       Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(role);
        authorities.add(userRole);

        return authorities;     // USER -> ROLE_USER
    }

    @Override   // getter method for password
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
      // TODO Auto-generated method stub
      return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
      // TODO Auto-generated method stub
      return true;
    }


    @Override
    public boolean isAccountNonLocked() {
      // TODO Auto-generated method stub
      return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
      // TODO Auto-generated method stub
      return true;
    }


    @Override
    public boolean isEnabled() {
      // TODO Auto-generated method stub
      return true;
    }


    
    
}
