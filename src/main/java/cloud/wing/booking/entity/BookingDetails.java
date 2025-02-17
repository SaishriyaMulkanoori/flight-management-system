package cloud.wing.booking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDetails {

	
	 private int bookingId;
	    private String airlineName;
	    private String flightNumber;
	    private String departureAirport;
	    private String arrivalAirport;
	    private LocalDateTime departureDate;
	    private LocalDateTime arrivalDate;
	    private int numberOfSeats;
	    private String seatClass;
	    private String passengerNames;
	    private String bookingStatus;
	    private BigDecimal totalAmount;
	    private String flightStatus;
	    
	    
	    
	    
	    
	    
		public BookingDetails(int bookingId, String airlineName, String flightNumber, String departureAirport,
				String arrivalAirport, LocalDateTime departureDate, LocalDateTime arrivalDate, int numberOfSeats,
				String seatClass, String passengerNames, String bookingStatus, BigDecimal totalAmount,String flightStatus) {
			super();
			this.bookingId = bookingId;
			this.airlineName = airlineName;
			this.flightNumber = flightNumber;
			this.departureAirport = departureAirport;
			this.arrivalAirport = arrivalAirport;
			this.departureDate = departureDate;
			this.arrivalDate = arrivalDate;
			this.numberOfSeats = numberOfSeats;
			this.seatClass = seatClass;
			this.passengerNames = passengerNames;
			this.bookingStatus = bookingStatus;
			this.totalAmount = totalAmount;
			this.flightStatus=flightStatus;
		}


		public BookingDetails() {
			super();
		}


		public int getBookingId() {
			return bookingId;
		}


		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}


		public String getAirlineName() {
			return airlineName;
		}


		public void setAirlineName(String airlineName) {
			this.airlineName = airlineName;
		}


		
		
		public String getFlightStatus() {
			return flightStatus;
		}


		public void setFlightStatus(String flightStatus) {
			this.flightStatus = flightStatus;
		}


		public String getFlightNumber() {
			return flightNumber;
		}


		public void setFlightNumber(String flightNumber) {
			this.flightNumber = flightNumber;
		}


		public String getDepartureAirport() {
			return departureAirport;
		}


		public void setDepartureAirport(String departureAirport) {
			this.departureAirport = departureAirport;
		}


		public String getArrivalAirport() {
			return arrivalAirport;
		}


		public void setArrivalAirport(String arrivalAirport) {
			this.arrivalAirport = arrivalAirport;
		}


		public LocalDateTime getDepartureDate() {
			return departureDate;
		}


		public void setDepartureDate(LocalDateTime departureDate) {
			this.departureDate = departureDate;
		}


		public LocalDateTime getArrivalDate() {
			return arrivalDate;
		}


		public void setArrivalDate(LocalDateTime arrivalDate) {
			this.arrivalDate = arrivalDate;
		}


		public int getNumberOfSeats() {
			return numberOfSeats;
		}


		public void setNumberOfSeats(int numberOfSeats) {
			this.numberOfSeats = numberOfSeats;
		}


		public String getSeatClass() {
			return seatClass;
		}


		public void setSeatClass(String seatClass) {
			this.seatClass = seatClass;
		}


		


		public String getPassengerNames() {
			return passengerNames;
		}


		public void setPassengerNames(String passengerNames) {
			this.passengerNames = passengerNames;
		}


		public String getBookingStatus() {
			return bookingStatus;
		}


		public void setBookingStatus(String bookingStatus) {
			this.bookingStatus = bookingStatus;
		}


		public BigDecimal getTotalAmount() {
			return totalAmount;
		}


		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}


		@Override
		public String toString() {
			return "BookingDetails [bookingId=" + bookingId + ", airlineName=" + airlineName + ", flightNumber="
					+ flightNumber + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
					+ ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate + ", numberOfSeats="
					+ numberOfSeats + ", seatClass=" + seatClass + ", passengerNames=" + passengerNames
					+ ", bookingStatus=" + bookingStatus + ", totalAmount=" + totalAmount + ", flightStatus="
					+ flightStatus + "]";
		}
	    
		
	    
	    
}
