package com.storybook;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		ModelAndView modelAndView = new ModelAndView("");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String chapterTitle = request.getParameter("chapterTitle");
		String note = request.getParameter("note");

		String[] tempLocationIdsArray = request.getParameter("checkedLocationIds").split(",");
		String[] tempCharacterIdsArray = request.getParameter("checkedCharacterIds").split(",");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created_at = formatter.format(new Date());
		
		// Add Story
		em.getTransaction().begin();
		
		Story story = new Story();
		
		story.setChapterTitle(chapterTitle);
		story.setNote(note);
		story.setBookId(bookId);
		story.setCreated_at(created_at);

		em.persist(story);

		em.getTransaction().commit();
		
		
		// Select Story Id
		Query query = em.createQuery("select s.storyId from Story s where s.created_at = :param").setParameter("param", created_at);
		int storyId = (int) query.getResultList().get(0);

		// Add Story_Location
		em.clear();          
        em.getTransaction().begin();
        
		for(int i = 0; i < tempLocationIdsArray.length; i++) {
			Story_Location story_location = new Story_Location();
			story_location.setStoryId(storyId);
			story_location.setLocationId(Integer.parseInt(tempLocationIdsArray[i]));

			em.persist(story_location);
			
			em.getTransaction().commit();

			
			em.clear();          
	        em.getTransaction().begin();
		}
		
		// Add Story_BookCharacter
		em.clear();          
        
		for(int i = 0; i < tempCharacterIdsArray.length; i++) {
			Story_BookCharacter story_bookCharacter = new Story_BookCharacter();
			story_bookCharacter.setStoryId(storyId);
			story_bookCharacter.setCharacterId(Integer.parseInt(tempCharacterIdsArray[i]));

			em.persist(story_bookCharacter);
			
			em.getTransaction().commit();

			
			em.clear();          
	        em.getTransaction().begin();
		}
		
		em.close();
		
		// return to list of stories for that book (Megan)
		return modelAndView;
	}
}

	