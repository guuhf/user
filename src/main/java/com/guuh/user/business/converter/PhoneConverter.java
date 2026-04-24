package com.guuh.user.business.converter;

import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.infraestructure.entity.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneConverter {
    public Phone toPhone(PhoneDTO phoneDTO){
        return Phone.builder()
                .phoneNumber(phoneDTO.getPhoneNumber())
                .countryCode(phoneDTO.getCountryCode())
                .build();
    }

    public List<Phone> toPhoneList(List<PhoneDTO> phonesDTO){
        if (phonesDTO == null){
            return null;
        }

        List<Phone> phones = new ArrayList<>();
        for (PhoneDTO phoneDTO : phonesDTO){
            phones.add(toPhone(phoneDTO));
        }
        return phones;
    }

    public PhoneDTO toPhoneDTO(Phone phone){
        return PhoneDTO.builder()
                .phoneNumber(phone.getPhoneNumber())
                .countryCode(phone.getCountryCode())
                .build();
    }

    public List<PhoneDTO> toPhoneDTOList(List<Phone> phones){
        if (phones == null){
            return null;
        }

        List<PhoneDTO> phonesDTO = new ArrayList<>();
        for (Phone phone : phones){
            phonesDTO.add(toPhoneDTO(phone));
        }
        return phonesDTO;
    }
    public Phone updatePhone(PhoneDTO phoneDTO, Phone phone){
        if (phoneDTO.getPhoneNumber() != null){
            phone.setPhoneNumber(phoneDTO.getPhoneNumber());
        }
        if (phoneDTO.getCountryCode() != null){
            phone.setCountryCode(phoneDTO.getCountryCode());
        }

        return phone;
    }
}
