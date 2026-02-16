# chocolate_shop
🍫 Chocolate Shop – Spring Boot E-Commerce Application
📌 Project Description

Chocolate Shop is a simple e-commerce web application built using Spring Boot, Spring Data JPA, Thymeleaf, and MySQL.

This project allows users to browse chocolates, search by name, add items to a cart, place orders, and manage stock quantity.

The application follows the MVC architecture and demonstrates real-time stock management and session-based cart functionality.

🚀 Features

✅ View all available chocolates
✅ Search chocolates by name (case-insensitive)
✅ Add chocolates to cart
✅ Remove chocolates from cart
✅ View cart with total price calculation
✅ Checkout with customer details
✅ Order confirmation page
✅ Stock quantity reduction after order
✅ Out-of-stock validation
✅ Session-based cart management

🛠️ Technologies Used

Java

Spring Boot

Spring Data JPA

Hibernate

Thymeleaf

MySQL

HTML / CSS

Maven

🗄️ Database Design
Chocolate Table
Column	Description
id	Primary Key (Auto Increment)
name	Chocolate Name
price	Price of Chocolate
quantity	Available Stock
🔄 Application Flow

User opens home page.

Chocolates are loaded from database.

User can search chocolates by keyword.

User adds chocolates to cart.

Cart is stored using HttpSession.

Total amount is calculated dynamically.

During order confirmation:

Stock is validated.

Quantity is reduced in database.

Cart is cleared.

Success page is displayed.

🎯 Key Learning Outcomes

Implemented MVC architecture

Used Spring Data JPA query methods

Managed session-based cart

Performed stock validation logic

Implemented dynamic total calculation

Handled database updates during order confirmation

📷 Screens Included

Home Page

Cart Page

Checkout Page

Success Page
