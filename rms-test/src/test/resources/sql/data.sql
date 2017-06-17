--
-- M_CODE
--
DELETE FROM M_CODE;
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'AAA', '未提出'    , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'Y01', '承認待ち１', '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'Y02', '承認待ち２', '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'Y03', '承認待ち３', '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'Y04', '承認待ち４', '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'N01', '否認１'    , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'N02', '否認２'    , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'N03', '否認３'    , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'N04', '否認４'    , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('A001', '月報承認状況', 'ZZZ', '承認済み'  , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('B001', '月報公開有無', '0', '非公開', '', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('B001', '月報公開有無', '1', '公開'  , '', '', '', '', 0, 0, now(), 'system', now(), 'system');
-- INSERT INTO M_CODE VALUES ('C001', '承認区分', 'Y', '承認', '', '', '', 0, 0, now(), 'system', now(), 'system');
-- INSERT INTO M_CODE VALUES ('C001', '承認区分', 'N', '否認'  , '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('D001', '部署', '1',  '第一ソリューション部',   '1SOL', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('D001', '部署', '2',  '第二ソリューション部',   '2SOL', '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('D001', '部署', '3',  '第一ソフトウェア開発部', '1PS',  '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('D001', '部署', '4',  '第二ソフトウェア開発部', '2PS',  '', '', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_CODE VALUES ('D001', '部署', '99', '全社',                   '全社', '', '', '', 0, 0, now(), 'system', now(), 'system');


--
-- M_ROLE
--
DELETE FROM M_ROLE;
INSERT INTO M_ROLE VALUES ('ROLE_APPLY',    '申請者', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE VALUES ('ROLE_APPROVE',  '承認者', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_ROLE VALUES ('ROLE_ADMIN',    '管理者', '', 0, 0, now(), 'system', now(), 'system');



--
-- M_USER
--
DELETE FROM M_USER;
INSERT INTO M_USER VALUES ('user01', '申請者０１', 'pass', 'xxx@xxx.xx', '1', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user02', '申請者０２', 'pass', 'xxx@xxx.xx', '1', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user03', '申請者０３', 'pass', 'xxx@xxx.xx', '2', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user04', '申請者０４', 'pass', 'xxx@xxx.xx', '2', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user05', '申請者０５', 'pass', 'xxx@xxx.xx', '2', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user06', '承認者０１', 'pass', 'xxx@xxx.xx', '1', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user07', '承認者０２', 'pass', 'xxx@xxx.xx', '1', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user08', '承認者０３', 'pass', 'xxx@xxx.xx', '1', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user09', '承認者０４', 'pass', 'xxx@xxx.xx', '2', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user10', '承認者０５', 'pass', 'xxx@xxx.xx', '2', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER VALUES ('user11', '管理者０１', 'pass', 'xxx@xxx.xx', '1', 0, 0, now(), 'system', now(), 'system');



--
-- M_USER_ROLE
--
DELETE FROM M_USER_ROLE;
INSERT INTO M_USER_ROLE VALUES ('user01', 'ROLE_APPLY', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user02', 'ROLE_APPLY', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user03', 'ROLE_APPLY', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user04', 'ROLE_APPLY', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user05', 'ROLE_APPLY', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user06', 'ROLE_APPROVE', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user07', 'ROLE_APPROVE', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user08', 'ROLE_APPROVE', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user09', 'ROLE_APPROVE', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user10', 'ROLE_APPROVE', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_ROLE VALUES ('user11', 'ROLE_ADMIN', 0, 0, now(), 'system', now(), 'system');



--
-- M_USER_APPROVE_FLOW
--
DELETE FROM M_USER_APPROVE_FLOW;
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user01', 1, 'user06', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user01', 2, 'user07', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user01', 3, 'user08', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user01', 3, 'user09', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user02', 2, 'user07', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user02', 3, 'user08', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user03', 3, 'user08', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user04', 3, 'user09', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user05', 1, 'user08', 0, 0, now(), 'system', now(), 'system');
INSERT INTO M_USER_APPROVE_FLOW VALUES ('user05', 3, 'user09', 0, 0, now(), 'system', now(), 'system');



--
-- T_REPORT
--
DELETE FROM T_REPORT;
INSERT INTO T_REPORT VALUES ('user01', 201607, '2016-07-25', '0', '', 'コメント', 'ZZZ', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT VALUES ('user01', 201608, '2016-08-25', '1', '', 'コメント', 'Y03', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT VALUES ('user01', 201609, '2016-09-25', '1', '', 'コメント', 'N01', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT VALUES ('user02', 201609, '2016-09-25', '1', '', 'コメント', 'ZZZ', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT VALUES ('user03', 201609, '2016-09-25', '1', '', 'コメント', 'Y03', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT VALUES ('user04', 201609, '2016-09-25', '1', '', 'コメント', 'ZZZ', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT VALUES ('user05', 201609, '2016-09-25', '1', '', 'コメント', 'ZZZ', 0, 0, now(), 'system', now(), 'system');



--
-- T_REPORT_APPROVE_FLOW
--
DELETE FROM T_REPORT_APPROVE_FLOW;
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201607', '1', 'user06', '2016-07-25', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201607', '2', 'user07', '2016-07-26', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201607', '3', 'user08', '2016-07-27', '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201608', '1', 'user06', '2016-08-25', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201608', '2', 'user07', '2016-08-26', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201608', '3', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201609', '1', 'user06', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201609', '2', 'user07', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user01', '201609', '3', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user02', '201609', '1', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user02', '201609', '2', 'user07', '2016-09-26', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user02', '201609', '3', 'user08', '2016-09-27', '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user03', '201609', '1', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user03', '201609', '2', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user03', '201609', '3', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user04', '201609', '1', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user04', '201609', '2', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user04', '201609', '3', 'user09', '2016-09-27', '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user05', '201609', '1', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user05', '201609', '2', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO T_REPORT_APPROVE_FLOW VALUES ('user05', '201609', '3', 'user09', '2016-09-27', '', 0, 0, now(), 'system', now(), 'system');
