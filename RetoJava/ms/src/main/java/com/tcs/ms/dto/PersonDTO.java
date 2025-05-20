package com.tcs.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long personId;
    private String personName;
    private String personGender;
    private String personAge;
    private String personCI;
    private String personAddress;
    private String personPhone;
}
