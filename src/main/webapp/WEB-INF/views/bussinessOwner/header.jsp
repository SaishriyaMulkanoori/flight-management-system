<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    header {
        background-color: #333;
        color: white;
        padding: 1rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .logo {
        display: flex;
        align-items: center;
        font-size: 1.5rem;
        font-weight: bold;
    }

    .logo-img {
        height: 40px;
        margin-right: 10px;
    }

    nav ul {
        list-style: none;
        display: flex;
        gap: 2rem;
        margin: 0;
        padding: 0;
    }

    nav a {
        color: white;
        text-decoration: none;
        font-weight: 500;
    }

    nav a:hover {
        color: #ddd;
    }
</style>

<header>
    <div class="logo">
        <img src="../images/logo.png" alt="Logo" class="logo-img" /> CloudWing
    </div>
    <nav>
        <ul>
            <li><a href="/bussinessOwner/home">Home</a></li>
           
           
         
            <li><a href="/bussinessOwner/logout">Logout</a></li>
        </ul>
    </nav>
</header>