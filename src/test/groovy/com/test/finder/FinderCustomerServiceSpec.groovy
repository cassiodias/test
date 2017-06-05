package com.test.finder

class FinderCustomerServiceSpec extends spock.lang.Specification {

    def "must not failed if customer list is empty"() {
        setup:
        CustomerRepository repository = Mock(CustomerRepository)
        FinderCustomerService finderService = new FinderCustomerService(repository)

        when:
        Customer[] customers = finderService.within(0)

        then:
        1 * repository.loadAll() >> []

        and: 'customers is empty'
        customers != null
        customers.size() == 0
    }

    def "must return empty customer list when customers is null"() {
        setup:
        CustomerRepository repository = Mock(CustomerRepository)
        FinderCustomerService finderService = new FinderCustomerService(repository)

        when:
        Customer[] customers = finderService.within(0)

        then:
        1 * repository.loadAll() >> null

        and: 'there is no null pointer and customers is empty'
        noExceptionThrown()
        customers.size() == 0
    }

    def "must load within 100k for a sample of customers"() {
        setup:
        def fakeCustomers = [
                new Customer(latitude: 54.080556, longitude: -6.361944, userID: 83, name: "Dundalk -> close!"), //83KM
                new Customer(latitude: 52.833502, longitude: -8.522366, userID: 161, name: "Limeric -> too far!"), //161KM
                new Customer(latitude: 52.44282715, longitude: -6.35568845, userID: 100, name: "Inch Hill, BlackWater -> limit!"), //100KM
                new Customer(latitude: 52.986375, longitude: -6.043701, userID: 42, name: "Wicklow -> close!"), //42KM
                new Customer(latitude: 52.41330972, longitude: -6.36074066, userID: 103, name: "BallyValloo -> far!") //103KM
        ]
        CustomerRepository repository = Mock(CustomerRepository)
        repository.loadAll() >> fakeCustomers

        FinderCustomerService finderService = new FinderCustomerService(repository)

        when:
        Customer[] customers = finderService.within(100)

        then: 'customers are valid'
        customers != null
        customers.size() == 3

        and: 'sorted ascending'
        customers[0].userID == 42
        customers[1].userID == 83
        customers[2].userID == 100
    }

    def "must read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending)"() {
        given:
        FinderCustomerService finderCustomerService = new FinderCustomerService(new CustomerRepository())

        when:
        List<Customer> customersForACoffee = finderCustomerService.within(100)

        then:
        customersForACoffee != null
        customersForACoffee.size() == 16
        // final print
        customersForACoffee.each { customer ->
            println customer
        }
    }

}
