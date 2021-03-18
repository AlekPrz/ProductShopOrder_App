package com.app.conventer.Store;

import com.app.model.Customer;
import lombok.Data;

import java.util.Set;
@Data
public class CustomerStore {
    private Set<Customer> customers;

}
