-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eurekauser
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shoppingcart` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '장바구니 고유값',
  `user_id` int NOT NULL COMMENT '사용자 고유값',
  `product_id` int NOT NULL COMMENT '제품 고유값',
  `option_id` int NOT NULL COMMENT '제품 옵션 고유값',
  `quantity` int NOT NULL COMMENT '제품 수량',
  PRIMARY KEY (`id`),
  KEY `FK_shoppingcart_user_id_user_id` (`user_id`),
  CONSTRAINT `FK_shoppingcart_user_id_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '사용자 고유값',
  `email` varchar(45) NOT NULL COMMENT '이메일 아이디',
  `pw` varchar(500) NOT NULL COMMENT '암호화 필수',
  `phone` varchar(20) DEFAULT NULL COMMENT '핸드폰번호',
  `salt` varchar(500) DEFAULT NULL,
  `enabled` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='쇼핑몰 이용 고객';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'ssafy@ssafy.com','$2a$10$0D.ht/OsXGeoNTNJZht0O.JgLlorikBnvxRyr2SOgOomqgdGtoa2K',NULL,'$2a$10$0D.ht/OsXGeoNTNJZht0O.',''),(9,'ssafy1@ssafy.com','$2a$10$u7WEACd1ggCnFVpNNA/ICuBgkQencbw2x5h3DpKTv.QHZFVwlqyJa',NULL,'$2a$10$u7WEACd1ggCnFVpNNA/ICu','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraddress`
--

DROP TABLE IF EXISTS `useraddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraddress` (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '주소 고유값',
  `nickname` varchar(45) DEFAULT NULL COMMENT '주소 닉네임',
  `user_id` int NOT NULL COMMENT '사용자 고유값',
  `main_address` varchar(500) NOT NULL COMMENT '큰주소',
  `sub_address` varchar(200) DEFAULT NULL COMMENT '상세주소',
  `zipcode` int DEFAULT NULL COMMENT '우편번호',
  `recipient_phone` varchar(45) DEFAULT NULL COMMENT '수신인 번호',
  `recipient_name` varchar(45) DEFAULT NULL COMMENT '받는사람이름',
  `delivery_msg` varchar(200) DEFAULT NULL COMMENT '배송메세지',
  PRIMARY KEY (`address_id`),
  KEY `FK_useraddress_user_id_user_id` (`user_id`),
  CONSTRAINT `FK_useraddress_user_id_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='고객 주소';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraddress`
--

LOCK TABLES `useraddress` WRITE;
/*!40000 ALTER TABLE `useraddress` DISABLE KEYS */;
INSERT INTO `useraddress` VALUES (2,'myhouse2',8,'main_address2','sub_address2',0,'1234231','recipient_name2','delivery_msg2'),(3,'myhouse',8,'main_address','sub_address',0,'1234','recipient_name','delivery_msg');
/*!40000 ALTER TABLE `useraddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usesrpayment`
--

DROP TABLE IF EXISTS `usesrpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usesrpayment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '결제수단 고유값',
  `nickname` varchar(45) DEFAULT NULL COMMENT '결제수단 닉네임',
  `user_id` int NOT NULL COMMENT '사용자 고유값',
  `card_company` varchar(45) NOT NULL COMMENT '카드사',
  `card_num` varchar(500) NOT NULL COMMENT '암호화 필수',
  `card_validity` varchar(500) NOT NULL COMMENT '암호화 필수',
  `card_cvc` varchar(500) DEFAULT NULL COMMENT '암호화 필수',
  `card_pw` varchar(500) NOT NULL COMMENT '암호화 필수',
  `card_userpw` varchar(500) DEFAULT NULL COMMENT '암호화 필수',
  PRIMARY KEY (`id`),
  KEY `FK_usesrpayment_user_id_user_id` (`user_id`),
  CONSTRAINT `FK_usesrpayment_user_id_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='고객 결제정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usesrpayment`
--

LOCK TABLES `usesrpayment` WRITE;
/*!40000 ALTER TABLE `usesrpayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `usesrpayment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-05  5:07:04
