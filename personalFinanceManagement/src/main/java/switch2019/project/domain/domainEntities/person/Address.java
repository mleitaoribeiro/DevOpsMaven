package switch2019.project.domain.domainEntities.person;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {

    // The private Address variables
    private final String street;
    private final String city;
    private final String postalCode;
    private final String birthPlace;

    //BirthDate Constructor
    public Address(String birthPlace) {
        this.birthPlace = setValidBirthPlace(birthPlace);
        this.street = null;
        this.city = null;
        this.postalCode = null;
    }

    public Address(String street, String city, String postalCode) {
        this.street = setValidStreet(street);
        this.city = setValidCity(city);
        this.postalCode = setValidPostalCode(postalCode);
        this.birthPlace = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, postalCode);
    }

    @Override
    public String toString() {
        return this.street + ", " + this.city + ", " + this.postalCode;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    private String setValidBirthPlace(String birthPlace) {
        if (isNumeric(birthPlace) || birthPlace == null || birthPlace.isEmpty()) {
            throw new IllegalArgumentException("The city in your Address is not valid or it's missing. Please try again.");
        } else {
            return birthPlace.toUpperCase();
        }
    }

    private String setValidCity(String city) {
        if (isNumeric(city) || city == null || city.isEmpty()) {
            throw new IllegalArgumentException("The city in your Address is not valid or it's missing. Please try again.");
        } else {
            return city.toUpperCase();
        }
    }

    public String setValidStreet(String street) {
        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException("The street format in your Address is not valid or it's missing. Please try again");
        } else return street.toUpperCase();
    }


    private String setValidPostalCode(String postalCode) {
        if (postalCode == null || postalCode.isEmpty())
            throw new IllegalArgumentException("Postal Code can´t be null! (Correct Format: xxxx-xxx)");
        else {
            if (postalCode.length() == 7) {
                postalCode = addHyphenToPostalCode(postalCode);
                return postalCode;
            }
            //Validates if the zip code is in the correct format (4620-580) - PT Format
            if (postalCodeIsInCorrectFormat(postalCode)) {
                return postalCode;
            } else {
                throw new IllegalArgumentException("Postal Code is not in the correct format! (xxxx-xxx)");
            }
        }
    }

    private boolean postalCodeIsInCorrectFormat(String postalCode) {
        String regex = "^[0-9]{4}-[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    private static String addHyphenToPostalCode(String postalCode) {
        return postalCode.substring(0, 4) + "-" + postalCode.substring(4, postalCode.length());
    }

    private static boolean isNumeric(String city) {
        if (city != null)
            for (char c : city.toCharArray()) {
                if (Character.isDigit(c))
                    return true;
            }
        return false;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

}
