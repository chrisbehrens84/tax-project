import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";


export default function PersonalInfoPage(){
        //firstName lastName, ssn, address, city, zip

    function personalInfoFormSubmit(event : any){
        event.preventDefault();
    }

    return(
        <>
            <div style={{marginLeft : '4rem'}}>
                <Form onSubmit={personalInfoFormSubmit}>

                    <Label htmlFor="firstNameInput">First Name:</Label>
                    <TextInput id='firstNameInput' name='firstNameInput' type='text'/>

                    <Label htmlFor="lastNameInput">Last Name:</Label>
                    <TextInput id='lastNameInput' name='lastNameInput' type='text'/>

                    <Label htmlFor="ssnInput">Social Security Number (SSN):</Label>
                    <TextInput id='ssnInput' name='ssnInput' type='text'/>

                    <Label htmlFor="addressInput">Address:</Label>
                    <TextInput id='addressInput' name='addressInput' type='text'/>

                    <Label htmlFor="cityInput">City:</Label>
                    <TextInput id='cityInput' name='cityInput' type='text'/>

                    <Label htmlFor="zipInput">Zip Code:</Label>
                    <TextInput id='zipInput' name='zipInput' type='text'/>

                    <Button type="submit">Next</Button>
                    
                </Form>
            </div>
        
        </>
    )
}