package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.CategoryDTO;

import switch2019.project.DTO.CreateGroupCategoryDTO;
import switch2019.project.DTO.CreateGroupCategoryInputDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.assemblers.CategoryDTOAssembler;

@RestController
public class US005_1AdminAddsCategoryControllerRest {

    @Autowired
    private US005_1AdminAddsCategoryToGroupService service;

    @GetMapping("/addCategoryToGroup")
    public ResponseEntity<CategoryDTO> addCategoryToGroup(@RequestParam(value = "groupDescription") String groupDescription,
                                                          @RequestParam(value = "personEmail") String personEmail,
                                                          @RequestParam(value = "categoryDenomination") String categoryDenomination) {

        CreateGroupCategoryDTO dto = CategoryDTOAssembler.createGroupCategoryDTOFromStrings(groupDescription, personEmail, categoryDenomination);
        CategoryDTO result = service.addCategoryToGroup(dto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/addCategoryToGroup")
    public ResponseEntity<CategoryDTO> addCategoryToGroup(@RequestBody CreateGroupCategoryInputDTO dto) {
        CreateGroupCategoryDTO categoryDTO = CategoryDTOAssembler.transformToCreateGroupCategoryDTO(dto);
        CategoryDTO result = service.addCategoryToGroup(categoryDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
