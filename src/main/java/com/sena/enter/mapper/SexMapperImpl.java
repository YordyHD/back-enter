package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.SexDTO;
import com.sena.enter.models.Sex;

@Component
public class SexMapperImpl implements SexMapper{

    @Override
    public SexDTO toDTO(Sex sex){
      if (sex == null){
        return null;
      }

      SexDTO dto = new SexDTO();
      dto.setId(sex.getId());
      dto.setSexName(sex.getSexN());
      return dto;
    }

    @Override
    public Sex toSex(SexDTO dto){
       if (dto == null){
        return null;
      }

      Sex sex = new Sex();
      sex.setId(dto.getId());
      sex.setSexN(dto.getSexName());
      return sex;
    }
}
