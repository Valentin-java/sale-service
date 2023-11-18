CREATE TABLE IF NOT EXISTS discount
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount      DECIMAL NOT NULL,
    start_date  TIMESTAMP NOT NULL,
    end_date    TIMESTAMP NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON COLUMN discount.id IS 'Первичный ключ';
COMMENT ON COLUMN discount.description IS 'Описание скидки';
COMMENT ON COLUMN discount.amount IS 'Сумма скидки';
COMMENT ON COLUMN discount.start_date IS 'Дата начала скидки';
COMMENT ON COLUMN discount.end_date IS 'Дата окончания скидки';
COMMENT ON COLUMN discount.created_at IS 'Дата создания записи';
COMMENT ON COLUMN discount.updated_at IS 'Дата последнего обновления записи';

ALTER TABLE discount
    OWNER TO root;
