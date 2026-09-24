package org.example;

import java.util.List;

import jakarta.persistence.*;

public class BuddyManager {

    public void performJPA() {

        // Creating objects representing some buddies
        BuddyInfo buddy1 = new BuddyInfo();
        buddy1.setPhoneNumber("123456789");
        buddy1.setName("Tennis racket");
        buddy1.setId(1);

        BuddyInfo buddy2 = new BuddyInfo();
        buddy2.setPhoneNumber("234567890");
        buddy2.setName("Guitar");
        buddy2.setId(2);

        AddressBook addressBook = new AddressBook();
        addressBook.setId(1);
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buddy-test");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the buddy entity objects
        em.persist(buddy1);
        em.persist(buddy2);
        em.persist(addressBook);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT p FROM BuddyInfo p");
        Query q2 = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        @SuppressWarnings("unchecked")
        List<AddressBook> addressBookResults = q2.getResultList();

        System.out.println("List of buddies\n----------------");

        for (BuddyInfo p : results) {

            System.out.println(p.getName() + " (phone number=" + p.getPhoneNumber() +  " and id=" + p.getId() + ")");
        }

        System.out.println("List of address books\n----------------");

        for (AddressBook a : addressBookResults) {
            System.out.println("This is address book " + a.getId());
            for (BuddyInfo p : a.getBuddies()) {
                System.out.println(p.getName() + " (phone number=" + p.getPhoneNumber() +  " and id=" + p.getId() + ")");
            }
        }

        // Closing connection
        em.close();

        emf.close();
    }
}