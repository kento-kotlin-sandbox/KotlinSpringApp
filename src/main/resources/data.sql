/* 従業員テーブルのデータ */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

/* ユーザマスタのデータ（管理者権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('kento75@xxx.co.jp', 'password', 'タカノケント', '1993-07-05', 25, false, 'ROLE_ADMIN');

/* ユーザマスタのデータ（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('test@xxx.co.jp', 'password', 'テストタロウ', '1995-04-02', 23, false, 'ROLE_GENERAL');
