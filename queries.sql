use cfrance3;

#1.
select * from Person;
select personId, lastName, firstName from Person where personId = 1;
select personId, lastName, firstName from Person where personId = 2;
select personId, lastName, firstName from Person where personId = 3;
select personId, lastName, firstName from Person where personId = 4;
select personId, lastName, firstName from Person where personId = 5;
select personId, lastName, firstName from Person where personId = 6;
select personId, lastName, firstName from Person where personId = 7;
select personId, lastName, firstName from Person where personId = 8;
select personId, lastName, firstName from Person where personId = 9;
select personId, lastName, firstName from Person where personId = 10;
#2.
select firstName, lastName, street, city, state, zip from Person join Address on Person.addressId = Address.addressId;
#3.
select address from Email where personId = 10;
#4.
update Email set address = "RockyBarr11@gmail.com" where personId = 3;
#5.
delete from Email where personId = 10;
delete from Person where personId = 10;
#6.
select Item.itemId, type, name, baseCost from Item join SaleItem on SaleItem.itemId = Item.itemId where saleId = 2;
#7.
select Item.itemId, Item.type, Item.name, Item.baseCost from Item 
	join SaleItem on SaleItem.itemId = Item.itemId
    join Sale on Sale.saleId = SaleItem.saleId
    join Person on Person.personId = Sale.customerId
    where customerId = 1;
#8.
    
