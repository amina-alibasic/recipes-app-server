CREATE TABLE IF NOT EXISTS category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS recipe
(
    id                      BIGSERIAL PRIMARY KEY,
    name                    VARCHAR(255)                        NOT NULL UNIQUE,
    servings                INT                                 NOT NULL,
    category_id             INT                                 NOT NULL,
    preparation_instruction TEXT                                NOT NULL,
    date                    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS ingredient
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    quantity VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS recipe_ingredients
(
    recipe_id     BIGINT       NOT NULL,
    ingredient_id BIGINT       NOT NULL,
    quantity      VARCHAR(255) NOT NULL,
    PRIMARY KEY (recipe_id, ingredient_id),
    FOREIGN KEY (recipe_id) REFERENCES recipe (id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredient (id) ON DELETE CASCADE
);
