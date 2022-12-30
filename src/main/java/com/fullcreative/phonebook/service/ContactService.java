package com.fullcreative.phonebook.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fullcreative.phonebook.Repository.ContactMethodRepository;
import com.fullcreative.phonebook.Repository.ContactRepository;
import com.fullcreative.phonebook.model.Contact;
import com.fullcreative.phonebook.model.ContactMethod;

@Service
public class ContactService {

    public Integer pagenumber = 0;
    public Integer totalpage = 1;
    private ContactRepository contactRepository;
    private ContactMethodRepository contactMethodRepository;

    public ContactService(ContactRepository contactRepository, ContactMethodRepository contactMethodRepository) {
        this.contactRepository = contactRepository;
        this.contactMethodRepository = contactMethodRepository;
    }

    public void saveContact(Contact entity) {
        contactRepository.save(entity);
    }

    private List<ContactMethod> getPhones(Long id) {
        List<ContactMethod> contactmethod = contactMethodRepository.findByContactid(id);
        if (!contactmethod.isEmpty()) {
            return contactmethod;
        } else {
            return null;
        }
    }

    public ResponseEntity<List<Contact>> getAllUserDetails(String useremail, Pageable pageable) {
        Page<Contact> contact = contactRepository.findByUserloginemail(useremail, pageable);
        pagenumber = pageable.getPageNumber();
        totalpage = contact.getTotalPages();
        System.out.println(pageable.getPageNumber());
        System.out.println(contact.getTotalPages());
        List<Contact> al = new ArrayList<>();
        if (!contact.isEmpty()) {
            Iterator<Contact> itr = contact.iterator();
            while (itr.hasNext()) {
                Long id = itr.next().getContactid();
                Contact cont = contactRepository.findById(id).get();
                List<ContactMethod> phone = getPhones(id);
                cont.setContact(phone);
                al.add(cont);
            }
            return new ResponseEntity<List<Contact>>(al, HttpStatus.OK);
        } else {
            System.out.println("id not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Contact> customcontact(Long id) {

        Optional<Contact> getContactbyid = contactRepository.findById(id);
        if (getContactbyid.isPresent()) {
            Contact cont = getContactbyid.get();
            List<ContactMethod> contact = getContact(id).getBody();
            if (!contact.isEmpty())
                cont.setContact(contact);
            return new ResponseEntity<>(cont, HttpStatus.OK);
        } else {
            System.out.println("id not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<ContactMethod>> getContact(Long id) {
        List<ContactMethod> contactmethod = contactMethodRepository.findByContactid(id);
        if (!contactmethod.isEmpty()) {
            return new ResponseEntity<>(contactmethod, HttpStatus.OK);
        } else {
            System.out.println("id not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
