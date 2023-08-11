//import React from "react";
import renderer from "react-test-renderer"
import SignupPage from "../components/pages/SignupPage";

const mockUsedNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
   ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockUsedNavigate,
}));


it('Sign Up Page Rendering', () =>{

    //Creates the json data for HTML tree
    const tree : any = renderer.create(<SignupPage/>).toJSON();

    //Compares to existing snapshot OR generates new one
    expect(tree).toMatchSnapshot();
    
});