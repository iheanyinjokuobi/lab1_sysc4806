package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AddressBookController {

    private final AddressBookRepository addressBookRepository;
    private final BuddyRepository buddyInfoRepository;

    public AddressBookController(
            AddressBookRepository addressBookRepository,
            BuddyRepository buddyInfoRepository) {

        this.addressBookRepository = addressBookRepository;
        this.buddyInfoRepository = buddyInfoRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("addressBooks", addressBookRepository.findAll());
        return "index";
    }

    @PostMapping("/create")
    public String createAddressBook() {

        AddressBook addressBook = new AddressBook();
        addressBookRepository.save(addressBook);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getAddressBook(@PathVariable Integer id,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        Optional<AddressBook> addressBook = addressBookRepository.findById(id);
        if (addressBook.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "error", "No such address book found");
            return "redirect:/";
        }
        model.addAttribute("addressBook", addressBook.get());
        return "addressbook";
    }

    @PostMapping("/{addressBookId}/buddies")
    public String addBuddy(
            @PathVariable Integer addressBookId,
            @RequestParam String name,
            @RequestParam String phoneNumber,
            @RequestParam String address,
            RedirectAttributes redirectAttributes) {

        Optional<AddressBook> addressBook = addressBookRepository.findById(addressBookId);
        if (addressBook.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "error", "No such address book found");
            return "redirect:/";
        }
        BuddyInfo buddy = new BuddyInfo(name, phoneNumber, address);
        buddyInfoRepository.save(buddy);
        addressBook.get().addBuddy(buddy);
        addressBookRepository.save(addressBook.get());
        return "redirect:/" + addressBookId;
    }
}
