package com.example.Managing.Vendors.Service;

import com.example.Managing.Vendors.DTO.VendorDTO;
import com.example.Managing.Vendors.Entity.Vendor;
import com.example.Managing.Vendors.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    public Optional<Vendor> getVendorByCode(String code) {
        return vendorRepository.findByVendorCode(code);
    }

    public String createVendor(VendorDTO vendorDTO) throws Exception {

        if(vendorDTO.getPinCode().length()!=6){
            throw new Exception("Invalid Pin Code");
        }
        for (int i = 0; i < vendorDTO.getPinCode().length(); i++) {
            if (!Character.isDigit(vendorDTO.getPinCode().charAt(i))) {
                throw new Exception("Invalid Pin Code");
            }
        }


        if (vendorDTO.getPhone().length() != 10){
            throw new Exception("Invalid Phone Number");
        }
        for (int i = 0; i < vendorDTO.getPhone().length(); i++) {
            if (!Character.isDigit(vendorDTO.getPhone().charAt(i))) {
                throw new Exception("Invalid Phone Number");
            }
        }


        if(vendorDTO.getEmail().endsWith(".com") && !vendorDTO.getEmail().contains("@")){
            throw new Exception("Invalid Email");
        }

        Vendor vendor = Vendor.builder().vendorName(vendorDTO.getVendorName()).address(vendorDTO.getAddress())
                .city(vendorDTO.getCity()).phone(vendorDTO.getPhone()).state(vendorDTO.getState())
                .pinCode(vendorDTO.getPinCode()).email(vendorDTO.getEmail()).build();

        Vendor vendor1 = new Vendor();
        vendor1.setVendorId(vendor.getVendorId());

        vendor.setVendorCode(String.valueOf(UUID.randomUUID()));
        vendorRepository.save(vendor);
        return "Vendor Added Successfully";
    }

    public String updateVendor(Long id, Map<String, Object> updates) throws Exception {

        Vendor vendorToUpdate = vendorRepository.findById(id).orElseThrow(() -> new Exception("Vendor not found with id: " + id));

        // iterate through the updates map and set the corresponding fields in the vendor object
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            if(field.equals("pinCode") && value.toString().length()!=6){
                throw new Exception("Trying to update invalid PinCode");
            }

            if(field.equals("phone") && value.toString().length()!=10){
                throw new Exception("Trying to update invalid Phone");
            }
            if(field.equals("email") && !value.toString().endsWith(".com") && !value.toString().contains("@")){
                throw new Exception("Trying to update invalid E-mail");
            }

            Field vendorField = vendorToUpdate.getClass().getDeclaredField(field);
            vendorField.setAccessible(true);
            vendorField.set(vendorToUpdate, value);
        }

        vendorRepository.save(vendorToUpdate);
        return "Updated";

    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
