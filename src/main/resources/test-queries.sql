--вывод всех элементов в таблице покупаталей
select id, name, surname from buyer;

--вывод покупателя с id 0
select * from buyer where id = 0;

--обновление покупателя с id 0
update buyer set name = 'pokupatel 1', surname = 'familiya 1' where id = 0;

--проверка обновления
select * from buyer where id = 0;

--поиск заказов покупателя с id 0
select * from buyer_order where buyer_id = 0;

--джоин заказов для покупателя с id 0
select * from buyer_order join stock on stock.id = buyer_order.stock_id where buyer_order.buyer_id = 0;

--удаление покупателя с id 0
delete from buyer where id = 0;

--джоин заказов для покупателя с id 0 (должно ничего не вернутся тк мы его удалили)
select * from buyer_order join stock on stock.id = buyer_order.stock_id where buyer_order.buyer_id = 0;

