package com.storybook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		
		List<Book> bookList = new ArrayList<Book>();

		em.getTransaction().begin();
		
		// Get user from DB
		User user = new User();
		Query query1 = em.createQuery("select u from User u where u.userId = :param").setParameter("param", Integer.parseInt(userId));
	
		user = (User) query1.getResultList().get(0);
		
		em.clear();
		
		// check user type
		if("Writer".equals(user.getUserType())) {
			Query query = em.createQuery("select b from Book b where b.userId = :param").setParameter("param", Integer.parseInt(userId));
			bookList = query.getResultList();
		}else { // Investor & Designer (will give them permission to access book or not?)
			Query query = em.createQuery("select b from Book b");
			bookList = query.getResultList();
		}
		
		em.close();
	
		modelAndView.addObject("userType", user.getUserType());	
		System.out.println(user.getUserType());
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
		ModelAndView modelAndView = new ModelAndView("chapter_list");
		
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
		
		Query chapters = em.createQuery("select s from Story s where s.bookId = :param").setParameter("param", bookId);
		List<Story> chapterList = chapters.getResultList();
		
		em.close();
		
		modelAndView.addObject("chapterList", chapterList);

		int userId = Integer.parseInt(request.getParameter("userId"));
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookId", bookId);
		
		return modelAndView;
	}


	@RequestMapping(value= "/chapterList")
	public ModelAndView chapter(String userId, String bookId)
	{
		ModelAndView modelAndView = new ModelAndView("chapter_list");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		
		Query chapters = em.createQuery("select s from Story s where s.bookId = :param").setParameter("param", Integer.parseInt(bookId));
		List<Story> chapterList = chapters.getResultList();
		
		em.close();
		
		modelAndView.addObject("chapterList", chapterList);
		modelAndView.addObject("bookId", bookId);
		modelAndView.addObject("userId", userId);
		
		// return to list of stories for that book (Megan)
		return modelAndView;
	}

	
	@RequestMapping(value= "/loadEditStory")
	public ModelAndView loadEditStory(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("edit_story");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int storyId = Integer.parseInt(request.getParameter("storyId"));
		

		Query chapters = em.createQuery("select s from Story s where s.bookId = :bookId and s.storyId = :storyId")
				.setParameter("bookId", bookId)
				.setParameter("storyId", storyId);
		List<Story> chapterList = chapters.getResultList();
		
		for (Story chapter : chapterList)
		{
			modelAndView.addObject("chapterTitle", chapter.getChapterTitle());
			modelAndView.addObject("note", chapter.getNote());
			modelAndView.addObject("createdAt", chapter.getCreated_at());
		}
		
		Query characters = em.createQuery("select c from Story_BookCharacter c where c.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_BookCharacter> characterList = characters.getResultList();
		String value = "";
		for (Story_BookCharacter chara : characterList)
		{
			value += chara.getCharacterId() + ",";
		}
		modelAndView.addObject("checkedCharacterIds", value);
		
		Query locations = em.createQuery("select l from Story_Location l where l.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_Location> locationList = locations.getResultList();
		value = "";
		for (Story_Location loc : locationList)
		{
			value += loc.getLocationId() + ",";
		}
		modelAndView.addObject("checkedLocationIds", value);
		
		Query query = em.createQuery("select b from Book b where b.userId = :userId and b.bookId = :bookId")
				.setParameter("userId", userId)
				.setParameter("bookId", bookId);
		
		Book book = (Book) query.getResultList().get(0);
		
		modelAndView.addObject("book", book);
		
		em.close();
		
		modelAndView.addObject("bookId", bookId);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("storyId", storyId);
		
		
		// return to list of stories for that book
		return modelAndView;
	}

	
	@RequestMapping(value= "/editStory", method = RequestMethod.POST)
	public ModelAndView editStory(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("chapter_list");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		// Select Story Id
		int storyId = Integer.parseInt(request.getParameter("storyId"));

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String chapterTitle = request.getParameter("chapterTitle");
		String note = request.getParameter("note");

		String[] tempLocationIdsArray = request.getParameter("checkedLocationIds").split(",");
		String[] tempCharacterIdsArray = request.getParameter("checkedCharacterIds").split(",");
		
		String createdAt = request.getParameter("createdAt");

		// update Story
		em.getTransaction().begin();
		
		Story story = new Story();
		
		story.setChapterTitle(chapterTitle);
		story.setNote(note);
		story.setBookId(bookId);
		story.setStoryId(storyId);
		story.setCreated_at(createdAt);

		em.merge(story);
		em.getTransaction().commit();

		// update Story_Location
		em.clear();          
        em.getTransaction().begin();
        
		Query locations = em.createQuery("select l from Story_Location l where l.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_Location> locationList = locations.getResultList();
		
		for (Story_Location loc : locationList)
		{
			if (em.contains(loc))
			{
				em.remove(loc);
				em.getTransaction().commit();
				em.clear();          
		        em.getTransaction().begin();
			}
		}
		
		Query characters = em.createQuery("select c from Story_BookCharacter c where c.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_BookCharacter> characterList = characters.getResultList();
		
		for (Story_BookCharacter chara : characterList)
		{
			if (em.contains(chara))
			{
				em.remove(chara);
				em.getTransaction().commit();
				em.clear();          
		        em.getTransaction().begin();
			}
		}
		

		em.clear();          
        //em.getTransaction().begin();
		
		for(int i = 0; i < tempLocationIdsArray.length; i++) {
			Story_Location story_location = new Story_Location();
			story_location.setStoryId(storyId);
			story_location.setLocationId(Integer.parseInt(tempLocationIdsArray[i]));
			
			em.persist(story_location);
			em.getTransaction().commit();
			em.clear();      
	        em.getTransaction().begin();     
		}
		
		// update Story_BookCharacter
		em.clear();      
        //em.getTransaction().begin();


		for(int i = 0; i < tempCharacterIdsArray.length; i++) {
			Story_BookCharacter story_bookCharacter = new Story_BookCharacter();
			story_bookCharacter.setStoryId(storyId);
			story_bookCharacter.setCharacterId(Integer.parseInt(tempCharacterIdsArray[i]));

			em.persist(story_bookCharacter);
			em.getTransaction().commit();
			em.clear();          
	        em.getTransaction().begin();
		}

		Query chapters = em.createQuery("select s from Story s where s.bookId = :param").setParameter("param", bookId);
		List<Story> chapterList = chapters.getResultList();
		
		em.close();
		
		modelAndView.addObject("chapterList", chapterList);

		int userId = Integer.parseInt(request.getParameter("userId"));
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("bookId", bookId);
		
		return modelAndView;
	}
	

	
	
	
	
	@RequestMapping(value= "/searchStories")
	public ModelAndView searchStories(String userId)
	{
		ModelAndView modelAndView = new ModelAndView("search_stories");

		modelAndView.addObject("userId", userId);
		return modelAndView;
	}

	
	@RequestMapping(value= "/searchStories", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("search_stories");
		
		String searchFilter = request.getParameter("searchFilter");
		String searchStr = request.getParameter("searchStr");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();
		
		List<Book> bookList = new ArrayList<Book>();
		List<Story> storyList = new ArrayList<Story>();
		
		Query queryForBookList;
		if("bookTitle".equals(searchFilter)) {
			queryForBookList = em.createQuery("select b from Book b where b.title like '%" + searchStr + "%'");
			bookList = queryForBookList.getResultList();

			
			if(bookList.size() > 0) {
				for(int i = 0; i < bookList.size(); i++) {
					int bookId = bookList.get(i).getBookId();
					
					Query queryForStoryList = em.createQuery("select s from Story s where s.bookId = :bookId")
							.setParameter("bookId", bookId);
					
					storyList = queryForStoryList.getResultList();
					bookList.get(i).setStoryList(storyList);
				}
			}
			
		}else if("genre".equals(searchFilter)) {
			queryForBookList = em.createQuery("select b from Book b where b.genre like '%" + searchStr + "%'");
			bookList = queryForBookList.getResultList();

			
			if(bookList.size() > 0) {
				for(int i = 0; i < bookList.size(); i++) {
					int bookId = bookList.get(i).getBookId();
					
					Query queryForStoryList = em.createQuery("select s from Story s where s.bookId = :bookId")
							.setParameter("bookId", bookId);
					
					storyList = queryForStoryList.getResultList();
					bookList.get(i).setStoryList(storyList);
				}
			}
			
		}else if("chapterTitle".equals(searchFilter)) {
			Query queryForStoryList = em.createQuery("select s from Story s where s.chapterTitle like '%" + searchStr + "%'");
			storyList = queryForStoryList.getResultList();
			
			// get book information for each story
			Book tempBook = new Book();
			if(storyList.size() > 0) {
				for(int i = 0; i < storyList.size(); i++) {
					Query queryForBook = em.createQuery("select b from Book b where b.bookId = :bookId")
							.setParameter("bookId", storyList.get(i).getBookId());
					
					tempBook = (Book)queryForBook.getResultList().get(0);
					
					
				}
				tempBook.setStoryList(storyList);
				bookList.add(tempBook);
			}
		}else if("storyNote".equals(searchFilter)) {
			Query queryForStoryList = em.createQuery("select s from Story s where s.note like '%" + searchStr + "%'");
			storyList = queryForStoryList.getResultList();
			
			// get book information for each story
			Book tempBook = new Book();
			if(storyList.size() > 0) {
				for(int i = 0; i < storyList.size(); i++) {
					Query queryForBook = em.createQuery("select b from Book b where b.bookId = :bookId")
							.setParameter("bookId", storyList.get(i).getBookId());
					
					tempBook = (Book)queryForBook.getResultList().get(0);
				}
				tempBook.setStoryList(storyList);
				bookList.add(tempBook);
			}
		}
		
		em.clear();
		em.close();
		
		modelAndView.addObject("userId", request.getParameter("userId"));
		modelAndView.addObject("bookList", bookList);
		return modelAndView;
	}
	
	
	@RequestMapping(value= "/toAddBook", method = RequestMethod.POST)
	public ModelAndView toAddBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("add_book");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		modelAndView.addObject("userId", userId);
		
		return modelAndView;
	}
}

	