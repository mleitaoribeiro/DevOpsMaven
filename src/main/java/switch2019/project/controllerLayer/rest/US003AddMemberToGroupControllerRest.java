package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.ServiceDTO.AddMemberDTO;
import switch2019.project.DTO.DeserializationDTO.AddMemberInfoDTO;
import switch2019.project.DTO.SerializationDTO.AddedMemberDTO;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.assemblers.GroupDTOAssembler;

@RestController
public class US003AddMemberToGroupControllerRest {

    @Autowired
    US003AddMemberToGroupService service;

    @PostMapping("/addMemberToGroup")
    public ResponseEntity<Object> addMemberToGroup(@RequestBody AddMemberInfoDTO info){

        AddMemberDTO addMemberDTO = GroupDTOAssembler.transformIntoAddMemberDTO(info);

        AddedMemberDTO addedMemberDTO = service.addMemberToGroup(addMemberDTO);

        return new ResponseEntity<>(addedMemberDTO, HttpStatus.CREATED);
    }
}