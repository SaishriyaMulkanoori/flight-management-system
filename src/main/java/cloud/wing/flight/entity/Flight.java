package cloud.wing.flight.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import cloud.wing.admin.entity.FlightManager;

public class Flight {

	private int flightId;
	private String flightNumber;
	private Airline airline;
	private String departureAirport;
	private String arrivalAirport;
	private Timestamp departureDate;
	private Timestamp arrivalDate;
	private int availableSeatsEconomy;
	private int availableSeatsBusiness;
	private int totalSeatsEconomy;
	private int totalSeatsBusiness;
	private BigDecimal priceEconomy;
	private BigDecimal priceBusiness;
	private FlightStatus flightStatus;
	private FlightManager flightManager;
	private boolean isDelete;

	public Flight() {
		super();
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
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

	public Timestamp getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Timestamp timestamp) {
		this.departureDate = timestamp;
	}

	public Timestamp getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Timestamp timestamp) {
		this.arrivalDate = timestamp;
	}

	public int getAvailableSeatsEconomy() {
		return availableSeatsEconomy;
	}

	public void setAvailableSeatsEconomy(int availableSeatsEconomy) {
		this.availableSeatsEconomy = availableSeatsEconomy;
	}

	public int getAvailableSeatsBusiness() {
		return availableSeatsBusiness;
	}

	public void setAvailableSeatsBusiness(int availableSeatsBusiness) {
		this.availableSeatsBusiness = availableSeatsBusiness;
	}

	public int getTotalSeatsEconomy() {
		return totalSeatsEconomy;
	}

	public void setTotalSeatsEconomy(int totalSeatsEconomy) {
		this.totalSeatsEconomy = totalSeatsEconomy;
	}

	public int getTotalSeatsBusiness() {
		return totalSeatsBusiness;
	}

	public void setTotalSeatsBusiness(int totalSeatsBusiness) {
		this.totalSeatsBusiness = totalSeatsBusiness;
	}

	public BigDecimal getPriceEconomy() {
		return priceEconomy;
	}

	public void setPriceEconomy(BigDecimal priceEconomy) {
		this.priceEconomy = priceEconomy;
	}

	public BigDecimal getPriceBusiness() {
		return priceBusiness;
	}

	public void setPriceBusiness(BigDecimal priceBusiness) {
		this.priceBusiness = priceBusiness;
	}

	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

	public FlightManager getFlightManager() {
		return flightManager;
	}

	public void setFlightManager(FlightManager flightManager) {
		this.flightManager = flightManager;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean delete) {
		isDelete = delete;
	}

	@Override

	public String toString() {

		return "Flight{" +

				"flightId=" + flightId +

				", flightNumber='" + flightNumber + '\'' +

				", airline=" + airline +

				", departureAirport='" + departureAirport + '\'' +

				", arrivalAirport='" + arrivalAirport + '\'' +

				", departureDate=" + departureDate +

				", arrivalDate=" + arrivalDate +

				", availableSeatsEconomy=" + availableSeatsEconomy +

				", availableSeatsBusiness=" + availableSeatsBusiness +

				", totalSeatsEconomy=" + totalSeatsEconomy +

				", totalSeatsBusiness=" + totalSeatsBusiness +

				", priceEconomy=" + priceEconomy +

				", priceBusiness=" + priceBusiness +

				", flightStatus=" + flightStatus +

				", isDelete=" + isDelete +

				'}';
	}

}
