package switch2019.project.model;

import java.util.Objects;

public class Address {

    private String street;
    private String city;
    private String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        if (isNumeric(city) || city == null) {
            this.city = null;
        } else {
            this.city = city;
        }
    }
    //Auxiliar method to check if String is numeric
    public static boolean isNumeric(String city) {
        if (!(city == null)) {
            for (char c : city.toCharArray()) {
                if (!Character.isDigit(c))
                    return false;
            }
            return true;
        }
        return false;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public void setZipCode(String Zip){/* code here */ }

    public String getZipCode(){
        return zipCode;
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
