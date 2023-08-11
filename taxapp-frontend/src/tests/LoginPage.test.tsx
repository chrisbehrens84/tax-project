//import React from "react";
import renderer from "react-test-renderer"
import LoginPage from "../components/pages/LoginPage";

const mockUsedNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
   ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockUsedNavigate,
}));


it('Login Page rendering', () =>{

    //Creates the json data for HTML tree
    const tree : any = renderer.create(<LoginPage/>).toJSON();

    //Compares to existing snapshot OR generates new one
    expect(tree).toMatchSnapshot();
    
});