--liquibase formatted sql
--changeset gleb.chernyakov:01_001
--CREATE SCHEMA IF NOT EXISTS mds;

CREATE TABLE IF NOT EXISTS mds.central_bank_questionnaire
(
	sid varchar(50),
	instr_name varchar(1024),
	tenor varchar(10),
	"source" varchar(50),
	unit varchar(50),
	crncy varchar(10),
	consensus_date date,
	f_val_av numeric(14,4),
	f_val_mn numeric(14,4),
	f_val_mx numeric(14,4),
	f_val_md numeric(14,4),
	f_val_a_pers numeric(14,4),
	prod_perm int,
	created_ts timestamptz not null default now(),
	updated_ts timestamptz null default now(),
    CONSTRAINT central_bank_questionnaire_unique_key UNIQUE NULLS NOT DISTINCT (sid, consensus_date)
);