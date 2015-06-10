-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-05-19 18:53:15.146


-- tables
-- Table Occupation
CREATE TABLE Occupation (
    id bigint    NOT NULL ,
    property_id bigint    NOT NULL ,
    start_date date    NOT NULL ,
    end_date date    NOT NULL ,
    CHECK (start_date < end_date),
    CONSTRAINT Occupation_pk PRIMARY KEY (id)
);

-- Table Person
CREATE TABLE Person (
    id bigint    NOT NULL ,
    firstname varchar(20)    NOT NULL ,
    lastname varchar(30)    NOT NULL ,
    middlename varchar(20)    NULL ,
    email varchar(50)    NOT NULL ,
    registration_date date    NOT NULL ,
    phone varchar(20)    NOT NULL ,
    CONSTRAINT Person_pk PRIMARY KEY (id)
);

-- Table Property
CREATE TABLE Property (
    id bigint    NOT NULL ,
    name varchar(70)    NOT NULL ,
    owner_id bigint    NOT NULL ,
    country varchar(70)    NOT NULL ,
    state varchar(20)    NULL ,
    region varchar(70)    NULL ,
    city varchar(70)    NOT NULL ,
    address varchar(70)    NOT NULL ,
    creation_date date    NOT NULL ,
    daily_price int    NOT NULL ,
    monthly_price int    NOT NULL ,
    CONSTRAINT Property_pk PRIMARY KEY (id)
);

-- Table Rental_Request
CREATE TABLE Rental_Request (
    id bigint    NOT NULL ,
    occupation_id bigint    NOT NULL ,
    visitor_id bigint    NOT NULL ,
    status int    NOT NULL ,
    CONSTRAINT Rental_Request_pk PRIMARY KEY (id)
);





-- foreign keys
-- Reference:  Apartment_Occupation (table: Occupation)


ALTER TABLE Occupation ADD FOREIGN KEY  (property_id)
    REFERENCES Property (id);
-- Reference:  Apartment_Owner (table: Property)


ALTER TABLE Property ADD FOREIGN KEY (owner_id)
    REFERENCES Person (id);
-- Reference:  Occupation_Transaction (table: Occupation)


ALTER TABLE Occupation ADD FOREIGN KEY (id)
    REFERENCES Rental_Request (occupation_id);
-- Reference:  Person_Transaction (table: Rental_Request)


ALTER TABLE Rental_Request ADD FOREIGN KEY (visitor_id)
    REFERENCES Person (id);



-- End of file.

