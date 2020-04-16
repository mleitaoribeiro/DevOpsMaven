package switch2019.project.infrastructure.repositories;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import static org.junit.jupiter.api.Assertions.*;

class LedgerRepositoryTest {

    @Test
    public void getById(){
        LedgerRepository ledgerRepository = new LedgerRepository();

        Ledger ledger = new Ledger();

        Ledger ledger1 = ledgerRepository.getByID(ledger);

        assertEquals(null, ledger1);
    }

    @Test
    public void isIDOnRepository(){
        LedgerRepository ledgerRepository = new LedgerRepository();

        Ledger ledger = new Ledger();

        boolean ledger1 = ledgerRepository.isIDOnRepository(ledger);

        assertFalse(ledger1);
    }

    @Test
    public void repositorySize(){
        LedgerRepository ledgerRepository = new LedgerRepository();


        int ledger1 = ledgerRepository.repositorySize();

        assertEquals(0, ledger1);
    }


}