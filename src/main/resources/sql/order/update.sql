UPDATE orders SET status = ?::status_enum,
                  payment_status = ?::payment_enum,
                  difficult = ?,
                  price = ?
WHERE id = ?;