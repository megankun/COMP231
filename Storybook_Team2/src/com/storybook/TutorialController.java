package com.storybook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TutorialController {

	@RequestMapping(value= "/tutorial")
	public ModelAndView tutorial(String userId) {
		ModelAndView modelAndView = new ModelAndView("tutorial");
		
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
}