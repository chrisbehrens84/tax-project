package com.skillstorm.taxappbackend.services;

public class TaxCalculationsService {
  




/*totalDedcutions
        **filingStatus come form TaxInformation filingStatus
    // if(filingStatus == "Single" && age >= 65 && isBlind){
          totalDeductions = 17550
       } else if(filingStatus == "Single" && (age >= 65 || isBlind)){
        totalDeductions = 17550
       } else if(filingStatus == "Single"){
        totalDeduction = 13850
       } else if (filingStatus == "Married Filing Jointly" && age >= 65 && isBlind) {
        totalDeduction = 30700
       } else if (filingStatus == "Married Filing Jointly" && (age >= 65 || isBlind)) {
        totalDeduction = 28500
       } else if(filingStatus =="Married Filing Jointly"){
        totalDeductions = 27700
       }
  //totalPaid
   totalPaid =  taxesPaidW2
  //totalCredits
     totalCredits = (numberOfDependendts * 2000)
  */

  //Total_Income = W2Wages + income1099

  //Total_paid = taxPaid1099 + w2Withheld


  //taxabelIncome = Total_income - totalDeductions

  //Integer taxes = 0
  /*
       if(filingStatus == "Single" && taxableIncome >= 578,128){
          taxes += (174238+((taxableIncome -578125)*.37))
       }else if (filingStatus == "Single" && taxableIncome >= 231250){
           taxes += 52832+((taxableIncome -231250)*.35))
       }else if (filingStatus == "Single" && taxableIncome >= 182100){
          taxes += (37104+(taxableIncome -182100)*.32))
       }else if (filingStatus == "Single" && taxableIncome >= 95375){
          taxes += (16290+(taxableIncome -95375)*.24))
       } else if (filingStatus == "Single" && taxableIncome >= 44725){
          taxes += (5147+(taxableIncome -44727)*.22))
       } else if (filingStatus == "Single" && taxableIncome >= 11000){
          taxes += (1100+(taxableIncome -44727)*.12))
       } else if (filingStatus == "Single" && taxableIncome >= 0){
          taxes += (taxableIncome * .1)
       } else if(filingStatus == "Married Filing Jointly" && taxableIncome >= 693750){
          taxes += 183132+(taxableIncome -578125)*.37))
       }else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 462500){
           taxes += (102195+(taxableIncome -462500)*.35))
       }else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 364200){
          taxes += (70739+(taxableIncome -182100)*.32))
       }else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 190750){
          taxes += (32580+(taxableIncome -95375)*.24))
       } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 89450){
          taxes += (10294+(taxableIncome -44727)*.22))
       } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 22000){
          taxes += (8094+(taxableIncome -44727)*.12))
       } else if (filingStatus == "Married Filing Jointly" && taxableIncome >= 0){
          taxes += (taxableIncome * .1)
       }

       reg_taxes = taxes



       **************************************
       if (taxes - (Dependents * 2000 > (0 - (Dependents *1500)))){
          net_taxes = (reg_taxes - (Dependents * 2000)
       } else{
        net_taxes = (0 - (Dependents *1500))
       }
       

       effective_taxrate = (regTaxes / taxableIncome) * 100
  */
  

  

}
