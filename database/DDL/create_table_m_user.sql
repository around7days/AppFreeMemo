--------------------------------------------
--論理名：ユーザマスタ
--物理名：m_user
--------------------------------------------
drop table if exists m_user;
create table m_user (
	user_id					varchar(20)		comment 'ユーザID'				primary key,
	user_nm					varchar(255)	comment 'ユーザ名'				not null,
	password				varchar(255)	comment 'パスワード'			not null,
	email						varchar(255)	comment 'メールアドレス',
	applicant_kbn		varchar(255)	comment '申請者区分',
	approver_kbn		varchar(255)	comment '承認者区分',
	admin_kbn				varchar(255)	comment '管理者区分',
	version					int						comment 'バージョン'			not null,
	del_flg					int						comment '削除フラグ'			not null,
	ins_date				timestamp			comment '登録日時'				not null,
	ins_id					varchar(20)		comment '登録ID'					not null,
	upd_date				timestamp			comment '更新日時'				not null,
	upd_id 					varchar(20)		comment '更新ID'					not null
)
comment='ユーザマスタ'
;
