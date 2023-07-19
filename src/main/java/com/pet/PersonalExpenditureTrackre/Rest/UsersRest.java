package com.pet.PersonalExpenditureTrackre.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.PersonalExpenditureTrackre.entities.Users;
import com.pet.PersonalExpenditureTrackre.repositories.UsersRepo;

@RestController
public class UsersRest {
	
	@Autowired
	UsersRepo urepo;
	
	@GetMapping("/allusers")
	public List<Users> getAllUsers() {
		return urepo.findAll();
	}

}
