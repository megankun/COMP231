package com.storybook.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.storybook.entity.Book;
import com.storybook.entity.Story;
import com.storybook.entity.Story_BookCharacter;
import com.storybook.entity.Story_Location;
import com.storybook.entity.User;



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

		// get the list of stories/chapters for the selected book by id
		Query chapters = em.createQuery("select s from Story s where s.bookId = :param").setParameter("param", Integer.parseInt(bookId));
		List<Story> chapterList = chapters.getResultList();


 		Query query1 = em.createQuery("select u from User u where u.userId = :param").setParameter("param", Integer.parseInt(userId));
 		User user = (User) query1.getResultList().get(0);
		
		em.close();
		
		modelAndView.addObject("chapterList", chapterList);
		modelAndView.addObject("bookId", bookId);
		modelAndView.addObject("userId", userId);

		modelAndView.addObject("userType", user.getUserType());
		
		// return to list of stories for that book
		return modelAndView;
	}

	
	@RequestMapping(value= "/loadEditStory")
	public ModelAndView loadEditStory(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView modelAndView = new ModelAndView("edit_story");
		
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();
		
		// get the book id, user id, and story id from the view
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int storyId = Integer.parseInt(request.getParameter("storyId"));
		
		// get the specific story from the table Story by the bookId and storyId
		Query chapters = em.createQuery("select s from Story s where s.bookId = :bookId and s.storyId = :storyId")
				.setParameter("bookId", bookId)
				.setParameter("storyId", storyId);
		List<Story> chapterList = chapters.getResultList();
		
		// add previous values to the view
		for (Story chapter : chapterList)
		{
			modelAndView.addObject("chapterTitle", chapter.getChapterTitle());
			modelAndView.addObject("note", chapter.getNote());
			modelAndView.addObject("createdAt", chapter.getCreated_at());
		}
		
		// get the characters previously in the selected story and add the characters to the view
		Query characters = em.createQuery("select c from Story_BookCharacter c where c.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_BookCharacter> characterList = characters.getResultList();
		String value = "";
		for (Story_BookCharacter chara : characterList)
		{
			value += chara.getCharacterId() + ",";
		}
		modelAndView.addObject("checkedCharacterIds", value);
		
		// get the locations previously in the selected story and add the locations to the view
		Query locations = em.createQuery("select l from Story_Location l where l.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_Location> locationList = locations.getResultList();
		value = "";
		for (Story_Location loc : locationList)
		{
			value += loc.getLocationId() + ",";
		}
		modelAndView.addObject("checkedLocationIds", value);
		
		// get the book id from the table Book and add the book id to the view
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

		// Select Story Id, book id, chapter title, note, locations, characters, and created at time
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
		em.clear();          
        em.getTransaction().begin();


		// remove all Story_Location for the specific story
		Query locations = em.createQuery("select l from Story_Location l where l.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_Location> locationList = locations.getResultList();
		
		for (Story_Location loc : locationList){
			if (em.contains(loc)){
				em.remove(loc);
				em.getTransaction().commit();
				em.clear();          
		        em.getTransaction().begin();
			}
		}
		
		// remove all Story_BookCharacter for the specific story
		Query characters = em.createQuery("select c from Story_BookCharacter c where c.storyId = :storyId")
				.setParameter("storyId", storyId);
		List<Story_BookCharacter> characterList = characters.getResultList();
		
		for (Story_BookCharacter chara : characterList){
			if (em.contains(chara)){
				em.remove(chara);
				em.getTransaction().commit();
				em.clear();          
		        em.getTransaction().begin();
			}
		}

		em.clear();
		
		// update Story_Location
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

		for(int i = 0; i < tempCharacterIdsArray.length; i++) {
			Story_BookCharacter story_bookCharacter = new Story_BookCharacter();
			story_bookCharacter.setStoryId(storyId);
			story_bookCharacter.setCharacterId(Integer.parseInt(tempCharacterIdsArray[i]));

			em.persist(story_bookCharacter);
			em.getTransaction().commit();
			em.clear();          
	        em.getTransaction().begin();
		}

		// get the list of chapters by bookId
		Query chapters = em.createQuery("select s from Story s where s.bookId = :param")
				.setParameter("param", bookId);
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
		
		// get the user id and add to the view
		int userId = Integer.parseInt(request.getParameter("userId"));
		modelAndView.addObject("userId", userId);
		
		return modelAndView;
	}

	@RequestMapping(value= "/uploadDraft")
	public ModelAndView uploadDraft(String userId, String bookId, String storyId)
	{
		ModelAndView modelAndView = new ModelAndView("upload_draft");

		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();

		em.getTransaction().begin();
		
		Query queryForBook = em.createQuery("select b from Book b where b.bookId = :bookId")
				.setParameter("bookId", Integer.parseInt(bookId));
		Book book = (Book)queryForBook.getResultList().get(0);

		Query queryForStory = em.createQuery("select s from Story s where s.storyId = :storyId")
				.setParameter("storyId", Integer.parseInt(storyId));
		Story story = (Story)queryForStory.getResultList().get(0);

		em.close();
		
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("book", book);
		modelAndView.addObject("story", story);
		
		return modelAndView;
	}
	
	/**
	 * Upload single file 
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView uploadFileHandler(@RequestParam("name") String name,
									@RequestParam("file") MultipartFile file,
									String userId, String bookId, String storyId) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator 
						+ userId+"_"+bookId+"_"+storyId+"_"+name);
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				// Saved File Location=/Users/kyungjin/Downloads/Semester 5/COMP303 - Java EE Programming/Utilties/apache-tomcat-9.0.24/tmpFiles/
			} catch (Exception e) {
				// fail to upload
			}
		} else {
			// fail to upload, file empty
		}
		return chapter(userId, bookId);
	}
	
	//draftList
	@RequestMapping(value= "/draftList")
	public ModelAndView draftList(String userId, String bookId, String storyId)
	{
		ModelAndView modelAndView = new ModelAndView("draft_list");
		
		return modelAndView;
	}
	

	// Delete Story
	@RequestMapping(value= "/deleteStory")
	public ModelAndView deleteStory(String userId, String bookId, String storyId)
	{	
		// delete book include all stories, locations, characters
		factory = Persistence.createEntityManagerFactory("Storybook_Team2");
		em = factory.createEntityManager();		
		int deletedCount;
		
		// Delete Story_Location 
		em.getTransaction().begin();
		Query queryforStory_Location = em.createQuery("delete from Story_Location sl where sl.storyId = :storyId");
		deletedCount = queryforStory_Location.setParameter("storyId", Integer.parseInt(storyId)).executeUpdate();		
		em.getTransaction().commit();
		em.clear();
		
		// Delete Story_BookCharacter
		em.getTransaction().begin();	
		Query queryforStory_BookCharacter = em.createQuery("delete from Story_BookCharacter sc where sc.storyId = :storyId");
		deletedCount = queryforStory_BookCharacter.setParameter("storyId", Integer.parseInt(storyId)).executeUpdate();
		
		em.getTransaction().commit();
		em.clear();

		// Delete Story
		em.getTransaction().begin();
		Query queryforStory = em.createQuery("delete from Story s where s.storyId = :storyId");
		deletedCount = queryforStory.setParameter("storyId", Integer.parseInt(storyId)).executeUpdate();
		em.getTransaction().commit();		
		
		em.close();

		
		return new ModelAndView("redirect:/chapterList?userId=" + userId + "&bookId=" + bookId);
	}
}

	