package com.bsk.mapper;

import com.bsk.domain.Customer;
import com.bsk.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer map(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getNip(),
                customerDTO.getName(),
                customerDTO.getPhoneNumber(),
                customerDTO.getStreet(),
                customerDTO.getHouseNumber(),
                customerDTO.getFlatNumber(),
                customerDTO.getPostalCode(),
                customerDTO.getCity(),
                customerDTO.getDiscount());
    }
}
