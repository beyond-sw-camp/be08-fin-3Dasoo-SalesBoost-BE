-- tb_department, tb_customer는 데이터 있으므로 추가x

INSERT INTO tb_user (user_no, email, employee_id, join_date, name, password, role, dept_id, created_at, updated_at)
VALUES
    (4, 'user3@example.com', 'EMP004', '2024-04-10', '최미진', 'password123', 'USER', 4, NOW(), NOW()),
    (5, 'user4@example.com', 'EMP005', '2024-05-15', '정우진', 'password123', 'USER', 5, NOW(), NOW());

INSERT INTO tb_calendar (user_no) VALUES (1), (2), (3), (4), (5);

INSERT INTO tb_process (process_no, created_at, updated_at, description, expected_duration, is_default, process_name)
VALUES
    (1, NOW(), NOW(), '계약 체결 과정', 30, 1, '계약'),
    (2, NOW(), NOW(), '제안서 작성 과정', 20, 0, '제안서'),
    (3, NOW(), NOW(), '견적서 검토 과정', 15, 0, '견적서'),
    (4, NOW(), NOW(), '고객 협상 과정', 25, 1, '협상'),
    (5, NOW(), NOW(), '영업 전략 수립 과정', 40, 0, '영업 전략');

INSERT INTO tb_lead (created_at, updated_at, aware_path, start_date, end_date, exp_margin, exp_profit, exp_sales, name, note, process, status, sub_process, sucess_per, customer_no)
VALUES
    (NOW(), NOW(), 'DEMO', '2024-06-01', '2024-12-01', 500, 2000, 5000, '신규 거래처 확보', '확보를 위한 전략 논의', 1, 'PROGRESS', 1, 70, 1),
    (NOW(), NOW(), 'PARTNER', '2024-03-15', '2024-08-20', 800, 3000, 6000, '파트너사 신규 계약', '파트너사 계약 체결 준비', 2, 'PROGRESS', 2, 20,2),
    (NOW(), NOW(), 'EXISTING', '2024-02-10', '2024-07-15', 700, 2500, 7000, '기존 거래처 리뉴얼', '계약 리뉴얼 논의', 3, 'SUCCESS', 3, 80, 3),
    (NOW(), NOW(), 'EDUCATION', '2024-05-01', '2024-09-01', 600, 2200, 5500, '교육 프로그램 참여', '교육 프로그램 제공 논의', 4, 'HOLD', 4, 65, 4),
    (NOW(), NOW(), 'SEARCH', '2024-04-10', '2024-10-10', 900, 3200, 7500, '검색 통한 신규 고객 확보', '검색 마케팅 준비', 5, 'PROGRESS', 5, 85, 5);

INSERT INTO tb_act (no, act_cont, act_date, cls, complete_yn, end_time, name, plan_cont, purpose, start_time, calendar_no, lead_no)
VALUES
    (1, '화상 회의 플랫폼 논의', '2024-07-01', 'ONLINE', 'N', '15:00', '프로젝트 회의 준비', '신규 회의 준비', '프로젝트 논의', '13:00', 2, 1),
    (2, '거래처 방문', '2024-07-03', 'VISIT', 'Y', '17:00', '거래처 미팅', '계약 관련 회의', '상담 및 계약 체결', '15:00', 2, 2),
    (3, '전화 문의 응대', '2024-07-05', 'PHONE', 'N', '11:30', '문의사항 해결', '고객 문의 사항 응대', '고객 응대', '10:00', 2, 3),
    (4, '이메일 발송', '2024-07-07', 'EMAIL', 'Y', '14:00', '정보 이메일 발송', '상품 정보 제공', '상품 홍보 및 고객 안내', '13:00', 2, 4),
    (5, '온라인 미팅 준비', '2024-07-09', 'OTHER', 'N', '16:00', '프로젝트 미팅', '프로젝트 논의', '프로젝트 진행 상황 점검', '14:00', 2, 5);

INSERT INTO tb_plan (no, created_at, updated_at, content, end_time, personal_yn, plan_cls, plan_date, start_time, title, calendar_no)
VALUES
    (1, NOW(), NOW(), '팀원 전체 회의', '10:00', 'N', 'COMPANY', '2024-07-02', '09:00', '회의 일정', 2),
    (2, NOW(), NOW(), '신규 계약 검토', '15:00', 'Y', 'CONTRACT', '2024-07-04', '13:00', '계약 검토', 2),
    (3, NOW(), NOW(), '개인 개발 공부', '22:00', 'Y', 'PERSONAL', '2024-07-06', '20:00', '개인 학습', 2),
    (4, NOW(), NOW(), '견적서 작성', '17:00', 'N', 'ESTIMATE', '2024-07-08', '15:00', '견적서 작업', 2),
    (5, NOW(), NOW(), '영업 전략 회의', '16:00', 'N', 'SALES', '2024-07-10', '14:00', '영업 전략 논의', 2);

INSERT INTO tb_todo (todo_no, created_at, updated_at, content, due_date, priority, private_yn, status, title, todo_cls, calendar_no)
VALUES
    (1, NOW(), NOW(), '고객사 자료 준비', '2024-07-02', 'HIGH', 'N', 'TODO', '자료 준비', 'SALES', 2),
    (2, NOW(), NOW(), '팀 회의 참석', '2024-07-03', 'MEDIUM', 'N', 'INPROGRESS', '회의 참여', 'COMPANY', 2),
    (3, NOW(), NOW(), '개인 일정 조율', '2024-07-04', 'LOW', 'Y', 'DONE', '일정 조율', 'PERSONAL', 2),
    (4, NOW(), NOW(), '계약서 내용 확인', '2024-07-05', 'HIGH', 'N', 'TODO', '계약서 검토', 'CONTRACT', 2),
    (5, NOW(), NOW(), '이메일 답변 작성', '2024-07-06', 'MEDIUM', 'N', 'TODO', '이메일 답변', 'EMAIL', 2);

INSERT INTO tb_poetntial_customer (p_cust_no, created_at, updated_at, addr, cls, company, dept, email, fax, grade, modify_date, name, note, phone, position, register_date,tel)
VALUES
    (1, NOW(), NOW(), '서울시 강남구', 'B2B', 'POC 회사', '영업부', 'poc1@poc.com', '02-111-2222', 'A', NOW(), '홍길동', '잠재 고객 관리 필요', '010-8888-9999', '과장', NOW(), '02-999-8888'),
    (2, NOW(), NOW(), '서울시 서초구', 'B2C', 'POC2 회사', '개발부', 'poc2@poc.com', '02-222-3333', 'B', NOW(), '이순신', '기술 문의 진행 중', '010-7777-6666', '팀장', NOW(),'02-888-7777'),
    (3, NOW(), NOW(), '서울시 종로구', 'B2B', 'POC3 회사', '기획부', 'poc3@poc.com', '02-333-4444', 'C', NOW(), '박문수', '견적서 요청 예정', '010-5555-4444', '대리', NOW(),  '02-777-6666'),
    (4, NOW(), NOW(), '서울시 중구', 'B2C', 'POC4 회사', '인사부', 'poc4@poc.com', '02-444-5555', 'A', NOW(), '최영', '고객사 미팅 예정', '010-3333-2222', '사원', NOW(),'02-666-5555'),
    (5, NOW(), NOW(), '서울시 용산구', 'B2B', 'POC5 회사', '총무부', 'poc5@poc.com', '02-555-6666', 'S', NOW(), '정몽주', '계약 준비 중', '010-1111-0000', '부장', NOW(), '02-555-4444');

INSERT INTO tb_contact_history (contact_history_no, created_at, updated_at, cls, contact_date, content, p_cust_no, user_no)
VALUES
    (1, NOW(), NOW(), 'CALL', '2024-07-01', '고객사 문의 전화 응대', 1, 1),
    (2, NOW(), NOW(), 'EMAIL', '2024-07-02', '계약 관련 이메일 발송', 2, 2),
    (3, NOW(), NOW(), 'VISIT', '2024-07-03', '거래처 방문 상담', 3, 3),
    (4, NOW(), NOW(), 'ONLINE_MEET', '2024-07-04', '온라인 미팅 진행', 4, 4),
    (5, NOW(), NOW(), 'CHANNEL_TALK', '2024-07-05', '고객 상담 채팅', 5, 5);

INSERT INTO tb_proposal (prop_no, created_at, updated_at, cont, end_date, name, note, pr_date, req_date, start_date, submit_date, lead_no)
VALUES
    (1, NOW(), NOW(), '제품 A 제안 내용', '2024-12-31', '제품 A 제안서', '제안서 작성 필요', '2024-07-01', '2024-06-25', '2024-06-01', '2024-06-30', 1),
    (2, NOW(), NOW(), '제품 B 제안 내용', '2024-11-30', '제품 B 제안서', '기술 지원 필요', '2024-08-01', '2024-07-20', '2024-07-01', '2024-07-31', 2),
    (3, NOW(), NOW(), '제품 C 제안 내용', '2024-10-31', '제품 C 제안서', '추가 설명 요청', '2024-09-01', '2024-08-25', '2024-08-01', '2024-08-31', 3),
    (4, NOW(), NOW(), '제품 D 제안 내용', '2024-09-30', '제품 D 제안서', '가격 협상 필요', '2024-10-01', '2024-09-20', '2024-09-01', '2024-09-30', 4),
    (5, NOW(), NOW(), '제품 E 제안 내용', '2024-08-31', '제품 E 제안서', '배송 관련 협의 필요', '2024-11-01', '2024-10-25', '2024-10-01', '2024-10-31', 5);


INSERT INTO tb_estimate (est_no, created_at, updated_at, est_date, name, note, prod_cnt, supply_price, surtax_yn, tax, tax_cls, total_price, prop_no)
VALUES
    (1, NOW(), NOW(), '2024-06-01', '견적서 A', '제품 A에 대한 견적', 10, 10000, 'Y', 1000, '10%', 11000, 1),
    (2, NOW(), NOW(), '2024-07-10', '견적서 B', '제품 B에 대한 견적', 5, 20000, 'N', 0, '0%', 20000, 2),
    (3, NOW(), NOW(), '2024-08-15', '견적서 C', '제품 C에 대한 견적', 8, 15000, 'Y', 1500, '10%', 16500, 3),
    (4, NOW(), NOW(), '2024-09-05', '견적서 D', '제품 D에 대한 견적', 12, 12000, 'N', 0, '0%', 12000, 4),
    (5, NOW(), NOW(), '2024-10-20', '견적서 E', '제품 E에 대한 견적', 6, 25000, 'Y', 2500, '10%', 27500, 5);

INSERT INTO tb_contract (contract_no, created_at, updated_at, arrival_noti_day, arrival_noti_yn, cls, cont_date, end_date, exp_arrival_date, name, note, payment_terms, prod_cnt, renewal_noti_day, renewal_noti_yn, start_date, supply_price, surtax_yn, tax, tax_cls, price, warrenty, est_no)
VALUES
    (1, NOW(), NOW(), '10', 'Y', 'SALES', '2024-06-01', '2024-12-01', '2024-06-15', '신규 계약 체결', '계약 세부 내용 확인 필요', '선불', 5, '30', 'Y', '2024-06-01', 10000, 'Y', 1000, '10%', 11000, 12, 1),
    (2, NOW(), NOW(), '15', 'N', 'ESTIMATE', '2024-07-01', '2024-12-31', '2024-07-15', '견적 계약 체결', '견적 관련 확인 필요', '후불', 3, '60', 'N', '2024-07-01', 15000, 'N', 0, '0%', 15000, 6, 2),
    (3, NOW(), NOW(), '5', 'Y', 'PROPOSAL', '2024-08-01', '2025-02-28', '2024-08-15', '제안 계약 체결', '제안 세부 사항 검토 중', '선불', 10, '20', 'Y', '2024-08-01', 20000, 'Y', 2000, '10%', 22000, 24, 3),
    (4, NOW(), NOW(), '20', 'N', 'CONTRACT', '2024-09-01', '2025-03-31', '2024-09-15', '계약 연장', '계약 연장 필요 사항 확인', '후불', 8, '90', 'N', '2024-09-01', 18000, 'N', 0, '0%', 18000, 12, 4),
    (5, NOW(), NOW(), '7', 'Y', 'SALES', '2024-10-01', '2025-04-30', '2024-10-20', '영업 계약 체결', '영업 전략 검토 완료', '선불', 12, '14', 'Y', '2024-10-01', 25000, 'Y', 2500, '10%', 27500, 18, 5);

INSERT INTO tb_product (prod_no, created_at, updated_at, abbr_name, dept, eng_name, field, name, price, prod_code, quantity, release_date, supply_price, tax_rate, unit, upp_group)
VALUES
    (1, NOW(), NOW(), 'PRD001', '영업부', 'Product A', '전자기기', '제품 A', 50000, 'P001', 100, '2024-05-01', 45000, 10, '개', '전자'),
    (2, NOW(), NOW(), 'PRD002', '개발부', 'Product B', '소프트웨어', '제품 B', 30000, 'P002', 50, '2024-06-01', 27000, 8, '카피', '소프트웨어'),
    (3, NOW(), NOW(), 'PRD003', '기획부', 'Product C', '소모품', '제품 C', 10000, 'P003', 200, '2024-07-01', 9000, 5, '박스', '소모품'),
    (4, NOW(), NOW(), 'PRD004', '총무부', 'Product D', '사무용품', '제품 D', 15000, 'P004', 150, '2024-08-01', 14000, 7, '개', '사무용품'),
    (5, NOW(), NOW(), 'PRD005', '인사부', 'Product E', '의류', '제품 E', 20000, 'P005', 120, '2024-09-01', 18000, 6, '벌', '의류');

INSERT INTO tb_est_prod (est_prod_no, created_at, updated_at, discount, qty, supply_price, tax, total_amt, unit_amt, unit_prop_amt, est_no, prod_no)
VALUES
    (1, NOW(), NOW(), 5, 10, 1000, 100, 11000, 1000, 900, 1, 1),
    (2, NOW(), NOW(), 10, 5, 2000, 200, 11000, 2000, 1800, 2, 2),
    (3, NOW(), NOW(), 15, 8, 1500, 150, 13200, 1500, 1350, 3, 3),
    (4, NOW(), NOW(), 7, 12, 1200, 120, 14400, 1200, 1100, 4, 4),
    (5, NOW(), NOW(), 20, 6, 2500, 250, 16500, 2500, 2200, 5, 5);


INSERT INTO tb_sales (sales_no, created_at, updated_at, busi_type, busi_type_detail, exp_arrival_date, note, price, prod_cnt, sales_cls, sales_date, supply_price, surtax_yn, tax, tax_cls, contract_no)
VALUES
    (1, NOW(), NOW(), '도매', '대량 주문', '2024-08-15', '배송 일정 조율 필요', 60000, 10, 'SALES', '2024-07-10', 55000, 'Y', 5000, '10%', 1),
    (2, NOW(), NOW(), '소매', '소량 주문', '2024-09-01', '고객 요청 사항 반영', 32000, 5, 'SALES', '2024-07-20', 30000, 'N', 0, '0%', 2),
    (3, NOW(), NOW(), '도매', '정기 주문', '2024-09-10', '정기 배송 필요', 180000, 30, 'SALES', '2024-08-01', 170000, 'Y', 10000, '10%', 3),
    (4, NOW(), NOW(), '소매', '일반 판매', '2024-10-05', '고객 피드백 반영 필요', 45000, 3, 'SALES', '2024-08-15', 42000, 'N', 0, '0%', 4),
    (5, NOW(), NOW(), '도매', '대형 고객', '2024-11-20', '특별 가격 협상 완료', 250000, 50, 'SALES', '2024-09-01', 240000, 'Y', 10000, '10%', 5);

INSERT INTO tb_sub_process (sub_process_no, created_at, updated_at, action, expected_duration, progress_step, sub_process_name, success_rate, process_no)
VALUES
    (1, NOW(), NOW(), '고객 상담 진행', 15, '초기 상담', '상담', 80, 1),
    (2, NOW(), NOW(), '계약 협의 진행', 20, '계약 협의', '계약', 70, 2),
    (3, NOW(), NOW(), '견적서 작성 완료', 10, '견적 작성', '견적', 85, 3),
    (4, NOW(), NOW(), '프로젝트 계획 수립', 25, '계획 수립', '계획', 75, 4),
    (5, NOW(), NOW(), '최종 보고서 작성', 30, '보고서 작성', '보고서', 90, 5);

INSERT INTO tb_step (step_no, complete_date, complete_yn, level, lead_no, sub_process_no)
VALUES
    (1, '2024-07-15', 'Y', 1, 1, 1),
    (2, '2024-08-20', 'N', 2, 2, 2),
    (3, '2024-09-10', 'Y', 3, 3, 3),
    (4, '2024-10-05', 'N', 4, 4, 4),
    (5, '2024-11-01', 'Y', 5, 5, 5);

INSERT INTO tb_target_sale (target_sale_no, created_at, updated_at, month, month_target, year, product_no, user_no)
VALUES
    (1, NOW(), NOW(), 7, 500000, 2024, 1, 1),
    (2, NOW(), NOW(), 8, 300000, 2024, 2, 2),
    (3, NOW(), NOW(), 9, 200000, 2024, 3, 3),
    (4, NOW(), NOW(), 10, 450000, 2024, 4, 4),
    (5, NOW(), NOW(), 11, 350000, 2024, 5, 5);
