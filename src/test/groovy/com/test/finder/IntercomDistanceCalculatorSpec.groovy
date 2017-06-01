package com.test.finder

import spock.lang.Specification

class IntercomDistanceCalculatorSpec extends Specification {

    def "must calculate distance from office"() {
        given:
        IntercomDistanceCalculator distanceCalculation = new IntercomDistanceCalculator()

        when:
        Double distance = distanceCalculation.fromCustomerLocation(latitude as Double, longitude as Double)

        then:
        expectedKmDistance == Math.round(distance)

        where:
        latitude    | longitude   | expectedKmDistance
        52.986375   | -6.043701   | 42   // Close to Wicklow
        54.080556   | -6.361944   | 83   // Close to Dundalk
        52.833502   | -8.522366   | 161  // Close to Limeric
        53.34040581 | -6.26375198 | 0    // Very close: Mercer Street Lower
        91          | 1           | -1   // invalid range
        80          | 190         | -1   // invalid range
        null        | 190         | -1   // invalid range
        80          | null        | -1   // invalid range
        80          | 180         | 5184 // very far from Ireland
    }

}
