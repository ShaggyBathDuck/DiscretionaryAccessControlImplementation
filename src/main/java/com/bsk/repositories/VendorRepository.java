package com.bsk.repositories;

import com.bsk.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    public List<Vendor> findByNameContainsOrCityContainsOrNipContainsOrPhoneNumberContainsOrStreetContainsOrPostalCodeContains
            (String name, String city, String nip, String phoneNumber, String street, String postalCode);
}
