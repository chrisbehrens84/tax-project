import {  Card, CardFooter, CardGroup, CardMedia } from "@trussworks/react-uswds";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import CalculationChoice from "../utility/CalculationChoice";
import { setTaxInfoId } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";
import personalInfoImage from "../../assets/personalInfoStock.png"
import newCalculationImage from "../../assets/calculationStartImage.png"


export default function LandingPage(){

    const {t} = useTranslation()

    const dispatch = useDispatch();

    const user = useSelector((store : any) => store.user);

    const navigate = useNavigate();


    const [taxCalculationNum, setTaxCalculationNum] = useState(0);
    const [taxCalculations, setTaxCalculations] = useState([]);
    const [personalInfoFilled, setPersonalInfoFilled] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [firstName, setFirstName] = useState("");
    const [personalHover, setPersonalHover] = useState(false);
    const [calculationHover, setCalculationHover] = useState(false);
    //const [indexCount, setIndexCount] = useState(1);
    var indexCount : number = 1;

    useEffect(() => {
        fetch(`http://44.201.48.146:8080/tax-information/user/${user.id}`)
            .then(data=>data.json())
            .then(returnedData=>{
                setTaxCalculationNum(returnedData.length);
                setTaxCalculations(returnedData);
                console.log(returnedData);
                console.log(returnedData.length);
            })
            .catch(error => {
                navigate("/")
                console.error(error)
            });

        fetch(`http://44.201.48.146:8080/users/${user.id}`)
            .then(data=>data.json())
            .then(returnedData=>{
                console.log(returnedData);
                console.log(returnedData.ssn);
                checkPersonalInfo(returnedData);
                setFirstName(returnedData.firstName);
                dispatch(setTaxInfoId(""));
                indexCount = 1;
                setIsLoading(false);
            })
            .catch(error => {
                navigate("/")
                console.error(error)
            });

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

    function personalHoverOn(){
        setPersonalHover(true);
    }
    function personalHoverOff(){
        setPersonalHover(false);
    }

    function calculationHoverOn(){
        setCalculationHover(true);
    }
    function calculationHoverOff(){
        setCalculationHover(false);
    }

    function getIndex(){
        let i = indexCount;
        indexCount++;
        return i;
    }



    return(
        <>
            {isLoading && 
                <>
                    <p>{t("loading")}...</p>
                </>
            }
            {isLoading == false &&
                <div style={{paddingBottom: "0", marginLeft:"10vw", marginRight:"10vw", textAlign:"center"}}>
                    <h1>{t("Welcome")}, {firstName == ""|| firstName == null  ? t("User") : firstName}!</h1>
                    {personalInfoFilled == false &&
                        <>
                            <p>{t("finishYourInfo")}</p>
                            <CardGroup>
                                <Card containerProps={ personalHover ? {className:'border-ink'} : {}} 
                                    onMouseEnter={personalHoverOn} onMouseLeave={personalHoverOff} 
                                    gridLayout={{tablet : { col: 4}, offset : 4}} onClick={personalInfo} 
                                    style={{cursor:"pointer"}}>

                                    <CardMedia>
                                        <img src={personalInfoImage} style={{width:"100%"}}></img>
                                    </CardMedia>
                                    <CardFooter>
                                        <h2>{t("Update Personal Info")}</h2>
                                    </CardFooter>
                                </Card>
                            </CardGroup>
                            <div style={{marginBottom:"4em"}}></div>
                        </>
                    }
                    {personalInfoFilled &&
                        <>
                            <div style={{marginBottom:"5em"}}>
                                {taxCalculationNum == 0 && 
                                    <>
                                        <p>{t("noCalc")}</p>
                                    </>
                                }

                                {taxCalculationNum == 1 && 
                                    <>
                                        <p>{t("oneCalc")}</p>
                                        <CalculationChoice index={1} calculation={taxCalculations[0]}></CalculationChoice>
                                    </>
                                }

                                {taxCalculationNum >= 2 && 
                                    <>
                                        <p>{t("twoCalc")}</p>
                                        {taxCalculations.map((taxCalculation : any) =>{

                                            return <CalculationChoice key={taxCalculation.id} taxCalculations={taxCalculations} setTaxCalculations={setTaxCalculations} index={getIndex()} calculation={taxCalculation}></CalculationChoice>
                                        })}
                                    </>
                                }
                            </div>
                            <CardGroup >
                                <Card containerProps={ personalHover ? {className:'border-ink'} : {}} 
                                    onMouseEnter={personalHoverOn} onMouseLeave={personalHoverOff} 
                                    gridLayout={{tablet : { col: 4}, offset : 2}} onClick={personalInfo} 
                                    style={{cursor:"pointer"}}>

                                    <CardMedia>
                                        <img src={personalInfoImage} style={{width:"100%"}}></img>
                                    </CardMedia>
                                    <CardFooter>
                                        <h2>{t("Update Personal Info")}</h2>
                                    </CardFooter>
                                </Card>
                                <Card containerProps={ calculationHover ? {className:'border-ink'} : {}} 
                                    onMouseEnter={calculationHoverOn} onMouseLeave={calculationHoverOff} 
                                    gridLayout={{tablet : { col: 4}}} onClick={newCalculation} 
                                    style={{cursor:"pointer"}}>
                                        
                                    <CardMedia>
                                        <img src={newCalculationImage} style={{width:"100%"}}></img>
                                    </CardMedia>
                                    <CardFooter>
                                        <h2>{t("Start New Tax Form")}</h2>
                                    </CardFooter>
                                </Card>
                            </CardGroup>
                        </>
                    }
                </div>
            }
        </>
    )
}