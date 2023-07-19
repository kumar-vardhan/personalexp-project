package com.pet.PersonalExpenditureTrackre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.PersonalExpenditureTrackre.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{

}
