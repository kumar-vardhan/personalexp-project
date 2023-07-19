package com.pet.PersonalExpenditureTrackre.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pet.PersonalExpenditureTrackre.entities.Expenditure;

public interface ExpenditureRepo extends JpaRepository<Expenditure, Integer>{
	
	@Query("select sum(e.amount),e.category.catCode from Expenditure e where month(spentOn)=:month group by e.category.catCode")
	List<String> totalAmount(@Param("month") int month);
	
	List<Expenditure> findByTagsContaining(String tags);

	Page<Expenditure> findByUser_UserNameAndCategory_CatCode(String string,String cat, PageRequest of);

	Page<Expenditure> findByUser_UserNameAndPayment_Names(String string,String mode, PageRequest of);
	
	Page<Expenditure> findBySpentOnBetween(Date spentOn, Date spentOn2, PageRequest of);

	List<Expenditure> findTop2ByUser_UserNameOrderByAmountDesc(String userName);
	//List<Expenditure> findTop2ByAmount();
	
//	@Query("SELECT e from  Expenditure e where e.user.userName=:name AND e.date BETWEEN :min and :max")
//	 List<Expenditure>getExpenditureBetweenDates(@Param("name")String username ,@Param("min")Date min,@Param("max")Date max,PageRequest pageable);
}
