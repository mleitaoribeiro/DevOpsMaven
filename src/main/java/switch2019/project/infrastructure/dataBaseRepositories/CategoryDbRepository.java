
package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.CategoryDomainDataAssembler;
import switch2019.project.dataModel.entities.CategoryJpa;
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

    public CategoryDbRepository() {
    }

    @Override
    public String toString() {
        return "Category Repository: " + categoryJpaRepository.toString();
    }

    /**
     * Find category by ID
     *
     * @param categoryID
     * @return account
     */

    public Category getByID(ID categoryID) {
        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findById(categoryID.toString());
        if (categoryJpa.isPresent())
            return CategoryDomainDataAssembler.toDomain(categoryJpa.get());
        throw new ArgumentNotFoundException("No category found with that ID.");
    }


    /**
     * method to validate if the account is in the accounts Repository
     *
     * @param categoryID
     * @return boolean
     */

    public boolean isIDOnRepository(ID categoryID) {
        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findById(categoryID.toString());
        if (categoryJpa.isPresent())
            return true;
        return false;
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

            Category category = getByID(new CategoryID(nameOfCategory, ownerID));

            CategoryJpa categoryJpa = new CategoryJpa(nameOfCategory.getDenominationValue(), ownerID.toString());

            categoryJpaRepository.save(categoryJpa);

            return category;

        } else throw new ResourceAlreadyExistsException("This category already exists.");
    }


    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     * @return boolean
     */

    public boolean removeCategory(Denomination categoryToRemove, OwnerID ownerID) {

        CategoryJpa categoryJpa = new CategoryJpa(categoryToRemove.getDenominationValue(), ownerID.toString());

        if (!isIDOnRepository(new CategoryID(categoryToRemove, ownerID))) {
            categoryJpaRepository.delete(categoryJpa);
            return true;
        }

        return false;
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
            else throw new ArgumentNotFoundException("No category found with that ID.");
        }
        throw new IllegalArgumentException("Owner ID can't be null.");
    }
}


