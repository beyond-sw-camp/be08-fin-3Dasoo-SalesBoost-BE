INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 1,'001','영업부','dept_sales',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 1);

INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 2,'002','개발부','dept_coding',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 2);

INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 3,'003','인사부','dept_people',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 3);

# INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
# VALUES (1,'001','영업부','dept_sales',now(),now());