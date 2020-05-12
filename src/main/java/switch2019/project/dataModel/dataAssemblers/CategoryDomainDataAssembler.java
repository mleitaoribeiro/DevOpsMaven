
package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.Owner;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.regex.Pattern;

public class CategoryDomainDataAssembler {

    public CategoryJpa toData (Category category) {
        return new CategoryJpa(category.getID().getOwnerIDString(), category.getNameOfCategory());
    }

    public Category toDomain (CategoryJpa categoryJPA) {

        Denomination categoryDenomination = new Denomination(categoryJPA.getCategoryKeyJPA().getDenomination());

        String owner = categoryJPA.getCategoryKeyJPA().getOwner();

        OwnerID ownerID;

        if (isEmail(owner))
            ownerID = new PersonID(new Email(owner));
        else
            ownerID = new GroupID(new Description(owner));

        return new Category (categoryDenomination, ownerID);
    }

    private boolean isEmail(String owner) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(owner).matches();
    }


}

