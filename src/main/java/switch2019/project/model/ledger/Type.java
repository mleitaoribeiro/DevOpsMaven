package switch2019.project.model.ledger;

import java.util.Objects;

public class Type {
    private boolean type;

    /**
     * Constructor
     * @param type
     */

    public Type (boolean type){
        this.type=type;
    }

    @Override
    public String toString() {
        if (type) return "CREDIT";
        else return "DEBIT";
    }

    /**
     * Get Type
     */
    public boolean getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return type == type1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

