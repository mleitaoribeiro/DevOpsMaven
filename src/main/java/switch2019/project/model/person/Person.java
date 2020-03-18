package switch2019.project.model.person;

import switch2019.project.model.account.Account;
import switch2019.project.model.category.Category;
import switch2019.project.model.frameworks.Owner;
import switch2019.project.model.frameworks.OwnerID;
import switch2019.project.model.ledger.*;
import switch2019.project.model.shared.*;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Person implements Owner {

    // Private Person variables
    private PersonID personID;
    private PersonName name;
    private DateAndTime birthDate; // year[¨], month [0-12], day[0-31] && Birth Date =< now()
    private Set<Person> siblingList;
    private Person mother;
    private Person father;
    private Address address;
    private Address birthPlace;
    private AccountRepository accountsList;
    private CategoryRepository categoryList;
    private Ledger ledger;

    /**
     * Default Person constructor
     *
     * @param name
     * @param birthDate
     * @param birthPlace
     * @param homeAddress
     */

    public Person(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        siblingList = new HashSet<>();
        categoryList = new CategoryRepository();
        accountsList = new AccountRepository();
        ledger = new Ledger();
        address = homeAddress;
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

    public Person(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Person mother, Person father,
                  Email email) {
        personID = new PersonID(email);
        this.name = new PersonName(name);
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        address = homeAddress;
        this.mother = mother;
        this.father = father;
        this.siblingList = new HashSet<>();
        categoryList = new CategoryRepository();
        accountsList = new AccountRepository();
        ledger = new Ledger();
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
        return Objects.equals(personID, person.personID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(personID);
    }

    @Override
    public String toString() {
        return "Email: " + personID.getEmail() + ", Person "  + name.getPersonName() + ", currently lives in " + address.toString() +
                ", was born in " + birthPlace.getBirthPlace() + ", on " + birthDate.getYearMonthDay() + ".";
    }

    /**
     * Method to get Person ID
     * @return personID
     */

    public PersonID getID() {
        return personID;
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

    public boolean createTransaction(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, Type type) {
        if (!accountFrom.equals(accountTo))
            return ledger.addTransactionToLedger(amount, description, localDate, category, accountFrom, accountTo, type);
        else return false;
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

    public boolean scheduleNewTransaction(Periodicity periodicity, MonetaryValue amount, String description, LocalDateTime date,
                                          Category category, Account accountFrom, Account accountTo, Type type) {
        return ledger.scheduleNewTransaction(periodicity, amount, description, date, category, accountFrom, accountTo, type);
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


