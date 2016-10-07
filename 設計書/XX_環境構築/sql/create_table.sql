--
-- ユーザマスタ
--
drop table if exists M_USER cascade;
create table M_USER (
  user_id varchar(20) not null comment 'ユーザID'
  , user_nm varchar(255) not null comment 'ユーザ名'
  , password varchar(255) not null comment 'パスワード'
  , email varchar(255) comment 'メールアドレス'
  , department_id varchar(20) comment '部署ID   コードマスタ：D001'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_USER_PKC primary key (user_id)
) comment 'ユーザマスタ' ;


--
-- ユーザ承認フローマスタ
--
drop table if exists M_USER_APPROVE_FLOW cascade;
create table M_USER_APPROVE_FLOW (
  user_id varchar(20) not null comment 'ユーザID'
  , approve_seq int not null comment '承認SEQ'
  , approve_user_id varchar(20) not null comment '承認者ID'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_USER_APPROVE_FLOW_PKC primary key (user_id,approve_seq)
) comment 'ユーザ承認フローマスタ' ;


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
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
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
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
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
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint M_CODE_PKC primary key (code_kbn,code)
) comment 'コードマスタ' ;


--
-- 月報テーブル
--
drop table if exists T_REPORT cascade;
create table T_REPORT (
  apply_user_id varchar(20) not null comment '申請者ID'
  , target_ym int not null comment '対象年月'
  , apply_date datetime comment '申請日'
  , publish_flg varchar(1) comment '公開有無   コードマスタ：B001'
  , file_path varchar(255) comment '月報ファイルパス'
  , comment varchar(255) comment 'コメント'
  , status varchar(3) not null comment '承認状況   コードマスタ：A001'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint T_REPORT_PKC primary key (apply_user_id,target_ym)
) comment '月報テーブル' ;


--
-- 月報承認フローテーブル
--
drop table if exists T_REPORT_APPROVE_FLOW cascade;
create table T_REPORT_APPROVE_FLOW (
  apply_user_id varchar(20) not null comment '申請者ID'
  , target_ym int not null comment '対象年月'
  , approve_seq int not null comment '承認SEQ'
  , approve_user_id varchar(20) not null comment '承認者ID'
--  , approve_kbn varchar(1) comment '承認区分  コードマスタ：C001'
  , approve_date datetime comment '承認日'
  , comment varchar(255) comment 'コメント'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint T_REPORT_APPROVE_FLOW_PKC primary key (apply_user_id,target_ym,approve_seq)
) comment '月報承認フローテーブル' ;

