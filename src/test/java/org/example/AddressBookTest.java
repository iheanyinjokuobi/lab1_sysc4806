package org.example;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {
    AddressBook addressBook;
    BuddyInfo buddy1;
    BuddyInfo buddy2;
    BuddyInfo buddy3;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        addressBook = new AddressBook();

        buddy1 = new BuddyInfo("John", "12345678");
        buddy2 = new BuddyInfo("Ada", "23456789");
        buddy3 = new BuddyInfo("Ada", "34567890");

        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);
    }

    @org.junit.jupiter.api.Test
    void getBuddies() {
        // the addressBook contains all the 3 buddies
        assertTrue(addressBook.getBuddies().contains(buddy1));
        assertTrue(addressBook.getBuddies().contains(buddy2));
        assertTrue(addressBook.getBuddies().contains(buddy3));
    }

    @org.junit.jupiter.api.Test
    void addBuddy() {
        // the addressBook contains exactly 3 buddies
        assertEquals(3, addressBook.getCount());
    }
}