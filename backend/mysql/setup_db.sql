CREATE DATABASE `flotteLotte`;
USE `flotteLotte`;
CREATE TABLE `user` (
  `id` int(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email_address` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) DEFAULT CHARSET=latin1;
CREATE TABLE `trips` (
  `id` int(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `point_of_departure` varchar(60) DEFAULT NULL,
  `destination` varchar(60) DEFAULT NULL,
  `trip_start` bigint(20) UNSIGNED DEFAULT NULL,
  `trip_finish` bigint(20) UNSIGNED DEFAULT NULL,
  `driver` boolean NOT NULL
) DEFAULT CHARSET=latin1;

-- Beispieldaten
INSERT INTO `user` (`first_name`, `last_name`, `email_address`, `password`)
VALUES
( 'Anton', 'Weber', 'anton@weber.de', 'anton'),
( 'Gunther', 'Schneider', 'gunther@schneider.de', 'gunther')

INSERT INTO `trips`
VALUES
('1', 'Jahnstraße 2, 02906 Niesky', 'Bahnhofstraße 35, 02826 Görlitz', 1697882400000, 1697882400000, 1),
('1', 'Jahnstraße 2, 02906 Niesky', 'Paul-Taubadel-Straße, 02827 Görlitz', 1697882400000, 1697882400000, 1),
('1', 'Jahnstraße 2, 02906 Niesky', 'Elisabethplatz, 02826 Görlitz', 1697882400000, 1697882400000, 1),
('2', 'Helmut-Just Straße 4, Ödernitz', 'Jakobstraße 5, 02826 Görlitz', 1697882400000, 1697882400000, 0),