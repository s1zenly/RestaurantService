UPDATE dishes_orders
SET quantity = quantity + ?
WHERE order_id = ? AND dish_id = ?