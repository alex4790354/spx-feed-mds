--liquibase formatted sql
--changeset gleb.chernyakov:01_005

CREATE TABLE IF NOT EXISTS mds.opec_rates
(
	sid varchar(50),
	update_date date,
	effective_date date,
	"value" numeric(14,4),
	updated_ts timestamptz null default now(),
	created_ts timestamptz not null default now(),
    CONSTRAINT opec_rates_unique_key UNIQUE NULLS NOT DISTINCT (sid, update_date, effective_date)
);