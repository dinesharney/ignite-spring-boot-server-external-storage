-- Table for CustomerDTO
CREATE TABLE IF NOT EXISTS customers (
    id CHAR(36) PRIMARY KEY,  -- UUID stored as a 36-character string
    name VARCHAR(255),
    email VARCHAR(255),
    detail TEXT
);

-- Table for OrderDTO
CREATE TABLE IF NOT EXISTS orders (
    id CHAR(36) PRIMARY KEY,  -- UUID stored as a 36-character string
    product VARCHAR(255),
    price DOUBLE,
    detail TEXT
);

-- Table for ProductDTO
CREATE TABLE IF NOT EXISTS products (
    id CHAR(36) PRIMARY KEY,  -- UUID stored as a 36-character string
    name VARCHAR(255),
    price DOUBLE,
    detail TEXT
);

-- Table for UserDTO
CREATE TABLE IF NOT EXISTS users (
    id CHAR(36) PRIMARY KEY,  -- UUID stored as a 36-character string
    name VARCHAR(255),
    email VARCHAR(255),
    detail TEXT
);
