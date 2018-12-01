/************CREATING TABLE trn_electronics***********/

CREATE TABLE trn_electronics (
									  item_id INT AUTO_INCREMENT PRIMARY KEY
									, product_name VARCHAR(20)
									, model_name VARCHAR(20) NOT NULL
									, manufacturer_name VARCHAR(20)
									, product_type VARCHAR(20)
									, colour VARCHAR(20)
									, storage_capacity_GB INT
									, quantity INT
									, price INT /*CHECK(price<100000)*/
	                         			
									, product_id INT
									, model_id_number INT
									, manufacturer_id INT
									, INDEX (manufacturer_name, product_name)
									
									, KEY product_id_key(product_id)
									, CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES trn_product(pd_id) ON DELETE NO ACTION ON UPDATE NO ACTION
									
									, KEY model_id_key(model_id_number)
									, CONSTRAINT fk_model_id FOREIGN KEY(model_id_number) REFERENCES trn_model(md_id_number) ON DELETE NO ACTION ON UPDATE NO ACTION
									
									, KEY manufacturer_id_key(manufacturer_id)
									, CONSTRAINT fk_manufacturer_id FOREIGN KEY(manufacturer_id) REFERENCES trn_manufacturer(mf_id) ON DELETE NO ACTION ON UPDATE NO ACTION
									
								
								);
								
								

/************CREATING TABLE trn_manufacturer***********/
								
CREATE TABLE trn_manufacturer (
									 manufacturer_name VARCHAR(20)
								   , mf_id INT AUTO_INCREMENT PRIMARY KEY
								);
								
								
								
/************CREATING TABLE trn_model***********/

CREATE TABLE trn_model (
							 md_id_number INT AUTO_INCREMENT PRIMARY KEY
						  , model_name VARCHAR(20)
						  , md_id VARCHAR(10) UNIQUE
						);
						
						
						
/************CREATING TABLE trn_product***********/

CREATE TABLE trn_product (
									  product_name VARCHAR(20)
								   , pd_id INT AUTO_INCREMENT PRIMARY KEY
								   , availability CHAR DEFAULT 'A'
								);



/************CREATING TABLE trn_table_join***********/

CREATE TABLE trn_table_join(
								       product_name VARCHAR(20)
									  , model_name VARCHAR(20)
									  , price_Rs INT CHECK(price_Rs < 10)
									  , manufacturer_id INT
								   );
									


/************CREATING TABLE trn_new_table_join***********/

CREATE TABLE trn_new_table_join(
									
								         product_name VARCHAR(20)
									    , model_name VARCHAR(20) NOT NULL
									    , price_Rs INT NOT NULL
									    , manufacturer_id INT
										);
										
										
										
/************INSERTING VALUES FOR trn_electronics***********/

DELIMITER //
CREATE TRIGGER test_before_insert BEFORE INSERT ON trn_electronics
FOR EACH ROW
BEGIN
    IF  (NEW.price >100000) THEN
        SET NEW.price=0;
		  /*SET MESSAGE_TEXT = "check constraint on price failed";*/
    END IF;
END//   
DELIMITER;   					

INSERT INTO trn_electronics (product_name, model_name, manufacturer_name, product_type, colour, storage_capacity_GB, quantity, price, product_id, model_id_number, manufacturer_id )
VALUES 
  ("Mobile", "Galaxy S9", "Samsung", "Smartphone", "Black", 64, 1, 53850, 1, 1, 1)
, ("Mobile", "Galaxy Note 8", "Samsung", "Phablet", "Blue", 64, 1, 55900, 1, 2, 1)
, ("Gaming Console", "PlayStation 4", "Sony", "Slim console", "Black", 1000, 1, 38599, 2, 3, 2)
, ("Laptop", "Legion Y520", "Lenovo", "Gaming laptop", "Black", 1000, 2,200000, 3, 4, 3)
, ("Mobile", "Oneplus 6", "OnePlus", "Smartphone", "Silver", 64, 3, 34999, 1, 5, 4)
, ("Gaming Console", "Xbox One X", "Microsoft", "Slim console", "White", 1000, 1, 40990, 2, 6, 5);
 
 
 
/************INSERTING VALUES FOR trn_manufacturer***********/

INSERT INTO trn_manufacturer (manufacturer_name) VALUES ("Samsung");
INSERT INTO trn_manufacturer (manufacturer_name) VALUES ("Sony");
INSERT INTO trn_manufacturer (manufacturer_name) VALUES ("Lenovo");
INSERT INTO trn_manufacturer (manufacturer_name) VALUES ("OnePlus");
INSERT INTO trn_manufacturer (manufacturer_name) VALUES ("Microsoft");

/*INSERT INTO trn_manufacturer (manufacturer_name) VALUES ("XYZ");*/



/************INSERTING VALUES FOR trn_model***********/

INSERT INTO trn_model (model_name, md_id) VALUES ("Galaxy S9", "SM-G960/DS");
INSERT INTO trn_model (model_name, md_id) VALUES ("Galaxy Note 8", "SM-N950F");
INSERT INTO trn_model (model_name, md_id) VALUES ("PlayStation 4", "B079QBC25W");
INSERT INTO trn_model (model_name, md_id) VALUES ("Legion Y520", "80WK00R0IN");
INSERT INTO trn_model (model_name, md_id) VALUES ("Oneplus 6", "A6000");
INSERT INTO trn_model (model_name, md_id) VALUES ("Xbox One X", "CYV-00022");

INSERT INTO trn_model(manufacture_date) 
VALUES (20170423)
, (20180510)
, (20141115)
, (20151001)
, (20171215)
, (20170205);

UPDATE trn_model SET manufacture_date = 20170423 WHERE md_id = "SM-G960/DS";
UPDATE trn_model SET manufacture_date = 20180510 WHERE md_id = "SM-N950F";
UPDATE trn_model SET manufacture_date = 20141115 WHERE md_id = "B079QBC25W";
UPDATE trn_model SET manufacture_date = 20151001 WHERE md_id = "80WK00R0IN";
UPDATE trn_model SET manufacture_date = 20171215 WHERE md_id = "A6000";
UPDATE trn_model SET manufacture_date = 20170205 WHERE md_id = "CYV-00022";



/************INSERTING VALUES FOR trn_product***********/
 
INSERT INTO trn_product (product_name) VALUES ("Mobile");
INSERT INTO trn_product (product_name) VALUES ("Gaming Console");
INSERT INTO trn_product (product_name) VALUES ("Laptop");



/************INSERTING VALUES FOR trn_table_join***********/

INSERT INTO trn_table_join VALUES ("Mobile", "Galaxy S9",  53850,1);								
INSERT INTO trn_table_join VALUES ("Mobile", "Galaxy Note 8",  55900,1);
INSERT INTO trn_table_join VALUES ("Gaming Console", "PlayStation 4",  38599,2);
INSERT INTO trn_table_join VALUES ("Laptop", "Legion Y520",  99899,3);
INSERT INTO trn_table_join VALUES ("Mobile", "Oneplus 6",  34999,4);
INSERT INTO trn_table_join VALUES ("Gaming Console", "Xbox One X", 40990,5);
INSERT INTO trn_table_join VALUES ("XYZ", "AAA", 2020,6);



/************INSERTING VALUES FOR trn_new_table_join***********/

INSERT INTO trn_new_table_join VALUES ("ABCD", "FGHFGH",  2589,8);								
INSERT INTO trn_new_table_join VALUES ("QWERTY", "UIOP",  4567,9);
INSERT INTO trn_new_table_join VALUES ("Gaming Console", "PlayStation 4",  38599,2); 
INSERT INTO trn_new_table_join VALUES ("Laptop", "Legion Y520",  99899,3);

/*INSERT INTO trn_new_table_join VALUES (NULL, NULL,NULL,10);
INSERT INTO trn_new_table_join VALUES (NULL, "NULL CHECKED",1010,10);*/