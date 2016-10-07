--
--ユーザマスタビュー
--
create or replace view V_M_USER as
select
    U.user_id                                     -- ユーザID
  , U.user_nm                                     -- ユーザ名
  , U.password                                    -- パスワード
  , U.email                                       -- メールアドレス
  , U.department_id                                -- 部署ID
  , D001.code_nm as department_nm                  -- 部署名
  , D001.attr1  as department_rnm                  -- 部署略称
  , F1.approve_user_id as approve_user_id1        -- 承認者ID1
  , (select X.user_nm from m_user X where X.user_id = F1.approve_user_id) as approve_user_nm1 -- 承認者名1
  , F2.approve_user_id as approve_user_id2        -- 承認者ID2
  , (select X.user_nm from m_user X where X.user_id = F2.approve_user_id) as approve_user_nm2 -- 承認者名2
  , F3.approve_user_id as approve_user_id3        -- 承認者ID3
  , (select X.user_nm from m_user X where X.user_id = F3.approve_user_id) as approve_user_nm3 -- 承認者名3
  , U.version                                     -- バージョン
  , U.del_flg                                     -- 削除フラグ
  , U.ins_date                                    -- 登録日時
  , U.ins_id                                      -- 登録ID
  , U.upd_date                                    -- 更新日時
  , U.upd_id                                      -- 更新ID
from
  m_user U
  left join m_user_approve_flow F1
    on U.user_id = F1.user_id
    and F1.approve_seq = 1
  left join m_user_approve_flow F2
    on U.user_id = F2.user_id
    and F2.approve_seq = 2
  left join m_user_approve_flow F3
    on U.user_id = F3.user_id
    and F3.approve_seq = 3
  left join m_code D001
    on D001.code_kbn = 'D001'
    and U.department_id = D001.code
;


--
--ユーザ役割マスタビュー
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
  m_user_role A
  inner join m_role B
    on A.role = B.role
;


--
--月報テーブルビュー
--
create or replace view V_T_REPORT as
select
    A.apply_user_id                               -- 申請者ID
  , U.user_nm   as apply_user_nm                  -- 申請者名
  , A.target_ym                                   -- 年月
  , A.apply_date                                  -- 申請日
  , A.publish_flg                                 -- 公開有無
  , B001.code_nm as publish_flg_nm                -- 公開有無名称
  , F1.approve_user_id as approve_user_id1        -- 承認者ID1
  , (select X.user_nm from m_user X where X.user_id = F1.approve_user_id) as approve_user_nm1 -- 承認者名1
  , F1.approve_date as approve_date1              -- 承認日1
  , F2.approve_user_id as approve_user_id2        -- 承認者ID2
  , (select X.user_nm from m_user X where X.user_id = F2.approve_user_id) as approve_user_nm2 -- 承認者名2
  , F2.approve_date as approve_date2              -- 承認日2
  , F3.approve_user_id as approve_user_id3        -- 承認者ID3
  , (select X.user_nm from m_user X where X.user_id = F3.approve_user_id) as approve_user_nm3 -- 承認者名3
  , F3.approve_date as approve_date3              -- 承認日3
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
  t_report A
  inner join m_user U
    on A.apply_user_id = U.user_id
  left join t_report_approve_flow F1
    on A.apply_user_id = F1.apply_user_id
    and A.target_ym = F1.target_ym
    and F1.approve_seq = 1
  left join t_report_approve_flow F2
    on A.apply_user_id = F2.apply_user_id
    and A.target_ym = F2.target_ym
    and F2.approve_seq = 2
  left join t_report_approve_flow F3
    on A.apply_user_id = F3.apply_user_id
    and A.target_ym = F3.target_ym
    and F3.approve_seq = 3
  inner join m_code A001
    on A001.code_kbn = 'A001'
    and A.status = A001.code
  left join m_code B001
    on B001.code_kbn = 'B001'
    and A.publish_flg = B001.code
;