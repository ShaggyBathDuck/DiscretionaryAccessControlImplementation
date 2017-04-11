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

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void add(Customer customer){
        customerRepository.save(customer);
    }

    public void remove(String nip){
        customerRepository.delete(nip);
    }

    public void update(String nip, Customer customer) {
    }
}
