package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Denomination;

import java.util.Set;

public interface CategoryRepository extends Repository {

    /**
     * Find an Category by it´s id
     *
     * @param categoryID for categoryId
     *
     */

    Category getByID (ID categoryID);

    /**
     * Add a new category to CategoryList
     *
     * @param nameOfCategory for nameOfCategory
     * @param ownerID for ownerId
     *
     * @return category created
     */

    Category createCategory (Denomination nameOfCategory, OwnerID ownerID);

    /**
     * Get list of Categories By Owner ID
     *
     * @param ownerID for ownerId
     * @return category created
     */

    Set<Category> returnCategoriesByOwnerID (OwnerID ownerID);

    /**
     * Add multiple categories
     * @param setOfCategories for setOfCategories
     * @param ownerID for ownerId
     * @return boolean
     */

    boolean addMultipleCategories(Set<Denomination> setOfCategories, OwnerID ownerID);
}
