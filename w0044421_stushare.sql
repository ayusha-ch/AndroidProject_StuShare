-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 01, 2020 at 10:15 PM
-- Server version: 5.5.62-cll
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `w0044421_stushare`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `_id` int(16) NOT NULL,
  `createdAT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bookCode` int(16) NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `edition` year(4) NOT NULL,
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `isbnNumber` int(16) NOT NULL,
  `details` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `_id` int(16) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `carCode` int(16) NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `available_date` date NOT NULL,
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `year` year(4) NOT NULL,
  `mileage` int(11) NOT NULL,
  `price` double NOT NULL,
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `details` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(10) NOT NULL,
  `eventCode` text NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `organizerId` varchar(255) NOT NULL,
  `orgEmail` text NOT NULL,
  `status` varchar(255) NOT NULL,
  `startDate` varchar(255) NOT NULL,
  `startTime` varchar(255) NOT NULL,
  `endDate` varchar(255) NOT NULL,
  `endTime` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `detail` varchar(255) NOT NULL,
  `imagePath` varchar(3000) NOT NULL,
  `like_count` int(11) NOT NULL,
  `rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `eventCode`, `createdAt`, `organizerId`, `orgEmail`, `status`, `startDate`, `startTime`, `endDate`, `endTime`, `title`, `detail`, `imagePath`, `like_count`, `rating`) VALUES
(2, 'ttt7', '2020-01-17 22:38:32', '2', '', 'active', '20200329', '10', '20200331', '12', 'Job fair for T127 graduates2321312', 'Guys, are you looking for a job, come to E430 and build up your next work with your potential employer', 'https://cdn.pixabay.com/photo/2017/10/31/09/55/dream-job-2904780_960_720.jpg', 0, 3.7),
(3, '0b67', '2020-01-17 22:38:32', '2', '', 'active', '20200402', '12', '20200604', '12', 'Fruit picking', 'Fruit picking event is on GBC now, admission fee $9 tax included. Please contact coordinator@lovefruitpicking.com', 'https://cdn.pixabay.com/photo/2018/09/08/00/25/apples-3661792_960_720.jpg', 0, 4.5),
(6, '2br4', '2020-03-03 00:45:48', '8', '', 'active', '20200303', '18:45', '20201204', '18:45', '2 people fighting', 'Two guys fighting one people see', 'https://thtcentre.com/wp-content/uploads/2018/07/Events-624x269.jpg', 0, 4.9),
(7, 'sd2D', '2020-03-13 02:50:38', '8', '', 'active', '20200313', '13:50', '20200913', '15:29', 'hello', 'new description for the vent', 'https://www.1053rock.ca/wp-content/uploads/sites/29/2016/11/1053-ROCK-CommunityEvents-Spotlight-1052x592-1024x576.png', 0, 1.4),
(8, 'sda1', '2020-03-13 02:52:17', '8', '', 'active', '20200316', '13:51', '20200716', '22:52', 'testing event', 'this is two line description for the event', 'https://www.marsdd.com/wp-content/uploads/2019/08/TORONTO-TECH-EVENTS-FEATURE.jpg', 0, 3.3),
(11, 'AB9C', '2020-03-17 03:54:17', '2', '', 'active', '20200323', '1000', '20200428', '1059', 'JavaScript workshop', 'JavaScript workshop for beginner, meet us at room C799!', 'https://cdn.pixabay.com/photo/2016/11/19/14/00/code-1839406_960_720.jpg', 0, 2.5),
(12, 'AB0C', '2020-03-17 03:55:30', '2', '', 'active', '20200320', '1000', '20200628', '1059', 'Resume workshop today', 'Prepare you resume for any potential hiring opportunity.Please contact resume@gbc.com', 'https://cdn.pixabay.com/photo/2018/02/27/10/07/opportunity-3185099_960_720.jpg', 0, 2),
(13, 'HNnGSi', '2020-03-21 20:45:58', '1', '', 'active', '20200321', '2', '20200825', '2', 'this is to test sharing code213213213', 'let me see', 'https://cdn.pixabay.com/photo/2016/11/19/14/00/code-1839406_960_720.jpg', 0, 2.4),
(14, 'R5mBYA', '2020-04-01 18:46:09', '10', 'yu.shi@georgebrown.ca', 'active', '20200402', '15:45', '20200829', '16:46', 'this event is to t', 'see this for detail please', 'https://cdn.pixabay.com/photo/2016/11/19/14/00/code-1839406_960_720.jpg', 0, 5),
(15, 'ZCVFzL', '2020-04-01 18:50:56', '10', '1', 'active', '20200404', '15:50', '20200404', '15:15', '231321312', '123312213123321', 'https://cdn.pixabay.com/photo/2016/11/19/14/00/code-1839406_960_720.jpg', 0, 5);

-- --------------------------------------------------------

--
-- Table structure for table `event_liked`
--

CREATE TABLE `event_liked` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `likedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_liked`
--

INSERT INTO `event_liked` (`id`, `event_id`, `user_id`, `likedAt`) VALUES
(2, 13, 2, '2020-04-01 23:50:32'),
(3, 13, 3, '2020-04-01 23:50:32'),
(4, 14, 2, '2020-04-01 23:50:32'),
(5, 13, 8, '2020-04-01 23:50:32'),
(6, 15, 10, '2020-04-01 23:50:32'),
(85, 14, 8, '2020-04-02 02:12:39'),
(83, 15, 8, '2020-04-02 02:12:34');

-- --------------------------------------------------------

--
-- Table structure for table `event_rated`
--

CREATE TABLE `event_rated` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `rate_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_rated`
--

INSERT INTO `event_rated` (`id`, `event_id`, `user_id`, `rating`, `rate_at`) VALUES
(1, 13, 10, 2, '2020-03-30 01:00:52'),
(8, 15, 8, 5, '2020-04-01 20:42:38'),
(7, 11, 10, 2, '2020-03-30 01:13:00'),
(6, 12, 10, 2, '2020-03-30 01:12:44'),
(9, 15, 13, 5, '2020-04-01 20:57:36');

-- --------------------------------------------------------

--
-- Table structure for table `event_reg`
--

CREATE TABLE `event_reg` (
  `id` int(10) NOT NULL,
  `eventId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `eventRegCode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `event_reg`
--

INSERT INTO `event_reg` (`id`, `eventId`, `userId`, `status`, `createdAt`, `eventRegCode`) VALUES
(3, 1, 3, 'registered', '2020-03-03 15:31:33', 0),
(4, 6, 8, 'registered', '2020-03-14 21:45:28', 0),
(5, 2, 10, 'registered', '2020-03-29 19:25:55', 0),
(6, 15, 13, 'registered', '2020-04-01 20:54:10', 0);

-- --------------------------------------------------------

--
-- Table structure for table `fav_table`
--

CREATE TABLE `fav_table` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `faved_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `_id` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `messageCode` int(11) NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `details` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`_id`, `createdAt`, `messageCode`, `title`, `sender_id`, `receiver_id`, `details`, `type`, `status`) VALUES
(1, '2020-03-27 20:00:11', 0, '', 1, 101044421, '', 'event', 'active'),
(2, '2020-03-27 23:36:30', 0, '', 1, 101044421, '', 'event', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `_id` int(16) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `roomCode` int(16) NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `available_date` date NOT NULL,
  `offering_wanted` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lease` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_number` int(11) NOT NULL,
  `pets` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `house_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rent` double NOT NULL,
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `details` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `collegeCode` varchar(255) DEFAULT NULL,
  `programCode` varchar(255) DEFAULT NULL,
  `registeredYear` varchar(255) DEFAULT NULL,
  `expireYear` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `imagePath` varchar(3000) NOT NULL,
  `activationCode` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `firstName`, `lastName`, `collegeCode`, `programCode`, `registeredYear`, `expireYear`, `status`, `question`, `answer`, `role`, `createdAt`, `imagePath`, `activationCode`) VALUES
(1, 'dharam@georgebrown.ca', 'Password2', 'Dharam', 'KC', 'GBC', 'T127', '2017', '2020', 'active', 'what is my favourite Teacher', 'Anjana', 'student', '0000-00-00 00:00:00', 'https://pixabay.com/vectors/avatar-people-person-business-user-3637561/', ''),
(2, 'david@georgebrown.ca', 'Password1', 'David', 'Shi ', 'GBC', 'T127', '2017', '2020', 'active', 'what is my favourite car1 ', 'Subaru ', 'admin', '2020-02-27 05:00:00', 'https://cdn.pixabay.com/photo/2016/03/09/10/23/model-1246028_1280.jpg', ''),
(3, 'Harman@georgebrown.ca', '11', 'Harmanpreet', 'Kaur', 'GBC', 'T127', '2017', '2020', 'active', 'Who is always hardworking?', 'me', 'student', '0000-00-00 00:00:00', 'https://cdn.pixabay.com/photo/2018/01/15/07/51/woman-3083376_1280.jpg', ''),
(8, '1@georgebrown.ca', '1', '1', '1', '', '1', '1', '1', 'active', '1', '1', 'student', '2020-03-02 22:35:41', '0', ''),
(10, '11@georgebrown.ca', '1', '22', '22', '', '', '2018', '2020', 'active', '2222?', '2', 'student', '2020-03-21 15:42:27', '', '222adsa'),
(12, '2', '2', '22', '22', '', '22', '2222', '2222', 'active', '22', '22', 'admin', '2020-03-21 17:07:29', '', 'QSzljJyH3b0h2KRjJZMa'),
(13, '2@georgebrown.ca', '22', '22', '22', '', '22', '22', '22', 'active', '22', '22', 'student', '2020-03-21 17:17:32', '', 'X1hp76i84nFLmaW97C4L'),
(16, 'yu.shi@georgebrown.ca', '1', '1', '1', '', '1', '11', '1', 'active', '1', '1', 'student', '2020-03-21 19:23:04', '', 'UiIZ6DpGuaBzLWmrum2x'),
(17, 'harmanpreet.kaur2@georgebrown.ca', '1', '1', '1', '', '1', '1', '1', 'pending', '1', '1', 'student', '2020-03-21 19:29:49', '', 'FMkWpNZPlvpp8NK72FVy'),
(18, '3@georgebrown.ca', 'Password2', '3213213', '3312231', '', '132', '2312', '2312', 'pending', '3213312132321', '3213231321123213', 'student', '2020-03-27 19:49:33', '', 'bL2K5An7gPsDD8rqT4u6');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event_liked`
--
ALTER TABLE `event_liked`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event_rated`
--
ALTER TABLE `event_rated`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event_reg`
--
ALTER TABLE `event_reg`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fav_table`
--
ALTER TABLE `fav_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `_id` int(16) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `_id` int(16) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `event_liked`
--
ALTER TABLE `event_liked`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `event_rated`
--
ALTER TABLE `event_rated`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `event_reg`
--
ALTER TABLE `event_reg`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `fav_table`
--
ALTER TABLE `fav_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `_id` int(16) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
