package com.abapskill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abapskill.entities.Vendor;
import com.abapskill.services.VendorService;

@RestController
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	// Test url http://localhost:8080/vendor
	@RequestMapping("/vendor")
	public List<Vendor> getVendors(){
		return vendorService.getAllVendors();
	}
	
	// Test url http://localhost:8080/vendor/1
	@RequestMapping("/vendor/{vendorCode}")
	public Vendor getVendorById(@PathVariable("vendorCode") String code) {
		Optional<Vendor> vendorbyId = vendorService.getVendorById(code);
		
//		return vendorbyId.get();
		return vendorbyId.orElse(new Vendor());
	}
	
	// Testing URL http://localhost:8080/vendor/search?name=Sunil co
	@RequestMapping("/vendor/search")
	public List <Vendor> searchVendorByName(@RequestParam String name){
		return vendorService.searchVendorByName(name);
	}
	
	// Testing URL http://localhost:8080/vendor/lookup/Suni
	@RequestMapping("/vendor/lookup/{name}")
	public List <Vendor> getVendorByName(@PathVariable("name") String name){
		return vendorService.getVendorsByName(name);
	}
	
	
	@PostMapping("/vendor")
	public Vendor createVendor(@RequestBody Vendor myVendorBody) {
		return vendorService.createVendor(myVendorBody);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/vendor")
	public Vendor updateVendor(@RequestBody Vendor vendor) {
		return vendorService.updateVendor(vendor);
	}
	
	@DeleteMapping("/vendor/{vendorId}")
	public String delete(@PathVariable("vendorId") String vendorId) {
		return vendorService.deleteVendor(vendorId);
	}


	
}
