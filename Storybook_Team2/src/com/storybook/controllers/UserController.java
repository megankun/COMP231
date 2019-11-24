package com.storybook.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.storybook.entity.User;


@Controller
public class UserController {
	private static EntityManagerFactory factory;
	private static EntityManager cs;

	@RequestMapping(value= "/login", params = "Sign In", method = RequestMethod.POST)
	// method to process login request
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("tutorial");
		
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


	@RequestMapping(value = "/userAccountInfo", method = RequestMethod.GET)
	public ModelAndView getUserAccountInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("accountinfo");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();
		Query query = cs.createQuery("select c from User c where c.userId = '" + userId + "'");
		User user = null;
		if(!query.getResultList().isEmpty()) {
			user = (User) query.getResultList().get(0);
				
		}
	
		modelAndView.addObject("user", user);
		modelAndView.addObject("userId", userId);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/edituserinfo", method = RequestMethod.POST)
	public ModelAndView editUserAccountInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("accountinfo");
		
		User user = new User();
	
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();
		cs.getTransaction().begin();
		user=cs.find(User.class, Integer.parseInt(request.getParameter("userId")));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setUserType(request.getParameter("userType"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		cs.persist(user);
		cs.getTransaction().commit();
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("userId", request.getParameter("userId"));
		
	    return modelAndView;	
		
	}
	
	@RequestMapping(value= "/userDetailsList")
	public ModelAndView userDetailsList(String userId) {
		ModelAndView modelAndView = new ModelAndView("user_list");

		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();
		cs.getTransaction().begin();

		Query query = cs.createQuery("select c from User c ");
		List<User> userList = query.getResultList();
		cs.getTransaction().commit();
		cs.close();
		
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}
	
	@RequestMapping(value = "/userAboutMeInfo", method = RequestMethod.GET)
	public ModelAndView userAboutMeInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("about_me");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int selectedUserId = Integer.parseInt(request.getParameter("selectedUserId"));
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();
		Query query = cs.createQuery("select c from User c where c.userId = '" + selectedUserId + "'");
		User user = null;
		if(!query.getResultList().isEmpty()) {
			user = (User) query.getResultList().get(0);
				
		}
	
		modelAndView.addObject("user", user);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("selectedUserId", selectedUserId);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/userAboutMeEdit", method = RequestMethod.GET)
	public ModelAndView userAboutMeEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("edit_aboutme");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();
		Query query = cs.createQuery("select c from User c where c.userId = '" + userId + "'");
		User user = null;
		if(!query.getResultList().isEmpty()) {
			user = (User) query.getResultList().get(0);
		}
	
		modelAndView.addObject("aboutUser", user.getAboutUser());
		modelAndView.addObject("user", user);
		modelAndView.addObject("userId", userId);
		
		return modelAndView;
	}	
	
	@RequestMapping(value = "/editAboutMeinfo", method = RequestMethod.POST)
	public ModelAndView editAboutMeinfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("user_list");
		
		User user = new User();
	
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		cs = factory.createEntityManager();
		cs.getTransaction().begin();
		user=cs.find(User.class, Integer.parseInt(request.getParameter("userId")));
		user.setAboutUser(request.getParameter("aboutUser"));
		cs.persist(user);
		cs.getTransaction().commit();
		Query query = cs.createQuery("select c from User c ");
		List<User> userList = query.getResultList();
		cs.close();
		
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("userId", request.getParameter("userId"));
		
	    return modelAndView;	
		
	}

}

	