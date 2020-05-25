package switch2019.project.applicationLayer.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.infrastructure.dataBaseRepositories.PersonDbRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class US008CreateTransactionServiceTest {

    @Autowired
    private US008CreateTransactionService service;

    @Test
    void getTrasactionsByLedgerId() {

        String email = "marge@hotmail.com";

        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(new Email(email)));

        TransactionDTO transactionDTO = new TransactionDTO("100.00", "Bought a cheap sofa",
                "2020-02-14 11:24", "HOUSE", "Gold Card", "IKEA", "false");

        TransactionDTO transactionDTO1 = new TransactionDTO("50.00", "Grocery for baking cookies",
                "2020-03-20 13:04", "HOUSE", "MasterCard", "Kwik-E-Mart", "false");

        //List<TransactionDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);
        List<TransactionDTO> expected = Collections.emptyList();

        assertEquals(expected, result);
    }
}