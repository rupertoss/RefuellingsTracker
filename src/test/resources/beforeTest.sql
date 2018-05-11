DROP TABLE IF EXISTS `refuellings`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `users_vehicles`;
DROP TABLE IF EXISTS `vehicles`;


CREATE TABLE `refuellings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fuel_type` int(11) NOT NULL,
  `mileage` int(11) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `quantity` decimal(6,2) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `vehicle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ldb98brdnv0xxb7gq2u15tb0` (`user_id`),
  KEY `FKpmstvg63bqno8h7x3fi2njhm` (`vehicle_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `users_vehicles` (
  `user_id` bigint(20) NOT NULL,
  `vehicle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`vehicle_id`),
  KEY `FKfamokmwi5l2oaqog49d0xtbq` (`vehicle_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `vehicles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mileage` int(10) unsigned NOT NULL DEFAULT '0',
  `plate_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `refuellings` VALUES (1,2,124578,174.53,42.12,'2018-05-01 12:00:00',1,1),(2,0,78456,150.00,30.00,'2018-05-02 13:05:00',2,2),(3,2,125108,121.78,27.34,'2018-05-04 14:50:00',1,1),(4,0,78994,185.94,37.61,'2018-05-05 22:15:00',2,2),(5,0,79621,61.02,12.02,'2018-05-08 08:20:30',1,2);
INSERT INTO `users` VALUES (1,'user1'),(2,'user2');
INSERT INTO `users_vehicles` VALUES (1,1),(1,2),(2,2);
INSERT INTO `vehicles` VALUES (1,123456,'RZ01234'),(2,78001,'RZE45678');