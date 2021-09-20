create database spa_reservation;

USING PRICE1 AS 3.00

/*Creates new table with attributes*/
CREATE TABLE CUSTOMER {
    CustomerID SMALLINT PRIMARY KEY,
    Customer_FirstName VARCHAR(255),
    Customer_LastName VARCHAR(255),
    Customer_StartTime TIME,
    Customer_TotalCost DECIMAL(2,2),
    Customer_EndTime TIME,
    MassageType VARCHAR(24),
    FOREIGN KEY (MassageType) REFERENCES MASSAGE(MassageType)
    };

CREATE TABLE MASSAGE{
    MassageID SMALLINT PRIMARY KEY,
    MassageType VARCHAR(24),
    MassageSpecificType VARCHAR(24),
    MassageCost DECIMAL(1,2),
    };

/*Finds any duplicates in the customer table*/
SELECT *,COUNT(*)
FROM CUSTOMER
GROUP BY *
HAVING COUNT(*) > 1


/*Adds new values to the table*/
/*Generic Data */
INSERT INTO CUSTOMER VALUES(1,NULL,NULL,NULL,0.00,8:00,NULL);
INSERT INTO CUSTOMER VALUES(2,NULL,NULL,NULL,NULL,NULL);
INSERT INTO CUSTOMER VALUES(3,NULL,NULL,NULL,NULL,NULL);
INSERT INTO CUSTOMER VALUES(4,NULL,NULL,NULL,NULL,NULL);
INSERT INTO CUSTOMER VALUES(5,NULL,NULL,NULL,NULL,NULL);
INSERT INTO CUSTOMER VALUES(6,NULL,NULL,NULL,NULL,NULL);

/*Deletes the whole row of the given id for customer*/
/*Generic Data */
DELETE FROM CUSTOMER WHERE CUSTOMER.CustomerId == 2


/*View selected data*/
SELECT * FROM CUSTOMER;


