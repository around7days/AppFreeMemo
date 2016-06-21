create table m_user (
	user_id		varchar(20)		comment 'ユーザーID'	primary key,
	user_nm		varchar(255)	comment 'ユーザー名'		not null,
	password	varchar(255)	comment 'パスワード'		not null,
	email		varchar(255)	comment 'メールアドレス',
	del_flg		int				comment '削除フラグ'		not null,
	ins_date	timestamp		comment '登録日時',
	ins_id		varchar(20)		comment '登録ID',
	upd_date	timestamp		comment '更新日時',
	upd_id 		varchar(20)		comment '更新ID'
)
comment='ユーザーマスタ'
;

