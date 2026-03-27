package com.guuh.user.business.dtos;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDTO {

    private String phoneNumber;
    private String countryCode;
}
