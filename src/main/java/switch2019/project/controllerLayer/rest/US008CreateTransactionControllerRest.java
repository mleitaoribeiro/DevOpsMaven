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
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
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
     * Controller that takes the posted information and creates a transaction belonging to a given person.
     *
     * @param info
     * @return
     */

    @PostMapping("persons/{personId}/ledger/transactions")
    public ResponseEntity<TransactionShortDTO> createPersonTransaction(@PathVariable String personId, @RequestBody CreateTransactionInfoDTO info) {

        //Arrange the entry dto with the given strings:
        CreatePersonalTransactionDTO dto = LedgerDTOAssembler.transformToCreatePersonalTransactionDTO(personId, info);

        //Use the service to obtain the exit DTO
        TransactionShortDTO result = service.addPersonalTransaction(dto);

        Link selfLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                .getPersonTransactionByID(personId, result.getId()))
                .withSelfRel();

        result.add(selfLink);

        Link personTransactions = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                .getPersonTransactionsByLedgerId(personId))
                .withRel("transactions");

        result.add(personTransactions);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Controller that takes the posted information, and creates an Transaction in a given Group.
     *
     * @param info
     * @return
     */


    @PostMapping("/groups/{groupDescription}/ledger")
    public ResponseEntity<TransactionShortDTO> createGroupTransaction(@PathVariable String groupDescription, @RequestBody CreateTransactionInfoDTO info) {


        //Arrange the entry dto with the given strings:
        CreateGroupTransactionDTO dto = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, info);

        //Use the service to obtain the exit DTO
        TransactionShortDTO result = service.addGroupTransaction(dto);

        Link selfLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                .getGroupTransactionByID(result.getId().toString(), result.getId()))
                .withSelfRel();

        result.add(selfLink);

        Link allTransactionsLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                .getGroupTransactionsByLedgerId(result.getId().toString()))
                .withRel("transactions");

        result.add(allTransactionsLink);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    /**
     * Get transactions by Id
     *
     * @param personId
     * @param id
     * @return transactionsDTO
     */
    @GetMapping(value = "persons/{personId}/ledger/transactions/{id}")
    public ResponseEntity<Object> getPersonTransactionByID(@PathVariable final String personId, @PathVariable final Long id) {

        //Instancing the TransactionDto:
        TransactionDTO transactionDTO = service.getTransactionByID(personId, id);

        //Returning the TransactionDTO and the HTTP status as a ResponseEntity
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }


    /**
     * Get all transactions by Owner Id
     *
     * @param groupId
     * @param id
     * @return List<TransactionShortDTO>
     */
    @GetMapping(value = "groups/{groupId}/ledger/transactions/{id}")
    public ResponseEntity<Object> getGroupTransactionByID(@PathVariable final String groupId, @PathVariable final Long id) {

        //Instancing the TransactionDto:
        TransactionDTO transactionDTO = service.getTransactionByID(groupId, id);

        //Returning the TransactionDTO and the HTTP status as a ResponseEntity
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }


    @GetMapping(value = "persons/{personId}/ledger/transactions")
    public ResponseEntity<Object> getPersonTransactionsByLedgerId(@PathVariable final String personId) {
        List<TransactionShortDTO> allTransactions = service.getTransactionsByLedgerId(personId);

        for (TransactionShortDTO transaction : allTransactions) {
            Link selfLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                    .getPersonTransactionByID(personId, transaction.getId()))
                    .withSelfRel();
            transaction.add(selfLink);
        }
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }

    @GetMapping(value = "groups/{groupId}/ledger/transactions")
    public ResponseEntity<Object> getGroupTransactionsByLedgerId(@PathVariable final String groupId) {
        List<TransactionShortDTO> allTransactions = service.getTransactionsByLedgerId(groupId);

        for (TransactionShortDTO transaction : allTransactions) {
            Link selfLink = linkTo(methodOn(US008CreateTransactionControllerRest.class)
                    .getGroupTransactionByID(groupId, transaction.getId()))
                    .withSelfRel();
            transaction.add(selfLink);
        }
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }
}


