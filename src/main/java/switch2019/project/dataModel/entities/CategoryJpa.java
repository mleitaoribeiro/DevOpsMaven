package switch2019.project.dataModel.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name="categories")
public class CategoryJpa {

    @EmbeddedId
    private CategoryIdJpa categoryIdJpa;

    protected CategoryJpa() {
    }

    public CategoryJpa(String owner, String denomination) {
        categoryIdJpa = new CategoryIdJpa(owner, denomination);
    }

    public CategoryIdJpa getCategoryKeyJPA() {
        return categoryIdJpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryJpa)) return false;
        CategoryJpa that = (CategoryJpa) o;
        return Objects.equals(categoryIdJpa, that.categoryIdJpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryIdJpa);
    }

    @Override
    public String toString() {
        return categoryIdJpa.getDenomination() + ", " + categoryIdJpa.getOwner();
    }

}

