CREATE TABLE IF NOT EXISTS product
(
    id           BIGSERIAL PRIMARY KEY,
    barcode      VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    category     VARCHAR(255) NOT NULL,
    vendor       VARCHAR(255) NOT NULL,
    price        DECIMAL(19, 2) NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

COMMENT ON COLUMN product.id IS 'Первичный ключ';
COMMENT ON COLUMN product.barcode IS 'Штрихкод продукта';
COMMENT ON COLUMN product.name IS 'Название продукта';
COMMENT ON COLUMN product.category IS 'Категория продукта';
COMMENT ON COLUMN product.vendor IS 'Производитель продукта';
COMMENT ON COLUMN product.price IS 'Цена продукта';
COMMENT ON COLUMN product.created_at IS 'Время создания записи';
COMMENT ON COLUMN product.updated_at IS 'Время обновления записи';

ALTER TABLE product
    OWNER TO root;
