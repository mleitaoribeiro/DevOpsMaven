package switch2019.project.model;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public void setZipCode(String zip){

        if (zip.length() == 7) {
            zip = addHyphenToZipCode(zip);
        }

        //Validates if the zip code is in the correct format (4620-580)
       String regex = "^[0-9]{4}-[0-9]{3}$";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(zip);
       if (matcher.matches()) {
           this.zipCode = zip;
       }
       else {
           throw new IllegalArgumentException("Zip-Code is not in the correct format! (xxxx-xxx)");
       }
    }

    //auxiliary method to Add '-' in case user forget to add it.
    public String addHyphenToZipCode (String zip) {
        if (zip.length() == 7 ) {
            return zip.substring(0, 4) + "-" + zip.substring(4, zip.length());
        }
        return zip;
    }


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
