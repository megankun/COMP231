package com.storybook.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.storybook.entity.Payment;
import com.storybook.entity.User;

class TotalInvestmentTest {

	private Payment p;
	private Payment q;
	private User u;

	@Test
	void investmentTest() {
		//Payment from User 1
		p = new Payment();
		p.setPaymentId(1);
		p.setBookId(1);
		p.setUserId(1);
		p.setCardType("Debit");
		p.setTotalPrice("250");

		//Payment from User 2
		q = new Payment();
		q.setPaymentId(2);
		q.setBookId(2);
		q.setUserId(2);
		q.setCardType("Debit");
		q.setTotalPrice("400");
		
		u = new User();
		u.setUserId(0);
		u.setUserType("Writer");		
		u.setFirstName("John");
		
		//u.setEarnings(Double.parseDouble(p.getTotalPrice()) + Double.parseDouble(q.getTotalPrice()));
		
		//System.out.printf("%s has made %f", u.getFirstName(), u.getEarnings());
	}
}
