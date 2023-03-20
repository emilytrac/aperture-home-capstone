create database aperture;

use aperture;

 -- Users of the app

create table user(
userId int auto_increment primary key,
userEmail varchar(40) unique not null,
firstName varchar(40) not null,
lastName varchar(40) not null,
userPassword varchar(40) not null
);

describe user;

insert into user(userEmail, firstName, lastName, userPassword)
values('a.harper@email.com', 'Alison', 'Harper', 'password'),
('j.smith@email.com', 'James', 'Smith', 'password'),
('e.jones@email.com', 'Emma', 'Jones', 'password'),
('f.robinson@email.com', 'Felicity', 'Robinson', 'password'),
('a.bowen@email.com', 'Andy', 'Bowen', 'password')
;

select * from user;

 -- Products sold by Aperture
 
 drop table product;
 
create table product(
productId int auto_increment primary key,
productName varchar(40) unique not null,
productCategory varchar(40) not null,
quantityAvailable int not null,
pricePerItem double not null,
quantitySold int not null
);

describe product;

insert into product(productName, productCategory, quantityAvailable, pricePerItem, quantitySold)
values('cuckoo clock', 'home decorations', 29, 55, 221),
('retro clock', 'home decorations', 112, 35, 388),
('gold mirror', 'home decorations', 252, 50, 248),
('oak mirror', 'home decorations', 352, 55, 148),
('linen candle', 'home decorations', 347, 12, 453),
('citrus candle', 'home decorations', 364, 12, 439),
('ornate vase', 'home decorations', 115, 40, 135),
('neutral rug', 'textiles and rugs', 99, 75, 151),
('persian rug', 'textiles and rugs', 51, 120, 49),
('woodland linen', 'textiles and rugs', 367, 60, 433),
('flower linen', 'textiles and rugs', 421, 60, 379),
('woodland cushions', 'textiles and rugs', 354, 40, 446),
('flower cushions', 'textiles and rugs', 398, 40, 402),
('abstract painting', 'art and prints', 230, 35, 570),
('star painting', 'art and prints', 168, 35, 632),
('wicker basket', 'organisation', 69, 30, 181),
('wooden keyhold', 'organisation', 121, 25, 129),
('gold bookends', 'organisation', 85, 15, 165),
('pink vase', 'plant decorations', 348, 20, 452),
('green vase', 'plant decorations', 267, 20, 533),
('fake monstera', 'plant decorations', 233, 30, 567),
('fake ivy', 'plant decorations', 345, 25, 445)
;

select * from product;