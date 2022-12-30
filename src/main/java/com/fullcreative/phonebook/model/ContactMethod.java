package com.fullcreative.phonebook.model;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;


@Entity(name="contactmethod")
public class ContactMethod {
@Id 
@Field(name = "aid")   
Long id;
Long contactid;
String type;
String subtype;
String value;



public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public Long getContactid() {
    return contactid;
}
public void setContactid(Long contactid) {
    this.contactid = contactid;
}
public String getType() {
    return type;
}
public void setType(String type) {
    this.type = type;
}

public String getSubtype() {
    return subtype;
}
public void setSubtype(String subtype) {
    this.subtype = subtype;
}
public String getValue() {
    return value;
}
public void setValue(String value) {
    this.value = value;
}
@Override
public String toString() {
    return "ContactMethod [id=" + id + ", contactid=" + contactid + ", type=" + type + ", subtype=" + subtype
            + ", value=" + value + "]";
}



}
