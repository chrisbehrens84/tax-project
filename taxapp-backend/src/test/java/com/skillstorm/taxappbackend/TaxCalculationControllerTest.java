package com.skillstorm.taxappbackend;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxappbackend.controllers.TaxCalculationsController;
import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxCalculations;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.services.TaxCalculationsService;
import com.skillstorm.taxappbackend.services.TaxInformationService;

@RunWith(MockitoJUnitRunner.class)
public class TaxCalculationControllerTest {

    @Mock
    private TaxCalculationsService taxCalculationsService;

    @Mock
    private TaxInformationService taxInformationService;

    @InjectMocks
    private TaxCalculationsController taxCalculationsController;

    @Test
    public void testGenerateTaxCalculations_TaxInformationFound() {
        // Create a mock TaxInformation object and set its ID
        TaxInformation taxInformation = new TaxInformation();
        String taxInfoId = "1";
        taxInformation.setId(taxInfoId);

        // Mock the behavior of the taxInformationService.getTaxInformationById() method
        when(taxInformationService.getTaxInformationById(taxInfoId)).thenReturn(taxInformation);

        // Create a mock TaxCalculations object and set its ID
        TaxCalculations taxCalculations = new TaxCalculations();
        String taxCalculationsId = "101";
        taxCalculations.setId(taxCalculationsId);

        // Mock the behavior of the taxCalculationsService.generateTaxCalculations()
        // method
        when(taxCalculationsService.generateTaxCalculations(taxInfoId, taxCalculations)).thenReturn(taxCalculations);

        // Call the method under test
        ResponseEntity<TaxCalculations> responseEntity = taxCalculationsController.generateTaxCalculations(taxInfoId);

        // Assert that the response entity contains the expected TaxCalculations object
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGenerateTaxCalculations_TaxInfoNotFound() {
        // Mock the behavior of the taxInformationService.getTaxInformationById() method when taxInformation is not found
        when(taxInformationService.getTaxInformationById(any())).thenReturn(null);

        // Call the method under test with a non-existing taxInformationId
        ResponseEntity<TaxCalculations> responseEntity = taxCalculationsController.generateTaxCalculations("1");

        // Assert that the response entity returns HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testGetAllTaxCalculations_Success() {
        // Create a list of mock TaxCalculations objects
        List<TaxCalculations> taxCalculationsList = new ArrayList<>();
        taxCalculationsList.add(new TaxCalculations());
        taxCalculationsList.add(new TaxCalculations());
        taxCalculationsList.add(new TaxCalculations());

        // Mock the behavior of the taxCalculationsService.getAllTaxCalculations()
        // method
        when(taxCalculationsService.getAllTaxCalculations()).thenReturn(taxCalculationsList);

        // Call the method under test
        List<TaxCalculations> result = taxCalculationsController.getAllTaxCalculations();

        // Assert that the result list contains the expected number of TaxCalculations
        // objects
        assertEquals(taxCalculationsList.size(), result.size());
        assertEquals(taxCalculationsList, result);
    }

    @Test
    public void testGetTaxCalculationsById_Success() {
        // Create a mock TaxCalculations object and set its ID
        TaxCalculations taxCalculations = new TaxCalculations();
        String taxCalculationsId = "101";
        taxCalculations.setId(taxCalculationsId);

        // Mock the behavior of the taxCalculationsService.getTaxCalculationsById()
        // method
        when(taxCalculationsService.getTaxCalculationsById(taxCalculationsId)).thenReturn(taxCalculations);

        // Call the method under test with an existing taxCalculationsId
        ResponseEntity<TaxCalculations> responseEntity = taxCalculationsController
                .getTaxCalculationsById(taxCalculationsId);

        // Assert that the response entity contains the expected TaxCalculations object
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taxCalculations, responseEntity.getBody());
    }

    @Test
    public void testGetTaxCalculationsById_TaxCalculationsNotFound() {
        // Mock the behavior of the taxCalculationsService.getTaxCalculationsById() method when taxCalculations is not found
        when(taxCalculationsService.getTaxCalculationsById(any())).thenReturn(null);

        // Call the method under test with a non-existing taxCalculationsId
        ResponseEntity<TaxCalculations> responseEntity = taxCalculationsController.getTaxCalculationsById("101");

        // Assert that the response entity returns HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testGetTaxCalculationsByTaxInformationId_Success() {
        // Create a mock TaxCalculations object and set its ID
        TaxCalculations taxCalculations = new TaxCalculations();
        String taxCalculationsId = "101";
        taxCalculations.setId(taxCalculationsId);

        // Mock the behavior of the
        // taxCalculationsService.getTaxCalculationsByTaxInformationId() method
        ResponseEntity<TaxCalculations> responseEntity = ResponseEntity.ok(taxCalculations);
        when(taxCalculationsService.getTaxCalculationsByTaxInformationId("1")).thenReturn(responseEntity);

        // Call the method under test with an existing taxInformationId
        ResponseEntity<TaxCalculations> result = taxCalculationsController.getTaxCalculationsByTaxInformationId("1");

        // Assert that the result matches the mock response entity
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(taxCalculations, result.getBody());
    }

    @Test
    public void testGetTaxCalculationsByTaxInformationId_TaxCalculationsNotFound() {
        // Mock the behavior of the
        // taxCalculationsService.getTaxCalculationsByTaxInformationId() method when
        // taxCalculations is not found
        ResponseEntity<TaxCalculations> responseEntity = ResponseEntity.notFound().build();
        when(taxCalculationsService.getTaxCalculationsByTaxInformationId("1")).thenReturn(responseEntity);

        // Call the method under test with a non-existing taxInformationId
        ResponseEntity<TaxCalculations> result = taxCalculationsController.getTaxCalculationsByTaxInformationId("1");

        // Assert that the response entity returns HttpStatus.NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }
}