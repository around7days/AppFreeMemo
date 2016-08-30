DELETE FROM M_CODE;
--承認状況：A001
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'AAA', '未提出'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'Y01', '承認待ち１', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'Y02', '承認待ち２', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'Y03', '承認待ち３', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'N01', '否認１'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'N02', '否認２'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'N03', '否認３'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'A001', '承認状況', 'ZZZ', '承認済み'  , '', '', '', 0, 0, now(), 'system', now(), 'system');
--公開有無：B001
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'B001', '公開有無', '0', '非公開', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE( code_kbn, code_kbn_nm, code, code_nm, attr1, attr2, attr3, version, del_flg, ins_date, ins_id, upd_date, upd_id) VALUES ( 'B001', '公開有無', '1', '公開'  , '', '', '', 0, 0, now(), 'system', now(), 'system');
commit;
