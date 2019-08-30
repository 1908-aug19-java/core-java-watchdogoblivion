--Selects
-- a.
-- Selects all employee rows and displays all columns
SELECT "EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email"
	FROM public."Employee";
-- b.
-- Selects all employee rows where the last name is king and displays all columns
SELECT "EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email"
	FROM public."Employee"
	WHERE "LastName" = 'King';
-- c.
-- Selects all album rows and displays the alubum id, title, and artist id. It is orderded descending alphabetically by title
SELECT "AlbumId", "Title", "ArtistId"
	FROM public."Album"
	ORDER BY "Title" DESC;
-- d.
-- Selects all customers and displays their first name. It is ordered by city ascending
SELECT "FirstName"
	FROM public."Customer"
	ORDER BY "City" ASC;
-- e.
-- Selects all invoices and displays all columns where billing address starts with T
SELECT "InvoiceId", "CustomerId", "InvoiceDate", "BillingAddress", "BillingCity", "BillingState", "BillingCountry", "BillingPostalCode", "Total"
	FROM public."Invoice"
	WHERE "BillingAddress" LIKE 'T%';
-- f.
-- Selects all rows from Track and display name where millisconds is the longest
SELECT "Name"
	FROM public."Track"
	WHERE "Milliseconds" = (SELECT MAX("Milliseconds") FROM public."Track") ;
-- g.
-- Selects the average of all the totals from invoice
SELECT AVG("Total")
	FROM public."Invoice";
-- h.
-- Selects all employees grouped by there title, and display the amount for each title
SELECT COUNT("EmployeeId")
	FROM public."Employee"
	GROUP BY "Title";

-- Inserts
-- a.
-- Inserts two records into genre with the data below
INSERT INTO public."Genre"(
	"GenreId", "Name")
	VALUES (26, 'Samuel'),
	       (27, 'Dorilas');
-- b.
-- Inserts two records into employee with the data below
INSERT INTO public."Employee"(
	"EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email")
	VALUES (9, 'Dorilas', 'Samuel', 'Software Developer', 1, '1992-11-01',
			TO_TIMESTAMP('2019-07-12','YYYY-MM-DD'), '61 Oak street', 'Milton', 'Ma', 'USA', '02186', '8573333429', 'N/A', 'samueldorilas@outlook.com'),
(10, 'My', 'Name', 'Software N/A', 2, '1991-1-10',
			'2015-09-11', '32 num street', 'Cambridge', 'NY', 'Canada', '02837', '8573333429', 'N/A', 'N/A');
-- c.
-- Inserts two records into customer with the data below
INSERT INTO public."Customer"(
	"CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId")
	VALUES (60, 'Samuel', 'Dorilas', 'Revature', '61 Oak street','Milton', 'Ma', 'USA', '02186', '8573333429', 'N/A', 'samueldorilas@outlook.com', 3),
(61, 'Name', 'My', 'Walmart','32 num street', 'Cambridge', 'NY', 'Canada', '02837', '8573333429', 'N/A', 'N/A', 4);

--UPDATE
-- a.
-- Updates the customer row where first name is Aaron
UPDATE public."Customer"
	SET "FirstName"='Robert Walter'
	WHERE "FirstName"='Aaron Mitchell';
--b.
-- Updates the artist row where name is Creedence Clearwater Revival
UPDATE public."Artist"
	SET "Name"='CCR'
	WHERE "Name" = 'Creedence Clearwater Revival';

-- JOINS
--a
-- INNER Performs and inner join on customer and invoice
SELECT "FirstName","InvoiceId"
	FROM "Customer" c
	INNER JOIN "Invoice" i
	ON c."CustomerId"=i."CustomerId";
--a.
-- OUTER Performs a full outer join on customer and invoice
SELECT c."CustomerId","FirstName","LastName", "InvoiceId","Total"
	FROM "Customer" c
	FULL OUTER JOIN "Invoice" i
	ON c."CustomerId"=i."CustomerId";
--a.
-- RIGHT Performs a right outer join on album and artist
SELECT album."Title", artist."Name"
	FROM "Album" album
	RIGHT OUTER JOIN "Artist" artist
	ON album."ArtistId"=artist."ArtistId";
--a.
-- CROSS Performs a cross join on album and artist
SELECT album."Title", artist."Name"
	FROM "Album" album
	CROSS JOIN "Artist" artist
	ORDER BY "Name" ASC;
--a.
-- SELF Performs a self join on employee displaying the managers from e1 and the reportee in e2 (reportee reports to manager)
SELECT e1."FirstName", e2."FirstName"
	FROM "Employee" e1, "Employee" e2
	WHERE e1."EmployeeId" = e2."ReportsTo";

--Joined Queries
--a. This query gets the sum of all totals from invoice for a customer, then uses that set in a join on customers to get the full name of the customer
SELECT c."FirstName" || c."LastName" as "FULL_NAME ", n.s as "TOTAL"
	FROM public."Customer" c
	JOIN 
	(SELECT "CustomerId", SUM("Total") as s
	FROM public."Invoice" i
	GROUP BY "CustomerId") as n
	ON n."CustomerId" = c."CustomerId";

--This script is to verify that my query was correct
SELECT "FirstName", "LastName", "Total"
	FROM public."Customer" c
	JOIN "Invoice" i ON c."CustomerId"=i."CustomerId"
	WHERE "FirstName" = 'Leonie';


--b. first way
-- This gets the sum of all the invoices for each unique customer, then uses that to  result set to select the customer id where the total matches a subquery that gets 
-- the maximum sum and then uses that customer id in a query to get the sales rep
SELECT e."FirstName" || e."LastName" as "FULL_NAME"
FROM "Employee" e JOIN "Customer" c
ON e."EmployeeId" = c."SupportRepId"
WHERE c."CustomerId" =
(SELECT "CustomerId" FROM
(SELECT "CustomerId", SUM("Total") as s
	FROM public."Invoice"
	GROUP BY "CustomerId") as n
WHERE n.s = (SELECT MAX(n.s)
FROM (SELECT "CustomerId", SUM("Total") as s
	FROM public."Invoice"
	GROUP BY "CustomerId") AS n));
	
--b. second way
-- This query performs a subquery that gets the sum of all invoices for each unique customer, then performs a join on employee and customer orders it by decsending, and 
--gets the first value which is the highest sum
SELECT e."FirstName" || ' ' ||  e."LastName" as "FULL_NAME", MAX(i.s) as "Highest Sale"
	FROM public."Employee" e
	
	JOIN public."Customer" c
	
	ON  e."EmployeeId" = c."SupportRepId"
	JOIN (SELECT "CustomerId", SUM("Total") as s
	FROM public."Invoice"
	GROUP BY "CustomerId") as i
	
	ON  c."CustomerId" = i."CustomerId"
	GROUP BY "FULL_NAME"
	ORDER BY "Highest Sale" DESC
	LIMIT 1;

--c.
-- This query joins genre, track, and invoiceline and counts the number of matches for each genre which represets the number of purchases for that genre
SELECT g."Name" as "Genre Name", COUNT(g."Name") as Purchases
	FROM public."Genre" g
	JOIN public."Track" t
	ON g."GenreId" = t."GenreId"
	JOIN public."InvoiceLine" il
	on t."TrackId" = il."TrackId"
	GROUP BY g."Name"
	ORDER BY Purchases DESC;

--User Defined Functions
--a.
create or replace function average_total_invoices()
returns public."Invoice"."Total"%type
language plpgsql
as $function$
BEGIN
return (SELECT AVG("Total") FROM public."Invoice");
END;
$function$;
--b.
create or replace function after1968()
returns setof public."Employee"
language plpgsql
as $function$
BEGIN
 return query SELECT * FROM public."Employee" WHERE "BirthDate" > '1968,12,31';
END;
$function$;
--c.
create or replace function get_employee_manager(emp_id public."Employee"."EmployeeId"%type)
returns setof public."Employee"
language plpgsql
as $function$
DECLARE
reportsToId public."Employee"."ReportsTo"%type = (SELECT "ReportsTo" FROM public."Employee" WHERE "EmployeeId" = emp_id);
BEGIN
return query SELECT * FROM public."Employee" WHERE "EmployeeId" = reportsToId;
END
$function$;
--d.
create or replace function get_Playlist_Price(playlist_id public."Track"."UnitPrice"%type)
returns public."Track"."UnitPrice"%type
language plpgsql
as $function$
BEGIN
return 
(SELECT SUM(t."UnitPrice")
 FROM public."Playlist" p JOIN public."PlaylistTrack" pt
 ON p."PlaylistId" = pt."PlaylistId" JOIN public."Track" t
 ON pt."TrackId" = t."TrackId"
 WHERE p."PlaylistId" = playlist_id
 GROUP BY p."PlaylistId");
END;
$function$

--This is used to verify the function
SELECT "get_playlist_price"(14);
SELECT "TrackId"
	FROM public."PlaylistTrack"
	WHERE "PlaylistId" = 14
	ORDER BY "TrackId";
	
SELECT SUM("UnitPrice")
	FROM public."Track"
	WHERE "TrackId">3429 and "TrackId"<3455;

