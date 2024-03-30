#1.A query to retrieve the main attributes of each person (their code, and last/first name)
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

#2.A query to retrieve the major fields for every person including their address (but excluding emails)
select firstName, lastName, Address.street, City.name as city, State.abbreviation as state, Zip.zipcode
	from Person
    join Address on Person.addressId = Address.addressId
    join State on Address.stateId = State.stateId
    join City on Address.cityId = City.cityId
    join Zip on Address.zipId = Zip.zipId;

#3.A query to get the email addresses of a specific person
select address from Email where personId = 10;

#4.A query to change the email address of a specific email record
update Email set address = "RockyBarr11@gmail.com" where personId = 3;

#5.A query (or series of queries) to remove a specific person record
delete from Email where personId = 10;
delete from Person where personId = 10;

#6.A query to get all the items on a specific sales record
select Item.itemCode, type, name, baseCost
	from Item 
    join SaleItem on SaleItem.itemId = Item.itemId where saleId = 2;

#7.A query to get all the items purchased by a specific person
select Item.itemCode, Item.type, Item.name, Item.baseCost
	from Item 
	join SaleItem on SaleItem.itemId = Item.itemId
    join Sale on Sale.saleId = SaleItem.saleId
    join Person on Person.personId = Sale.customerId
    where customerId = 1;

#8.A query to get all the items purchased by a specific person
select count(*) as numSales from Sale where storeId = 2;

#9.A query to find the total number of sales made by each employee
select firstName, lastName, count(*) as numSales
	from Sale
    join Person on Sale.salespersonId = Person.personId
    group by lastName, firstName;

#10.A query to find the subtotal charge of all data plans purchased in each sale (hint: you can take an aggregate of a mathematical expression). Do not include taxes.
select saleCode, SUM(gbsPurchased * baseCost) as subtotal
from SaleItem
join Item on SaleItem.itemId = Item.itemId
join Sale on SaleItem.saleId = Sale.saleId
where type = "D"
group by saleCode;

#11.A query to find and report any sale that includes multiple records of the same data plan.
select saleCode, name as planName, count(*) as numPlansPurchased, sum(gbsPurchased) as totalGbs
	from Item
    join SaleItem on Item.itemId = SaleItem.itemId
    join Sale on SaleItem.saleId = Sale.saleId
    where type = "D"
    group by saleCode
    having numPlansPurchased > 1;

#12.Write a query to detect a potential instance of fraud where an employee makes a sale to themselves.
select firstName, lastName, Item.name, saleCode
	from Sale
    join Person on Sale.salespersonId = Person.personId
    join SaleItem on Sale.saleId = SaleItem.saleId
    join Item on SaleItem.itemId = Item.itemId
    where salespersonId = customerId;
    
