--liquibase formatted sql
--changeset gleb.chernyakov:01_003

CREATE TABLE IF NOT EXISTS mds.opec_basket
(
	sid varchar(50),
	update_date date,
	"value" numeric(14, 4),
	updated_ts timestamptz null default now(),
	created_ts timestamptz not null default now(),
    CONSTRAINT opec_basket_unique_key UNIQUE NULLS NOT DISTINCT (sid, update_date, "value")
);