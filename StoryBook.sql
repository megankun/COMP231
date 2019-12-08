DROP database StoryBook;
Create database StoryBook;
use StoryBook;

create table User
(userId int PRIMARY KEY AUTO_INCREMENT,
email varchar(255),
password varchar(255),
userType varchar(255),
firstName varchar(255),
lastName varchar(255),
phoneNumber varchar(255));

INSERT INTO `StoryBook`.`User`
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

INSERT INTO `StoryBook`.`User`
(`email`,`password`,`userType`,`firstName`,`lastName`,`phoneNumber`)
VALUES
('writer@test.com',
'123','Writer','Writer','test','1234567890' );

INSERT INTO `StoryBook`.`User`
(`email`,`password`,`userType`,`firstName`,`lastName`,`phoneNumber`)
VALUES
('editor@test.com','123','Editor','Editor','test','1234567890' );

INSERT INTO `StoryBook`.`User`
(`email`,`password`,`userType`,`firstName`,`lastName`,`phoneNumber`)
VALUES
('investor@test.com','123','Investor','Investor','test','1234567890' );


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

 INSERT INTO `StoryBook`.`User`
(
`email`,
`password`,
`userType`,
`firstName`,
`lastName`,
`phoneNumber`)
VALUES
('kj@g.com',
'123','Writer','Kyungjin','Jeong','1234567890' );

INSERT INTO BOOK VALUES(1, 2, "The Little Prince", "Novel");
INSERT INTO BOOK VALUES(2, 2, "The Lord of the Rings", "Fantasy");
INSERT INTO BOOK VALUES(3, 2, "Harry Potter and the Philosopher's Stone", "Fantasy");
INSERT INTO BOOK VALUES(4, 1, "The Lion, the Witch and the Wardrobe", "Fantasy");
INSERT INTO BOOK VALUES(5, 2, "It", "Horror, Thriller");

ALTER TABLE Story CHANGE COLUMN `subTitle` `chapterTitle` VARCHAR(255);
ALTER TABLE Story DROP COLUMN `location`;


---------------------------------------------------- Kyungjin (2019-11-08)
DROP TABLE Story_BookCharacter;
DROP TABLE Story_BookLocation;
DROP TABLE BookCharacter;
DROP TABLE BookLocation;
-- DROP TABLE Story_Character;
-- DROP TABLE `Character`;


create table BookCharacter(
characterId int PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
age int(100),
appereance varchar(255),
bookId int not null,
FOREIGN KEY(bookId) REFERENCES Book(bookId)
);

create table Story_BookCharacter(
storyId  int not null,
characterId int not null,
FOREIGN KEY(storyId) REFERENCES Story(storyId),
FOREIGN KEY(characterId) REFERENCES BookCharacter(characterId));
 

create table Location(
locationId int PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
description longblob,
bookId int not null,
FOREIGN KEY(bookId) REFERENCES Book(bookId)
);

create table Story_Location(
storyId  int not null,
locationId int not null,
FOREIGN KEY(storyId) REFERENCES Story(storyId),
FOREIGN KEY(locationId) REFERENCES Location(locationId));

INSERT INTO Location Values(1, 'Centennial College Library L3-22', '1 table and 8 chairs', 1);
INSERT INTO Location Values(2, 'Centennial College Library L3-24', '1 table and 8 chairs', 1);
INSERT INTO Location Values(3, 'Centennial College Library L3-26', '1 table and 8 chairs', 1);


INSERT INTO Location Values(4, 'Denbrough House', 'Denbrough House is not only a house, but a location located at Derry, Maine. Home to Bill and Georgie Denbrough.', 5);
INSERT INTO Location Values(5, 'The Well House', 'According to Richie, it is a creepy-ass house where junkies and hobos like to sleep.', 5);

ALTER TABLE Location CHANGE COLUMN `description` `description` Text;

INSERT INTO BookCharacter VALUES (1, 'Kyungjin', 20, 'black hair', 1);
INSERT INTO BookCharacter VALUES (2, 'Yeseul', 20, 'black hair and brown eyes', 1);


INSERT INTO BookCharacter VALUES (3, 'Beverly Marsh', 11, 'Beverly has reddish auburn hair that reaches her shoulder blades, lovely gray-green eyes and milky skin with a spray of freckles.', 5);
INSERT INTO BookCharacter VALUES (4, 'Bill Denbrough', 12, 'Bill is described as a handsome boy with cropped red hair, dancing blue eyes, and a small frame along with a goofball grin whenever he rides his bike, Silver.', 5);
INSERT INTO BookCharacter VALUES (5, 'Ben Hanscom', 11, 'Ben is described as having short brown hair that spikes when its wet (like when building the Dam) and is obese for his age.', 5);




ALTER TABLE Story CHANGE COLUMN `note` `note` Text;

ALTER TABLE Story ADD COLUMN created_at DATETIME;




DELETE FROM Story_Location WHERE storyId >= 0;
DELETE FROM Story_BookCharacter WHERE storyId >= 0;
DELETE FROM Story WHERE storyId >= 0;

SELECT * FROM Story;
SELECT * FROM Story_Location;
SELECT * FROM Story_BookCharacter;



---------------------------------------------------- Megan (2019-11-24)
ALTER TABLE User ADD COLUMN aboutUser Text;

---------------------------------------------------- Fu (2019-12-1)
ALTER TABLE Payment ADD COLUMN bookId int;


---------------------------------------------------- Megan (2019-12-01)
ALTER TABLE Book ADD COLUMN bookDescription Text;



INSERT INTO Story 
(`storyId`,`bookId`,`chapterTitle`,`note`,`estimatedTime`,`created_at`) 
VALUES (1,5,'It','The film opens in October 1988 with Bill Denbrough and his seven-year-old brother, Georgie, making a sailboat out of a sheet of paper from Bill\'s notebook. Georgie, as his older brother is bedridden, then has to go to the basement to retrieve the paraffin, in order to make the boat float. There is a tense scene in which Georgie stares, in horror, at a pair of red eyes glaring at him. But, as he shines his torch at them, they are revealed to be a pair of two light bulbs. ',NULL,'2019-12-07 18:11:25');
INSERT INTO Story 
(`storyId`,`bookId`,`chapterTitle`,`note`,`estimatedTime`,`created_at`) 
VALUES (2,5,'Chapter 2','The remaining Losers, now in their late-thirties, have small recollections of their childhood memories. Bill Denbrough is a successful author and screenwriter who often gets criticized for his stories endings. Beverly Marsh lives as a fashion designer but endures an abusive relationship with her husband, Tom Rogan. Ben Hanscom has lost weight and is a successful architect. Richie Tozier has become a famous stand-up comedian. Eddie Kaspbrak is a risk assessor, with an overbearing wife, and Stanley Uris is a partner in a large accounting firm. All are deeply disturbed by the phone call from Mike, but reluctantly agree to return to Derry, except for Stanley, who commits suicide.',NULL,'2019-12-07 18:13:02');


INSERT INTO Story_BookCharacter 
(`storyId`,`characterId`) 
VALUES (1,3);
INSERT INTO Story_BookCharacter 
(`storyId`,`characterId`) 
VALUES (1,4);
INSERT INTO Story_BookCharacter 
(`storyId`,`characterId`) 
VALUES (1,5);


INSERT INTO Story_BookCharacter 
(`storyId`,`characterId`) 
VALUES (2,4);
INSERT INTO Story_BookCharacter 
(`storyId`,`characterId`) 
VALUES (2,5);


INSERT INTO Story_Location 
(`storyId`,`locationId`) 
VALUES (1,4);
INSERT INTO Story_Location 
(`storyId`,`locationId`) 
VALUES (2,5);



UPDATE Book
SET bookDescription = 'IT follows the story of seven preteen children from the fictional town of Derry, Maine who are stalked and terrorized by an evil eldritch entity they only know as IT. The mysterious creature has the ability to easily shapeshift and disguise itself to its prey, and uses their greatest and deepest psychological fears against them. One of ITs favorite disguises is the appearance of a circus clown called Pennywise.'
WHERE bookId = 5;



UPDATE User
SET aboutUser = 'I am a writer. I write books and create stories. I might be crazy'
WHERE userId = 2;



UPDATE User
SET aboutUser = 'I am Yeseul'
WHERE userId = 1;


