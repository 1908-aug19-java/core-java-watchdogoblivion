//Selects
a.
//Selects all employee rows and displays all columns
SELECT "EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email"
	FROM public."Employee"
b.
//Selects all employee rows where the last name is king and displays all columns
SELECT "EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email"
	FROM public."Employee"
	WHERE "LastName" = 'King';
c.
////Selects all album rows and displays the alubum id, title, and artist id. It is orderded descending alphabetically by title
SELECT "AlbumId", "Title", "ArtistId"
	FROM public."Album"
	ORDER BY "Title" DESC;
d.
//Selects all customers and displays their first name. It is ordered by city ascending
SELECT "FirstName"
	FROM public."Customer"
	ORDER BY "City" ASC;
e.
//Selects all invoices and displays all columns where billing address starts with T
SELECT "InvoiceId", "CustomerId", "InvoiceDate", "BillingAddress", "BillingCity", "BillingState", "BillingCountry", "BillingPostalCode", "Total"
	FROM public."Invoice"
	WHERE "BillingAddress" LIKE 'T%';
f.
//Selects all rows from Track and display name where millisconds is the longest
SELECT "Name"
	FROM public."Track"
	WHERE "Milliseconds" = (SELECT MAX("Milliseconds") FROM public."Track") ;
g.
//Selects the average of all the totals from invoice
SELECT AVG("Total")
	FROM public."Invoice";
h.
//Selects all employees grouped by there title, and display the amount for each title
SELECT COUNT("EmployeeId")
	FROM public."Employee"
	GROUP BY "Title";

//Inserts
a.
//Inserts two records into genre with the data below
INSERT INTO public."Genre"(
	"GenreId", "Name")
	VALUES (26, 'Samuel'),
	       (27, 'Dorilas');
b.
//Inserts two records into employee with the data below
INSERT INTO public."Employee"(
	"EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email")
	VALUES (9, 'Dorilas', 'Samuel', 'Software Developer', 1, '1992-11-01',
			TO_TIMESTAMP('2019-07-12','YYYY-MM-DD'), '61 Oak street', 'Milton', 'Ma', 'USA', '02186', '8573333429', 'N/A', 'samueldorilas@outlook.com'),
(10, 'My', 'Name', 'Software N/A', 2, '1991-1-10',
			'2015-09-11', '32 num street', 'Cambridge', 'NY', 'Canada', '02837', '8573333429', 'N/A', 'N/A');
c.
//Inserts two records into customer with the data below
INSERT INTO public."Customer"(
	"CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId")
	VALUES (60, 'Samuel', 'Dorilas', 'Revature', '61 Oak street','Milton', 'Ma', 'USA', '02186', '8573333429', 'N/A', 'samueldorilas@outlook.com', 3),
(61, 'Name', 'My', 'Walmart','32 num street', 'Cambridge', 'NY', 'Canada', '02837', '8573333429', 'N/A', 'N/A', 4);

//UPDATE
a.
//Updates the customer row where first name is Aaron
UPDATE public."Customer"
	SET "FirstName"='Robert Walter'
	WHERE "FirstName"='Aaron Mitchell';
//Updates the artist row where name is Creedence Clearwater Revival
UPDATE public."Artist"
	SET "Name"='CCR'
	WHERE "Name" = 'Creedence Clearwater Revival';

JOINS
//Performs and inner join on customer and invoice
SELECT "FirstName","InvoiceId"
	FROM "Customer" c
	INNER JOIN "Invoice" i
	ON c."CustomerId"=i."CustomerId";
//Performs a full outer join on customer and invoice
SELECT c."CustomerId","FirstName","LastName", "InvoiceId","Total"
	FROM "Customer" c
	FULL OUTER JOIN "Invoice" i
	ON c."CustomerId"=i."CustomerId";
//Performs a right outer join on album and artist
SELECT album."Title", artist."Name"
	FROM "Album" album
	RIGHT OUTER JOIN "Artist" artist
	ON album."ArtistId"=artist."ArtistId";
//Performs a cross join on album and artist
SELECT album."Title", artist."Name"
	FROM "Album" album
	CROSS JOIN "Artist" artist
	ORDER BY "Name" ASC;
//Performs a self join on employee displaying the managers from e1 and the reportee in e2 (reportee reports to manager)
SELECT e1."FirstName", e2."FirstName"
	FROM "Employee" e1, "Employee" e2
	WHERE e1."EmployeeId" = e2."ReportsTo"