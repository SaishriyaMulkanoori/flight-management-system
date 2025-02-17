package cloud.wing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cloud.wing.admin.entity.FlightManager;
import cloud.wing.admin.service.FlightManagerService;
import cloud.wing.passenger.entity.Passenger;
import cloud.wing.passenger.service.PassengerService;

@SpringBootTest
class CloudWingApplicationTests {

	@Autowired
	PassengerService passengerService;
	
	@Autowired
	FlightManagerService flightManagerService;
	
	
	
	@Test
	void contextLoads() {
	}

	
	  @Test
	    public void testAddPassenger() {
	        Passenger passenger = new Passenger();
	        passenger.setName("John Doe");
	        passenger.setEmail("john.doe@example.com");
	        passenger.setUsername("johndoe");
	        passenger.setGender("Male");
	        passenger.setDateOfBirth("1990-01-01");
	        passenger.setMobileNumber("1234567890");
	        passenger.setPassport_salt("randomSalt");
	        passenger.setPassword_hash("hashedPassword");
	       
	       
	    

	        passengerService.registerPassenger(passenger);

	        assertEquals(true, passengerService.findUserByEmail("john.doe@example.com").isPresent());
	       
	    }
	  
	  @Test
	    public void testAddFlightManager() {
	        FlightManager flightManager = new FlightManager();
	        flightManager.setName("Jane Doe");
	        flightManager.setEmail("jane.doe@example.com");
	        flightManager.setPassword_salt("randomSalt");
	        flightManager.setPassword_hash("hashedPassword");
	        flightManager.setMobile("9876543210");
	        flightManager.setUsername("janedoe");
	        flightManager.setGender("Female");
	        

	        flightManagerService.registerFlightManager(flightManager);

	        assertEquals(true, flightManagerService.findManagerByEmail("jane.doe@example.com").isPresent());
	        assertEquals("jane.doe@example.com", flightManagerService.findManagerByEmail("jane.doe@example.com").get().getEmail());
	    }
	  
	  
}
