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
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Menu` (
  `idMenu` int NOT NULL,
  `khaiVi` varchar(1000) NOT NULL,
  `monChinh` varchar(1000) NOT NULL,
  `trangMieng` varchar(1000) NOT NULL,
  `donGia` int NOT NULL,
  PRIMARY KEY (`idMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu` DISABLE KEYS */;
INSERT INTO `Menu` VALUES (1,'Súp gà kem Ngô, Nộm thập cẩm bò khô ','Cá Diệu Hồng chiên cốm, Đùi Gà Mỹ quay sốt nấm, Bò sốt tiêu đen + Bánh bao, Xôi vò hạt sen, Canh Mọc Nấm tươt hạt sen, Cải ngồng xào tỏi, Cơm trắng','Quýt Sài Gòn',250000),(2,'Súp Gà Nấm Tuyết, Nộm Miến Hải sản','Nem Tôm Hồng Kông, Đùi Gà Mỹ quay sốt Cam, Bò xào lúc lắc và Khoai Tây Chiên, Cá Diêu Hồng hấp Xì Dầu, Xôi Hoàng Phố, Canh Ngao chua, Cơm trắng ','Chè Trân Châu Khoai Môn',300000),(4,'Súp Gà Nấm Hương, Nộm Sứa biển cổ hũ Dừa','Tôm Sú hấp nước Dừa, Cá Tầm nướng kiểu dân tộc, Gà ủ Muối bọc lá Sen, Bò hầm Đậu ngự và Bánh Mỳ, Lơ xanh xào tương XO,  Xôi trống đồng, Canh củ Sen Sườn non, Cơm trắng','Bưởi da xanh',400000);
/*!40000 ALTER TABLE `Menu` ENABLE KEYS */;
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
