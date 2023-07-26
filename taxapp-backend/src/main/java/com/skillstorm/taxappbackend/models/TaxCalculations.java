package com.skillstorm.taxappbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taxCalculation")
public class TaxCalculations {

    @Id
    @Column(name = "taxCalculation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxCalculationID;

    // The total income is calculated by summing up all sources of income, including
    // W-2 income and 1099 income.
    @Column(name = "total_income")
    private Double totalIncome;

    // The total deductions summing up all the standard deductions
    @Column(name = "total_deductions")
    private Double totalDeductions;

    @Column(name = "total_credits")
    private Long totalCredits;

    @Column(name = "total_paid")
    private Long totalPaid;

    @Column(name = "marginal_tax_rate")
    private Long marginalTaxRate;

    @Column(name = "reg_taxes")
    private Long regTaxes;

    @Column(name = "net_taxes")
    private Long netTaxes;

    @OneToOne
    @JoinColumn(name = "taxInformationId", unique = true)
    private TaxInformation taxInformation;

    public TaxCalculations() {
    }

    public TaxCalculations(Double totalIncome, Double totalDeductions, Long totalCredits, Long totalPaid,
            Long marginalTaxRate, Long regTaxes, Long netTaxes, TaxInformation taxInformation) {
        this.totalIncome = totalIncome;
        this.totalDeductions = totalDeductions;
        this.totalCredits = totalCredits;
        this.totalPaid = totalPaid;
        this.marginalTaxRate = marginalTaxRate;
        this.regTaxes = regTaxes;
        this.netTaxes = netTaxes;
        this.taxInformation = taxInformation;
    }

    public Long getTaxCalculationID() {
        return taxCalculationID;
    }

    public void setTaxCalculationID(Long taxCalculationID) {
        this.taxCalculationID = taxCalculationID;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(Double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public Long getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Long totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Long getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Long totalPaid) {
        this.totalPaid = totalPaid;
    }
    
    public Long getMarginalTaxRate() {
        return marginalTaxRate;
    }
    
    public void setMarginalTaxRate(Long marginalTaxRate) {
        this.marginalTaxRate = marginalTaxRate;
    }
    
    
    
    public TaxInformation getTaxInformation() {
        return taxInformation;
    }
    
    public void setTaxInformation(TaxInformation taxInformation) {
        this.taxInformation = taxInformation;
    }
    
    public Long getNetTaxes() {
        return netTaxes;
    }

    public void setNetTaxes(Long netTaxes) {
        this.netTaxes = netTaxes;
    }

    public Long getRegTaxes() {
        return regTaxes;
    }

    public void setRegTaxes(Long regTaxes) {
        this.regTaxes = regTaxes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((taxCalculationID == null) ? 0 : taxCalculationID.hashCode());
        result = prime * result + ((totalIncome == null) ? 0 : totalIncome.hashCode());
        result = prime * result + ((totalDeductions == null) ? 0 : totalDeductions.hashCode());
        result = prime * result + ((totalCredits == null) ? 0 : totalCredits.hashCode());
        result = prime * result + ((totalPaid == null) ? 0 : totalPaid.hashCode());
        result = prime * result + ((marginalTaxRate == null) ? 0 : marginalTaxRate.hashCode());
        result = prime * result + ((regTaxes == null) ? 0 : regTaxes.hashCode());
        result = prime * result + ((netTaxes == null) ? 0 : netTaxes.hashCode());
        result = prime * result + ((taxInformation == null) ? 0 : taxInformation.hashCode());
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
        TaxCalculations other = (TaxCalculations) obj;
        if (taxCalculationID == null) {
            if (other.taxCalculationID != null)
                return false;
        } else if (!taxCalculationID.equals(other.taxCalculationID))
            return false;
        if (totalIncome == null) {
            if (other.totalIncome != null)
                return false;
        } else if (!totalIncome.equals(other.totalIncome))
            return false;
        if (totalDeductions == null) {
            if (other.totalDeductions != null)
                return false;
        } else if (!totalDeductions.equals(other.totalDeductions))
            return false;
        if (totalCredits == null) {
            if (other.totalCredits != null)
                return false;
        } else if (!totalCredits.equals(other.totalCredits))
            return false;
        if (totalPaid == null) {
            if (other.totalPaid != null)
                return false;
        } else if (!totalPaid.equals(other.totalPaid))
            return false;
        if (marginalTaxRate == null) {
            if (other.marginalTaxRate != null)
                return false;
        } else if (!marginalTaxRate.equals(other.marginalTaxRate))
            return false;
        if (regTaxes == null) {
            if (other.regTaxes != null)
                return false;
        } else if (!regTaxes.equals(other.regTaxes))
            return false;
        if (netTaxes == null) {
            if (other.netTaxes != null)
                return false;
        } else if (!netTaxes.equals(other.netTaxes))
            return false;
        if (taxInformation == null) {
            if (other.taxInformation != null)
                return false;
        } else if (!taxInformation.equals(other.taxInformation))
            return false;
        return true;
    }

   

    


}