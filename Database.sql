use nbustard2;

drop table if exists Address;
drop table if exists Sale;
drop table if exists DataPlan;
drop table if exists Purchase;
drop table if exists Lease;
drop table if exists Service;
drop table if exists VoicePlan;
drop table if exists Person;


create table Store (
storeId int not null primary key auto_increment,
storeCode varchar(10) not null
);

create table Address (
  addressId int not null primary key auto_increment,
  street varchar(256) not null,
  city varchar(256) not null,
  state varchar(256) not null,
  zip varchar(5) not null,
  personId int,
  storeId int,
  foreign key (storeId) references Store(storeId)
  );
  
create table Person (
personId int not null primary key auto_increment,
uuid varchar(40) not null,
firstName varchar(256) not null,
lastName varchar(256) not null,
storeId int,
saleId int,
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



create table DataPlan (
dataPlanId int not null primary key auto_increment,
itemCode varchar(10) not null,
name varchar(30) not null,
gbsPurchased double not null,
costPerGB double not null,
saleId int
);

create table Purchase (
purchaseId int not null primary key auto_increment,
itemCode varchar(10) not null,
name varchar(30) not null,
cost double not null,
saleId int
);

create table Lease (
leaseId int not null primary key auto_increment,
startDate varchar(10),
endDate varchar(10),
itemCode varchar(10) not null,
name varchar(30) not null,
cost double not null,
saleId int
);

create table Service (
serviceId int not null primary key auto_increment,
hoursBilled int not null,
servicemanId int not null,
itemCode varchar(10) not null,
name varchar(30) not null,
costPerHour double not null,
saleId int
);

create table VoicePlan (
voicePlanId int not null primary key auto_increment,
itemCode varchar(10) not null,
name varchar(30) not null,
costPerDay double not null,
daysPurchased int,
phoneNumber varchar(13),
saleId int
);

alter table Address
add foreign key (personId) references Person(personId);

alter table Person
add foreign key (saleId) references Sale(saleId);

alter table DataPlan
add foreign key (saleId) references Sale(saleId);

alter table Lease
add foreign key (saleId) references Sale(saleId);

alter table Purchase
add foreign key (saleId) references Sale(saleId);

alter table VoicePlan
add foreign key (saleId) references Sale(saleId);

alter table Service
add foreign key (servicemanId) references Person(personId),
add foreign key (saleId) references Sale(saleId);





