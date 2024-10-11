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


INSERT INTO `tb_customer` (customer_no,name,company,dept,position,email,phone,tel,grade,is_keyman,user_no,created_at,updated_at)
SELECT 1, '김은경', '네이버','개발1팀','과장','abc@naver.com','01012348888','0313445999','S',true,1,now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_customer WHERE customer_no=1);

INSERT INTO `tb_customer` (customer_no,name,company,dept,position,email,phone,tel,grade,is_keyman,user_no,created_at,updated_at)
SELECT 2, '이유진', '삼성','기획조정부','사원','samsung@gmail.com','01011112222','02888999','B',false,1,now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_customer WHERE customer_no=2);

INSERT INTO `tb_customer` (customer_no,name,company,dept,position,email,phone,tel,grade,is_keyman,user_no,created_at,updated_at)
SELECT 3, '정수진', 'LG','인사팀','대리','lg@gmail.com','01011112222','02888999','A',false,1,now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_customer WHERE customer_no=3);

INSERT INTO `tb_customer` (customer_no,name,company,dept,position,email,phone,tel,grade,is_keyman,user_no,created_at,updated_at)
SELECT 4, '이지정', '카카오','서버운영팀','사원','kakao@gmail.com','01011112222','02888999','A',false,1,now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_customer WHERE customer_no=4);

INSERT INTO `tb_customer` (customer_no,name,company,dept,position,email,phone,tel,grade,is_keyman,user_no,created_at,updated_at)
SELECT 5, '최규범', '샘표','인사과','사원','sampho@gmail.com','01011112222','02888999','A',false,1,now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_customer WHERE customer_no=5);

INSERT INTO `tb_customer` (customer_no,name,company,dept,position,email,phone,tel,grade,is_keyman,user_no,created_at,updated_at)
SELECT 6, '장현준', 'NC','개발팀','과장','nc@gmail.com','01011112222','02888999','A',false,1,now(),now()
WHERE NOT EXISTS (SELECT 1 FROM tb_customer WHERE customer_no=5);


