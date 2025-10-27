package com.sena.enter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.enter.models.BillMembreship;
import com.sena.enter.models.BillMembreshipId;

public interface BillMembreshipRepository extends JpaRepository<BillMembreship, BillMembreshipId>{

}
