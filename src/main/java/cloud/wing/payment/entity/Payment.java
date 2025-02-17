package cloud.wing.payment.entity;

import java.math.BigDecimal;
//import java.sql.Date;
import java.time.LocalDateTime;

import cloud.wing.passenger.entity.Passenger;

public class Payment {

	int paymentId;
	int bookingId;
	String paymentMethod;
	String cardNumber;
	String cardHoldername;
	String expiryDate;
	String PaymentStatus;
	LocalDateTime paymentDate;
	BigDecimal totalAmount;
	BigDecimal discountAmonut;
	Passenger passenger;

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Payment() {
		super();
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHoldername() {
		return cardHoldername;
	}

	public void setCardHoldername(String cardHoldername) {
		this.cardHoldername = cardHoldername;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPaymentStatus() {
		return PaymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getDiscountAmonut() {
		return discountAmonut;
	}

	public void setDiscountAmonut(BigDecimal discountAmonut) {
		this.discountAmonut = discountAmonut;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", bookingId=" + bookingId + ", paymentMethod=" + paymentMethod
				+ ", cardNumber=" + cardNumber + ", cardHoldername=" + cardHoldername + ", expiryDate=" + expiryDate
				+ ", PaymentStatus=" + PaymentStatus + ", paymentDate=" + paymentDate + ", totalAmount=" + totalAmount
				+ ", discountAmonut=" + discountAmonut + "]";
	}

}
