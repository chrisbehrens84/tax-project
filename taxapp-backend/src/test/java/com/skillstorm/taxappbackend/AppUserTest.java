package com.skillstorm.taxappbackend;

import org.junit.Assert;
import org.junit.Test;

import com.skillstorm.taxappbackend.models.AppUser;

public class AppUserTest {

    @Test
    public void testAppUserCreation() {
        // Arrange
        String email = "test@example.com";
        String password = "test123";
        String firstName = "John";
        String lastName = "Doe";
        Integer ssn = 123456789;
        String address = "123 Main Street";
        String city = "New York";
        Integer zip = 10001;
        String role = "ROLE_USER";

        // Act
        AppUser appUser = new AppUser(email, password, firstName, lastName, ssn, address, city, zip, role);

        // Assert
        Assert.assertEquals(email, appUser.getEmail());
        Assert.assertEquals(password, appUser.getPassword());
        Assert.assertEquals(firstName, appUser.getFirstName());
        Assert.assertEquals(lastName, appUser.getLastName());
        Assert.assertEquals(ssn, appUser.getSsn());
        Assert.assertEquals(address, appUser.getAddress());
        Assert.assertEquals(city, appUser.getCity());
        Assert.assertEquals(zip, appUser.getZip());
        Assert.assertEquals(role, appUser.getRole());
    }

    @Test
    public void testAppUserSetters() {
        // Arrange
        AppUser appUser = new AppUser();

        // Act
        appUser.setEmail("test@example.com");
        appUser.setPassword("test123");
        appUser.setFirstName("John");
        appUser.setLastName("Doe");
        appUser.setSsn(123456789);
        appUser.setAddress("123 Main Street");
        appUser.setCity("New York");
        appUser.setZip(10001);

        // Assert
        Assert.assertEquals("test@example.com", appUser.getEmail());
        Assert.assertEquals("test123", appUser.getPassword());
        Assert.assertEquals("John", appUser.getFirstName());
        Assert.assertEquals("Doe", appUser.getLastName());
        Assert.assertEquals(Integer.valueOf(123456789), appUser.getSsn());
        Assert.assertEquals("123 Main Street", appUser.getAddress());
        Assert.assertEquals("New York", appUser.getCity());
        Assert.assertEquals(Integer.valueOf(10001), appUser.getZip());
    }
}

