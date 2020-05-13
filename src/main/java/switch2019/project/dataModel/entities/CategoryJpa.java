package switch2019.project.dataModel.entities;

import lombok.Data;

import javax.persistence.*;

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

}

