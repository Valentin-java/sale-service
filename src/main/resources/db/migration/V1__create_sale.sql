CREATE TABLE IF NOT EXISTS sale
(
    id           BIGSERIAL PRIMARY KEY,
    customer_id  BIGINT NOT NULL,
    store_id     BIGINT NOT NULL,
    sale_date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(19, 2) NOT NULL,
    discount_id  BIGINT,
    payment_id   BIGINT NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

COMMENT ON COLUMN sale.id IS 'Первичный ключ';
COMMENT ON COLUMN sale.customer_id IS 'Идентификатор клиента';
COMMENT ON COLUMN sale.store_id IS 'Идентификатор магазина';
COMMENT ON COLUMN sale.sale_date IS 'Дата продажи';
COMMENT ON COLUMN sale.total_amount IS 'Общая сумма продажи';
COMMENT ON COLUMN sale.discount_id IS 'Идентификатор скидки';
COMMENT ON COLUMN sale.payment_id IS 'Идентификатор оплаты';
COMMENT ON COLUMN sale.created_at IS 'Дата создания записи';
COMMENT ON COLUMN sale.updated_at IS 'Дата последнего обновления записи';

ALTER TABLE sale
    OWNER TO root;
