package org.example;

import static org.junit.jupiter.api.Assertions.*;

class BuddyInfoTest {
    BuddyInfo buddy;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        buddy = new BuddyInfo("Iheanyi", "12345678");
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Iheanyi", buddy.getName());
    }

    @org.junit.jupiter.api.Test
    void getPhoneNumber() {
        assertEquals("12345678", buddy.getPhoneNumber());
    }

    @org.junit.jupiter.api.Test
    void setName() {
        buddy.setName("Dara");
        assertEquals("Dara", buddy.getName());
    }

    @org.junit.jupiter.api.Test
    void setPhoneNumber() {
        buddy.setPhoneNumber("987654321");
        assertEquals("987654321", buddy.getPhoneNumber());
    }
}