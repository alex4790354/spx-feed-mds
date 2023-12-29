--liquibase formatted sql
--changeset gleb.chernyakov:01_007

GRANT USAGE ON SCHEMA mds TO "spx-ddp";
GRANT USAGE ON SCHEMA mds TO "spx-classifier";

GRANT SELECT ON TABLE mds.opec_rates TO "spx-classifier";
GRANT SELECT ON TABLE mds.opec_basket TO "spx-classifier";