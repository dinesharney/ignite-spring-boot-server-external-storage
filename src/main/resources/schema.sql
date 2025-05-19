-- Table for CustomerDTO
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    detail TEXT
);

-- Table for OrderDTO
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    product VARCHAR(255),
    price DOUBLE,
    detail TEXT
);

-- Table for ProductDTO
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    detail TEXT
);

-- Table for UserDTO
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    detail TEXT
);
