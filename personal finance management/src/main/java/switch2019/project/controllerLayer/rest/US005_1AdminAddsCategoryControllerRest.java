package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serializationDTO.CategoryDenominationDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.assemblers.CategoryDTOAssembler;

import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class US005_1AdminAddsCategoryControllerRest {

    @Autowired
    US005_1AdminAddsCategoryToGroupService service;

    /**
     * US005.1
     * As a Group Administrator, I want to create a category and add it to the group.
     *
     * @param info
     * @return Response Entity with CategoryDTO and HTTPStatus
     */
    @PostMapping("groups/{groupDescription}/categories")
    public ResponseEntity<CategoryDTO> addCategoryToGroup(@PathVariable final String groupDescription,
                                                          @RequestBody CreateGroupCategoryInfoDTO info) {

        CreateGroupCategoryDTO categoryDTO = CategoryDTOAssembler.transformToCreateGroupCategoryDTO(groupDescription, info);

        CategoryDTO result = service.addCategoryToGroup(categoryDTO);

        Link selfLink = linkTo(methodOn(US005_1AdminAddsCategoryControllerRest.class)
                .getCategoryByCategoryID(result.getDenomination(), result.getOwnerID()))
                .withSelfRel();

        result.add(selfLink);


        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Method to get a category by categoryID
     *
     * @param categoryDescription
     * @param groupDescription
     * @return Response Entity with CategoryDTO and HTTPStatus
     */
    @GetMapping(value = "groups/{groupDescription}/categories/{categoryDescription}")
    public ResponseEntity<Object> getCategoryByCategoryID
    (@PathVariable final String categoryDescription, @PathVariable final String groupDescription) {

        CategoryDTO result = service.getCategoryByCategoryID(categoryDescription, groupDescription);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Method to get all categories by GroupID
     *
     * @param groupDescription
     * @return All categories from group and HTTPStatus
     */

    @GetMapping(value = "groups/{groupDescription}/categories")
    public ResponseEntity<Object> getCategoriesByGroupID
    (@PathVariable final String groupDescription) {

        Set<CategoryDenominationDTO> categoriesDTO = service.getCategoriesByGroupID(groupDescription);

        for (CategoryDenominationDTO category : categoriesDTO) {
            Link selfLink = linkTo(methodOn(US005_1AdminAddsCategoryControllerRest.class)
                    .getCategoryByCategoryID(groupDescription, category.getCategoryDenomination()))
                    .withSelfRel();

            category.add(selfLink);
        }

        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

}
