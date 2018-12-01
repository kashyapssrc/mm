/************ALTERING IN TABLE trn_electronics***********/

RENAME TABLE trn_electronics TO renamed_electronics;
SELECT * FROM renamed_electronics;
RENAME TABLE renamed_electronics TO trn_electronics;

ALTER TABLE trn_electronics DROP COLUMN manufacturer_name;
ALTER TABLE trn_electronics DROP COLUMN product_name;



/************ALTERING IN TABLE trn_model***********/

ALTER TABLE trn_model ADD(manufacture_date DATE);
ALTER TABLE trn_model DROP COLUMN manufacture_date;
ALTER TABLE trn_model CHANGE COLUMN manufacture_date launch_date DATE;
ALTER TABLE trn_model CHANGE COLUMN launch_date manufacture_date DATE;
ALTER TABLE trn_model MODIFY manufacture_date INT;
ALTER TABLE trn_model MODIFY manufacture_date DATE;