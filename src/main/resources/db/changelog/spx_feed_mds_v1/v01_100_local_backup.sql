--liquibase formatted sql
--changeset alexandr.vasilev:01_009

CREATE TABLE IF NOT EXISTS mds.central_bank_questionnaire
(
    sid VARCHAR(50),
    consensus_date VARCHAR(50),
    instr_name VARCHAR(50),
    tenor VARCHAR(50),
    source VARCHAR(50),
    unit VARCHAR(50),
    crncy VARCHAR(50),
    f_val_av VARCHAR(50),
    f_val_mn VARCHAR(50),
    f_val_mx VARCHAR(50),
    f_val_md VARCHAR(50),
    f_val_a_pers VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS mds.opec_basket
(
    sid VARCHAR(50),
    update_date VARCHAR(50),
    value VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS mds.opec_static
(
    sid VARCHAR(50),
    instr_name VARCHAR(50),
    instr_name_eng VARCHAR(50),
    frequency VARCHAR(50),
    source VARCHAR(50),
    unit VARCHAR(50),
    crncy VARCHAR(50),
    prod_perm VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS mds.opec_rates
(
    sid VARCHAR(50),
    update_date VARCHAR(50),
    effective_date VARCHAR(50),
    value int
);

