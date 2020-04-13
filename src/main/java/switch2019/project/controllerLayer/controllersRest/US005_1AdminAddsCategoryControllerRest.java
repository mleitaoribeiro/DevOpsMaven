package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.SerializationDTO.CategoryDTO;

import switch2019.project.DTO.ServiceDTO.CreateGroupCategoryDTO;
import switch2019.project.DTO.DeserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.assemblers.CategoryDTOAssembler;

@RestController
public class US005_1AdminAddsCategoryControllerRest {

    @Autowired
    private US005_1AdminAddsCategoryToGroupService service;

    @PostMapping("/addCategoryToGroup")
    public ResponseEntity<CategoryDTO> addCategoryToGroup(@RequestBody CreateGroupCategoryInfoDTO dto) {
        if (dto != null) {
            CreateGroupCategoryDTO categoryDTO = CategoryDTOAssembler.transformToCreateGroupCategoryDTO(dto);
            CategoryDTO result = service.addCategoryToGroup(categoryDTO);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
