package switch2019.project.domain.domainEntities.ledger;

import java.util.Objects;

public class Type {
    private final boolean typeValue;

    public Type (boolean typeValue){
        this.typeValue = typeValue;
    }

    @Override
    public String toString() {
        if (typeValue) return "CREDIT";
        else return "DEBIT";
    }

    public boolean getType() {
        return typeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return typeValue == type1.typeValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeValue);
    }
}

