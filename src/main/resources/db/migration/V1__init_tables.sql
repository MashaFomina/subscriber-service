CREATE SEQUENCE IF NOT EXISTS subscribers.subscriber_id
  increment by 1;

CREATE SEQUENCE IF NOT EXISTS subscribers.call_id
  increment by 1;

CREATE SEQUENCE IF NOT EXISTS subscribers.sms_id
  increment by 1;

CREATE SEQUENCE IF NOT EXISTS subscribers.tariff_id
  increment by 1;

CREATE TABLE IF NOT EXISTS subscribers.tariffs
(
  id              BIGINT        NOT NULL PRIMARY KEY DEFAULT nextval('subscribers.tariffs'),
  name            VARCHAR(255)  NOT NULL UNIQUE,
  price_call      REAL          NOT NULL,
  price_sms       REAL          NOT NULL,
  limit_day_calls SMALLINT      NOT NULL,
  one_sms_size    SMALLINT      NOT NULL,
  created_at      TIMESTAMP     DEFAULT now()
);

insert into subscribers.tariffs (id, name, price_call, price_sms, limit_day_calls, one_sms_size)
values (1, 'Basic', 1.5, 1.2, 5, 64);

CREATE TABLE IF NOT EXISTS subscribers.subscribers
(
  id         BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('subscribers.subscriber_id'),
  name       VARCHAR(255) NOT NULL,
  surname    VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  phone      VARCHAR(255) NOT NULL UNIQUE,
  balance    REAL         DEFAULT 0,
  created_at TIMESTAMP    DEFAULT now(),
  tariff_id  BIGINT       NOT NULL DEFAULT 1 REFERENCES subscribers.tariffs(id)
);

-- default password 123
insert into subscribers.subscribers (name, surname, password, phone, balance)
values
  ('Alexandr', 'Ivanov', '{bcrypt}$2a$10$/X23SC1fw0u1IHatW3kKRuHnt9sME2VGfmX6J95OBkOmwdlNnslGq', '+77770001122', 2.9),
  ('Robert', 'Ivanov', '{bcrypt}$2a$10$/X23SC1fw0u1IHatW3kKRuHnt9sME2VGfmX6J95OBkOmwdlNnslGq', '+77770001133', 3.5);

CREATE TABLE IF NOT EXISTS subscribers.calls
(
  id              BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('subscribers.call_id'),
  datetime        TIMESTAMP    DEFAULT now(),
  duration        REAL         DEFAULT 0.0,
  caller_id       BIGINT       NOT NULL REFERENCES subscribers.subscribers(id),
  receiver_id     BIGINT       DEFAULT NULL REFERENCES subscribers.subscribers(id),
  receiver_phone  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS subscribers.sms
(
  id              BIGINT       NOT NULL PRIMARY KEY DEFAULT nextval('subscribers.sms_id'),
  datetime        TIMESTAMP    DEFAULT now(),
  size            SMALLINT     NOT NULL,
  text            VARCHAR(160) NOT NULL,
  sender_id       BIGINT       NOT NULL REFERENCES subscribers.subscribers(id),
  receiver_id     BIGINT       DEFAULT NULL REFERENCES subscribers.subscribers(id),
  receiver_phone  VARCHAR(255) NOT NULL
);
