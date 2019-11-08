Create database Team2;
use Team2;

create table User
(userId int PRIMARY KEY AUTO_INCREMENT,
email varchar(255),
password varchar(255),
userType varchar(255),
firstName varchar(255),
lastName varchar(255),
phoneNumber varchar(255));

INSERT INTO `Team2`.`User`
(
`email`,
`password`,
`userType`,
`firstName`,
`lastName`,
`phoneNumber`)
VALUES
('yeseul@test.com',
'123','Writer','Yeseul','Jang','1234567890' );


create table Payment(
paymentId int PRIMARY KEY AUTO_INCREMENT,
userId int not null,
cardType varchar(255),
nameOnCard varchar(255),
cardNumber varchar(255),
expiryDate varchar(255),
cvc varchar(255),
country varchar(255),
zipCode varchar(255),
totalPrice varchar(255),
 FOREIGN KEY(userId) REFERENCES User(userId));
 
 create table Book
 (bookId int PRIMARY KEY AUTO_INCREMENT,
 userId int not null,
 title  varchar(255),
 genre  varchar(255),
  FOREIGN KEY(userId) REFERENCES User(userId));
 
create table Story(
storyId int PRIMARY KEY AUTO_INCREMENT,
bookId int not null,
subTitle varchar(255),
location varchar(255),
note longblob,
estimatedTime varchar(255),
 FOREIGN KEY(bookId) REFERENCES Book(bookId));
 
create table BookCharacter(
charactertId int PRIMARY KEY AUTO_INCREMENT,
characterName varchar(255),
age int(100),
appereance varchar(255));

create table Story_BookCharacter(
storyId  int not null,
charactertId int not null,
FOREIGN KEY(storyId) REFERENCES Story(storyId),
FOREIGN KEY(charactertId) REFERENCES BookCharacter(charactertId));
 
 
---------------------------------------------------- Kyungjin (2019-11-07)

create table BookLocation(
locationId int PRIMARY KEY AUTO_INCREMENT,
locationName varchar(255),
description longblob
);

create table Story_BookLocation(
storyId  int not null,
locationId int not null,
FOREIGN KEY(storyId) REFERENCES Story(storyId),
FOREIGN KEY(locationId) REFERENCES BookLocation(locationId));
 
INSERT INTO Book Values(1, 2, 'The Little Prince', 'Novel');
INSERT INTO Book Values(2, 2, 'The Lord of the Rings', 'Fantasy');
INSERT INTO Book Values(3, 2, 'Harry Potter and the Philosopher\'s Stone', 'Fantasy');
INSERT INTO Book Values(4, 1, 'The Lion, the Witch and the Wardrobe', 'Fantasy');
 
ALTER TABLE Story CHANGE COLUMN `subTitle` `chapterTitle` varchar(255);
ALTER TABLE Story DROP COLUMN location;
 