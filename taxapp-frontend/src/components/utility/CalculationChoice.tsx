import { Button } from "@trussworks/react-uswds";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { setTaxInfoId } from "../../slices/userSlice";
import { useTranslation } from "react-i18next";


export default function CalculationChoice({calculation} : any){

    const {t} = useTranslation();

    const navigate = useNavigate();
    const dispatch = useDispatch();

    function select(){
        dispatch(setTaxInfoId(calculation.id))
        navigate("/tax_info")
    }

    return(
        <>
            <div style={{border:"2px solid"}}>
                <p>{calculation.id}</p>
                <Button type="button" onClick={select}>{t("Select")}</Button>
            </div>
        </>
    )
}