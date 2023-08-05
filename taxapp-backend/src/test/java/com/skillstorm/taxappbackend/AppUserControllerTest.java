package com.skillstorm.taxappbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.taxappbackend.controllers.AppUserController;
import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.services.AppUserService;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AppUserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppUserController appUserController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(appUserController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser());
        userList.add(new AppUser());
        when(appUserService.getAllUsers()).thenReturn(userList);

        // Act & Assert
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetUserById_ExistingUser() throws Exception {
        // Arrange
        String userId = "12345";
        AppUser user = new AppUser();
        when(appUserService.getUserById(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()));
    }

    @Test
    public void testGetUserById_UserNotFound() throws Exception {
        // Arrange
        String userId = "12345";
        when(appUserService.getUserById(userId)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testCreateUser() throws Exception {
        // Arrange
        String email = "test@example.com";
        String password = "test123";
        AppUser newUser = new AppUser(email, password);
    
        // Mock the service call to create the user
        when(appUserService.createUser(email, password)).thenReturn(newUser);
    
        // Act & Assert
        mockMvc.perform(post("/users")
                .param("email", email)
                .param("password", password))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", Matchers.equalTo(email))) 
                .andExpect(jsonPath("$.password", Matchers.equalTo(password))); 
    }



    @Test
    public void testUpdateUser_UserNotFound() throws Exception {
        // Arrange
        String userId = "12345";
        AppUser updatedUser = new AppUser();

        when(appUserService.updateUser(userId, updatedUser)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(put("/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUserById_ExistingUser() throws Exception {
        // Arrange
        String userId = "12345";
        when(appUserService.getUserById(userId)).thenReturn(new AppUser());

        // Act & Assert
        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteUserById_UserNotFound() throws Exception {
        // Arrange
        String userId = "12345";
        when(appUserService.getUserById(userId)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNotFound());
    }
}

