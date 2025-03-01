DROP TABLE IF EXISTS PartsList;
DROP TABLE IF EXISTS OrderEntity;
DROP TABLE IF EXISTS UserEntity;
DROP TABLE IF EXISTS ReplacementPart;
DROP TABLE IF EXISTS OrderStatus;
DROP TABLE IF EXISTS UserType;

CREATE TABLE UserType (
	Id 	 SERIAL PRIMARY KEY,
	Name VARCHAR(40) NOT NULL
);

CREATE TABLE OrderStatus (
	Id 	 SERIAL PRIMARY KEY,
	Name VARCHAR(50) NOT NULL
);

CREATE TABLE ReplacementPart (
	Id 	 	SERIAL PRIMARY KEY,
	Name 	VARCHAR(50) NOT NULL,
	InStock INTEGER NOT NULL,
	Cost 	INTEGER NOT NULL
);

CREATE TABLE UserEntity (
	Id 	 	   SERIAL PRIMARY KEY,
	Login 	   VARCHAR(50),
	Password   VARCHAR(60),
	Status 	   VARCHAR(50),
	UserType   SMALLINT,
	FirstName  VARCHAR(50),
	LastName   VARCHAR(50),
	Phone 	   VARCHAR(11),
	Authorized SMALLINT,
	FOREIGN KEY (UserType) REFERENCES UserType (Id)
);

CREATE TABLE OrderEntity (
	Id 		  	SERIAL PRIMARY KEY,
	ThingName 	VARCHAR(50),
	Description VARCHAR(255),
	OrderStatus SMALLINT,
	Customer 	INTEGER,
	Master 		INTEGER,
	CostWork 	INTEGER,
	Deleted 	SMALLINT,
	FOREIGN KEY (OrderStatus) REFERENCES OrderStatus (Id),
	FOREIGN KEY (Customer) REFERENCES UserEntity (Id) ON DELETE CASCADE,
	FOREIGN KEY (Master) REFERENCES UserEntity (Id) ON DELETE CASCADE
);

CREATE TABLE PartsList (
	OrderEntity 	INTEGER,
	ReplacementPart INTEGER,
	NumberParts 	INTEGER,
	FOREIGN KEY (OrderEntity) REFERENCES OrderEntity (Id) ON DELETE CASCADE,
	FOREIGN KEY (ReplacementPart) REFERENCES ReplacementPart (Id) ON DELETE CASCADE
);

INSERT INTO UserType(Name)
VALUES
	('admin'),
	('moderator'),
	('customer'),
	('master'),
	('storekeeper');

INSERT INTO OrderStatus(Name)
VALUES
	('check_by_moderator'),
	('wait_master'),
	('creation_parts_list'),
	('wait_replacement_parts'),
	('repair_process'),
	('completed');

INSERT INTO ReplacementPart(Name, InStock, Cost)
VALUES
	('DC Electric Motor', 2, 250),
	('Relay 250V 6A', 3, 80),
	('Transistor switch 12V', 2, 100),
	('Electric motor brushes', 6, 30),
	('Micro switch', 6, 90);

INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized)
VALUES
	('admin', '$2a$10$EDZZmgHVA0ocst9I53EBF.NCqyOIfksSXpD2iGyJyTlGUq92BhrQe', 'unlocked', 1, 'Patrick', 'Daniel', '89106401824', 0),      -- password: 1
	('moderator', '$2a$10$7q7XR4YUJ1dIC..ds/M3OexE60waTk7Ng3KwdjjuCyk3v1bQ0TZHK', 'unlocked', 2, 'Adrian', 'Jones', '89156401826', 0),    -- password: 1
	('customer', '$2a$10$3AkkAWlXTCSVMFKn5sGRIODJU01s7kBCSSyWQ3M4095nzTX4iatCW', 'unlocked', 3, 'Timothy', 'Nelson', '89206403824', 0),   -- password: 1
	('master', '$2a$10$cfwder0EvdaDwnTNXXasF.f1.0oXc6ueP/3S8NtIiFErOuqbQLe9e', 'unlocked', 4, 'Richard', 'Stephens', '89106201524', 0),   -- password: 1
	('storekeeper', '$2a$10$bVIvns/toEaYnH211jZkheBeqa4uNEXl4Gq20lbMPAAcmav2kv79C', 'unlocked', 5, 'Justin', 'Malone', '89106107844', 0), -- password: 1
	('customer2', '$2a$10$7UE6MXwrJWutFKA7bj8af.G/2h38VUP8PE.EncrYNo1X9448v5hP6', 'locked', 3, 'William', 'Jordan', '89106177874', 0);    -- password: 1

INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted)
VALUES
	('Hair dryer', 'The hair dryer does not heat the air', 1, 3, 0),
	('Vacuum cleaner LG', 'Does not turn on', 2, 3, 0),
	('Mixer', 'The button does not work', 2, 3, 0);

INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted, Master)
VALUES
	('Iron', 'Does not turn on', 3, 3, 0, 4);

INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted, Master, CostWork)
VALUES
    ('Meat grinder', 'Does not turn on', 6, 3, 0, 4, 400);

INSERT INTO PartsList(OrderEntity, ReplacementPart, NumberParts)
VALUES
    (5, 4, 2),
	(5, 3, 1);