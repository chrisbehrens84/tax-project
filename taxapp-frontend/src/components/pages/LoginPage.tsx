import { Form, Label, TextInput, Button, Alert } from "@trussworks/react-uswds";
import { useDispatch } from "react-redux";
import {useNavigate} from 'react-router-dom'
import { setEmail, setId, setPassword } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";
import { useState } from "react";

export default function LoginPage(){

    const {t} = useTranslation();
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [loginAlert, setLoginAlert] = useState(false);

    //const user = useSelector((store : any) => store.user);

    const url = "http://44.201.48.146:8080/users/email"
    //const url = "http://localhost:8080/users/email"

    function loginFormSubmit(event : any){

        
        const data = new FormData(event.target);
        //fetch(url + `?email=${data.get('emailInput')}&password=${data.get('passwordInput')}`,{
        
        const messageBody = {
            email : data.get('emailInput'),
            password : data.get('passwordInput')
        }
        const newFormData = new FormData();

        newFormData.append("email", messageBody.email as string);
        newFormData.append("password", messageBody.password as string);

        console.log(newFormData.get("email"));

        const auth = "Basic " + btoa(messageBody.email + ":" + messageBody.password)

        console.log(auth);
        fetch(url + `?email=${messageBody.email}&password=${messageBody.password}` ,{
            method: 'POST',
            headers:{
                'Content-Type': 'application/json',
                //'Content-Type' : 'application/json'
                //'Authorization' : auth,
            },
            //credentials : 'include',
            //body: newFormData
        })
            .then(data => {
                console.log(data);
                if(data.status != 200){
                    setLoginAlert(true);
                    throw {data}
                }
                return data.json()})
            .then(returnedData =>{
                console.log(returnedData);
                dispatch(setId(returnedData.id));
                dispatch(setEmail(returnedData.email));
                dispatch(setPassword(returnedData.password));
                navigate('/home');
            })
            .catch(error => console.error(error));
        event.preventDefault();
    }

    function signUpButton(event : any){
        event.preventDefault();
        navigate('/signup');
    }



    return(
        <>  
            <div style={{width: "25%", minWidth:"25em", margin: "5vh auto", border:"2px solid black", borderRadius:"5px", padding:"2em", marginBottom:"130px", marginTop:"90px"}}>
                {/*<GridContainer>
                    <Grid row>
                        <Grid col={6} offset={3}>*/}
                            <div>
                                <h1>{t("Sign In")}</h1>
                                {loginAlert &&
                                    <>
                                        <Alert type="warning" heading={t("Warning status")} headingLevel="h4" noIcon>
                                            {t("warningText")}
                                        </Alert>
                                    </>
                                }
                                <Form onSubmit={loginFormSubmit}>
                                    <Label htmlFor="emailInput">{t("Email")}:</Label>
                                    <TextInput id='emailInput' name='emailInput' type='email'/>

                                    <Label htmlFor="passwordInput">{t("Password")}:</Label>
                                    <TextInput id='passwordInput' name='passwordInput' type='password'/>

                                    <Button type="submit">{t("Log in")}</Button>
                                    <Button type="button" onClick={signUpButton}>{t("Sign up")}</Button>
                                </Form>
                            </div>
                            {/*
                        </Grid>
                    </Grid>
                 </GridContainer> */}
            </div>
        </>
    )
}