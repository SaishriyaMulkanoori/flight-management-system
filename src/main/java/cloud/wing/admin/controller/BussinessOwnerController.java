package cloud.wing.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cloud.wing.admin.entity.BussinessOwner;
import cloud.wing.admin.entity.FlightManager;
import cloud.wing.admin.service.BussinessOwnerService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/bussinessOwner")
public class BussinessOwnerController {

	@Autowired
	BussinessOwnerService bussinessOwnerService;

	@GetMapping("/")
	public String openLogIn() {
		return "bussinessOwner/login";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String email, @RequestParam String password, ModelAndView mView,
			HttpSession session) {

		Optional<BussinessOwner> bOwner = bussinessOwnerService.isUserExists(email, password);

		if (bOwner.isPresent()) {
			BussinessOwner bussinessOwner = bOwner.get();
			session.setAttribute("owner", bussinessOwner);
			mView.addObject("message", "login");
			return new ModelAndView("redirect:/bussinessOwner/home");

		} else {
			mView.setViewName("bussinessOwner/login");
			mView.addObject("error", "Invalid credentials");

		}

		return mView;
	}

	@GetMapping("/home")

	public ModelAndView showHomePage(ModelAndView mView, HttpSession session) {
		BussinessOwner bOwner = (BussinessOwner) session.getAttribute("owner");

		if (bOwner == null) {
			return new ModelAndView("redirect:/bussinessOwner/");
		}

		int flightCount = bussinessOwnerService.getFlightCount();
		int managerCount = bussinessOwnerService.getManagerCount();
		int passengerCount = bussinessOwnerService.getPassengerCount();

		Map<String, Integer> map = new HashMap<>();
		map.put("flightCount", flightCount);
		map.put("managerCount", managerCount);
		map.put("passengerCount", passengerCount);

		mView.addObject("countData", map);

		mView.setViewName("bussinessOwner/dashboard");

		return mView;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelAndView mView) {
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/viewFlightManager")
	public ModelAndView viewFlightManager(HttpSession session, ModelAndView mView) {
		BussinessOwner bOwner = (BussinessOwner) session.getAttribute("owner");

		if (bOwner == null) {
			mView.setViewName("bussinessOwner/login");
			return mView;
		}

		List<FlightManager> fm = bussinessOwnerService.getAllFlightManager();
		mView.addObject("fmList", fm);
		System.out.println(fm.get(1).toString());
		mView.setViewName("bussinessOwner/flightManagerTable");
		return mView;
	}

}
