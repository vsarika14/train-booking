package com.sarika.trainbooking.repository;

import com.sarika.trainbooking.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
