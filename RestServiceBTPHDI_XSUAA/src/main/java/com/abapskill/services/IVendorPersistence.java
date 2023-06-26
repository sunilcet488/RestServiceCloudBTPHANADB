package com.abapskill.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abapskill.entities.Vendor;

public interface IVendorPersistence extends JpaRepository<Vendor,String> {
	
	List <Vendor> findByname(String name); 
	
	@Query(nativeQuery=true,value="SELECT * FROM public.vendor where name like %?1%" )
	List <Vendor> lookupByName(String name);
}
