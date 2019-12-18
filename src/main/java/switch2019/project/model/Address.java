package switch2019.project.model;

import java.util.Objects;

public class Address {

    private String street;
    private String city;
    private String zipCode;

    public Address (String street, String city, String zipCode){
        this.street=street;
        this.city=city;
        this.zipCode=zipCode;
    }

    public String getCity() {
        //ecrever código
        return "";
    }

    public void setCity(String city) {
        //escrever código;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equalsIgnoreCase(address.street) &&
                city.equalsIgnoreCase(address.city) &&
                zipCode.equalsIgnoreCase(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }
}
