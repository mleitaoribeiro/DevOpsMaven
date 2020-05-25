package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;

import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.assemblers.LedgerDTOAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US008_1CreateGroupTransactionControllerRest {

    @Autowired
    US008CreateTransactionService service;

    /**
     * Controller that takes the posted information, and creates an Transaction in a given Group.
     * @param info
     * @return
     */

    @PostMapping("/groups/{groupDescription}/ledger")
    public ResponseEntity<TransactionShortDTO> createGroupTransaction(@PathVariable String groupDescription,@RequestBody CreateTransactionInfoDTO info){


        //Arrange the entry dto with the given strings:
        CreateGroupTransactionDTO dto = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, info);

        //Use the service to obtain the exit DTO
        TransactionShortDTO result = service.addGroupTransaction(dto);

//        Link selfLink = linkTo(methodOn(US008_1CreateGroupTransactionControllerRest.class)
//
//                .withSelfRel();
//
//        result.add(selfLink);
        return new ResponseEntity<>( result,HttpStatus.CREATED);
    }

}


