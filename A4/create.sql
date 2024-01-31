--Chat GPT OpenAI. (2023). SQL Code for Database Creation with Tables Users, Favorites, Restaurants, and Reservations. ChatGPT Session."
CREATE DATABASE IF NOT EXISTS JoesTable;
USE JoesTable;

-- Table: Users
CREATE TABLE IF NOT EXISTS Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL
);

-- Table: Restaurants
CREATE TABLE IF NOT EXISTS Restaurants (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    address VARCHAR(45) NOT NULL,
    phone VARCHAR(45),
    price VARCHAR(45),
    rating VARCHAR(45),
    cuisine VARCHAR(45)
);

-- Table: Favorites
CREATE TABLE IF NOT EXISTS Favorites (
    user_id INT,
    restaurant_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id),
    PRIMARY KEY (user_id, restaurant_id)
);

-- Table: Reservations
CREATE TABLE IF NOT EXISTS Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    restaurant_id INT,
    reservation_date DATETIME NOT NULL,
    reservation_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);
