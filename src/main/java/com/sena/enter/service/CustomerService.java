package com.sena.enter.service;

import java.util.List;
import com.sena.enter.dto.CustomerDTO;

public interface CustomerService {
    
    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO);

    List<CustomerDTO> findAll();

    CustomerDTO findOne(Long id);

    void delete(Long id);
}