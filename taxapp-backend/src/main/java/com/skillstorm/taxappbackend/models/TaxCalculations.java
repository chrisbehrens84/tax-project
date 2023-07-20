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
@Table(name = "Tax_calculations")
public class TaxCalculations {

    @Id
    @Column(name = "calculation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer calculationID;

    @ManyToOne
    @JoinColumn(name = "taxpayer_id", referencedColumnName = "taxpayer_id")
    private TaxPayerInformation taxpayerInformation;

    // The total income is calculated by summing up all sources of income, including
    // W-2 income and 1099 income.
    @Column(name = "total_income")
    private Double totalIncome;

    // The total deductions summing up all the standard deductions
    @Column(name = "total_deductions")
    private Double totalDeductions;

    // subtracting the total deductions from the total income.
    @Column(name = "taxable_income")
    private Double taxableIncome;
    // the net amount of tax that the taxpayer either owes
    @Column(name = "tax_amount")
    private Double finalTaxAmount;

    public TaxCalculations() {
    }

    public TaxCalculations(TaxPayerInformation taxpayerInformation, Double totalIncome, Double totalDeductions,
            Double taxableIncome, Double finalTaxAmount) {
        this.taxpayerInformation = taxpayerInformation;
        this.totalIncome = totalIncome;
        this.totalDeductions = totalDeductions;
        this.taxableIncome = taxableIncome;
        this.finalTaxAmount = finalTaxAmount;
    }

    public Integer getCalculationID() {
        return calculationID;
    }

    public void setCalculationID(Integer calculationID) {
        this.calculationID = calculationID;
    }

    public TaxPayerInformation getTaxpayerInformation() {
        return taxpayerInformation;
    }

    public void setTaxpayerInformation(TaxPayerInformation taxpayerInformation) {
        this.taxpayerInformation = taxpayerInformation;
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

    public Double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(Double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public Double getFinalTaxAmount() {
        return finalTaxAmount;
    }

    public void setFinalTaxAmount(Double finalTaxAmount) {
        this.finalTaxAmount = finalTaxAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((calculationID == null) ? 0 : calculationID.hashCode());
        result = prime * result + ((taxpayerInformation == null) ? 0 : taxpayerInformation.hashCode());
        result = prime * result + ((totalIncome == null) ? 0 : totalIncome.hashCode());
        result = prime * result + ((totalDeductions == null) ? 0 : totalDeductions.hashCode());
        result = prime * result + ((taxableIncome == null) ? 0 : taxableIncome.hashCode());
        result = prime * result + ((finalTaxAmount == null) ? 0 : finalTaxAmount.hashCode());
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
        if (calculationID == null) {
            if (other.calculationID != null)
                return false;
        } else if (!calculationID.equals(other.calculationID))
            return false;
        if (taxpayerInformation == null) {
            if (other.taxpayerInformation != null)
                return false;
        } else if (!taxpayerInformation.equals(other.taxpayerInformation))
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
        if (taxableIncome == null) {
            if (other.taxableIncome != null)
                return false;
        } else if (!taxableIncome.equals(other.taxableIncome))
            return false;
        if (finalTaxAmount == null) {
            if (other.finalTaxAmount != null)
                return false;
        } else if (!finalTaxAmount.equals(other.finalTaxAmount))
            return false;
        return true;
    }

}
