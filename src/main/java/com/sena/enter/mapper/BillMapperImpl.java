package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.BillDTO;
import com.sena.enter.dto.CustomerDTO;
import com.sena.enter.models.Bill;
import com.sena.enter.models.Customer;

@Component
public class BillMapperImpl implements BillMapper {

    @Override
    public BillDTO toDTO(Bill bill) {
        if (bill == null) {
            return null;
        }

        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setPurchaseDate(bill.getPurchaDate());
        dto.setYeard(bill.getYea());
        dto.setNumberBill(bill.getNumBill());
        
        if (bill.getCustomer() != null) {
            CustomerDTO customerDto = new CustomerDTO();
            customerDto.setId(bill.getCustomer().getId());
            dto.setCustomer(customerDto);
        }

        return dto;
    }

    @Override
    public Bill toBill(BillDTO dto) {
        if (dto == null) {
            return null;
        }

        Bill bill = new Bill();
        bill.setId(dto.getId());
        bill.setPurchaDate(dto.getPurchaseDate());
        bill.setYea(dto.getYeard());
        bill.setNumBill(dto.getNumberBill());
        
        if (dto.getCustomer() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomer().getId());
            bill.setCustomer(customer);
        }

        return bill;
    }
}
