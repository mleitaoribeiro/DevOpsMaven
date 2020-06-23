package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.deserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class US006CreatePersonAccountControllerRest {

    @Autowired
    US006CreatePersonAccountService service;

    @PostMapping("/persons/{personEmail}/accounts")
    public ResponseEntity<AccountDTO> createPersonAccount(@PathVariable final String personEmail, @RequestBody CreatePersonAccountInfoDTO info) {

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(personEmail, info);

        AccountDTO result = service.createPersonAccount(createPersonAccountDTO);

        Link selfLink = linkTo(methodOn(US006CreatePersonAccountControllerRest.class)
                .getAccountByAccountID(result.getDenomination(), result.getOwnerID()))
                .withSelfRel();

        Link allAccountsLink = linkTo(methodOn(US006CreatePersonAccountControllerRest.class)
                .getAccountsByPersonID(result.getOwnerID()))
                .withSelfRel();

        result.add(selfLink);
        result.add(allAccountsLink);

        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

    @GetMapping(value = "persons/{personEmail}/accounts/{accountDenomination}")
    public ResponseEntity<AccountDTO> getAccountByAccountID
    (@PathVariable final String accountDenomination, @PathVariable final String personEmail) {

        AccountDTO result = service.getAccountByAccountID(accountDenomination, personEmail);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "persons/{personEmail}/accounts")
    public ResponseEntity<Object> getAccountsByPersonID
    (@PathVariable final String personEmail) {

        Set<AccountDTO> accounts = service.getAccountsByPersonID(personEmail);

        for (AccountDTO account : accounts) {
            Link selfLink = linkTo(methodOn(US006CreatePersonAccountControllerRest.class)
                    .getAccountByAccountID(account.getDenomination(), account.getOwnerID()))
                    .withSelfRel();

            account.add(selfLink);
        }

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
