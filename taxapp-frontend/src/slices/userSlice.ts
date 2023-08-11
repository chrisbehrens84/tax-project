import { createSlice } from "@reduxjs/toolkit";



const userSlice = createSlice({
    name : 'user',
    initialState : {
        id : "",
        email : "",
        password : "",
        taxInfoId : ""
    },
    reducers : {
        setId : (state, action) => {
            state.id = action.payload;
        },
        setEmail : (state, action) => {
            state.email = action.payload;
        },
        setPassword : (state, action) => {
            state.password = action.payload;
        },
        setTaxInfoId : (state, action) => {
            state.taxInfoId = action.payload;
        }
    }

});

export default userSlice.reducer;

export const {setId, setEmail, setPassword, setTaxInfoId} = userSlice.actions;