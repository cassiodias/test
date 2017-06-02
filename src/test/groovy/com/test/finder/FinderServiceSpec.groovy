package com.test.finder

class FinderServiceSpec extends spock.lang.Specification {

    def "must not failed if customer list is empty"() {
        setup:
        CustomerRepository repository = Mock(CustomerRepository)
        FinderService finderService = new FinderService(repository)

        when:
        Customer[] customers = finderService.findWithin(0)

        then:
        1 * repository.loadAll() >> []

        and: 'customers is empty'
        customers != null
        customers.size() == 0
    }

    def "must return empty customer list when customers is null"() {
        setup:
        CustomerRepository repository = Mock(CustomerRepository)
        FinderService finderService = new FinderService(repository)

        when:
        Customer[] customers = finderService.findWithin(0)

        then:
        1 * repository.loadAll() >> null

        and: 'there is no null pointer and customers is empty'
        noExceptionThrown()
        customers.size() == 0
    }

    def "must load within 100k for a sample of customers"() {
        setup:
        def fakeCustomers = [
                new Customer(latitude: 54.080556, longitude: -6.361944, userID: 83, name: "Dundalk -> close!"),
                new Customer(latitude: 52.833502, longitude: -8.522366, userID: 161, name: "Limeric -> too far!"),
                new Customer(latitude: 52.986375, longitude: -6.043701, userID: 42, name: "Wicklow -> close!")
        ]
        CustomerRepository repository = Mock(CustomerRepository)
        repository.loadAll() >> fakeCustomers

        FinderService finderService = new FinderService(repository)

        when:
        Customer[] customers = finderService.findWithin(100)

        then: 'customers are valid'
        customers != null
        customers.size() == 2

        and: 'sorted ascending'
        customers[0].userID == 42
        customers[1].userID == 83
    }

    def "must read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending)"() {
        //given: 'full '
    }

}
