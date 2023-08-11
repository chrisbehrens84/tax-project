import { Button, Fieldset, Form, Grid, GridContainer, Label, Radio, TextInput, Title } from "@trussworks/react-uswds";
import { useDispatch, useSelector } from "react-redux";
import { setTaxInfoId } from "../../slices/userSlice";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";


export default function TaxInfoPage(){
    /*
        filingStatus – “Single” “Married filing jointly”
        dependents – 0 or more
        w2Wages (w2 box 1) – 0  >=
        w2Witheld (w2 box 2) – 0 >=
        isBlind – true or false
        age – 0 >= 
        income1099 0 >=
        taxPaid1099 0> =
    */


    const url = 'http://44.201.48.146:8080/tax-information';

    const {t} = useTranslation();

    const [isLoading, setIsloading] = useState(true);

    const navigate = useNavigate();

    const dispatch = useDispatch();

    const user = useSelector((store : any) => store.user);

    /*
        @Id
        private String id;

        private String filingStatus;
        private Integer dependents;
        private Integer w2Wages;
        private Integer w2Withheld;
        private Boolean isBlind;
        private Integer age;
        private Integer income1099;
        private Integer taxPaid1099;

        // Reference to AppUser using @DBRef
        @DBRef
        private AppUser user;
    */

    const [defaultData, setDefaultData] = useState({
        filingStatus: "Single",
        dependents: 0,
        w2Wages: 0,
        w2Withheld: 0,
        isBlind: false,
        age: 0,
        income1099: 0,
        taxPaid1099: 0
    })

    useEffect(() => {
        if(user.id == ""){
            navigate("/");
            return
        }
        console.log(user.taxInfoId);
        if(user.taxInfoId != ""){
            fetch(url + `/${user.taxInfoId}`)
                .then(data => data.json())
                .then(returnedData =>{
                    const newData = {
                        filingStatus: returnedData.filingStatus,
                        dependents: returnedData.dependents,
                        w2Wages: returnedData.w2Wages,
                        w2Withheld: returnedData.w2Withheld,
                        isBlind: returnedData.isBlind,
                        age: returnedData.age,
                        income1099: returnedData.income1099,
                        taxPaid1099: returnedData.taxPaid1099
                    }
                    setDefaultData(newData);
                    console.log(returnedData);
                    setIsloading(false);
                })
                .catch(error => {
                    console.log(error);
                });
        }
        else{
            setIsloading(false);
        }

    }, []);

    function taxInfoFormSubmit(event : any){
        event.preventDefault();
        const data = new FormData(event.target);
        const info = {
            id: null,
            filingStatus: data.get('filingStatus'),
            dependents: data.get('dependentInput'),
            w2Wages: data.get('w2WageInput'),
            w2Withheld: data.get('w2WitheldInput'),
            isBlind: data.get('isBlindInput'),
            age: data.get('ageInput'),
            income1099: data.get('1099IncomeInput'),
            taxPaid1099: data.get('1099TaxPaidInput'),
            user:{
                id: user.id
            }
        }
        
        let fetchUrl = "";
        let fetchMethod = "";

        if(user.taxInfoId == ""){
            fetchUrl = url + `/${user.id}`
            fetchMethod = 'POST'
        }
        else{
            console.log(`${user.taxInfoId}`)
            fetchUrl = url + `/${user.taxInfoId}`
            info.id = user.taxInfoId;
            fetchMethod = 'PUT'
        }

        fetch(fetchUrl, {
            method: fetchMethod,
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(info)
        })
            .then(data => data.json())
            .then(returnedData =>{
                console.log(returnedData);
                dispatch(setTaxInfoId(returnedData.id))
                event.target.reset();
                navigate("/results")
            })
            .catch(error => console.error(error));

    }

    return(
        <>
            {isLoading &&
                <>
                    <p>loading...</p>
                </>
            }
            {!isLoading &&
                <>
                    <div>

                        <Form onSubmit={taxInfoFormSubmit} style={{maxWidth:"100vw"}}>
                            <GridContainer>
                                <Title>{t("Tax Information")}</Title>
                                <Grid row style={{border:"2px solid black", borderRadius:"5px", margin:"2em 0"}}>
                                    <Grid col={6}>
                                        <div style={{ height:"100%", minWidth:"25em",  padding:"2em", marginRight:'1em'}}>
                                            <Label htmlFor="filingStatus">{t("Filing Status")}:</Label>
                                            <Fieldset id="filingStatus">
                                                <Radio id="single" name="filingStatus" defaultChecked={defaultData.filingStatus=="Single"} label={t("Single")} value="Single"/>
                                                <Radio id="marringFilingJointly" name="filingStatus" defaultChecked={defaultData.filingStatus=="Married filing jointly"} label={t("Married Filing Jointly")} value="Married filing jointly"/>

                                            </Fieldset>
                                            
                                            <Label htmlFor="dependentInput">{t("Number of Dependents")}</Label>
                                            <TextInput id='dependentInput' name='dependentInput' type='number' min={0} defaultValue={defaultData.dependents}/>

                                            <Label htmlFor="w2WageInput">{t("W2 Wages")}:</Label>
                                            <TextInput id='w2WageInput' name='w2WageInput' type='number' min={0} defaultValue={defaultData.w2Wages}/>

                                            <Label htmlFor="w2WitheldInput">{t("W2 Witheld")}:</Label>
                                            <TextInput id='w2WitheldInput' name='w2WitheldInput' type='number' min={0} defaultValue={defaultData.w2Withheld}/>
                                        </div>
                                    </Grid>
                                    <Grid col={6}>
                                        <div style={{ height:"100%", minWidth:"25em",  padding:"2em", marginLeft:'1em'}}>
                                            <Label htmlFor="isBlindInput">{t("Are you legally blind?")}</Label>
                                            <Fieldset id="isBlindInput">
                                                <Radio id="true" name="isBlindInput"  defaultChecked={defaultData.isBlind} label={t("Blind")} value="true"/>
                                                <Radio id="false" name="isBlindInput" defaultChecked={!defaultData.isBlind} label={t("Not Blind")} value="false"/>

                                            </Fieldset>

                                            <Label htmlFor="ageInput">{t("Age")}:</Label>
                                            <TextInput id='ageInput' name='ageInput' type='number' min={0} max={130} defaultValue={defaultData.age}/>

                                            <Label htmlFor="1099IncomeInput">{t("1099 Income")}:</Label>
                                            <TextInput id='1099IncomeInput' name='1099IncomeInput' type='number' min={0} defaultValue={defaultData.income1099}/>

                                            <Label htmlFor="1099TaxPaidInput">{t("1099 Taxes Paid")}:</Label>
                                            <TextInput id='1099TaxPaidInput' name='1099TaxPaidInput' type='number' min={0} defaultValue={defaultData.taxPaid1099}/>

                                            <Button type="submit">{t("Calculate")}</Button>
                                        </div>
                                    </Grid>
                                </Grid>
                            </GridContainer>

                            
                            
                            
                            
                        </Form>
                    </div>
                </>
            }
            
        </>
    )
}