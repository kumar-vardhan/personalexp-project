package com.pet.PersonalExpenditureTrackre.Rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pet.PersonalExpenditureTrackre.entities.Expenditure;
import com.pet.PersonalExpenditureTrackre.repositories.ExpenditureRepo;
import com.pet.PersonalExpenditureTrackre.repositories.PaymentsRepo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ExpenditureRest {
	
	@Autowired
	ExpenditureRepo erepo;
	
//	@GetMapping("/top5expenses/{sort}")
//	public List<Expenditure> getTopExp(@PathVariable("sort")int sort){
//		String username=SecurityContextHolder.getContext().getAuthentication().getName();
//		switch(sort) {
//		case 1:
//			return erepo.findByUser_
//		}
//	}
	
	
	@GetMapping("/allexpenses")
	public List<Expenditure> getAllExpenses(){
		return erepo.findAll();
	}

	//6
	@GetMapping("/totalamount/{month}")
	public List<String> getTotalAmount(@PathVariable("month")int month){
		return erepo.totalAmount(month);
	}
	
	//5
	@GetMapping("/list/expenses/{tags}")
	public List<Expenditure> listAllExpenses(@PathVariable("tags") String tags){
		return erepo.findByTagsContaining(tags);
	}
	//2
	@GetMapping("/allexpenses/catcode/{code}")
	public List<Expenditure> listAllExpensesByCat(@PathVariable("code")String code){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		var page =   erepo.findByUser_UserNameAndCategory_CatCode(username,code,PageRequest.of(0,5,Sort.by("expId")));
	 	return page.getContent();
	}
	//3
	@GetMapping("/allexpenses/{paymode}")
	public List<Expenditure> listAllExpensesByPayment(@PathVariable("paymode") String paymode){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		var page =   erepo.findByUser_UserNameAndPayment_Names(username,paymode,PageRequest.of(0,5,Sort.by("expId")));
	 	return page.getContent();
	}
	//4
	@GetMapping("/allexpenses/date/{date1}/{date2}")
	@Operation(description = "enter the date in yyyy-mm-dd format")
	public List<Expenditure> listAllExpensesByDate(@PathVariable("date1")Date date1,@PathVariable("date2")Date date2){
		var page =   erepo.findBySpentOnBetween(date1,date2,PageRequest.of(0,5,Sort.by("Payment.code").descending()));
	 	return page.getContent();
	}
	
	//7
	@GetMapping("/top5expenses/user")
	public List<Expenditure> top5Expenses(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		return erepo.findTop2ByUser_UserNameOrderByAmountDesc(username);
	}
	//8
	@DeleteMapping("/deleteExpenditure")
	public void deleteExp(@RequestParam("eid") int eid) {
		var v = erepo.findById(eid);
		if(v.isPresent()) {
			erepo.deleteById(eid);
		}
		else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid data");
	}
	//8
	@PutMapping("/updateExpenditure")
	public Expenditure setExpId(@RequestParam("id")int id,@RequestParam("amount")double amount) {
		var exp = erepo.findById(id);
		if(exp.isPresent())
		{
			var t=exp.get();
			t.setAmount(amount);
			erepo.save(t);
			return t;
			
		}
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found ");
	}
	//12
	
	@Autowired
	private PaymentsRepo paymentrepo;
	
	@PostMapping("/addexpenditure")
	public Expenditure addExpenditure(@RequestBody Expenditure exp) {
		var payname = paymentrepo.getByCode(exp.getCode());
		
		if(exp.getUserName().compareTo(payname.getUserName())==0) {
				
			erepo.save(exp);
			return exp;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"name mismatch");
		}
	}




}
