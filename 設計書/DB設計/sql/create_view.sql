create view V_M_USER_ROLE as
select
    A.user_id
  , A.role_id
  , B.role_nm
  , B.role
  , A.version
  , A.del_flg
  , A.ins_date
  , A.ins_id
  , A.upd_date
  , A.upd_id
from
  m_user_role A
  left join m_role B
    on A.role_id = B.role_id
