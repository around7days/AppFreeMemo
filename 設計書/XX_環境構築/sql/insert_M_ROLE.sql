DELETE FROM M_ROLE;
INSERT INTO M_ROLE VALUES ('ROLE_APPLY',    '申請者', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE VALUES ('ROLE_APPROVE',  '承認者', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE VALUES ('ROLE_ADMIN',    '管理者', '', 0, 0, now(), 'system', now(), 'system');
commit;
