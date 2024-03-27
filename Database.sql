use nbustard2;


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
foreign key (storeId) references Store(storeId)
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


alter table Store
add foreign key (addressId) references Address(addressId);








