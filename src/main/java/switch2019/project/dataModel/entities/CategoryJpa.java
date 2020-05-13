package switch2019.project.dataModel.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name="categories")
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
}

