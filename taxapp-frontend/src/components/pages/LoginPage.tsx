import { Form, Label, TextInput, Button } from "@trussworks/react-uswds";
import { useDispatch } from "react-redux";
import {useNavigate} from 'react-router-dom'
import { setEmail, setId, setPassword } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";

export default function LoginPage(){

    const {t} = useTranslation();
    const navigate = useNavigate();
    const dispatch = useDispatch();

    //const user = useSelector((store : any) => store.user);

    const url = "http://localhost:8080/users/email"

    function loginFormSubmit(event : any){

        
        const data = new FormData(event.target);
        //fetch(url + `?email=${data.get('emailInput')}&password=${data.get('passwordInput')}`,{
        
        const messageBody = {
            email : data.get('emailInput'),
            password : data.get('passwordInput')
        }
        const auth = "Basic " + btoa(messageBody.email + ":" + messageBody.password)
        console.log(auth);
        fetch(url ,{

            method: 'POST',
            headers:{
                'Content-Type': 'application/json',
                'Authorization' : auth
            },
            body: JSON.stringify(messageBody)
        })
            .then(data => data.json())
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
            <div style={{marginLeft : '4rem'}}>
                <Form onSubmit={loginFormSubmit}>
                    <Label htmlFor="emailInput">{t("Email")}:</Label>
                    <TextInput id='emailInput' name='emailInput' type='text'/>

                    <Label htmlFor="passwordInput">{t("Password")}:</Label>
                    <TextInput id='passwordInput' name='passwordInput' type='password'/>

                    <Button type="submit">{t("Log in")}</Button>
                    <Button type="button" onClick={signUpButton}>{t("Sign up")}</Button>
                </Form>
            </div>
        </>
    )
}