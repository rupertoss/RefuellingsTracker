INSERT INTO `refuel`.`users` (`id`,`name`) VALUES ('1','user1');
INSERT INTO `refuel`.`users` (`id`,`name`) VALUES ('2','user2');

INSERT INTO `refuel`.`vehicles` (`id`, `mileage`, `plate_number`) VALUES ('1', '123456', 'RZ01234');
INSERT INTO `refuel`.`vehicles` (`id`, `mileage`, `plate_number`) VALUES ('2', '78001', 'RZE45678');

INSERT INTO `refuel`.`refuellings` (`id`, `fuel_type`, `mileage`, `price`, `quantity`, `timestamp`, `user_id`, `vehicle_id`) VALUES ('1', '2', '124578', '174.53', '42.12', '2018-05-01 12:00:00', '1', '1');
INSERT INTO `refuel`.`refuellings` (`id`, `fuel_type`, `mileage`, `price`, `quantity`, `timestamp`, `user_id`, `vehicle_id`) VALUES ('2', '0', '78456', '150.00', '30.00', '2018-05-02 13:05:00', '2', '2');
INSERT INTO `refuel`.`refuellings` (`id`, `fuel_type`, `mileage`, `price`, `quantity`, `timestamp`, `user_id`, `vehicle_id`) VALUES ('3', '2', '125108', '121.78', '27.34', '2018-05-04 14:50:00', '1', '1');
INSERT INTO `refuel`.`refuellings` (`id`, `fuel_type`, `mileage`, `price`, `quantity`, `timestamp`, `user_id`, `vehicle_id`) VALUES ('4', '0', '78994', '185.94', '37.61', '2018-05-05 22:15:00', '2', '2');
INSERT INTO `refuel`.`refuellings` (`id`, `fuel_type`, `mileage`, `price`, `quantity`, `timestamp`, `user_id`, `vehicle_id`) VALUES ('5', '0', '79621', '61.02', '12.02', '2018-05-08 08:20:30', '1', '2');

INSERT INTO `refuel`.`users_vehicles` (`user_id`, `vehicle_id`) VALUES ('1', '1');
INSERT INTO `refuel`.`users_vehicles` (`user_id`, `vehicle_id`) VALUES ('1', '2');
INSERT INTO `refuel`.`users_vehicles` (`user_id`, `vehicle_id`) VALUES ('2', '2');



