package com.MotherSon.CRM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Itinerarys;
import com.MotherSon.CRM.models.Mealspackage;
import com.MotherSon.CRM.security.services.MealspackageService;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("Motherson/crm/v1/mealspackage")
public class MealspackageController {
	
	
	@Autowired
	private MealspackageService mealspackageService;
	
	
//	@GetMapping("/getall")
//	public List<Mealspackage> getAllMealspackage(){
//		return mealspackageService.getAllMealspackage();
//		
//	}
	
	
	@GetMapping("/getAll")
	public Page<Mealspackage> getMealspackage(
			@RequestParam(value = "page" , defaultValue = "0") int page,
			@RequestParam(value = "size" , defaultValue = "10") int size,
			@RequestParam(value = "sortDirection" , defaultValue = "asc") String sortDirection
			){
		return mealspackageService.getMealspackage(page , size , sortDirection);
	}
	
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Mealspackage> getMealspackageById(@PathVariable Long id) {
		Optional<Mealspackage> mealsPackage = mealspackageService.getMealspackageById(id);
		return mealsPackage.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
		
		
		
	
	
	@PostMapping("/create")
	public Mealspackage addMealspackage(@RequestBody Mealspackage mealspackage) {
		return this.mealspackageService.addMealspackage(mealspackage);
		
		
	}

}
