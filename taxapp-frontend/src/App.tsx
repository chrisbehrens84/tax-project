
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import LoginPage from './components/pages/LoginPage'
import SignupPage from './components/pages/SignupPage'
import LandingPage from './components/pages/LandingPage'
import PersonalInfoPage from './components/pages/PersonalInfoPage'
import TaxInfoPage from './components/pages/TaxInfoPage'
import TaxResultsPage from './components/pages/TaxResultsPage'
import { Header, PrimaryNav, Title } from '@trussworks/react-uswds'
import { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'

export default function App(){

    const user = useSelector((store : any) => store.user);


    const [menuItems, setMenuItems] = useState([<Link to={"/"}>Login</Link>,
        <Link to={"/signup"}>Signup</Link>,
        <Link to={"/home"}>Home</Link>,
        <Link to={"/personal_info"}>Personal Info</Link>,
        <Link to={"/tax_info"}>Tax Info</Link>,
        <Link to={"/results"}>Results</Link> ]);

    useEffect(() => {
        if(user.id == ""){
            setMenuItems([<Link to={"/"}>Login</Link>])
        }
        else{
            setMenuItems([<Link to={"/home"}>Home</Link>])
        }
    }, [user.id]);
        
    return(
        <>
            <BrowserRouter>
                <Header basic={true}>
                    <div className='usa-nav-container'>
                        <div className='usa-navbar'>
                            <Title>Tax Calculator App</Title>
                        </div>
                        <PrimaryNav items={menuItems}></PrimaryNav>
                    </div>
                </Header>
                <Routes>
                    <Route path="/" element={<LoginPage/>} />
                    <Route path="/signup" element={<SignupPage/>} />
                    <Route path="/home" element={<LandingPage/>} />
                    <Route path="/personal_info" element={<PersonalInfoPage/>} />
                    <Route path="/tax_info" element={<TaxInfoPage/>} />
                    <Route path="/results" element={<TaxResultsPage/>} />
                </Routes>
            </BrowserRouter>
        </>
    )
}