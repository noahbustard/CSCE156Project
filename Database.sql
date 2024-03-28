use nbustard2;


drop table if exists SaleItem;
drop table if exists Item;
drop table if exists Sale;
drop table if exists Person;
drop table if exists Store;
drop table if exists Address;

create table Address (
  addressId int not null primary key auto_increment,
  street varchar(256) not null,
  city varchar(256) not null,
  state varchar(256) not null,
  zip int not null
  );

create table Store (
storeId int not null primary key auto_increment,
addressId int not null,
foreign key (addressId) references Address(addressId)
);

create table Person (
personId int not null primary key auto_increment,
firstName varchar(256) not null,
lastName varchar(256) not null,
storeId int,
addressId int,
foreign key (storeId) references Store(storeId),
foreign key (addressId) references Address(addressId)
);

create table Sale (
saleId int not null primary key auto_increment,
date varchar(10) not null,
storeId int,
customerId int,
salespersonId int,
foreign key (storeId) references Store(storeId),
foreign key (customerId) references Person(personId),
foreign key (salespersonId) references Person(personId)
);

create table Item(
itemId int not null primary key auto_increment,
type varchar(1),
name varchar(256),
baseCost double
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
itemId int,
saleId int,
foreign key (itemId) references Item(itemId),
foreign key (saleId) references Sale(saleId)
);

insert into Item (itemId,type,name,baseCost) values (1,"P","BlackBerry 4", 0.01);
insert into Item (itemId,type,name,baseCost) values (2,"P","iPhone X", 999.00);
insert into Item (itemId,type,name,baseCost) values (3,"S","Water Damage", 100.00);
insert into Item (itemId,type,name,baseCost) values (4,"S","Screen Repair", 76.00);
insert into Item (itemId,type,name,baseCost) values (5,"S","Camera Repair", 200.00);
insert into Item (itemId,type,name,baseCost) values (6,"D","LTE Deluxe", 30.00);
insert into Item (itemId,type,name,baseCost) values (7,"V","Unlimited Daily", 50.00);
insert into Item (itemId,type,name,baseCost) values (8,"P","iPhone 11", 1100.00);
insert into Item (itemId,type,name,baseCost) values (9,"P","iPhone 14", 1600.00);
insert into Item (itemId,type,name,baseCost) values (10,"P","iPhone 15", 1700.00);

insert into Sale (saleId, date) values (1,"2023-12-01");
insert into Sale (saleId, date) values (2,"2023-11-03");
insert into Sale (saleId, date) values (3,"2023-11-15");

insert into Address (addressId,street,city,state,zip) values (1,"250 Tomscot","Roanoke","VA",66205);
insert into Address (addressId,street,city,state,zip) values (2,"1946 Farwell","Dallas","TX",85072);
insert into Address (addressId,street,city,state,zip) values (3,"101 Autumn Leaf","Fort Wayne","IN",46857);
insert into Address (addressId,street,city,state,zip) values (4,"4682 Quincy","Kalamazoo","MI",49048);
insert into Address (addressId,street,city,state,zip) values (5,"02872 Bartillon","Brooklyn","NY",11236);
insert into Address (addressId,street,city,state,zip) values (6,"1234 Lotheville","Saint Petersburg","FL",33705);
insert into Address (addressId,street,city,state,zip) values (7,"9876 Goodland","Raleigh","NC",27615);
insert into Address (addressId,street,city,state,zip) values (8,"457612 Petterle","Las Vegas","NV",89160);
insert into Address (addressId,street,city,state,zip) values (9,"1 Maywood","San Jose","CA",95155);
insert into Address (addressId,street,city,state,zip) values (10,"999 Goodland","Oklahoma City","OK",73157);
insert into Address (addressId,street,city,state,zip) values (11,"666 Messerschmidt","Bethlehem","PA",18018);
insert into Address (addressId,street,city,state,zip) values (12,"333 Sunbrook","Roanoke","VA",24029);

insert into Store (storeId,addressId) values (1,1);
insert into Store (storeId,addressId) values (2,2);

insert into Person (personId,firstName,lastName,addressId) values (1,"Edithe","Driffill",3);
insert into Person (personId,firstName,lastName,addressId) values (2,"Sherline","Bowerman",4);
insert into Person (personId,firstName,lastName,addressId) values (3,"Rockey","Barrick",5);
insert into Person (personId,firstName,lastName,storeId,addressId) values (4,"Krystalle","Shier",1,6);
insert into Person (personId,firstName,lastName,storeId,addressId) values (5,"Sisile","Lammerts",2,7);
insert into Person (personId,firstName,lastName,addressId) values (6,"Costa","Martino",8);
insert into Person (personId,firstName,lastName,addressId) values (7,"Verne","Lynes",9);
insert into Person (personId,firstName,lastName,addressId) values (8,"Olympia","Mitrovic",10);
insert into Person (personId,firstName,lastName,addressId) values (9,"Tulley","McConville",11);
insert into Person (personId,firstName,lastName,addressId) values (10,"Poppy","Parmby",12);

insert into SaleItem (saleItemId,itemId,saleId) values (1,1,1);
insert into SaleItem (saleItemId,startDate,endDate,itemId,saleId) values (2,"2023-01-01","2025-12-25",2,2);
insert into SaleItem (saleItemId,hoursBilled,servicemanId,itemId,saleId) values (3,2.0,6,3,3);
insert into SaleItem (saleItemId,gbsPurchased,itemId,saleId) values (4,150.0,6,1);
insert into SaleItem (saleItemId,phoneNumber,daysPurchased,itemId,saleId) values (5,"402-472-2401",95,7,2);

update Sale set storeId = 2,customerId = 1,salespersonId = 4 where saleId = 1;
update Sale set storeId = 2,customerId = 2,salespersonId = 4 where saleId = 2;
update Sale set storeId = 1,customerId = 1,salespersonId = 5 where saleId = 3;

