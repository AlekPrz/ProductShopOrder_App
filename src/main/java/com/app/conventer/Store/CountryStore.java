package com.app.conventer.Store;

import com.app.model.Country;
import lombok.Data;

import java.util.Set;
@Data

public class CountryStore {
    private Set<Country> countries;


    @Override
    public String toString() {
        return "CountryStore{" +
                "countries=" + countries +
                '}';
    }
}
