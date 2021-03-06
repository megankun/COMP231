package com.storybook.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.storybook.entity.Book;
import com.storybook.entity.Payment;


@Controller
public class PaymentController {
	
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	
	@RequestMapping(value= "/fundingList")
	public ModelAndView fundingListforSelectedBook(String bookId, String userId) {
		ModelAndView modelAndView = new ModelAndView("funding_list");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select c from Payment c where c.bookId = :param").setParameter("param", Integer.parseInt(bookId));
		List<Payment> fundingList = query.getResultList();
		
		em.close();
		System.out.println("test");
        
		for(int i = 0; i < fundingList.size(); i++) {
			 System.out.println("test1");
	            System.out.println(fundingList.get(i).getNameOnCard());
	            System.out.println(fundingList.get(i).getTotalPrice());
	            
	        }


		
		modelAndView.addObject("fundingList", fundingList);
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId", bookId);
		return modelAndView;
	}
	
	
	@RequestMapping(value= "/payment")
	public ModelAndView paymentforselectedBook(String bookId, String userId) {
		ModelAndView modelAndView = new ModelAndView("payment");
		
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId", bookId);
		return modelAndView;
	}
	
	//Method to Handle Payment Transactions
	@RequestMapping(value= "/payment", method = RequestMethod.POST)
	public ModelAndView addPayment(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("paymentConfirmation");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();
		
			
					
	 if(!"".equals(request.getParameter("name"))) {

			
			// insert
			int bookId  = Integer.parseInt(request.getParameter("bookId"));	
			int userId  = Integer.parseInt(request.getParameter("userId"));	
	
			String cardType = request.getParameter("cardType");
			String nameOnCard = request.getParameter("nameOnCard");
			String cardNumber = request.getParameter("cardNumber");
			String expiryDate = request.getParameter("expiryDate");
			String cvc = request.getParameter("cvc");
			String country = request.getParameter("country");
			String zipCode = request.getParameter("zipCode");
			String totalPrice = request.getParameter("totalPrice");
			
			em.getTransaction().begin();

			Payment payment = new Payment();
			payment.setBookId(bookId);
			payment.setUserId(userId);
			payment.setNameOnCard(nameOnCard);
			payment.setCardType(cardType);
			payment.setCardNumber(cardNumber);
			payment.setExpiryDate(expiryDate);
			payment.setCvc(cvc);
			payment.setCountry(country);
			payment.setZipCode(zipCode);
			payment.setTotalPrice(totalPrice);
			
			em.persist(payment);
			em.getTransaction().commit();
			em.clear();
			
			
		}		
	 	em.close();
	 	int userId1  = Integer.parseInt(request.getParameter("userId"));	
	 	modelAndView.addObject("userId",userId1);
		return modelAndView;
	}
	
	
	@RequestMapping(value= "/editpayment")
	public ModelAndView editPaymentInfoforBook(String bookId, String userId) {
		ModelAndView modelAndView = new ModelAndView("edit_payment");
	    factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();
		Query query = em.createQuery("select p from Payment p where p.userId = " + userId + " and p.bookId = " + bookId );
		Payment payment = new Payment();
		if (query.getResultList().isEmpty())
		{
			payment.setUserId(Integer.parseInt(userId));
			payment.setBookId(Integer.parseInt(bookId));
			em.persist(payment);
			em.getTransaction().commit();
			em.clear();

			Query query2 = em.createQuery("select p from Payment p where p.userId = " + userId + " and p.bookId = " + bookId );
			payment = (Payment) query2.getResultList().get(0);
		 	em.close();
		}
		else
		{
			payment = (Payment) query.getResultList().get(0);
		}
		
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId", bookId);
		modelAndView.addObject("paymentId", payment.getPaymentId());
		modelAndView.addObject("payment", payment);
		return modelAndView;
	}
	
	
	@RequestMapping(value= "/updatepayment", method = RequestMethod.POST)
	public ModelAndView updatePaymentforBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("edit_payment");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int paymentId = Integer.parseInt(request.getParameter("paymentId"));
		String cardType = request.getParameter("cardType");
		String nameOnCard = request.getParameter("nameOnCard");
		String cardNumber = request.getParameter("cardNumber");
		String expiryDate = request.getParameter("expiryDate");
		String cvc = request.getParameter("cvc");
		String country = request.getParameter("country");
		String zipCode = request.getParameter("zipCode");
		String totalPrice = request.getParameter("totalPrice");
	 	
	 	Payment payment = new Payment();
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
	    em.getTransaction().begin();
	    payment=em.find(Payment.class, paymentId);
	    
	    payment.setBookId(bookId);
		payment.setUserId(userId);
		payment.setNameOnCard(nameOnCard);
		payment.setCardType(cardType);
		payment.setCardNumber(cardNumber);
		payment.setExpiryDate(expiryDate);
		payment.setCvc(cvc);
		payment.setCountry(country);
		payment.setZipCode(zipCode);
		payment.setTotalPrice(totalPrice);;
		em.persist(payment);
		em.getTransaction().commit();
		
		modelAndView.addObject("payment", payment);
		modelAndView.addObject("userId", userId);
				
	  	return modelAndView;
	}


}
