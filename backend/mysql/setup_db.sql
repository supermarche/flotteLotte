SET NAMES 'utf8' COLLATE 'utf8_unicode_ci';

CREATE DATABASE `flotteLotte`;
USE `flotteLotte`;
CREATE TABLE `user` (
  `id` int(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email_address` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) DEFAULT charset utf8;
CREATE TABLE `trips` (
  `id` int(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `point_of_departure` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `destination` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL,
  `trip_start` bigint(20) UNSIGNED DEFAULT NULL,
  `trip_finish` bigint(20) UNSIGNED DEFAULT NULL,
  `driver` boolean NOT NULL
) DEFAULT charset utf8;
CREATE TABLE `match` (
  `id` int(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `trip_id_driver` int(10) NOT NULL,
  `trip_id_passenger` int(10) NOT NULL
) DEFAULT charset utf8;
CREATE TABLE `messages` (
  `id` int(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `receiver` int(10) NOT NULL,
  `message` varchar(250) NOT NULL
) DEFAULT charset utf8;

-- Beispieldaten
INSERT INTO `user` (`first_name`, `last_name`, `email_address`, `password`)
VALUES
( 'Anton', 'Weber', 'anton@weber.de', 'anton'),
( 'Gunther', 'Schneider', 'gunther@schneider.de', 'gunther');

INSERT INTO `trips` (`userid`, `point_of_departure`, `destination`, `trip_start`, `trip_finish`, `driver`)
VALUES
('1', 'Jahnstraße 2, 02906 Niesky', 'Bahnhofstraße 35, 02826 Görlitz', 1697882400000, 1697882400000, 1),
('1', 'Jahnstraße 2, 02906 Niesky', 'Paul-Taubadel-Straße, 02827 Görlitz', 1697882400000, 1697882400000, 1),
('1', 'Jahnstraße 2, 02906 Niesky', 'Elisabethplatz, 02826 Görlitz', 1697882400000, 1697882400000, 1),
('2', 'Helmut-Just Straße 20, Ödernitz', 'Jakobstraße 5, 02826 Görlitz', 1697882400000, 1697882400000, 0);

INSERT INTO `match` (`trip_id_driver`, `trip_id_passenger`)
VALUES
('1', '4');