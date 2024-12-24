package com.nhadat.service;

import com.nhadat.model.Customer;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    List<Customer> findAll();
}
