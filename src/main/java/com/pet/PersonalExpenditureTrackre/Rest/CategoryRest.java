package com.pet.PersonalExpenditureTrackre.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.PersonalExpenditureTrackre.entities.Category;
import com.pet.PersonalExpenditureTrackre.repositories.CategoryRepo;

@RestController
public class CategoryRest {
	
	@Autowired
	CategoryRepo crepo;
	
	@GetMapping("/allcategories")
	public List<Category> getAllCategory(){
		return crepo.findAll();
	}
	
	
}
