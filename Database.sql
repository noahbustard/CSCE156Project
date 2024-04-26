/*
Drops any existing tables to ensure only data listed
below has been inserted into the tables
*/
use nbustard2;

drop table if exists Email;
drop table if exists SaleItem;
drop table if exists Item;
drop table if exists Sale;
drop table if exists Store;
drop table if exists Person;
drop table if exists Address;

create table Address (
addressId int not null primary key auto_increment,
street varchar(256) not null,
city varchar(256) not null,
state varchar(20) not null,
zip int not null
);

create table Person (
personId int not null primary key auto_increment,
uuid varchar(36) not null unique,
firstName varchar(256) not null,
lastName varchar(256) not null,
addressId int not null,
foreign key (addressId) references Address(addressId)
);

create table Store (
storeId int not null primary key auto_increment,
storeCode varchar(256) not null unique,
addressId int not null,
managerId int not null,
foreign key (addressId) references Address(addressId),
foreign key (managerId) references Person(personId)
);

create table Sale (
saleId int not null primary key auto_increment,
saleCode varchar(256) not null unique,
date varchar(10) not null,
storeId int not null,
customerId int not null,
salespersonId int not null,
foreign key (storeId) references Store(storeId),
foreign key (customerId) references Person(personId),
foreign key (salespersonId) references Person(personId)
);

create table Item(
itemId int not null primary key auto_increment,
itemCode varchar(256) not null unique,
baseCost double not null,
type varchar(1) not null,
name varchar(256) not null
);

create table SaleItem (
saleItemId int not null primary key auto_increment,
startDate varchar(10),
endDate varchar(10),
hoursBilled double,
servicemanId int,
gbsPurchased double,
phoneNumber varchar(12),
daysPurchased int,
itemId int not null,
saleId int not null,
foreign key (itemId) references Item(itemId),
foreign key (saleId) references Sale(saleId),
foreign key (servicemanId) references Person(personId)
);

create table Email (
emailId int not null primary key auto_increment,
address varchar(256) not null,
personId int not null,
foreign key (personId) references Person(personId)
);


insert into Item (itemId,itemCode,type,name,baseCost) values (1,"e001","P","BlackBerry 4", 0.01);
insert into Item (itemId,itemCode,type,name,baseCost) values (2,"e002","P","iPhone X", 999.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (3,"s001","S","Water Damage", 100.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (4,"s002","S","Screen Repair", 76.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (5,"s003","S","Camera Repair", 200.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (6,"p001","D","LTE Deluxe", 30.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (7,"p002","V","Unlimited Daily", 50.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (8,"e004","P","iPhone 11", 1100.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (9,"e005","P","iPhone 14", 1600.00);
insert into Item (itemId,itemCode,type,name,baseCost) values (10,"e006","P","iPhone 15", 1700.00);

insert into Address (addressId,street,city,state,zip) values (1,"250 Tomscot","Roanoke","VA",66205);
insert into Address (addressId,street,city,state,zip) values (2,"1946 Farwell","dallas","TX",85072);
insert into Address (addressId,street,city,state,zip) values (3,"101 Autumn Leaf","Fort Wayne","IN",46857);
insert into Address (addressId,street,city,state,zip) values (4,"4682 Quincy","Kalamazoo","MI",49048);
insert into Address (addressId,street,city,state,zip) values (5,"02872 Bartillon","Brooklyn","NY",11236);
insert into Address (addressId,street,city,state,zip) values (6,"1234 Lotheville","Saint Petersburg","FL",33705);
insert into Address (addressId,street,city,state,zip) values (7,"9876 Goodland","Raleigh","NC",27615);
insert into Address (addressId,street,city,state,zip) values (8,"457612 Petterle","Lav Vegas","NV",89160);
insert into Address (addressId,street,city,state,zip) values (9,"1 Maywood","San Jose","CA",95155);
insert into Address (addressId,street,city,state,zip) values (10,"999 Goodland","Oklahoma City","OK",73157);
insert into Address (addressId,street,city,state,zip) values (11,"666 Messerschmidt","Bethlemehem","PA",18018);
insert into Address (addressId,street,city,state,zip) values (12,"333 Sunbrook","Roanoke","VA",24029);

insert into Person (personId,uuid,firstName,lastName,addressId) values (1,"7b70a695-33c1-4a1a-9c95-51054f4017f6","Edithe","Driffill",3);
insert into Person (personId,uuid,firstName,lastName,addressId) values (2,"fdce2d71-7a42-42c8-b6a3-53dad5d21194","Sherline","Bowerman",4);
insert into Person (personId,uuid,firstName,lastName,addressId) values (3,"9a70a695-33c1-4a1a-9c95-51054f4018f6","Rockey","Barrick",5);
insert into Person (personId,uuid,firstName,lastName,addressId) values (4,"65405595-a97d-4939-adf4-71dcc2ff7b3d","Krystalle","Shier",6);
insert into Person (personId,uuid,firstName,lastName,addressId) values (5,"961b7c38-6c9b-463c-8a8d-a784fd0afd9c","Sisile","Lammerts",7);
insert into Person (personId,uuid,firstName,lastName,addressId) values (6,"20ff9b2b-715a-4833-b56a-e81a987e0ac8","Costa","Martino",8);
insert into Person (personId,uuid,firstName,lastName,addressId) values (7,"f934513d-2072-494c-ad1e-fcaea12e3784","Verne","Lynes",9);
insert into Person (personId,uuid,firstName,lastName,addressId) values (8,"92693cae-5788-47e9-9d67-cb1a3355b1f1","Olympia","Mitrovic",10);
insert into Person (personId,uuid,firstName,lastName,addressId) values (9,"fdf2e7ff-c814-47ea-8f85-df79d5cf9eef","Tulley","McConville",11);
insert into Person (personId,uuid,firstName,lastName,addressId) values (10,"fdce2d71-7a42-42c8-b6a3-53dad5d21193","Poppy","Parmby",12);

insert into Store (storeId,storeCode,addressId, managerId) values (1,"25d901",1,4);
insert into Store (storeId,storeCode,addressId, managerId) values (2,"3c0234",2,5);

insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (1,"s001","2023-12-01",2,1,4);
insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (2,"s002","2023-11-03",2,2,4);
insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (3,"s003","2023-11-15",1,1,5);


insert into SaleItem (saleItemId,itemId,saleId) values (1,1,1);
insert into SaleItem (saleItemId,startDate,endDate,itemId,saleId) values (2,"2023-01-01","2025-12-25",2,2);
insert into SaleItem (saleItemId,hoursBilled,servicemanId,itemId,saleId) values (3,2.0,6,3,3);
insert into SaleItem (saleItemId,gbsPurchased,itemId,saleId) values (4,150.0,6,1);
insert into SaleItem (saleItemId,phoneNumber,daysPurchased,itemId,saleId) values (5,"402-472-2401",95,7,2);



insert into Email (emailId, address, personId) values (1,"edriffill0@timesonline.co.uk",1);
insert into Email (emailId, address, personId) values (2,"sbowerman1@reuters.com",2);
insert into Email (emailId, address, personId) values (3,"rbarrick2@dot.gov",3);
insert into Email (emailId, address, personId) values (4,"kshier3@aol.com",4);
insert into Email (emailId, address, personId) values (5,"slammerts4@yellowbook.com",5);
insert into Email (emailId, address, personId) values (6,"cmartino5@reference.com",6);
insert into Email (emailId, address, personId) values (7,"vlynes6@thetimes.co.uk",7);
insert into Email (emailId, address, personId) values (8,"omitrovic7@freewebs.com",8);
insert into Email (emailId, address, personId) values (9,"tmcconville8@ifeng.com",9);
insert into Email (emailId, address, personId) values (10,"pparmby9@drupal.org",10);
