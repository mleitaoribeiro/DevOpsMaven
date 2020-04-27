package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class US006CreatePersonAccountControllerRest {

    @Autowired
    US006CreatePersonAccountService service;

    /**
     * US006
     * As an User, I want to create an account to myself.
     * It has a denomination and a description, so then I can use the account in my transactions.
     *
     * @param info
     * @return Response Entity with AccountDTO and HTTPStatus
     */

    @PostMapping("/persons")
    public ResponseEntity<AccountDTO> createPersonAccount(@RequestBody CreatePersonAccountInfoDTO info) {

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.transformIntoCreatePersonAccountDTO(info);

        AccountDTO result = service.createPersonAccount(createPersonAccountDTO);

        Link selfLink = linkTo(methodOn(US006CreatePersonAccountControllerRest.class)
                .getAccountByAccountID(result.getDenomination(), result.getOwnerID()))
                .withSelfRel();

       result.add(selfLink);

       return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

    /**
     * Method to get an Account by AccountID
     *
     * @param accountDenomination
     * @param personEmail
     * @return Response Entity with AccountDTO and HTTPStatus
     */

    @GetMapping(value = "persons/{personEmail}/accounts/{accountDenomination}")
    public ResponseEntity<AccountDTO> getAccountByAccountID
    (@PathVariable final String accountDenomination, @PathVariable final String personEmail) {

        AccountDTO result = service.getAccountByAccountID(accountDenomination, personEmail);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
