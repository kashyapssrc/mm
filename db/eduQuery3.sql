SELECT roll_number, stud_name, gender, stud_dob, stud_email, stud_phone, address, college_name, dept_name, emp_name AS hod_name
FROM edu_student AS stud
LEFT JOIN edu_college AS coll ON stud.college_id = coll.id
LEFT JOIN edu_college_department AS cdept ON stud.cdept_id = cdept.cdept_id
LEFT JOIN edu_department AS dept ON cdept.udept_code = dept.dept_code
LEFT JOIN edu_employee AS emp ON cdept.cdept_id = emp.cdept_id
LIMIT 0,20;

SELECT roll_number, stud_name, gender, stud_dob, stud_email, stud_phone, address, college_name, dept_name, emp_name AS hod_name
FROM edu_student AS stud
LEFT JOIN edu_college AS coll ON stud.college_id = coll.id
LEFT JOIN edu_college_department AS cdept ON stud.cdept_id = cdept.cdept_id
LEFT JOIN edu_department AS dept ON cdept.udept_code = dept.dept_code
LEFT JOIN edu_employee AS emp ON cdept.cdept_id = emp.cdept_id
LIMIT 20,20;