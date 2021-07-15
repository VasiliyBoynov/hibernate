DROP TABLE IF EXISTS products CASCADE;
create table products
(
    ID bigint auto_increment,
    title varchar not null,
    cost varchar not null,
    constraint PRODUCTS_PK
        primary key (ID)
);
INSERT INTO products (title, cost) VALUES
('box', '10.1'),
('sphere', '20.2'),
('maul', '100.3'),
('door', '50.4'),
('camera', '500.5');