package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.deserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US007CreateGroupAccountControllerRest {

    @Autowired
    US007CreateGroupAccountService service;

    @PostMapping("/groups/{groupDescription}/accounts")
    public ResponseEntity<AccountDTO> createGroupAccount(@PathVariable String groupDescription, @RequestBody CreateGroupAccountInfoDTO info)  {

        //Arrange the entry dto with the given strings:
        CreateGroupAccountDTO dto = AccountDTOAssembler.transformToCreateGroupAccountDTO(groupDescription, info);

        //Use the service to obtain the exit DTO
        AccountDTO result = service.createGroupAccount(dto);

        Link selfLink = linkTo(methodOn(US007CreateGroupAccountControllerRest.class)
                .getAccountByAccountID(result.getDenomination(),result.getOwnerID()))
                .withSelfRel();

        result.add(selfLink);

        Link accountsLink = linkTo(methodOn(US007CreateGroupAccountControllerRest.class)
                .getAccountsByGroupID(result.getOwnerID()))
                .withRel("accounts");

        result.add(accountsLink);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/groups/{ownerID}/accounts/{accountDenomination}")
    public ResponseEntity<Object> getAccountByAccountID
            (@PathVariable final String accountDenomination, @PathVariable String ownerID){

        AccountDTO result = service.getAccountByAccountID(accountDenomination,ownerID);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/groups/{groupDescription}/accounts")
    public ResponseEntity<Object> getAccountsByGroupID
    (@PathVariable final String groupDescription) {

        Set<AccountDTO> accounts = service.getAccountsByGroupID(groupDescription);

        for (AccountDTO account : accounts) {
            Link selfLink = linkTo(methodOn(US007CreateGroupAccountControllerRest.class)
                    .getAccountByAccountID(account.getDenomination(), account.getOwnerID()))
                    .withSelfRel();
            account.add(selfLink);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
