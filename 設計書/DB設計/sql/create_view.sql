--
--ユーザマスタ情報ビュー
--
create or replace view V_M_USER as
select
    U.user_id                                     -- ユーザID
  , U.user_nm                                     -- ユーザ名
  , U.password                                    -- パスワード
  , U.email                                       -- メールアドレス
  , U.approve_user_id1                            -- 承認者ID1
  , A1.user_nm as approve_user_nm1                -- 承認者名1
  , U.approve_user_id2                            -- 承認者ID2
  , A2.user_nm as approve_user_nm2                -- 承認者名2
  , U.approve_user_id3                            -- 承認者ID3
  , A3.user_nm as approve_user_nm3                -- 承認者名3
  , U.version                                     -- バージョン
  , U.del_flg                                     -- 削除フラグ
  , U.ins_date                                    -- 登録日時
  , U.ins_id                                      -- 登録ID
  , U.upd_date                                    -- 更新日時
  , U.upd_id                                      -- 更新ID
from
  m_user U
  left join m_user A1
    on U.approve_user_id1 = A1.user_id
  left join m_user A2
    on U.approve_user_id2 = A2.user_id
  left join m_user A3
    on U.approve_user_id3 = A3.user_id
;


--
--ユーザ役割マスタ情報ビュー
--
create or replace view V_M_USER_ROLE as
select
    A.user_id                                     -- ユーザID
  , B.role                                        -- 役割
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
--月報管理テーブルビュー
--
create or replace view V_T_REPORT as
select
    A.apply_user_id                               -- 申請者ID
  , U0.user_nm   as apply_user_nm                 -- 申請者名
  , A.target_ym                                   -- 年月
  , A.apply_date                                  -- 申請日
  , A.publish_flg                                 -- 公開有無
  , B001.code_nm as publish_nm                    -- 公開有無名
  , A.status                                      -- 承認状況
  , A001.code_nm as status_nm                     -- 承認状況名
  , A.approve_user_id1                            -- 承認者ID1
  , U1.user_nm   as approve_user_nm1              -- 承認者名1
  , A.approve_user_id2                            -- 承認者ID2
  , U2.user_nm   as approve_user_nm2              -- 承認者名2
  , A.approve_user_id3                            -- 承認者ID3
  , U3.user_nm   as approve_user_nm3              -- 承認者名3
  , A.file_path                                   -- 月報ファイルパス
  , A.version                                     -- バージョン
  , A.del_flg                                     -- 削除フラグ
  , A.ins_date                                    -- 登録日時
  , A.ins_id                                      -- 登録ID
  , A.upd_date                                    -- 更新日時
  , A.upd_id                                      -- 更新ID
from
  t_report A
  left join m_user U0
    on A.apply_user_id = U0.user_id
  left join m_user U1
    on A.approve_user_id1 = U1.user_id
  left join m_user U2
    on A.approve_user_id2 = U2.user_id
  left join m_user U3
    on A.approve_user_id3 = U3.user_id
  left join m_code A001
    on A.status = A001.code
    and A001.code_kbn = 'A001'
  left join m_code B001
    on A.publish_flg = B001.code
    and B001.code_kbn = 'B001'
;