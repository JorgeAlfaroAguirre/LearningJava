package com.ltp.contacts.web;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import java.util.List;
//@Controller
@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    // @GetMapping("/contact/all")
    // public ResponseEntity<List<Contact>> getContacts() {
    //     List<Contact> contacts = contactService.getContacts();
    //     return new ResponseEntity<>(contacts, HttpStatus.OK);
    // }


    @GetMapping("/contact/{id}")
    //@ResponseBody
    public ResponseEntity<Contact> getContact(@PathVariable String id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> createContact(@RequestBody Contact contact){
        contactService.saveContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // @PutMapping("/contact/{id}")
    // public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact contact) {
    //     contactService.updateContact(id, contact);   
    //     return new ResponseEntity<Contact>(contactService.getContactById(id), HttpStatus.OK);
    // }


}
