-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: NhaHang
-- ------------------------------------------------------
-- Server version	8.0.22-0ubuntu0.20.04.2

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
-- Table structure for table `HopDong`
--

DROP TABLE IF EXISTS `HopDong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HopDong` (
  `idHopDong` int NOT NULL AUTO_INCREMENT,
  `ngayToChuc` varchar(45) DEFAULT NULL,
  `thoiDiem` varchar(45) DEFAULT NULL,
  `maSanh` varchar(45) DEFAULT NULL,
  `soMenu` varchar(45) DEFAULT NULL,
  `soBan` varchar(45) DEFAULT NULL,
  `soKhach` varchar(45) DEFAULT NULL,
  `nhanVien` varchar(45) DEFAULT NULL,
  `tenKhachHang` varchar(45) DEFAULT NULL,
  `diaChi` varchar(45) DEFAULT NULL,
  `dienThoai` varchar(45) DEFAULT NULL,
  `ngayLapDon` varchar(45) DEFAULT NULL,
  `giaDichVu` varchar(45) DEFAULT NULL,
  `dichVuDaChon` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idHopDong`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HopDong`
--

LOCK TABLES `HopDong` WRITE;
/*!40000 ALTER TABLE `HopDong` DISABLE KEYS */;
INSERT INTO `HopDong` VALUES (1,'2020-11-03','chiều','S002','1','2','2','1','1','1','1','26/11/2020 07:29:44','1,800,000','  6  5'),(2,'2020-11-03','chiều','S001','2','2','1','3','1','2','3','26/11/2020 07:34:31','1,500,000','  7'),(3,'2020-11-03','chiều','S001','2','2','2','Minh','Minh','XYz','0982','26/11/2020 09:01:12','1,400,000',''),(4,'2020-11-03','chiều','S001','2','2','2','Minh','Minh','XYz','0982','26/11/2020 09:01:12','2,100,000','  7  4'),(5,'2020-11-03','chiều','S003','1','2','2','a','a','a','a','26/11/2020 13:35:11','1,800,000','  6  5'),(6,'2020-11-03','chiều','S003','1','2','2','a','a','a','a','26/11/2020 13:35:11','1,800,000','  6  5'),(7,'2020-11-10','chiều','S001','2','2','2','b','b','b','b','26/11/2020 13:36:45','650,000','  4  8'),(8,'2020-11-04','chiều','S002','1','2','2','2','2','2','2','26/11/2020 14:14:12','2,300,000','  2  6'),(9,'2020-11-10','tối','S001','2','2','2','2','2','2','2','26/11/2020 14:46:36','1,400,000',''),(10,'2020-11-03','sáng','S001','2','2','2','Linh','LInh','Ha Noi','0987','26/11/2020 19:20:05','3,600,000','  2  4  7');
/*!40000 ALTER TABLE `HopDong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-26 20:55:17
