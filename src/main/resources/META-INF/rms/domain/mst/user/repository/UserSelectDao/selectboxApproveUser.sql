select
    A.user_id as 'key'
  , A.user_nm as 'value'
from
  m_user A
  inner join m_user_role B
    on A.user_id = B.user_id
    and B.role = 'ROLE_APPROVE'
where
  A.del_flg = 0
order by
  A.user_id
