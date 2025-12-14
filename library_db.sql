-- --------------------------------------------------------
-- Host:                         mysql-3fabcf8-seu-6407.h.aivencloud.com
-- Server version:               8.0.35 - Source distribution
-- Server OS:                    Linux
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for library_db
DROP DATABASE IF EXISTS `library_db`;
CREATE DATABASE IF NOT EXISTS `library_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_db`;

-- Dumping structure for table library_db.allotment
DROP TABLE IF EXISTS `allotment`;
CREATE TABLE IF NOT EXISTS `allotment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentcode` varchar(50) DEFAULT NULL,
  `studentname` varchar(100) DEFAULT NULL,
  `bookid` int DEFAULT NULL,
  `bookname` varchar(100) DEFAULT NULL,
  `unit` int DEFAULT NULL,
  `fare` double DEFAULT NULL,
  `issuedate` date DEFAULT NULL,
  `returndate` date DEFAULT NULL,
  `status` enum('borrowed','returned','pending') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_db.allotment: ~6 rows (approximately)
INSERT INTO `allotment` (`id`, `studentcode`, `studentname`, `bookid`, `bookname`, `unit`, `fare`, `issuedate`, `returndate`, `status`) VALUES
	(1, '0215540', 'Tamim Hassan Yomim', 1, 'Java Essentials', 1, 100, '2025-11-15', '2025-11-23', 'returned'),
	(2, '0360676', 'Rafiq Islam', 6, '	Fundamental of C++', 1, 60, '2025-11-07', '2025-11-26', 'pending'),
	(3, '0490721', 'Shishir Khan', 4, 'Advanced Algorithms', 2, 40, '2025-11-21', '2025-11-29', 'borrowed'),
	(4, '0360678', 'Arif Rahman', 5, 'Database Concepts', 1, 0, '2025-11-21', '2025-11-23', 'returned'),
	(5, '0215540', 'Tamim Hassan Yomim', 5, 'Database Concepts', 1, 140, '2025-11-22', '2025-12-06', 'borrowed'),
	(6, '0360678', 'Arif Rahman', 6, 'Fundamental of C++', 1, 120, '2025-11-22', '2025-12-05', 'borrowed'),
	(7, '0490721', 'Shishir Khan', 4, 'Advanced Algorithms', 2, 280, '2025-11-22', '2025-12-06', 'borrowed'),
	(8, '0490723', 'Tanjim Alam', 6, 'Fundamental of C++', 2, 280, '2025-11-22', '2025-12-06', 'borrowed');

-- Dumping structure for table library_db.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publisherName` varchar(255) DEFAULT NULL,
  `price` double(10,2) NOT NULL,
  `quantity` int NOT NULL DEFAULT (0),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_db.book: ~7 rows (approximately)
INSERT INTO `book` (`id`, `bookName`, `author`, `publisherName`, `price`, `quantity`) VALUES
	(1, 'Java Essentials', 'Robert King', 'Cambridge Press', 45.00, 17),
	(2, 'Learning Spring Boot', 'Sarah Miles', 'O\'Reilly', 52.00, 8),
	(3, 'Data Structures Simplified', 'Kevin Grant', 'McGraw-Hill', 39.99, 20),
	(4, 'Advanced Algorithms', 'Nolan Peters', 'Springer', 65.75, 3),
	(5, 'Database Concepts', 'Emily Turner', 'Cambridge Press', 48.00, 16),
	(6, 'Fundamental of C++', 'Robert King', 'McGraw-Hill', 22.00, 8),
	(8, 'Intro to JavaScript', 'SS Hossain', 'Boltu Publication', 60.00, 10);

-- Dumping structure for table library_db.bookorder
DROP TABLE IF EXISTS `bookorder`;
CREATE TABLE IF NOT EXISTS `bookorder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookid` int DEFAULT (0),
  `bookname` varchar(50) DEFAULT NULL,
  `vendorname` varchar(100) NOT NULL DEFAULT '',
  `totalprice` double(10,2) NOT NULL DEFAULT (0),
  `unit` int NOT NULL DEFAULT (0),
  `orderdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_db.bookorder: ~6 rows (approximately)
INSERT INTO `bookorder` (`id`, `bookid`, `bookname`, `vendorname`, `totalprice`, `unit`, `orderdate`) VALUES
	(2, 1, 'Java Essentials', 'HeisenBerg', 500.00, 10, '2025-11-18'),
	(3, 1, 'Java Essentials', 'Knowledge Hub', 225.00, 5, '2025-11-18'),
	(4, 6, 'Fundamental of C++', 'Nigesh ', 110.00, 5, '2025-11-18'),
	(5, 8, 'Intro to JavaScript', 'Knowledge Hub', 600.00, 10, '2025-11-18'),
	(6, 1, 'Java Essentials', 'EduBooks', 400.00, 9, '2025-11-19'),
	(7, 1, 'Java Essentials', 'HeisenBerg', 50.00, 2, '2025-11-19'),
	(8, 6, 'Fundamental of C++', 'Pages & Co', 100.00, 5, '2025-11-20');

-- Dumping structure for table library_db.publisher
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE IF NOT EXISTS `publisher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_db.publisher: ~5 rows (approximately)
INSERT INTO `publisher` (`id`, `publisher_name`, `address`, `description`) VALUES
	(1, 'Pearson', 'London, UK', 'Educational books publisher'),
	(2, 'Mofiz Publications', 'Dhaka, Bangladesh', 'Very good publication'),
	(3, 'McGraw-Hill', 'New York, USA', 'Academic and professional books'),
	(4, 'Springer', 'Berlin, Germany', 'Scientific and technical publications'),
	(5, 'Cambridge Press', 'Cambridge, UK', 'Educational and research books'),
	(6, 'gg', '24/6 Kazipara, Dhaka', 'sdasda'),
	(7, 'Boltu Publication', 'FaramGyatt, Dhaka', 'Very bad publication');

-- Dumping structure for table library_db.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `code` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_db.student: ~11 rows (approximately)
INSERT INTO `student` (`id`, `name`, `email`, `code`, `phone`) VALUES
	(1, 'Tamim Hassan Yomim', 'tamim@example.com', '0215540', '+8801712345323'),
	(2, 'Sadia Akter', 'sadia@example.com', '0242124', '+8801723456789'),
	(3, 'Rafiq Islam', 'rafiq@example.com', '0360676', '+8801734567890'),
	(4, 'Nabila Karim', 'nabila@example.com', '0360677', '+8801745678901'),
	(5, 'Arif Rahman', 'arif@example.com', '0360678', '+8801756789012'),
	(6, 'Mithila Sultana', 'mithila@example.com', '0360679', '+8801767890123'),
	(8, 'Shishir Khan', 'khanshishir@example.com', '0490721', '+8801789012345'),
	(9, 'Tanjim Alam', 'tanjim@example.com', '0490723', '+8801790123456'),
	(10, 'Rumana Akter', 'rumana@example.com', '0490724', '+8801701234567');

-- Dumping structure for table library_db.vendor
DROP TABLE IF EXISTS `vendor`;
CREATE TABLE IF NOT EXISTS `vendor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vendor_name` varchar(100) NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_db.vendor: ~5 rows (approximately)
INSERT INTO `vendor` (`id`, `vendor_name`, `company_name`, `phone`, `email`, `address`) VALUES
	(1, 'Book World', 'Book World Ltd', '+8801912345678', 'contact@bookworld.com', 'Dhaka, Bangladesh'),
	(2, 'ReadMore', 'ReadMore Co.', '+8801923456789', 'info@readmore.com', 'Chittagong, Bangladesh'),
	(3, 'Pages & Co', 'Pages & Co Ltd.', '+8801934567890', 'support@pagesco.com', 'Khulna, Bangladesh'),
	(4, 'Knowledge Hub', 'Knowledge Hub Pvt Ltd.', '+8801945678901', 'hello@khub.com', 'Rajshahi, Bangladesh'),
	(5, 'EduBooks', 'EduBooks Inc.', '+8801956789012', 'info@edubooks.com', 'Sylhet, Bangladesh'),
	(6, 'Nigesh ', 'Boltu and Boltu CO', '+8801712345673', 'sajidbro004@gmail.com', '24/6 Kazipara, Dhaka'),
	(7, 'HeisenBerg', 'Toufiq and Co.', '+8801237231', 'ss@gmail.com', 'Dhaka, Bangladesh');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
