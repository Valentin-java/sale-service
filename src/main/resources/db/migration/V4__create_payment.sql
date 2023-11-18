CREATE TABLE IF NOT EXISTS payment
(
    id          BIGSERIAL PRIMARY KEY,
    sale_id     BIGINT NOT NULL,
    amount      DECIMAL(19, 2) NOT NULL,
    method      VARCHAR(50) NOT NULL,
    status      VARCHAR(50) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON COLUMN payment.id IS 'Первичный ключ';
COMMENT ON COLUMN payment.sale_id IS 'Идентификатор продажи';
COMMENT ON COLUMN payment.amount IS 'Сумма оплаты';
COMMENT ON COLUMN payment.method IS 'Метод оплаты (наличные, карта и т.д.)';
COMMENT ON COLUMN payment.status IS 'Статус оплаты (оплачено, не оплачено)';
COMMENT ON COLUMN payment.created_at IS 'Дата создания записи';
COMMENT ON COLUMN payment.updated_at IS 'Дата последнего обновления записи';

ALTER TABLE payment
    OWNER TO root;
