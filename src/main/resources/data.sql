INSERT INTO category (name)
VALUES ('Appetizer'),
       ('Main Course'),
       ('Dessert'),
       ('Breakfast')
ON CONFLICT (name) DO NOTHING;
