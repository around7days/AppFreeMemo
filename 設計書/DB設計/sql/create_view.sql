--ユーザマスタ情報ビュー
create or replace view V_M_USER as
SELECT
  U.user_id
  , U.user_nm
  , U.password
  , U.email
  , U.approver1_id
  , A1.user_nm as approver1_nm
  , U.approver2_id
  , A2.user_nm as approver2_nm
  , U.approver3_id
  , A3.user_nm as approver3_nm
  , U.version
  , U.del_flg
  , U.ins_date
  , U.ins_id
  , U.upd_date
  , U.upd_id
FROM
  m_user U
  left join m_user A1
    on U.approver1_id = A1.user_id
  left join m_user A2
    on U.approver2_id = A2.user_id
  left join m_user A3
    on U.approver3_id = A3.user_id
;


--ユーザ役割マスタ情報ビュー
create or replace view V_M_USER_ROLE as
select
    A.user_id
  , B.role
  , B.role_nm
  , A.version
  , A.del_flg
  , A.ins_date
  , A.ins_id
  , A.upd_date
  , A.upd_id
from
  m_user_role A
  inner join m_role B
    on A.role = B.role
;

