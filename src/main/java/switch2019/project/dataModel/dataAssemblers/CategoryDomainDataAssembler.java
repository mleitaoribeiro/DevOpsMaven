package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.category.Category;

public class CategoryDomainDataAssembler {

    public CategoryJpa toData (Category category) {
        return new CategoryJpa(category.getID());
    }

    public Category toDomain (CategoryJpa categoryJPA) {
        return new Category (categoryJPA.getID().getDenomination(), categoryJPA.getID().getOwnerID());
    }

}
