package com.example.Managing.Vendors.Repository;

import com.example.Managing.Vendors.Entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByVendorCode(String vendorCode);
}
