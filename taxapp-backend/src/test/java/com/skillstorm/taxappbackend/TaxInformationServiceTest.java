package com.skillstorm.taxappbackend;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;
import com.skillstorm.taxappbackend.services.TaxCalculationsService;
import com.skillstorm.taxappbackend.services.TaxInformationService;

@RunWith(MockitoJUnitRunner.class)
public class TaxInformationServiceTest {

    @InjectMocks
    private TaxInformationService taxInformationService;

    @Mock
    private TaxInformationRepository taxInformationRepository;

    @Mock
    private TaxCalculationsService taxCalculationsService;

    @Test
    public void testSaveTaxInformation_Success() {
        // Create a mock TaxInformation object to be saved
        TaxInformation taxInformation = new TaxInformation();
        taxInformation.setId("1");
        // Set other attributes for the taxInformation object as needed

        // Mock the behavior of the taxInformationRepository.save() method
        when(taxInformationRepository.save(taxInformation)).thenReturn(taxInformation);

        // Call the method under test
        TaxInformation savedTaxInformation = taxInformationService.saveTaxInformation(taxInformation);

        // Assert that the method returns the saved TaxInformation object
        assertEquals(taxInformation, savedTaxInformation);

        
    }

    @Test
    public void testGetAllTaxInformation_Success() {
        // Create a list of mock TaxInformation objects
       AppUser user = new AppUser();
       AppUser user1 = new AppUser();
      
       List<TaxInformation> taxInformationList = new ArrayList<>();
        
        TaxInformation taxInformation1 = new TaxInformation( "Single", 2,50000,4000,true,45,45000,5000,user );
        
        TaxInformation taxInformation2 = new TaxInformation( "Maarried filing Jointly", 1,5000,10000,true,30,30000,1500,user1 );
        
        taxInformationList.add(taxInformation1);
        taxInformationList.add(taxInformation2);

        // Mock the behavior of the taxInformationRepository.findAll() method
        when(taxInformationRepository.findAll()).thenReturn(taxInformationList);

        // Call the method under test
        List<TaxInformation> result = taxInformationService.getAllTaxInformation();

        // Assert that the method returns the expected list of TaxInformation objects
        assertEquals(taxInformationList, result);
    }

    @Test
    public void testGetTaxInformationById_Success() {
        // Create a mock TaxInformation object
        AppUser user = new AppUser();
        List<TaxInformation> taxInformationList = new ArrayList<>();
        
        TaxInformation taxInformation1 = new TaxInformation( "Single", 2,50000,4000,true,45,45000,5000,user );

        taxInformation1.setId("123");
        
        // Mock the behavior of the taxInformationRepository.findById() method
        when(taxInformationRepository.findById("123")).thenReturn(Optional.of(taxInformation1));

        // Call the method under test
        TaxInformation result = taxInformationService.getTaxInformationById("123");

        // Assert that the method returns the expected TaxInformation object
        assertEquals(taxInformation1, result);
    }

    @Test
    public void testGetTaxInformationByUserId_Success() {
        // Create a list of mock TaxInformation objects
        AppUser user = new AppUser();
        user.setId("1");
      
       List<TaxInformation> taxInformationList = new ArrayList<>();
        
        TaxInformation taxInformation1 = new TaxInformation( "Single", 2,50000,4000,true,45,45000,5000,user );
        taxInformationList.add(taxInformation1);
        
        // Mock the behavior of the taxInformationRepository.findByUser_Id() method
        when(taxInformationRepository.findByUser_Id("1")).thenReturn(taxInformationList);

        // Call the method under test
        List<TaxInformation> result = taxInformationService.getTaxInformationByUserId("1");

        // Assert that the method returns the expected list of TaxInformation objects
        assertEquals(taxInformationList, result);
    }

    @Test
    public void testUpdateTaxInformation_Success() {
        // Create a mock TaxInformation object with updated attributes
        AppUser user = new AppUser();
        user.setId("1");
      
        List<TaxInformation> taxInformationList = new ArrayList<>();
        
        TaxInformation taxInformation1 = new TaxInformation( "Single", 2,50000,4000,true,45,45000,5000,user );
        taxInformationList.add(taxInformation1);

        TaxInformation updatedTaxInformation = new TaxInformation( "Single", 1,50000,4000,true,45,45000,5000,user );
       

        // Mock the behavior of the taxInformationRepository.findById() method
        when(taxInformationRepository.findById("1")).thenReturn(Optional.of(taxInformation1)); // Set original attributes as needed

        // Mock the behavior of the taxInformationRepository.save() method
        when(taxInformationRepository.save(any())).thenReturn(updatedTaxInformation);

        // Call the method under test
        TaxInformation result = taxInformationService.updateTaxInformation("1", updatedTaxInformation);

        // Assert that the method returns the updated TaxInformation object
        assertEquals(updatedTaxInformation, result);

        // Verify that the taxCalculationsService.callCreateTaxCalculations() method is called with the correct taxInformationId
        verify(taxInformationService, times(1)).callCreateTaxCalculations("1");
    }

    @Test
    public void testDeleteTaxInformationById_Success() {
        // Create a mock TaxInformation object
        TaxInformation taxInformation = new TaxInformation();
        taxInformation.setId("1");
        // Set other attributes for taxInformation as needed

        // Mock the behavior of the taxInformationRepository.findById() method
        when(taxInformationRepository.findById("1")).thenReturn(Optional.of(taxInformation));

        // Call the method under test
        taxInformationService.deleteTaxInformationById("1");

        // Verify that the taxInformationRepository.deleteById() and taxCalculationsService.deleteTaxCalculationsById() methods are called with the correct taxInformationId
        verify(taxInformationRepository, times(1)).deleteById("1");
        verify(taxCalculationsService, times(1)).deleteTaxCalculationsById(anyString());
    }

}


