package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addressbooks")
public class AddressBookRestController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyRepository buddyInfoRepository;

    public AddressBookRestController(
            AddressBookRepository addressBookRepository,
            BuddyRepository buddyInfoRepository) {

        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @PostMapping
    public AddressBook createAddressBook(
            @RequestBody AddressBook addressBook) {

        return addressBookRepository.save(addressBook);
    }

    @GetMapping("/{id}")
    public AddressBook getAddressBook(
            @PathVariable Integer id, Model model) {
        AddressBook addressBook = addressBookRepository.findById(id).orElseThrow();
        model.addAttribute("buddies", addressBook.getBuddies());
        return addressBook;
    }

    @PostMapping("/{addressBookId}/buddies/{buddyId}")
    public AddressBook addBuddy(
            @PathVariable Integer addressBookId,
            @PathVariable Integer buddyId) {

        AddressBook addressBook =
                addressBookRepository.findById(addressBookId)
                        .orElseThrow();

        BuddyInfo buddy =
                buddyInfoRepository.findById(buddyId)
                        .orElseThrow();

        addressBook.addBuddy(buddy);

        return addressBookRepository.save(addressBook);
    }
    }

