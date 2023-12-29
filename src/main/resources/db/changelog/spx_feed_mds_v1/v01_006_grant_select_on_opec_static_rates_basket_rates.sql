--liquibase formatted sql
--changeset gleb.chernyakov:01_006

GRANT SELECT ON mds.opec_basket TO "spx-ddp";
GRANT SELECT ON mds.opec_rates TO "spx-ddp";
GRANT SELECT ON mds.opec_static TO "spx-ddp";
GRANT SELECT ON mds.opec_static TO "spx-classifier";