package com.skillstorm.taxappbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tax_calculation")
public class TaxCalculations {

    @Id
    private String id;

    private Integer effective_tax_rate;
    private Double totalCredits;
    private Double totalDeductions;
    private Double totalIncome;
    private Double totalPaid;
    private Double totalTaxableIncome;
    private Double netTaxes;
    private Double finalTaxes;
    private Double totalTaxWithCredits;
    private Integer marginalTaxRate;

    @DBRef
    private TaxInformation taxInformation;

    public TaxCalculations() {
    }

    public TaxCalculations(Integer effective_tax_rate, Double totalCredits, Double totalDeductions, Double totalIncome,
            Double totalPaid, Double totalTaxableIncome, Double netTaxes, Double finalTaxes, Double totalTaxWithCredits,
            Integer marginalTaxRate, TaxInformation taxInformation) {
        this.effective_tax_rate = effective_tax_rate;
        this.totalCredits = totalCredits;
        this.totalDeductions = totalDeductions;
        this.totalIncome = totalIncome;
        this.totalPaid = totalPaid;
        this.totalTaxableIncome = totalTaxableIncome;
        this.netTaxes = netTaxes;
        this.finalTaxes = finalTaxes;
        this.totalTaxWithCredits = totalTaxWithCredits;
        this.marginalTaxRate = marginalTaxRate;
        this.taxInformation = taxInformation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEffective_tax_rate() {
        return effective_tax_rate;
    }

    public void setEffective_tax_rate(Integer effective_tax_rate) {
        this.effective_tax_rate = effective_tax_rate;
    }

    public Double getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Double totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(Double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Double getTotalTaxableIncome() {
        return totalTaxableIncome;
    }

    public void setTotalTaxableIncome(Double totalTaxableIncome) {
        this.totalTaxableIncome = totalTaxableIncome;
    }

    public Double getNetTaxes() {
        return netTaxes;
    }

    public void setNetTaxes(Double netTaxes) {
        this.netTaxes = netTaxes;
    }

    public Double getFinalTaxes() {
        return finalTaxes;
    }

    public void setFinalTaxes(Double finalTaxes) {
        this.finalTaxes = finalTaxes;
    }

    public Double getTotalTaxWithCredits() {
        return totalTaxWithCredits;
    }

    public void setTotalTaxWithCredits(Double totalTaxWithCredits) {
        this.totalTaxWithCredits = totalTaxWithCredits;
    }

    public TaxInformation getTaxInformation() {
        return taxInformation;
    }

    public void setTaxInformation(TaxInformation taxInformation) {
        this.taxInformation = taxInformation;
    }

    public Integer getMarginalTaxRate() {
        return marginalTaxRate;
    }

    public void setMarginalTaxRate(Integer marginalTaxRate) {
        this.marginalTaxRate = marginalTaxRate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((effective_tax_rate == null) ? 0 : effective_tax_rate.hashCode());
        result = prime * result + ((totalCredits == null) ? 0 : totalCredits.hashCode());
        result = prime * result + ((totalDeductions == null) ? 0 : totalDeductions.hashCode());
        result = prime * result + ((totalIncome == null) ? 0 : totalIncome.hashCode());
        result = prime * result + ((totalPaid == null) ? 0 : totalPaid.hashCode());
        result = prime * result + ((totalTaxableIncome == null) ? 0 : totalTaxableIncome.hashCode());
        result = prime * result + ((netTaxes == null) ? 0 : netTaxes.hashCode());
        result = prime * result + ((finalTaxes == null) ? 0 : finalTaxes.hashCode());
        result = prime * result + ((totalTaxWithCredits == null) ? 0 : totalTaxWithCredits.hashCode());
        result = prime * result + ((marginalTaxRate == null) ? 0 : marginalTaxRate.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (effective_tax_rate == null) {
            if (other.effective_tax_rate != null)
                return false;
        } else if (!effective_tax_rate.equals(other.effective_tax_rate))
            return false;
        if (totalCredits == null) {
            if (other.totalCredits != null)
                return false;
        } else if (!totalCredits.equals(other.totalCredits))
            return false;
        if (totalDeductions == null) {
            if (other.totalDeductions != null)
                return false;
        } else if (!totalDeductions.equals(other.totalDeductions))
            return false;
        if (totalIncome == null) {
            if (other.totalIncome != null)
                return false;
        } else if (!totalIncome.equals(other.totalIncome))
            return false;
        if (totalPaid == null) {
            if (other.totalPaid != null)
                return false;
        } else if (!totalPaid.equals(other.totalPaid))
            return false;
        if (totalTaxableIncome == null) {
            if (other.totalTaxableIncome != null)
                return false;
        } else if (!totalTaxableIncome.equals(other.totalTaxableIncome))
            return false;
        if (netTaxes == null) {
            if (other.netTaxes != null)
                return false;
        } else if (!netTaxes.equals(other.netTaxes))
            return false;
        if (finalTaxes == null) {
            if (other.finalTaxes != null)
                return false;
        } else if (!finalTaxes.equals(other.finalTaxes))
            return false;
        if (totalTaxWithCredits == null) {
            if (other.totalTaxWithCredits != null)
                return false;
        } else if (!totalTaxWithCredits.equals(other.totalTaxWithCredits))
            return false;
        if (marginalTaxRate == null) {
            if (other.marginalTaxRate != null)
                return false;
        } else if (!marginalTaxRate.equals(other.marginalTaxRate))
            return false;
        if (taxInformation == null) {
            if (other.taxInformation != null)
                return false;
        } else if (!taxInformation.equals(other.taxInformation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TaxCalculations [id=" + id + ", effective_tax_rate=" + effective_tax_rate + ", totalCredits="
                + totalCredits + ", totalDeductions=" + totalDeductions + ", totalIncome=" + totalIncome
                + ", totalPaid=" + totalPaid + ", totalTaxableIncome=" + totalTaxableIncome + ", netTaxes=" + netTaxes
                + ", finalTaxes=" + finalTaxes + ", totalTaxWithCredits=" + totalTaxWithCredits + ", marginalTaxRate="
                + marginalTaxRate + ", taxInformation=" + taxInformation + "]";
    }

}