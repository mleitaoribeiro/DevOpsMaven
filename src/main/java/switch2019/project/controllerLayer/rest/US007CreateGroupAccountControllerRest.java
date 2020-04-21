package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupAccountDTO;
import switch2019.project.DTO.DeserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

@RestController
public class US007CreateGroupAccountControllerRest {

    @Autowired
    US007CreateGroupAccountService service;

    /**
     * Controller that takes the posted information, and creates an Account in a given Group.
     * @param info
     * @return
     */
    @PostMapping("/addGroupAccount")
    public ResponseEntity<AccountDTO> addGroupAccount (@RequestBody CreateGroupAccountInfoDTO info)  {

        //Arrange the entry dto with the given strings:
        CreateGroupAccountDTO dto = AccountDTOAssembler.transformToCreateGroupAccountDTO(info);

        //Use the service to obtain the exit DTO
        AccountDTO result = service.createGroupAccount(dto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
