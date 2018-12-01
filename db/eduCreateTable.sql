/************CREATING TABLE edu_university***********/

CREATE TABLE edu_university(
							 
							      univ_code CHAR(4) PRIMARY KEY
							    , univ_name VARCHAR(100) NOT NULL
								
								);
								


/************CREATING TABLE edu_department***********/
								
CREATE TABLE edu_department(
									
									dept_code CHAR(4) PRIMARY KEY
								 , dept_name VARCHAR(50) NOT NULL
								 
								 , univ_code CHAR(4)
								 , KEY univ_code_key (univ_code)
								 , CONSTRAINT fk_department_univ_code FOREIGN KEY (univ_code) REFERENCES edu_university(univ_code) ON DELETE NO ACTION ON UPDATE NO ACTION 
									
								);
								

/************CREATING TABLE edu_college***********/
				

CREATE TABLE edu_college(
							   
							   id INT PRIMARY KEY
							 , college_code CHAR(4) NOT NULL 
							 , college_name VARCHAR(100) NOT NULL
							 
							 , univ_code CHAR(4)
							 , KEY univ_code_key(univ_code)
							 , CONSTRAINT fk_college_univ_code FOREIGN KEY(univ_code) REFERENCES edu_university(univ_code) ON DELETE NO ACTION ON UPDATE NO ACTION 
							 
							 , city VARCHAR(50) NOT NULL
							 , state VARCHAR(50) NOT NULL
							 , year_opened YEAR(4) NOT NULL 
							 
							 );
							 


/************CREATING TABLE edu_college_department***********/

CREATE TABLE edu_college_department(
        									 
											 cdept_id INT PRIMARY KEY
										  
										  , udept_code CHAR(4)
										  , KEY udept_code_key(udept_code)
							 			  , CONSTRAINT fk_cd_udept_code FOREIGN KEY(udept_code) REFERENCES edu_department(dept_code) ON DELETE NO ACTION ON UPDATE NO ACTION
							 			  
										  , college_id INT
										  , KEY college_id_key(college_id)
							 			  , CONSTRAINT fk_cd_college_id FOREIGN KEY(college_id) REFERENCES edu_college(id) ON DELETE NO ACTION ON UPDATE NO ACTION	
										
										);
										
										
										
/************CREATING TABLE edu_designation***********/

CREATE TABLE edu_designation(
									
									 id INT AUTO_INCREMENT PRIMARY KEY
								  , desig_name VARCHAR(30) NOT NULL
								  , rank CHAR(1) NOT NULL

								 );
								 
								 
								 
/************CREATING TABLE edu_employee***********/
								 
CREATE TABLE edu_employee(

								id INT AUTO_INCREMENT PRIMARY KEY
							 , emp_name VARCHAR(100) NOT NULL
							 , emp_dob DATE NOT NULL
							 , emp_email VARCHAR(50) NOT NULL
							 , emp_phone BIGINT NOT NULL
							 
							 , college_id INT
							 , KEY college_id_key(college_id)
  			 			    , CONSTRAINT fk_employee_college_id FOREIGN KEY(college_id) REFERENCES edu_college(id) ON DELETE NO ACTION ON UPDATE NO ACTION	
								
							 , cdept_id INT
							 , KEY cdept_id_key(cdept_id)
  			 			    , CONSTRAINT fk_employee_cdept_id FOREIGN KEY(cdept_id) REFERENCES edu_college_department(cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION	
							 
							 , desig_id INT
							 , KEY desig_id_key(desig_id)
  			 			    , CONSTRAINT fk_employee_desig_id FOREIGN KEY(desig_id) REFERENCES edu_designation(id) ON DELETE NO ACTION ON UPDATE NO ACTION
							 
							 );


							 
/************CREATING TABLE edu_syllabus***********/
							 
CREATE TABLE edu_syllabus(

								  id INT AUTO_INCREMENT PRIMARY KEY
								
								, cdept_id INT
								, KEY cdept_id_key(cdept_id)
  			 			   	, CONSTRAINT fk_syllabus_cdept_id FOREIGN KEY(cdept_id) REFERENCES edu_college_department(cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION	  
								
								, syllabus_code CHAR(4) NOT NULL
								, syllabus_name VARCHAR(100) NOT NULL
								
							);
							 
							 

/************CREATING TABLE edu_professor_syllabus***********/

CREATE TABLE edu_professor_syllabus(
												
									   	  emp_id INT
							 				, KEY emp_id_key(emp_id)
  			 			    				, CONSTRAINT fk_pfs_emp_id FOREIGN KEY(emp_id) REFERENCES edu_employee(id) ON DELETE NO ACTION ON UPDATE NO ACTION
											
											, syllabus_id INT
							 				, KEY syllabus_id_key(syllabus_id)
  			 			    				, CONSTRAINT fk_syllabus_id FOREIGN KEY(syllabus_id) REFERENCES edu_syllabus(id) ON DELETE NO ACTION ON UPDATE NO ACTION	
											
											, semester TINYINT NOT NULL
											
											);



/************CREATING TABLE edu_student***********/

CREATE TABLE edu_student(
								
								id INT AUTO_INCREMENT PRIMARY KEY
							 , roll_number CHAR(8) NOT NULL
							 , stud_name VARCHAR(100) NOT NULL
							 , stud_dob DATE NOT NULL
							 , gender CHAR(1) NOT NULL
							 , stud_email VARCHAR(50) NOT NULL
							 , stud_phone BIGINT NOT NULL
							 , address VARCHAR(200) NOT NULL
							 , academic_year YEAR(4) NOT NULL
							 
							 , cdept_id INT
							 , KEY cdept_id_key(cdept_id)
  			 			    , CONSTRAINT fk_student_cdept_id FOREIGN KEY(cdept_id) REFERENCES edu_college_department(cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION	
							 
							 , college_id INT
							 , KEY college_id_key(college_id)
  			 			    , CONSTRAINT fk_student_college_id FOREIGN KEY(college_id) REFERENCES edu_college(id) ON DELETE NO ACTION ON UPDATE NO ACTION	
								
							);
							


/************CREATING TABLE edu_semester_result***********/
							
CREATE TABLE edu_semester_result(
										
										  stud_id INT
							 			, KEY stud_id_key(stud_id)
  			 			    			, CONSTRAINT fk_sr_stud_id FOREIGN KEY(stud_id) REFERENCES edu_student(id) ON DELETE NO ACTION ON UPDATE NO ACTION	
										
										, syllabus_id INT
						 				, KEY syllabus_id_key(syllabus_id)
		 			    				, CONSTRAINT fk_sr_syllabus_id FOREIGN KEY(syllabus_id) REFERENCES edu_syllabus(id) ON DELETE NO ACTION ON UPDATE NO ACTION	 
										
										, semester TINYINT NOT NULL
										, grade VARCHAR(2) NOT NULL
										, credits FLOAT NOT NULL
										, result_date DATE NOT NULL
										
									 );



/************CREATING TABLE edu_semester_fee***********/
									 
CREATE TABLE edu_semester_fee(
									   stud_id INT
							 		 , KEY stud_id_key(stud_id)
  			 			    		 , CONSTRAINT fk_sf_stud_id FOREIGN KEY(stud_id) REFERENCES edu_student(id) ON DELETE NO ACTION ON UPDATE NO ACTION	
										
									 , cdept_id INT
							 	    , KEY cdept_id_key(cdept_id)
  			 			    		 , CONSTRAINT fk_sf_cdept_id FOREIGN KEY(cdept_id) REFERENCES edu_college_department(cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION	
							 
									 , semester TINYINT NOT NULL
									 , amount DOUBLE(18,2) NULL
									 , paid_year YEAR(4) NULL
									 , paid_status VARCHAR(10) NOT NULL
									  			
									);									 