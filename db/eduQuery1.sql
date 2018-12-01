SELECT college_code, college_name, univ_name, city, state, year_opened, dept_name, emp_name AS hod_name
FROM edu_university AS univ 
LEFT JOIN edu_department AS dept ON univ.univ_code = dept.univ_code 
LEFT JOIN edu_college_department AS cdept ON dept.dept_code = cdept.udept_code
LEFT JOIN edu_college AS coll ON cdept.college_id = coll.id 
LEFT JOIN edu_employee AS emp ON cdept.cdept_id = emp.cdept_id  
WHERE dept_name IN ("Computer Science Engineering", "Information Technology Engineering") AND desig_id = 1;
