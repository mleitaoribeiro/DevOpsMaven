package switch2019.project.model.person;

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

    /**
     * Return birthPlace as a String
     *
     * @return String
     */

    public String getBirthPlace() {
        return this.birthPlace;
    }

    /**
     * Private Set for BirthPlace: Validade if birth place is not a number, null or it's missing
     *
     * @param birthPlace
     */
    private String setValidBirthPlace(String birthPlace) {
        if (isNumeric(birthPlace) || birthPlace == null || birthPlace.isEmpty()) {
            throw new IllegalArgumentException("The city in your Address is not valid or it's missing. Please try again.");
        } else {
           return birthPlace.toUpperCase();
        }
    }

    /**
     * Private set for City: Validate if City Name is not a number, null or it's missing
     *
     * @param city
     */

    private String setValidCity(String city) {
        if (isNumeric(city) || city == null || city.isEmpty()) {
            throw new IllegalArgumentException("The city in your Address is not valid or it's missing. Please try again.");
        } else {
            return city.toUpperCase();
        }
    }

    /**
     * Private set for Street: Validate if Street is not a number, null or it's missing
     *
     * @param street
     */


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

    /**
     * Auxiliary method to Validate if the postal code is in the correct format (4620-580) - Validation for PT users
     *
     * @param postalCode
     * @return
     */

    private boolean postalCodeIsInCorrectFormat(String postalCode) {
        String regex = "^[0-9]{4}-[0-9]{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    /**
     * Auxiliary method to Add '-' in case user forget to add it
     *
     * @param postalCode
     * @return
     */

    private static String addHyphenToPostalCode(String postalCode) {
        return postalCode.substring(0, 4) + "-" + postalCode.substring(4, postalCode.length());
    }

    /**
     * Validate if City is not Numeric
     *
     * @param city
     * @return
     */

    private static boolean isNumeric(String city) {
        if(city !=null)
            for (char c : city.toCharArray()) {
                if (Character.isDigit(c))
                    return true;
            } return false;
    }
}
