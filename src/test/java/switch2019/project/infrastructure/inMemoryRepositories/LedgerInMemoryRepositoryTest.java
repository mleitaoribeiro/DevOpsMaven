package switch2019.project.infrastructure.inMemoryRepositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.LedgerID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class LedgerInMemoryRepositoryTest {

    @Test
    @DisplayName("Test if Ledger was created - Happy Case")
    public void createLedger() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        Ledger ledgerExpected = new Ledger(ownerID);

        // Act
        Ledger ledger = ledgerInMemoryRepository.createLedger(ownerID);
        boolean result = ledger.equals(ledgerExpected);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if Ledger was created - Exception")
    public void createLedgerException() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));

        ledgerInMemoryRepository.createLedger(ownerID);

        // Act
        try {
            ledgerInMemoryRepository.createLedger(ownerID);
        }

        // Assert
        catch (ResourceAlreadyExistsException exception) {
            assertEquals("This ledger already exists.", exception.getMessage());
        }
    }

    @Test
    @DisplayName("Get Ledger by ID")
    public void getByID() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        OwnerID ownerID2 = new PersonID(new Email("mm@gmail.com"));
        ledgerInMemoryRepository.createLedger(ownerID);
        ledgerInMemoryRepository.createLedger(ownerID2);

        Ledger ledgerExpected = new Ledger(ownerID);

        // Act
        Ledger ledger = ledgerInMemoryRepository.getByID(new LedgerID(ownerID));

        //Assert
        assertEquals(ledgerExpected, ledger);

    }

    @Test
    @DisplayName("Get Ledger by ID - Exception")
    public void getByIDException() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        ledgerInMemoryRepository.createLedger(ownerID);

        //Act
        Throwable thrown = catchThrowable(() -> {
            ledgerInMemoryRepository.getByID(new LedgerID(new GroupID(new Description("NOT FOUND"))));
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No ledger found with that ID.");

    }

    @Test
    @DisplayName("Check if a LedgerID is in the Repository - True")
    public void isIDOnRepositoryTrue() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));
        ledgerInMemoryRepository.createLedger(ownerID);

        // Act
        boolean ledger = ledgerInMemoryRepository.isIDOnRepository(new LedgerID(ownerID));

        // Assert
        assertTrue(ledger);
    }

    @Test
    @DisplayName("Check if a LedgerID is in the Repository - False")
    public void isIDOnRepositoryFalse() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();
        OwnerID ownerID = new GroupID(new Description("switch"));

        // Act
        boolean ledger = ledgerInMemoryRepository.isIDOnRepository(new LedgerID(ownerID));

        // Assert
        assertFalse(ledger);
    }

    @Test
    @DisplayName("Get LedgerRepository size")
    public void repositorySize() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        OwnerID ownerID1 = new GroupID(new Description("isep"));
        ledgerInMemoryRepository.createLedger(ownerID1);

        OwnerID ownerID2 = new GroupID(new Description("switch"));
        ledgerInMemoryRepository.createLedger(ownerID2);

        OwnerID ownerID3 = new GroupID(new Description("bashtards"));
        ledgerInMemoryRepository.createLedger(ownerID3);

        // Act
        long ledgerSize = ledgerInMemoryRepository.repositorySize();

        // Assert
        assertEquals(3, ledgerSize);
    }

    @Test
    @DisplayName("Get LedgerRepository size - Empty")
    public void repositorySizeEmpty() {

        // Arrange
        LedgerInMemoryRepository ledgerInMemoryRepository = new LedgerInMemoryRepository();

        // Act
        long ledgerSize = ledgerInMemoryRepository.repositorySize();

        // Assert
        assertEquals(0, ledgerSize);
    }
}