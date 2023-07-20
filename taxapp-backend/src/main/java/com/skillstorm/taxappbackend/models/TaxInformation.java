package com.skillstorm.taxappbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TaxInformation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tax_info_id;

    @Column
    private Integer taxpayer_id;

    @Column
    private String filing_status;

    @Column
    private Integer age;

    @Column
    private Integer num_of_dependents;

    @Column
    private boolean isBlind;

    @Column
    private Integer income_w2;
    @Column
    private Integer income_1099;
    @Column
    private Integer taxes_paid_w2;
    @Column
    private Integer taxes_paid_1099;
    @Column
    private String employer;
    @Column
    private String company_name;

}
