<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title> Flight Manager Login</title>
  <link href="../css/bussinessOwner/login.css" rel="stylesheet"/>
<style>

    /* Importing Google Fonts - Poppins */


</style>
</head>
<body>
  <div class="container">
    <!-- Title section -->
    <div class="title"> Bussiness Owner Login</div>
    <div class="content">
      <!-- Registration form -->
      <form action="login"   method="post">
        <div class="user-details">
          
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" id="email" name="email" placeholder="Enter your email" required>
          </div>
       
          <!-- Input for Password -->
          <div class="input-box">
            <span class="details">Password</span>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
          </div>
          <!-- Input for Confirm Password -->
         
        </div>
       
        <!-- Submit button -->
        <div class="button">
          <input type="submit" value="Login">
        </div>
        
          
      </form>
      
        <%@include file="../error.jsp" %>
     
    </div>
  </div>
 




  
</body>
</html>