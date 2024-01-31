# DeliveryYelpApp
Overview
Joe's Table is a comprehensive web application that allows users to search for restaurants, view detailed information, make reservations, and manage user accounts. This system leverages Java Servlets, JDBC for database interactions, and integrates with the Yelp API for restaurant searches. It includes an intuitive front-end designed with HTML, CSS, and JavaScript, enhancing user experience with features like map integration and dynamic content updates.

Features
1. User Account Management
Signup: Users can create an account by providing an email, username, and password.
Login: Existing users can login using their username and password.
Account Validation: Ensures that email addresses are valid and passwords are correctly confirmed during signup.
2. Restaurant Search (Yelp API Integration)
Basic Search: Users can search for restaurants by name, location (latitude and longitude), and various sorting options (e.g., rating, distance).
Detailed View: Clicking on a restaurant provides detailed information like address, phone number, cuisine type, and Yelp ratings.
3. Google Maps Integration
Map View: Allows users to visually select a location by dropping a pin on the map, automatically filling in the latitude and longitude fields in the search form.
4. User Interactions
Favorites: Users can add or remove restaurants from their favorites list.
Reservations: Users can make reservations at restaurants, specifying date, time, and additional notes.
Technical Components
Backend
Java Servlets: Handle HTTP requests and responses.
JDBC Connector: Manages database connections, user authentication, and queries.
Yelp API: Fetches restaurant information based on user queries.
Frontend
HTML and CSS: Structure and style the web pages.
JavaScript: Enhances interactivity, handles API calls, and dynamically updates content.
Google Maps JavaScript API: Implements map functionality.
Installation and Setup
Database Setup: Configure a MySQL database with the necessary tables (Users, Favorites, Reservations).
Java Environment: Ensure Java SDK and Tomcat server are installed.
API Keys: Obtain Yelp API and Google Maps API keys, replace them in the Servlet and HTML files respectively.
Deployment: Deploy the application on a Tomcat server.
Usage
Home Page: Start at the home page where you can login or sign up.
Search Restaurants: Use the search feature to find restaurants. You can specify names, locations, and sorting options.
View Details: Select a restaurant to view more details and make reservations.
Manage Account: Users can update their account details or log out.

##Images 

![alt text](https://github.com/benc2711/DeliveryYelpApp/blob/main/ScreenshotsFS/fs1.png)
![alt text](https://github.com/benc2711/DeliveryYelpApp/blob/main/ScreenshotsFS/fs2.png)
![alt text](https://github.com/benc2711/DeliveryYelpApp/blob/main/ScreenshotsFS/fs3.png)
![alt text](https://github.com/benc2711/DeliveryYelpApp/blob/main/ScreenshotsFS/fs4.png)
