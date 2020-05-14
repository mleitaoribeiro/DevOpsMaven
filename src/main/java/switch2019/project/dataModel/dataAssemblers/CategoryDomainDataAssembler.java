
package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.regex.Pattern;

public class CategoryDomainDataAssembler {

    /**
     * Private constructor to hide the implicit public one
     */

    private CategoryDomainDataAssembler() {}

    /**
     * Method for transforming an Category object into a CategoryJPA object
     *
     * @param category
     * @return categoryJPA
     */

    public static CategoryJpa toData(Category category) {
        return new CategoryJpa(category.getID().getOwnerIDString(), category.getNameOfCategory());
    }

    /**
     * Method for transforming an CategoryJPA object into a Category object
     *
     * @param categoryJPA
     * @return category
     */

    public static Category toDomain(CategoryJpa categoryJPA) {

        Denomination categoryDenomination = new Denomination(categoryJPA.getCategoryKeyJPA().getDenomination());
        String owner = categoryJPA.getCategoryKeyJPA().getOwner();
        OwnerID ownerID;

        if (isEmail(owner))
            ownerID = new PersonID(new Email(owner));
        else
            ownerID = new GroupID(new Description(owner));

        return new Category(categoryDenomination, ownerID);
    }

    /**
     * Auxiliary method to check if a string is an email (Person ID)
     *
     * @param owner
     * @return
     */

    private static boolean isEmail(String owner) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(owner).matches();
    }
}

