
USE spa_reservation;

DELIMITER  //
CREATE PROCEDURE addCardToCustomer(IN customerID SMALLINT,IN cardID INT,IN cardNum INT,
	IN expDATE VARCHAR(4),IN CVV SMALLINT(3),IN provider VARCHAR(15),IN amount FLOAT(4))
BEGIN
	INSERT INTO CREDITCARD VALUES (cardID,cardNum,expDate,CVV,provider,amount);
    UPDATE CUSTOMER SET CUSTOMER.CREDIT_ID = carID WHERE CUSTOMER.CUS_ID = customerID;
END // DELIMITER ;


-- CREATE FUNCTION getPrice(id SMALLINT)
-- RETURN FLOAT(2)
-- RETURN (SELECT ); 

-- CREATE FUNCTION makeBalance(STime TIME,ETime TIME,id SMALLINT)
-- RETURN FLOAT(4)
-- RETURN (STime - ETime) * getPrice(id);

-- make procedures, adds a reservation
DELIMITER  //
CREATE PROCEDURE makeReservation(IN firstName VARCHAR(155),IN lastName VARCHAR(155),
	IN STime TIME,IN ETime TIME, IN SPA_ID SMALLINT)
BEGIN
	INSERT INTO CUSTOMER (FirstName,LastName,StartTime,Customer_Balance,EndTime,Card) 
    VALUES (firstName,lastName,STime,makeBalance(STime,ETime,MASSAGE_ID),ETime,NULL,SPA_ID);
END // DELIMITER ;


-- Ensures the right default payment per employee
DELIMITER  //
CREATE TRIGGER employ_upd
BEFORE
UPDATE ON EMPLOYEES
FOR EACH ROW
BEGIN
	IF NEW.PAYROLL < 14 OR NEW.PAYROLL > 30 THEN -- if less than minimum wage or over
		IF NEW.JOB_TYPE = "manager" OR NEW.JOB_TYPE = "MANAGER" THEN
			SET NEW.PAYROLL = 25;
		END IF;
		IF NEW.JOB_TYPE = "associate" OR NEW.JOB_TYPE = "ASSOCIATE" THEN
			SET NEW.PAYROLL = 16;
		END IF;
    END IF;
END // DELIMITER ;

-- Add new associate 
DELIMITER  //
CREATE PROCEDURE addNewEmployee(IN firstName VARCHAR(155),IN lastName VARCHAR(155),
	IN jobType VARCHAR(15),IN payroll SMALLINT,IN loginNum INT)
BEGIN
	INSERT INTO EMPLOYEE (FirstName,LastName,StartTime,PAYROLL,LOGIN) 
    VALUES (firstName,lastName,jobType,payroll,loginNum);
END // DELIMITER ;



