INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 1,'001','영업부','dept_sales',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 1);

INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 2,'002','개발부','dept_coding',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 2);

INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 3,'003','인사부','dept_people',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 3);

INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 4,'004','총무부','dept_generalAffairs',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 4);

INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 5,'005','기획부','dept_plan',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 5);


INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 6,'006','전산,IT부','dept_IT',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 6);

INSERT INTO `tb_user` (user_no,name,email,password,employee_id,dept_id,role,created_at,updated_at,join_date)
SELECT 1,'테스트','test@naver.com','abcd','2024101101','001','USER',now(),now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_user WHERE email = 'test@naver.com');
