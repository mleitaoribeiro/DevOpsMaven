package switch2019.project.controllers;

import switch2019.project.model.*;

import java.time.LocalDateTime;

public class MemberGetsBalanceInDateRangeController {

    /**
     * US018
     * As a group member, i want to to get the balance of group transactions given a specific date range
     */
    public double getGroupBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate, Group group1, Person member) throws IllegalArgumentException {
        if (!group1.isGroupMember(member) || member == null) {
            throw new IllegalArgumentException("The group member is not valid.");
        }
        return group1.getGroupBalanceInDateRange(initialDate, finalDate);
    }

}