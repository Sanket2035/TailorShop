-- Create the database
CREATE DATABASE poshtailors;

-- Connect to the database (only works in psql CLI, not in pgAdmin)
\c poshtailors;

-- Orders Table
CREATE TABLE Orders (
    OrderID SERIAL PRIMARY KEY,
    CustomerName VARCHAR(100) NOT NULL,
    CustomerMobile VARCHAR(15) NOT NULL,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PickupDate TIMESTAMP,
    QtyShirts INT NOT NULL,
    QtyPants INT NOT NULL,
    Note TEXT,
    TotalPayment DECIMAL(10, 2),
    PaymentStatus VARCHAR(50),
    advancePayment NUMERIC(10,2) DEFAULT 0
);

-- Shirts Table
CREATE TABLE Shirts (
    ShirtID SERIAL PRIMARY KEY,
    OrderID INT NOT NULL,
    ShirtType VARCHAR(50) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    Height DECIMAL(10,2),
    Chest DECIMAL(10,2),
    Fron1 DECIMAL(10,2),
    Front2 DECIMAL(10,2),
    Shoulder DECIMAL(10,2),
    Belly DECIMAL(10,2),
    Sleeve DECIMAL(10,2),
    Caller DECIMAL(10,2),
    Cup DECIMAL(10,2),
    Other TEXT,
    FOREIGN KEY(OrderID) REFERENCES Orders(OrderID)
);

-- Pants Table
CREATE TABLE Pants (
    PantID SERIAL PRIMARY KEY,
    OrderID INT NOT NULL,
    PantType VARCHAR(50) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    Height DECIMAL(10,2),
    Waist DECIMAL(10,2),
    Seat DECIMAL(10,2),
    Thigh DECIMAL(10,2),
    Knee DECIMAL(10,2),
    Bottom DECIMAL(10,2),
    Hang DECIMAL(10,2),
    Other TEXT,
    FOREIGN KEY(OrderID) REFERENCES Orders(OrderID)
);

-- ShirtTypes Table
CREATE TABLE ShirtTypes (
    ShirtType VARCHAR(50) NOT NULL PRIMARY KEY, 
    Price DECIMAL(10,2) NOT NULL 
);

-- PantTypes Table
CREATE TABLE PantTypes (
    PantType VARCHAR(50) NOT NULL PRIMARY KEY, 
    Price DECIMAL(10,2) NOT NULL 
);
