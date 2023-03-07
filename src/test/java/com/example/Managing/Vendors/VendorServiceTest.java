package com.example.Managing.Vendors;

import com.example.Managing.Vendors.DTO.VendorDTO;
import com.example.Managing.Vendors.Entity.Vendor;
import com.example.Managing.Vendors.Repository.VendorRepository;
import com.example.Managing.Vendors.Service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllVendors() {
        List<Vendor> expectedVendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(expectedVendors);

        List<Vendor> actualVendors = vendorService.getAllVendors();

        assertEquals(expectedVendors, actualVendors);
        verify(vendorRepository, times(1)).findAll();
    }

    @Test
    void testGetVendorById() {
        Long vendorId = 1L;
        Vendor expectedVendor = new Vendor();
        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(expectedVendor));

        Optional<Vendor> actualVendorOptional = vendorService.getVendorById(vendorId);

        assertEquals(expectedVendor, actualVendorOptional.get());
        verify(vendorRepository, times(1)).findById(vendorId);
    }
    //--------------------------------------------------------------------------------------

    @Test
    void testGetVendorByCode() {
        String vendorCode = "abc123";
        Vendor vendor = new Vendor();
        vendor.setVendorId(3L);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("adafqf-acac-1131-caca-cac-0d23f7d2d6a3");
        vendor.setAddress("123 Main St");
        vendor.setCity("TestAnyTown");
        vendor.setState("NY");
        vendor.setPinCode("123456");
        vendor.setPhone("5555551234");
        vendor.setEmail("testvendor@example.com");


        when(vendorRepository.findByVendorCode(vendorCode)).thenReturn(Optional.of(vendor));

        Optional<Vendor> actualVendorOptional = vendorService.getVendorByCode(vendorCode);

        //assertEquals(expectedVendor, actualVendorOptional.get());
        assertAll(() -> vendor.getVendorName().equals(actualVendorOptional.get().getVendorName()),
                () -> vendor.getVendorId().equals(actualVendorOptional.get().getVendorId()),()-> vendor.getPinCode().equals(actualVendorOptional.get().getPinCode()),
                        ()-> vendor.getAddress().equals(actualVendorOptional.get().getAddress()),() -> vendor.getVendorCode().equals(actualVendorOptional.get().getVendorCode()),
                ()-> vendor.getCity().equals(actualVendorOptional.get().getCity()),() -> vendor.getState().equals(actualVendorOptional.get().getState()),
                ()-> vendor.getPhone().equals(actualVendorOptional.get().getPhone()), ()-> vendor.getEmail().equals(actualVendorOptional.get().getEmail()));
        verify(vendorRepository, times(1)).findByVendorCode(vendorCode);
    }
    //--------------------------------------------------------------------------------------

    @Test
    void testCreateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("123 Main St");
        vendorDTO.setCity("Anytown");
        vendorDTO.setState("CA");
        vendorDTO.setPinCode("123456");
        vendorDTO.setPhone("5555551234");
        vendorDTO.setEmail("testvendor@example.com");

        Vendor expectedVendor = Vendor.builder()
                .vendorName(vendorDTO.getVendorName())
                .address(vendorDTO.getAddress())
                .city(vendorDTO.getCity())
                .state(vendorDTO.getState())
                .pinCode(vendorDTO.getPinCode())
                .phone(vendorDTO.getPhone())
                .email(vendorDTO.getEmail())
                .vendorCode(String.valueOf(UUID.randomUUID()))
                .build();

        when(vendorRepository.save(any(Vendor.class))).thenReturn(expectedVendor);

        String actualResult = vendorService.createVendor(vendorDTO);

        assertEquals("Vendor Added Successfully", actualResult);
        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    void testCreateVendorWithInvalidPinCode() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setPinCode("invalid");

        assertThrows(Exception.class, () -> vendorService.createVendor(vendorDTO));
    }

    @Test
    void testCreateVendorWithInvalidPhoneNumber() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("Test Address");
        vendorDTO.setCity("Test City");
        vendorDTO.setState("Test State");
        vendorDTO.setPinCode("123456");
        vendorDTO.setPhone("12345"); // Invalid phone number
        vendorDTO.setEmail("test@example.com");
        VendorService vendorService = new VendorService();
        assertThrows(Exception.class, () -> vendorService.createVendor(vendorDTO));
    }

    @Test
    void testCreateVendorWithInvalidEmail() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName("Test Vendor");
        vendorDTO.setAddress("Test Address");
        vendorDTO.setCity("Test City");
        vendorDTO.setPhone("1234567890");
        vendorDTO.setState("Test State");
        vendorDTO.setPinCode("123456");
        vendorDTO.setEmail("invalid-email"); //Invalid Email
        VendorService vendorService = new VendorService();
        assertThrows(Exception.class, () -> vendorService.createVendor(vendorDTO));
    }
    //--------------------------------------------------------------------------------------

    @Test
    void testDeleteVendorWithValidId() {
        Long vendorId = 1L;

        doNothing().when(vendorRepository).deleteById(vendorId);
        vendorService.deleteVendor(vendorId);

        verify(vendorRepository, times(1)).deleteById(vendorId);
    }

    //--------------------------------------------------------------------------------------
    @Test
    void testUpdateVendor() throws Exception{
        Long id = 1L;
        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("adafqf-acac-1131-caca-cac-0d23f7d2d6a3");
        vendor.setAddress("123 Main St");
        vendor.setCity("TestAnyTown");
        vendor.setState("NY");
        vendor.setPinCode("123456");
        vendor.setPhone("5555551234");
        vendor.setEmail("testvendor@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("vendorName", "newVendorName");

        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(vendor)).thenReturn(vendor);

        String actualResult =  vendorService.updateVendor(id, updates);
        assertEquals("Updated", actualResult);

    }

    @Test
    void testUpdateVendorWithInvalidPinCode() throws Exception{
        Long id = 1L;
        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("adafqf-acac-1131-caca-cac-0d23f7d2d6a3");
        vendor.setAddress("123 Main St");
        vendor.setCity("TestAnyTown");
        vendor.setState("NY");
        vendor.setPinCode("123456");
        vendor.setPhone("5555551234");
        vendor.setEmail("testvendor@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("pinCode", "2324");// Invalid PinCode

        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));
        assertThrows(Exception.class, () -> vendorService.updateVendor(id,updates));
    }

    @Test
    void testUpdateVendorWithInvalidPhone() throws Exception{
        Long id = 1L;
        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("adafqf-acac-1131-caca-cac-0d23f7d2d6a3");
        vendor.setAddress("123 Main St");
        vendor.setCity("TestAnyTown");
        vendor.setState("NY");
        vendor.setPinCode("123456");
        vendor.setPhone("5555551234");
        vendor.setEmail("testvendor@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("phone", "2324"); //InValid Phone

        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));
        assertThrows(Exception.class, () -> vendorService.updateVendor(id,updates));
    }

    @Test
    void testUpdateVendorWithInvalidEmail() throws Exception{
        Long id = 1L;
        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("adafqf-acac-1131-caca-cac-0d23f7d2d6a3");
        vendor.setAddress("123 Main St");
        vendor.setCity("TestAnyTown");
        vendor.setState("NY");
        vendor.setPinCode("123456");
        vendor.setPhone("5555551234");
        vendor.setEmail("testvendor@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("email", "2324"); //InValid Email

        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));
        assertThrows(Exception.class, () -> vendorService.updateVendor(id,updates));
    }

    @Test
    void testUpdateVendorWithInvalidId() throws Exception{
        Long id = 1L;
        Long testId = 99L; //InValid ID
        Vendor vendor = new Vendor();
        vendor.setVendorId(id);
        vendor.setVendorName("Test Vendor");
        vendor.setVendorCode("adafqf-acac-1131-caca-cac-0d23f7d2d6a3");
        vendor.setAddress("123 Main St");
        vendor.setCity("TestAnyTown");
        vendor.setState("NY");
        vendor.setPinCode("123456");
        vendor.setPhone("5555551234");
        vendor.setEmail("testvendor@example.com");

        Map<String, Object> updates = new HashMap<>();
        updates.put("email", "example@gmail.com");

        when(vendorRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> vendorService.updateVendor(testId,updates));
        //String actualResult  = vendorService.updateVendor(testId, updates);

        //assertEquals("Vendor not found with id: " + testId, actualResult);
    }

}
