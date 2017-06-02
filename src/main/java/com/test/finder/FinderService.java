package com.test.finder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FinderService {

    private CustomerRepository customerRepository;

    public FinderService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    List<Customer> findWithin(int km) {
        List<Customer> customers = customerRepository.loadAll();
        return Optional.ofNullable(customers).
                orElseGet(Collections::emptyList).
                stream().
                filter(c -> new IntercomDistanceCalculator().fromCustomerLocation(c.getLatitude(), c.getLongitude()) <= km).
                sorted(Comparator.comparing(Customer::getUserID)).
                collect(Collectors.toList());
    }

}
