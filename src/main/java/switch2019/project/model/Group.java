package switch2019.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class Group {

    private String description;
    private LocalDate startingDate;
    private HashSet<Person> members;
    private HashSet<Person> admins;
    private AccountsList groupAccountsList;
    private CategoryList categoryList;
    private Ledger ledger;

    /**
     * Default Group constructor
     *
     * @param description
     */

    public Group(String description) {
        setDescription(description);
        startingDate = LocalDate.now();
        members = new HashSet<>();
        admins = new HashSet<>();
        groupAccountsList = new AccountsList();
        categoryList = new CategoryList();
        ledger= new Ledger();
    }

    /**
     * setter Description
     *
     * @param description
     */
    public void setDescription(String description) {
        if (description != null) {
            this.description = description.toUpperCase();
        }
    }

    /**
     * Override of equals for Group
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(description, group.description) &&
                Objects.equals(startingDate, group.startingDate) &&
                Objects.equals(members, group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, startingDate, members);
    }

    /**
     * Add a new person to a group
     *
     * @param person
     * @return true if member was added, false if it wasn't
     */
    public boolean addMember(Person person) {
        if (this.members.size() > 0 && person != null) {
            return members.add(person);
        } else if (person != null) {
            return setAdmin(person);
        } else
            return false;
    }

    /**
     * Remove one member from a group
     *
     * @param memberToRemove
     * @return true if member was removed, false if it wasn't
     */
    public boolean removeMember(Person memberToRemove) {
        if (memberToRemove != null) {
            if (admins.contains(memberToRemove) && members.contains(memberToRemove) && admins.size() > 1) {
                return admins.remove(memberToRemove) && members.remove(memberToRemove);
            } else if (!admins.contains(memberToRemove) && members.contains(memberToRemove)) {
                return members.remove(memberToRemove);
            } else
                return false;
        } else return false;
    }

    /**
     * Add multiple members to Group
     *
     * @param newMembers
     * @return true if multiple members were added, false if they weren't
     */
    public boolean addMultipleMembers(HashSet<Person> newMembers) {
        if (members.size() != 0) {
            for (Person member : newMembers) {
                addMember(member);
            }
            return this.members.containsAll(newMembers);
        } else
            throw new IllegalArgumentException("You cannot add an empty list of members or a non existing person. Please try again.");
    }

    /**
     * Remove multiple Members
     *
     * @param toRemove HashSet of members that are going to be removed
     * @return true if multiple members were removed, false if they weren't
     */
    public boolean removeMultipleMembers(HashSet<Person> toRemove) {
        for (Person member : toRemove) {
            removeMember(member);
        }
        return !this.members.containsAll(toRemove);

    }

    /**
     * Validate if a group is a family
     *
     * @return boolean
     */
    public boolean isFamily() {
        Person dadPerson = null;
        Person momPerson = null;
        for (Person person : members) {
            for (Person person2 : members) {
                if (!person.equals(person2)) {
                    if (person.isFather(person2))
                        dadPerson = person2;
                    else if (person.isMother(person2))
                        momPerson = person2;
                }
            }
        }
        if (dadPerson == null || momPerson == null) return false;

        for (Person person : members) {
            if (!person.equals(dadPerson) && !person.equals(momPerson)) {
                if (!person.isMother(momPerson) || !person.isFather(dadPerson)) return false;
            }
        }
        return true;
    }

    /**
     * Setter function to promote a person directly to group administrator
     *
     * @param person
     * @return true if person was promoted, false if it wasn't
     */
    public boolean setAdmin(Person person) {
        if (person != null && !this.admins.contains(person)) {
            this.members.add(person);
            this.admins.add(person);
            if (this.admins.contains(person) && this.members.contains(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * toString 
     *
     */
    @Override
    public String toString() {
        return "Group: " + description +
                ", created on " + startingDate + ".";

    }

    /**
     * Promote multiple members to group admins
     *
     * @param multipleMembers
     * @return true if person was promoted, false if it wasn't
     */
    public boolean promoteMultipleMemberToAdmin(HashSet<Person> multipleMembers) {
        for (Person member : multipleMembers) {
            setAdmin(member);
        }
        return admins.containsAll(multipleMembers);
    }

    /**
     * Demote group admin to group member only.
     *
     * @param member
     * @return true if group admin was demoted, false if it wasn't
     */
    public boolean demoteMemberFromAdmin(Person member) {
        if (this.members.contains(member) && this.admins.contains(member) && this.admins.size() >= 2) {
            return this.admins.remove(member);
        }
        return false;
    }

    /**
     * Demote multiple group admins to member only
     *
     * @param multipleMembers
     * @return true if all
     */

    public boolean demoteMultipleMembersFromAdmin(HashSet<Person> multipleMembers) {
        if (multipleMembers.size() >= this.admins.size()) {
            return false;
        }
        for (Person member : multipleMembers) {
            demoteMemberFromAdmin(member);
        }
        return members.containsAll(multipleMembers) && !admins.containsAll(multipleMembers);
    }

    /**
     * Validate if a person is a Group Admin
     *
     * @param isAdmin
     * @return
     */
    public boolean isGroupAdmin (Person isAdmin) {
        if (this.admins.contains(isAdmin) && isAdmin != null) {
            return true;
        }
        return false;
    }

    /**
     * Validate if a person is a Group Admin
     *
     * @param isMember
     * @return
     */
    public boolean isGroupMember (Person isMember) {
        return false;
    }


    /**
     * Add account to Group´s Account List
     *
     * @param accountDenomination
     * @param accountDescription
     * @return true if account was added to GroupAccountsList, false if it wasn't
     */
    public boolean addAccountToGroupAccountsList(String accountDenomination, String accountDescription) {
        return this.groupAccountsList.createAndAddAccountToAccountsList(accountDenomination, accountDescription);
    }


    /**
     * Develop method to create a new Account to the group: US7 - As a groupAdmin, I want to create a group account
     *
     * @param accountDenomination
     * @param accountDescription
     * @return true if group account was created, false if it wasn't
     */
    public boolean createGroupAccount(String accountDenomination, String accountDescription) {
        if (accountDenomination != null && accountDescription != null && this.description != null) {
            return this.addAccountToGroupAccountsList(accountDenomination, accountDescription);
        }
        return false;
    }

    /**
     * Develop method add one category to group's Category List
     *
     * @param nameOfCategory
     * @return true if category was added to group's Category List, false if it wasn't
     */

    public boolean createAndAddCategoryToCategoryList(String nameOfCategory) {
        if (nameOfCategory != null) {
            return categoryList.addCategoryToCategoryList(nameOfCategory);
        } else return false;
    }


    /**
     * Remove a category from CategoryList
     *
     * @param nameOfcategory
     */

    public boolean removeCategoryFromList(String nameOfcategory) {
        if (nameOfcategory == null) {
            return false;
        }
        return categoryList.removeCategoryFromList(nameOfcategory);
    }

    /**
     * Develop method to create a new group transaction (US008.1)
     *
     * @return
     */

    public boolean createGroupTransaction(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, boolean type) {
        return false;
    }

}


