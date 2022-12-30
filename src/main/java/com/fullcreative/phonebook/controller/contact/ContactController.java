package com.fullcreative.phonebook.controller.contact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fullcreative.phonebook.Repository.ContactRepository;
import com.fullcreative.phonebook.error.ContactNotFoundException;
import com.fullcreative.phonebook.Repository.ContactMethodRepository;

import com.fullcreative.phonebook.model.Contact;
import com.fullcreative.phonebook.model.ContactMethod;
import com.fullcreative.phonebook.service.ContactService;

@RestController
public class ContactController {

    private ContactMethodRepository contactMethodRepository;
    private ContactRepository contactRepository;
    private ContactService contactservice;

    public ContactController(ContactMethodRepository contactMethodRepository, ContactRepository contactRepository,
            ContactService contactservice) {
        this.contactMethodRepository = contactMethodRepository;
        this.contactRepository = contactRepository;
        this.contactservice = contactservice;
    }

    @GetMapping("/findcontact/{id}")
    private ResponseEntity<List<ContactMethod>> getContact(@PathVariable Long id) {
        return contactservice.getContact(id);

    }

    @CrossOrigin
    @GetMapping("/find/{id}")
    public ResponseEntity<Contact> customcontact(@PathVariable("id") Long id) {
        return contactservice.customcontact(id);
    }

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<?> saveContact(@RequestBody Contact contact) {
        System.out.println(contact);
        Long id = contactRepository.save(contact).getContactid();
        System.out.println(id);
        List<ContactMethod> contacttype = contact.getContact();
        if (contacttype != null) {
            contacttype.stream().forEach(emp -> {
                emp.setContactid(id);
            });
            contactMethodRepository.saveAll(contacttype);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findAll")
    public List<Contact> findAllContact() {
        Iterator<Contact> itr = contactRepository.findAll().iterator();
        List<Contact> allcontact = new ArrayList<>();
        while (itr.hasNext()) {
            Contact contact = itr.next();
            Long id = contact.getContactid();
            List<ContactMethod> phone = getContact(id).getBody();
            if (!phone.isEmpty())
                contact.setContact(phone);
            allcontact.add(contact);
        }
        return allcontact;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteByContactid(@PathVariable Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @CrossOrigin
    @PostMapping("/addcontact/{id}")
    public void addContact(@PathVariable Long id, @RequestBody ContactMethod contact) {
        System.out.println(contact);
        if (id != null) {
            contact.setContactid(id);
            contactMethodRepository.save(contact);
        }

    }

    @CrossOrigin
    @DeleteMapping("/deletecontact/{id}")
    public void deletephone(@PathVariable Long id) {
        contactMethodRepository.deleteByContactid(id);
    }

    @CrossOrigin
    @DeleteMapping("/deletebyid/{id}")
    public void deleteContactbyid(@PathVariable Long id) {
        contactMethodRepository.deleteById(id);
    }

    @CrossOrigin
    @DeleteMapping("/deleteidd/{id}")
    public void deleteContactid(@PathVariable Long id) {
        deletephone(id);
        contactRepository.deleteById(id);
    }

    @CrossOrigin
    @PutMapping("/updatebyid")
    public Contact updatecontactbyid(@RequestBody Contact contact) {
        System.out.println(contact);
        Long idd = contactRepository.save(contact).getContactid();
        System.out.println(idd);
        List<ContactMethod> contacttype = contact.getContact();
        if (contacttype != null) {
            contactMethodRepository.saveAll(contacttype);
        }
        return contact;

    }

    @CrossOrigin
    @GetMapping("/allcontact/{useremail}/{page}")
    public ResponseEntity<List<Contact>> getAllUserDetails(@PathVariable Integer page,
            @PathVariable("useremail") String useremail) {

        Pageable pageable = PageRequest.of(page, 6);
        return contactservice.getAllUserDetails(useremail, pageable);

    }

    @CrossOrigin
    @GetMapping("/getpn")
    public Integer getpagenumber() {
        return contactservice.pagenumber;
    }

    @CrossOrigin
    @GetMapping("/gettp")
    public Integer gettotalpage() {
        return contactservice.totalpage;
    }

    @GetMapping("/findbyquery/{id}")
    public List<ContactMethod> search(@PathVariable String id) {
        return contactMethodRepository.findByQuery(id);
    }

    
    @CrossOrigin
    @GetMapping("/search/{useremail}/{page}/{name}")
    public List<Contact> getUserDetails(@PathVariable String useremail, @PathVariable Integer page,
            @PathVariable("name") String name) throws ContactNotFoundException {
        List<Contact> list = new ArrayList<>();
        Iterator<Contact> itr = getAllUserDetails(page, useremail).getBody().iterator();
        while (itr.hasNext()) {
            Contact contact = itr.next();
            if (contact.getName().toLowerCase().indexOf(name.toLowerCase()) >= 0) {
                list.add(contact);
            }
        }
        if (list.isEmpty()) {
            throw new ContactNotFoundException("contact is not available");
        }
        return list;
    }

    
    @RequestMapping(value="/findbyquery",method = RequestMethod.GET)
    public String  test() {
        return "(id)";
    }


}