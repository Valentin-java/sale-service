CREATE TABLE IF NOT EXISTS customer
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    phone       VARCHAR(50),
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON COLUMN customer.id IS 'Первичный ключ';
COMMENT ON COLUMN customer.name IS 'Имя клиента';
COMMENT ON COLUMN customer.email IS 'Электронная почта клиента';
COMMENT ON COLUMN customer.phone IS 'Телефонный номер клиента';
COMMENT ON COLUMN customer.created_at IS 'Дата создания записи';
COMMENT ON COLUMN customer.updated_at IS 'Дата последнего обновления записи';

ALTER TABLE customer
    OWNER TO root;
