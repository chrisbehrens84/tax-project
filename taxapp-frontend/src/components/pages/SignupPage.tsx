import { Button, Form, Label, TextInput } from "@trussworks/react-uswds"
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setEmail, setId, setPassword } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";


export default function SignupPage(){

    const navigate = useNavigate();
    const {t} = useTranslation();
    const url = 'http://44.201.48.146:8080/users';

    const dispatch = useDispatch();

    function signupFormSubmit(event : any){
        event.preventDefault();
        const data = new FormData(event.target);




        //TODO talk to chris and ricardo about changing the format of this request
        fetch(url + `?email=${data.get('emailInput')}&password=${data.get('passwordInput')}`, {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
        })
            .then(data => data.json())
            .then(returnedData =>{
                console.log(returnedData);
                dispatch(setId(returnedData.id));
                dispatch(setPassword(returnedData.password));
                dispatch(setEmail(returnedData.email));
                event.target.reset();
                navigate("/home");
            })
            .catch(error => console.error(error));

    }

    function returnToLogin(){
        navigate('/');
    }



    return(
        <>
            <div style={{marginLeft : '4rem'}}>
                <Form onSubmit={signupFormSubmit}>
                    <Label htmlFor="emailInput">{t("Email")}:</Label>
                    <TextInput id='emailInput' name='emailInput' type='email'/>
                    <Label htmlFor="passwordInput">{t("Password")}:</Label>
                    <TextInput id='passwordInput' name='passwordInput' type='password'/>
                    <Label htmlFor="passwordConfirmInput">{t("Confirm Password")}:</Label>
                    <TextInput id='passwordConfirmInput' name='passwordConfirmInput' type='password'/>
                    <Button type="submit">{t("Sign up")}</Button>
                    <Button type="button" onClick={returnToLogin}>{t("Cancel")}</Button>
                </Form>
            </div>
        </>
    )
}