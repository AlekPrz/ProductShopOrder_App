package com.app.dto;

import com.app.exceptions.MyException;
import com.app.model.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private CountryDto countryDto;

  /*  public CustomerDto(Long id, Integer age, String name, String surname, CountryDto countryDto) {
        this.id = id;
        setAge(age);
        setName(name);
        setSurname(surname);
        this.countryDto = countryDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) throws MyException {
        if(age<=18){
            throw new MyException("[DTO] CUSTOMER AGE  IS NOT CORRECT");

        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MyException {
        if (name == null || !name.matches("[A-Z ]+")) {
            throw new MyException("[DTO] CUSTOMER NAME IS NOT CORRECT");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws MyException {
        if (surname == null || !surname.matches("[A-Z ]+")) {
            throw new MyException("[DTO] CUSTOMER SURNAME IS NOT CORRECT");
        }
        this.surname = surname;
    }


    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(age, that.age) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, age, name, surname);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", countryDto=" + countryDto +
                '}';
    }*/
}
