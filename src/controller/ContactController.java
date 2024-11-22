package controller;

import model.Contact;
import repository.ContactRepository;

import java.util.List;

public class ContactController {
    ContactRepository contactRepository = new ContactRepository();

    public List<Contact> getAll() {
        List<Contact> contacts = contactRepository.getAll();
        return contacts;
    }

    public void add(Contact contact) {
        contactRepository.add(contact);
    }

    public boolean isExist(int phone) {
        return contactRepository.findByPhone(phone) != null;
    }

    public Contact findByPhone(int phone) {
        return contactRepository.findByPhone(phone);
    }

    public void deleteByPhone(int phone) {
        contactRepository.deleteByPhone(phone);
    }

    public void update(Contact contact) {
        contactRepository.update(contact);
    }

    public Contact findByName(String name) {
        return (Contact) contactRepository.findByName(name);
    }
}
