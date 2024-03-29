INSERT INTO provider (address, contact_number, name)
VALUES ('123 Main St', '123-456-7890', 'Provider A');

INSERT INTO provider (address, contact_number, name)
VALUES ('456 Oak Ave', '987-654-3210', 'Provider B');

INSERT INTO provider (address, contact_number, name)
VALUES ('789 Elm St', '555-123-4567', 'Provider C');

INSERT INTO users (username, password, enabled, date_license, address, contact_number, name) VALUES ('daniel','$2a$10$g2x5dFa3o/H.JNL5lemMbux18g3Tw1CI5xW9HqZnijhWmmmktnnVW',true, '2025-02-21 19:55:55' , '123 Main St', '123-456-7890', 'Provider A');
INSERT INTO users (username, password, enabled, date_license, address, contact_number, name) VALUES ('cristian','$2a$10$70k3hS.ITKWdonTA7P5KLO73ojmfqPp8uf.iMFdIHYCTzKTfVlAaO',true, '2025-02-21 19:55:55', '456 Oak Ave', '987-654-3210', 'Provider B');
INSERT INTO users (username, password, enabled, date_license, address, contact_number, name) VALUES ('Jess','$2a$10$70k3hS.ITKWdonTA7P5KLO73ojmfqPp8uf.iMFdIHYCTzKTfVlAaO',true, '2025-02-21 19:55:55', '789 Elm St', '555-123-4567', 'Provider C');


INSERT INTO roles (name) VALUES ('ROLE_PROVIDER');
INSERT INTO roles (name) VALUES ('ROLE_SELLER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);

-- For Provider A
INSERT INTO product_in_stock (description, name, price, quantity, provider_id)
VALUES
    ('Product A1 Description', 'Product A1', 10.99, 100, 1),
    ('Product A2 Description', 'Product A2', 20.50, 50, 1),
    ('Product A3 Description', 'Product A3', 15.75, 75, 1),
    -- Add more products for Provider A
    ('Product A4 Description', 'Product A4', 18.25, 60, 1),
    ('Product A5 Description', 'Product A5', 12.99, 80, 1),
    -- Add more products for Provider A
    ('Product A6 Description', 'Product A6', 25.00, 45, 1),
    ('Product A7 Description', 'Product A7', 30.75, 70, 1),
    -- Add more products for Provider A
    ('Product A8 Description', 'Product A8', 22.50, 55, 1),
    ('Product A9 Description', 'Product A9', 17.25, 90, 1),
    -- Add more products for Provider A
    ('Product A10 Description', 'Product A10', 16.99, 120, 1),
    -- Add more products for Provider A
    ('Product A11 Description', 'Product A11', 19.50, 65, 1),
    -- Add more products for Provider A
    ('Product A12 Description', 'Product A12', 23.75, 85, 1),
    -- Add more products for Provider A
    ('Product A13 Description', 'Product A13', 28.25, 55, 1),
    -- Add more products for Provider A
    ('Product A14 Description', 'Product A14', 14.99, 100, 1),
    -- Add more products for Provider A
    ('Product A15 Description', 'Product A15', 21.50, 70, 1),
    -- Add more products for Provider A
    ('Product A16 Description', 'Product A16', 27.75, 90, 1),
    -- Add more products for Provider A
    ('Product A17 Description', 'Product A17', 32.50, 110, 1),
    -- Add more products for Provider A
    ('Product A18 Description', 'Product A18', 35.25, 45, 1),
    -- Add more products for Provider A
    ('Product A19 Description', 'Product A19', 39.99, 75, 1),
    -- Add more products for Provider A
    ('Product A20 Description', 'Product A20', 42.50, 95, 1);

-- For Provider B
INSERT INTO product_in_stock (description, name, price, quantity, provider_id)
VALUES
    ('Product B1 Description', 'Product B1', 10.99, 100, 2),
    ('Product B2 Description', 'Product B2', 20.50, 50, 2),
    ('Product B3 Description', 'Product B3', 15.75, 75, 2),
    -- Add more products for Provider B
    ('Product B4 Description', 'Product B4', 18.25, 60, 2),
    ('Product B5 Description', 'Product B5', 12.99, 80, 2),
    -- Add more products for Provider B
    ('Product B6 Description', 'Product B6', 25.00, 45, 2),
    ('Product B7 Description', 'Product B7', 30.75, 70, 2),
    -- Add more products for Provider B
    ('Product B8 Description', 'Product B8', 22.50, 55, 2),
    ('Product B9 Description', 'Product B9', 17.25, 90, 2),
    -- Add more products for Provider B
    ('Product B10 Description', 'Product B10', 16.99, 120, 2),
    -- Add more products for Provider B
    ('Product B11 Description', 'Product B11', 19.50, 65, 2),
    -- Add more products for Provider B
    ('Product B12 Description', 'Product B12', 23.75, 85, 2),
    -- Add more products for Provider B
    ('Product B13 Description', 'Product B13', 28.25, 55, 2),
    -- Add more products for Provider B
    ('Product B14 Description', 'Product B14', 14.99, 100, 2),
    -- Add more products for Provider B
    ('Product B15 Description', 'Product B15', 21.50, 70, 2),
    -- Add more products for Provider B
    ('Product B16 Description', 'Product B16', 27.75, 90, 2),
    -- Add more products for Provider B
    ('Product B17 Description', 'Product B17', 32.50, 110, 2),
    -- Add more products for Provider B
    ('Product B18 Description', 'Product B18', 35.25, 45, 2),
    -- Add more products for Provider B
    ('Product B19 Description', 'Product B19', 39.99, 75, 2),
    -- Add more products for Provider B
    ('Product B20 Description', 'Product B20', 42.50, 95, 2);

-- For Provider C
INSERT INTO product_in_stock (description, name, price, quantity, provider_id)
VALUES
    ('Product C1 Description', 'Product C1', 10.99, 100, 3),
    ('Product C2 Description', 'Product C2', 20.50, 50, 3),
    ('Product C3 Description', 'Product C3', 15.75, 75, 3),
    ('Product C4 Description', 'Product C4', 18.25, 60, 3),
    ('Product C5 Description', 'Product C5', 12.99, 80, 3),
    ('Product C6 Description', 'Product C6', 25.00, 45, 3),
    ('Product C7 Description', 'Product C7', 30.75, 70, 3);


INSERT INTO product_in_stock (description, name, price, quantity, provider_id)
VALUES
    ('Product C1 Description', 'Product C1', 10.99, 100, 3),
    ('Product C2 Description', 'Product C2', 20.50, 50, 3),
    ('Product C3 Description', 'Product C3', 15.75, 75, 3),
    ('Product C4 Description', 'Product C4', 18.25, 60, 3),
    ('Product C5 Description', 'Product C5', 12.99, 80, 3),
    ('Product C6 Description', 'Product C6', 25.00, 45, 3),
    ('Product C7 Description', 'Product C7', 30.75, 70, 3);



