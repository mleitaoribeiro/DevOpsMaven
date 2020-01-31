package switch2019.project.controllers;

import switch2019.project.model.*;

import java.time.LocalDateTime;

public class MemberGetsBalanceInDateRangeController {

    /**
     * US018
     * As a group member, i want to to get the balance of group transactions given a specific date range
     *
     * @param initialDate
     * @param finalDate
     * @param group1
     * @param member
     * @return balance in date range
     */
    public double getGroupBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate, Group group1, Person member) {

        //verify if person is member of the group
        if (!group1.isGroupMember(member)) {
            throw new IllegalArgumentException("The group member is not valid.");
        }

        //balance in Date Range
        return group1.getGroupBalanceInDateRange(initialDate, finalDate);
    }

}
