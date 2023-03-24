-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pos
--

CREATE DATABASE IF NOT EXISTS pos;
USE pos;

--
-- Definition of table `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `brandid` varchar(10) NOT NULL,
  `brandname` varchar(45) NOT NULL,
  PRIMARY KEY  (`brandid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand`
--

/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` (`brandid`,`brandname`) VALUES 
 ('BR-0000001','gg9'),
 ('BR-0000002','Hp1');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;


--
-- Definition of table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerid` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY  (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `itemdetail`
--

DROP TABLE IF EXISTS `itemdetail`;
CREATE TABLE `itemdetail` (
  `itemid` varchar(10) NOT NULL,
  `merid` varchar(10) NOT NULL,
  `itemname` varchar(45) NOT NULL,
  `curSaleprice` bigint(20) unsigned zerofill NOT NULL default '00000000000000000000',
  `remark` varchar(100) NOT NULL,
  `totalQty` int(10) unsigned zerofill NOT NULL default '0000000000',
  PRIMARY KEY  (`itemid`),
  KEY `mfk` (`merid`),
  CONSTRAINT `mfk` FOREIGN KEY (`merid`) REFERENCES `merchandise` (`merid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `itemdetail`
--

/*!40000 ALTER TABLE `itemdetail` DISABLE KEYS */;
INSERT INTO `itemdetail` (`itemid`,`merid`,`itemname`,`curSaleprice`,`remark`,`totalQty`) VALUES 
 ('IT_0000001','ME-0000001','aa',00000000000000000110,'apk',0000000060),
 ('IT_0000002','ME-0000001','SA',00000000000000000119,'DDS',0000000034),
 ('IT_0000003','ME-0000002','DD',00000000000000000001,'gg',0000000000),
 ('IT_0000004','ME-0000001','hgh',00000000000000000000,'hhh',0000000000),
 ('IT_0000005','ME-0000001','monitor',00000000000000000000,'good',0000000000);
/*!40000 ALTER TABLE `itemdetail` ENABLE KEYS */;


--
-- Definition of table `merchandise`
--

DROP TABLE IF EXISTS `merchandise`;
CREATE TABLE `merchandise` (
  `merid` varchar(10) NOT NULL,
  `brandid` varchar(10) NOT NULL,
  `typeid` varchar(10) NOT NULL,
  PRIMARY KEY  (`merid`),
  KEY `bfk` (`brandid`),
  KEY `tfk` (`typeid`),
  CONSTRAINT `bfk` FOREIGN KEY (`brandid`) REFERENCES `brand` (`brandid`),
  CONSTRAINT `tfk` FOREIGN KEY (`typeid`) REFERENCES `type` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `merchandise`
--

/*!40000 ALTER TABLE `merchandise` DISABLE KEYS */;
INSERT INTO `merchandise` (`merid`,`brandid`,`typeid`) VALUES 
 ('ME-0000001','BR-0000001','TY-0000001'),
 ('ME-0000002','BR-0000002','TY-0000001');
/*!40000 ALTER TABLE `merchandise` ENABLE KEYS */;


--
-- Definition of table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `purchaseid` varchar(10) NOT NULL,
  `supplierid` varchar(10) NOT NULL,
  `purchasedate` date NOT NULL,
  PRIMARY KEY  (`purchaseid`),
  KEY `sfk` (`supplierid`),
  CONSTRAINT `sfk` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` (`purchaseid`,`supplierid`,`purchasedate`) VALUES 
 ('P-00000001','SU-0000001','2012-01-17'),
 ('P-00000002','SU-0000001','2012-01-17'),
 ('P-00000003','SU-0000001','2012-01-17');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;


--
-- Definition of table `purchasedetail`
--

DROP TABLE IF EXISTS `purchasedetail`;
CREATE TABLE `purchasedetail` (
  `purchaseid` varchar(10) NOT NULL,
  `purchaseprice` bigint(20) unsigned NOT NULL,
  `purchasequantity` int(10) unsigned NOT NULL,
  `itemid` varchar(10) NOT NULL,
  KEY `FK_purchasedetail_1` (`purchaseid`),
  KEY `FK_purchasedetail_2` (`itemid`),
  CONSTRAINT `FK_purchasedetail_1` FOREIGN KEY (`purchaseid`) REFERENCES `purchase` (`purchaseid`),
  CONSTRAINT `FK_purchasedetail_2` FOREIGN KEY (`itemid`) REFERENCES `itemdetail` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchasedetail`
--

/*!40000 ALTER TABLE `purchasedetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchasedetail` ENABLE KEYS */;


--
-- Definition of table `sale`
--

DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `Saleid` varchar(10) NOT NULL,
  `customerid` varchar(10) NOT NULL,
  `saledate` date NOT NULL,
  PRIMARY KEY  (`Saleid`),
  KEY `cfk` (`customerid`),
  CONSTRAINT `cfk` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale`
--

/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;


--
-- Definition of table `saledetail`
--

DROP TABLE IF EXISTS `saledetail`;
CREATE TABLE `saledetail` (
  `Saleid` varchar(10) NOT NULL,
  `saleprice` bigint(20) unsigned NOT NULL,
  `salequantity` int(10) unsigned NOT NULL,
  `itemid` varchar(10) NOT NULL,
  KEY `itemfk` (`itemid`),
  KEY `salefk` (`Saleid`),
  CONSTRAINT `itemfk` FOREIGN KEY (`itemid`) REFERENCES `itemdetail` (`itemid`),
  CONSTRAINT `salefk` FOREIGN KEY (`Saleid`) REFERENCES `sale` (`Saleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saledetail`
--

/*!40000 ALTER TABLE `saledetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `saledetail` ENABLE KEYS */;


--
-- Definition of table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `supplierid` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY  (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`supplierid`,`name`,`address`,`phoneno`,`email`) VALUES 
 ('SU-0000001','Thet Hnin Suuuuy','South Dagon','09786544543','chenchen113@gmail.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;


--
-- Definition of table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeid` varchar(10) NOT NULL,
  `typename` varchar(20) NOT NULL,
  PRIMARY KEY  (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type`
--

/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` (`typeid`,`typename`) VALUES 
 ('TY-0000001','Lattopp');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
