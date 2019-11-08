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
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EpisodeController {
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@RequestMapping(value= "/episode")
	public ModelAndView episode(String userId) {
		ModelAndView modelAndView = new ModelAndView("episode");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select b from Book b where b.userId = :param").setParameter("param", Integer.parseInt(userId));
		List<Book> bookList = query.getResultList();
		
		em.close();

		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookList", bookList);
		
		return modelAndView;
	}

	@RequestMapping(value= "/addStory")
	public ModelAndView addStory(int userId, int bookId) {
		ModelAndView modelAndView = new ModelAndView("add_story");

		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select b from Book b where b.userId = :userId and b.bookId = :bookId")
				.setParameter("userId", userId)
				.setParameter("bookId", bookId);
		
		Book book = (Book) query.getResultList().get(0);
		
		em.close();

		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookId", bookId);
		modelAndView.addObject("book", book);
		
		return modelAndView;
	}
	

	@RequestMapping(value= "/addStory", method = RequestMethod.POST)
	public ModelAndView addStory(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("IN!!");
		
		
		return null;
	}
}

	