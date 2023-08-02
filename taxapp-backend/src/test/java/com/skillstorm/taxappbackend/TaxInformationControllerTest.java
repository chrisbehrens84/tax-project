package com.skillstorm.taxappbackend;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.taxappbackend.controllers.TaxInformationController;
import com.skillstorm.taxappbackend.models.AppUser;
import com.skillstorm.taxappbackend.models.TaxInformation;
import com.skillstorm.taxappbackend.services.AppUserService;
import com.skillstorm.taxappbackend.services.TaxInformationService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TaxInformationControllerTest {

        @Mock
        private TaxInformationService taxInformationService;

        @Mock
        private AppUserService appUserService;

        @InjectMocks
        private TaxInformationController taxInformationController;

        private MockMvc mockMvc;
        private ObjectMapper objectMapper;

        @Before
        public void setUp() {
                objectMapper = new ObjectMapper();
                mockMvc = MockMvcBuilders.standaloneSetup(taxInformationController).build();
        }

        @Test
        public void testSaveTaxInformation_Success() throws Exception {
                String userId = "123";
                AppUser user = new AppUser();
                user.setId(userId);

                TaxInformation taxInformation = new TaxInformation();
                taxInformation.setId("1");

                when(appUserService.getUserById(userId)).thenReturn(user);
                when(taxInformationService.saveTaxInformation(any())).thenReturn(taxInformation);

                mockMvc.perform(MockMvcRequestBuilders.post("/tax-information/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(taxInformation)))
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value("1"));

                verify(appUserService, times(1)).getUserById(userId);
                verify(taxInformationService, times(1)).saveTaxInformation(any());
        }

        @Test
        public void testSaveTaxInformation_UserNotFound() throws Exception {
                String userId = "nonexistentId";
                when(appUserService.getUserById(userId)).thenReturn(null);

                mockMvc.perform(MockMvcRequestBuilders.post("/tax-information/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(new TaxInformation())))
                                .andExpect(status().isNotFound());

                verify(appUserService, times(1)).getUserById(userId);
                verify(taxInformationService, never()).saveTaxInformation(any());
        }

        @Test
        public void testGetAllTaxInformation() throws Exception {
                List<TaxInformation> taxInformationList = new ArrayList<>();
                AppUser user = new AppUser();
                TaxInformation taxInformation1 = new TaxInformation("Single", 2, 50000, 4000, true, 45, 45000, 5000,
                                user);

                TaxInformation taxInformation2 = new TaxInformation("Maarried filing Jointly", 1, 5000, 10000, true, 30,
                                30000,
                                1500, user);

                taxInformationList.add(taxInformation1);
                taxInformationList.add(taxInformation2);

                when(taxInformationService.getAllTaxInformation()).thenReturn(taxInformationList);

                mockMvc.perform(MockMvcRequestBuilders.get("/tax-information"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$", hasSize(taxInformationList.size())));
                System.out.println("**************************");
                System.out.println(taxInformationList.size());
                System.out.println("**************************");
                // Add more assertions for the JSON response

                verify(taxInformationService, times(1)).getAllTaxInformation();
        }

        @Test
        public void testGetTaxInformationById_Success() throws Exception {
                String taxInformationId = "1";
                TaxInformation taxInformation = new TaxInformation();
                taxInformation.setId(taxInformationId);

                when(taxInformationService.getTaxInformationById(taxInformationId)).thenReturn(taxInformation);

                mockMvc.perform(MockMvcRequestBuilders.get("/tax-information/" + taxInformationId))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(taxInformationId));

                verify(taxInformationService, times(1)).getTaxInformationById(taxInformationId);
        }

        @Test
        public void testGetTaxInformationById_TaxInformationNotFound() throws Exception {
                String taxInformationId = "nonexistentId";
                when(taxInformationService.getTaxInformationById(taxInformationId)).thenReturn(null);

                mockMvc.perform(MockMvcRequestBuilders.get("/tax-information/" + taxInformationId))
                                .andExpect(status().isNotFound());

                verify(taxInformationService, times(1)).getTaxInformationById(taxInformationId);
        }

        @Test
        public void testGetTaxInformationByUserId_Success() throws Exception {
                String userId = "123";

                AppUser user = new AppUser();
                user.setId(userId);

                AppUser user1 = new AppUser();
                user.setId("321");

                List<TaxInformation> taxInformationList = new ArrayList<>();

                TaxInformation taxInformation1 = new TaxInformation("Single", 2, 50000, 4000, true, 45, 45000, 5000,
                                user);

                TaxInformation taxInformation2 = new TaxInformation("Maarried filing Jointly", 1, 5000, 10000, true, 30,
                                30000,
                                1500, user1);

                taxInformationList.add(taxInformation1);
                taxInformationList.add(taxInformation2);

                when(taxInformationService.getTaxInformationByUserId(userId)).thenReturn(taxInformationList);

                mockMvc.perform(MockMvcRequestBuilders.get("/tax-information/user/" + userId))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$", hasSize(taxInformationList.size())));

                verify(taxInformationService, times(1)).getTaxInformationByUserId(userId);
        }

        @Test
        public void testUpdateTaxInformation_Success() throws Exception {
                String taxInformationId = "1";
                String filingStatus = "Single";
                String filingStatus2 = "Married filing jointly";
                TaxInformation taxInformation = new TaxInformation();
                taxInformation.setId("1");
                taxInformation.setFilingStatus(filingStatus);
                // Set other attributes for updatedTaxInformation as needed

                TaxInformation updatedTaxInformation = new TaxInformation();

                updatedTaxInformation.setFilingStatus(filingStatus2);

                when(taxInformationService.updateTaxInformation(eq(taxInformationId), eq(updatedTaxInformation)))
                                .thenReturn(updatedTaxInformation);

                mockMvc.perform(MockMvcRequestBuilders.put("/tax-information/" + taxInformationId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updatedTaxInformation)))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.filingStatus").value("Married filing jointly"));

                verify(taxInformationService, times(1)).updateTaxInformation(eq(taxInformationId), any());
        }

        @Test
        public void testDeleteTaxInformationById_Success() throws Exception {
                String taxInformationId = "1";
                TaxInformation taxInformation = new TaxInformation();
                taxInformation.setId(taxInformationId);

                when(taxInformationService.getTaxInformationById(taxInformationId)).thenReturn(taxInformation);

                mockMvc.perform(MockMvcRequestBuilders.delete("/tax-information/" + taxInformationId))
                                .andExpect(status().isNoContent());

                verify(taxInformationService, times(1)).getTaxInformationById(taxInformationId);
                verify(taxInformationService, times(1)).deleteTaxInformationById(taxInformationId);
        }

        @Test
        public void testDeleteTaxInformationById_TaxInformationNotFound() throws Exception {
                String taxInformationId = "nonexistentId";
                when(taxInformationService.getTaxInformationById(taxInformationId)).thenReturn(null);

                mockMvc.perform(MockMvcRequestBuilders.delete("/tax-information/" + taxInformationId))
                                .andExpect(status().isNotFound());

                verify(taxInformationService, times(1)).getTaxInformationById(taxInformationId);
                verify(taxInformationService, never()).deleteTaxInformationById(taxInformationId);
        }

}
