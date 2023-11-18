CREATE TABLE IF NOT EXISTS sale_item
(
    id          BIGSERIAL PRIMARY KEY,
    sale_id     BIGINT NOT NULL REFERENCES sale(id),
    product_id  BIGINT NOT NULL,
    quantity    INT NOT NULL,
    unit_price  DECIMAL(19, 2) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

COMMENT ON COLUMN sale_item.id IS 'Первичный ключ';
COMMENT ON COLUMN sale_item.sale_id IS 'Идентификатор продажи';
COMMENT ON COLUMN sale_item.product_id IS 'Идентификатор продукта';
COMMENT ON COLUMN sale_item.quantity IS 'Количество продукта';
COMMENT ON COLUMN sale_item.unit_price IS 'Цена за единицу продукта';
COMMENT ON COLUMN sale_item.created_at IS 'Дата создания записи';
COMMENT ON COLUMN sale_item.updated_at IS 'Дата последнего обновления записи';

ALTER TABLE sale_item
    OWNER TO root;
