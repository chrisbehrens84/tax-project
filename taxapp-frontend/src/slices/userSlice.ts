import { createSlice } from "@reduxjs/toolkit";



const userSlice = createSlice({
    name : 'user',
    initialState : {
        id : null
    },
    reducers : {
        set : (state, action) => {
            state.id = action.payload;
        }
    }

});

export default userSlice.reducer;

export const {set} = userSlice.actions;