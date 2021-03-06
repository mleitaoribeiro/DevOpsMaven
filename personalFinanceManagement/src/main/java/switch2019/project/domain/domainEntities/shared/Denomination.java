package switch2019.project.domain.domainEntities.shared;

import switch2019.project.utils.StringUtils;

import java.util.Objects;

public class Denomination {

    //Private instance of denomination
    private final String denominationValue;

    //Denomination constructor
    public Denomination(String denominationValue) {
        this.denominationValue = setValidDenomination(denominationValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Denomination otherDenominationValue = (Denomination) o;
        return this.denominationValue.equals(otherDenominationValue.denominationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denominationValue);
    }

    @Override
    public String toString() {
        return denominationValue;
    }

    private String setValidDenomination(String newDenomination) {

        if (newDenomination == null || newDenomination.isEmpty()){
            throw new IllegalArgumentException("The denomination can't be null or empty.");
        } else return StringUtils.normalizeDenomination(newDenomination);
    }

    public String getDenominationValue() {
        return denominationValue;
    }
}
