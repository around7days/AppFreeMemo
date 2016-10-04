DELETE FROM M_CODE;
--月報承認状況：A001
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'AAA', '未提出'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'Y01', '承認待ち１', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'Y02', '承認待ち２', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'Y03', '承認待ち３', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'N01', '否認１'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'N02', '否認２'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'N03', '否認３'    , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'A001', '承認状況', 'ZZZ', '承認済み'  , '', '', '', 0, 0, now(), 'system', now(), 'system');
--月報公開有無：B001
INSERT INTO M_CODE VALUES ( 'B001', '公開有無', '0', '非公開', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'B001', '公開有無', '1', '公開'  , '', '', '', 0, 0, now(), 'system', now(), 'system');
--承認区分：C001
--INSERT INTO M_CODE VALUES ( 'C001', '承認区分', 'Y', '承認', '', '', '', 0, 0, now(), 'system', now(), 'system');
--INSERT INTO M_CODE VALUES ( 'C001', '承認区分', 'N', '否認'  , '', '', '', 0, 0, now(), 'system', now(), 'system');
--部署：D001
INSERT INTO M_CODE VALUES ( 'D001', '部署', '1', '第一ソリューション部', '1SOL', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'D001', '部署', '2', '第二ソリューション部', '2SOL', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'D001', '部署', '3', '第一ソフトウェア開発部', '1PS', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ( 'D001', '部署', '4', '第二ソフトウェア開発部', '2PS', '', '', 0, 0, now(), 'system', now(), 'system');
commit;
