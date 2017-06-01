package com.test.finder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

/**
 * Load a list of Customer from Customer Resource File and Object Mapper.
 *
 * @author Cassio Dias
 */
public class CustomerRepository {

    private CustomerResource customerResource;
    private ObjectMapper objectMapper;

    CustomerRepository(CustomerResource customerResource, ObjectMapper objectMapper) {
        checkDependencies(customerResource, objectMapper);

        this.customerResource = customerResource;
        this.objectMapper = objectMapper;
    }

    public Customer[] loadAll() {
        try {
            File customerResourceFile = this.customerResource.loadFile();
            return this.objectMapper.readValue(customerResourceFile, Customer[].class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load customers [" + e.getMessage() + "]");
        }
    }

    private void checkDependencies(CustomerResource customerResource, ObjectMapper objectMapper) {
        if (customerResource == null || objectMapper == null) {
            throw new IllegalArgumentException("CustomerResource and ObjectMapper are required dependencies");
        }
    }

}
