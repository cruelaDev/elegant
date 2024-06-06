create table "order"
(
    id      uuid primary key,
    user_id uuid references "user"
);

CREATE TABLE address
(
    id           uuid PRIMARY KEY,
    city         varchar NOT NULL,
    country      varchar NOT NULL,
    state        varchar NOT NULL,
    zip_code      int     NOT NULL,
    street       varchar NOT NULL,
    phone_number varchar NOT NULL,
    order_id     uuid UNIQUE,
    FOREIGN KEY (order_id) REFERENCES "order"
);

CREATE TABLE product_set_order
(
    order_id       uuid REFERENCES "order",
    product_set_id uuid REFERENCES product_set,
    PRIMARY KEY (order_id, product_set_id)
);
