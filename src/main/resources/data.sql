INSERT INTO category (name) VALUES ('Appetizer') ON CONFLICT (name) DO NOTHING;;
INSERT INTO category (name) VALUES ('Main Course') ON CONFLICT (name) DO NOTHING;;
INSERT INTO category (name) VALUES ('Dessert') ON CONFLICT (name) DO NOTHING;;
INSERT INTO category (name) VALUES ('Breakfast') ON CONFLICT (name) DO NOTHING;;
