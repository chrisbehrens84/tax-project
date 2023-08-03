import { Form, Label, TextInput, Button } from "@trussworks/react-uswds";
import {useNavigate} from 'react-router-dom'

export default function LoginPage(){

    const navigate = useNavigate();

    function loginFormSubmit(event : any){
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
                    <Label htmlFor="emailInput">Email:</Label>
                    <TextInput id='emailInput' name='emailInput' type='text'/>
                    <Label htmlFor="passwordInput">Password:</Label>
                    <TextInput id='passwordInput' name='passwordInput' type='text'/>
                    <Button type="submit">Log In</Button>
                    <Button type="button" onClick={signUpButton}>Sign up</Button>
                </Form>
            </div>
        </>
    )
}