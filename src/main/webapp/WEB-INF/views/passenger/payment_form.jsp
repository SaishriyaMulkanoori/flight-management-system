<%@page import="java.util.Date"%>

<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.time.LocalDate"%>

<%@page import="cloud.wing.booking.entity.Booking"%>

<%@page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">



<head>

  <meta charset="UTF-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Payment Form</title>

  <link href="../css/passenger/payment_form.css" rel="stylesheet" type="text/css">

  <% 

    Booking booking = (Booking) request.getAttribute("booking");

    Integer loyaltyPoints = (Integer) request.getAttribute("loyaltyPoints"); // Fetched loyalty points

    String bookingRef = booking.getBookingRef();

    double totalAmount =booking.getTotalAmount().doubleValue();

    DecimalFormat df = new DecimalFormat("#.00"); // Format amount to 2 decimal places

  %>

  <script>

    // Update the total amount dynamically when loyalty points are entered

    function updateTotalAmount(points, totalAmount) {

      const pointValue = 1; // Example: 1 point = 1 currency

      const discount = points * pointValue;

      const updatedAmount = totalAmount - discount;

      document.getElementById("finalTotalAmount").value = updatedAmount.toFixed(2); // Update UI

    }

  </script>

</head>



<body>

  <div class="payment-form">

    <h2>Payment Form</h2>

    <form action="/<%= bookingRef %>" method="post">

      <!-- Cardholder details -->

      <label for="cardholder-name">Card Holder Name:</label>

      <input type="text" id="cardholder-name" pattern="[A-Za-z\s]+" name="cardHoldername" required>



      <label for="card-number">Card Number:</label>

      <input type="text" id="card-number" pattern="\d+" maxlength="16" minlength="16" name="cardNumber" required>



      <label for="expiry-date">Expiry Date:</label>

      <input type="month" id="expiry-date" min=<%= new SimpleDateFormat("yyyy-MM").format(new Date()) %> name="expiryDate" required>



      <label for="cvv">CVV:</label>

      <input type="text"  pattern="\d+" id="cvv" maxlength="3" minlength="3" required>



      <!-- Total Amount -->

      <label for="total-amount">Total Amount:</label>

      <input type="number" id="totalAmount" name="totalAmount" value="<%= booking.getTotalAmount() %>" readonly required>



      <!-- Loyalty Points -->

      <label for="loyalty-points">Redeem Loyalty Points (Available: <%= loyaltyPoints %>):</label>

      <input type="number" id="loyalty-points" name="pointsToRedeem" min="0" max="<%= loyaltyPoints %>" 

        oninput="updateTotalAmount(this.value, <%= totalAmount %>)">



      <!-- Final Amount -->

      <label for="final-total-amount">Final Total Amount:</label>

      <input type="number" id="finalTotalAmount" readonly value="<%= df.format(totalAmount) %>">



      <button type="submit">Submit</button>

    </form>

  </div>

</body>



</html>