INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
SELECT 1,'001','영업부','dept_sales',now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_department WHERE dept_no = 1);

# INSERT INTO `tb_department` (dept_no, dept_code,dept_name,eng_name,created_at,updated_at)
# VALUES (1,'001','영업부','dept_sales',now(),now());