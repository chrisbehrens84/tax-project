
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import LoginPage from './components/pages/LoginPage'
import SignupPage from './components/pages/SignupPage'
import LandingPage from './components/pages/LandingPage'
import PersonalInfoPage from './components/pages/PersonalInfoPage'
import TaxInfoPage from './components/pages/TaxInfoPage'
import TaxResultsPage from './components/pages/TaxResultsPage'
import { Address, Button, Footer, Header, Logo, NavMenuButton, PrimaryNav, Title } from '@trussworks/react-uswds'
import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { setEmail, setId, setPassword, setTaxInfoId } from './slices/userSlice'
import { useTranslation } from 'react-i18next'

export default function App(){

    const {t, i18n} = useTranslation();

    const [expanded, setExpanded] = useState(false);
    const user = useSelector((store : any) => store.user);
    const dispatch = useDispatch();


    const [menuItems, setMenuItems] = useState([<Link to={"/"}>Login</Link>,
        <Link to={"/signup"}>Signup</Link>,
        <Link to={"/home"}>Home</Link>,
        <Link to={"/personal_info"}>Personal Info</Link>,
        <Link to={"/tax_info"}>Tax Info</Link>,
        <Link to={"/results"}>Results</Link> ]);

    function clearUser(){
        dispatch(setId(""));
        dispatch(setEmail(""));
        dispatch(setPassword(""));
        dispatch(setTaxInfoId(""));
    }
    useEffect(() => {
        if(user.id == ""){
            setMenuItems([<Link to={"/"}>Login</Link>,
                          <Button type='button' onClick={toggleLanguage}>{t("Change Language")}</Button>])
        }
        else{
            setMenuItems([<Link to={"/home"}>Home</Link>,
                          <Link to={"/"} onClick={clearUser}>Logout</Link>,
                          <Button type='button' onClick={toggleLanguage}>{t("Change Language")}</Button>])
        }
    }, [user.id]);

    function toggleLanguage(){
        if (i18n.language == "en"){
            i18n.changeLanguage("es");
        }
        else{
            i18n.changeLanguage("en");
        }
    }
        
    return(
        <>
            <BrowserRouter>
                <div  style={{height : "100vh"}}>
                    <div className={`usa-overlay ${expanded ? 'is-visible' : ''}`}></div>
                    <Header basic={true} className='bg-accent-cool-lighter' >
                        <div className='usa-nav-container'>
                            <div className='usa-navbar'>
                                <Title>{t("appTitle")}</Title>
                                <NavMenuButton onClick={() =>{setExpanded(!expanded)}} label="Menu" />
                            </div>
                            <PrimaryNav items={menuItems} mobileExpanded={expanded} onToggleMobileNav={() =>{setExpanded(!expanded)}}></PrimaryNav>
                            
                        </div>
                    </Header>
                    <div style={{}}>
                        <Routes>
                            <Route path="/" element={<LoginPage/>} />
                            <Route path="/signup" element={<SignupPage/>} />
                            <Route path="/home" element={<LandingPage/>} />
                            <Route path="/personal_info" element={<PersonalInfoPage/>} />
                            <Route path="/tax_info" element={<TaxInfoPage/>} />
                            <Route path="/results" element={<TaxResultsPage/>} />
                        </Routes>
                    </div>
                    <Footer
                        //className='bg-primary-light'
                        //style={{position:"absolute", left:0, bottom:0, right:0}}
                        size="slim"
                        primary={<></>}
                        secondary={
                            <>
                                <Logo
                                    size="slim"
                                    image={
                                    <img
                                        className="usa-footer__logo-img"
                                        alt="img alt text"
                                        src={"./src/assets/Logo_of_the_Internal_Revenue_Service.svg.png"}
                                    />
                                    }
                                    heading={<p className="usa-footer__logo-heading">Internal Revenue Services</p>}
                                />
                                <Address
                                    size="slim"
                                    items={[
                                    <a key="telephone" href="tel:1-800-555-5555">
                                        (800) 555-5555
                                    </a>,
                                    <a key="email" href="mailto:fpolicastro@skillstorm.com">
                                        fpolicastro@skillstorm.com
                                    </a>,
                                    ]}
                                />

                            </>
                        }
                    />
                </div>
            </BrowserRouter>
        </>
    )
}