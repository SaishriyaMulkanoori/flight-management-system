package cloud.wing.booking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import cloud.wing.passenger.entity.Passenger;

public class Booking {

	
	private Integer bookingId;
    private Integer passengerId;
    private Integer flightId;
	private LocalDateTime bookingDate;
    private Integer numberOfSeats;
    private String seatClass;
    private BigDecimal totalAmount;
    private String status;
    private String bookingRef;
    private List<String> passengerNames;
    
    
    
    public List<String> getPassengerNames() {
		return passengerNames;
	}


	public void setPassengerNames(List<String> passengerNames) {
		this.passengerNames = passengerNames;
	}


	private Passenger passenger;
    
    
    public Booking(Integer flightId, Integer numberOfSeats, String seatClass, BigDecimal totalAmount) {
		super();
		this.flightId = flightId;
		this.numberOfSeats = numberOfSeats;
		this.seatClass = seatClass;
		this.totalAmount = totalAmount;
	}
    
    
    
    
    
    
	public Booking(Integer flightId, Integer numberOfSeats, String seatClass, BigDecimal totalAmount,
			List<String> passengerNames) {
		super();
		this.flightId = flightId;
		this.numberOfSeats = numberOfSeats;
		this.seatClass = seatClass;
		this.totalAmount = totalAmount;
		this.passengerNames = passengerNames;
	}


	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", passengerId=" + passengerId + ", flightId=" + flightId
				+ ", bookingDate=" + bookingDate + ", numberOfSeats=" + numberOfSeats + ", seatClass=" + seatClass
				+ ", totalAmount=" + totalAmount + ", status=" + status + " ]" + bookingRef;
	}



	public Booking(Integer passengerId, Integer flightId, Integer numberOfSeats, BigDecimal totalAmount ) {
		super();
		this.passengerId = passengerId;
		this.flightId = flightId;
		this.numberOfSeats = numberOfSeats;
		this.totalAmount = totalAmount;
		
	}



	public String getBookingRef() {
		return bookingRef;
	}



	public void setBookingRef(String bookingRef) {
		this.bookingRef = bookingRef;
	}



	public Booking() {
		super();
	}



	public Booking(Integer bookingId, Integer passengerId, Integer flightId, LocalDateTime bookingDate,
			Integer numberOfSeats, String seatClass, BigDecimal totalAmount, String status,String bookingRef) {
		super();
		this.bookingId = bookingId;
		this.passengerId = passengerId;
		this.flightId = flightId;
		this.bookingDate = bookingDate;
		this.numberOfSeats = numberOfSeats;
		this.seatClass = seatClass;
		this.totalAmount = totalAmount;
		this.status = status;
		this.bookingRef=bookingRef;
		
	}



	public Booking(Integer passengerId, Integer flightId, LocalDateTime bookingDate, Integer numberOfSeats,
			String seatClass, BigDecimal totalAmount, String status) {
		super();
		this.passengerId = passengerId;
		this.flightId = flightId;
		this.bookingDate = bookingDate;
		this.numberOfSeats = numberOfSeats;
		this.seatClass = seatClass;
		this.totalAmount = totalAmount;
		this.status = status;
	}



	public Integer getBookingId() {
		return bookingId;
	}



	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}



	public Integer getPassengerId() {
		return passengerId;
	}



	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}



	public Integer getFlightId() {
		return flightId;
	}



	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}



	public LocalDateTime getBookingDate() {
		return bookingDate;
	}



	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}



	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}



	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}



	public String getSeatClass() {
		return seatClass;
	}



	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}



	public BigDecimal getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}


	public Passenger getPassenger() {
		return passenger;
	}


	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}


}
