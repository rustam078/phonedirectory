package com.fullcreative.phonebook.Repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.fullcreative.phonebook.model.SignUp;


public interface SignupRepository extends DatastoreRepository<SignUp,String>{

  
    
}
