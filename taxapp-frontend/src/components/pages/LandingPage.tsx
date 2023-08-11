import { Button } from "@trussworks/react-uswds";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import CalculationChoice from "../utility/CalculationChoice";
import { setTaxInfoId } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";


export default function LandingPage(){

    const {t} = useTranslation()

    const dispatch = useDispatch();

    const user = useSelector((store : any) => store.user);

    const navigate = useNavigate();


    const [taxCalculationNum, setTaxCalculationNum] = useState(0);
    const [taxCalculations, setTaxCalculations] = useState([]);
    const [personalInfoFilled, setPersonalInfoFilled] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch(`http://44.201.48.146:8080/tax-information/user/${user.id}`)
            .then(data=>data.json())
            .then(returnedData=>{
                setTaxCalculationNum(returnedData.length);
                setTaxCalculations(returnedData);
                console.log(returnedData);
                console.log(returnedData.length);
            })
            .catch(error => console.error(error));

        fetch(`http://44.201.48.146:8080/users/${user.id}`)
            .then(data=>data.json())
            .then(returnedData=>{
                console.log(returnedData);
                console.log(returnedData.ssn);
                checkPersonalInfo(returnedData);
                dispatch(setTaxInfoId(""));
                setIsLoading(false);
            })
            .catch(error => console.error(error));


    }, []);

    function checkPersonalInfo(data : any){
        if(data.address == null || data.city == null || data.firstName == null || data.lastName == null || data.ssn == null || data.zip == null){
            setPersonalInfoFilled(false);
        }
        else{
            setPersonalInfoFilled(true);
        }
    }

    function newCalculation(){
        dispatch(setTaxInfoId(""));
        navigate("/tax_info");
    }
    function personalInfo(){
        navigate("/personal_info");
    }
    return(
        <>
            {isLoading && 
                <>
                    <p>{t("loading")}...</p>
                </>
            }
            {isLoading == false &&
                <>
                    {personalInfoFilled == false &&
                        <>
                            <p>{t("finishYourInfo")}</p>
                            <Button type="button" onClick={personalInfo}>{t("Update Personal Info")}</Button>
                        </>
                    }
                    {personalInfoFilled &&
                        <>
                            {taxCalculationNum == 0 && 
                                <>
                                    <p>{t("noCalc")}</p>
                                    <Button type="button" onClick={newCalculation}>{t("Get Started")}</Button>
                                </>
                            }

                            {taxCalculationNum == 1 && 
                                <>
                                    <p>{t("oneCalc")}</p>
                                    <CalculationChoice calculation={taxCalculations[0]}></CalculationChoice>
                                    <Button type="button" onClick={newCalculation}>{t("Get Started")}</Button>
                                </>
                            }

                            {taxCalculationNum >= 2 && 
                                <>
                                    <p>{t("twoCalc")}</p>
                                    {taxCalculations.map((taxCalculation : any) =>{
                                        return <CalculationChoice key={taxCalculation.id} calculation={taxCalculation}></CalculationChoice>
                                    })}
                                    <Button type="button" onClick={newCalculation}>{t("Get Started")}</Button>
                                </>
                            }
                            <p>{t("updateInfo")}</p>
                            <Button type="button" onClick={personalInfo}>{t("Update Personal Info")}</Button>
                        </>
                    }
                </>
            }
        </>
    )
}