package switch2019.project.applicationLayer;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateCategoryInGroupDTO;
import switch2019.project.assemblers.CategoryDTOAssembler;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.infrastructure.repositories.CategoryRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.domain.domainEntities.shared.Denomination;



import java.util.Optional;

public class US005_1AdminAddsCategoryToCategoryListService {

    //Repositories are given as attributes of this service in order to allow its usage by the addCategoryToGroup method
    //without passing them as parameter:
    private GroupsRepository groupsRepository;
    private CategoryRepository categoryRepository;
    private PersonRepository personRepository;

    //Service constructor:
    public US005_1AdminAddsCategoryToCategoryListService(GroupsRepository groupRep, CategoryRepository categoryRep, PersonRepository personRep) {
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
    public CategoryDTO addCategoryToGroup(CreateCategoryInGroupDTO dto) {


        //finding the right group where the new category will be added:
        Group group = groupsRepository.findGroupByDescription(new Description(dto.getGroupDescription()));

        //Gathering the information needed to create the category:
        Denomination catDenomination = new Denomination(dto.getCategoryDenomination());
        GroupID catGroupId = group.getID();

        //verify if the category creator is a group admin in order to continue with the method:
        if (group.isGroupAdmin(personRepository.findPersonByEmail(new Email(dto.getPersonEmail())).getID())) {

            //Instancing the Category to be created and adding it to the category repository:
            Category categoryToBeAdded = categoryRepository.createCategory(catDenomination, catGroupId);

            //create category and associate it with the group:
            return CategoryDTOAssembler.createCategoryDTOFromCategory(categoryToBeAdded);
        } else {
            throw new IllegalArgumentException("This person is not a group admin or member and could not add the category.");
        }
    }
}


