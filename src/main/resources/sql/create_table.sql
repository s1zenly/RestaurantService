CREATE TABLE IF NOT EXISTS personalities (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (

) INHERITS (personalities);

CREATE TABLE IF NOT EXISTS admins (
    access_level access_enum NOT NULL
) INHERITS (personalities);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status status_enum DEFAULT 'Inactive',
    user_id INT NOT NULL,
    difficult INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES personalities(id)
);

CREATE TABLE IF NOT EXISTS dishes (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    difficult INTEGER NOT NULL,
    price INT NOT NULL
);

CREATE TABLE IF NOT EXISTS dishes_orders (
    order_id INT NOT NULL,
    dish_id INT NOT NULL,
    PRIMARY KEY (order_id, dish_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (dish_id) REFERENCES dishes(id)
);
