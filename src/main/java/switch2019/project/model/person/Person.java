package switch2019.project.model.person;

import switch2019.project.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Person {
    // Private Person variables
    private String name;
    private LocalDate birthDate; // year[¨], month [0-12], day[0-31] && Birth Date =< now()
    private Set<Person> siblingList;
    private Person mother;
    private Person father;
    private Address address;
    private Address birthPlace;
    private CategoryList categoryList;
    private AccountsList accountsList;
    private Ledger ledger;
    private ScheduledTasksList scheduledTasksList;

    /**
     * Default Person constructor
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     */

    public Person(String name, LocalDate birthDate, Address birthPlace, Address homeAddress) {
        this.name = name;
        this.birthPlace = birthPlace;
        setBirthDate(birthDate);
        siblingList = new HashSet<>();
        categoryList = new CategoryList();
        accountsList = new AccountsList();
        ledger = new Ledger();
        address = homeAddress;
        scheduledTasksList = new ScheduledTasksList();
    }

    /**
     * Overload Person constructor
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     * @param mother
     * @param father
     */

    public Person(String name, LocalDate birthDate, Address birthPlace, Address homeAddress, Person mother, Person father) {
        this.name = name;
        setBirthDate(birthDate);
        this.birthPlace = birthPlace;
        address = homeAddress;
        this.mother = mother;
        this.father = father;
        this.siblingList = new HashSet<>();
        categoryList = new CategoryList();
        accountsList = new AccountsList();
        ledger = new Ledger();
        scheduledTasksList = new ScheduledTasksList();
    }

    /**
     * override of equals for Person Instance and @override hashcode
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(birthPlace, person.birthPlace) &&
                Objects.equals(address, person.address) &&
                Objects.equals(father, person.father) &&
                Objects.equals(mother, person.mother);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, birthPlace, address);
    }

    @Override
    public String toString() {
        return "Person: " + name + ", currently lives in " + address.homeAddressToString() +
                ", was born in " + birthPlace.birthplaceToString() +
                ", on " + birthDate +
                ".";
    }

    /**
     * Set Person Birth Date: with input validation
     *
     * @param birthDate
     */

    public void setBirthDate(LocalDate birthDate) {
        if(birthDate == null)
            throw new IllegalArgumentException(("Birth Date Can't be Null."));
        else if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth Date Not Supported.");
        }
        else this.birthDate = birthDate;
    }

    /**
     * Get BirthDate
     *
     * @return birthDate
     */

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Set Person Name: No input Validation
     *
     * @param newName
     */

    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Get Person Name
     *
     * @return Person's name
     */

    public String getName() {
        return this.name;
    }

    /**
     * Add a new Sibling to siblingList
     *
     * @param newSibling
     */

    public void addSibling(Person newSibling) {
        siblingList.add(newSibling);
        newSibling.siblingList.add(this);
    }

    /**
     * Add Multiple Siblings
     *
     * @param newSiblings
     */

    public void addMultipleSiblings(Set<Person> newSiblings) {
        for (Person person : newSiblings) {
            addSibling(person);
        }
    }

    /**
     * Remove a Sibling
     *
     * @param sibling
     */
    public void removeSibling(Person sibling) {
        siblingList.remove(sibling);
        sibling.siblingList.remove(this);
    }

    /**
     * Remove multiple Siblings
     *
     * @param toRemove HashSet of siblings that are going to be removed.
     */
    public void removeMultipleSibling(Set<Person> toRemove) {
        for (Person person : toRemove) {
            removeSibling(person);
        }
    }

    /**
     * Getter function for the sibling's list
     *
     * @return siblingList
     */
    public Set<Person> getSiblingList() {
        return new HashSet<>(siblingList);
    }

    /**
     * Set Mother
     *
     * @param mother new mother Person
     */
    public void setMother(Person mother) {
        this.mother = mother;
    }

    /**
     * Validate if a person is the Mother of another person
     *
     * @param mother Person to validate if it's the mother
     * @return boolean
     */
    public boolean isMother(Person mother) {
        if (this.mother == null) return false;
        else return this.mother.equals(mother);
    }

    /**
     * Set Father
     *
     * @param father new father Person
     */

    public void setFather(Person father) {
        this.father = father;
    }

    /**
     * Validate if a person is the Father of another person
     *
     * @param father Person to validate if it's the father
     * @return boolean
     */

    public boolean isFather(Person father) {
        if (this.father == null || father == null) return false;
        else return this.father.equals(father);
    }

    /**
     * Method used to compare 2 sibling Lists to check if they are equal(True) or different(False)
     *
     * @param otherPerson
     */
    public boolean checkSameSiblings(Person otherPerson) {
        Set<Person> list1 = this.getSiblingList();
        Set<Person> list2 = otherPerson.getSiblingList();
        list1.remove(otherPerson);
        list2.remove(this);
        return list1.equals(list2);
    }

    /**
     * Develop a method to check if two people have the Same Mother
     *
     * @param otherPerson Person to validate if has the same mother
     * @return boolean
     */
    public boolean checkSameMother(Person otherPerson) {
        if (mother == null || otherPerson.mother == null) {
            return false;
        } else return mother.equals(otherPerson.mother);
    }


    /**
     * Develop a method to check if two people have the Same Father
     *
     * @param otherPerson to validate if has the same father
     */
    public boolean checkSameFather(Person otherPerson) {
        if (father == null || otherPerson.father == null) {
            return false;
        } else return father.equals(otherPerson.father);
    }


    /**
     * Person exists on the other Person siblings list (USER STORIES)
     *
     * @return boolean
     */
    public boolean personExistsOnSiblingsList(Person otherPerson) {
        return siblingList.contains(otherPerson);
    }


    /**
     * Develop method to check if two individuals are siblings (USER STORIES)
     *
     * @return boolean
     */

    public boolean isSibling(Person otherPerson) {
        return (personExistsOnSiblingsList(otherPerson) ||
                checkSameFather(otherPerson) || checkSameMother(otherPerson));
    }

    /**
     * Develop method to create a new transaction (USER STORY)
     *
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public boolean createTransaction(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, boolean type) {
        if (!accountFrom.equals(accountTo))
            return ledger.addTransactionToLedger(amount, description, localDate, category, accountFrom, accountTo, type);
        else return false;
    }

    /**
     * Develop method to create a new Account: US6 - As a user, I want to create an account for myself.
     *
     * @param accountDenomination
     * @param accountDescription
     */
    public boolean createAccount(String accountDenomination, String accountDescription) {
        return accountsList.createAndAddAccountToAccountsList(accountDenomination, accountDescription);
    }

    /**
     * Develop method to create a new Category USERSTORY5: As a user, I want to add a category to my category list.
     *
     * @param nameOfCategory
     */

    public boolean createCategoryAndAddToCategoryList(String nameOfCategory) {
        return categoryList.addCategoryToCategoryList(nameOfCategory);
    }

    /**
     * Remove a category from CategoryList
     *
     * @param nameOfcategory
     */
    public boolean removeCategoryFromList(String nameOfcategory) {
        return categoryList.removeCategoryFromList(nameOfcategory);
    }

    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */
    public boolean createAndAddMultipleCategoriesToList(Set<String> categories) {
        return categoryList.addMultipleCategoriesToList(categories);
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */
    public boolean removeMultipleCategoriesToList(Set<String> categories) {
        return categoryList.removeMultipleCategoriesToList(categories);
    }

    /**
     * Method to get the numbers of Categories in the Category List
     */
    public int numberOfCategoryInTheCategoryList() {
        return this.categoryList.numberOfCategoryInTheCategoryList();
    }

    /**
     * Get the balance of the transactions of one Person given a specific date range(US0017)
     *
     * @param initialDate
     * @param finalDate
     */

    public double getPersonalBalanceInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {
        return ledger.getBalanceInDateRange(initialDate, finalDate);
    }


    /**
     * Get the person's ledger transactions in a given period (US011)
     *
     * @param initialDate
     * @param finalDate
     */

    public List<Transaction> returnPersonLedgerInDateRange(LocalDateTime initialDate, LocalDateTime finalDate) {
        return ledger.getTransactionsInDateRange(initialDate, finalDate);
    }

    /**
     * Develop method to create a new schedule (USER STORY)
     *
     * @param periodicity
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public boolean scheduleNewTransaction(String periodicity, MonetaryValue amount, String description, LocalDateTime date,
                                          Category category, Account accountFrom, Account accountTo, boolean type) {
        return scheduledTasksList.addNewSchedule(this, periodicity, amount, description, date,
                category, accountFrom, accountTo, type);
    }

    /**
     * Develop method to return the ledger size
     * @return ledger size
     */
    public int ledgerSize() {
        return ledger.getLedgerSize();
    }


    /**
     * Get the user ledger transactions in a given period from specific account (US010.2)
     *
     * @param account1
     * @param initialDate
     * @param finalDate
     */
    public List<Transaction> getOneAccountTransactionsFromUser(Account account1, LocalDateTime initialDate, LocalDateTime finalDate) {
        List<Transaction> listOfTransactionsFromPeriod = this.ledger.getTransactionsInDateRange(initialDate, finalDate);
        return this.ledger.getTransactionsFromOneAccount(account1, listOfTransactionsFromPeriod);
    }
}


