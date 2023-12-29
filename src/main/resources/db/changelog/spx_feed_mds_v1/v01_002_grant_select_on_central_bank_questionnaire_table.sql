--liquibase formatted sql
--changeset gleb.chernyakov:01_002

GRANT SELECT ON mds.central_bank_questionnaire TO "spx-ddp";