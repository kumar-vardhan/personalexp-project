package com.pet.PersonalExpenditureTrackre.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pet.PersonalExpenditureTrackre.entities.Payment;
import com.pet.PersonalExpenditureTrackre.repositories.PaymentsRepo;

@RestController
public class PaymentRest {

	@Autowired
	PaymentsRepo prepo;

	
	@GetMapping("/allpayments")
	public List<Payment> getAllPayments(){
		return prepo.findAll();
	}
	//11
	@GetMapping("/allpays/user")
	public List<Payment> allPayments(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		return prepo.findbyPayment(username);
	}
	//1
	@PostMapping("/add/payment")
	public Payment addPayment(@RequestBody Payment pay) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		
		var paycode = prepo.findById(pay.getCode());
		if(paycode.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"code is already present");
		}
		else
		{
			if(pay.getUserName().equals(username))
			{
				prepo.save(pay);
				return pay;
			}
			else
			{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid data");
			}
		}
	}
	//9
	@DeleteMapping("/deletePayment/{code}")
	public void deletePay(@PathVariable("code") int code) {
		var v = prepo.findById(code);
		if(v.isPresent()) {
			prepo.deleteById(code);
		}
		else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid data");
	}
	//9
	@PutMapping("/updatePayment/{code}/{pname}")
	public void updatePay(@PathVariable("code") int code, @PathVariable("pname") String payname)
	{
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		var a = prepo.findById(code);
		if(a.isPresent())
		{
			var b = a.get();
			if(b.getUser().getUserName().equals(username))
			{
				b.setNames(payname);
				prepo.save(b);
			}
		}
	}
	
	
}
