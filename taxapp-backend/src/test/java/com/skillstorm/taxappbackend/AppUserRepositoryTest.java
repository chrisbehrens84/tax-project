package com.skillstorm.taxappbackend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.repositories.AppUserRepository;
import com.skillstorm.taxappbackend.services.AppUserService;

@RunWith(MockitoJUnitRunner.class)
public class AppUserRepositoryTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;


    @Test
    public void testExistsByEmail_EmailExists() {
        String email = "existing@example.com";

        when(appUserRepository.existsByEmail(email)).thenReturn(true);

        boolean result = appUserRepository.existsByEmail(email);

        assertTrue(result);

        verify(appUserRepository, times(1)).existsByEmail(email);
    }

    @Test
    public void testExistsByEmail_EmailNotExists() {
        String email = "nonexistent@example.com";

        when(appUserRepository.existsByEmail(email)).thenReturn(false);

        boolean result = appUserRepository.existsByEmail(email);

        assertFalse(result);

        verify(appUserRepository, times(1)).existsByEmail(email);
    }

   
}

