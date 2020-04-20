package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
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

@Service
public class US005_1AdminAddsCategoryToGroupService {

    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PersonRepository personRepository;


    /**
     * User Story 5.1 .- As a group admin i want to associate a category to my group.
     *
     * @param dto
     * @return categoryDTO
     */
    public CategoryDTO addCategoryToGroup(CreateGroupCategoryDTO dto) {

        Group group = groupsRepository.findGroupByDescription(new Description(dto.getGroupDescription()));
        PersonID personID = personRepository.findPersonByEmail(new Email(dto.getPersonEmail())).getID();

        if (group.isGroupAdmin(personID)) {
            Category categoryAdded = categoryRepository.createCategory(new Denomination(dto.getCategoryDenomination()), group.getID());
            return CategoryDTOAssembler.createCategoryDTOFromCategory(categoryAdded);
        } else
            throw new IllegalArgumentException("This person is not member or admin of this group.");
    }
}


