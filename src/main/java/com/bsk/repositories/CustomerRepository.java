package com.bsk.repositories;

import com.bsk.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public List<Customer> findByNameContainsOrCityContainsOrNipContainsOrPhoneNumberContainsOrStreetContainsOrPostalCodeContains
            (String name, String city, String nip, String phoneNumber, String street, String postalCode);
}
