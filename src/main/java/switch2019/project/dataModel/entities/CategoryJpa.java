package switch2019.project.dataModel.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="categories")
public class CategoryJpa {

    @EmbeddedId
    private CategoryKeyJpa categoryKeyJpa;

    protected CategoryJpa() {
    };

    public CategoryJpa(String owner, String denomination) {
        categoryKeyJpa = new CategoryKeyJpa(owner, denomination);
    }

}

