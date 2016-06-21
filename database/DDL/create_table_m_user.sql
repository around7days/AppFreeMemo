create table m_user (
	user_id		varchar(20)		comment 'ユーザーID'		primary key,
	user_nm		varchar(255)	comment 'ユーザー名'		not null,
	password	varchar(255)	comment 'パスワード'		not null,
	email		varchar(255)	comment 'メールアドレス',
	del_flg		int(1)			comment '削除フラグ'		not null,
	ins_date	timestamp		comment '登録日時'			not null,
	ins_id		varchar(20)		comment '登録ID'			not null,
	upd_date	timestamp		comment '更新日時'			not null,
	upd_id 		varchar(20)		comment '更新ID'			not null
)
comment='ユーザーマスタ'
;
