DELETE FROM M_ROLE;
INSERT INTO M_ROLE(role_id, role_nm, role, description, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES (1, '申請者', 'ROLE_APPLICANT', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE(role_id, role_nm, role, description, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES (2, '承認者', 'ROLE_APPROVER',  '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE(role_id, role_nm, role, description, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES (3, '管理者', 'ROLE_ADMIN',     '', 0, 0, now(), 'system', now(), 'system');
commit;
