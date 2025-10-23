package com.sena.enter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.enter.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
