package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.LedgerIdJpa;
import switch2019.project.dataModel.entities.LedgerJpa;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerDomainDataAssemblerTest {

    @Test
    void toDomainTRUE() {

        // Arrange
        Ledger ledger = new Ledger(new PersonID(new Email("marta@gmail.com")));
        LedgerJpa ledgerJpa = new LedgerJpa(new LedgerIdJpa("marta@gmail.com"), "2020-05-21");

        // Act
        Ledger ledgerFromJpa = LedgerDomainDataAssembler.toDomain(ledgerJpa);

        // Assert
        assertEquals(ledger, ledgerFromJpa);
    }

    @Test
    void toDomainFALSE() {

        // Arrange
        Ledger ledger = new Ledger(new PersonID(new Email("marta@gmail.com")));
        LedgerJpa ledgerJpa = new LedgerJpa(new LedgerIdJpa("switch@gmail.com"), "2020-05-21");

        // Act
        Ledger ledgerFromJpa = LedgerDomainDataAssembler.toDomain(ledgerJpa);

        // Assert
        assertNotEquals(ledger, ledgerFromJpa);
    }

    @Test
    void toDataTRUE() {

        // Arrange
        Ledger ledger = new Ledger(new PersonID(new Email("marta@gmail.com")));
        LedgerJpa ledgerJpa = new LedgerJpa(new LedgerIdJpa("marta@gmail.com"), new DateAndTime().yearMonthDayToString());

        // Act
        LedgerJpa ledgerFromDomain = LedgerDomainDataAssembler.toData(ledger);

        // Assert
        assertEquals(ledgerJpa, ledgerFromDomain);
    }

    @Test
    void toDataFALSE() {

        // Arrange
        Ledger ledger = new Ledger(new PersonID(new Email("marta@gmail.com")));
        LedgerJpa ledgerJpa = new LedgerJpa(new LedgerIdJpa("switch@gmail.com"), "2020-05-21");

        // Act
        LedgerJpa ledgerFromDomain = LedgerDomainDataAssembler.toData(ledger);

        // Assert
        assertNotEquals(ledgerJpa, ledgerFromDomain);
    }
}