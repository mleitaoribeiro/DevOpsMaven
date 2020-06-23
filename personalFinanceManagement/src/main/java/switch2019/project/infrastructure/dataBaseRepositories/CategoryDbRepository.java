
package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.CategoryDomainDataAssembler;
import switch2019.project.dataModel.entities.CategoryIdJpa;
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

@Component
@Primary
public class CategoryDbRepository implements CategoryRepository {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    // String literals - Exceptions
    private static final String NO_CATEGORY_FOUND = "No category found with that ID.";
    private static final String CATEGORY_ALREADY_EXISTS = "This category already exists.";
    private static final String NULL_OWNER = "Owner ID can't be null.";

    public Category getByID(ID categoryID)  {
        String[] split = categoryID.toString().replace(", ", ",").split(",");
        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findByCategoryIdJpa(new CategoryIdJpa(split[1], split[0]));
        if (categoryJpa.isPresent())
            return CategoryDomainDataAssembler.toDomain(categoryJpa.get());
        else throw new ArgumentNotFoundException(NO_CATEGORY_FOUND);
    }

    public boolean isIDOnRepository(ID categoryID) {
        String[] split = categoryID.toString().replace(", ", ",").split(",");
        Optional<CategoryJpa> categoryJpa = categoryJpaRepository.findByCategoryIdJpa(new CategoryIdJpa(split[1], split[0]));
        return categoryJpa.isPresent();
    }

    public long repositorySize() {
        return this.categoryJpaRepository.count();
    }

    public Category createCategory(Denomination nameOfCategory, OwnerID ownerID) {
        if (!isIDOnRepository(new CategoryID(nameOfCategory, ownerID))) {
            Category category = new Category(nameOfCategory, ownerID);
            CategoryJpa categoryJpa = categoryJpaRepository.save(CategoryDomainDataAssembler.toData(category));
            return CategoryDomainDataAssembler.toDomain(categoryJpa);
        } else throw new ResourceAlreadyExistsException(CATEGORY_ALREADY_EXISTS);
    }

    public boolean addMultipleCategories(Set<Denomination> categories, OwnerID ownerID) {
        long sizeBefore = this.categoryJpaRepository.count();
        for (Denomination category : categories) {
            this.createCategory(category, ownerID);
        }
        return this.categoryJpaRepository.count() == sizeBefore + categories.size();
    }

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


