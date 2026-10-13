package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buddies")
public class BuddyInfoController {

    private final BuddyRepository repository;

    public BuddyInfoController(BuddyRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public BuddyInfo createBuddy(
            @RequestBody BuddyInfo buddy) {

        return repository.save(buddy);
    }

    @GetMapping("/{id}")
    public BuddyInfo getBuddy(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

}