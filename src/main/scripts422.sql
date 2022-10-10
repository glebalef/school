CREATE TABLE drivers (
    Name TEXT,
    Age SERIAL,
    License BOOLEAN,
    License_number SERIAL PRIMARY KEY ,
    CAR CHARACTER(17) REFERENCES cars (Vin)
);

CREATE TABLE cars (
    Brand TEXT,
    Model TEXT,
    Price money,
    Vin CHARACTER(17) PRIMARY KEY
);









