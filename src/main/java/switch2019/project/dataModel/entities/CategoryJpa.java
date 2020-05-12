package switch2019.project.dataModel.entities;

import lombok.Data;
import switch2019.project.domain.domainEntities.shared.CategoryID;

import javax.persistence.*;

@Data
//@Entity
//@Table(name="categories")
public class CategoryJpa {
    @Id
    @Embedded
    private CategoryID categoryID;

    protected CategoryJpa() {
    };

    public CategoryJpa(CategoryID categoryID) {
        this.categoryID = categoryID;
    }
}

