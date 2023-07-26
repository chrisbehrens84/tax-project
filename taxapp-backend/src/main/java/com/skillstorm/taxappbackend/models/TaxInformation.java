package com.skillstorm.taxappbackend.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taxInfo")
public class TaxInformation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxInfoId;

    @Column(name = "filing_status")
    private String filingStatus;

    @Column(name = "age")
    private Long age;

    @Column(name = "dependents")
    private Long dependents;

    @Column(name = "isBlind")
    private boolean isBlind;

    @Column(name = "income_w2")
    private Long incomeW2;

    @Column(name = "income_1099")
    private Long income1099;

    @Column(name = "taxes_paid_w2")
    private Long taxesPaidW2;

    @Column(name = "taxes_paid_1099")
    private Long taxesPaid1099;

    @Column(name = "employer")
    private String employer;

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToOne(mappedBy = "taxInformation", cascade = CascadeType.ALL)
    private TaxCalculations taxCalculations;

    public TaxInformation() {
    }

    public TaxInformation(String filingStatus, Long age, Long dependents, boolean isBlind, Long incomeW2,
            Long income1099, Long taxesPaidW2, Long taxesPaid1099, String employer, String companyName,
            UserInfo userInfo, TaxCalculations taxCalculations) {
        this.filingStatus = filingStatus;
        this.age = age;
        this.dependents = dependents;
        this.isBlind = isBlind;
        this.incomeW2 = incomeW2;
        this.income1099 = income1099;
        this.taxesPaidW2 = taxesPaidW2;
        this.taxesPaid1099 = taxesPaid1099;
        this.employer = employer;
        this.companyName = companyName;
        this.userInfo = userInfo;
        this.taxCalculations = taxCalculations;
    }

    public Long getTaxInfoId() {
        return taxInfoId;
    }

    public void setTaxInfoId(Long taxInfoId) {
        this.taxInfoId = taxInfoId;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getDependents() {
        return dependents;
    }

    public void setDependents(Long dependents) {
        this.dependents = dependents;
    }

    public boolean isBlind() {
        return isBlind;
    }

    public void setBlind(boolean isBlind) {
        this.isBlind = isBlind;
    }

    public Long getIncomeW2() {
        return incomeW2;
    }

    public void setIncomeW2(Long incomeW2) {
        this.incomeW2 = incomeW2;
    }

    public Long getIncome1099() {
        return income1099;
    }

    public void setIncome1099(Long income1099) {
        this.income1099 = income1099;
    }

    public Long getTaxesPaidW2() {
        return taxesPaidW2;
    }

    public void setTaxesPaidW2(Long taxesPaidW2) {
        this.taxesPaidW2 = taxesPaidW2;
    }

    public Long getTaxesPaid1099() {
        return taxesPaid1099;
    }

    public void setTaxesPaid1099(Long taxesPaid1099) {
        this.taxesPaid1099 = taxesPaid1099;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TaxCalculations getTaxCalculations() {
        return taxCalculations;
    }

    public void setTaxCalculations(TaxCalculations taxCalculations) {
        this.taxCalculations = taxCalculations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((taxInfoId == null) ? 0 : taxInfoId.hashCode());
        result = prime * result + ((filingStatus == null) ? 0 : filingStatus.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((dependents == null) ? 0 : dependents.hashCode());
        result = prime * result + (isBlind ? 1231 : 1237);
        result = prime * result + ((incomeW2 == null) ? 0 : incomeW2.hashCode());
        result = prime * result + ((income1099 == null) ? 0 : income1099.hashCode());
        result = prime * result + ((taxesPaidW2 == null) ? 0 : taxesPaidW2.hashCode());
        result = prime * result + ((taxesPaid1099 == null) ? 0 : taxesPaid1099.hashCode());
        result = prime * result + ((employer == null) ? 0 : employer.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
        result = prime * result + ((taxCalculations == null) ? 0 : taxCalculations.hashCode());
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
        if (taxInfoId == null) {
            if (other.taxInfoId != null)
                return false;
        } else if (!taxInfoId.equals(other.taxInfoId))
            return false;
        if (filingStatus == null) {
            if (other.filingStatus != null)
                return false;
        } else if (!filingStatus.equals(other.filingStatus))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (dependents == null) {
            if (other.dependents != null)
                return false;
        } else if (!dependents.equals(other.dependents))
            return false;
        if (isBlind != other.isBlind)
            return false;
        if (incomeW2 == null) {
            if (other.incomeW2 != null)
                return false;
        } else if (!incomeW2.equals(other.incomeW2))
            return false;
        if (income1099 == null) {
            if (other.income1099 != null)
                return false;
        } else if (!income1099.equals(other.income1099))
            return false;
        if (taxesPaidW2 == null) {
            if (other.taxesPaidW2 != null)
                return false;
        } else if (!taxesPaidW2.equals(other.taxesPaidW2))
            return false;
        if (taxesPaid1099 == null) {
            if (other.taxesPaid1099 != null)
                return false;
        } else if (!taxesPaid1099.equals(other.taxesPaid1099))
            return false;
        if (employer == null) {
            if (other.employer != null)
                return false;
        } else if (!employer.equals(other.employer))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (userInfo == null) {
            if (other.userInfo != null)
                return false;
        } else if (!userInfo.equals(other.userInfo))
            return false;
        if (taxCalculations == null) {
            if (other.taxCalculations != null)
                return false;
        } else if (!taxCalculations.equals(other.taxCalculations))
            return false;
        return true;
    }
}