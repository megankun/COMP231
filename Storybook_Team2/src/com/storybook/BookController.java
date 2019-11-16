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
public class BookController {
	private static EntityManagerFactory factory;
	private static EntityManager em;

	
	/**
	 * POST response to add a new book to the database
	 * @param request
	 * @param response
	 * @return episode view
	 */
	@RequestMapping(value= "/addBook", method = RequestMethod.POST)
	public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView("episode");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		// Get the book title and genre from the view
		String title = request.getParameter("bookTitle");
		String genre = request.getParameter("genre");

		// Add Book
		em.getTransaction().begin();
		
		Book book = new Book();
		
		book.setUserId(userId);
		book.setTitle(title);
		book.setGenre(genre);

		em.persist(book);

		em.getTransaction().commit();

		em.clear();
		
		// Get list of books to display in the next view
		Query query = em.createQuery("select b from Book b where b.userId = :param").setParameter("param", userId);
		List<Book> bookList = query.getResultList();
		
		em.clear();
		
		// Get the user type to display the correct objects in the next view
		User user = new User();
		Query userQuery = em.createQuery("select u from User u where u.userId = :param").setParameter("param", userId);
	
		user = (User) userQuery.getResultList().get(0);
		
		em.close();
	
		modelAndView.addObject("userType", user.getUserType());	
		System.out.println(user.getUserType());
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookList", bookList);
		
		return modelAndView;
	}
	
	
}

	