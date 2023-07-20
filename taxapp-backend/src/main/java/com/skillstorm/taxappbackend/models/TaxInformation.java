package com.skillstorm.taxappbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tax_information")
public class TaxInformation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tax_info_id;

    @ManyToOne
    @JoinColumn(name = "taxpayer_id", referencedColumnName = "taxpayer_id")
    private TaxPayerInformation taxpayerInformation;

    @Column(name = "filing_status")
    private String filing_status;

    @Column(name = "age")
    private Integer age;

    @Column(name = "number_of_dependents")
    private Integer number_of_dependents;

    @Column(name = "isBlind", columnDefinition = "BIT")
    private boolean isBlind;

    @Column(name = "income_w2")
    private Integer income_w2;

    @Column(name = "income_1099")
    private Integer income_1099;

    @Column(name = "taxes_paid_w2")
    private Integer taxes_paid_w2;

    @Column(name = "taxes_paid_1099")
    private Integer taxes_paid_1099;

    @Column(name = "employer")
    private String employer;

    @Column(name = "company_name")
    private String company_name;

    public TaxInformation() {
    }

    public TaxInformation(TaxPayerInformation taxpayerInformation, String filing_status, Integer age,
            Integer number_of_dependents, boolean isBlind, Integer income_w2, Integer income_1099,
            Integer taxes_paid_w2, Integer taxes_paid_1099, String employer, String company_name) {
        this.taxpayerInformation = taxpayerInformation;
        this.filing_status = filing_status;
        this.age = age;
        this.number_of_dependents = number_of_dependents;
        this.isBlind = isBlind;
        this.income_w2 = income_w2;
        this.income_1099 = income_1099;
        this.taxes_paid_w2 = taxes_paid_w2;
        this.taxes_paid_1099 = taxes_paid_1099;
        this.employer = employer;
        this.company_name = company_name;
    }

    public Integer getTax_info_id() {
        return tax_info_id;
    }

    public void setTax_info_id(Integer tax_info_id) {
        this.tax_info_id = tax_info_id;
    }

    public TaxPayerInformation getTaxpayerInformation() {
        return taxpayerInformation;
    }

    public void setTaxpayerInformation(TaxPayerInformation taxpayerInformation) {
        this.taxpayerInformation = taxpayerInformation;
    }

    public String getFiling_status() {
        return filing_status;
    }

    public void setFiling_status(String filing_status) {
        this.filing_status = filing_status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumber_of_dependents() {
        return number_of_dependents;
    }

    public void setNumber_of_dependents(Integer number_of_dependents) {
        this.number_of_dependents = number_of_dependents;
    }

    public boolean isBlind() {
        return isBlind;
    }

    public void setBlind(boolean isBlind) {
        this.isBlind = isBlind;
    }

    public Integer getIncome_w2() {
        return income_w2;
    }

    public void setIncome_w2(Integer income_w2) {
        this.income_w2 = income_w2;
    }

    public Integer getIncome_1099() {
        return income_1099;
    }

    public void setIncome_1099(Integer income_1099) {
        this.income_1099 = income_1099;
    }

    public Integer getTaxes_paid_w2() {
        return taxes_paid_w2;
    }

    public void setTaxes_paid_w2(Integer taxes_paid_w2) {
        this.taxes_paid_w2 = taxes_paid_w2;
    }

    public Integer getTaxes_paid_1099() {
        return taxes_paid_1099;
    }

    public void setTaxes_paid_1099(Integer taxes_paid_1099) {
        this.taxes_paid_1099 = taxes_paid_1099;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tax_info_id == null) ? 0 : tax_info_id.hashCode());
        result = prime * result + ((taxpayerInformation == null) ? 0 : taxpayerInformation.hashCode());
        result = prime * result + ((filing_status == null) ? 0 : filing_status.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((number_of_dependents == null) ? 0 : number_of_dependents.hashCode());
        result = prime * result + (isBlind ? 1231 : 1237);
        result = prime * result + ((income_w2 == null) ? 0 : income_w2.hashCode());
        result = prime * result + ((income_1099 == null) ? 0 : income_1099.hashCode());
        result = prime * result + ((taxes_paid_w2 == null) ? 0 : taxes_paid_w2.hashCode());
        result = prime * result + ((taxes_paid_1099 == null) ? 0 : taxes_paid_1099.hashCode());
        result = prime * result + ((employer == null) ? 0 : employer.hashCode());
        result = prime * result + ((company_name == null) ? 0 : company_name.hashCode());
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
        if (tax_info_id == null) {
            if (other.tax_info_id != null)
                return false;
        } else if (!tax_info_id.equals(other.tax_info_id))
            return false;
        if (taxpayerInformation == null) {
            if (other.taxpayerInformation != null)
                return false;
        } else if (!taxpayerInformation.equals(other.taxpayerInformation))
            return false;
        if (filing_status == null) {
            if (other.filing_status != null)
                return false;
        } else if (!filing_status.equals(other.filing_status))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (number_of_dependents == null) {
            if (other.number_of_dependents != null)
                return false;
        } else if (!number_of_dependents.equals(other.number_of_dependents))
            return false;
        if (isBlind != other.isBlind)
            return false;
        if (income_w2 == null) {
            if (other.income_w2 != null)
                return false;
        } else if (!income_w2.equals(other.income_w2))
            return false;
        if (income_1099 == null) {
            if (other.income_1099 != null)
                return false;
        } else if (!income_1099.equals(other.income_1099))
            return false;
        if (taxes_paid_w2 == null) {
            if (other.taxes_paid_w2 != null)
                return false;
        } else if (!taxes_paid_w2.equals(other.taxes_paid_w2))
            return false;
        if (taxes_paid_1099 == null) {
            if (other.taxes_paid_1099 != null)
                return false;
        } else if (!taxes_paid_1099.equals(other.taxes_paid_1099))
            return false;
        if (employer == null) {
            if (other.employer != null)
                return false;
        } else if (!employer.equals(other.employer))
            return false;
        if (company_name == null) {
            if (other.company_name != null)
                return false;
        } else if (!company_name.equals(other.company_name))
            return false;
        return true;
    }

}
