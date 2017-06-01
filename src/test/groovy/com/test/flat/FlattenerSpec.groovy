package com.test.flat

import spock.lang.Specification

class FlattenerSpec extends Specification {

    def "must be able to handle empty array"() {
        given: 'an empty array'
        Object[] emptyArray = []

        when:
        def flattened = Flattener.flattenInteger(emptyArray)

        then:
        noExceptionThrown()
        flattened.size() == 0
    }

    def "must be able to handle null array"() {
        given: 'an empty array'
        Object[] nullArray = null

        when:
        def flattened = Flattener.flattenInteger(nullArray)

        then:
        noExceptionThrown()
        flattened.size() == 0
    }

    def "must be able to handle a flatted array"() {
        given: 'a flattened array'
        Object[] toBeFlattened = [1, 2, 4, 2]

        when:
        def flattened = Flattener.flattenInteger(toBeFlattened)

        then:
        flattened.size() == 4
        toBeFlattened == flattened
    }

    // note: spock running more than one scenario using parametrized test"
    def "must be able to handle a flattened array just with integer values"() {
        when:
        def flattened = Flattener.flattenInteger(arrayToFlat as Object[])

        then:
        noExceptionThrown()
        flattened == expectation as Integer[]

        where:
        arrayToFlat      | expectation
        [1, null, 2]     | [1, 2]
        [1, 'cassio', 3] | [1, 3]
        [1,]             | [1]
    }

    def "must be able to handle a nested arrays"() {
        given: 'a non flattened array'
        def nestedArray = [4, 5] as Object[]
        Object[] toBeFlattened = [1, 2, 3, nestedArray]

        when:
        def flattened = Flattener.flattenInteger(toBeFlattened)

        then:
        flattened.size() == 5
        flattened == [1, 2, 3, 4, 5]
    }

    def "must be able to handle nested arrays with weird values"() {
        given: 'a non flattened array'
        Object[] toBeFlattened = [1, 2, 3,
                                  [4, 'cassio'] as Object[],
                                  ['cassio', 3.3] as Object[],
                                  [null, 5,] as Object[]
        ]

        when:
        def flattened = Flattener.flattenInteger(toBeFlattened)

        then:
        flattened.size() == 5
        flattened == [1, 2, 3, 4, 5]
    }

}
