package com.wipro.ContactManager2

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    // Static list to store contacts
    private static List<Contact> contactList = new ArrayList<>();
    private static int idCounter = 1;

    // Add contact
    public static boolean addContact(Contact c) {
        try {
            c.setId(idCounter++);
            contactList.add(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get all contacts
    public static List<Contact> getAllContacts() {
        return contactList;
    }

    // Get contact by id
    public static Contact getContactById(int id) {
        for (Contact c : contactList) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    // Update contact
    public static boolean updateContact(Contact updated) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId() == updated.getId()) {
                contactList.set(i, updated);
                return true;
            }
        }
        return false;
    }

    // Delete contact
    public static boolean deleteContact(int id) {
        return contactList.removeIf(c -> c.getId() == id);
    }
}