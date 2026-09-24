package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    private List<BuddyInfo> buddies;
    private int count;
    @Id
    private Integer id;

    public AddressBook() {
        this.buddies = new ArrayList<>();
        this.count = 0;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        BuddyInfo buddy1 = new BuddyInfo("John", "12345678");
        BuddyInfo buddy2 = new BuddyInfo("Ada", "23456789");
        BuddyInfo buddy3 = new BuddyInfo("Efe", "34567890");

        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);

        addressBook.printBuddies();

        System.out.println("Running BuddyManager...\n\n");

        BuddyManager buddyManager = new BuddyManager();

        buddyManager.performJPA();
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void addBuddy(BuddyInfo buddy) {
        this.buddies.add(buddy);
        count += 1;
    }

    public int getCount() {
        return count;
    }

    public void printBuddies() {
        for (BuddyInfo buddy : buddies) {
            System.out.println("Buddy name is " + buddy.getName() + " and buddy phone number is " + buddy.getPhoneNumber());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
