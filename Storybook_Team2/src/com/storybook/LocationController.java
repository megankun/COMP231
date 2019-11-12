package com.storybook;

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

@Controller
public class LocationController {
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@RequestMapping(value= "/locationList")
	public ModelAndView locationList(String bookId) {
		ModelAndView modelAndView = new ModelAndView("location_list");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select l from Location l where l.bookId = :param").setParameter("param", Integer.parseInt(bookId));
		List<Book> locationList = query.getResultList();
		
		em.close();

		modelAndView.addObject("locationList", locationList);
		
		return modelAndView;
	}
	

	@RequestMapping(value= "/updatedLocation")
	public ModelAndView newlocationList(String bookId,int userId) {
		ModelAndView modelAndView = new ModelAndView("location_list_update");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select l from Location l where l.bookId = " + bookId);
		List<Book> locationList = query.getResultList();

		em.close();		
		
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId",bookId);
		modelAndView.addObject("locationList", locationList);
		return modelAndView;
	}
	
	
	@RequestMapping(value= "/newLocation", method = RequestMethod.GET)
	public ModelAndView createNewLocation(@RequestParam String bookId, int userId) {
		ModelAndView modelAndView = new ModelAndView("newLocation");
		
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId", bookId);
		return modelAndView;
	}
	
	@RequestMapping(value= "/editLocation")
	public ModelAndView editLocation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("edit_location");
		
		int userId  = Integer.parseInt(request.getParameter("userId"));
		int locationId  = Integer.parseInt(request.getParameter("locationId"));
		int bookId  = Integer.parseInt(request.getParameter("bookId"));	
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		modelAndView.addObject("locationId",locationId);
		modelAndView.addObject("name", name);
		modelAndView.addObject("description",description);
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId", bookId);
	
		
		return modelAndView;
	}
	
	@RequestMapping(value= "/newLocation", method = RequestMethod.POST)
	public ModelAndView createNewLocationforBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("location_list_update");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();
		
	
		if(!"".equals(request.getParameter("locationId")) && !"".equals(request.getParameter("bookId")) &&
				!"".equals(request.getParameter("name"))) {
			
			System.out.println("Update");
			
			// update
			int locationId  = Integer.parseInt(request.getParameter("locationId"));
			int bookId  = Integer.parseInt(request.getParameter("bookId"));
			
			String name = request.getParameter("name");
			String description = request.getParameter("description");


			em.getTransaction().begin();
			
			Query query = em.createQuery("update Location "
										+ "set name = '" + name + "', "
										+ "description = '" + description + "', "
										+ "bookId = '" + bookId + "' "
										+ "where locationId = " + locationId);
										
			query.executeUpdate();
			
			em.getTransaction().commit();
			em.clear();			
		
			
		}else if(!"".equals(request.getParameter("name"))) {

			System.out.println("insert");
			
			// insert
			int bookId  = Integer.parseInt(request.getParameter("bookId"));	
			System.out.print(bookId);
			String name = request.getParameter("name");
			System.out.print(name);
			String description = request.getParameter("description");

			em.getTransaction().begin();

			Location location = new Location();
			location.setBookId(bookId);
			location.setName(name);
			location.setDescription(description);
		
			
			em.persist(location);
			em.getTransaction().commit();
			em.clear();
			
			
		}		
		
		em.getTransaction().begin();
		
		int bookId  = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		Query query = em.createQuery("select l from Location l where l.bookId = " + bookId);
		List<Book> locationList = query.getResultList();

		em.close();		

		modelAndView.addObject("userId",userId);
		modelAndView.addObject("bookId",bookId);
		modelAndView.addObject("locationList", locationList);
		
		return modelAndView;
	}
}
