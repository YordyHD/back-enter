package com.sena.enter.mapper;

import com.sena.enter.dto.CustomerDTO;
import com.sena.enter.models.Customer;

public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toCustomer(CustomerDTO dto);

}
