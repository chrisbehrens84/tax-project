import { Button, Form, Label, TextInput } from "@trussworks/react-uswds"
import { useNavigate } from "react-router-dom";


export default function SignupPage(){

    const navigate = useNavigate();

    function signupFormSubmit(event : any){
        event.preventDefault();

    }

    function returnToLogin(){
        navigate('/');
    }


    return(
        <>
            <div style={{marginLeft : '4rem'}}>
                <Form onSubmit={signupFormSubmit}>
                    <Label htmlFor="emailInput">Email:</Label>
                    <TextInput id='emailInput' name='emailInput' type='text'/>
                    <Label htmlFor="passwordInput">Password:</Label>
                    <TextInput id='passwordInput' name='passwordInput' type='text'/>
                    <Label htmlFor="passwordConfirmInput">Confirm Password:</Label>
                    <TextInput id='passwordConfirmInput' name='passwordConfirmInput' type='text'/>
                    <Button type="submit">Sign Up</Button>
                    <Button type="button" onClick={returnToLogin}>Cancel</Button>
                </Form>
            </div>
        </>
    )
}