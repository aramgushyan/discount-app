CREATE SEQUENCE IF NOT EXISTS cities_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS companies_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS countries_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS regions_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS units_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE cities
(
    id        BIGINT       NOT NULL,
    region_id BIGINT       NOT NULL,
    name      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_cities PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id       BIGINT       NOT NULL,
    owner_id BIGINT       NOT NULL,
    name     VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_companies PRIMARY KEY (id)
);

CREATE TABLE countries
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_countries PRIMARY KEY (id)
);

CREATE TABLE regions
(
    id         BIGINT       NOT NULL,
    country_id BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_regions PRIMARY KEY (id)
);

CREATE TABLE units
(
    id         BIGINT       NOT NULL,
    company_id BIGINT       NOT NULL,
    city_id    BIGINT       NOT NULL,
    address    VARCHAR(255) NOT NULL,
    admin_id   BIGINT       NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    status     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_units PRIMARY KEY (id)
);

ALTER TABLE regions
    ADD CONSTRAINT uc_96f9452c991d3a33dd05360a1 UNIQUE (name, country_id);

ALTER TABLE companies
    ADD CONSTRAINT uc_companies_name UNIQUE (name);

ALTER TABLE countries
    ADD CONSTRAINT uc_countries_name UNIQUE (name);

ALTER TABLE cities
    ADD CONSTRAINT uc_ee470790383b14ec7226b6c65 UNIQUE (name, region_id);

ALTER TABLE units
    ADD CONSTRAINT uc_units_email UNIQUE (email);

ALTER TABLE units
    ADD CONSTRAINT uc_units_phone UNIQUE (phone);

ALTER TABLE cities
    ADD CONSTRAINT FK_CITIES_ON_REGION FOREIGN KEY (region_id) REFERENCES regions (id);

ALTER TABLE regions
    ADD CONSTRAINT FK_REGIONS_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES countries (id);

ALTER TABLE units
    ADD CONSTRAINT FK_UNITS_ON_CITY FOREIGN KEY (city_id) REFERENCES cities (id);

ALTER TABLE units
    ADD CONSTRAINT FK_UNITS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);