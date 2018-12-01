SELECT roll_number, stud_name, gender, stud_dob, stud_email, stud_phone, address, college_name, dept_name, semester, credits
FROM edu_student AS stud
LEFT JOIN edu_college AS coll ON stud.college_id = coll.id
LEFT JOIN edu_college_department AS cdept ON stud.cdept_id = cdept.cdept_id
LEFT JOIN edu_department AS dept ON cdept.udept_code = dept.dept_code
LEFT JOIN edu_semester_result AS semres ON stud.id = semres.stud_id
WHERE credits > 8;


SELECT roll_number, stud_name, gender, stud_dob, stud_email, stud_phone, address, college_name, dept_name, semester, credits
FROM edu_student AS stud
LEFT JOIN edu_college AS coll ON stud.college_id = coll.id
LEFT JOIN edu_college_department AS cdept ON stud.cdept_id = cdept.cdept_id
LEFT JOIN edu_department AS dept ON cdept.udept_code = dept.dept_code
LEFT JOIN edu_semester_result AS semres ON stud.id = semres.stud_id
WHERE credits > 5;