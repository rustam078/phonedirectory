package com.fullcreative.phonebook.model;


import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;


@Entity(name="contact")
public class Contact {

@Id
Long contactid;

private String userloginemail;
String name;

@Transient
List<ContactMethod> contact;








public Long getContactid() {
    return contactid;
}

public void setContactid(Long contactid) {
    this.contactid = contactid;
}



public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public List<ContactMethod> getContact() {
    return contact;
}

public void setContact(List<ContactMethod> contact) {
    this.contact = contact;
}




@Override
public String toString() {
    return "Contact [contactid=" + contactid + ", userloginemail=" + userloginemail + ", name=" + name + ", contact="
            + contact + "]";
}

public Contact() {
}




public Contact(Long contactid, String userloginemail, String name, List<ContactMethod> contact) {
    this.contactid = contactid;
    this.userloginemail = userloginemail;
    this.name = name;
    this.contact = contact;
}

public String getUserloginemail() {
    return userloginemail;
}

public void setUserloginemail(String userloginemail) {
    this.userloginemail = userloginemail;

    
}


}
