package com.test.finder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Load a list of Customer from Customer Resource File and Object Mapper.
 *
 * @author Cassio Dias
 */
public class CustomerRepository {

    // Real world would be injected
    private CustomerResource customerResource = new CustomerResource();
    private ObjectMapper objectMapper = new ObjectMapper();

    List<Customer> loadAll() {
        try {
            File customerResourceFile = this.customerResource.loadFile();
            return Arrays.asList(this.objectMapper.readValue(customerResourceFile, Customer[].class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load customers [" + e.getMessage() + "]");
        }
    }

}
