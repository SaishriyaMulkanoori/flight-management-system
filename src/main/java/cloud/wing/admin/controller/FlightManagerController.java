package cloud.wing.admin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cloud.wing.admin.entity.FlightManager;
import cloud.wing.admin.service.FlightManagerService;
import cloud.wing.payment.service.EmailService;
import cloud.wing.utility.Utils;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/flightManager")
public class FlightManagerController {

	@Autowired
	FlightManagerService flightManagerService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public String openHomePage() {

		return "flightManager/flightManager_login";
	}

	@GetMapping("/signup")
	public ModelAndView loginPage() {

		ModelAndView modelAndView = new ModelAndView("/flightManager/flightManager_signup");
		return modelAndView;
	}

	@PostMapping("/registration")
	public String customerRegistration(@RequestParam String name, @RequestParam String mobile,
			@RequestParam String email, @RequestParam String gender, @RequestParam String password,
			@RequestParam String cpassword, @RequestParam String username, Model model

	) {

		if (password.equals(cpassword)) {
			FlightManager flightManager = new FlightManager(name, mobile, email, password, gender, username);

			String message = flightManagerService.registerFlightManager(flightManager);
			model.addAttribute("message", message);
		} else {
			model.addAttribute("error", "Password and confirm not matched");
		}

		String destination = "flightManager/flightManager_signup";

		return destination;

	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String email, @RequestParam String password, ModelAndView mView,
			HttpSession session) {

		Optional<FlightManager> fm = flightManagerService.isUserExists(email, password);

		if (fm.isPresent()) {

			FlightManager manager = fm.get();

			if (manager.isApproved()) {
				session.setAttribute("loggedInFm", manager);
				mView.addObject("message", "");
				return new ModelAndView("redirect:/flightManager/home");

			} else {
				mView.setViewName("flightManager/flightManager_login");
				mView.addObject("error", "Your application is Pending");

			}

		} else {
			mView.setViewName("flightManager/flightManager_login");
			mView.addObject("error", "Invalid credentials");

		}

		return mView;

	}

	@GetMapping("/home")
	public ModelAndView showHomePage(HttpSession session, ModelAndView modelAndView) {
		FlightManager fm = (FlightManager) session.getAttribute("loggedInFm");

		if (fm == null) {
			return new ModelAndView("redirect:/flightManager/");
		}

		int flightCount = flightManagerService.getFlightCount();
		int passsengerCount = flightManagerService.getPassengerCount();
		double revenue = flightManagerService.totalRevenue();
		int activeFlight = flightManagerService.activeFlightCount();

		Map<String, Integer> map = new HashMap<>();
		map.put("flightCount", flightCount);
		map.put("passsengerCount", passsengerCount);
		map.put("activeFlight", activeFlight);
		map.put("revenue", (int) revenue);
		modelAndView.addObject("data", map);

		modelAndView.setViewName("flightManager/flightManager_dashboard");

		return modelAndView;
	}

	@GetMapping("/changeAuth/{adminId}")
	public String changeAuth(@PathVariable int adminId) {
		flightManagerService.modifyAuthority(adminId);
		return "redirect:/bussinessOwner/viewFlightManager";
	}

	@GetMapping("/profile")
	public String openProfilePage(HttpSession session, Model model) {

		FlightManager flightManager = (FlightManager) session.getAttribute("loggedInFm");

		if (flightManager == null) {

			return "redirect:/flightManager/";
		}

		Optional<FlightManager> flightManagerData = flightManagerService.findManagerByEmail(flightManager.getEmail());
		FlightManager manager = flightManagerData.get();

		model.addAttribute("managerLogin", manager);

		return "flightManager/profile";
	}

	@PostMapping("/update")
	public String opensavePage(@RequestParam String name, @RequestParam String username, @RequestParam String gender,
			@RequestParam String mobile_number, Model model, HttpSession session) {
		;
		FlightManager manager = (FlightManager) session.getAttribute("loggedInFm");

		if (manager == null) {
			return "redirect:/flightmanager/";
		}

		int id = manager.getId();

		FlightManager flightManager = new FlightManager(id, name, mobile_number, gender, username);

		@SuppressWarnings("unused")
		String message = flightManagerService.updateFlightManager(flightManager);
		model.addAttribute("message", "Profile Updated Successfully");

		return "redirect:/flightManager/profile";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelAndView mView) {
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "flightManager/forgotPassword";
	}

	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam String email, Model model, HttpSession session) {

		Optional<FlightManager> flightManager = flightManagerService.findManagerByEmail(email);
		if (flightManager.isPresent()) {
			String otp = Utils.generateOTP();
			session.setAttribute("email", email);
			session.setAttribute("otp", otp);
			try {

				emailService.sendForgotPasswordOtp(email, otp);

			} catch (Exception e) {

				model.addAttribute("error", "Failed to send Otp " + e.getMessage());
			}
			return "flightManager/verifyOtp";
		} else {

			model.addAttribute("error", "user not found! ");
			return "flightManager/forgotPassword";
		}

	}

	@PostMapping("/verify_otp")
	public String Verifyotp(@RequestParam String otp, HttpSession session, Model model) {

		String sendOtp = (String) session.getAttribute("otp");

		if (otp.equals(sendOtp)) {
			return "flightManager/changePassword";
		} else {
			model.addAttribute("error", "invalid otp! Please enter a valid otp");
			return "flightManager/verifyOtp";
		}
	}

	@PostMapping("/update-password")
	public String updatePassword(@RequestParam String password, @RequestParam String cpassword, HttpSession session,
			Model model) {
		String email = (String) session.getAttribute("email");
		if (!password.equals(cpassword)) {
			model.addAttribute("error", "Password and confirm password not match");
			return "flightManager/changePassword";
		}

		boolean status = flightManagerService.updatePassword(email, password);

		if (status) {
			model.addAttribute("message", "Password changed successfully");
		} else {
			model.addAttribute("error", "Failed to change password");
		}

		return "flightManager/flightManager_login";
	}

}
