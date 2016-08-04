--
-- ユーザマスタ
--
drop table if exists M_USER cascade;
create table M_USER (
  user_id varchar(20) not null comment 'ユーザID'
  , user_nm varchar(255) not null comment 'ユーザ名'
  , password varchar(255) not null comment 'パスワード'
  , email varchar(255) comment 'メールアドレス'
  , approve_user_id1 varchar(20) comment '承認者ID1'
  , approve_user_id2 varchar(20) comment '承認者ID2'
  , approve_user_id3 varchar(20) comment '承認者ID3'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date timestamp not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date timestamp not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_USER_PKC primary key (user_id)
) comment 'ユーザマスタ' ;


--
-- 役割マスタ
--
drop table if exists M_ROLE cascade;
create table M_ROLE (
  role varchar(40) not null comment '役割'
  , role_nm varchar(255) not null comment '役割名'
  , description varchar(255) comment '説明'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date timestamp not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date timestamp not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_ROLE_PKC primary key (role)
) comment '役割マスタ' ;


--
-- ユーザ役割マスタ
--
drop table if exists M_USER_ROLE cascade;
create table M_USER_ROLE (
  user_id varchar(20) not null comment 'ユーザID'
  , role varchar(40) not null comment '役割'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date timestamp not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date timestamp not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_USER_ROLE_PKC primary key (user_id,role)
) comment 'ユーザ役割マスタ' ;


--
-- コードマスタ
--
drop table if exists M_CODE cascade;
create table M_CODE (
  code_kbn varchar(20) not null comment 'コード区分'
  , code_kbn_nm varchar(255) not null comment 'コード区分名称'
  , code varchar(20) not null comment 'コード'
  , code_nm varchar(255) not null comment 'コード名称'
  , attr1 varchar(255) comment '属性１'
  , attr2 varchar(255) comment '属性２'
  , attr3 varchar(255) comment '属性３'
  , description varchar(255) comment '説明'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date timestamp not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date timestamp not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_CODE_PKC primary key (code_kbn,code)
) comment 'コードマスタ' ;


--
-- 月報管理テーブル
--
drop table if exists T_REPORT cascade;
create table T_REPORT (
  apply_user_id varchar(20) not null comment '申請者ID'
  , target_ym int not null comment '対象年月'
  , apply_date timestamp not null comment '申請日'
  , publish_flg varchar(1) not null comment '公開有無'
  , status varchar(3) not null comment '承認状況'
  , approve_user_id1 varchar(20) comment '承認者ID1'
  , approve_user_id2 varchar(20) comment '承認者ID2'
  , approve_user_id3 varchar(20) not null comment '承認者ID3'
  , file_path varchar(255) not null comment '月報ファイルパス'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date timestamp not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date timestamp not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint T_REPORT_PKC primary key (apply_user_id,target_ym)
) comment '月報管理テーブル' ;


--
-- 月報管理テーブル（履歴）
--
drop table if exists T_REPORT_HIS cascade;
create table T_REPORT_HIS (
  apply_user_id varchar(20) not null comment '申請者ID'
  , target_ym int not null comment '対象年月'
  , seq int not null comment '連番'
  , apply_date timestamp not null comment '申請日'
  , publish_flg varchar(1) not null comment '公開有無'
  , status varchar(3) not null comment '承認状況'
  , approve_user_id1 varchar(20) comment '承認者ID1'
  , approve_user_id2 varchar(20) comment '承認者ID2'
  , approve_user_id3 varchar(20) not null comment '承認者ID3'
  , file_path varchar(255) not null comment '月報ファイルパス'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date timestamp not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date timestamp not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint T_REPORT_HIS_PKC primary key (apply_user_id,target_ym,seq)
) comment '月報管理テーブル（履歴）' ;
