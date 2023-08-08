import { Button } from "@trussworks/react-uswds";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import CalculationChoice from "../utility/CalculationChoice";
import { setTaxInfoId } from "../../slices/userSlice";


export default function LandingPage(){

    const dispatch = useDispatch();

    const user = useSelector((store : any) => store.user);

    const navigate = useNavigate();


    const [taxCalculationNum, setTaxCalculationNum] = useState(0);
    const [taxCalculations, setTaxCalculations] = useState([]);
    const [personalInfoFilled, setPersonalInfoFilled] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch(`http://localhost:8080/tax-information/user/${user.id}`)
            .then(data=>data.json())
            .then(returnedData=>{
                setTaxCalculationNum(returnedData.length);
                setTaxCalculations(returnedData);
                console.log(returnedData);
                console.log(returnedData.length);
            })
            .catch(error => console.error(error));

        fetch(`http://localhost:8080/users/${user.id}`)
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
                    <p>loading...</p>
                </>
            }
            {isLoading == false &&
                <>
                    {personalInfoFilled == false &&
                        <>
                            <p>Finish filling out your personal info, and then start a tax calculation below</p>
                            <Button type="button" onClick={personalInfo}>Get Started</Button>
                        </>
                    }
                    {personalInfoFilled &&
                        <>
                            {taxCalculationNum == 0 && 
                                <>
                                    <p>You have no tax information filled out! Get started below</p>
                                    <Button type="button" onClick={newCalculation}>Get Started</Button>
                                </>
                            }

                            {taxCalculationNum == 1 && 
                                <>
                                    <p>You've already started a tax information form. Continue where you left off, or start a new one!</p>
                                    <CalculationChoice calculation={taxCalculations[0]}></CalculationChoice>
                                    <Button type="button" onClick={newCalculation}>Get Started</Button>
                                </>
                            }

                            {taxCalculationNum >= 2 && 
                                <>
                                    <p>Select from previous tax calculations or start a new one:</p>
                                    {taxCalculations.map((taxCalculation : any) =>{
                                        return <CalculationChoice key={taxCalculation.id} calculation={taxCalculation}></CalculationChoice>
                                    })}
                                    <Button type="button" onClick={newCalculation}>Get Started</Button>
                                </>
                            }
                            <p>OR Update your personal info below:</p>
                            <Button type="button" onClick={personalInfo}>Update Info</Button>
                        </>
                    }
                </>
            }
        </>
    )
}