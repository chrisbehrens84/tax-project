
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import LoginPage from './components/pages/LoginPage'
import SignupPage from './components/pages/SignupPage'
import LandingPage from './components/pages/LandingPage'
import PersonalInfoPage from './components/pages/PersonalInfoPage'
import TaxInfoPage from './components/pages/TaxInfoPage'
import TaxResultsPage from './components/pages/TaxResultsPage'
import { Header, PrimaryNav, Title } from '@trussworks/react-uswds'

export default function App(){


    const menuItems = [<Link to={"/"}>Login</Link>,
        <Link to={"/signup"}>Signup</Link>,
        <Link to={"/home"}>Home</Link>,
        <Link to={"/personal_info"}>Personal Info</Link>,
        <Link to={"/tax_info"}>Tax Info</Link>,
        <Link to={"/results"}>Results</Link> ];
        
    return(
        <>
            <BrowserRouter>
                <Header basic={true}>
                    <div className='usa-nav-container'>
                        <div className='usa-navbar'>
                            <Title>Tax Calculator</Title>
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