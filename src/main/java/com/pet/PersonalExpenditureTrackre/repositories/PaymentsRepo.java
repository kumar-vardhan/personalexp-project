package com.pet.PersonalExpenditureTrackre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pet.PersonalExpenditureTrackre.entities.Payment;

public interface PaymentsRepo extends JpaRepository<Payment, Integer> {
	@Query(value="select * from payments where user_name=:name",nativeQuery = true)
	List<Payment> findbyPayment(@Param("name") String name);
	
	Payment getByCode(int paycode);
}
