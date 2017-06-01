package com.test.finder

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class CustomerRepositorySpec extends Specification {

    def "should map when there is a proper resource available"() {
        given:
        CustomerResource customerResource = Spy(CustomerResource)
        ObjectMapper mapper = Spy(ObjectMapper)
        CustomerRepository customerRepository = new CustomerRepository(customerResource, mapper)

        when:
        Customer[] customers = customerRepository.loadAll()

        then:
        customers != null
        customers.size() > 0
        customers[0].with {
            name != null
            userID != null
            longitude != null
            latitude != null
        }

        and: 'customer resource must be executed'
        1 * customerResource.loadFile()

        and: 'object mapper must be executed'
        1 * mapper.readValue(_ as File, _ as Class)
    }

    def "should handle properly exceptions when customer resource fails"() {
        given:
        CustomerResource customerResource = Mock(CustomerResource)
        ObjectMapper mapper = Spy(ObjectMapper)
        CustomerRepository customerRepository = new CustomerRepository(customerResource, mapper)

        when:
        customerRepository.loadAll()

        then: 'Exception must be thrown'
        thrown(RuntimeException)

        and: 'customer resource throws exception when executed'
        1 * customerResource.loadFile() >> {
            // mocked behavior
            throw new IOException()
        }

        and: 'object mapper must not be executed'
        0 * mapper.readValue(_ as File, _ as Class)
    }

    def "should failed when there is no required dependencies"() {
        when:
        new CustomerRepository(customerResorce, mapper)

        then:
        thrown(IllegalArgumentException)

        where:
        customerResorce        | mapper
        null                   | null
        new CustomerResource() | null
        null                   | new ObjectMapper()
    }

}
