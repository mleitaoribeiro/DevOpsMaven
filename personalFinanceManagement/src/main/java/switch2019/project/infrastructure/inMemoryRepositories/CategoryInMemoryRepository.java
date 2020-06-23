package switch2019.project.infrastructure.inMemoryRepositories;

import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryInMemoryRepository implements CategoryRepository {

    // Private instance variables
    private final Set<Category> categories;

    public CategoryInMemoryRepository() {
        categories = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Category Repository: " + categories.toString();
    }

    public Category getByID (ID categoryID) {
        for (Category category : categories) {
            if (category.getID().equals(categoryID))
                return category;
        }
        throw new ArgumentNotFoundException("No category found with that ID.");
    }

    public boolean isIDOnRepository(ID categoryID) {
        for (Category category : categories) {
            if (category.getID().equals(categoryID))
                return true;
        }
        return false;
    }

    public long repositorySize () {
        return this.categories.size();
    }

    public Category createCategory(Denomination nameOfCategory, OwnerID ownerID) {
        if (this.categories.contains(new Category(nameOfCategory, ownerID)))
            throw new ResourceAlreadyExistsException("This category already exists.");
        else {
            Category newCategory = new Category(nameOfCategory, ownerID);
            categories.add(newCategory);
            return newCategory;
        }
    }

    public boolean addMultipleCategories(Set<Denomination> categories, OwnerID ownerID) {
        int sizeBefore = this.categories.size();
        for (Denomination category : categories) {
            this.createCategory(category, ownerID);
        }
        return this.categories.size() == sizeBefore + categories.size();
    }

    public boolean isSetOfCategoriesValid(Set<Denomination> setOfCategories, OwnerID ownerID) {
        Set<Category> list = new HashSet<>();
        for (Denomination category : setOfCategories) {
            list.add(new Category(category, ownerID));
        }
        return this.categories.containsAll(list);
    }

    public Set<Category> returnCategoriesByOwnerID(OwnerID ownerID) {
        Set<Category> listOfCategoriesByOwnerID = new HashSet<>();
        if (ownerID != null) {
            for (Category category : categories)
                if (category.getOwnerID().equals(ownerID))
                    listOfCategoriesByOwnerID.add(category);
            if (!listOfCategoriesByOwnerID.isEmpty())
                return listOfCategoriesByOwnerID;
            else throw new ArgumentNotFoundException("No category found with that ID.");
        }
        throw new IllegalArgumentException("Owner ID can't be null.");
    }

}
