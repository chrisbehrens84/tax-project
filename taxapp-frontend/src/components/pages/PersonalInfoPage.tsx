import { Button, Form, Label, TextInput } from "@trussworks/react-uswds";
import { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";


export default function PersonalInfoPage(){
        //firstName lastName, ssn, address, city, zip

    const {t} = useTranslation();
    const navigate = useNavigate();

    const user = useSelector((store:any) => store.user);

    const [isLoading, setIsLoading] = useState(true);
    const [defaultData, setDefaultData] = useState({
        firstName: "",
        lastName: "",
        ssn: "",
        address: "",
        city: "",
        zip: ""
    })
    const [rawSsn, setRawSsn] = useState("");
    const [maskedSsn, setMaskedSsn] = useState("");

    function handleSsnChange(event : any){
        setMaskedSsn(event.target.value);
        event.preventDefault()
    }

    useEffect(() => {
        fetch(`http://localhost:8080/users/${user.id}`)
        .then(data => data.json())
        .then(returnedData => {

            const newData = {
                firstName: returnedData.firstName,
                lastName: returnedData.lastName,
                ssn: returnedData.ssn,
                address: returnedData.address,
                city: returnedData.city,
                zip: returnedData.zip
            }
            let key : keyof typeof newData;
            for( key  in newData){
                if (newData[key] == null){
                    newData[key] = "";
                }
            }
            
            setMaskedSsn(newData.ssn);
            setDefaultData(newData);
            setIsLoading(false);
        })
        .catch(error => console.error(error));

    }, []);



    function personalInfoFormSubmit(event : any){
        event.preventDefault();
        const data = new FormData(event.target);
        const url = 'http://localhost:8080/users';

        const userUpdate = {
            id : user.id,
            email : user.email,
            password : user.password,
            firstName : data.get('firstNameInput'),
            lastName : data.get('lastNameInput'),
            ssn : data.get('ssnInput'),
            address : data.get('addressInput'),
            city : data.get('cityInput'),
            zip : data.get('zipInput')
        }


        fetch(url + `/${userUpdate.id}`, {
            method: 'PUT',
            headers:{
                'Content-Type': 'application/json',
            },
            body : JSON.stringify(userUpdate)
        })
            .then(data => data.json())
            .then(returnedData =>{
                console.log(returnedData);
                event.target.reset();
                navigate("/home")
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
            {isLoading == false &&
                <>
                    <div style={{marginLeft : '4rem'}}>
                        <Form onSubmit={personalInfoFormSubmit}>

                            <Label htmlFor="firstNameInput">{t("First Name")}:</Label>
                            <TextInput id='firstNameInput' name='firstNameInput' type='text' defaultValue={defaultData.firstName}/>

                            <Label htmlFor="lastNameInput">{t("Last Name")}:</Label>
                            <TextInput id='lastNameInput' name='lastNameInput' type='text' defaultValue={defaultData.lastName}/>

                            <Label htmlFor="ssnInput">{t("Social Security Number")} (SSN):</Label>
                            <TextInput id='ssnInput' name='ssnInput' type='text' onChange={handleSsnChange} value={maskedSsn}/>

                            <Label htmlFor="addressInput">{t("Address")}:</Label>
                            <TextInput id='addressInput' name='addressInput' type='text' defaultValue={defaultData.address}/>

                            <Label htmlFor="cityInput">{t("City")}:</Label>
                            <TextInput id='cityInput' name='cityInput' type='text' defaultValue={defaultData.city}/>

                            <Label htmlFor="zipInput">{t("Zip Code")}:</Label>
                            <TextInput id='zipInput' name='zipInput' type='text' defaultValue={defaultData.zip}/>

                            <Button type="submit">{t("Next")}</Button>
                            
                        </Form>
                    </div>
                </>
            }
        
        </>
    )
}