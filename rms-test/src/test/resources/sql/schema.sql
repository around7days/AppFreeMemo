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

--
-- お知らせテーブル
--
drop table if exists T_INFOMATION cascade;
create table T_INFOMATION (
  seq int not null comment '連番'
  , info varchar(4096) comment 'お知らせ情報'
  , version int not null comment 'バージョン'
  , del_flg int not null comment '削除フラグ'
  , ins_date datetime not null comment '登録日時'
  , ins_id varchar(20) not null comment '登録ID'
  , upd_date datetime not null comment '更新日時'
  , upd_id varchar(20) not null comment '更新ID'
  , constraint T_INFOMATION_PKC primary key (seq)
) comment 'お知らせテーブル' ;


--
-- ユーザマスタビュー
--
create or replace view V_M_USER as
select
    U.user_id                                     -- ユーザID
  , U.user_nm                                     -- ユーザ名
  , U.password                                    -- パスワード
  , U.email                                       -- メールアドレス
  , U.department_id                               -- 部署ID
  , D001.code_nm as department_nm                 -- 部署名
  , D001.attr1  as department_rnm                 -- 部署略称
  , F1.approve_user_id as approve_user_id1        -- 承認者ID1
  , (select X.user_nm from M_USER X where X.user_id = F1.approve_user_id) as approve_user_nm1 -- 承認者名1
  , F2.approve_user_id as approve_user_id2        -- 承認者ID2
  , (select X.user_nm from M_USER X where X.user_id = F2.approve_user_id) as approve_user_nm2 -- 承認者名2
  , F3.approve_user_id as approve_user_id3        -- 承認者ID3
  , (select X.user_nm from M_USER X where X.user_id = F3.approve_user_id) as approve_user_nm3 -- 承認者名3
  , F4.approve_user_id as approve_user_id4        -- 承認者ID4
  , (select X.user_nm from M_USER X where X.user_id = F4.approve_user_id) as approve_user_nm4 -- 承認者名4
  , U.version                                     -- バージョン
  , U.del_flg                                     -- 削除フラグ
  , U.ins_date                                    -- 登録日時
  , U.ins_id                                      -- 登録ID
  , U.upd_date                                    -- 更新日時
  , U.upd_id                                      -- 更新ID
from
  M_USER U
  left join M_USER_APPROVE_FLOW F1
    on U.user_id = F1.user_id
    and F1.approve_seq = 1
  left join M_USER_APPROVE_FLOW F2
    on U.user_id = F2.user_id
    and F2.approve_seq = 2
  left join M_USER_APPROVE_FLOW F3
    on U.user_id = F3.user_id
    and F3.approve_seq = 3
  left join M_USER_APPROVE_FLOW F4
    on U.user_id = F4.user_id
    and F4.approve_seq = 4
  left join M_CODE D001
    on D001.code_kbn = 'D001'
    and U.department_id = D001.code
;


--
-- ユーザ役割マスタビュー
--
create or replace view V_M_USER_ROLE as
select
    A.user_id                                     -- ユーザID
  , A.role                                        -- 役割
  , B.role_nm                                     -- 役割名
  , A.version                                     -- バージョン
  , A.del_flg                                     -- 削除フラグ
  , A.ins_date                                    -- 登録日時
  , A.ins_id                                      -- 登録ID
  , A.upd_date                                    -- 更新日時
  , A.upd_id                                      -- 更新ID
from
  M_USER_ROLE A
  inner join M_ROLE B
    on A.role = B.role
;


--
-- 月報テーブルビュー
--
create or replace view V_T_REPORT as
select
    A.apply_user_id                               -- 申請者ID
  , U.user_nm   as apply_user_nm                  -- 申請者名
  , A.target_ym                                   -- 年月
  , A.apply_date                                  -- 申請日
  , U.department_id                               -- 部署ID
  , D001.code_nm as department_nm                 -- 部署名
  , D001.attr1 as department_rnm                  -- 部署略称
  , A.publish_flg                                 -- 公開有無
  , B001.code_nm as publish_flg_nm                -- 公開有無名称
  , F1.approve_user_id as approve_user_id1        -- 承認者ID1
  , (select X.user_nm from M_USER X where X.user_id = F1.approve_user_id) as approve_user_nm1 -- 承認者名1
  , F1.approve_date as approve_date1              -- 承認日1
  , F2.approve_user_id as approve_user_id2        -- 承認者ID2
  , (select X.user_nm from M_USER X where X.user_id = F2.approve_user_id) as approve_user_nm2 -- 承認者名2
  , F2.approve_date as approve_date2              -- 承認日2
  , F3.approve_user_id as approve_user_id3        -- 承認者ID3
  , (select X.user_nm from M_USER X where X.user_id = F3.approve_user_id) as approve_user_nm3 -- 承認者名3
  , F3.approve_date as approve_date3              -- 承認日3
  , F4.approve_user_id as approve_user_id4        -- 承認者ID4
  , (select X.user_nm from M_USER X where X.user_id = F4.approve_user_id) as approve_user_nm4 -- 承認者名4
  , F4.approve_date as approve_date4              -- 承認日4
  , A.file_path                                   -- 月報ファイルパス
  , A.comment as apply_user_comment               -- 申請者コメント
  , A.status                                      -- 承認状況
  , A001.code_nm as status_nm                     -- 承認状況名称
  , A.version                                     -- バージョン
  , A.del_flg                                     -- 削除フラグ
  , A.ins_date                                    -- 登録日時
  , A.ins_id                                      -- 登録ID
  , A.upd_date                                    -- 更新日時
  , A.upd_id                                      -- 更新ID
from
  T_REPORT A
  inner join M_USER U
    on A.apply_user_id = U.user_id
  left join T_REPORT_APPROVE_FLOW F1
    on A.apply_user_id = F1.apply_user_id
    and A.target_ym = F1.target_ym
    and F1.approve_seq = 1
  left join T_REPORT_APPROVE_FLOW F2
    on A.apply_user_id = F2.apply_user_id
    and A.target_ym = F2.target_ym
    and F2.approve_seq = 2
  left join T_REPORT_APPROVE_FLOW F3
    on A.apply_user_id = F3.apply_user_id
    and A.target_ym = F3.target_ym
    and F3.approve_seq = 3
  left join T_REPORT_APPROVE_FLOW F4
    on A.apply_user_id = F4.apply_user_id
    and A.target_ym = F4.target_ym
    and F4.approve_seq = 4
  inner join M_CODE A001
    on A001.code_kbn = 'A001'
    and A.status = A001.code
  left join M_CODE B001
    on B001.code_kbn = 'B001'
    and A.publish_flg = B001.code
  left join M_CODE D001
    on D001.code_kbn = 'D001'
    and D001.code = U.department_id
;
