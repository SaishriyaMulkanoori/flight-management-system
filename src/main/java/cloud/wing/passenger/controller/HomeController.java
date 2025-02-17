package cloud.wing.passenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView home(ModelAndView mView, HttpSession sesssion) {

		sesssion.getAttribute("loggedInUser");

		mView.setViewName("passenger/dashboard");
		return mView;

	}

}
