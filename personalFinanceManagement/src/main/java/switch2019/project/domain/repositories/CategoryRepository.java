package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Denomination;

import java.util.Set;

public interface CategoryRepository extends Repository {

    Category getByID (ID categoryID);

    Category createCategory (Denomination nameOfCategory, OwnerID ownerID);

    Set<Category> returnCategoriesByOwnerID (OwnerID ownerID);

    boolean addMultipleCategories(Set<Denomination> setOfCategories, OwnerID ownerID);
}
