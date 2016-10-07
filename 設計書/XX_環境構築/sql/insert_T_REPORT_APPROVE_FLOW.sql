DELETE FROM T_REPORT_APPROVE_FLOW;
INSERT INTO t_report_approve_flow VALUES ('user01', '201607', '1', 'user06', '2016-07-25', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user01', '201607', '2', 'user07', '2016-07-26', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user01', '201607', '3', 'user08', '2016-07-27', '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO t_report_approve_flow VALUES ('user01', '201608', '1', 'user06', '2016-08-25', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user01', '201608', '2', 'user07', '2016-08-26', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user01', '201608', '3', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO t_report_approve_flow VALUES ('user01', '201609', '1', 'user06', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user01', '201609', '2', 'user07', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user01', '201609', '3', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO t_report_approve_flow VALUES ('user02', '201609', '1', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user02', '201609', '2', 'user07', '2016-09-26', '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user02', '201609', '3', 'user08', '2016-09-27', '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO t_report_approve_flow VALUES ('user03', '201609', '1', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user03', '201609', '2', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user03', '201609', '3', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO t_report_approve_flow VALUES ('user04', '201609', '1', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user04', '201609', '2', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user04', '201609', '3', 'user09', '2016-09-27', '', 0, 0, now(), 'system', now(), 'system');

INSERT INTO t_report_approve_flow VALUES ('user05', '201609', '1', 'user08', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user05', '201609', '2', '', null, '', 0, 0, now(), 'system', now(), 'system');
INSERT INTO t_report_approve_flow VALUES ('user05', '201609', '3', 'user09', '2016-09-27', '', 0, 0, now(), 'system', now(), 'system');
commit;
