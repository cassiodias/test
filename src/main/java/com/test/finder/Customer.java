package com.test.finder;

public class Customer {

    private Integer userID;
    private String latitude;
    private String longitude;
    private String name;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (userID != null ? !userID.equals(customer.userID) : customer.userID != null) return false;
        if (latitude != null ? !latitude.equals(customer.latitude) : customer.latitude != null) return false;
        if (longitude != null ? !longitude.equals(customer.longitude) : customer.longitude != null) return false;
        return name != null ? name.equals(customer.name) : customer.name == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userID=" + userID +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
