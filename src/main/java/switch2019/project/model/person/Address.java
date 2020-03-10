package switch2019.project.model.person;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
    // The private Address variables
    private String street;
    private String city;
    private String zipCode;
    private String birthPlace;

    /**
     * Address constructor
     *
     * @param birthPlace
     */

    public Address(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * Address constructor
     *
     * @param street
     * @param city
     * @param zipCode
     */

    public Address(String street, String city, String zipCode) {
        setStreet(street);
        setCity(city);
        setZipCode(zipCode);
    }

    /**
     * Public get for City
     *
     * @return city
     */

    public String getCity() {
        return this.city;
    }

    /**
     * Auxiliar method to check if String is numeric
     *
     * @param city
     * @return
     */

    public static boolean isNumeric(String city) {
        if (city != null) {
            for (char c : city.toCharArray()) {
                if (!Character.isDigit(c))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Public set for City: Validate if City Name is not a number.
     *
     * @param city
     */

    public void setCity(String city) {
        if (isNumeric(city) || city == null) {
            throw new IllegalArgumentException("The city in your Address is not valid or it's missing. Please try again.");
        } else {
            this.city = city.toUpperCase();
        }
    }

    /**
     * Public get for Street
     *
     * @return street
     */

    public String getStreet() {
        return street;
    }

    /**
     * Public set for Street : no validation
     *
     * @return street
     */

    public void setStreet(String street) {
        if (isNumeric(street) || street == null) {
            throw new IllegalArgumentException("The street format in your Address is not valid or it's missing. Please try again");
        } else this.street = street.toUpperCase();
    }

    /**
     * Public get for Zip-Code: with input validation
     *
     * @return zipCode
     */

    public String getZipCode() {
        return zipCode;
    }

    /**
     * Public set for Zip-Code: with input validation
     *
     * @param zip
     */

    public void setZipCode(String zip) {
        if (zip == null)
            throw new IllegalArgumentException("Zip-Code can´t be null! (Correct Format: xxxx-xxx)");
        else {
            if (zip.length() == 7) {
                zip = addHyphenToZipCode(zip);
            }
            //Validates if the zip code is in the correct format (4620-580) - PT Format
            if (validateIfZipCodeIsInCorrectFormate(zip)) {
                this.zipCode = zip;
            } else {
                throw new IllegalArgumentException("Zip-Code is not in the correct format! (xxxx-xxx)");
            }
        }
    }

    /**
     * Auxiliary method to Validate if the zip code is in the correct format (4620-580) - Validation for PT users
     *
     * @param zip
     * @return
     */

    private boolean validateIfZipCodeIsInCorrectFormate(String zip) {
        String regex = "^[0-9]{4}-[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(zip);
        return matcher.matches();
    }

    /**
     * Auxiliary method to Add '-' in case user forget to add it
     *
     * @param zip
     * @return
     */

    private static String addHyphenToZipCode(String zip) {
        return zip.substring(0, 4) + "-" + zip.substring(4, zip.length());
    }

    /**
     * Override method equals
     *
     * @param o
     * @return boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode);
    }

    /**
     * Override method hashcode
     *
     * @return
     */

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }

    /**
     * Return homeAdress as a String
     *
     * @return String
     */

    public String homeAddressToString() {
        return street + ", " + city + ", " + zipCode;
    }

    /**
     * Return birthPlace as a String
     *
     * @return String
     */

    public String birthplaceToString() {
        return birthPlace;
    }
}

