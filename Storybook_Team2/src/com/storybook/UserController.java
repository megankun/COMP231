package com.storybook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	private static EntityManagerFactory factory;
	private static EntityManager cs;

	@RequestMapping(value= "/login", params = "Sign In", method = RequestMethod.POST)
	// method to process login request
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("main");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User checkUser = new User();
		
		// *** Need to check user is real user or not!
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();

		cs.getTransaction().begin();

		Query query = cs.createQuery("select c from User c where c.email = '" + email + "' and c.password = '" + password +"'");
		
		if(!query.getResultList().isEmpty()) {
			checkUser = (User) query.getResultList().get(0);
			cs.close();
			
			view.addObject("userId", checkUser.getUserId());
			view.addObject("FirstName", checkUser.getFirstName());
			// login success
			return view;
			
		} else {
			// login fail
			String message = "Please check your account !";
			ModelAndView indexView = new ModelAndView("signIn");
			indexView.addObject("message", message);
			
			return indexView;
		}
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String customers(HttpServletRequest request, HttpServletResponse response) {
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView SuccessSignUp(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();

		
		if(!"".equals(request.getParameter("userId")) && !"".equals(request.getParameter("email")) &&
				!"".equals(request.getParameter("password"))) {
			
			System.out.println("Update");
			
			// update
			int userId = Integer.parseInt(request.getParameter("userId"));
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println("password ======= " + password);
			String userType = request.getParameter("userType");
			String firstname = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
	
			String phoneNumber = request.getParameter("phoneNumber");

			cs.getTransaction().begin();
			
			Query query = cs.createQuery("update User "
										+ "set email = '" + email + "', "
										+ "password = '" + password + "', "
										+ "userType = '" + userType + "', "
										+ "firstName = '" + firstname + "', "
										+ "lastName = '" + lastname + "', "
										+ "phoneNumber = '" + phoneNumber + "' "
										+ "where userId = " + userId);
			query.executeUpdate();
			
			cs.getTransaction().commit();
			cs.clear();			
			
			message = "Your Account is successfully updated !";
			
		}else if(!"".equals(request.getParameter("email")) && !"".equals(request.getParameter("password"))) {

			System.out.println("insert");
			
			// insert
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String userType = request.getParameter("userType");
			String firstname = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
			String phoneNumber = request.getParameter("phoneNumber");
	
			cs.getTransaction().begin();

			User u = new User();
			u.setEmail(email);
			u.setPassword(password);
			u.setFirstName(firstname);
			u.setLastName(lastname);
			u.setUserType(userType);
			u.setPhoneNumber(phoneNumber);
			
			cs.persist(u);
			cs.getTransaction().commit();
			cs.clear();
			
			message = "Your Account is successfully saved !";
		}		
		
		cs.close();		
		
		return new ModelAndView("signIn", "message", message);		
	}
}

	