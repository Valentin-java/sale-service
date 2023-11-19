CREATE TABLE IF NOT EXISTS inventory_item
(
    id              BIGSERIAL PRIMARY KEY,
    product_id      BIGINT NOT NULL REFERENCES Product(id),
    store_id        BIGINT NOT NULL,
    quantity        INT NOT NULL,
    special_price   DECIMAL(19, 2),
    available       BOOLEAN NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

COMMENT ON COLUMN inventory_item.id IS 'Первичный ключ';
COMMENT ON COLUMN inventory_item.product_id IS 'Внешний ключ к таблице Product';
COMMENT ON COLUMN inventory_item.store_id IS 'Внешний ключ к таблице Store';
COMMENT ON COLUMN inventory_item.quantity IS 'Количество доступного продукта';
COMMENT ON COLUMN inventory_item.special_price IS 'Специальная цена продукта';
COMMENT ON COLUMN inventory_item.available IS 'Доступность продукта';
COMMENT ON COLUMN inventory_item.created_at IS 'Время создания записи';
COMMENT ON COLUMN inventory_item.updated_at IS 'Время обновления записи';

ALTER TABLE inventory_item
    OWNER TO root;