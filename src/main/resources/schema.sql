-- 1. Create the Category table
CREATE TABLE IF NOT EXISTS category
(
    id            SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- 2. Create the Recipe table
CREATE TABLE IF NOT EXISTS recipe
(
    id          SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    servings    INT          NOT NULL,
    category_id INT          NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

-- 3. Create the Ingredient table
CREATE TABLE IF NOT EXISTS ingredient
(
    id              SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- 4. Create the Recipe_Ingredients table (junction table between Recipe and Ingredient)
CREATE TABLE IF NOT EXISTS recipe_ingredients
(
    recipe_id     INT          NOT NULL,
    ingredient_id INT          NOT NULL,
    quantity      VARCHAR(255) NOT NULL,
    PRIMARY KEY (recipe_id, ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredient (id) ON DELETE CASCADE
);
