package com.sena.enter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.enter.models.BillMembreship;
import com.sena.enter.models.BillMembreshipId;

public interface BillMembreshipRepository extends JpaRepository<BillMembreship, BillMembreshipId>{
    List<BillMembreship> findByBill_Id(Long billId);
    List<BillMembreship> findByMembreship_Id(Long membreshipId);
}
