package com.storybook.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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

import com.storybook.entity.Book;
import com.storybook.entity.BookCharacter;
import com.storybook.entity.Location;
import com.storybook.entity.User;



@Controller
public class BookController {
	private static EntityManagerFactory factory;
	private static EntityManager em;

	
	@RequestMapping(value= "/addBook", method = RequestMethod.POST)
	public ModelAndView addBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("episode");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		
		String title = request.getParameter("bookTitle");
		String genre = request.getParameter("genre");

		
		// Add Story
		em.getTransaction().begin();
		
		Book book = new Book();
		
		book.setUserId(userId);
		book.setTitle(title);
		book.setGenre(genre);

		em.persist(book);

		em.getTransaction().commit();

		em.clear();

		Query query = em.createQuery("select b from Book b where b.userId = :param").setParameter("param", userId);
		List<Book> bookList = query.getResultList();
		
		em.clear();
		
		User user = new User();
		Query query1 = em.createQuery("select u from User u where u.userId = :param").setParameter("param", userId);
	
		user = (User) query1.getResultList().get(0);
		
		em.close();
	
		modelAndView.addObject("userType", user.getUserType());	
		System.out.println(user.getUserType());
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookList", bookList);
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value= "/checkfinaldraft", method = RequestMethod.GET)
	public ModelAndView checkFinalDraft(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("bookfinaldraft");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		//Search book title and genre
		Query queryForBook = em.createQuery("select b from Book b where b.bookId = :bookId")
				.setParameter("bookId", bookId);
		Book book = (Book)queryForBook.getResultList().get(0);
			
		//Search all the locations of the book
		Query queryForLocation = em.createQuery("select l from Location l where l.bookId = :bookId")
				.setParameter("bookId", bookId);
		List<Location> locations = queryForLocation.getResultList();
				
		//Search all the characters of the book
		Query queryForCharacter = em.createQuery("select b from BookCharacter b where b.bookId = :bookId")
				.setParameter("bookId", bookId);
		List<BookCharacter> characters = queryForCharacter.getResultList();
		
		
	    em.clear();
		em.close();
	
		modelAndView.addObject("book", book);	
		modelAndView.addObject("locations", locations);
		modelAndView.addObject("characters", characters);
		modelAndView.addObject("userId", userId);
		//modelAndView.addObject("stories", stories);
		
		return modelAndView;
	}


	/**
	 * POST response to edit a book in the database
	 * @param request
	 * @param response
	 * @return episode view
	 */
	@RequestMapping(value= "/editBook", method = RequestMethod.POST)
	public ModelAndView editBook(HttpServletRequest request, HttpServletResponse response) 
	{
		
		ModelAndView modelAndView = new ModelAndView("episode");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		// Get the book title and genre from the view
		String title = request.getParameter("bookTitle");
		String genre = request.getParameter("genre");
		String bookDescription = request.getParameter("bookDescription");
		int bookId = Integer.parseInt(request.getParameter("bookId"));

		// Edit Book
		em.getTransaction().begin();
		
		Book book = new Book();
		
		book.setUserId(userId);
		book.setBookId(bookId);
		book.setTitle(title);
		book.setGenre(genre);
		book.setBookDescription(bookDescription);

		em.merge(book);

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
	
	
	/**
	 * @param userId
	 * @param bookId
	 * @return view to edit book details
	 */
	@RequestMapping(value= "/editBook")
	public ModelAndView editBook(int userId, int bookId) {
		ModelAndView modelAndView = new ModelAndView("edit_book");

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
	
	@RequestMapping(value="/viewDescription")
	public ModelAndView viewBookDescription(int userId, int bookId)
	{
		ModelAndView modelAndView = new ModelAndView("view_description");

		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("select b from Book b where b.bookId = :bookId")
				.setParameter("bookId", bookId);
		
		Book book = (Book) query.getResultList().get(0);
		
		em.close();

		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookId", bookId);
		modelAndView.addObject("book", book);
		
		return modelAndView;
	}
	

	@RequestMapping(value="/deleteBook")
	public ModelAndView deleteBook(int userId, int bookId)
	{		
		// delete book include all stories, locations, characters
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();		
		int deletedCount;
		
		// Delete Story_Location & Location 
		em.getTransaction().begin();		
		// Get Location Ids
		Query queryforLocationId = em.createQuery("select l.locationId from Location l where l.bookId = :bookId")
				.setParameter("bookId", bookId);
		List<Integer> locationId = queryforLocationId.getResultList();
		
		for(int i = 0; i < locationId.size(); i++) {			
			// Delete Story_Location
			Query queryforStoryLocation = em.createQuery("delete from Story_Location sl where sl.locationId = :locationId");
			deletedCount = queryforStoryLocation.setParameter("locationId", locationId.get(i)).executeUpdate();
			
			// Delete Location
			Query queryforLocation = em.createQuery("delete from Location l where l.locationId = :locationId");
			deletedCount = queryforLocation.setParameter("locationId", locationId.get(i)).executeUpdate();
		}
		em.getTransaction().commit();
		em.clear();
		
		// Delete Story_BookCharacter & BookCharacter
		em.getTransaction().begin();		
		// Get Character Ids
		Query queryforCharacterId = em.createQuery("select c.characterId from BookCharacter c where c.bookId = :bookId")
				.setParameter("bookId", bookId);
		List<Integer> characterId = queryforCharacterId.getResultList();

		for(int i = 0; i < characterId.size(); i++) {			
			// Delete Story_Character
			Query queryforStoryCharacter = em.createQuery("delete from Story_BookCharacter sc where sc.characterId = :characterId");
			deletedCount = queryforStoryCharacter.setParameter("characterId", characterId.get(i)).executeUpdate();
			
			// Delete Character
			Query queryforCharacter = em.createQuery("delete from BookCharacter c where c.characterId = :characterId");
			deletedCount = queryforCharacter.setParameter("characterId", characterId.get(i)).executeUpdate();
		}
		em.getTransaction().commit();
		em.clear();

		// Delete Stories
		em.getTransaction().begin();
		// Get Story Ids
		Query queryforStoryId = em.createQuery("select s.storyId from Story s where s.bookId = :bookId")
				.setParameter("bookId", bookId);
		List<Integer> storyIds = queryforStoryId.getResultList();
		
		for(int i = 0; i < storyIds.size(); i++) {
			// Delete Story
			Query queryforStory = em.createQuery("delete from Story s where s.storyId = :storyId");
			deletedCount = queryforStory.setParameter("storyId", storyIds.get(i)).executeUpdate();
		}
		em.getTransaction().commit();
		em.clear();

		// Delete Payment
		em.getTransaction().begin();
		
		Query queryforPayment = em.createQuery("delete from Payment p where p.bookId = :bookId");
		deletedCount = queryforPayment.setParameter("bookId", bookId).executeUpdate();

		em.getTransaction().commit();
		em.clear();
		
		// Delete Book
		em.getTransaction().begin();
		
		Query queryforBook = em.createQuery("delete from Book b where b.bookId = :bookId");
		deletedCount = queryforBook.setParameter("bookId", bookId).executeUpdate();

		em.getTransaction().commit();
		
		em.close();

		
		return new ModelAndView("redirect:/episode", "userId", userId);
	}
}

	