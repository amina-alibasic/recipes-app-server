INSERT INTO recipe (name, servings, category_id, preparation_instruction)
VALUES ('Bruschetta', 4, (SELECT id FROM category WHERE name = 'Appetizer'),
        '1. Toast bread. 2. Rub garlic. 3. Add tomatoes.'),
       ('Spaghetti Bolognese', 2, (SELECT id FROM category WHERE name = 'Main Course'),
        '1. Cook spaghetti. 2. Prepare sauce. 3. Combine and serve.'),
       ('Chocolate Cake', 8, (SELECT id FROM category WHERE name = 'Dessert'),
        '1. Preheat oven. 2. Mix ingredients. 3. Bake for 30 minutes.'),
       ('Pancakes', 4, (SELECT id FROM category WHERE name = 'Breakfast'), '1. Mix batter. 2. Cook on griddle.');


INSERT INTO ingredient (name, quantity)
VALUES ('Tomato', '2 cups diced'),
       ('Basil', '1/4 cup chopped'),
       ('Garlic', '2 cloves minced'),
       ('Bread', NULL),
       ('Spaghetti', '200g'),
       ('Ground Beef', '250g'),
       ('Egg', '2'),
       ('Flour', '2 cups'),
       ('Sugar', '1.5 cups'),
       ('Cocoa Powder', '1/2 cup'),
       ('Butter', '1 cup melted'),
       ('Milk', '1 cup');

-- For Bruschetta (Recipe 1)
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity)
VALUES ((SELECT id FROM recipe WHERE name = 'Bruschetta'), (SELECT id FROM ingredient WHERE name = 'Tomato'),
        '3 cups diced'),    -- Overriding quantity
       ((SELECT id FROM recipe WHERE name = 'Bruschetta'), (SELECT id FROM ingredient WHERE name = 'Basil'),
        '1/4 cup chopped'), -- Using default
       ((SELECT id FROM recipe WHERE name = 'Bruschetta'), (SELECT id FROM ingredient WHERE name = 'Garlic'),
        '2 cloves minced'), -- Using default
       ((SELECT id FROM recipe WHERE name = 'Bruschetta'), (SELECT id FROM ingredient WHERE name = 'Bread'),
        '5 slices');
-- Overriding null quantity

-- For Spaghetti Bolognese (Recipe 2)
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity)
VALUES ((SELECT id FROM recipe WHERE name = 'Spaghetti Bolognese'),
        (SELECT id FROM ingredient WHERE name = 'Spaghetti'), '250g'),   -- Overriding quantity
       ((SELECT id FROM recipe WHERE name = 'Spaghetti Bolognese'),
        (SELECT id FROM ingredient WHERE name = 'Ground Beef'), '300g'), -- Overriding quantity
       ((SELECT id FROM recipe WHERE name = 'Spaghetti Bolognese'), (SELECT id FROM ingredient WHERE name = 'Garlic'),
        '1 clove minced');
-- Using default

-- For Chocolate Cake (Recipe 3)
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity)
VALUES ((SELECT id FROM recipe WHERE name = 'Chocolate Cake'), (SELECT id FROM ingredient WHERE name = 'Flour'),
        '2 cups'),   -- Using default
       ((SELECT id FROM recipe WHERE name = 'Chocolate Cake'), (SELECT id FROM ingredient WHERE name = 'Sugar'),
        '1.5 cups'), -- Using default
       ((SELECT id FROM recipe WHERE name = 'Chocolate Cake'), (SELECT id FROM ingredient WHERE name = 'Cocoa Powder'),
        '1/2 cup'),  -- Using default
       ((SELECT id FROM recipe WHERE name = 'Chocolate Cake'), (SELECT id FROM ingredient WHERE name = 'Butter'),
        '1.5 cups melted');
-- Overriding quantity

-- For Pancakes (Recipe 4)
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity)
VALUES ((SELECT id FROM recipe WHERE name = 'Pancakes'), (SELECT id FROM ingredient WHERE name = 'Egg'),
        '3'),        -- Overriding quantity
       ((SELECT id FROM recipe WHERE name = 'Pancakes'), (SELECT id FROM ingredient WHERE name = 'Flour'),
        '1.5 cups'), -- Overriding quantity
       ((SELECT id FROM recipe WHERE name = 'Pancakes'), (SELECT id FROM ingredient WHERE name = 'Milk'),
        '1.5 cups'), -- Overriding quantity
       ((SELECT id FROM recipe WHERE name = 'Pancakes'), (SELECT id FROM ingredient WHERE name = 'Sugar'),
        '2 tbsp'); -- Overriding quantity
