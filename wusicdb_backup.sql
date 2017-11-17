-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: wusicdb
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `album_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `artwork` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist` (
  `artist_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `hometown` varchar(45) NOT NULL,
  PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_in_album`
--

DROP TABLE IF EXISTS `artist_in_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist_in_album` (
  `artist_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  KEY `artist_id` (`artist_id`),
  KEY `album_id` (`album_id`),
  CONSTRAINT `artist_in_album_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`artist_id`),
  CONSTRAINT `artist_in_album_ibfk_2` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_in_album`
--

LOCK TABLES `artist_in_album` WRITE;
/*!40000 ALTER TABLE `artist_in_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `artist_in_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_sings_song`
--

DROP TABLE IF EXISTS `artist_sings_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist_sings_song` (
  `artist_id` int(11) DEFAULT NULL,
  `song_id` int(11) DEFAULT NULL,
  KEY `artist_id` (`artist_id`),
  KEY `song_id` (`song_id`),
  CONSTRAINT `artist_sings_song_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`artist_id`),
  CONSTRAINT `artist_sings_song_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_sings_song`
--

LOCK TABLES `artist_sings_song` WRITE;
/*!40000 ALTER TABLE `artist_sings_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `artist_sings_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `genre_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `song_count` int(11) NOT NULL,
  `is_private` tinyint(4) NOT NULL,
  PRIMARY KEY (`playlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song` (
  `song_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `lyrics` multilinestring DEFAULT NULL,
  PRIMARY KEY (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_in_album`
--

DROP TABLE IF EXISTS `song_in_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_in_album` (
  `song_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `track_number` int(11) DEFAULT NULL,
  KEY `song_id` (`song_id`),
  KEY `album_id` (`album_id`),
  CONSTRAINT `song_in_album_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
  CONSTRAINT `song_in_album_ibfk_2` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_in_album`
--

LOCK TABLES `song_in_album` WRITE;
/*!40000 ALTER TABLE `song_in_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `song_in_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_in_genre`
--

DROP TABLE IF EXISTS `song_in_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_in_genre` (
  `song_id` int(11) DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  KEY `song_id` (`song_id`),
  KEY `genre_id` (`genre_id`),
  CONSTRAINT `song_in_genre_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
  CONSTRAINT `song_in_genre_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_in_genre`
--

LOCK TABLES `song_in_genre` WRITE;
/*!40000 ALTER TABLE `song_in_genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `song_in_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_in_playlist`
--

DROP TABLE IF EXISTS `song_in_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_in_playlist` (
  `song_id` int(11) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL,
  KEY `song_id` (`song_id`),
  KEY `playlist_id` (`playlist_id`),
  CONSTRAINT `song_in_playlist_ibfk_1` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
  CONSTRAINT `song_in_playlist_ibfk_2` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_in_playlist`
--

LOCK TABLES `song_in_playlist` WRITE;
/*!40000 ALTER TABLE `song_in_playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `song_in_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_follows`
--

DROP TABLE IF EXISTS `user_follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_follows` (
  `followerID` int(11) DEFAULT NULL,
  `followingID` int(11) DEFAULT NULL,
  KEY `followerID` (`followerID`),
  KEY `followingID` (`followingID`),
  CONSTRAINT `user_follows_ibfk_1` FOREIGN KEY (`followerID`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_follows_ibfk_2` FOREIGN KEY (`followingID`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_follows`
--

LOCK TABLES `user_follows` WRITE;
/*!40000 ALTER TABLE `user_follows` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_likes_song`
--

DROP TABLE IF EXISTS `user_likes_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_likes_song` (
  `user_id` int(11) DEFAULT NULL,
  `song_id` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `song_id` (`song_id`),
  CONSTRAINT `user_likes_song_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_likes_song_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_likes_song`
--

LOCK TABLES `user_likes_song` WRITE;
/*!40000 ALTER TABLE `user_likes_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_likes_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_owns_playlist`
--

DROP TABLE IF EXISTS `user_owns_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_owns_playlist` (
  `user_id` int(11) DEFAULT NULL,
  `playlist_id` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `playlist_id` (`playlist_id`),
  CONSTRAINT `user_owns_playlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_owns_playlist_ibfk_2` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_owns_playlist`
--

LOCK TABLES `user_owns_playlist` WRITE;
/*!40000 ALTER TABLE `user_owns_playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_owns_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_owns_song`
--

DROP TABLE IF EXISTS `user_owns_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_owns_song` (
  `user_id` int(11) DEFAULT NULL,
  `song_id` int(11) DEFAULT NULL,
  `date_purchased` date DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `song_id` (`song_id`),
  CONSTRAINT `user_owns_song_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_owns_song_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_owns_song`
--

LOCK TABLES `user_owns_song` WRITE;
/*!40000 ALTER TABLE `user_owns_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_owns_song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-17 12:43:03
