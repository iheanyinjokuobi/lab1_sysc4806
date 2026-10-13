package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @OneToMany(fetch = FetchType.EAGER)
    private List<BuddyInfo> buddies;
    private int count;
    @Id
    @GeneratedValue
    private Integer id;

    public AddressBook() {
        this.buddies = new ArrayList<>();
        this.count = 0;
    }

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
