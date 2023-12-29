--liquibase formatted sql
--changeset alexandr.vasilev:01_009

CREATE TABLE IF NOT EXISTS mds.minec_static
(
	sid                 VARCHAR(50),
	instr_name          VARCHAR(1024),
    instr_name_eng      VARCHAR(1024),
    frequency           VARCHAR(50),
    source_name         VARCHAR(50),
    source_name_eng     VARCHAR(50),
    source_link         VARCHAR(1024),
    source_id           VARCHAR(50),
    unit                VARCHAR(50),
    unit_eng            VARCHAR(50),
    crncy               VARCHAR(10),
    prod_perm           VARCHAR(50),
    creation_time       TIMESTAMP WITH TIME ZONE DEFAULT now(),
    update_time         TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT PK_MINEC_STATIC PRIMARY KEY (sid)
);

CREATE TABLE IF NOT EXISTS mds.minec_export
(
    sid                 VARCHAR(50),
    update_date         DATE,
    effective_date      DATE,
    value               NUMERIC(14, 4),
    creation_time       TIMESTAMP WITH TIME ZONE DEFAULT now(),
    update_time         TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT PK_MINEC_EXPORT PRIMARY KEY (sid, update_date, effective_date)
);

CREATE TABLE IF NOT EXISTS mds.minec_forecast
(
    sid                 VARCHAR(50),
    update_date         DATE,
    effective_date      DATE,
    f_val               NUMERIC(14, 4),
    f_val_mn            NUMERIC(14, 4),
    creation_time       TIMESTAMP WITH TIME ZONE DEFAULT now(),
    update_time         TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT PK_MINEC_FORECAST PRIMARY KEY (sid, update_date, effective_date)
);
