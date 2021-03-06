package switch2019.project.dataModel.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryIdJpa implements Serializable {

    private String denomination;
    private String owner;

    protected CategoryIdJpa() {};

    public CategoryIdJpa(String owner, String denomination) {
        this.owner = owner;
        this.denomination = denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryIdJpa)) return false;
        CategoryIdJpa that = (CategoryIdJpa) o;
        return owner.equalsIgnoreCase(that.owner) &&
                denomination.equalsIgnoreCase(that.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, denomination);
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

