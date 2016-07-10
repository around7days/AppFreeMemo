--------------------------------------------
--論理名：コードマスタ
--物理名：m_code
--------------------------------------------
drop table if exists m_code;
create table m_code (
	code_kbn			varchar(20)		comment 'コード区分'			not null,
	code_kbn_nm		varchar(255)	comment 'コード区分名称'	not null,
	code					varchar(20)		comment 'コード'					not null,
	code_nm				varchar(255)	comment 'コード名称'			not null,
	attr1					varchar(255)	comment '属性１',
	attr2					varchar(255)	comment '属性２',
	attr3					varchar(255)	comment '属性３',
	del_flg				int(1)				comment '削除フラグ'			not null,
	ins_date			timestamp			comment '登録日時'				not null,
	ins_id				varchar(20)		comment '登録ID'					not null,
	upd_date			timestamp			comment '更新日時'				not null,
	upd_id 				varchar(20)		comment '更新ID'					not null
)
comment='コードマスタ'
;
alter table m_code add primary key(code_kbn, code)
;
