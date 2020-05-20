package switch2019.project.dataModel.entities;

import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class LedgerIdJpa {

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
}
