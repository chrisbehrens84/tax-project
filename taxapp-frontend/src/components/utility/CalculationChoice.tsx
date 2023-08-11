import { Button, Grid, GridContainer } from "@trussworks/react-uswds";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setTaxInfoId } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";


export default function CalculationChoice({calculation, index, taxCalculations, setTaxCalculations} : any){

    const {t} = useTranslation();

    const url = "http://44.201.48.146:8080/tax-information";

    const navigate = useNavigate();
    const dispatch = useDispatch();

    function deleteCalc(){
        fetch(url + `/${calculation.id}`,
        {
            method: 'DELETE',
            headers: {
                'Content-Type' : 'application/json'
            }
        })
            .then(data => {
                console.log(data)
                setTaxCalculations(taxCalculations.filter((x:any) => {
                    if (x.id == calculation.id) {return false};
                    return true;
                }))
            })
            .catch(error => console.error(error));
    }


    function select(){
        dispatch(setTaxInfoId(calculation.id))
        navigate("/tax_info")
    }

    

    return(
        <>
            <div style={{margin:'1em'}}>
                <GridContainer>
                    <Grid row style={{border:"2px solid", borderRadius:"5px"}}>
                        <Grid col={2} style={{textAlign:"left"}}>
                            <h2 style={{paddingLeft:'1em'}}>{t("Form")} {index}</h2>
                        </Grid>
                        <Grid col={2} style={{textAlign:"right"}}>
                            <p>{t("W2 Wages")} : </p>
                            <p>{t("W2 Witheld")} : </p>
                        </Grid>
                        <Grid col={1} style={{textAlign:"left"}}>
                            <p> &nbsp;&nbsp;{calculation.w2Wages}</p>
                            <p> &nbsp;&nbsp;{calculation.w2Withheld}</p>

                        </Grid>
                        <Grid col={2} style={{textAlign:"right"}}>
                            <p>{t("1099 Income")} : </p>
                            <p>{t("1099 Taxes Paid")} : </p>
                        </Grid>
                        <Grid col={1} style={{textAlign:"left"}}>
                            <p> &nbsp;&nbsp;{calculation.income1099}</p>
                            <p> &nbsp;&nbsp;{calculation.taxPaid1099}</p>

                        </Grid>
                        <Grid col={2}>
                            <Button type="button" onClick={select} style={{margin:'20px'}}>{t("Select")}</Button>
                        </Grid>
                        <Grid col={2}>
                            <Button type="button" onClick={deleteCalc} style={{margin:'20px'}} secondary>{t("Delete")}</Button>
                        </Grid>
                    </Grid>
                </GridContainer>
            </div>
        </>
    )
}