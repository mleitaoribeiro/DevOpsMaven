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
     * @param categoryID
     *
     */

    Category getByID (ID categoryID);

    /**
     * Add a new category to CategoryList
     *
     * @param nameOfCategory
     * @param ownerID
     *
     */

    Category createCategory (Denomination nameOfCategory, OwnerID ownerID);

    /**
     * Remove one category from the Repository
     *
     * @param categoryToRemove
     *
     */

    boolean removeCategory (Denomination categoryToRemove, OwnerID ownerID);

    /**
     * Get list of Categories By Owner ID
     *
     * @param ownerID
     *
     */

    Set<Category> returnCategoriesByOwnerID (OwnerID ownerID);


}
