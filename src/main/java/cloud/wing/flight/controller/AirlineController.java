package cloud.wing.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import cloud.wing.flight.entity.Airline;
import cloud.wing.flight.service.AirlineService;
import exception.DuplicateAirlineException;

import java.util.List;

@Controller

@RequestMapping("/airline")

public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	@GetMapping("/list")

	public String getAllAirlines(Model model) {

		System.out.println("in get all Airlines");
		List<Airline> airlines = airlineService.getAllAirlines();
		model.addAttribute("airlines", airlines);

		return "/airline/airlineList"; // View name to display airlines

	}

	@GetMapping("/add")

	public String showAddAirlineForm(Model model) {

		model.addAttribute("airline", new Airline());

		return "/airline/addAirline"; // View name for adding a new airline

	}

	@PostMapping("/add")

	public String addAirline(@ModelAttribute("airline") Airline airline,Model model) {
        try {
		airlineService.addAirline(airline);
        
		return "redirect:/airline/list";
        }catch(DuplicateAirlineException ex) {
        	
        	model.addAttribute("error", ex.getMessage());
        	model.addAttribute("airlines", airlineService.getAllAirlines());
        	return "/airline/addAirline";
        }
	}

	@GetMapping("/edit/{id}")

	public String showEditAirlineForm(@PathVariable("id") int airlineId, Model model) {

		System.out.println("in edit Airlines");
		Airline airline = airlineService.getAirlineById(airlineId);

		model.addAttribute("airline", airline);

		return "/airline/editAirline";

	}
	
	@PostMapping("/update")

	public String updateAirline(@ModelAttribute Airline airline,Model model) {
        try {
		airlineService.updateAirline(airline);

		return "redirect:/airline/list";
        }catch(DuplicateAirlineException ex) {
        	
        	model.addAttribute("error", ex.getMessage());
        	model.addAttribute("airlines", airlineService.getAllAirlines());
        	return "/airline/editAirline";
        }

	}
	
	@GetMapping("/remove/{id}")

	public String removeAirline(@PathVariable("id") int airlineId) {

		airlineService.removeAirline(airlineId);

		return "redirect:/airline/list";

	}

}
