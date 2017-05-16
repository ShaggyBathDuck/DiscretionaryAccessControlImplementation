package com.bsk.services;

import com.bsk.domain.Customer;
import com.bsk.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> read() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void delete(Integer id) {
        customerRepository.delete(id);
    }

    public Customer findById(Integer id) {
        return customerRepository.findOne(id);
    }
}
