package com.skillstorm.taxappbackend.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userInfo")
public class UserInfo {

    @Id // primary key
    @Column(name = "userInfo_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SPECIFY AUTO INCREMENT
    private Long userInfoId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private Long zipcode;

    @Column(name = "ssn")
    private Long ssn;

    @OneToOne
    @JoinColumn(name = "userId", unique = true)
    private AppUser appUser;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<TaxInformation> taxInformationList;

    public UserInfo() {
    }

    public UserInfo(Long userInfoId, String firstName, String lastName, String address, String city, String state,
        Long zipcode, Long ssn, AppUser appUser, List<TaxInformation> taxInformationList) {
      this.userInfoId = userInfoId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.city = city;
      this.state = state;
      this.zipcode = zipcode;
      this.ssn = ssn;
      this.appUser = appUser;
      this.taxInformationList = taxInformationList;
    }



    public Long getUserInfoId() {
      return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
      this.userInfoId = userInfoId;
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
    
    
    
  public String getState() {
      return state;
   }
    
    
    
  public void setState(String state) {
      this.state = state;
   }
    
  public Long getZipcode() {
      return zipcode;
  }
    
    
    
        public void setZipcode(Long zipcode) {
          this.zipcode = zipcode;
        }
    public Long getSsn() {
      return ssn;
    }

    public void setSsn(Long ssn) {
      this.ssn = ssn;
    }

   



    public List<TaxInformation> getTaxInformationList() {
      return taxInformationList;
    }

    public void setTaxInformationList(List<TaxInformation> taxInformationList) {
      this.taxInformationList = taxInformationList;
    }

    public AppUser getAppUser() {
      return appUser;
    }

    public void setAppUser(AppUser appUser) {
      this.appUser = appUser;
    }



    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((userInfoId == null) ? 0 : userInfoId.hashCode());
      result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
      result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
      result = prime * result + ((address == null) ? 0 : address.hashCode());
      result = prime * result + ((city == null) ? 0 : city.hashCode());
      result = prime * result + ((state == null) ? 0 : state.hashCode());
      result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
      result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
      result = prime * result + ((appUser == null) ? 0 : appUser.hashCode());
      result = prime * result + ((taxInformationList == null) ? 0 : taxInformationList.hashCode());
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
      UserInfo other = (UserInfo) obj;
      if (userInfoId == null) {
        if (other.userInfoId != null)
          return false;
      } else if (!userInfoId.equals(other.userInfoId))
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
      if (state == null) {
        if (other.state != null)
          return false;
      } else if (!state.equals(other.state))
        return false;
      if (zipcode == null) {
        if (other.zipcode != null)
          return false;
      } else if (!zipcode.equals(other.zipcode))
        return false;
      if (ssn == null) {
        if (other.ssn != null)
          return false;
      } else if (!ssn.equals(other.ssn))
        return false;
      if (appUser == null) {
        if (other.appUser != null)
          return false;
      } else if (!appUser.equals(other.appUser))
        return false;
      if (taxInformationList == null) {
        if (other.taxInformationList != null)
          return false;
      } else if (!taxInformationList.equals(other.taxInformationList))
        return false;
      return true;
    }   
}