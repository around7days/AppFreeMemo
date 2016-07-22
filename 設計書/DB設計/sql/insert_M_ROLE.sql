DELETE FROM M_ROLE;
INSERT INTO M_ROLE(role, role_nm, description, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('ROLE_APPLICANT', '申請者', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE(role, role_nm, description, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('ROLE_APPROVER',  '承認者', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE(role, role_nm, description, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('ROLE_ADMIN',     '管理者', '', 0, 0, now(), 'system', now(), 'system');
commit;
