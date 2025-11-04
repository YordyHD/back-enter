package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.BillDTO;
import com.sena.enter.mapper.BillMapper;
import com.sena.enter.models.Bill;
import com.sena.enter.repository.BillRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public BillServiceImpl(BillRepository billRepository, BillMapper billMapper) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }

    @Override
    public BillDTO save(BillDTO billDTO) {
        if (billDTO == null) {
            return null;
        }
        if (billDTO.getCustomer() == null) {
            return null;
        }
        Bill bill = billMapper.toBill(billDTO);
        if (bill == null) {
            return null;
        }
        bill = billRepository.save(bill);
        return billMapper.toDTO(bill);
    }

    @Override
    public BillDTO update(BillDTO billDTO) {
        if (billDTO == null || billDTO.getId() == null) {
            return null;
        }
        if (billDTO.getCustomer() == null) {
            return null;
        }
        if (!billRepository.existsById(billDTO.getId())) {
            return null;
        }
        Bill bill = billMapper.toBill(billDTO);
        bill = billRepository.save(bill);
        return billMapper.toDTO(bill);
    }

    @Override
    public BillDTO findOne(Long id) {
        if (id == null) {
            return null;
        }
        return billRepository.findById(id)
            .map(billMapper::toDTO)
            .orElse(null);
    }

    @Override
    public List<BillDTO> findAll() {
        return billRepository.findAll().stream()
            .map(billMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null && billRepository.existsById(id)) {
            billRepository.deleteById(id);
        }
    }
}

