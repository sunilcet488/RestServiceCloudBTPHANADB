package com.abapskill.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.abapskill.entities.Vendor;

// limitation of this rest service using spring starter data rest is that it can not do post operation with association
// this is only used for the get operations
// collectionResourceRel = "vendor" -- database table name
@RepositoryRestResource(collectionResourceRel = "vendor",path="newVendor")
public interface VendorControllerNew extends JpaRepository<Vendor, String>{
	
}
