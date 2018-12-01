SELECT emp_name, emp_dob, emp_email, emp_phone, dept_code, dept_name, college_name, city, state, year_opened, rank
FROM edu_employee AS emp
LEFT JOIN edu_college_department AS cdept ON emp.cdept_id = cdept.cdept_id
LEFT JOIN edu_department AS dept ON cdept.udept_code = dept.dept_code
LEFT JOIN edu_college AS coll ON emp.college_id = coll.id
LEFT JOIN edu_designation AS desig ON emp.desig_id = desig.id
ORDER BY rank;


SELECT emp_name, emp_dob, emp_email, emp_phone, dept_code, dept_name, college_name, city, state, year_opened, rank
FROM edu_employee AS emp
LEFT JOIN edu_college_department AS cdept ON emp.cdept_id = cdept.cdept_id
LEFT JOIN edu_department AS dept ON cdept.udept_code = dept.dept_code
LEFT JOIN edu_college AS coll ON emp.college_id = coll.id
LEFT JOIN edu_designation AS desig ON emp.desig_id = desig.id
ORDER BY college_name;