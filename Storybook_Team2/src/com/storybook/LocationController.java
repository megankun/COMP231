package com.storybook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
