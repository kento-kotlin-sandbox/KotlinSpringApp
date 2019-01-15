/* 従業員テーブルのデータ */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

/* ユーザマスタのデータ（管理者権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('kento75@xxx.co.jp', 'password', 'タカノケント', '1993-07-05', 25, false, 'ROLE_ADMIN');
