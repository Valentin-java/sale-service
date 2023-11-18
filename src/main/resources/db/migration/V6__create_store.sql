CREATE TABLE IF NOT EXISTS store
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    location    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON COLUMN store.id IS 'Первичный ключ';
COMMENT ON COLUMN store.name IS 'Название магазина';
COMMENT ON COLUMN store.location IS 'Местоположение магазина';
COMMENT ON COLUMN store.created_at IS 'Дата создания записи';
COMMENT ON COLUMN store.updated_at IS 'Дата последнего обновления записи';

ALTER TABLE store
    OWNER TO root;
