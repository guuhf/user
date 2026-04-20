package com.guuh.user.business;


import com.guuh.user.business.converter.PhoneConverter;
import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.infraestructure.entity.Phone;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.DuplicateUserPhoneException;
import com.guuh.user.infraestructure.exceptions.PhoneNotFoundException;
import com.guuh.user.infraestructure.repository.PhoneRepository;
import com.guuh.user.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final PhoneConverter converter;
    private final UserService userService;

    public PhoneDTO addPhoneToUser(PhoneDTO phoneDTO) {
        Phone phone = converter.toPhone(phoneDTO);
        User user = userService.getLoggedUser();

        validatePhoneUniqueness(phone);
        phone.setUser(user);
        user.getPhones().add(phone);
        userRepository.save(user);
        return converter.toPhoneDTO(phone);
    }

    public void validatePhoneUniqueness(Phone phone){
        if (phoneRepository.existsByPhone(phone)){
            throw new DuplicateUserPhoneException("Phone number already exists for this user!");
        }
    }

    public PhoneDTO updatePhones(PhoneDTO phoneDTO, Long id){
        User user = userService.getLoggedUser();
        Phone phone = phoneRepository.findByIdAndUserId(id, user.getId()).orElseThrow(()->
                new PhoneNotFoundException("Phone not found!"));

        converter.updatePhone(phoneDTO, phone);
        return converter.toPhoneDTO(phoneRepository.save(phone));
    }

    public PhoneDTO getPhoneData(Long id) {
        User user = userService.getLoggedUser();
        return converter.toPhoneDTO(phoneRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() ->
                new PhoneNotFoundException("Phone no found!")));
    }

    public void deletePhone(Long id) {
        User user = userService.getLoggedUser();
        Phone phone = phoneRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() ->
                new PhoneNotFoundException("Phone not found"));
        phoneRepository.delete(phone);
    }
}
