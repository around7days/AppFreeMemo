--------------------------------------------
--論理名：月報管理テーブル
--物理名：t_report
--------------------------------------------
drop table if exists t_report;
create table t_report (
	applicant_id			varchar(20)		comment '申請者ID'					not null,
	target_ym					int						comment '対象年月(yyyymm)'	not null,
	applicant_date		timestamp			comment '申請日'						not null,
	status						varchar(3)		comment '承認状況' 					not null,
	approver1_id			varchar(20)		comment '承認者１ID',
	approver2_id			varchar(20)		comment '承認者２ID',
	approver3_id			varchar(20)		comment '承認者３ID'				not null,
	file_path					varchar(100)	comment '月報ファイルパス'	not null,
	version						int						comment 'バージョン'				not null,
	del_flg						int						comment '削除フラグ'				not null,
	ins_date					timestamp			comment '登録日時'					not null,
	ins_id						varchar(20)		comment '登録ID'						not null,
	upd_date					timestamp			comment '更新日時'					not null,
	upd_id 						varchar(20)		comment '更新ID'						not null
)
comment='月報管理テーブル'
;
alter table t_report add primary key(applicant_id, target_ym)
;

