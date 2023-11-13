-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 09, 2023 at 10:24 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `integrated_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `product_price` double DEFAULT NULL,
  `product_add_date` timestamp NULL DEFAULT current_timestamp(),
  `product_color` varchar(255) DEFAULT NULL,
  `product_for` varchar(255) DEFAULT NULL,
  `product_image_path` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `product_price`, `product_add_date`, `product_color`, `product_for`, `product_image_path`, `product_name`, `product_type`) VALUES
(1, 369.99, '2023-10-13 08:40:51', 'Black', 'Men', '1--Black suit.jpg', 'Black suit', 'Suit'),
(2, 349.99, '2023-10-13 08:40:51', 'Brown', 'Men', '2--Brown suit.jpg', 'Brown suit', 'Suit'),
(3, 67.5, '2023-10-13 08:45:21', 'White', 'Men', '3--White shirt.jfif', 'White Shirt', 'Shirt'),
(4, 235, '2023-10-13 08:45:21', 'Black', 'Men', '4--Black jacket.jpg', 'Black Jacket', 'Jacket'),
(5, 399.99, '2023-10-13 08:48:10', 'Blue', 'Men', '5--Navi Blue suit.jpg', 'Navi Blue Suit', 'Suit'),
(6, 112, '2023-10-13 08:48:10', 'White', 'Women', '6--White dress.webp', 'White Dress', 'Dress'),
(7, 85, '2023-10-13 08:48:10', 'Blue', 'Men', '7--Colored Blue shirt.webp', 'Colored Blue Shirt', 'Shirt'),
(8, 154, '2023-10-13 08:48:10', 'Red', 'Women', '8--Red dress.jpg', 'Red Dress', 'Dress'),
(9, 425, '2023-10-13 09:00:34', 'White', 'Men', '9--1700s White suit.jpg', '1700s White Suit', 'Suit'),
(10, 84, '2023-10-13 09:00:34', 'Pink', 'Women', '10--Pink Ten on Ten Women Solid Casual Pink Shirt.jfif', 'Pink Ten on Ten Women Solid Casual Pink Shirt', 'Shirt'),
(11, 140, '2023-10-13 09:00:34', 'Black', 'Women', '11--ASOS DESIGN denim cargo pocket shacket in washed black.webp', 'ASOS DESIGN denim cargo pocket shacket in washed black', 'Jacket'),
(12, 265, '2023-10-13 09:00:34', 'Black', 'Women', '12--ASOS DESIGN Supersoft button through cardigan mini dress in black.webp', 'ASOS DESIGN Supersoft button through cardigan mini dress in black', 'Dress');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `role_id` int(11) NOT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_gender` tinyint(4) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_firstname` varchar(255) DEFAULT NULL,
  `user_lastname` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
