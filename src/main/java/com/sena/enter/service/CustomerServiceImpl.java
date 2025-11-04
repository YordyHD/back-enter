package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.CustomerDTO;
import com.sena.enter.mapper.CustomerMapper;
import com.sena.enter.models.Customer;
import com.sena.enter.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        if (!isValidCustomer(customerDTO)) {
            return null;
        }
        Customer customer = customerMapper.toCustomer(customerDTO);
        if (customer == null) {
            return null;
        }
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        if (customerDTO == null || customerDTO.getId() == null) {
            return null;
        }
        if (!isValidCustomer(customerDTO)) {
            return null;
        }
        if (!customerRepository.existsById(customerDTO.getId())) {
            return null;
        }
        Customer customer = customerMapper.toCustomer(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO findOne(Long id) {
        if (id == null) {
            return null;
        }
        return customerRepository.findById(id)
            .map(customerMapper::toDTO)
            .orElse(null);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
            .map(customerMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null && customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
    }

    private boolean isValidCustomer(CustomerDTO customerDTO) {
        return customerDTO.getDocumentNumber() != null &&
               customerDTO.getFirstName() != null &&
               customerDTO.getFirstLasName() != null &&
               customerDTO.getUser() != null &&
               customerDTO.getDocumentType() != null &&
               customerDTO.getSex() != null &&
               customerDTO.getCities() != null &&
               customerDTO.getFilmGenres() != null;
    }
}