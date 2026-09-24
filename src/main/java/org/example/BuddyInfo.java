package org.example;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo {
    private String name;

    @Id
    private Integer id;

    private String phoneNumber;

    public BuddyInfo(){}

    public BuddyInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
