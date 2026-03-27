package com.guuh.user.business.dtos;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String email;
    private String password;

    List<AddressDTO> addresses;
    List<PhoneDTO> phones;
}
