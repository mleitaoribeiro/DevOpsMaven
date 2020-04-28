package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.assemblers.CategoryDTOAssembler;
import switch2019.project.customExceptions.NoPermissionException;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.domain.domainEntities.shared.Denomination;

@Service
public class US005_1AdminAddsCategoryToGroupService {

    @Autowired
    private GroupRepository groupsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PersonRepository personRepository;


    /**
     * User Story 5.1 - As a group admin i want to associate a category to my group.
     *
     * @param dto
     * @return categoryDTO
     */
    public CategoryDTO addCategoryToGroup(CreateGroupCategoryDTO dto) {

        Group group = groupsRepository.findGroupByDescription(new Description(dto.getGroupDescription()));
        PersonID personID = personRepository.findPersonByEmail(new Email(dto.getPersonEmail())).getID();

        if (!group.isGroupMember(personID)) {
            throw new NoPermissionException("This person is not member of this group.");
        }
        else if (!group.isGroupAdmin(personID))
            throw new NoPermissionException("This person is not admin of this group.");
        else {
            Category categoryAdded = categoryRepository.createCategory(new Denomination(dto.getCategoryDenomination()), group.getID());
            return CategoryDTOAssembler.createCategoryDTOFromCategory(categoryAdded);
        }
    }

    /**
     * method that finds a category by category ID
     *
     * @param categoryDescription
     * @param groupDescription
     * @return CategoryDTO representing a Category
     */
    public CategoryDTO getCategoryByCategoryID (String categoryDescription, String groupDescription) {

        //find groupID that created the category
        OwnerID newGroupID = groupsRepository.findGroupByDescription(new Description (groupDescription)).getID();

        //transform strings given in a category ID
        CategoryID newCategoryID = new CategoryID(new Denomination(categoryDescription), newGroupID);

        //find category by ID
        Category newCategory = categoryRepository.getByID(newCategoryID);

        //return DTO that represents category
        return CategoryDTOAssembler.createCategoryDTOFromCategory(newCategory);
    }
}


