--liquibase formatted sql
--changeset gleb.chernyakov:01_008

GRANT SELECT ON TABLE mds.central_bank_questionnaire TO "spx-classifier";