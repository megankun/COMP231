package com.storybook.tests;

import static org.junit.jupiter.api.Assertions.*;
import com.storybook.entity.*;
import org.junit.jupiter.api.Test;

public class PaymentTest {
	
	private Payment p;

	@Test
	void paymentTest() {
		p = new Payment();
		p.setPaymentId(1);
		p.setBookId(1);
		p.setUserId(1);
		p.setCardType("Debit");
		p.setTotalPrice(250);
	}

}
