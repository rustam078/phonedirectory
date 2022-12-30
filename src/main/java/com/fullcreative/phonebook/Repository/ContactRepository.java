package com.fullcreative.phonebook.Repository;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.fullcreative.phonebook.model.Contact;




public interface ContactRepository extends DatastoreRepository<Contact,Long> {


    // @Query(Select * from Contact where userid=10 AND contactid=(select contactid from ContactType where data='rustam@gmail.com'))
    // Contact searchdata();

    @Query("SELECT * FROM |com.fullcreative.phonebook.model.Contact| WHERE contact.userid=10 AND contact.contactid =contactmethod.contactid AND contactmethod.value= @act")
    public List<Contact> findByPhone(@Param("act") String value);

    public Page<Contact> findByUserloginemail(String useremail,Pageable pageable);
    
}
