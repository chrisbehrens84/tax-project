import { Button, Fieldset, Form, Label, Radio, TextInput } from "@trussworks/react-uswds";


export default function TaxInfoPage(){
    /*
        filingStatus – “Married” “Single” “Married filing jointly”
        dependents – 0 or more
        w2Wages (w2 box 1) – 0  >=
        w2Witheld (w2 box 2) – 0 >=
        isBlind – true or false
        age – 0 >= 
        income1099 0 >=
        taxPaid1099 0> =
    */

    function taxInfoFormSubmit(event : any){
        event.preventDefault();
    }

    return(
        <>
            <div style={{marginLeft : '4rem'}}>
                <Form onSubmit={taxInfoFormSubmit}>

                    <Label htmlFor="filingStatus">Filing Status:</Label>
                    <Fieldset id="filingStatus">
                        <Radio id="single" name="filingStatus" defaultChecked label="Single" value="Single"/>
                        <Radio id="married" name="filingStatus"  label="Married" value="Married"/>
                        <Radio id="marringFilingJointly" name="filingStatus"  label="Married Filing Jointly" value="Married Filing Jointly"/>

                    </Fieldset>
                    
                    <Label htmlFor="dependentInput">Number of Dependents</Label>
                    <TextInput id='dependentInput' name='dependentInput' type='number' defaultValue={0}/>

                    <Label htmlFor="w2WageInput">W2 Wages:</Label>
                    <TextInput id='w2WageInput' name='w2WageInput' type='number'/>

                    <Label htmlFor="w2WitheldInput">W2 Witheld:</Label>
                    <TextInput id='w2WitheldInput' name='w2WitheldInput' type='number'/>

                    <Label htmlFor="ageInput">Age:</Label>
                    <TextInput id='ageInput' name='ageInput' type='number'/>

                    <Label htmlFor="1099IncomeInput">1099 Income:</Label>
                    <TextInput id='1099IncomeInput' name='1099IncomeInput' type='number'/>

                    <Label htmlFor="1099TaxPaidInput">1099 Taxes Paid:</Label>
                    <TextInput id='1099TaxPaidInput' name='1099TaxPaidInput' type='number'/>

                    <Button type="submit">Next</Button>
                    
                </Form>
            </div>
        </>
    )
}