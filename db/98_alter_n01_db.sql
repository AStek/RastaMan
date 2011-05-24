alter table "ACCOUNT" add constraint
"ACCOUNT_LGN" unique ("LOGIN")
/
alter table "RESOURCES" add constraint
"RESOURCES_TTL" unique ("TITLE")
/
alter table "ROLE" add constraint
"ROLE_TTL" unique ("TITLE")
/