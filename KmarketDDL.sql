/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP DATABASE IF EXISTS `kmarket`;
CREATE DATABASE IF NOT EXISTS `kmarket` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kmarket`;

DROP TABLE IF EXISTS `km_member`;
CREATE TABLE IF NOT EXISTS `km_member` (
    `uid` varchar(20) NOT NULL,
    `pass` varchar(255) NOT NULL,
    `name` varchar(20) NOT NULL,
    `gender` varchar(10) NOT NULL COMMENT 'FEMALE, MALE,SELLER',
    `hp` char(13) NOT NULL,
    `email` varchar(100) NOT NULL,
    `role` varchar(20) NOT NULL COMMENT '1:일반회원:ROLE_USER, 2:판매회원: ROLE_SELLER,3:관리자:ROLE_ADMIN',
    `point` int(11) DEFAULT '0' COMMENT '누적 포인트',
    `level` varchar(20) DEFAULT 'UNRANKED' COMMENT 'INVALID:탈퇴 UNRANKED:일반 SILVER:실버 GOLD:골드 SELLER:판매자',
    `zip` varchar(10) DEFAULT NULL,
    `addr1` varchar(255) DEFAULT NULL,
    `addr2` varchar(255) DEFAULT NULL,
    `company` varchar(20) DEFAULT NULL,
    `ceo` varchar(20) DEFAULT NULL,
    `bizRegNum` char(12) DEFAULT NULL,
    `comRegNum` varchar(100) DEFAULT NULL,
    `tel` varchar(20) DEFAULT NULL,
    `manager` varchar(20) DEFAULT NULL,
    `managerHp` char(13) DEFAULT NULL,
    `fax` varchar(20) DEFAULT NULL,
    `regip` varchar(100) NOT NULL,
    `wdate` datetime DEFAULT NULL,
    `rdate` datetime NOT NULL,
    `locationTerms` tinyint(4) DEFAULT '0' COMMENT '0: 약관 동의 안함\n1: 약관 동의 함',
    PRIMARY KEY (`uid`),
    UNIQUE KEY email (`email`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_article`;
CREATE TABLE IF NOT EXISTS `km_article` (
    `article_id` int(11) NOT NULL AUTO_INCREMENT,
    `cate` varchar(20) DEFAULT 'free',
    `menu1` varchar(45) DEFAULT 'free' COMMENT 'notice, faq,qna:1,2차 모두 적용',
    `menu2` varchar(45) DEFAULT 'free',
    `title` varchar(255) DEFAULT NULL,
    `content` text NOT NULL,
    `file` tinyint(4) DEFAULT '0',
    `hit` int(11) DEFAULT '0',
    `uid` varchar(20) NOT NULL,
    `regip` varchar(100) NOT NULL,
    `rdate` datetime NOT NULL,
    PRIMARY KEY (`article_id`),
    CONSTRAINT `fk_km_article_km_member` FOREIGN KEY (`uid`) REFERENCES `km_member` (`uid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_comment`;
CREATE TABLE IF NOT EXISTS `km_comment`
(
    `comment_id` int(11) Not Null AUTO_INCREMENT,
    `uid` varchar(20) NOT NULL,
    `article_id` int(11) NOT NULL,
    `content` text NOT NULL,
    `rdate` datetime NOT NULL,
    `modified_date` datetime NOT NULL,
    CONSTRAINT `fk_km_article_km_member` FOREIGN KEY (`uid`) REFERENCES `km_member` (`uid`),
    CONSTRAINT `fk_km_comment_km_article` FOREIGN KEY (`article_id`) REFERENCES `km_article` (`article_id`),
    primary key (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# faq,qna, notice 모두 적용한다.
DROP TABLE IF EXISTS `km_article_cate`;
CREATE TABLE IF NOT EXISTS `km_article_cate` (
    `article_cate_id` tinyint(4) AUTO_INCREMENT,
    `cate` varchar(20) NOT NULL,
    `menu1` varchar(45) NOT NULL,
    `menu2` varchar(45) NOT NULL,
    primary key (`article_cate_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_coupon`;
CREATE TABLE IF NOT EXISTS `km_coupon` (
    `couponNo` int(11) NOT NULL AUTO_INCREMENT,
    `couponName` varchar(255) NOT NULL,
    `uid` varchar(20) NOT NULL,
    `discount` int(11) DEFAULT '0',
    `requirement` varchar(255) NOT NULL,
    `status` tinyint(4) DEFAULT '1',
    `rdate` datetime NOT NULL,
    `wdate` datetime NOT NULL,
    PRIMARY KEY (`couponNo`),
    KEY `fk_km_coupon_km_member1_idx` (`uid`),
    CONSTRAINT `fk_km_coupon_km_member1` FOREIGN KEY (`uid`) REFERENCES `km_member` (`uid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_member_point`;
CREATE TABLE IF NOT EXISTS `km_member_point` (
    `pointNo` int(11) NOT NULL AUTO_INCREMENT,
    `uid` varchar(20) NOT NULL,
    `ordNo` int(11) NOT NULL,
    `point` int(11) NOT NULL,
    `pointDate` datetime NOT NULL,
    `desc` varchar(45) DEFAULT NULL,
    `wdate` datetime NOT NULL,
    PRIMARY KEY (`pointNo`),
    KEY `fk_km_member_point_km_member1_idx` (`uid`),
    KEY `fk_km_member_point_km_product_order1_idx` (`ordNo`),
    CONSTRAINT `fk_km_member_point_km_member1` FOREIGN KEY (`uid`) REFERENCES `km_member` (`uid`),
    CONSTRAINT `fk_km_member_point_km_product_order1` FOREIGN KEY (`ordNo`) REFERENCES `km_product_order` (`ordNo`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_member_terms`;
CREATE TABLE IF NOT EXISTS `km_member_terms` (
    `member_terms_id` int(11) AUTO_INCREMENT,
                                                 `terms` text NOT NULL,
                                                 `privacy` text NOT NULL,
                                                 `location` text NOT NULL,
                                                 `finance` text NOT NULL,
                                                 `tax` text NOT NULL,
    primary key (`member_terms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP table if exists `km_file`;
create table if not exists `km_file`
(
    `stored_name` varchar(255),
    `upload_name` varchar(255),
    primary key (`stored_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_product`;
CREATE TABLE IF NOT EXISTS `km_product` (
                                            `prodNo` int(11) NOT NULL AUTO_INCREMENT,
                                            `prodCate1` tinyint(4) NOT NULL,
                                            `prodCate2` tinyint(4) NOT NULL,
                                            `prodName` varchar(100) NOT NULL,
                                            `descript` varchar(100) NOT NULL,
                                            `company` varchar(100) NOT NULL,
                                            `seller` varchar(20) NOT NULL,
                                            `price` int(11) NOT NULL,
                                            `discount` tinyint(4) DEFAULT '0',
                                            `point` int(11) DEFAULT '0',
                                            `stock` int(11) DEFAULT '0',
                                            `sold` int(11) DEFAULT '0',
                                            `delivery` int(11) DEFAULT '0',
                                            `hit` int(11) DEFAULT '0',
                                            `score` tinyint(4) DEFAULT '0',
                                            `review` int(11) DEFAULT '0',
                                            `thumb1` varchar(255) NOT NULL,
                                            `thumb2` varchar(255) NOT NULL,
                                            `thumb3` varchar(255) NOT NULL,
                                            `detail` varchar(255) NOT NULL,
                                            `status` varchar(20) DEFAULT '새상품',
                                            `duty` varchar(20) DEFAULT '과세상품',
                                            `receipt` varchar(255) DEFAULT '발행가능 - 신용카드 전표, 온라인 현금영수증',
                                            `bizType` varchar(20) DEFAULT '사업자 판매자',
                                            `origin` varchar(20) DEFAULT '상세설명참고',
                                            `ip` varchar(20) NOT NULL,
                                            `rdate` datetime NOT NULL,
                                            PRIMARY KEY (`prodNo`),
                                            KEY `fk_km_product_km_product_cate21_idx` (`prodCate1`),
                                            CONSTRAINT `fk_km_product_km_product_cate1` FOREIGN KEY (`prodCate1`) REFERENCES `km_product_cate1` (`cate1`),
                                            CONSTRAINT `fk_km_product_km_product_cate2` FOREIGN KEY (`prodCate2`) REFERENCES `km_product_cate2` (`cate2`),
                                            constraint `fk_km_product_km_product_seller` foreign key (`seller`) references `km_member` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `km_product_cart`;
CREATE TABLE IF NOT EXISTS `km_product_cart` (
    `cartNo` int(11) NOT NULL AUTO_INCREMENT,
    `uid` varchar(20) NOT NULL,
    `prodNo` int(11) NOT NULL,
    `count` int(11) NOT NULL,
    `price` int(11) NOT NULL,
    `discount` int(11) NOT NULL,
    `point` int(11) NOT NULL,
    `delivery` int(11) NOT NULL,
    `total` int(11) NOT NULL,
    `rdate` datetime NOT NULL,
    PRIMARY KEY (`cartNo`),
    KEY `fk_km_product_cart_km_member1_idx` (`uid`),
    KEY `fk_km_product_cart_km_product1_idx` (`prodNo`),
    CONSTRAINT `fk_km_product_cart_km_member1` FOREIGN KEY (`uid`) REFERENCES `km_member` (`uid`),
    CONSTRAINT `fk_km_product_cart_km_product1` FOREIGN KEY (`prodNo`) REFERENCES `km_product` (`prodNo`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_product_cate1`;
CREATE TABLE IF NOT EXISTS `km_product_cate1` (
    `cate1` tinyint(4) NOT NULL COMMENT '2자리 숫자\n   10 : 브랜드패션	\n   11 : 패션의류/잡화/뷰티	\n   12 : 유아동	\n   13 : 식품/생필품	\n   14 : 홈데코/취미/반려	\n   15 : 컴퓨터/디지털/가전	\n   16 : 스포츠/건강/렌탈	\n   17 : 자동차/공구	\n   18 : 여행/도서/티켓/쿠폰	\n',
    `c1Name` varchar(20) NOT NULL COMMENT '   10 : 브랜드패션	\n   11 : 패션의류/잡화/뷰티	\n   12 : 유아동	\n   13 : 식품/생필품	\n   14 : 홈데코/취미/반려	\n   15 : 컴퓨터/디지털/가전	\n   16 : 스포츠/건강/렌탈	\n   17 : 자동차/공구	\n   18 : 여행/도서/티켓/쿠폰	\n',
    PRIMARY KEY (`cate1`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_product_cate2`;
CREATE TABLE IF NOT EXISTS `km_product_cate2` (
    `cate1` tinyint(4) NOT NULL COMMENT '   10 : 브랜드패션	\n   11 : 패션의류/잡화/뷰티	\n   12 : 유아동	\n   13 : 식품/생필품	\n   14 : 홈데코/취미/반려	\n   15 : 컴퓨터/디지털/가전	\n   16 : 스포츠/건강/렌탈	\n   17 : 자동차/공구	\n   18 : 여행/도서/티켓/쿠폰	\n',
    `cate2` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '2자리 숫자\n10 -  브랜드패션\n   10- 브랜드 여성의류\n   11- 브랜드 남성의류\n   12- 브랜드 진/',
    `c2Name` varchar(20) NOT NULL,
    primary key (cate2),
    KEY `fk_km_product_cate2_km_product_cate11` (`cate1`),
    CONSTRAINT `fk_km_product_cate2_km_product_cate11` FOREIGN KEY (`cate1`) REFERENCES `km_product_cate1` (`cate1`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_product_order`;
CREATE TABLE IF NOT EXISTS `km_product_order` (
    `ordNo` int(11) NOT NULL AUTO_INCREMENT,
    `ordUid` varchar(20) NOT NULL,
    `ordCount` int(11) DEFAULT '0',
    `ordPrice` int(11) DEFAULT '0',
    `ordDiscount` int(11) DEFAULT '0',
    `ordDelivery` int(11) DEFAULT '0',
    `savePoint` int(11) DEFAULT '0',
    `usedPoint` int(11) DEFAULT '0',
    `ordTotPrice` int(11) DEFAULT '0',
    `recipName` varchar(20) NOT NULL,
    `recipHp` char(13) NOT NULL,
    `recipZip` char(5) NOT NULL,
    `recipAddr1` varchar(255) NOT NULL,
    `recipAddr2` varchar(255) NOT NULL,
    `ordPayment` tinyint(4) NOT NULL,
    `ordComplete` tinyint(4) NOT NULL,
    `ordDate` datetime NOT NULL,
    PRIMARY KEY (`ordNo`),
    KEY `fk_km_product_order_km_member1_idx` (`ordUid`),
    CONSTRAINT `fk_km_product_order_km_member1` FOREIGN KEY (`ordUid`) REFERENCES `km_member` (`uid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_product_order_item`;
CREATE TABLE IF NOT EXISTS `km_product_order_item` (
    `ordItem_id` int(11) AUTO_INCREMENT,
    `ordNo` int(11) NOT NULL,
    `prodNo` int(11) NOT NULL,
    `count` int(11) NOT NULL,
    `price` int(11) NOT NULL,
    `discount` tinyint(4) NOT NULL,
    `point` int(11) NOT NULL,
    `delivery` int(11) NOT NULL,
    `total` int(11) NOT NULL,
    primary key (`ordItem_id`),
    KEY `fk_km_product_order_item_km_product_order1_idx` (`ordNo`),
    KEY `fk_km_product_order_item_km_product1_idx` (`prodNo`),
    CONSTRAINT `fk_km_product_order_item_km_product1` FOREIGN KEY (`prodNo`) REFERENCES `km_product` (`prodNo`),
    CONSTRAINT `fk_km_product_order_item_km_product_order1` FOREIGN KEY (`ordNo`) REFERENCES `km_product_order` (`ordNo`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_product_review`;
CREATE TABLE IF NOT EXISTS `km_product_review` (
    `revNo` int(11) NOT NULL AUTO_INCREMENT,
    `prodNo` int(11) NOT NULL,
    `content` varchar(255) NOT NULL,
    `uid` varchar(20) NOT NULL,
    `rating` tinyint(4) NOT NULL,
    `regip` varchar(100) NOT NULL,
    `rdate` datetime NOT NULL,
    PRIMARY KEY (`revNo`),
    KEY `fk_km_product_review_km_product1_idx` (`prodNo`),
    KEY `fk_km_product_review_km_member1_idx` (`uid`),
    CONSTRAINT `fk_km_product_review_km_member1` FOREIGN KEY (`uid`) REFERENCES `km_member` (`uid`),
    CONSTRAINT `fk_km_product_review_km_product1` FOREIGN KEY (`prodNo`) REFERENCES `km_product` (`prodNo`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `km_terms_policy`;
CREATE TABLE IF NOT EXISTS `km_terms_policy` (
    `terms_policy_id` int(11) NOT NULL AUTO_INCREMENT,
                                                 `buyer` text NOT NULL,
                                                 `seller` text NOT NULL,
                                                 `finance` text NOT NULL,
                                                 `location` text NOT NULL,
                                                 `privacy` text NOT NULL,
    primary key (`terms_policy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;