  package cloud.wing.flight.controller;

	import java.beans.PropertyEditorSupport;
	import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cloud.wing.admin.entity.FlightManager;
import cloud.wing.admin.service.BussinessOwnerService;
import cloud.wing.admin.service.FlightManagerService;
import cloud.wing.flight.entity.Airline;
import cloud.wing.flight.entity.Flight;
import cloud.wing.flight.service.AirlineService;
import cloud.wing.flight.service.FlightService;
import exception.DuplicateFlightNumberException;
import jakarta.servlet.http.HttpSession;

@Controller

@RequestMapping("/flight")

public class FlightController {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private FlightManagerService flightManagerservice;
	
	@Autowired
	private BussinessOwnerService bussinessOwnerService;

	@Autowired

	private FlightService flightService;

	@InitBinder

	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {

			@Override

			public void setAsText(String text) throws IllegalArgumentException {

				try {

					setValue(new Timestamp(dateFormat.parse(text).getTime()));

				} catch (ParseException e) {

					throw new IllegalArgumentException("Invalid date format", e);

				}

			}

		});

	}

	@GetMapping("/list")

	public String getAllFlights(Model model) {

		List<Flight> flights = flightService.getAllFlights();
		Collections.sort(flights, new Comparator<Flight>() {
            @Override
            public int compare(Flight f1, Flight f2) {
                return Integer.compare(f2.getFlightId(), f1.getFlightId());
            }
        });
	
		model.addAttribute("flights",flights );
		return "/flight/flightList";

	}

	@GetMapping("/add")

	public String showAddFlightForm(Model model) {

		List<Airline> airlines = airlineService.getAllAirlines();
		model.addAttribute("airlines", airlines);

		List<FlightManager> flightManagers = bussinessOwnerService.getAllFlightManager();

		model.addAttribute("flightManagers", flightManagers);

		model.addAttribute("flight", new Flight());

		return "flight/addFlight"; 

	}

	@PostMapping("/add")

	public String addFlight(@ModelAttribute("flight") Flight flight, Model model) {

		Airline airline = airlineService.getAirlineById(flight.getAirline().getAirlineId());
		FlightManager flightManager = flightManagerservice.getFlightManagerById(flight.getFlightManager().getId());
		flight.setAirline(airline);
		flight.setAvailableSeatsBusiness(flight.getTotalSeatsBusiness());
		flight.setAvailableSeatsEconomy(flight.getTotalSeatsEconomy());
		flight.setFlightManager(flightManager);
		try {
			flightService.addFlight(flight);
			return "redirect:/flight/list"; // Redirect to flight list page
		} catch (DuplicateFlightNumberException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("airlines", airlineService.getAllAirlines());
			model.addAttribute("flightManagers", bussinessOwnerService.getAllFlightManager());
			model.addAttribute("flight", flight);

			return "flight/addFlight";
		}

	}

	@GetMapping("/edit/{id}")

	public String showEditFlightForm(@PathVariable("id") int flightId, Model model) {

		Flight flight = flightService.getFlightById(flightId);

		List<Airline> airlines = airlineService.getAllAirlines();
		List<FlightManager> flightManager = bussinessOwnerService.getAllFlightManager();
		
			

		model.addAttribute("flightManager", flightManager);
		
		model.addAttribute("flight", flight);
		System.out.println(flight);
		model.addAttribute("airlines", airlines);

		return "/flight/editFlight"; // Form view to edit an existing flight

	}

	@PostMapping("/update")

	public String updateFlight(@ModelAttribute("flight") Flight flight) {

		try {
			flightService.updateFlight(flight);
			return "redirect:/flight/list";
		} catch (DuplicateFlightNumberException e) {

			return "flight/editFlight";
		}

	}

	@GetMapping("/delete/{id}")

	public String deleteFlight(@PathVariable("id") int flightId) {

		flightService.deleteFlight(flightId);

		return "redirect:/flight/list"; 

	}
	
	
	@GetMapping("/searchFlights")
	public String searchFlights(@RequestParam String departureAirport, @RequestParam String arrivalAirport,
			@RequestParam Date departureDate, Model model, HttpSession session) {

		

		List<Flight> flights = flightService.getSearchFlight(departureAirport, arrivalAirport, departureDate);

		

		model.addAttribute("flights", flights);

		
		return "passenger/availableFlights";

	}
	
	
	
	
	 @GetMapping("/flightReport")
	  public String bookingReport( @RequestParam(required = false) Integer airlineId, Model model ) {
		  List<Flight>flights= null;
		  
		  List<Airline> airlines= airlineService.getAllAirlines();
		
		  System.out.println("airlinr id "+ airlineId);
		  if(!airlines.isEmpty()) {
			  model.addAttribute("airlines", airlines);
		  }
		  if(airlineId!=null) {
			  flights=flightService.getAllFlightByAirlineId(airlineId);
			
		  }
		  else {
			flights=flightService.getAllFlights();
		  }
		  
			  model.addAttribute("flights",flights);
		  
		  
		 
		  
		return "bussinessOwner/flightReport";
		  
	  }

}





