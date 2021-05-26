-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: k4d104.p.ssafy.io    Database: auth
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('sumin@sumin.com','$2a$10$K6qSuz7lNGpSnQVBQ.oFP.jxztOky/fwTvcdFcsul5cpra2KNYyVq','USER'),('goo@goo.com','$2a$10$E7ayZfaGi3KEALGu94q5..THmj5aJ0qlLSh80X60YZO93MjrfaNnG','USER'),('aa@aa.com','$2a$10$sDzGIe9G7CP9g/FVeMekeuLXk62jZtMcT230jRb.5yhQGVUD7wWvq','USER'),('goo2@naver.com','$2a$10$ocivCtSWcp47./Qb5ZgQEueRy.zZbvyT2iyXH.ZHBg71EnkcuRzuG','USER'),('seller1@seller1.com','$2a$10$6CUi6PLeVAyp/ZxeC.oZKePsz5qpjOlpmcfPUewGTfNCa95sOvhHC','SELLER'),('goo@go.com','$2a$10$M6mKvzUDJ6L66bC2J8Ms2OI0eKH.UE8mWeTdsNF9.Q7GW7QKkocYG','SELLER'),('j@j.com','$2a$10$0RYCD2RjHJyPeaQrPh6pTe0VwxStMrXqji14RvqrsgCDwB29XFl62','SELLER'),('rnjstpwls@naver.com','$2a$10$1XWOg7WZdGO1xmfDPMBluOz8BqvAgMPDhnQ7DISXvF549bcAOg.KK','USER'),('tlnzldr@naver.com','$2a$10$PVBs3gr2WOjeLHCBlH.u5u5CuJtvMir4QcdTVCyIOSkX7NoiEM/Py','USER'),('tttt@tttt.com','$2a$10$poe3mqNC5YRhUU8/872CvuxeBX6xjopFeFLkHF5b1eNjiYbbAQApK','USER'),('dkdla@dkdla.com','$2a$10$0g73txUP6L0d9mW0EHXPhuY/kb62DDJSs6Fp5QaXhXLM6GWkjguRi','USER'),('snack@snack.com','$2a$10$WnRpbOpACE6g.DkwwDWFPuxQwgEE1zlV80GN6hMDckHTYQe25PlZi','SELLER'),('hohouser@hoho.com','$2a$10$7JhD9k7Wwt7XUtBLAPI56.UbOTrnYzR5CfW2/7uCFiHhG3JWmw3FK','USER'),('hoho@hoho.com','$2a$10$PUNrp0yLhE.Syur/ZntRruIZOTXfUJ77rjZ1QhVc7EbjtMDMstTMW','SELLER'),('aaa@aaa.com','$2a$10$/1ngeMaG/VOddDyCL.JYKe/qJ3CdkZEezgF0Ovas/iJ6..lcdV4v6','SELLER'),('gooseller@goo.com','$2a$10$LyWKfUprFzvN/BwsdTfHWeT6Erqazk/1eefVCN4UQuOmQWm8SU852','SELLER'),('oo@oo.com','$2a$10$P3Kbj582.tRi8uvwVqfn0uO.xHQVBK7Oeltgpq8DYGJ9Imb9DSEHO','SELLER'),('goo2@goo.com','$2a$10$MXXiPuNEt6zcCsZZThAUpOeoBzyCvM8dih7Wa0Gjvxi4.HceeVdDq','USER'),('goo3@goo.com','$2a$10$MxAyUNHCtnaO6FpnOnBi1e0/SKwuevlT2PAZ4fECdEh4pDVv5Dg4S','USER'),('jun@jun.com','$2a$10$ZiYfSF5lMAnRTxv2IMXN0eEArjIN1NJ6YgtFw9xz0I7EGpK4e.hMK','USER'),('final@final.com','$2a$10$HeLGCV1NabUQWdMiKbJZceEnpQZhf8nWIitllkhpArjOogJtWfDyK','USER'),('haha@haha.com','$2a$10$zAMBtFU/3jGyW9Sa1isxmum.YKyUgC8zJcOv2nO/aI7I6Gloir9TG','SELLER'),('haha2@haha.com','$2a$10$qUvu7tNphA5H3OpSaVzOx.tEi2Xz0ZD0g4DuGnYCMTResYa5g6oUq','SELLER'),('jae@jae.com','$2a$10$ruQQg9kuDFWzvgOG7eUMMuywRsq9/fLOERnbvPrwlj3UPUODECv6O','SELLER'),('goo4@goo.com','$2a$10$4RrLNP3cjRM8g5xsQSzDAu7aZRCJ2d4i5lS3owfkU534VqLQ57xCG','SELLER'),('susu@susu.com','$2a$10$x0PCh.i8UzpVk5fXUF9IMe23qd18QHfqSOWteFdIGkR0HllHVpFsi','SELLER'),('dd@dd.com','$2a$10$b3TvumJcajSu2gYIN9lvs.rCvfEYbOH8uTbTYH2CWCYlCQQSt9LYq','SELLER'),('zzzz@zzzz.com','$2a$10$VQR9cGosBEGZsGx9A8W4tec2mI7PViE2r68ZxwDky4k2Wc17mThNG','USER');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-27  1:21:47
