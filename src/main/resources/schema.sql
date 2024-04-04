--удаление таблиц если существуют

--таблица соответствий между пользователем и товаром (many to many отношение)
drop table if exists buyer_order;
--таблица товаров
drop table if exists stock;
--таблица пользователей
drop table if exists buyer;

--создание новых таблиц

--поле id уникальный идентификатор товара, поле description описание товара, поле price цена за единицу товара, поле quant количество доступных единиц товара
create table if not exists stock (id bigint not null, description text, price real, quant bigint, primary key(id));
--поле id уникальный идентификатор пользователя, поле name имя пользователя, поле surname фамилия пользователя
create table if not exists buyer (id bigint not null, name text, surname text, primary key(id));
--поле stock_id ункиальный идентификатор товара должен содержаться в таблице товаров, поле buyer_id уникальный идентификатор пользователя должен
--содержаться в таблице пользователей, поле date время совершения покупки, поле order_count количество купленных единиц товара
create table if not exists buyer_order (stock_id bigint not null, buyer_id bigint not null,
                                        date timestamp, order_count bigint,
                                        foreign key (stock_id) references stock(id) on delete cascade,
                                        foreign key (buyer_id) references buyer(id) on delete cascade);

--заполнение таблицы товаров
insert into stock(id, description, price, quant) values (0, 'stock 1', 10.0, 10);
insert into stock(id, description, price, quant) values (1, 'stock 2', 11.0, 11);
insert into stock(id, description, price, quant) values (2, 'stock 3', 12.0, 12);
insert into stock(id, description, price, quant) values (3, 'stock 4', 13.0, 13);
insert into stock(id, description, price, quant) values (4, 'stock 5', 14.0, 14);
insert into stock(id, description, price, quant) values (5, 'stock 6', 15.0, 15);
insert into stock(id, description, price, quant) values (6, 'stock 7', 16.0, 16);
insert into stock(id, description, price, quant) values (7, 'stock 8', 17.0, 17);
insert into stock(id, description, price, quant) values (8, 'stock 9', 18.0, 18);
insert into stock(id, description, price, quant) values (9, 'stock 10', 19.0, 19);
insert into stock(id, description, price, quant) values (10, 'stock 11', 20.0, 20);

--проверка корректности заполнения
select * from stock;

--заполнение таблицы покупателей
insert into buyer(id, name, surname) values (0, 'name 1', 'surname 1');
insert into buyer(id, name, surname) values (1, 'name 2', 'surname 2');
insert into buyer(id, name, surname) values (2, 'name 3', 'surname 3');
insert into buyer(id, name, surname) values (3, 'name 4', 'surname 4');
insert into buyer(id, name, surname) values (4, 'name 5', 'surname 5');
insert into buyer(id, name, surname) values (5, 'name 6', 'surname 6');
insert into buyer(id, name, surname) values (6, 'name 7', 'surname 7');
insert into buyer(id, name, surname) values (7, 'name 8', 'surname 8');
insert into buyer(id, name, surname) values (8, 'name 9', 'surname 9');
insert into buyer(id, name, surname) values (9, 'name 10', 'surname 10');
insert into buyer(id, name, surname) values (10, 'name 11', 'surname 11');

--проверка заполнения
select * from buyer;

--заполнение таблицы заказов
insert into buyer_order(stock_id, buyer_id, date, order_count) values (0, 0, current_timestamp, 1);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (0, 1, current_timestamp, 2);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (0, 2, current_timestamp, 1);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (0, 3, current_timestamp, 4);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (1, 0, current_timestamp, 9);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (1, 10, current_timestamp, 2);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (4, 2, current_timestamp, 2);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (3, 1, current_timestamp, 4);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (1, 1, current_timestamp, 7);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (2, 5, current_timestamp, 8);
insert into buyer_order(stock_id, buyer_id, date, order_count) values (5, 1, current_timestamp, 7);

--проверка заполнения
select * from buyer_order;