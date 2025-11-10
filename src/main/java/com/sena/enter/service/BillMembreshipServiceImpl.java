package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.BillMembreshipDTO;
import com.sena.enter.mapper.BillMembreshipMapper;
import com.sena.enter.models.BillMembreship;
import com.sena.enter.models.BillMembreshipId;
import com.sena.enter.repository.BillMembreshipRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BillMembreshipServiceImpl implements BillMembreshipService {

    private final BillMembreshipRepository billMembreshipRepository;
    private final BillMembreshipMapper billMembreshipMapper;

    public BillMembreshipServiceImpl(BillMembreshipRepository billMembreshipRepository, BillMembreshipMapper billMembreshipMapper) {
        this.billMembreshipRepository = billMembreshipRepository;
        this.billMembreshipMapper = billMembreshipMapper;
    }

    @Override
    public BillMembreshipDTO save(BillMembreshipDTO billMembreshipDTO) {
        if (billMembreshipDTO == null) {
            return null;
        }
        if (billMembreshipDTO.getBill() == null || billMembreshipDTO.getMembreship() == null) {
            return null;
        }
        BillMembreship billMembreship = billMembreshipMapper.toBillMembreship(billMembreshipDTO);
        if (billMembreship == null) {
            return null;
        }
        billMembreship = billMembreshipRepository.save(billMembreship);
        return billMembreshipMapper.toDTO(billMembreship);
    }

    @Override
    public BillMembreshipDTO update(BillMembreshipDTO billMembreshipDTO) {
        if (billMembreshipDTO == null || billMembreshipDTO.getBill() == null || billMembreshipDTO.getMembreship() == null) {
            return null;
        }
        BillMembreshipId id = new BillMembreshipId(billMembreshipDTO.getBill().getId(), billMembreshipDTO.getMembreship().getId());
        if (!billMembreshipRepository.existsById(id)) {
            return null;
        }
        BillMembreship billMembreship = billMembreshipMapper.toBillMembreship(billMembreshipDTO);
        billMembreship = billMembreshipRepository.save(billMembreship);
        return billMembreshipMapper.toDTO(billMembreship);
    }

    @Override
    public BillMembreshipDTO findOne(Long billId, Long membreshipId) {
        if (billId == null || membreshipId == null) {
            return null;
        }
        return billMembreshipRepository.findById(new BillMembreshipId(billId, membreshipId))
            .map(billMembreshipMapper::toDTO)
            .orElse(null);
    }

    @Override
    public List<BillMembreshipDTO> findAll() {
        return billMembreshipRepository.findAll().stream()
            .map(billMembreshipMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long billId, Long membreshipId) {
        if (billId != null && membreshipId != null) {
            BillMembreshipId id = new BillMembreshipId(billId, membreshipId);
            if (billMembreshipRepository.existsById(id)) {
                billMembreshipRepository.deleteById(id);
            }
        }
    }
}