package com.app.dto;

import com.app.exceptions.MyException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
    private Long id;
    private String city;


    /*public void setName(String name) throws MyException {
        if (name == null || !name.matches("[A-Z ]+")) {
            throw new MyException("[DTO] COUNTRY NAME IS NOT CORRECT");
        }
        this.city = name;
    }*/

}
