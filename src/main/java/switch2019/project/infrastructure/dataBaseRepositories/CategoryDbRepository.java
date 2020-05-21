
package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.AccountDomainDataAssembler;
import switch2019.project.dataModel.dataAssemblers.CategoryDomainDataAssembler;
import switch2019.project.dataModel.entities.CategoryIdJpa;
import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.infrastructure.jpa.CategoryJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component("CategoryDbRepository")
public class CategoryDbRepository implements CategoryRepository {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    // String literals - Exceptions
    private static final String NO_CATEGORY_FOUND = "No category found with that ID.";
    private static final String CATEGORY_ALREADY_EXISTS = "This category already exists.";
    private static final String NULL_OWNER = "Owner ID can't be null.";

    /**
     * Find category by ID
     *
     * @param categoryID
     * @return account
     */

    public Category getByID(ID categoryID)  {
        String[] split = categoryID.toString().replace(", ", ",").split(",");
        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findByCategoryIdJpa(new CategoryIdJpa(split[1], split[0]));
        if (categoryJpa.isPresent())
            return CategoryDomainDataAssembler.toDomain(categoryJpa.get());
        else throw new ArgumentNotFoundException(NO_CATEGORY_FOUND);
    }


    /**
     * method to validate if the account is in the accounts Repository
     *
     * @param categoryID
     * @return boolean
     */

    public boolean isIDOnRepository(ID categoryID) {
        String[] split = categoryID.toString().replace(", ", ",").split(",");
        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findByCategoryIdJpa(new CategoryIdJpa(split[1], split[0]));
        return categoryJpa.isPresent();
    }


    /**
     * Method to get the numbers of Categories in the Category List
     *
     * @return category
     */

    public long repositorySize() {
        return this.categoryJpaRepository.count();
    }


    /**
     * Add a new category to CategoryList
     *
     * @param nameOfCategory
     * @param ownerID
     * @return category
     */

    public Category createCategory(Denomination nameOfCategory, OwnerID ownerID) {
        if (!isIDOnRepository(new CategoryID(nameOfCategory, ownerID))) {
            Category category = new Category(nameOfCategory, ownerID);
            CategoryJpa categoryJpa = categoryJpaRepository.save(CategoryDomainDataAssembler.toData(category));
            return CategoryDomainDataAssembler.toDomain(categoryJpa);
        } else throw new ResourceAlreadyExistsException(CATEGORY_ALREADY_EXISTS);
    }


    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     * @return boolean
     */

    public boolean removeCategory(Denomination categoryToRemove, OwnerID ownerID) {
        if (this.isIDOnRepository(new CategoryID(categoryToRemove, ownerID))) {
            categoryJpaRepository.delete(CategoryDomainDataAssembler.toData(new Category(categoryToRemove, ownerID)));
            return true;
        }
        else throw new ArgumentNotFoundException(NO_CATEGORY_FOUND);
    }

    /**
     * Add multiple categories to CategoryList
     *
     * @return boolean
     */

    public boolean addMultipleCategories(Set<Denomination> categories, OwnerID ownerID) {
        long sizeBefore = this.categoryJpaRepository.count();
        for (Denomination category : categories) {
            this.createCategory(category, ownerID);
        }
        return this.categoryJpaRepository.count() == sizeBefore + categories.size();
    }


    /**
     * Get list of Categories By Owner ID
     *
     * @param ownerID
     * @return
     */

    public Set<Category> returnCategoriesByOwnerID(OwnerID ownerID) {
        Set<Category> listOfCategoriesByOwnerID = new HashSet<>();
        if (ownerID != null) {
            for (CategoryJpa categoryJpa : categoryJpaRepository.findAllByCategoryIdJpa_Owner(ownerID.toString()))
                listOfCategoriesByOwnerID.add(CategoryDomainDataAssembler.toDomain(categoryJpa));
            if (!listOfCategoriesByOwnerID.isEmpty())
                return listOfCategoriesByOwnerID;
            else throw new ArgumentNotFoundException(NO_CATEGORY_FOUND);
        }
        throw new IllegalArgumentException(NULL_OWNER);
    }
}


