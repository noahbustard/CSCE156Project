use cfrance3;

#1.
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
select firstName, lastName, Address.street, City.name as city, State.abbreviation as state, Zip.zipcode
	from Person
    join Address on Person.addressId = Address.addressId
    join State on Address.stateId = State.stateId
    join City on Address.cityId = City.cityId
    join Zip on Address.zipId = Zip.zipId;
#3.
select address from Email where personId = 10;
#4.
update Email set address = "RockyBarr11@gmail.com" where personId = 3;
#5.
delete from Email where personId = 10;
delete from Person where personId = 10;
#6.
select Item.itemCode, type, name, baseCost
	from Item 
    join SaleItem on SaleItem.itemId = Item.itemId where saleId = 2;
#7.
select Item.itemCode, Item.type, Item.name, Item.baseCost
	from Item 
	join SaleItem on SaleItem.itemId = Item.itemId
    join Sale on Sale.saleId = SaleItem.saleId
    join Person on Person.personId = Sale.customerId
    where customerId = 1;
#8.
select count(*) as numSales from Sale where storeId = 2;
#9.
select firstName, lastName, count(*) as numSales
	from Sale
    join Person on Sale.salespersonId = Person.personId
    group by lastName, firstName;
#10.
select saleCode, SUM(gbsPurchased * baseCost) as subtotal
from SaleItem
join Item on SaleItem.itemId = Item.itemId
join Sale on SaleItem.saleId = Sale.saleId
where type = "D"
group by saleCode;
#11.
select saleCode, name as planName, count(*) as numPlansPurchased, sum(gbsPurchased) as totalGbs
	from Item
    join SaleItem on Item.itemId = SaleItem.itemId
    join Sale on SaleItem.saleId = Sale.saleId
    where type = "D"
    group by saleCode
    having numPlansPurchased > 1;


select * from Sale;
select * from SaleItem;
#12.
select firstName, lastName, Item.name, saleCode
	from Sale
    join Person on Sale.salespersonId = Person.personId
    join SaleItem on Sale.saleId = SaleItem.saleId
    join Item on SaleItem.itemId = Item.itemId
    where salespersonId = customerId;
    
