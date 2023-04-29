# Spring Webflux Stack with R2DBC

Spring Webflux Stack with R2DBC를 학습하는 리포지토리

Database : mariadb <br>
ORM : None

## Database Setting
### Install
docker run -p 3306:3306 --name mariadb -e MYSQL_ROOT_PASSWORD= 1234 -d mariadb:10.11.2

### Create Database
create database study;

### Create Table
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`)
)

