-- supermarket_inventory.sql

-- Categories Table
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Products Table
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category_id INT REFERENCES categories(id),
    price DECIMAL(10,2) NOT NULL,
    unit VARCHAR(50) NOT NULL
);

-- Inventory Table
CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(id),
    quantity INT NOT NULL,
    expiry_date DATE,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed Data
INSERT INTO categories (name) VALUES 
  ('Dairy'), 
  ('Produce'), 
  ('Bakery');

INSERT INTO products (name, category_id, price, unit) VALUES 
  ('Milk', 1, 45.00, 'litre'),
  ('Apple', 2, 120.00, 'kg'),
  ('Bread', 3, 30.00, 'piece');

INSERT INTO inventory (product_id, quantity, expiry_date) VALUES 
  (1, 50, '2025-08-25'),
  (2, 100, '2025-08-22'),
  (3, 30, '2025-08-20');