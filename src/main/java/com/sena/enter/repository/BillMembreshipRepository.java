package com.sena.enter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.enter.models.BillMembreshipId;

@Repository
public interface BillMembreshipRepository extends JpaRepository<BillMembreshipRepository, BillMembreshipId>{
    List<BillMembreshipRepository> findByMembreship_Id(Long billId);
    List<BillMembreshipRepository> findByBill_Id(Long membreshipId);
}
