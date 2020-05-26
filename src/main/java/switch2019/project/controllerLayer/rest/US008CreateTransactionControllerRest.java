package switch2019.project.controllerLayer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.assemblers.LedgerDTOAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class US008CreateTransactionControllerRest {

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

    /**
     * Get transactions by Id
     * @param ownerID, id
     * @return transactionsDTO
     */



    /**
     * Get all transactions by Owner Id
     * @param personId
     * @return List<TransactionShortDTO>
     */

    @GetMapping(value = "persons/{personId}/ledger/transactions")
    public ResponseEntity<Object> getPersonsTransactionsByLedgerId (@PathVariable final String personId) {
        List<TransactionShortDTO> allTransactions = service.getTransactionsByLedgerId(personId);
/*
        for(TransactionShortDTO transaction : allTransactions) {
            Link selfLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                    .getPersonTransactionByID(personId, transaction.getId()))
                    .withSelfRel();
            transaction.add(selfLink);
        }*/
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }

    @GetMapping(value = "groups/{groupId}/ledger/transactions")
    public ResponseEntity<Object>  getGroupTransactionsByLedgerId (@PathVariable final String groupId) {
        List<TransactionShortDTO> allTransactions = service.getTransactionsByLedgerId(groupId);
    /*
        for(TransactionShortDTO transaction : allTransactions) {
            Link selfLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                    .getGroupTransactionByID(groupId, transaction.getId()))
                    .withSelfRel();
            transaction.add(selfLink);
        }*/
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }
}


