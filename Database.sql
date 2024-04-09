/*
Drops any existing tables to ensure only data listed
below has been inserted into the tables
*/

drop table if exists Email;
drop table if exists SaleItem;
drop table if exists Item;
drop table if exists Sale;
drop table if exists Store;
drop table if exists Person;
drop table if exists Address;
drop table if exists Zip;
drop table if exists City;
drop table if exists State;


create table State (
stateId int not null primary key auto_increment,
abbreviation varchar(2)
);

create table City (
cityId int not null primary key auto_increment,
name varchar(256) not null,
stateId int not null,
foreign key (stateId) references State(stateId)
);

create table Zip (
zipId int not null primary key auto_increment,
zipcode int(5) not null,
cityId int not null,
foreign key (cityId) references City(cityId)
);

create table Address (
addressId int not null primary key auto_increment,
street varchar(40) not null,
cityId int not null,
stateId int not null,
zipId int not null,
foreign key (stateId) references State(stateId),
foreign key (cityId) references City(cityId),
foreign key (zipId) references Zip(zipId)
);

create table Person (
personId int not null primary key auto_increment,
firstName varchar(256) not null,
lastName varchar(256) not null,
addressId int not null,
foreign key (addressId) references Address(addressId)
);

create table Store (
storeId int not null primary key auto_increment,
storeCode varchar(6) not null,
addressId int not null,
managerId int not null,
foreign key (addressId) references Address(addressId),
foreign key (managerId) references Person(personId)
);

create table Sale (
saleId int not null primary key auto_increment,
saleCode varchar(4) not null,
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
itemCode varchar(4) not null,
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

insert into State (stateId, abbreviation) values (1,"VA");
insert into State (stateId, abbreviation) values (2,"TX");
insert into State (stateId, abbreviation) values (3,"IN");
insert into State (stateId, abbreviation) values (4,"MI");
insert into State (stateId, abbreviation) values (5,"NY");
insert into State (stateId, abbreviation) values (6,"FL");
insert into State (stateId, abbreviation) values (7,"NC");
insert into State (stateId, abbreviation) values (8,"NV");
insert into State (stateId, abbreviation) values (9,"CA");
insert into State (stateId, abbreviation) values (10,"OK");
insert into State (stateId, abbreviation) values (11,"PA");

insert into City (cityId,name,stateId) values (1,"Roanoke",1);
insert into City (cityId,name,stateId) values (2,"Dallas",2);
insert into City (cityId,name,stateId) values (3,"Fort Wayne",3);
insert into City (cityId,name,stateId) values (4,"Kalamazoo",4);
insert into City (cityId,name,stateId) values (5,"Brooklyn",5);
insert into City (cityId,name,stateId) values (6,"Saint Petersburg",6);
insert into City (cityId,name,stateId) values (7,"Raleigh",7);
insert into City (cityId,name,stateId) values (8,"Las Vegas",8);
insert into City (cityId,name,stateId) values (9,"San Jose",9);
insert into City (cityId,name,stateId) values (10,"Oklahoma City",10);
insert into City (cityId,name,stateId) values (11,"Bethlehem",11);

insert into Zip (zipId,zipcode,cityId) values (1,66205,1);
insert into Zip (zipId,zipcode,cityId) values (2,85072,2);
insert into Zip (zipId,zipcode,cityId) values (3,46857,3);
insert into Zip (zipId,zipcode,cityId) values (4,49048,4);
insert into Zip (zipId,zipcode,cityId) values (5,11236,5);
insert into Zip (zipId,zipcode,cityId) values (6,33705,6);
insert into Zip (zipId,zipcode,cityId) values (7,27615,7);
insert into Zip (zipId,zipcode,cityId) values (8,89160,8);
insert into Zip (zipId,zipcode,cityId) values (9,95155,9);
insert into Zip (zipId,zipcode,cityId) values (10,73157,10);
insert into Zip (zipId,zipcode,cityId) values (11,18018,11);
insert into Zip (zipId,zipcode,cityId) values (12,24029,1);

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

insert into Address (addressId,street,cityId,stateId,zipId) values (1,"250 Tomscot",1,1,1);
insert into Address (addressId,street,cityId,stateId,zipId) values (2,"1946 Farwell",2,2,2);
insert into Address (addressId,street,cityId,stateId,zipId) values (3,"101 Autumn Leaf",3,3,3);
insert into Address (addressId,street,cityId,stateId,zipId) values (4,"4682 Quincy",4,4,4);
insert into Address (addressId,street,cityId,stateId,zipId) values (5,"02872 Bartillon",5,5,5);
insert into Address (addressId,street,cityId,stateId,zipId) values (6,"1234 Lotheville",6,6,6);
insert into Address (addressId,street,cityId,stateId,zipId) values (7,"9876 Goodland",7,7,7);
insert into Address (addressId,street,cityId,stateId,zipId) values (8,"457612 Petterle",8,8,8);
insert into Address (addressId,street,cityId,stateId,zipId) values (9,"1 Maywood",9,9,9);
insert into Address (addressId,street,cityId,stateId,zipId) values (10,"999 Goodland",10,10,10);
insert into Address (addressId,street,cityId,stateId,zipId) values (11,"666 Messerschmidt",11,11,11);
insert into Address (addressId,street,cityId,stateId,zipId) values (12,"333 Sunbrook",1,1,12);

insert into Person (personId,firstName,lastName,addressId) values (1,"Edithe","Driffill",3);
insert into Person (personId,firstName,lastName,addressId) values (2,"Sherline","Bowerman",4);
insert into Person (personId,firstName,lastName,addressId) values (3,"Rockey","Barrick",5);
insert into Person (personId,firstName,lastName,addressId) values (4,"Krystalle","Shier",6);
insert into Person (personId,firstName,lastName,addressId) values (5,"Sisile","Lammerts",7);
insert into Person (personId,firstName,lastName,addressId) values (6,"Costa","Martino",8);
insert into Person (personId,firstName,lastName,addressId) values (7,"Verne","Lynes",9);
insert into Person (personId,firstName,lastName,addressId) values (8,"Olympia","Mitrovic",10);
insert into Person (personId,firstName,lastName,addressId) values (9,"Tulley","McConville",11);
insert into Person (personId,firstName,lastName,addressId) values (10,"Poppy","Parmby",12);

insert into Store (storeId,storeCode,addressId, managerId) values (1,"25d901",1,4);
insert into Store (storeId,storeCode,addressId, managerId) values (2,"3c0234",2,5);

insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (1,"s001","2023-12-01",2,1,4);
insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (2,"s002","2023-11-03",2,2,4);
insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (3,"s003","2023-11-15",1,1,5);
insert into Sale (saleId,saleCode,date,storeId,customerId,salespersonId) values (4,"s004","2023-11-15",1,5,5);

insert into SaleItem (saleItemId,itemId,saleId) values (1,1,1);
insert into SaleItem (saleItemId,startDate,endDate,itemId,saleId) values (2,"2023-01-01","2025-12-25",2,2);
insert into SaleItem (saleItemId,hoursBilled,servicemanId,itemId,saleId) values (3,2.0,6,3,3);
insert into SaleItem (saleItemId,gbsPurchased,itemId,saleId) values (4,150.0,6,1);
insert into SaleItem (saleItemId,phoneNumber,daysPurchased,itemId,saleId) values (5,"402-472-2401",95,7,2);
insert into SaleItem (saleItemId,gbsPurchased,itemId,saleId) values (6,10.0,6,1);
insert into SaleItem (saleItemId,gbsPurchased,itemId,saleId) values (7,25.5,6,2);
insert into SaleItem (saleItemId,itemId,saleId) values (8,2,4);

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
