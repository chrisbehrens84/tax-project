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
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxCalculations;
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

        TaxInformation taxInformation1 = new TaxInformation("Single", 2, 50000, 4000, true, 45, 45000, 5000, user);

        TaxInformation taxInformation2 = new TaxInformation("Maarried filing Jointly", 1, 5000, 10000, true, 30, 30000,
                1500, user1);

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

        TaxInformation taxInformation1 = new TaxInformation("Single", 2, 50000, 4000, true, 45, 45000, 5000, user);

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

        TaxInformation taxInformation1 = new TaxInformation("Single", 2, 50000, 4000, true, 45, 45000, 5000, user);
        taxInformationList.add(taxInformation1);

        // Mock the behavior of the taxInformationRepository.findByUser_Id() method
        when(taxInformationRepository.findByUser_Id("1")).thenReturn(taxInformationList);

        // Call the method under test
        List<TaxInformation> result = taxInformationService.getTaxInformationByUserId("1");

        // Assert that the method returns the expected list of TaxInformation objects
        assertEquals(taxInformationList, result);
    }

    // @Test
    // public void testUpdateTaxInformation_Success() {
    // // Create a mock TaxInformation object with updated attributes
    // AppUser user = new AppUser();
    // user.setId("1");

    // List<TaxInformation> taxInformationList = new ArrayList<>();

    // TaxInformation taxInformation1 = new TaxInformation( "Single",
    // 2,50000,4000,true,45,45000,5000,user );
    // taxInformationList.add(taxInformation1);

    // TaxInformation updatedTaxInformation = new TaxInformation( "Single",
    // 1,50000,4000,true,45,45000,5000,user );

    // // Mock the behavior of the taxInformationRepository.findById() method
    // when(taxInformationRepository.findById("1")).thenReturn(Optional.of(taxInformation1));
    // // Set original attributes as needed

    // // Mock the behavior of the taxInformationRepository.save() method
    // when(taxInformationRepository.save(any())).thenReturn(updatedTaxInformation);

    // // Call the method under test
    // TaxInformation result = taxInformationService.updateTaxInformation("1",
    // updatedTaxInformation);

    // // Assert that the method returns the updated TaxInformation object
    // assertEquals(updatedTaxInformation, result);

    // // Verify that the taxCalculationsService.callCreateTaxCalculations() method
    // is called with the correct taxInformationId
    // verify(taxInformationService, times(1)).callCreateTaxCalculations("1");
    // }

    @Test
    public void testUpdateTaxInformation_Success() {
        // Create a mock AppUser object
        AppUser user = new AppUser();
        user.setId("1");

        // Create a mock TaxInformation object with updated attributes
        TaxInformation updatedTaxInfo = new TaxInformation("Married", 3, 60000, 4500, false, 30, 50000, 5500, user);

        // Mock the behavior of the taxInformationRepository.findById() method
        TaxInformation existingTaxInfo = new TaxInformation("Single", 2, 50000, 4000, true, 45, 45000, 5000, user);
        existingTaxInfo.setId("2");

        when(taxInformationRepository.findById("2")).thenReturn(Optional.of(existingTaxInfo));

        // Mock the behavior of the taxInformationRepository.save() method
        when(taxInformationRepository.save(any())).thenReturn(updatedTaxInfo);

        // Mock the behavior of the
        // taxCalculationsService.getTaxCalculationsByTaxInformationId() method
        TaxCalculations taxCalculations = new TaxCalculations();
        ResponseEntity<TaxCalculations> responseEntity = ResponseEntity.ok(taxCalculations);
        when(taxCalculationsService.getTaxCalculationsByTaxInformationId("2")).thenReturn(responseEntity);

        // Call the method under test
        TaxInformation result = taxInformationService.updateTaxInformation("2", updatedTaxInfo);

        // Verify that taxInformationRepository.findById() was called with the correct
        // argument
        verify(taxInformationRepository).findById("2");

        // Verify that taxInformationRepository.save() was called with the
        // existingTaxInfo object
        verify(taxInformationRepository).save(existingTaxInfo);

        // Verify that taxCalculationsService.getTaxCalculationsByTaxInformationId() was
        // called with the correct argument
        verify(taxCalculationsService).getTaxCalculationsByTaxInformationId("2");

        // Verify that taxCalculationsService.deleteTaxCalculationsById() was called
        // with the correct argument
        verify(taxCalculationsService).deleteTaxCalculationsById(any());

        // check that the returned TaxInformation object is the same as the
        // one passed in
        assertEquals(updatedTaxInfo, result);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateTaxInformation_TaxInfoNotFound() {
        // Mock the behavior of the taxInformationRepository.findById() method when taxInformation is not found
        when(taxInformationRepository.findById("1")).thenReturn(Optional.empty());

        // Call the method under test with a non-existing taxInformationId
        taxInformationService.updateTaxInformation("1", new TaxInformation());
    }

    @Test
    public void testDeleteTaxInformationById_Success() {
        // Create a mock TaxInformation object and set its ID
        TaxInformation taxInformation = new TaxInformation();
        String taxInfoId = "1";
        taxInformation.setId(taxInfoId);

        // Mock the behavior of the taxInformationRepository.findById() method
        when(taxInformationRepository.findById(taxInfoId)).thenReturn(Optional.of(taxInformation));

        // Mock the behavior of the
        // taxCalculationsService.getTaxCalculationsByTaxInformationId() method
        TaxCalculations taxCalculations = new TaxCalculations();
        ResponseEntity<TaxCalculations> responseEntity = ResponseEntity.ok(taxCalculations);
        when(taxCalculationsService.getTaxCalculationsByTaxInformationId(taxInfoId)).thenReturn(responseEntity);

        // Call the method under test
        taxInformationService.deleteTaxInformationById(taxInfoId);

        // Verify that taxCalculationsService.deleteTaxCalculationsById() was called
        // with the correct argument
        verify(taxCalculationsService).deleteTaxCalculationsById(taxCalculations.getId());

        // Verify that taxInformationRepository.deleteById() was called with the correct
        // argument
        verify(taxInformationRepository).deleteById(taxInfoId);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteTaxInformationById_TaxInfoNotFound() {
        // Mock the behavior of the taxCalculationsService.getTaxCalculationsByTaxInformationId() method when taxInformation is not found
        when(taxCalculationsService.getTaxCalculationsByTaxInformationId(any())).thenReturn(null);

        // Call the method under test with a non-existing taxInformationId
        taxInformationService.deleteTaxInformationById("1");
    }

}
