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
  zip varchar(5) not null
  );

create table Store (
storeId int not null primary key auto_increment,
storeCode varchar(10) not null,
addressId int not null,
foreign key (addressId) references Address(addressId)
);

create table Person (
personId int not null primary key auto_increment,
uuid varchar(40) not null,
firstName varchar(256) not null,
lastName varchar(256) not null,
storeId int,
addressId int,
foreign key (storeId) references Store(storeId),
foreign key (addressId) references Address(addressId)
);

create table Sale (
saleId int not null primary key auto_increment,
saleCode varchar(10) not null,
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
itemCode varchar(4),
type varchar(1),
name varchar(256),
baseCost double
);

create table SaleItem (
saleCode varchar(4),
itemCode varchar(4),
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
foreign key (saleId) references Sale(saleId)
);









