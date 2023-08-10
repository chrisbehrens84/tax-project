package com.skillstorm.taxappbackend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;
import com.skillstorm.taxappbackend.services.AppUserService;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserService appUserService;

    @Test
    public void testCreateUser_Success() {
        String email = "test@example.com";
        String password = "test123";

        when(appUserRepository.existsByEmail(email)).thenReturn(false);

        AppUser newUser = new AppUser();
        newUser.setEmail(email);
        newUser.setPassword(password);

        when(appUserRepository.save(any(AppUser.class))).thenReturn(newUser);

        AppUser createdUser = appUserService.createUser(email, password);

        assertNotNull(createdUser);
        assertEquals(email, createdUser.getEmail());
        assertEquals(password, createdUser.getPassword());

        verify(appUserRepository, times(1)).existsByEmail(email);
        verify(appUserRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    public void testCreateUser_EmailExists() {
        String email = "existing@example.com";
        String password = "test123";

        when(appUserRepository.existsByEmail(email)).thenReturn(true);

        appUserService.createUser(email, password);
    }

    @Test
    public void testUpdateUser_Success() {
        String userId = "123";
        AppUser existingUser = new AppUser();
        existingUser.setId(userId);
        existingUser.setEmail("oldemail@example.com");
        existingUser.setPassword("oldpassword");

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        AppUser updatedUser = new AppUser();
        updatedUser.setId(userId);
        updatedUser.setEmail("newemail@example.com");
        updatedUser.setPassword("newpassword");

        when(appUserRepository.save(existingUser)).thenReturn(updatedUser);

        AppUser result = appUserService.updateUser(userId, updatedUser);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());

        verify(appUserRepository, times(1)).findById(userId);
        verify(appUserRepository, times(1)).save(existingUser);
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        String userId = "nonexistentId";

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        AppUser updatedUser = new AppUser();

        AppUser result = appUserService.updateUser(userId, updatedUser);

        assertNull(result);

        verify(appUserRepository, times(1)).findById(userId);
        verify(appUserRepository, times(0)).save(any(AppUser.class));
    }

    @Test
    public void testGetAllUsers() {
        List<AppUser> users = new ArrayList<>();

        when(appUserRepository.findAll()).thenReturn(users);

        List<AppUser> result = appUserService.getAllUsers();

        assertEquals(users.size(), result.size());

        verify(appUserRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_Success() {
        String userId = "123";
        AppUser user = new AppUser();
        user.setId(userId);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(user));

        AppUser result = appUserService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());

        verify(appUserRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        String userId = "nonexistentId";

        when(appUserRepository.findById(userId)).thenReturn(Optional.empty());

        AppUser result = appUserService.getUserById(userId);

        assertNull(result);

        verify(appUserRepository, times(1)).findById(userId);
    }

    @Test
    public void testDeleteUserById() {
        String userId = "123";

        appUserService.deleteUserById(userId);

        verify(appUserRepository, times(1)).deleteById(userId);
    }
}