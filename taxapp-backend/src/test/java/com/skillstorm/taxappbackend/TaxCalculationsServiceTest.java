package com.skillstorm.taxappbackend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.repositories.TaxCalculationsRepository;
import com.skillstorm.taxappbackend.repositories.TaxInformationRepository;
import com.skillstorm.taxappbackend.services.TaxCalculationsService;

public class TaxCalculationsServiceTest {

    @Mock
    private TaxCalculationsRepository taxCalculationsRepository;

    @Mock
    private TaxInformationRepository taxInformationRepository;

    @InjectMocks
    private TaxCalculationsService taxCalculationsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveTaxCalculations() {
        // Create a mock TaxCalculations object
        TaxCalculations taxCalculations = new TaxCalculations();
        taxCalculations.setId("101");

        // Mock the behavior of the taxCalculationsRepository.save() method
        when(taxCalculationsRepository.save(taxCalculations)).thenReturn(taxCalculations);

        // Call the method under test
        TaxCalculations savedTaxCalculations = taxCalculationsService.saveTaxCalculations(taxCalculations);

        // Assert that the method returns the saved TaxCalculations object
        assertEquals(taxCalculations, savedTaxCalculations);
    }

    @Test
    public void testGetAllTaxCalculations() {
        // Create a list of mock TaxCalculations objects
        List<TaxCalculations> taxCalculationsList = new ArrayList<>();
        TaxCalculations taxCalculations1 = new TaxCalculations();
        taxCalculations1.setId("101");
        TaxCalculations taxCalculations2 = new TaxCalculations();
        taxCalculations2.setId("102");
        taxCalculationsList.add(taxCalculations1);
        taxCalculationsList.add(taxCalculations2);

        // Mock the behavior of the taxCalculationsRepository.findAll() method
        when(taxCalculationsRepository.findAll()).thenReturn(taxCalculationsList);

        // Call the method under test
        List<TaxCalculations> result = taxCalculationsService.getAllTaxCalculations();

        // Assert that the method returns the list of TaxCalculations objects
        assertEquals(taxCalculationsList, result);
    }

    @Test
    public void testGetTaxCalculationsById_TaxCalculationsFound() {
        // Create a mock TaxCalculations object and set its ID
        TaxCalculations taxCalculations = new TaxCalculations();
        String taxCalculationsId = "101";
        taxCalculations.setId(taxCalculationsId);

        // Mock the behavior of the taxCalculationsRepository.findById() method
        when(taxCalculationsRepository.findById(taxCalculationsId)).thenReturn(Optional.of(taxCalculations));

        // Call the method under test
        TaxCalculations result = taxCalculationsService.getTaxCalculationsById(taxCalculationsId);

        // Assert that the method returns the TaxCalculations object
        assertEquals(taxCalculations, result);
    }

    @Test
    public void testGetTaxCalculationsById_TaxCalculationsNotFound() {
        // Mock the behavior of the taxCalculationsRepository.findById() method when TaxCalculations is not found
        when(taxCalculationsRepository.findById(any())).thenReturn(Optional.empty());

        // Call the method under test with a non-existing TaxCalculations ID
        TaxCalculations result = taxCalculationsService.getTaxCalculationsById("999");

        // Assert that the method returns null
        assertNull(result);
    }

    @Test
    public void testGetTaxCalculationsByTaxInformationId_TaxCalculationsFound() {
        // Create a mock TaxCalculations object and set its ID
        TaxCalculations taxCalculations = new TaxCalculations();
        String taxInformationId = "1";
        taxCalculations.setId("101");

        // Mock the behavior of the taxCalculationsRepository.findByTaxInformation_Id()
        // method
        when(taxCalculationsRepository.findByTaxInformation_Id(taxInformationId))
                .thenReturn(Optional.of(taxCalculations));

        // Call the method under test
        ResponseEntity<TaxCalculations> responseEntity = taxCalculationsService
                .getTaxCalculationsByTaxInformationId(taxInformationId);

        // Assert that the response entity contains the expected TaxCalculations object
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(taxCalculations.getId(), responseEntity.getBody().getId());
    }

    @Test
    public void testGetTaxCalculationsByTaxInformationId_TaxCalculationsNotFound() {
        // Mock the behavior of the taxCalculationsRepository.findByTaxInformation_Id() method when TaxCalculations is not found
        when(taxCalculationsRepository.findByTaxInformation_Id(any())).thenReturn(Optional.empty());

        // Call the method under test with a non-existing TaxInformation ID
        ResponseEntity<TaxCalculations> responseEntity = taxCalculationsService.getTaxCalculationsByTaxInformationId("999");

        // Assert that the response entity returns HttpStatus.NOT_FOUND
        // assertNull(responseEntity);
        assertNull(responseEntity);
    }

    @Test
    public void testUpdateTaxCalculations_TaxCalculationsFound() {
        // Create a mock TaxCalculations object and set its ID
        TaxCalculations existingTaxCalculations = new TaxCalculations();
        String taxCalculationsId = "101";
        existingTaxCalculations.setId(taxCalculationsId);

        // Create a mock updated TaxCalculations object
        TaxCalculations updatedTaxCalculations = new TaxCalculations();
        updatedTaxCalculations.setId(taxCalculationsId);
        updatedTaxCalculations.setEffective_tax_rate(30);
        updatedTaxCalculations.setTotalCredits(500.0);

        // Mock the behavior of the taxCalculationsRepository.findById() method
        when(taxCalculationsRepository.findById(taxCalculationsId)).thenReturn(Optional.of(existingTaxCalculations));

        // Mock the behavior of the taxCalculationsRepository.save() method
        when(taxCalculationsRepository.save(existingTaxCalculations)).thenReturn(updatedTaxCalculations);

        // Call the method under test
        TaxCalculations result = taxCalculationsService.updateTaxCalculations(taxCalculationsId,
                updatedTaxCalculations);

        // Assert that the method returns the updated TaxCalculations object
        assertEquals(updatedTaxCalculations, result);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateTaxCalculations_TaxCalculationsNotFound() {
        // Mock the behavior of the taxCalculationsRepository.findById() method when TaxCalculations is not found
        when(taxCalculationsRepository.findById(any())).thenReturn(Optional.empty());

        // Call the method under test with a non-existing TaxCalculations ID
        TaxCalculations updatedTaxCalculations = new TaxCalculations();
        taxCalculationsService.updateTaxCalculations("999", updatedTaxCalculations);
    }

    @Test
    public void testDeleteTaxCalculationsById_TaxCalculationsFound() {
        // Create a mock TaxCalculations object and set its ID
        TaxCalculations taxCalculations = new TaxCalculations();
        String taxCalculationsId = "101";
        taxCalculations.setId(taxCalculationsId);

        // Mock the behavior of the taxCalculationsRepository.existsById() method
        when(taxCalculationsRepository.existsById(taxCalculationsId)).thenReturn(true);

        // Call the method under test
        ResponseEntity<Integer> responseEntity = taxCalculationsService.deleteTaxCalculationsById(taxCalculationsId);

        // Assert that the response entity returns HttpStatus.OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().intValue());
        verify(taxCalculationsRepository).deleteById(taxCalculationsId);
    }

    @Test
    public void testDeleteTaxCalculationsById_TaxCalculationsNotFound() {
        // Mock the behavior of the taxCalculationsRepository.existsById() method when TaxCalculations is not found
        when(taxCalculationsRepository.existsById(any())).thenReturn(false);

        // Call the method under test with a non-existing TaxCalculations ID
        ResponseEntity<Integer> responseEntity = taxCalculationsService.deleteTaxCalculationsById("999");

        // Assert that the response entity returns HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(taxCalculationsRepository, never()).deleteById(any());
    }

    // TODO: Doucle check assertions generated are correct
    @Test
    public void testGenerateTaxCalculations_TaxInformationFound() {
        // Create mock TaxInformation object
        TaxInformation taxInformation = new TaxInformation();
        taxInformation.setId("1");
        taxInformation.setFilingStatus("Single");
        taxInformation.setDependents(1);
        taxInformation.setW2Wages(60000);
        taxInformation.setW2Withheld(10000);
        taxInformation.setIsBlind(false);
        taxInformation.setAge(30);
        taxInformation.setIncome1099(5000);
        taxInformation.setTaxPaid1099(800);

        // Mock the behavior of the taxInformationRepository.findById() method
        when(taxInformationRepository.findById("1")).thenReturn(Optional.of(taxInformation));

        // Call the method under test
        TaxCalculations result = taxCalculationsService.generateTaxCalculations("1", new TaxCalculations());

        // Perform assertions on the generated TaxCalculations object
        assertNotNull(result);
        assertEquals(65000.0, result.getTotalIncome(), 0.001);
        assertEquals(10800.0, result.getTotalPaid(), 0.001);
        assertEquals(13850.0, result.getTotalDeductions(), 0.001);
        assertEquals(51150.0, result.getTotalTaxableIncome(), 0.001);
        assertEquals(6560.50, result.getNetTaxes(), 0.001);
        assertEquals(13, result.getEffective_tax_rate().intValue());
        assertEquals(2000.0, result.getTotalCredits(), 0.001);
        assertEquals(-6239.5, result.getFinalTaxes(), 0.001);
        assertEquals(4560.5, result.getTotalTaxWithCredits(), 0.001);
        assertEquals(22, result.getMarginalTaxRate().intValue());

        // Verify that taxCalculationsRepository.save() was called with the generated
        // TaxCalculations object
        verify(taxCalculationsRepository).save(result);
    }

    @Test(expected = RuntimeException.class)
    public void testGenerateTaxCalculations_TaxInformationNotFound() {
        // Mock the behavior of the taxInformationRepository.findById() method when TaxInformation is not found
        when(taxInformationRepository.findById(any())).thenReturn(Optional.empty());

        // Call the method under test with a non-existing TaxInformation ID
        taxCalculationsService.generateTaxCalculations("999", new TaxCalculations());
    }
}