/************INSERTING INTO TABLE edu_university***********/

INSERT INTO edu_university(univ_code, univ_name)
VALUES
	   ('SRM', "SRM University")
    , ('DU', "University of Delhi")	
    , ('JNU', "Jawaharlal Nehru University");


/************INSERTING INTO TABLE edu_department***********/

INSERT INTO edu_department(dept_code, dept_name, univ_code)
VALUES
      ('DAME', "Automobile Engineering", 'DU')
	 , ('DCE', "Civil Engineering", 'DU')
	 , ('DCSE', "Computer Science Engineering", 'DU')
	 , ('DECE', "Electronics and Communication Engineering", 'DU')
	 , ('DEE', "Electrical Engineering", 'DU')
	 , ('DEEE', "Electrical and Electronics Engineering", 'DU')
	 , ('DEIE', "Electronics and Instrumentation Engineering", 'DU')
	 , ('DITE', "Information Technology Engineering", 'DU')
 	 , ('DME', "Mechanical Engineering", 'DU')
	 , ('JAME', "Automobile Engineering", 'JNU')
	 , ('JCE', "Civil Engineering", 'JNU')
	 , ('JCSE', "Computer Science Engineering", 'JNU')
	 , ('JECE', "Electronics and Communication Engineering", 'JNU')
	 , ('JEE', "Electrical Engineering", 'JNU')
	 , ('JEEE', "Electrical and Electronics Engineering", 'JNU')
	 , ('JEIE', "Electronics and Instrumentation Engineering", 'JNU')
	 , ('JITE', "Information Technology Engineering", 'JNU')
	 , ('JME', "Mechanical Engineering", 'JNU')
	 , ('SAME', "Automobile Engineering", 'SRM')
	 , ('SCE', "Civil Engineering", 'SRM')
	 , ('SCSE', "Computer Science Engineering", 'SRM')
	 , ('SECE', "Electronics and Communication Engineering", 'SRM')
	 , ('SEEE', "Electrical and Electronics Engineering", 'SRM')
	 , ('SEIE', "Electronics and Instrumentation Engineering", 'SRM')
	 , ('SITE', "Information Technology Engineering", 'SRM')
	 , ('SME', "Mechanical Engineering", 'SRM');


/************INSERTING INTO TABLE edu_college***********/

INSERT INTO edu_college(id, college_code, college_name, univ_code, city, state, year_opened)
VALUES
	   (1001, 'SNCR', "SRM NCR Campus", 'SRM', "Modinagar", "Uttar Pradesh", 1997)
	 , (1002, 'SEEC', "Easwari Engineering College", 'SRM', "Chennai", "Tamil Nadu", 1996)
	 , (1003, 'SVEC', "Valliammai Engineering College", 'SRM', "Kanchipuram", "Tamil Nadu", 1999)
	 , (1101, 'DLSR', "Lady Shri Ram College For Women", 'DU', "New Delhi", "Delhi", 1956)
	 , (1102, 'DMH', "Miranda House", 'DU', "Delhi", "Delhi", 1948)
	 , (1103, 'DRC', "Rajdhani College", 'DU', "New Delhi", "Delhi", 1964)
	 , (1111, 'JCME', "College of Military Engineering", 'JNU', "Pune", "Maharashtra", 1943)
	 , (1112, 'JINA', "Indian Naval Academy", 'JNU', "Kannur", "Kerala", 1969)
	 , (1113, 'JVIT', "Vemu Institute of Technology", 'JNU', "Chittoor", "Andhra Pradesh", 2008);


/************INSERTING INTO TABLE edu_college_department***********/

INSERT INTO edu_college_department(cdept_id, udept_code, college_id)
VALUES
	   (111, 'SEEE', 1001)
	 , (112, 'SECE', 1001)
	 , (113, 'SME', 1001)
	 , (114, 'SCE', 1001)
	 , (115, 'SAME', 1001)
	 , (116, 'SCSE', 1001)
	 , (117, 'SITE', 1001)
	 , (121, 'SEEE', 1002)
	 , (122, 'SECE', 1002)
	 , (123, 'SEIE', 1002)
	 , (124, 'SME', 1002)
	 , (125, 'SCE', 1002)
	 , (126, 'SAME', 1002)
	 , (127, 'SCSE', 1002)
	 , (128, 'SITE', 1002)
	 , (131, 'SEEE', 1003)
	 , (132, 'SECE', 1003)
	 , (133, 'SME', 1003)
	 , (134, 'SCE', 1003)
	 , (135, 'SCSE', 1003)
	 , (211, 'DEEE', 1101)
	 , (212, 'DECE', 1101)
	 , (213, 'DCE', 1101)
	 , (214, 'DCSE', 1101)
	 , (215, 'DITE', 1101)
	 , (221, 'DEE', 1102)
	 , (222, 'DECE', 1102)
	 , (223, 'DEIE', 1102)
	 , (224, 'DME', 1102)
	 , (225, 'DCE', 1102)
	 , (226, 'DCSE', 1102)
	 , (227, 'DITE', 1102)
	 , (231, 'DEE', 1103)
	 , (232, 'DEEE', 1103)
	 , (233, 'DECE', 1103)
	 , (234, 'DEIE', 1103)
	 , (235, 'DME', 1103)
	 , (236, 'DCE', 1103)
	 , (237, 'DCSE', 1103)
	 , (311, 'JEE', 1111)
	 , (312, 'JME', 1111)
	 , (313, 'JCE', 1111)
	 , (321, 'JECE', 1112)
	 , (322, 'JME', 1112)
	 , (331, 'JEEE', 1113)
	 , (332, 'JECE', 1113)
	 , (333, 'JME', 1113)
	 , (334, 'JCE', 1113)
	 , (335, 'JCSE', 1113);
	 
	 

/************INSERTING INTO TABLE edu_designation***********/

INSERT INTO edu_designation(desig_name, rank)
VALUES
	  ("HOD", 1)
	, ("Professor", 2)
	, ("Assistant professor", 3)
	, ("Lab Assistant", 4);
	
	
/************INSERTING INTO TABLE edu_employee***********/

INSERT INTO edu_employee(emp_name, emp_dob, emp_email, emp_phone, college_id, cdept_id, desig_id)
VALUES
	   ("Dr.Prof. V.S.Gupta", 19720505, "vsgupta@gmail.com", 9876543210, 1001, 111, 1)
	 , ("Prof. G.S.Chaurasia", 19750312, "gschaurasia@gmail.com", 7854961345, 1001, 111, 2)
	 , ("Dr. Himani Dem",19900214, "himanidem@gmail.com", 8824524654, 1001, 112, 3)
	 , ("Mr. Pradeep Kumar", 19880425, "pradeepkumar@gmail.com", 9866541235, 1001, 116, 4)
	 , ("Prof. S.K.Bharadwaj", 19730820, "skbharadwaj@gmail.com", 7782311560, 1002, 123, 1)
	 , ("Prof. D.S.Srikant", 19781115, "dssrikant@gmail.com", 6215588349, 1002, 128, 2)
	 , ("Dr. Preeti Pannu", 19910110, "preetipannu@gmail.com", 9878987756, 1002, 122, 3)
	 , ("Mr. Vinoth", 19881204, "vinoth@gmail.com", 8799615468, 1002, 121, 4)
	 , ("Prof. James Heller", 19750623, "jamesheller@gmail.com", 9778282350, 1003, 131, 1)
	 , ("Prof. G.K.Dubey", 19790917, "gkdubey@gmail.com", 9988776655, 1003, 133, 2)
	 , ("Mr. Shivam Goyal", 19880530, "shivamgoyal@gmail.com", 6874531295, 1003, 133, 3)
	 , ("Mr. S.P.Sharma", 19850711, "spsharma@gmail.com", 7865491359, 1003, 134, 4)
	 , ("Prof. H.N.Verma", 19681021, "hnverma@gmail.com", 7889402356, 1101, 212, 1)
	 , ("Prof. J.P.Chaudhary", 19700305, "jpchaudhary@gmail.com", 6985472013, 1101, 214, 2)
	 , ("Mr. Amit Kumar", 19820909, "amitkumar@gmail.com", 7989502467, 1101, 213, 3)
	 , ("Mr. Suresh Jha", 19900125, "sureshjha@gmail.com", 8972560143, 1101, 211, 4)
	 , ("Prof. P.M.Bala", 19760818, "pmbala@gmail.com", 8282641039, 1102, 221, 1)
	 , ("Prof. S.N.Pandey", 19790413, "snpandey@gmail.com", 9468751065, 1102, 226, 2)
	 , ("Mr. Bipin Kumar", 19870528, "bipinkumar@gmail.com", 9756413254, 1102, 224, 3)
	 , ("Mr. K.C.Aggarwal", 19900902, "kcaggarwal@gmail.com", 6023879453, 1102, 227, 4)
	 , ("Prof. M.A.Ansari", 19790602, "maansari@gmail.com", 7737377709, 1103, 232, 1)
	 , ("Prof. M.Mohan", 19811226, "mmohan@gmail.com", 6847956123, 1103, 233, 2)
	 , ("Dr. Mohan Raj", 19830531, "mohanraj@gmail.com", 8796542013, 1103, 236, 3)
	 , ("Mr. Ankit Gehlot", 19870628, "ankitgehlot@gmail.com", 7998603422, 1103, 234, 4)
	 , ("Prof. Anand Pandey", 19751124, "anandpandey@gmail.com", 9293568015, 1111, 313, 1)
	 , ("Prof. D.K.Meena", 19770113, "dkmeena@gmail.com", 8832105649, 1111, 312, 2)
	 , ("Dr. Shikha Bansal", 19910601, "shikhabansal@gmail.com", 7737085288, 1111, 311, 3)
	 , ("Mr. Manoj Pnadey", 19890206, "manojpandey@gmail.com", 9416782255, 1112, 322, 1)
	 , ("Prof. Kumkum Bharadwaj", 19780709, "kumkumbharadwaj@gmail.com", 9484720516, 1112, 321, 2)
	 , ("Dr. Sneha Gupta", 19891027, "snehagupta@gmail.com", 7689856431, 1113, 335, 1)
	 , ("Dr. Faizal Khan", 19850919, "faizalkhan@gmail.com", 8640255589, 1113, 332, 2)
	 , ("Mr. Adil Ansari", 19880517, "adilansari@gmail.com", 6647822359, 1113, 334, 3)
	 , ("Ms. Hansa Chaudhary", 19921111, "hansachaudhary@gmail.com", 9975483302, 1113, 331, 4);


/************INSERTING INTO TABLE edu_syllabus***********/

INSERT INTO edu_syllabus(cdept_id, syllabus_code, syllabus_name)
VALUES
	   (111, 'EE11', "Transmission and Distribution")
	 , (121, 'EE12', "Microprocessors and Microcontrollers")
	 , (131, 'EE13', "Analysis of Electrical Circuits")
	 , (112, 'EC11', "Electronic Devices and Circuits")
	 , (122, 'EC12', "Digital Signal Processor")
	 , (132, 'EC13', "Control Systems")
	 , (123, 'EI11', "Instrumentation and Control")
	 , (123, 'EI12', "Signalling and Measurement")
	 , (113, 'ME11', "Thermodynamics")
	 , (124, 'ME12', "Fluid Mechanics")
	 , (133, 'ME13', "Engineering Drawing")
	 , (114, 'CE11', "Basic Civil Engineering")
	 , (125, 'CE12', "Structural Designing")
	 , (134, 'CE13', "Concrete")
	 , (115, 'AM11', "Strength Of Material")
	 , (126, 'AM12', "Engines")
	 , (116, 'CS11', "Database Management System")
	 , (127, 'CS12', "JAVA Programming")
	 , (135, 'CS13', "Machine Learning")
	 , (117, 'IT11', "Networking")
	 , (128, 'IT12', "HTML Programming")
	 , (221, 'E201', "Electrical Machines")
	 , (231, 'E202', "Power System")
	 , (211, 'EE21', "Transmission and Distribution")
	 , (211, 'EE22', "Microprocessors and Microcontrollers")
	 , (232, 'EE23', "Analysis of Electrical Circuits")
	 , (212, 'EC21', "Electronic Devices and Circuits")
	 , (222, 'EC22', "Digital Signal Processor")
	 , (233, 'EC23', "Control Systems")
	 , (223, 'EI21', "Instrumentation and Control")
	 , (234, 'EI22', "Signalling and Measurement")
	 , (224, 'ME21', "Thermodynamics")
	 , (235, 'ME22', "Fluid Mechanics")
	 , (235, 'ME23', "Engineering Drawing")
	 , (213, 'CE21', "Basic Civil Engineering")
	 , (225, 'CE22', "Structural Designing")
	 , (236, 'CE23', "Concrete")
	 , (214, 'CS21', "Database Management System")
	 , (226, 'CS22', "JAVA Programming")
	 , (237, 'CS23', "Machine Learning")
	 , (215, 'IT21', "Networking")
	 , (227, 'IT22', "HTML Programming")
	 , (311, 'E301', "Electrical Machines")
	 , (311, 'E302', "Power System")
	 , (331, 'EE31', "Transmission and Distribution")
	 , (331, 'EE32', "Microprocessors and Microcontrollers")
	 , (331, 'EE33', "Analysis of Electrical Circuits")
	 , (321, 'EC31', "Electronic Devices and Circuits")
	 , (321, 'EC32', "Digital Signal Processor")
	 , (332, 'EC33', "Control Systems")
	 , (312, 'ME31', "Thermodynamics")
	 , (322, 'ME32', "Fluid Mechanics")
	 , (333, 'ME33', "Engineering Drawing")
	 , (313, 'CE31', "Basic Civil Engineering")
	 , (313, 'CE32', "Structural Designing")
	 , (334, 'CE33', "Concrete")
	 , (335, 'CS31', "Database Management System")
	 , (335, 'CS32', "JAVA Programming")
	 , (335, 'CS33', "Machine Learning");
	 
	 
/************INSERTING INTO TABLE edu_professor_syllabus***********/	 

INSERT INTO edu_professor_syllabus(emp_id, syllabus_id, semester)
VALUES
	   (1, 1, 3)
	 , (5, 7, 2)
	 , (6, 21, 4)
	 , (9, 3, 2)
	 , (10, 11, 1)
	 , (13, 27, 3)
	 , (14, 38, 5)
	 , (17, 22, 4)
	 , (18, 39, 6)
	 , (21, 26, 2)
	 , (22, 29, 7)
	 , (25, 54, 1)
	 , (26, 51, 5)
	 , (28, 52, 7)
	 , (29, 49, 6)
	 , (30, 59, 8)
	 , (31, 50, 8);
	 
	 
/************INSERTING INTO TABLE edu_student***********/	

INSERT INTO edu_student(roll_number, stud_name, stud_dob, gender, stud_email, stud_phone, address, academic_year, cdept_id, college_id)
VALUES
	   ('NCR111', "Mohammed Almas", 19970602, 'M', "mohammedalmas@gmail.com", 7737377708, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 111, 1001)
	 , ('NCR112', "Smriti Prasad", 19970812, 'F', "smritiprasad@gmail.com", 7759845321, "D106, Sunrise Apartments, Mayur Vihar, Delhi", 2018, 116, 1001)
	 , ('NCR113', "Sautik Vats", 19960502, 'M', "sautikvats@gmail.com", 8956231475, "F-56, Ganesh Nagar, Lucknow, U.P", 2017, 114, 1001)
	 , ('NCR114', "Soumya Bharadwaj", 19951203, 'F', "soumyabharadwaj@gmail.com", 9987456023, "T-3/10C, AnuKiran Colony, Rawatbhata, Rajasthan", 2014, 112, 1001)
	 , ('NCR115', "Kritika Sharma", 19950812, 'F', "kritikasharma@gmail.com", 6987451023, "G89, Ajnara Flats, Sawai Madhopur, Rajasthan", 2015, 115, 1001)
	 , ('EEC121', "Vivek Gupta", 19960722, 'M', "vivekgupta@gmail.com", 8889745621, "22, Kohlam Street,Preet Vihar,Delhi", 2018, 123, 1002)
	 , ('EEC122', "Bhavya Saxena", 19960901, 'F', "bhavyasaxena@gmail.com", 9745681235, "N34, Ramprasth Greens, Ghaziabad, U.P", 2015, 124, 1002)
	 , ('EEC123', "Kuldeep Chaudhary", 19960310, 'M', "kuldeepchaudhary@gmail.com", 7756482105, "106D, Pathan Nagar, Bhopal, M.P", 2014, 127, 1002)
	 , ('EEC124', "Siddhant Sanjay", 19950514, 'M', "sidshantsanjay@gmail.com", 9898756412, "T-4, Pratap Nagar, Jaipur, Rajasthan", 2016, 128, 1002)
	 , ('SEEC125', "Parag Bhargava", 19960115, 'M', "paragbhargava@gmail.com", 8324867954, "12/24, Karol Bagh, Delhi", 2016, 122, 1002)
	 , ('SVEC131', "Shreya Talukdar", 19961116, 'F', "shreyatalukdar@gmail.com", 9635874025, "T-2/36A, Anukiran Colony, Rawatbhata, Rajasthan", 2018, 131, 1003)
	 , ('SVEC132', "Shubham Sharma", 19950315, 'M', "shubhamsharma@gmail.com", 9475186329, "101, Street No.4, Karkarduma, Delhi", 2017, 132, 1003)
	 , ('SVEC133', "Faizan Khan", 19960612, 'M', "faizankhan@gmail.com", 7842515896, "R45, Pind Nagar, Ludhiana, Punjab", 2017, 133, 1003)
	 , ('SVEC134', "Anand Tiwari", 19950807, 'M', "anandtiwari@gmail.com", 9997216048, "G122, Sector 4, Vaishali, Rajasthan", 2014, 134, 1003)
	 , ('SVEC135', "Basit Shakeel Bhat", 19950221, 'M', "basitshakeelbhat@gmail.com", 8674950321, "66, Sector 18, Noida, U.P", 2015, 135, 1003)
	 , ('DLSR211', "Umang Saxena", 19960124, 'M', "umangsaxena@gmail.com", 9658741236, "TR115, Ambience Colony, Gurgaon, Haryana", 2017, 211, 1101)
	 , ('DLSR212', "Aditya Kaushik", 19950907, 'M', "adityakaushik@gmail.com", 9876543201, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2017, 212, 1101)
	 , ('DLSR213', "Sanchay Bajpai", 19950318, 'M', "sanchaybajpai@gmail.com", 7742365890, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2017, 213, 1101)
	 , ('DLSR214', "Dhruv Sharma", 19960126, 'M', "dhruvsharma@gmail.com", 6479958230, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 214, 1101)
	 , ('DLSR215', "Yashasvi Tomar", 19970605, 'F', "yashasvitomar@gmail.com", 7846259130, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 215, 1101)
	 , ('DMH221', "Avi Kaushik", 19951018, 'M', "avikaushik@gmail.com", 9982235597, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 223, 1102)
	 , ('DMH222', "Gaurav Mathur", 19971108, 'M', "gauravmathur@gmail.com", 9874612537, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 224, 1102)
	 , ('DMH223', "Praharsh Mukund", 19950501, 'M', "praharshmukund@gmail.com", 6897451320,	"T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2019, 225, 1102)
	 , ('DMH224', "Shubham Agrawal", 19970427, 'M', "shubhamagrawal@gmail.com", 9632587410,	"T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2019, 221, 1102)
	 , ('DMH225', "Utsav Kothari", 19960822, 'M', "utsavkothari@gmail.com", 7539514862, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2020, 227, 1102)
	 , ('DRC231', "Amit Rai", 19961220, 'M', "amitrai@gmail.com", 7878685401, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2021, 231, 1103)
	 , ('DRC232', "Suraj Sharma", 19961230, 'M', "surajsharma@gmail.com", 6899543105, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2022, 234, 1103)
	 , ('DRC233', "Ravi Bhushan", 19960619, 'M', "ravibhushan@gmail.com", 7795402598, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2016, 235, 1103)
	 , ('DRC234', "Avinash Singh", 19960208, 'M', "avinashsingh@gmail.com", 8895641023, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2017, 236, 1103)
	 , ('DRC235', "Siddhant Goyal", 19950712, 'M', "siddhantgoyal@gmail.com", 9414942525, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2019, 237, 1103)
	 , ('JCME311', "Alok Kumar Singh", 19950915, 'M', "alokkumarsingh@gmail.com", 6987183025, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2019, 311, 1111)
	 , ('JCME312', "Ashwani Krishna", 19970603, 'F', "ashwanikrishna@gmail.com", 9943185720, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2020, 312, 1111)
	 , ('JCME313', "Farheen Ahmed", 19960329, 'F', "farheenahmed@gmail.com", 9974520133, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2015, 313, 1111)
	 , ('JINA321', "Vishal Tiwari", 19950517, 'M', "vishaltiwari@gmail.com", 9413358624, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2017, 321, 1112)
	 , ('JINA322', "Sudeep Ranjan", 19960104, 'M', "sudeepranjan@gmail.com", 8751023647, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2015, 322, 1112) 
	 , ('JVIT331', "Shubhangi Tarun", 19961126, 'F', "shubhangitarun@gmail.com", 9898784652, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2014, 331, 1113)
	 , ('JVIT332', "Adil Yusuf", 19960913, 'M', "adilyusuf@gmail.com", 9486916203, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 332, 1113)
	 , ('JVIT333', "Nikhil Srivastav", 19950817, 'M', "nikhilsrivastav@gmail.com", 9556842013, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2019, 333, 1113)
	 , ('JVIT334', "Nalin Sharma", 19960919, 'M', "nalinsharma@gmail.com", 7845269013, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2017, 334, 1113)
	 , ('JVIT335', "Anirudh Tiwari", 19960402, 'M', "anirudhtiwari@gmail.com", 7768234405, "T-4/106D, Anupratap Colony, Rawatbhata, Rajasthan", 2018, 335, 1113);
	 
	 
/************INSERTING INTO TABLE edu_semester_result***********/

INSERT INTO edu_semester_result(stud_id, syllabus_id, semester, grade, credits, result_date)
VALUES
	   (1, 1, 1, 'S', 9.2, 20141215)
	 , (2, 17, 3, 'A', 8.8, 20151220)
	 , (5, 15, 3, 'B', 7, 20121224)
	 , (20, 41, 4, 'A', 8, 20160612)	 
	 , (24, 22, 1, 'B', 7.6, 20151221)
	 , (30,	40, 2, 'C', 6.5, 20160615)
 	 , (31,	44, 1, 'C', 6, 20151218)
	 , (33,	55, 5, 'E', 4, 20130105)
	 , (37,	50, 4, 'D', 5.2, 20160620);
	 

	 
	 
/************INSERTING INTO TABLE edu_semester_fee***********/

INSERT INTO edu_semester_fee(cdept_id, stud_id, semester, amount, paid_year, paid_status)
VALUES
	  (111, 1, 2, 165000, 2015,"PAID")
	, (116, 2, 4, 165000, 2016,"PAID")
	, (115, 5, 4, 165000, 2013,"PAID")
	, (215, 20, 5, 120000, 2016,"UNPAID")
	, (221, 24, 1, 120000, 2015,"UNPAID")
	, (237, 30, 3, 120000, 2016,"UNPAID")
	, (311, 31, 2, 110000, 2016,"PAID")
	, (313, 33, 6, 110000, 2014,"PAID")
	, (332, 37, 5, 110000,2016 ,"UNPAID");	