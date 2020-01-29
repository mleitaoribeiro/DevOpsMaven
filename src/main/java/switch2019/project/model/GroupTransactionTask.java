package switch2019.project.model;

import java.time.LocalDateTime;
import java.util.TimerTask;

public class GroupTransactionTask extends TimerTask {
    // Private Task attributes
    private Group group;
    private MonetaryValue amount;
    private String description;
    private LocalDateTime date;
    private Category category;
    private Account accountFrom;
    private Account accountTo;
    private boolean type;

    /**
     * PersonalTransactionTask Constructor
     */
    public GroupTransactionTask(Group group, MonetaryValue amount, String description, LocalDateTime date,
                                   Category category, Account accountFrom, Account accountTo, boolean type) {
        this.group = group;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    public void run() {
        group.createGroupTransaction(amount, description, date, category, accountFrom, accountTo, type);
    }
}
