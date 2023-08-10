import { Table } from "@trussworks/react-uswds";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";


export default function TaxResultsPage(){

    /*
        Total Income- totalIncome
        Deductions - totalDeductions
        totalTaxableIncome
        Regular Taxes netTaxes
        All Tax Credits totalCredits
        Total Tax With Credits totalTaxWithCredits
        Effective Tax effective_tax_rate
        Tax Pre-Payments totalPaid
        Amount Due / Refund Amount – finalTaxes
    */

    const url = "http://localhost:8080/tax-calculations"

    const {t, i18n} = useTranslation();

    const user = useSelector((store : any) => store.user);
    const [totalBackground, setTotalBackground] = useState("")
    const [totalString, setTotalString] = useState("")

    const [taxResultVals, setTaxResultVals] = useState({
        totalIncome: 0,
        totalDeductions: 0,
        totalTaxableIncome: 0,
        netTaxes: 0,
        totalCredits: 0,
        totalTaxWithCredits: 0,
        effective_tax_rate: 0,
        marginalTaxRate: 0,
        totalPaid: 0,
        finalTaxes: 0
    });
    
    useEffect(() => {
        fetch(url + `/tax-information/${user.taxInfoId}`)
            .then(data => data.json())
            .then(returnedData => {
                setTaxResultVals({
                    totalIncome: returnedData.totalIncome,
                    totalDeductions: returnedData.totalDeductions,
                    totalTaxableIncome: returnedData.totalTaxableIncome,
                    netTaxes: returnedData.netTaxes,
                    totalCredits: returnedData.totalCredits,
                    totalTaxWithCredits: returnedData.totalTaxWithCredits,
                    effective_tax_rate: returnedData.effective_tax_rate,
                    marginalTaxRate: returnedData.marginalTaxRate,
                    totalPaid: returnedData.totalPaid,
                    finalTaxes: returnedData.finalTaxes
                })
                if (returnedData.finalTaxes < 0){
                    setTotalBackground("#70e17b");
                    setTotalString(t("Refund Amount"));
                }
                else{
                    setTotalBackground("#f2938c");
                    setTotalString(t("Taxes Due"));
                }
                console.log(returnedData)
            })
            .catch(error => console.error(error))
    }, [])

    useEffect(() => {
        if (taxResultVals.finalTaxes < 0){
            setTotalString(t("Refund Amount"));
        }
        else{
            setTotalString(t("Taxes Due"));
        }
    }, [i18n.language])



    return(
        <>

            <div style={{marginLeft : "4em"}}>
                <Table bordered caption="Tax Results">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">{t("Amount")} ($)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row"><strong>{t("Total Income")}</strong></th>
                            <td>{taxResultVals.totalIncome}</td>
                        </tr>
                        <tr>
                            <th scope="row">{t("Deductions")}</th>
                            <td>{taxResultVals.totalDeductions}</td>
                        </tr>
                        <tr>
                            <th scope="row">{t("Taxable Income")}</th>
                            <td>{taxResultVals.totalTaxableIncome}</td>
                        </tr>
                        <tr>
                            <th scope="row"><strong>{t("Regular Taxes")}</strong></th>
                            <td>{taxResultVals.netTaxes}</td>
                        </tr>
                        <tr>
                            <th scope="row">{t("All Tax Credits")}</th>
                            <td>{taxResultVals.totalCredits}</td>
                        </tr>
                        <tr>
                            <th scope="row"><strong>{t("Total Tax With Credits")}</strong></th>
                            <td>{taxResultVals.totalTaxWithCredits}</td>
                        </tr>
                        <tr>
                            <th scope="row">{t("Marginal tax rate")}</th>
                            <td>{taxResultVals.marginalTaxRate}%</td>
                        </tr>
                        <tr>
                            <th scope="row">{t("Effective tax rate")}</th>
                            <td>{taxResultVals.effective_tax_rate}%</td>
                        </tr>
                        <tr>
                            <th scope="row">{t("Tax Pre-Payments")}</th>
                            <td>{taxResultVals.totalPaid}</td>
                        </tr>
                        <tr style={{backgroundColor : totalBackground}}>
                            <th scope="row" style={{backgroundColor : totalBackground}}><strong>{totalString}</strong></th>
                            <td style={{backgroundColor : totalBackground}}>{Math.abs(taxResultVals.finalTaxes)}</td>
                        </tr>
                    </tbody>
                </Table>
            </div>
            
        </>
    )
}