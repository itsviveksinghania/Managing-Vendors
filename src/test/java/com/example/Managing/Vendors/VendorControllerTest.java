package com.example.Managing.Vendors;

import com.example.Managing.Vendors.Controller.VendorController;
import com.example.Managing.Vendors.DTO.VendorDTO;
import com.example.Managing.Vendors.Entity.Vendor;
import com.example.Managing.Vendors.Service.VendorService;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;


public class VendorControllerTest {

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("Test Address");
        vendorDTO.setCity("Test City");
        vendorDTO.setState("Test State");
        vendorDTO.setPinCode("123456");
        vendorDTO.setPhone("1234567890");
        vendorDTO.setEmail("test@example.com");

        when(vendorService.createVendor(vendorDTO)).thenReturn("Vendor Added Successfully");

        ResponseEntity<String> response = vendorController.createVendor(vendorDTO);

        verify(vendorService, times(1)).createVendor(vendorDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Vendor Added Successfully", response.getBody());

    }

    @Test
    public void testCreateVendorInvalidPinCode() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("Test Address");
        vendorDTO.setCity("Test City");
        vendorDTO.setState("Test State");
        vendorDTO.setPinCode("12356");
        vendorDTO.setPhone("1234567890");
        vendorDTO.setEmail("test@example.com");

        when(vendorService.createVendor(vendorDTO)).thenThrow(new Exception("Invalid Pin Code"));

        ResponseEntity<String> response = vendorController.createVendor(vendorDTO);

        verify(vendorService, times(1)).createVendor(vendorDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Pin Code", response.getBody());

    }
    @Test
    public void testCreateVendorInvalidEmail() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("Test Address");
        vendorDTO.setCity("Test City");
        vendorDTO.setState("Test State");
        vendorDTO.setPinCode("123456");
        vendorDTO.setPhone("1234567890");
        vendorDTO.setEmail("testexamplecom");

        when(vendorService.createVendor(vendorDTO)).thenThrow(new Exception("Invalid Email"));

        ResponseEntity<String> response = vendorController.createVendor(vendorDTO);

        verify(vendorService, times(1)).createVendor(vendorDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Email", response.getBody());

    }

    @Test
    public void testCreateVendorInvalidPhone() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("Test Address");
        vendorDTO.setCity("Test City");
        vendorDTO.setState("Test State");
        vendorDTO.setPinCode("123456");
        vendorDTO.setPhone("123456789");
        vendorDTO.setEmail("test@example.com");

        when(vendorService.createVendor(vendorDTO)).thenThrow(new Exception("Invalid Phone Number"));

        ResponseEntity<String> response = vendorController.createVendor(vendorDTO);

        verify(vendorService, times(1)).createVendor(vendorDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Phone Number", response.getBody());

    }
    //--------------------------------------------------------------------------------------

    @Test
    public void testGetVendorById() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(1L);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        when(vendorService.getVendorById(1L)).thenReturn(Optional.of(vendor));

        ResponseEntity<Vendor> responseEntity = vendorController.getVendorById(1L);

        verify(vendorService, times(1)).getVendorById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vendor, responseEntity.getBody());
    }

    @Test
    public void testGetVendorByInvalidId() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(1L);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        when(vendorService.getVendorById(5L)).thenReturn(Optional.empty());

        ResponseEntity<Vendor> responseEntity = vendorController.getVendorById(5L);
        verify(vendorService, times(1)).getVendorById(5L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
    //--------------------------------------------------------------------------------------

    @Test
    public void testGetVendorByCode() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(1L);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        when(vendorService.getVendorByCode("TEST1234")).thenReturn(Optional.of(vendor));

        ResponseEntity<Vendor> responseEntity = vendorController.getVendorByCode("TEST1234");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vendor, responseEntity.getBody());

        verify(vendorService, times(1)).getVendorByCode("TEST1234");
    }

    @Test
    public void testGetVendorByInvalidCode() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(1L);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        when(vendorService.getVendorByCode("TEST123")).thenReturn(Optional.empty());

        ResponseEntity<Vendor> responseEntity = vendorController.getVendorByCode("TEST123");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        verify(vendorService, times(1)).getVendorByCode("TEST123");
    }
    //--------------------------------------------------------------------------------------

    @Test
    public void testUpdateVendor() throws Exception {
        Long id = 1L;

        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("vendorName", "newVendorName");

        when(vendorService.updateVendor(id, updates)).thenReturn("Updated");

        ResponseEntity<String> responseEntity = vendorController.updateVendor(id, updates);
        verify(vendorService, times(1)).updateVendor(id, updates);

        assertAll(() -> {
            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
            assertEquals("Updated", responseEntity.getBody());
        });
    }

    @Test
    public void testUpdateVendorInvalidPhone() throws Exception{
        Long id = 1L;

        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("phone", "123456789");

        when(vendorService.updateVendor(id, updates)).thenThrow(new Exception("Trying to update invalid Phone"));

        ResponseEntity<String> responseEntity = vendorController.updateVendor(id, updates);
        verify(vendorService, times(1)).updateVendor(eq(id), anyMap());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Trying to update invalid Phone", responseEntity.getBody());
    }

    @Test
    public void testUpdateVendorInvalidEmail() throws Exception{
        Long id = 1L;

        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("email", "testexample.com");

        when(vendorService.updateVendor(id, updates)).thenThrow(new Exception("Trying to update invalid E-mail"));

        ResponseEntity<String> responseEntity = vendorController.updateVendor(id, updates);
        verify(vendorService, times(1)).updateVendor(eq(id), anyMap());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Trying to update invalid E-mail", responseEntity.getBody());
    }

    @Test
    public void testUpdateVendorInvalidPinCode() throws Exception{
        Long id = 1L;

        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("pinCode", "3453");

        when(vendorService.updateVendor(id, updates)).thenThrow(new Exception("Trying to update invalid PinCode"));

        ResponseEntity<String> responseEntity = vendorController.updateVendor(id, updates);
        verify(vendorService, times(1)).updateVendor(eq(id), anyMap());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Trying to update invalid PinCode", responseEntity.getBody());
    }

    //testUpdateVendorInvalidID
    @Test
    public void testUpdateVendorInvalidID() throws Exception{
        Long id = 1L;
        Long testId = 2L;
        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("TEST1234");
        vendor.setAddress("Test Address");
        vendor.setCity("Test City");
        vendor.setState("Test State");
        vendor.setPinCode("123456");
        vendor.setPhone("1234567890");
        vendor.setEmail("test@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("pinCode", "345378");

        when(vendorService.updateVendor(testId, updates)).thenThrow(new Exception("Vendor not found with id: " + testId));

        ResponseEntity<String> responseEntity = vendorController.updateVendor(testId, updates);
        verify(vendorService, times(1)).updateVendor(testId, updates);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Vendor not found with id: " + testId, responseEntity.getBody());
    }
    //--------------------------------------------------------------------------------------

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(new Vendor(1L, "Vendor 1", "V001", "testAddress", "testCity", "testState", "123456", "1234567890", "test@example.com"));
        vendors.add(new Vendor(2L, "Vendor 2", "V002", "testAddress2", "testCity2", "testState2", "654321", "0987654321", "test2@example.com"));
        vendors.add(new Vendor(3L, "Vendor 3", "V003","testAddress3", "testCity3", "testState3", "567890", "5432167890", "test3@example.com"));

        when(vendorService.getAllVendors()).thenReturn(vendors);

        List<Vendor> response = vendorController.getAllVendors();

        verify(vendorService, times(1)).getAllVendors();
        assertEquals(3, response.size());
        assertEquals(vendors.get(0).getVendorName(), response.get(0).getVendorName());
    }
    //--------------------------------------------------------------------------------------

    @Test
    public void testDeleteVendor() {
        doNothing().when(vendorService).deleteVendor(1L);

        ResponseEntity<Void> responseEntity = vendorController.deleteVendor(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(vendorService, times(1)).deleteVendor(1L);
    }
}
