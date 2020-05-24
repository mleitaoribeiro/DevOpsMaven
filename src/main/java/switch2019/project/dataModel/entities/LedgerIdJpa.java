package switch2019.project.dataModel.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class LedgerIdJpa implements Serializable {

    private String owner;

    protected LedgerIdJpa() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerIdJpa key = (LedgerIdJpa) o;
        return owner.equalsIgnoreCase(key.owner);
    }
    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }

    @Override
    public String toString() {
        return owner;
    }

    public LedgerIdJpa(String owner) {
        this.owner = owner;
    }

    //Getter and Setter for the owner associated with the LedgerId

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
