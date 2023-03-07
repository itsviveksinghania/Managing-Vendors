package com.example.Managing.Vendors.Controller;

import com.example.Managing.Vendors.DTO.VendorDTO;
import com.example.Managing.Vendors.Entity.Vendor;
import com.example.Managing.Vendors.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/all")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Optional<Vendor> vendorOptional = vendorService.getVendorById(id);
        if (vendorOptional.isPresent()) {
            return ResponseEntity.ok(vendorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Vendor> getVendorByCode(@PathVariable String code) {
        Optional<Vendor> vendorOptional = vendorService.getVendorByCode(code);
        if (vendorOptional.isPresent()) {
            return ResponseEntity.ok(vendorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> createVendor(@RequestBody VendorDTO vendorDTO) {
        try {
            String response = vendorService.createVendor(vendorDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateVendor(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        try {
            String response = vendorService.updateVendor(id, updates);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

}
