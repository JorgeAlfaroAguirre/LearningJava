package com.ltp.contacts.service;

// import java.util.List;
import java.util.stream.IntStream;

import com.ltp.contacts.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.contacts.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact getContactById(String id) {
        return contactRepository.getContact(findIndexById(id));
    }
    private int findIndexById(String id) {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow();
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    }
    // @Override
    // public void updateContact(String id, Contact contact) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    // }
    // @Override
    // public void deleteContact(String id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
    // }
    // @Override
    // public List<Contact> getContacts() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getContacts'");
    // }
}
