package com.MotherSon.CRM.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.dto.CustomException;
import com.MotherSon.CRM.dto.ErrorResponse;
import com.MotherSon.CRM.dto.Response;
import com.MotherSon.CRM.models.Country;
import com.MotherSon.CRM.models.Customer;
import com.MotherSon.CRM.repository.CustomerRepository;
import com.MotherSon.CRM.security.services.CustomerService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/customer")
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	@GetMapping("getallCustomer")
	public ResponseEntity<?> getAllCustomerList(){
		return ResponseEntity.ok(customerRepository.findAll());
	}
	
	
	@GetMapping("/getall")
	public Page<Customer> getCustomer(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return customerService.getCustomer(page, size, sortDirection);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
		Optional<Customer> C = customerService.getCustomerById(id);
		return C.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}


    

//	@PostMapping("/create")
//	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
//        return customerService.saveCustomer(customer);
//    }
	
	// Create Code
		 @PostMapping("/create")
		    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		        try {
		            // Call the service method to create a customer
		            return customerService.createCustomer(customer);
	 
		        } catch (CustomException e) {
		            // Handle the custom exception and map it to ErrorResponse
		            ErrorResponse errorResponse = new ErrorResponse(
		                "FAILURE",
		                e.getMessage(),
		                e.getErrorCode()
		            );
	 
		            Response<ErrorResponse> response = new Response<>(
		                "FAILURE",
		                "Validation error",
		                errorResponse
		            );
	 
		            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	 
		        } catch (Exception e) {
		            // Handle unexpected exceptions
		            ErrorResponse errorResponse = new ErrorResponse(
		                "FAILURE",
		                "An unexpected error occurred: " + e.getMessage(),
		                "500"
		            );
	 
		            Response<ErrorResponse> response = new Response<>(
		                "FAILURE",
		                "Internal server error",
		                errorResponse
		            );
	 
		            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		    }
	 
	
	@PostMapping("/findcustomer")
	public ResponseEntity<?> checkCustomerExistence(@RequestBody Map<String, String> customerData) {
	    try {
	        String emailId = customerData.get("emailId");
	        String contactNo = customerData.get("contactNo");
	        
	        // Call the service method to check customer existence
	        return customerService.checkCustomerExistence(emailId, contactNo);
	    } catch (Exception e) {
	        ErrorResponse errorResponse = new ErrorResponse("FAILURE", "An unexpected error occurred: " + e.getMessage(), "500");
	        Response<ErrorResponse> response = new Response<>("FAILURE", "Internal server error", errorResponse);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
 
	
	
	
	
	// put method for update
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long id , @RequestBody Customer customer ){
		if(customer != null)
		{
			Customer c = new Customer();
			c.setId(id);
			c.setSalutation(customer.getSalutation());
			c.setfName(customer.getfName());
			c.setlName(customer.getlName());
			c.setEmailId(customer.getEmailId());
			c.setContactNo(customer.getContactNo());
			c.setMarritalStatus(customer.getMarritalStatus());
			c.setCustomerType(customer.getCustomerType());
			c.setLeadSource(customer.getLeadSource());
			c.setAdharNo(customer.getAdharNo());
			c.setPassportId(customer.getPassportId());
			c.setCreatedby(customer.getCreatedby());
			c.setModifiedby(customer.getModifiedby());
			c.setCreatedDate(customer.getCreatedDate());
			c.setIpaddress(customer.getIpaddress());
			c.setModifiedDate(customer.getModifiedDate());
			c.setStatus(customer.isStatus());
			c.setIsdelete(customer.isIsdelete());
			
			customerService.updateCustomer(c);
			return ResponseEntity.ok(c);
			
		}
		else
		{
			return ResponseEntity.notFound().build();
			
		}}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Customer> deleteCustomer(@PathVariable long id){
			
			try
			{
			customerService.findById(id);
			customerService.deletedById(id);
			return ResponseEntity.noContent().build();
			}
			finally
			{
				return ResponseEntity.notFound().build();
			}
		}}