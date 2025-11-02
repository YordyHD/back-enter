package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.enter.dto.ViewDTO;
import com.sena.enter.dto.CustomerDTO;
import com.sena.enter.models.View;
import com.sena.enter.models.Customer;

@Component
public class ViewMapperImpl implements ViewMapper {

    @Autowired
    private AudiovisualContentMapper audiovisualContentMapper;

    @Override
    public ViewDTO toDTO(View view) {
        if (view == null) {
            return null;
        }

        ViewDTO dto = new ViewDTO();
        dto.setId(view.getId());
        dto.setTimeSeen(view.getTiSeen());
        dto.setDuration(view.getDurat());
        
        if (view.getCustomer() != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(view.getCustomer().getId());
            dto.setCustomer(customerDTO);
        }
        
        if (view.getAudiovisualContent() != null) {
            dto.setAudiovisualContent(audiovisualContentMapper.toDTO(view.getAudiovisualContent()));
        }

        return dto;
    }

    @Override
    public View toEntity(ViewDTO dto) {
        if (dto == null) {
            return null;
        }

        View view = new View();
        view.setId(dto.getId());
        view.setTiSeen(dto.getTimeSeen());
        view.setDurat(dto.getDuration());
        
        if (dto.getCustomer() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomer().getId());
            view.setCustomer(customer);
        }
        
        if (dto.getAudiovisualContent() != null) {
            view.setAudiovisualContent(audiovisualContentMapper.toAudiovisualContent(dto.getAudiovisualContent()));
        }

        return view;
    }

    @Override
    public View updateViewFromDto(View view, ViewDTO dto) {
        if (view == null || dto == null) {
            return view;
        }

        if (dto.getTimeSeen() != null) {
            view.setTiSeen(dto.getTimeSeen());
        }
        if (dto.getDuration() != null) {
            view.setDurat(dto.getDuration());
        }
        
        if (dto.getCustomer() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomer().getId());
            view.setCustomer(customer);
        }
        
        if (dto.getAudiovisualContent() != null) {
            view.setAudiovisualContent(audiovisualContentMapper.toAudiovisualContent(dto.getAudiovisualContent()));
        }

        return view;
    }
}