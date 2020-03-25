package switch2019.project.services;

import switch2019.project.DTO.CreateCategoryInGroupDTO;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
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
    public Optional<Category> addCategoryToGroup(CreateCategoryInGroupDTO dto) {

        //finding the right group where the new category will be added:
        Group group = groupsRepository.findGroupByDescription(new Description(dto.getGroupDescription()));

        //verify if the category creator is a group admin in order to continue with the method:
        if (group.isGroupAdmin(personRepository.findPersonByEmail(new Email(dto.getPersonEmail())).getID())) {

            //create category and associate it with the group:
            return Optional.of(categoryRepository.createCategory(new Denomination(dto.getCategoryDenomination()), group.getID()));

        } else return Optional.empty();
    }
}


