/************OPERATIONS ON TABLE trn_electronics***********/

SELECT * FROM trn_electronics;
TRUNCATE TABLE trn_electronics;
DROP TABLE trn_electronics;

SELECT * FROM trn_electronics WHERE manufacturer_id = (SELECT id FROM manufacturer WHERE manufacturer_name = "Samsung");
SELECT MAX(price) FROM trn_electronics;
SELECT MIN(price) FROM trn_electronics;
SELECT SUM(price) FROM trn_electronics;
SELECT AVG(price) FROM trn_electronics;
SELECT COUNT(price) FROM trn_electronics;

SELECT storage_capacity_GB, COUNT(item_id) FROM trn_electronics GROUP BY storage_capacity_GB HAVING COUNT(item_id) > 2;

SELECT * FROM trn_electronics ORDER BY storage_capacity_GB;
SELECT * FROM trn_electronics ORDER BY price;
SELECT * FROM trn_electronics ORDER BY price DESC;
SELECT * FROM trn_electronics GROUP BY product_name HAVING (product_name IN ("Laptop","Mobile"));
SELECT * FROM trn_electronics WHERE product_name IN ("Laptop","Mobile") GROUP BY product_name;
SELECT * FROM trn_electronics GROUP BY storage_capacity_GB ;
SELECT * FROM trn_electronics GROUP BY storage_capacity_GB HAVING (item_id>2);
SELECT * FROM trn_electronics GROUP BY storage_capacity_GB HAVING (item_id>2) OR product_id=1;
SELECT * FROM (SELECT * FROM trn_electronics GROUP BY manufacturer_name) AS grp ORDER BY item_id;
SELECT * FROM trn_electronics GROUP BY manufacturer_name HAVING (item_id>2);

CREATE VIEW electonics_view AS SELECT * FROM trn_electronics;

SELECT * FROM trn_electronics WHERE product_name LIKE "__p%";
SELECT * FROM trn_electronics WHERE product_name LIKE "_a%" AND price<50000;
SELECT * FROM trn_electronics WHERE manufacturer_id IN(1,2,3);

SELECT * FROM trn_electronics WHERE manufacturer_id= (SELECT mf_id FROM trn_manufacturer where manufacturer_name = 'samsung');

SELECT * FROM trn_electronics AS e
JOIN trn_product AS p ON e.product_id=p.pd_id
JOIN trn_manufacturer AS m ON e.manufacturer_id=m.mf_id;



/************OPERATIONS ON TABLE trn_manufacturer***********/

SELECT * FROM trn_manufacturer;
TRUNCATE TABLE trn_manufacturer;
DROP TABLE trn_manufacturer;

SELECT mf_id FROM trn_manufacturer WHERE manufacturer_name = 'Samsung';
SELECT * FROM trn_manufacturer WHERE mf_id IN(3,6,5,8);
SELECT * FROM trn_manufacturer WHERE mf_id NOT IN(2,4);



/************OPERATIONS ON TABLE trn_model***********/

SELECT * FROM trn_model;
TRUNCATE TABLE trn_model;
DROP TABLE trn_model;

SELECT model_name FROM trn_model;
SELECT model_name AS mn FROM trn_model WHERE model_name = 'Galaxy Note 8';
SELECT m.mn FROM (SELECT model_name AS mn FROM trn_model) AS m WHERE m.mn = 'Galaxy Note 8';


/************OPERATIONS ON TABLE trn_product***********/

SELECT * FROM trn_product;
TRUNCATE TABLE trn_product;
DROP TABLE trn_product;



/************OPERATIONS ON TABLE trn_table_join***********/

SELECT * FROM trn_table_join;
TRUNCATE TABLE trn_table_join;
DROP TABLE trn_table_join;

SELECT * FROM trn_table_join LEFT JOIN trn_manufacturer USING (mf_id);

SELECT * FROM trn_new_table_join LEFT JOIN trn_table_join USING (manufacturer_id);

SELECT * FROM trn_new_table_join LEFT JOIN trn_table_join ON trn_new_table_join.manufacturer_id = trn_table_join.manufacturer_id;

SELECT * FROM trn_table_join UNION SELECT * FROM trn_new_table_join;

SELECT * FROM trn_table_join AS tabjoin CROSS JOIN trn_manufacturer AS manuf ON tabjoin.manufacturer_id = manuf.mf_id;

DELETE FROM trn_table_join WHERE price_Rs=2020;
SELECT DISTINCT product_name FROM trn_table_join;



/************OPERATIONS ON TABLE trn_new_table_join***********/

SELECT * FROM trn_new_table_join;
TRUNCATE TABLE trn_new_table_join;
DROP TABLE trn_new_table_join;


SELECT product_name, CONCAT(model_name,', ', price_Rs,', ', manufacturer_id) AS details FROM trn_new_table_join;