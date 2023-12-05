-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2023 at 05:52 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restomanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `msemployee`
--

CREATE TABLE `msemployee` (
  `EmployeeID` char(5) NOT NULL CHECK (`EmployeeID` regexp 'EM[0-9][0-9][0-9]'),
  `EmployeeName` varchar(255) NOT NULL,
  `RestaurantID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `msemployee`
--

INSERT INTO `msemployee` (`EmployeeID`, `EmployeeName`, `RestaurantID`) VALUES
('EM001', 'dummy', 1);

-- --------------------------------------------------------

--
-- Table structure for table `msmenu`
--

CREATE TABLE `msmenu` (
  `MenuID` char(5) NOT NULL CHECK (`MenuID` regexp 'ME[0-9][0-9][0-9]'),
  `MenuName` varchar(255) NOT NULL,
  `MenuType` varchar(255) NOT NULL,
  `Price` int(11) NOT NULL,
  `MenuDescription` varchar(255) NOT NULL,
  `MenuLocation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `msmenu`
--

INSERT INTO `msmenu` (`MenuID`, `MenuName`, `MenuType`, `Price`, `MenuDescription`, `MenuLocation`) VALUES
('ME002', 'Nasi Goreng', 'Normal', 30000, '', ''),
('ME003', 'Bakmie Ayam', 'Normal', 15000, '', ''),
('ME004', 'Mie Pelangi', 'Special', 40000, 'Bakmie dengan warna yang beragam', ''),
('ME005', 'Nasi Ayam Chasiu', 'Special', 35000, 'Nasi dengan ayam chasiu yang manis dan gurih', ''),
('ME006', 'Nasi Hainam', 'Special', 45000, 'Nasi Hainam dengan daging pilihan bebek atau ayam', ''),
('ME007', 'Bakmie Kuta', 'LocalSpecial', 20000, 'Bakmie ayam dengan gaya khas Kuta', 'Kuta'),
('ME008', 'GATAU BANG', 'LocalSpecial', 45600, 'Ini makanan bang', 'bumi'),
('ME009', 'Pudding Padang', 'LocalSpecial', 15000, 'Pudding manis dengan gula Padang', 'Padang'),
('ME010', 'GreenFields Strawberry', 'LocalSpecial', 60000, 'Yogurt rasa Stoberi', 'Lampung');

-- --------------------------------------------------------

--
-- Table structure for table `msorder`
--

CREATE TABLE `msorder` (
  `MenuID` char(5) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `OrderStatus` varchar(255) NOT NULL,
  `CustomerName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `msorder`
--

INSERT INTO `msorder` (`MenuID`, `Quantity`, `OrderStatus`, `CustomerName`) VALUES
('ME003', 5, 'In Order', 'Davis Kelvin'),
('ME010', 6, 'In Order', 'Davis Kelvin');

-- --------------------------------------------------------

--
-- Table structure for table `msreservation`
--

CREATE TABLE `msreservation` (
  `ReservationID` char(5) NOT NULL CHECK (`ReservationID` regexp 'RE[0-9][0-9][0-9]'),
  `EmployeeID` char(5) NOT NULL,
  `CustomerName` varchar(255) NOT NULL,
  `TableType` varchar(255) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `msreservation`
--

INSERT INTO `msreservation` (`ReservationID`, `EmployeeID`, `CustomerName`, `TableType`, `Quantity`) VALUES
('RE001', 'EM001', 'Davis Kelvin', 'Romantic', 10),
('RE003', 'EM001', 'John Doe', 'Family', 5),
('RE005', 'EM001', 'istrinya john doe', 'Family', 5),
('RE006', 'EM001', 'orang lah pokoknya', 'Family', 3);

-- --------------------------------------------------------

--
-- Table structure for table `msrestaurant`
--

CREATE TABLE `msrestaurant` (
  `RestaurantID` int(11) NOT NULL,
  `RestaurantName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `msrestaurant`
--

INSERT INTO `msrestaurant` (`RestaurantID`, `RestaurantName`) VALUES
(1, 'Surabaya'),
(2, 'Bandung'),
(3, 'Jakarta'),
(4, 'Samarinda'),
(5, 'Padang'),
(6, 'Kuta');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `msemployee`
--
ALTER TABLE `msemployee`
  ADD PRIMARY KEY (`EmployeeID`),
  ADD KEY `RestaurantID` (`RestaurantID`);

--
-- Indexes for table `msmenu`
--
ALTER TABLE `msmenu`
  ADD PRIMARY KEY (`MenuID`);

--
-- Indexes for table `msorder`
--
ALTER TABLE `msorder`
  ADD KEY `MenuID` (`MenuID`);

--
-- Indexes for table `msreservation`
--
ALTER TABLE `msreservation`
  ADD PRIMARY KEY (`ReservationID`),
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- Indexes for table `msrestaurant`
--
ALTER TABLE `msrestaurant`
  ADD PRIMARY KEY (`RestaurantID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `msemployee`
--
ALTER TABLE `msemployee`
  ADD CONSTRAINT `msemployee_ibfk_1` FOREIGN KEY (`RestaurantID`) REFERENCES `msrestaurant` (`RestaurantID`);

--
-- Constraints for table `msorder`
--
ALTER TABLE `msorder`
  ADD CONSTRAINT `msorder_ibfk_1` FOREIGN KEY (`MenuID`) REFERENCES `msmenu` (`MenuID`);

--
-- Constraints for table `msreservation`
--
ALTER TABLE `msreservation`
  ADD CONSTRAINT `msreservation_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `msemployee` (`EmployeeID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
