
package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.StringUtils;

public class CategoryDomainDataAssembler {

    private CategoryDomainDataAssembler() {}

    public static CategoryJpa toData(Category category) {
        return new CategoryJpa(category.getID().getOwnerIDString(), category.getNameOfCategory());
    }

    public static Category toDomain(CategoryJpa categoryJPA) {

        Denomination categoryDenomination = new Denomination(categoryJPA.getCategoryIdJpa().getDenomination());
        String owner = categoryJPA.getCategoryIdJpa().getOwner();
        OwnerID ownerID;

        if (StringUtils.isEmail(owner))
            ownerID = new PersonID(new Email(owner));
        else
            ownerID = new GroupID(new Description(owner));

        return new Category(categoryDenomination, ownerID);
    }
}

