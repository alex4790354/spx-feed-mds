--liquibase formatted sql
--changeset gleb.chernyakov:01_004

CREATE TABLE IF NOT EXISTS mds.opec_static
(
	sid varchar(50),
	instr_name varchar(1024),
	instr_name_eng varchar(1024),
	frequency varchar(10),
	"source" varchar(50),
	unit varchar(50),
	crncy varchar(50),
	prod_perm int,
	updated_ts timestamptz null default now(),
	created_ts timestamptz not null default now(),
    CONSTRAINT opec_static_unique_key UNIQUE NULLS NOT DISTINCT (sid)
);