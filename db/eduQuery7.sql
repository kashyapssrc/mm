/*Single Update*/
UPDATE edu_semester_fee SET paid_status = "PAID", paid_year = 2017 WHERE stud_id = 20;

/*Bulk Update*/
UPDATE edu_semester_fee SET paid_status = "PAID", paid_year = 2017 WHERE stud_id IN (24, 30, 37);