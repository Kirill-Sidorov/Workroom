-- Get all system users
SELECT UserEntity.Id,
       UserEntity.Login,
       UserEntity.Password,
       UserEntity.Status,
       UserEntity.FirstName,
       UserEntity.LastName,
       UserEntity.Phone,
       UserEntity.Authorized,
       UserTypeTable.Name AS UserType
FROM UserEntity
         INNER JOIN UserType AS UserTypeTable
                    ON UserEntity.UserType = UserTypeTable.Id

-- Get user by login
SELECT UserEntity.Id,
       UserEntity.Login,
       UserEntity.Password,
       UserEntity.Status,
       UserEntity.FirstName,
       UserEntity.LastName,
       UserEntity.Phone,
       UserEntity.Authorized,
       UserTypeTable.Name AS UserType
FROM UserEntity
         INNER JOIN UserType AS UserTypeTable
                    ON UserEntity.UserType = UserTypeTable.Id
WHERE Login = ?

-- Get user by id
SELECT UserEntity.Id,
       UserEntity.Login,
       UserEntity.Password,
       UserEntity.Status,
       UserEntity.FirstName,
       UserEntity.LastName,
       UserEntity.Phone,
       UserEntity.Authorized,
       UserTypeTable.Name AS UserType
FROM UserEntity
         INNER JOIN UserType AS UserTypeTable
                    ON UserEntity.UserType = UserTypeTable.Id
WHERE UserEntity.Id = ?

-- Get list of users by status
SELECT UserEntity.Id,
       UserEntity.Login,
       UserEntity.Password,
       UserEntity.Status,
       UserEntity.FirstName,
       UserEntity.LastName,
       UserEntity.Phone,
       UserEntity.Authorized,
       UserTypeTable.Name AS UserType
FROM UserEntity
         INNER JOIN UserType AS UserTypeTable
                    ON UserEntity.UserType = UserTypeTable.Id
WHERE UserEntity.Status = ?

-- Change user authorization field
UPDATE UserEntity
SET Authorized=?
WHERE Id = ?

-- Change user status
UPDATE UserEntity
SET Status=?
WHERE Id = ?

-- Create user
INSERT
INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized)
VALUES (?, ?, 'unlocked', ?, ?, ?, ?, 0)

-- Update user record
UPDATE UserEntity
SET Login=?,
    FirstName=?,
    LastName=?,
    Phone=?
WHERE Id = ?

-- Delete user by id
DELETE
FROM UserEntity
WHERE Id = ?

-- Get all orders
SELECT OrderEntity.Id,
       OrderEntity.ThingName,
       OrderEntity.Description,
       OrderEntity.CostWork,
       OrderEntity.Customer   AS CustomerId,
       CustomerTable.LastName AS CustomerName,
       OrderEntity.Master     AS MasterId,
       MasterTable.LastName   AS MasterName,
       OrderStatus.Name       AS OrderStatus,
       OrderEntity.Deleted
FROM OrderEntity
         INNER JOIN UserEntity AS CustomerTable
                    ON OrderEntity.Customer = CustomerTable.Id
         INNER JOIN OrderStatus
                    ON OrderEntity.OrderStatus = OrderStatus.Id
         LEFT JOIN UserEntity AS MasterTable
                    ON OrderEntity.Master = MasterTable.Id

-- Get user orders
SELECT OrderEntity.Id,
       OrderEntity.ThingName,
       OrderEntity.Description,
       OrderEntity.CostWork,
       OrderEntity.Customer   AS CustomerId,
       CustomerTable.LastName AS CustomerName,
       OrderEntity.Master     AS MasterId,
       MasterTable.LastName   AS MasterName,
       OrderStatus.Name       AS OrderStatus,
       OrderEntity.Deleted
FROM OrderEntity
         INNER JOIN UserEntity AS CustomerTable
                    ON OrderEntity.Customer = CustomerTable.Id
         INNER JOIN OrderStatus
                    ON OrderEntity.OrderStatus = OrderStatus.Id
         LEFT JOIN UserEntity AS MasterTable
                    ON OrderEntity.Master = MasterTable.Id
WHERE OrderEntity.Customer = ?
  AND OrderEntity.Deleted = ?

-- Get master's orders by master's id and order status
SELECT OrderEntity.Id,
       OrderEntity.ThingName,
       OrderEntity.Description,
       OrderEntity.CostWork,
       OrderEntity.Customer   AS CustomerId,
       CustomerTable.LastName AS CustomerName,
       OrderEntity.Master     AS MasterId,
       MasterTable.LastName   AS MasterName,
       OrderStatus.Name       AS OrderStatus,
       OrderEntity.Deleted
FROM OrderEntity
         INNER JOIN UserEntity AS CustomerTable
                    ON OrderEntity.Customer = CustomerTable.Id
         INNER JOIN OrderStatus
                    ON OrderEntity.OrderStatus = OrderStatus.Id
         LEFT JOIN UserEntity AS MasterTable
                    ON OrderEntity.Master = MasterTable.Id
WHERE OrderEntity.Master = ?
  AND OrderEntity.OrderStatus = ?

-- Create an order
INSERT
INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted)
VALUES (?, ?, ?, ?, 0)

-- Get a list of orders by status
SELECT OrderEntity.Id,
       OrderEntity.ThingName,
       OrderEntity.Description,
       OrderEntity.CostWork,
       OrderEntity.Customer   AS CustomerId,
       CustomerTable.LastName AS CustomerName,
       OrderEntity.Master     AS MasterId,
       MasterTable.LastName   AS MasterName,
       OrderStatus.Name       AS OrderStatus,
       OrderEntity.Deleted
FROM OrderEntity
         INNER JOIN UserEntity AS CustomerTable
                    ON OrderEntity.Customer = CustomerTable.Id
         INNER JOIN OrderStatus
                    ON OrderEntity.OrderStatus = OrderStatus.Id
         LEFT JOIN UserEntity AS MasterTable
                    ON OrderEntity.Master = MasterTable.Id
WHERE OrderEntity.OrderStatus = ? AND OrderEntity.Deleted = 0

-- Get order by id
SELECT OrderEntity.Id,
       OrderEntity.ThingName,
       OrderEntity.Description,
       OrderEntity.CostWork,
       OrderEntity.Customer   AS CustomerId,
       CustomerTable.LastName AS CustomerName,
       OrderEntity.Master     AS MasterId,
       MasterTable.LastName   AS MasterName,
       OrderStatus.Name       AS OrderStatus,
       OrderEntity.Deleted
FROM OrderEntity
         INNER JOIN UserEntity AS CustomerTable
                    ON OrderEntity.Customer = CustomerTable.Id
         INNER JOIN OrderStatus
                    ON OrderEntity.OrderStatus = OrderStatus.Id
         LEFT JOIN UserEntity AS MasterTable
                    ON OrderEntity.Master = MasterTable.Id
WHERE OrderEntity.Id = ?

-- Get a list of orders that are marked for deletion
SELECT OrderEntity.Id,
       OrderEntity.ThingName,
       OrderEntity.Description,
       OrderEntity.CostWork,
       OrderEntity.Customer   AS CustomerId,
       CustomerTable.LastName AS CustomerName,
       OrderEntity.Master     AS MasterId,
       MasterTable.LastName   AS MasterName,
       OrderStatus.Name       AS OrderStatus,
       OrderEntity.Deleted
FROM OrderEntity
         INNER JOIN UserEntity AS CustomerTable
                    ON OrderEntity.Customer = CustomerTable.Id
         INNER JOIN OrderStatus
                    ON OrderEntity.OrderStatus = OrderStatus.Id
         LEFT JOIN UserEntity AS MasterTable
                   ON OrderEntity.Master = MasterTable.Id
WHERE OrderEntity.Deleted = ?

-- Update order record
UPDATE OrderEntity
SET ThingName=?,
    Description=?
WHERE Id = ?

-- Update order status
UPDATE OrderEntity
SET OrderStatus=?
WHERE Id = ?

-- Change the order deletion field
UPDATE OrderEntity
SET Deleted=?
WHERE Id = ?

-- Change order master
UPDATE OrderEntity
SET Master=?
WHERE Id = ?

-- Delete order by id
DELETE
FROM OrderEntity
WHERE Id = ?

-- Change the cost of the order
UPDATE OrderEntity
SET CostWork=?
WHERE Id = ?

-- Get a list of all spare parts in the warehouse
SELECT *
FROM ReplacementPart

-- Get spare part by id
SELECT *
FROM ReplacementPart
WHERE Id = ?

-- Update spare part record
UPDATE ReplacementPart
SET Name=?,
    InStock=?,
    Cost=?
WHERE Id = ?

-- Create a spare part
INSERT
INTO ReplacementPart(Name, InStock, Cost)
VALUES (?, ?, ?)

-- Delete spare part by id
DELETE
FROM ReplacementPart
WHERE Id = ?


-- Update the number of spare parts in stock for a spare part by id
UPDATE ReplacementPart
SET InStock=?
WHERE Id = ?

-- Get spare parts list for order by id
SELECT *
FROM PartsList
WHERE OrderEntity = ?

-- Update order spare parts quantity
UPDATE PartsList
SET NumberParts=?
WHERE OrderEntity = ?
  AND ReplacementPart = ?

-- Add (create) spare part to the spare parts list of the order
INSERT
INTO PartsList(OrderEntity, ReplacementPart, NumberParts)
VALUES (?, ?, ?)

-- Remove spare part from order
DELETE
FROM PartsList
WHERE OrderEntity = ?
  AND ReplacementPart = ?

-- Get a list of spare parts for the order with all spare parts from the warehouse
SELECT PartsList.OrderEntity AS OrderEntity,
       ReplacementPart.Id    AS ReplacementPart,
       ReplacementPart.Name  AS Name,
       ReplacementPart.Cost  AS Cost,
       PartsList.NumberParts AS NumberParts
FROM ReplacementPart
         INNER JOIN PartsList
                    ON ReplacementPart.Id = PartsList.ReplacementPart
WHERE PartsList.OrderEntity = ?
UNION
SELECT ?                    AS OrderEntity,
       ReplacementPart.Id   AS ReplacementPart,
       ReplacementPart.Name AS Name,
       ReplacementPart.Cost AS Cost,
       0                    AS NumberParts
FROM ReplacementPart
WHERE ReplacementPart.Id NOT IN (SELECT PartsList.ReplacementPart
                                 FROM PartsList
                                 WHERE PartsList.OrderEntity = ?)

-- Get a list of spare parts for the order with the quantity of these spare parts in stock
SELECT PartsList.ReplacementPart,
       PartsList.OrderEntity,
       PartsList.NumberParts,
       ReplacementPart.Name    AS Name,
       ReplacementPart.InStock AS InStock
FROM ReplacementPart
         LEFT JOIN PartsList
                   ON ReplacementPart.Id = PartsList.ReplacementPart
WHERE PartsList.OrderEntity = ?

-- Get a list of spare parts for your order
SELECT PartsList.OrderEntity AS OrderEntity,
       ReplacementPart.Id    AS ReplacementPart,
       ReplacementPart.Name  AS Name,
       ReplacementPart.Cost  AS Cost,
       PartsList.NumberParts AS NumberParts
FROM ReplacementPart
         INNER JOIN PartsList
                    ON ReplacementPart.Id = PartsList.ReplacementPart
WHERE PartsList.OrderEntity = ?