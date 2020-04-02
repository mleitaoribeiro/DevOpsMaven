package switch2019.project.applicationLayer;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateGroupCategoryDTO;
import switch2019.project.assemblers.CategoryDTOAssembler;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.infrastructure.repositories.CategoryRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.domain.domainEntities.shared.Denomination;

public class US005_1AdminAddsCategoryToGroupService {

    private GroupsRepository groupsRepository;
    private CategoryRepository categoryRepository;
    private PersonRepository personRepository;

    public US005_1AdminAddsCategoryToGroupService(GroupsRepository groupRep, CategoryRepository categoryRep, PersonRepository personRep) {
        this.groupsRepository = groupRep;
        this.categoryRepository = categoryRep;
        this.personRepository = personRep;
    }

    /**
     * User Story 5.1 .- As a group admin i want to associate a category to my group.
     *
     * @param dto
     * @return
     */
    public CategoryDTO addCategoryToGroup(CreateGroupCategoryDTO dto) {

        Group group = groupsRepository.findGroupByDescription(new Description(dto.getGroupDescription()));
        PersonID personID = personRepository.findPersonByEmail(new Email(dto.getPersonEmail())).getID();

        if (group.isGroupAdmin(personID)) {
            Category categoryAdded = categoryRepository.createCategory(new Denomination(dto.getCategoryDenomination()), group.getID());
            return CategoryDTOAssembler.createCategoryDTOFromCategory(categoryAdded);
        } else
            throw new IllegalArgumentException("This person is not a group admin or member and could not add the category.");
    }
}


