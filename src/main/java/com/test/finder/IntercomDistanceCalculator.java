package com.test.finder;

/**
 * @author wikipedia + Cassio Dias
 *
 * source: https://en.wikipedia.org/wiki/Great-circle_distance#cite_ref-3
 *         http://andrew.hedges.name/experiments/haversine/
 *         http://www.mapcoordinates.net/en (validated from here)
 */
class IntercomDistanceCalculator {

    private final double KM_AVERAGE_CIRCUMFERENCE = 6372.8;
    private final Double intercomLatitude = 53.3393;
    private final Double intercomLongitude = -6.2576841;

    /**
     * @param latitude
     * @param longitude
     * @return the distance in KM from Intercom office using the Haversine formula, which is numerically better
     * conditioned for small distances. Also returns -1 when customer location is invalid (@see invalidRange method).
     */
    public Double fromCustomerLocation(Double latitude, Double longitude) {
        // roughly checking the range
        if (invalidRange(latitude, longitude))
            return -1.;

        Double degreeLatitude = Math.toRadians(latitude - intercomLatitude);
        Double degreeLongitude = Math.toRadians(longitude - intercomLongitude);

        Double radianIntercomLatitude = Math.toRadians(intercomLatitude);
        Double radianCustomerLatitude = Math.toRadians(latitude);

        Double operation = Math.pow(Math.sin(degreeLatitude / 2), 2) + Math.pow(Math.sin(degreeLongitude / 2), 2) *
                Math.cos(radianIntercomLatitude) * Math.cos(radianCustomerLatitude);

        Double SquareRootOfOperation = Math.sqrt(operation);
        Double deltaLimit = 2 * Math.asin(SquareRootOfOperation);

        return KM_AVERAGE_CIRCUMFERENCE * deltaLimit;
    }

    private boolean invalidRange(Double latitude, Double longitude) {
        if (latitude == null || longitude == null)
            return true;

        if (!(latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180))
            return true;

        return false;
    }

}