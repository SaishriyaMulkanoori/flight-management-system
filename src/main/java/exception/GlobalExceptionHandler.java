package exception;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import cloud.wing.flight.entity.Airline;
import cloud.wing.flight.entity.Flight;
import cloud.wing.flight.service.AirlineService;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateFlightNumberException.class)
	public String handleUniqueFlightNumberException(DuplicateFlightNumberException ex, Model model,
			AirlineService airlineService) {

		model.addAttribute("error", ex.getMessage());
		List<Airline> airlines = airlineService.getAllAirlines();
		model.addAttribute("airlines", airlines);
		model.addAttribute("flight", new Flight());

		return "/flight/addFlight";
	}

	@ExceptionHandler(DuplicateAirlineException.class)
	public String handleDuplicateAirlineException(DuplicateAirlineException ex, Model model,
			AirlineService airlineService) {

		model.addAttribute("error", ex.getMessage());
		List<Airline> airlines = airlineService.getAllAirlines();
		model.addAttribute("airlines", airlines);
		model.addAttribute("airline", new Airline());

		return "/airline/addAirline";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, Model model) {
		ModelAndView modelAndView = new ModelAndView("globalError");
		model.addAttribute("message", ex.getMessage());
		return modelAndView;
	}

	@ExceptionHandler(exception = SerialException.class)
	public String handleSerialException(SerialException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	IOException
	@ExceptionHandler(exception = IOException.class)
	public String handleIOException(IOException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	SQLException
	@ExceptionHandler(exception = SQLException.class)
	public String handleSQLException(SQLException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	NullPointerException
	@ExceptionHandler(exception = NullPointerException.class)
	public String handleNullPointerException(NullPointerException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	IllegalArgumentException
	@ExceptionHandler(exception = IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	MultipartException (related to file upload issues)
	@ExceptionHandler(exception = MultipartException.class)
	public String handleMultipartException(MultipartException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	HttpClientErrorException (related to REST template errors)
	@ExceptionHandler(exception = HttpClientErrorException.class)
	public String handleHttpClientErrorException(HttpClientErrorException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	HttpServerErrorException (related to REST template errors)
	@ExceptionHandler(exception = HttpServerErrorException.class)
	public String handleHttpServerErrorException(HttpServerErrorException exception) {
		exception.printStackTrace();
		return "globalError";
	}

//    	ResourceAccessException (related to REST template errors)
	@ExceptionHandler(exception = ResourceAccessException.class)
	public String handleResourceAccessException(ResourceAccessException exception) {
		exception.printStackTrace();
		return "globalError";
	}

	@ExceptionHandler(exception = EmptyResultDataAccessException.class)
	public String handleMultipartException(EmptyResultDataAccessException exception) {
		exception.printStackTrace();
		return "globalError";
	}

	@ExceptionHandler(exception = DataAccessException.class)
	public String handleDataAccessException(DataAccessException exception, Model model) {
		exception.printStackTrace();
		return "globalError";
	}

	@ExceptionHandler(exception = NoResourceFoundException.class)
	public String handleNoResourceFoundException(NoResourceFoundException exception) {
		exception.printStackTrace();
		return "globalError";
	}

	@ExceptionHandler(exception = SQLIntegrityConstraintViolationException.class)
	public String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
		exception.printStackTrace();
		return "globalError";
	}
}
