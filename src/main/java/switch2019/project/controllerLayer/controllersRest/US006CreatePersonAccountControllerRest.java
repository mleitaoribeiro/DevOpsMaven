package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;


@RestController
public class US006CreatePersonAccountControllerRest {

    @Autowired
    US006CreatePersonAccountService service;

    @PostMapping("/createPersonAccount")
    public ResponseEntity<AccountDTO> createPersonAccount(@RequestBody CreatePersonAccountInfoDTO info) {

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(info);

        AccountDTO result = service.createPersonAccount(createPersonAccountDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }
}
