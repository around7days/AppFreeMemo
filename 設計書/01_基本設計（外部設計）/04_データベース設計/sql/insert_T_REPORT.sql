DELETE FROM T_REPORT;
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, file_path, comment, status, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201601, '2016-01-25', '0', '', 'コメント', 'ZZZ', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, file_path, comment, status, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user01', 201609, '2016-09-25', '1', '', 'コメント', 'Y01', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT(apply_user_id, target_ym, apply_date, publish_flg, file_path, comment, status, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ('user02', 201609, '2016-09-25', '1', '', 'コメント', 'ZZZ', 0, 0, now(), 'system', now(), 'system');
commit;
