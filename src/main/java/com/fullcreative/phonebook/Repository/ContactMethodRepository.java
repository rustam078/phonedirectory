package com.fullcreative.phonebook.Repository;


import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.fullcreative.phonebook.model.ContactMethod;



public interface ContactMethodRepository extends DatastoreRepository<ContactMethod,Long> {


  
    void deleteByContactid(Long id);

    List<ContactMethod> findByContactid(Long id);

    @Query("SELECT * FROM |com.fullcreative.phonebook.model.ContactMethod| WHERE  contactmethod.value= @act")
    public List<ContactMethod> findByQuery(@Param("act") String value);
    

}
