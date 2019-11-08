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
public class CharacterController {

	private static EntityManagerFactory factory;
	private static EntityManager em;

	@RequestMapping(value= "/characterList")
	public ModelAndView characterList(String bookId) {
		ModelAndView modelAndView = new ModelAndView("character_list");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select c from BookCharacter c where c.bookId = :param").setParameter("param", Integer.parseInt(bookId));
		List<Book> characterList = query.getResultList();
		
		em.close();

		modelAndView.addObject("characterList", characterList);
		
		
		return modelAndView;
	}
}
