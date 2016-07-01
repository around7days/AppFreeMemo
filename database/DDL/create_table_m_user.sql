--------------------------------------------
--論理名：ユーザマスタ
--物理名：m_user
--------------------------------------------
drop table m_user;
create table m_user (
	applicant_id		varchar(20)		comment 'ユーザID'			primary key,
	user_nm					varchar(255)	comment 'ユーザ名'			not null,
	password				varchar(255)	comment 'パスワード'			not null,
	email						varchar(255)	comment 'メールアドレス',
	del_flg					int(1)				comment '削除フラグ'			not null,
	ins_date				timestamp			comment '登録日時'				not null,
	ins_id					varchar(20)		comment '登録ID'					not null,
	upd_date				timestamp			comment '更新日時'				not null,
	upd_id 					varchar(20)		comment '更新ID'					not null
)
comment='ユーザマスタ'
;
