INSERT INTO category (name)
VALUES ('Appetizer'),
       ('Main Course'),
       ('Dessert'),
       ('Breakfast'),
       ('Soup'),
       ('Salad'),
       ('Side Dish'),
       ('Snack')
ON CONFLICT (name) DO NOTHING;
