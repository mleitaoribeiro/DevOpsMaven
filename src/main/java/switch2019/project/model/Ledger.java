package switch2019.project.model;

import java.util.HashSet;

public class Ledger {
    //Private Ledger variables
    private HashSet<Transaction> ledgerList;

    /**
     * Overload Ledger Construtor
     *
     *
     */

    public Ledger (){
        ledgerList=new HashSet<Transaction>();
    }
}
