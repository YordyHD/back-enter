package com.sena.enter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.enter.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
