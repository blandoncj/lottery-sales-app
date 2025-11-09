-- database schema for lottery sales application (demo version because I use Hibernate to create the tables automatically)

CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    document_number VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE lottery_draws (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    draw_date TIMESTAMP NOT NULL
);

CREATE TABLE lottery_tickets (
    id BIGINT PRIMARY KEY,
    number VARCHAR(10) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    draw_id BIGINT,
    CONSTRAINT fk_draw FOREIGN KEY (draw_id) REFERENCES lottery_draws(id)
);

CREATE TABLE sales (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE sale_tickets (
    sale_id BIGINT NOT NULL,
    ticket_id BIGINT NOT NULL,
    PRIMARY KEY (sale_id, ticket_id),
    CONSTRAINT fk_sale FOREIGN KEY (sale_id) REFERENCES sales(id),
    CONSTRAINT fk_ticket FOREIGN KEY (ticket_id) REFERENCES lottery_tickets(id)
);
