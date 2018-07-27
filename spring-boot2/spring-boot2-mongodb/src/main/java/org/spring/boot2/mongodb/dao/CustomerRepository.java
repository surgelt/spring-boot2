package org.spring.boot2.mongodb.dao;

import java.util.List;

import org.spring.boot2.mongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	List<Customer> findByFirstName(String firstName);

	List<Customer> findByLastName(String lastName);
}