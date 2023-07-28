package com.skillstorm.taxappbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tax_information")
public class TaxInformation {
    @Id
    private String id;

    private String filingStatus;
    private Integer dependents;
    private Integer w2Wages;
    private Integer w2Withheld;
    private Boolean isBlind;
    private Integer age;
    private Integer income1099;
    private Integer taxPaid1099;

    // Reference to AppUser using @DBRef
    @DBRef
    private AppUser user;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getFilingStatus() {
      return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
      this.filingStatus = filingStatus;
    }

    public Integer getDependents() {
      return dependents;
    }

    public void setDependents(Integer dependents) {
      this.dependents = dependents;
    }

    public Integer getW2Wages() {
      return w2Wages;
    }

    public void setW2Wages(Integer w2Wages) {
      this.w2Wages = w2Wages;
    }

    public Integer getW2Withheld() {
      return w2Withheld;
    }

    public void setW2Withheld(Integer w2Withheld) {
      this.w2Withheld = w2Withheld;
    }

    public Boolean getIsBlind() {
      return isBlind;
    }

    public void setIsBlind(Boolean isBlind) {
      this.isBlind = isBlind;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }

    public Integer getIncome1099() {
      return income1099;
    }

    public void setIncome1099(Integer income1099) {
      this.income1099 = income1099;
    }

    public Integer getTaxPaid1099() {
      return taxPaid1099;
    }

    public void setTaxPaid1099(Integer taxPaid1099) {
      this.taxPaid1099 = taxPaid1099;
    }

    public AppUser getUser() {
      return user;
    }

    public void setUser(AppUser user) {
      this.user = user;
    }

    

    public TaxInformation() {
    }

    public TaxInformation(String filingStatus, Integer dependents, Integer w2Wages, Integer w2Withheld, Boolean isBlind,
        Integer age, Integer income1099, Integer taxPaid1099, AppUser user) {
      this.filingStatus = filingStatus;
      this.dependents = dependents;
      this.w2Wages = w2Wages;
      this.w2Withheld = w2Withheld;
      this.isBlind = isBlind;
      this.age = age;
      this.income1099 = income1099;
      this.taxPaid1099 = taxPaid1099;
      this.user = user;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((filingStatus == null) ? 0 : filingStatus.hashCode());
      result = prime * result + ((dependents == null) ? 0 : dependents.hashCode());
      result = prime * result + ((w2Wages == null) ? 0 : w2Wages.hashCode());
      result = prime * result + ((w2Withheld == null) ? 0 : w2Withheld.hashCode());
      result = prime * result + ((isBlind == null) ? 0 : isBlind.hashCode());
      result = prime * result + ((age == null) ? 0 : age.hashCode());
      result = prime * result + ((income1099 == null) ? 0 : income1099.hashCode());
      result = prime * result + ((taxPaid1099 == null) ? 0 : taxPaid1099.hashCode());
      result = prime * result + ((user == null) ? 0 : user.hashCode());
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
      TaxInformation other = (TaxInformation) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      if (filingStatus == null) {
        if (other.filingStatus != null)
          return false;
      } else if (!filingStatus.equals(other.filingStatus))
        return false;
      if (dependents == null) {
        if (other.dependents != null)
          return false;
      } else if (!dependents.equals(other.dependents))
        return false;
      if (w2Wages == null) {
        if (other.w2Wages != null)
          return false;
      } else if (!w2Wages.equals(other.w2Wages))
        return false;
      if (w2Withheld == null) {
        if (other.w2Withheld != null)
          return false;
      } else if (!w2Withheld.equals(other.w2Withheld))
        return false;
      if (isBlind == null) {
        if (other.isBlind != null)
          return false;
      } else if (!isBlind.equals(other.isBlind))
        return false;
      if (age == null) {
        if (other.age != null)
          return false;
      } else if (!age.equals(other.age))
        return false;
      if (income1099 == null) {
        if (other.income1099 != null)
          return false;
      } else if (!income1099.equals(other.income1099))
        return false;
      if (taxPaid1099 == null) {
        if (other.taxPaid1099 != null)
          return false;
      } else if (!taxPaid1099.equals(other.taxPaid1099))
        return false;
      if (user == null) {
        if (other.user != null)
          return false;
      } else if (!user.equals(other.user))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "TaxInformation [id=" + id + ", filingStatus=" + filingStatus + ", dependents=" + dependents + ", w2Wages="
          + w2Wages + ", w2Withheld=" + w2Withheld + ", isBlind=" + isBlind + ", age=" + age + ", income1099="
          + income1099 + ", taxPaid1099=" + taxPaid1099 + ", user=" + user + "]";
    }

   
}



